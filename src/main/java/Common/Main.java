package Common;

import ServerPackage.BogFactory;
import ServerPackage.BrugerFactory;
import ServerPackage.LoginValidation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SuperBogListe Liste=new SuperBogListe(new ArrayList());
        //Liste.addBog(new Bog("Hej med dig","Femto","Gren",0, "Ting", "Dansk"));
        //System.out.println(Liste.getBog(0).toString());

        BogFactory bogFactory= new BogFactory(Liste);
        bogFactory.hentBog();

        LoginValidation loginValidation= new LoginValidation();
        LoginInf loginInf=new LoginInf("bob","123");
        loginValidation.validerLogin(loginInf);

        //bogFactory.lavBog(Liste.getBog(0));

        int brugerID=0;
       // LoginInf loginInf=new LoginInf("bobby","bobsen");
       // Bruger bruger=new Bruger(loginInf, new Bogliste(new ArrayList()), brugerID, Liste);

        BrugerFactory brugerFactory = new BrugerFactory(Liste);
        //brugerFactory.lavBruger(bruger);

        brugerFactory.hentBruger();

        LoginInf loginInf1=new LoginInf("person3","123");
        //brugerFactory.hentBrugerBooks(loginInf);



    }
}
