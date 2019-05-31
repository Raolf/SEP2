package Common;

public class Bruger {
    private LoginInf Loginfo;
    private Bogliste bogliste;
    private SuperBogListe superBogListe;
    private int brugerID;

    public Bruger(LoginInf loginfo, Bogliste bogliste, int brugerID, SuperBogListe superBogListe) {
        this.Loginfo = loginfo;
        this.bogliste = bogliste;
        this.brugerID = brugerID;
        this.superBogListe= superBogListe;
    }

    public Bogliste getBogliste() {
        return bogliste;
    }

    public LoginInf getLoginfo() {
        return Loginfo;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public SuperBogListe getSuperBogListe() {
        return superBogListe;
    }

}
