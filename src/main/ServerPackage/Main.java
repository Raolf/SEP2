package main.ServerPackage;

import main.Common.SingleBrugerListe;

public class Main {
    public static void main(String[] args) {
        SingleBrugerListe singleBrugerListe = SingleBrugerListe.getInstance();
        singleBrugerListe.getBrugerListe();
        Server server = new Server(6789);
        Thread thread = new Thread(server);
        thread.start();
    }
}
