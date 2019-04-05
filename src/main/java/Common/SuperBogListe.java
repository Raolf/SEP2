package Common;

import java.util.ArrayList;

public class SuperBogListe extends Bogliste{

    private ArrayList superListe;

    public SuperBogListe(ArrayList superListe) {
        super(superListe);
        this.superListe = superListe;
    }

    public void redigerBog(int bogNr, Bog bog){
        superListe.set(bogNr, bog);
    }
}