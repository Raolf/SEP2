package Common;

public class SingleBrugerListe {
    private static BrugerListe brugerListe = null;
    private static SingleBrugerListe singleBrugerListe;
    private SingleBrugerListe() {
        brugerListe = new BrugerListe();
    }

    public static SingleBrugerListe getInstance(){
        if (singleBrugerListe==null){
            singleBrugerListe = new SingleBrugerListe();
        }
        return singleBrugerListe;
    }
    public BrugerListe getBrugerListe(){
        return brugerListe;
    }
}
