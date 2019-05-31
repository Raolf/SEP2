package Common;

public class SingleBrugerListe {
    private static BrugerListe singleBrugerListe=null;

    public SingleBrugerListe() {

    }

    public static BrugerListe getInstance(){
        if (singleBrugerListe==null){
            singleBrugerListe = new BrugerListe();
        }
        return singleBrugerListe;
    }
}
