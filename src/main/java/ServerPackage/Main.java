package ServerPackage;

import ClientPackage.Klient;
import Common.SingleBrugerListe;

public class Main {
    public static void main(String[] args) {
        SingleBrugerListe singleBrugerListe = SingleBrugerListe.getInstance();
        System.out.println(singleBrugerListe);
        Server server = new Server(6789);
        Thread thread = new Thread(server);
        thread.start();
    }
}
