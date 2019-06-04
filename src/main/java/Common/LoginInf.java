package Common;

public class LoginInf {

    private String brugernavn;
    private String password;

    public LoginInf(String brugernavn, String password) {
        this.brugernavn = brugernavn;
        this.password = password;
    }

    public String getBrugernavn(){
        return brugernavn;
    }

    public String getPassword() {
        return password;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInf{" +
                "brugernavn='" + brugernavn + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
