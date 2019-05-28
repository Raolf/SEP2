package Common;

import java.util.ArrayList;

public class BrugerListe {
    private ArrayList Liste= new ArrayList();

    public BrugerListe() {

    }

    public void getBruger(int brugerNr){
        Liste.get(brugerNr);
    }

    public void addBruger(Bruger bruger){
        Liste.add(bruger);
    }
}
