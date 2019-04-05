package Common;

public class Bog {
    private TextFil tekst;
    private String titel;
    private String forfatter;
    private int bogID;

    public Bog(TextFil tekst, String titel, String forfatter, int bogID) {
        this.tekst = tekst;
        this.titel = titel;
        this.forfatter = forfatter;
        this.bogID = bogID;
    }

    public TextFil getTekst() {
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

    @Override
    public String toString() {
        return "Common.Bog{" +
                "tekst=" + tekst +
                ", titel='" + titel + '\'' +
                ", forfatter='" + forfatter + '\'' +
                ", bogID=" + bogID +
                '}';
    }
}
