package Common;

import java.util.ArrayList;

public class SuperBogListe{

    private ArrayList<Bog> Liste;

    public SuperBogListe(ArrayList Liste) {
        this.Liste=Liste;
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

    public void redigerBog(int bogNr, Bog bog){
        Liste.set(bogNr, bog);
    }
}