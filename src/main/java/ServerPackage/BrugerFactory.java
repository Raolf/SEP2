package ServerPackage;
import Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BrugerFactory {
    private Bruger bruger;
    private Superbruger superbruger;
    private SuperBogListe Liste;
    private SingleBrugerListe singleBrugerListe;
    String DBpass = "Juh88bxr";

    public BrugerFactory(SuperBogListe Liste){
        this.Liste = Liste;
    }

    public void hentBruger(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", DBpass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Bibliotek\".bruger;");
            while (rs.next()) {
                String brugernavn= rs.getString("brugernavn");
                String password= rs.getString("password");
                int brugerID=rs.getInt("brugerid");
                boolean issuperbruger=rs.getBoolean("superbruger");

                LoginInf loginInf=new LoginInf(brugernavn,password);
                Bruger bruger=new Bruger(loginInf, hentBrugerBooks(loginInf), brugerID, Liste);

                BrugerListe singleBrugerListe= SingleBrugerListe.getInstance().getBrugerListe();

                singleBrugerListe.addBruger(bruger);

                System.out.println("Brugernavn = " + brugernavn);
                System.out.println("Password = " + password);
                System.out.println("BrugerID = " + brugerID);
                System.out.println("SuperBruger = " + issuperbruger);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database query ok ");
    }

    public Bogliste hentBrugerBooks(LoginInf loginInf){
        Connection c = null;
        Statement stmt = null;
        ArrayList<Bog> arrayList= new ArrayList<Bog>();
        Bogliste bogliste=new Bogliste(arrayList);
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", DBpass);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Bibliotek\".bruger;");

            while (rs.next() && new LoginValidation().validerLogin(loginInf) != 0){

                if(loginInf.getBrugernavn().equals(rs.getString("brugernavn"))){
                    String str = rs.getString("books");
                    if(str==null){
                        System.out.println("Har ingen bøger");
                    }
                    else{
                        String[] arrOfStr = str.split(":");

                        for (String a : arrOfStr){
                            Bog bog = Liste.getBog(Integer.parseInt(a)-1);
                            bogliste.addBog(bog);
                            System.out.println(bog.getTitel());
                        }
                    }
                }
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Database query ok ");
        return bogliste;
    }

    public void lavBruger(Bruger bruger){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", DBpass);

            System.out.println("Database open ok");

            System.out.println(bruger.getLoginfo().toString());

            if(bruger.getClass().getSimpleName().equals("Bruger")){
                stmt = c.createStatement();
                String sql = "INSERT INTO \"Bibliotek\".bruger values ('" + bruger.getLoginfo().getBrugernavn() + "','" + bruger.getLoginfo().getPassword() + "','" + bruger.getBrugerID() + "','" + 0 + "');";
                stmt.executeUpdate(sql);
                stmt.close();
            }
            else if(bruger.getClass().getSimpleName().equals("Superbruger")){
                stmt = c.createStatement();
                String sql = "INSERT INTO \"Bibliotek\".bruger values ('" + bruger.getLoginfo().getBrugernavn() + "','" + bruger.getLoginfo().getPassword() + "','" + bruger.getBrugerID() + "','" + 1 + "');";
                stmt.executeUpdate(sql);
                stmt.close();
            }

            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database update ok");
    }

    public void updateBooks(Bruger bruger){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", DBpass);
            System.out.println("Database open ok");
            stmt = c.createStatement();

            StringBuilder stringBuilder= new StringBuilder();
            for (int i=0; i<bruger.getBogliste().getListe().size(); i++){

                stringBuilder.append(bruger.getBogliste().getBog(i).getBogID());
                stringBuilder.append(':');
            }
            if(stringBuilder.lastIndexOf(stringBuilder.toString())==':'){
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(stringBuilder.toString()));
            }

            System.out.println(stringBuilder.toString());

            String sql = "UPDATE \"Bibliotek\".bruger SET books = '"+ stringBuilder.toString() +"' where brugerid = '"+bruger.getBrugerID()+"'";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Database update ok");
    }
}
