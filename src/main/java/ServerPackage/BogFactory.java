package ServerPackage;

import Common.Bog;
import Common.TextFil;

public class BogFactory {
    private int bogID=0;

    public Bog lavBog(TextFil tekst, String titel, String forfatter){

        return new Bog(tekst,titel,forfatter,bogCounter()-1);
    }

    public int bogCounter(){
        bogID++;
        return bogID;
    }
}
