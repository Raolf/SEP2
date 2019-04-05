package Common;

import java.util.ArrayList;

public class BrugerListe {
    private ArrayList Liste;

    public BrugerListe(ArrayList liste) {
        Liste = liste;
    }

    public void getBruger(int brugerNr){
        Liste.get(brugerNr);
    }

    public void addBruger(Bruger bruger){
        Liste.add(bruger);
    }
}
