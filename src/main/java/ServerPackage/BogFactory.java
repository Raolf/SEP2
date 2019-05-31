package ServerPackage;

import Common.Bog;
import Common.SuperBogListe;
import Common.TextFil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BogFactory {

    private Bog bog;
    private SuperBogListe Liste;

    public BogFactory(SuperBogListe Liste){
        this.Liste = Liste;
    }

    public void hentBog(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "antonbanton1");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Bibliotek\".bog;");
            while (rs.next()) {
                String tekst = rs.getString("tekst");
                String titel = rs.getString("titel");
                String forfatter = rs.getString("forfatter");
                int bogID = rs.getInt("bogID");
                String emne = rs.getString("emne");
                String sprog = rs.getString("sprog");

                bog = new Bog(tekst,titel,forfatter,bogID,emne,sprog);

                Liste.addBog(bog);

                System.out.println("Tekst = " + tekst);
                System.out.println("Titel = " + titel);
                System.out.println("Forfatter = " + forfatter);
                System.out.println("BogID = " + bogID);
                System.out.println("Emne = " + emne);
                System.out.println("Sprog = " + sprog);
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

    public void lavBog(Bog bog){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "antonbanton1");

            System.out.println("Database open ok");

            System.out.println(bog.getTekst());

            stmt = c.createStatement();
            String sql = "INSERT INTO \"Bibliotek\".bog values ('" + bog.getTekst() + "','"+ bog.getTitel() + "','" + bog.getForfatter() + "','" + bog.getBogID() + "','" + bog.getEmne() + "','" + bog.getSprog() + "');";
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
