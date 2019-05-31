package Common;

import java.util.ArrayList;

public class Bogliste {

    private ArrayList<Bog> Liste;

    public Bogliste(ArrayList liste) {
        this.Liste = liste;
    }

    public ArrayList getListe() {
        return Liste;
    }

    public Bog getBog(int bogNr){
        return Liste.get(bogNr);
    }

    public void getAllBog(){
        for(int i=0; i<Liste.size(); i++){
            System.out.println(Liste.get(i).toString());
        }
    }

    public void addBog(Bog bog){
        Liste.add(bog);
    }

    public void removeBog(int bogNr){
        Liste.remove(bogNr);
    }
}
