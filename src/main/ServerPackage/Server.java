package main.ServerPackage;

import main.Common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server implements Runnable{
    int port;
    ServerSocket serverSocket;
    static ArrayList<InetAddress> clientList = new ArrayList<InetAddress>();
    Object pending;
    InetAddress clientAddress;
    SingleBrugerListe singleBrugerListe = SingleBrugerListe.getInstance();
    BrugerListe brugerListe = singleBrugerListe.getBrugerListe();
    SuperBogListe superBogListe = new SuperBogListe(new ArrayList());
    int valBruger;
    UserHost userT;
    Bruger inBruger;
    String input = null;
    BogFactory bogf = new BogFactory(superBogListe);
    BrugerFactory brugerf = new BrugerFactory(superBogListe);

    public Server (int port){
        this.port = port;

        bogf.hentBog();
        brugerf.hentBruger();

    }
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(new Server(port));
            thread.start();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            LoginValidation logVal = new LoginValidation();
            clientAddress = socket.getInetAddress();

            while(true) {

                if(socket.getInputStream().available() != 0){
                    input = (String) inputStream.readObject();
                }

                if(input != null){
                    System.out.println("Input is: " + input);

                    ArrayList<String> order = new ArrayList<String>(Arrays.asList(input.split("\\.")));
                    System.out.println(order.get(0));
                    System.out.println("Order Size:" + order.size());
                    if (!clientList.contains(clientAddress)) {
                        if ((order.size() > 0) && order.get(0).equals("New")){
                            inBruger = (Bruger) inputStream.readObject();
                            brugerf.lavBruger(inBruger);
                            brugerListe.addBruger(inBruger);
                        }
                        else if ((order.size() > 0) && order.get(0).equals("login")) {
                            pending = ("Forbindelse oprettet");
                            clientList.add(clientAddress);
                            System.out.println("Forbindelse oprettet");
                            if (order.size() > 1) {
                                valBruger = logVal.validerLogin(new LoginInf(order.get(1), order.get(2)));
                                if (valBruger != 0) {
                                    newUserHost(brugerListe.getBruger(valBruger));
                                    System.out.println("New User Host Created" + " " + valBruger);
                                } else {
                                    System.out.println("Login Failed");
                                }
                            }
                        }
                    } else if (clientList.contains(clientAddress)) {
                        if ((order.size() > 0)) {
                            System.out.println("Besked modtaget: " + input);
                            userT.message(input);
                            input = null;
                        }
                    }
                }
                if (pending != null) {
                    outputStream.writeObject(pending);
                    System.out.println("Sending: " + pending);
                    pending = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void newUserHost(Bruger bruger){
        userT = new UserHost(bruger, this);
        Thread thread = new Thread(userT);
        thread.start();
    }
    public void newTaskMaster(){

    }
    public synchronized void send(Object obj) {
        pending = obj;
        System.out.println("server pending: "+ pending + " from: " + obj);
    }

    public SuperBogListe getSuperBogliste(){
        return superBogListe;
    }
}
