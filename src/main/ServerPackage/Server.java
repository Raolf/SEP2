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
    SuperBogListe superBogListe;
    int valBruger;


    public Server (int port){
        this.port = port;
        BogFactory bf = new BogFactory(superBogListe);
        bf.hentBog();

    }
    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            while(true){
                LoginValidation logVal = new LoginValidation();
                System.out.println(singleBrugerListe);
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new Server(port));
                thread.start();
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                String input = (String) inputStream.readObject();
                clientAddress = socket.getInetAddress();

                System.out.println(input);
                System.out.println(input.split("\\.").length);
                for (String s:input.split("\\.")) {
                    System.out.println(s);
                }

                ArrayList<String> order = new ArrayList<String>(Arrays.asList(input.split("\\.")));

                System.out.println("Order Size:" + order.size());
                if (clientList.size() > 0 && !clientList.contains(clientAddress)){
                    if((order.size()> 0) && order.get(0) == "login"){
                        //for (singleBrugerListe)
                        pending = ("Forbindelse oprettet");
                        clientList.add(clientAddress);
                        System.out.println("Forbindelse oprettet");
                        if (order.size() > 1){
                            valBruger = logVal.validerLogin(new LoginInf(order.get(1),order.get(2)));
                            if(valBruger != 0){
                                newUserHost(brugerListe.getBruger(valBruger));
                                System.out.println("New User Host Created");
                            } else{
                                System.out.println("Login Failed");
                            }
                        }
                    }
                }else if(clientList.size() > 0 && clientList.contains(clientAddress)){
                    if((order.size()> 0) && order.get(0).equals("hail")){
                        System.out.println("Besked modtaget: "+input);
                    }
                }

                if (pending != null){
                    outputStream.writeObject(pending);
                    System.out.println("Sending: "+ pending);
                    pending = null;
                }else{
                    System.out.println("null pending");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void newUserHost(Bruger bruger){
        UserHost userT = new UserHost(bruger, this);
        Thread thread = new Thread(userT);
        thread.start();
    }
    public void newTaskMaster(){

    }
    public synchronized void send(Object obj) {
        pending = obj;
    }

    public SuperBogListe getSuperBogliste(){
        return superBogListe;
    }
}
