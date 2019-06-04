package main.Common;

public class SingleBrugerListe {
    private static BrugerListe brugerListe = null;
    private static SingleBrugerListe singleBrugerListe;
    private SingleBrugerListe() {

    }

    public static SingleBrugerListe getInstance(){
        if (singleBrugerListe==null){
            singleBrugerListe = new SingleBrugerListe();
        }
        return singleBrugerListe;
    }
    public BrugerListe getBrugerListe(){
        if (brugerListe == null){
            brugerListe = new BrugerListe();
        }
        return brugerListe;

    }
}
