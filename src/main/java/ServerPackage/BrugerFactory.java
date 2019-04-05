package ServerPackage;
import Common.*;

import java.util.ArrayList;

public class BrugerFactory {
    int brugerID=0;

    public void lavBruger(String brugernavn, String password){
        LoginInf loginInf=new LoginInf(brugernavn, password);
        Bogliste bogliste=new Bogliste(new ArrayList());
        SuperBogListe superBogListe=null;
        Bruger bruger=new Bruger(loginInf,bogliste,brugerID,superBogListe);
        BrugerListe brugerListe = null;
        brugerListe.addBruger(bruger);
        brugerID++;
    }

}
