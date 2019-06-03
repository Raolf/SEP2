package Common;

import java.util.ArrayList;

public class BrugerListe {
    private ArrayList<Bruger> Liste= new ArrayList();

    public BrugerListe() {

    }

    public Bruger getBruger(int brugerNr){
        return Liste.get(brugerNr-1);
    }

    public void addBruger(Bruger bruger){
        Liste.add(bruger);
    }
}
