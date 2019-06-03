package ServerPackage;

import Common.LoginInf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class LoginValidation {

    public boolean validerLogin(LoginInf loginInf){

        boolean isvalid= false;

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "antonbanton1");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM \"Bibliotek\".bruger;");

            while ( rs.next() ) {
                if (loginInf.getBrugernavn().equals(rs.getString("brugernavn")) && loginInf.getPassword().equals(rs.getString("password"))) {
                    isvalid = true;
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
        System.out.println(isvalid);
        return isvalid;
    }
}