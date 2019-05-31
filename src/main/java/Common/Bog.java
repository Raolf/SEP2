package Common;

public class Bog {
    private String tekst;
    private String titel;
    private String forfatter;
    private int bogID;
    private String emne;
    private String sprog;

    public Bog(String tekst, String titel, String forfatter, int bogID, String emne, String sprog) {
        this.tekst = tekst;
        this.titel = titel;
        this.forfatter = forfatter;
        this.bogID = bogID;
        this.emne = emne;
        this.sprog = sprog;
    }


    public String getTekst() {
        return tekst;
    }

    public String getTitel() {
        return titel;
    }

    public String getForfatter() {
        return forfatter;
    }

    public int getBogID() {
        return bogID;
    }

    public String getEmne() {
        return emne;
    }

    public String getSprog() {
        return sprog;
    }

    @Override
    public String toString() {
        return "Bog{" +
                "tekst='" + tekst + '\'' +
                ", titel='" + titel + '\'' +
                ", forfatter='" + forfatter + '\'' +
                ", bogID=" + bogID +
                ", emne='" + emne + '\'' +
                ", sprog='" + sprog + '\'' +
                '}';
    }
}
