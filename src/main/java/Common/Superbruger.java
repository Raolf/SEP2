package Common;

public class Superbruger extends Bruger{
    private BrugerListe brugerListe;

    public Superbruger(LoginInf loginfo, Bogliste bogliste, int brugerID, BrugerListe brugerListe, SuperBogListe superBogListe) {
        super(loginfo, bogliste, brugerID, superBogListe);
        this.brugerListe = brugerListe;
    }

    public BrugerListe getBrugerListe() {
        return brugerListe;
    }

}
