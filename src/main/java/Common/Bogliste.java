package Common;

import java.util.ArrayList;

public class Bogliste {

    private ArrayList Liste;

    public Bogliste(ArrayList liste) {
        Liste = liste;
    }

    public ArrayList getListe() {
        return Liste;
    }

    public Object getBog(int bogNr){
        return Liste.get(bogNr);
    }

    public void addBog(Bog bog){
        Liste.add(bog);
    }

    public void removeBog(int bogNr){
        Liste.remove(bogNr);
    }
}
