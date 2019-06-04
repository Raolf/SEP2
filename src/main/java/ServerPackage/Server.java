package ServerPackage;

import Common.*;

import javax.management.ObjectName;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
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

    public Server (int port){
        this.port = port;
        BogFactory bogF = new BogFactory(superBogListe);
        bogF.hentBog();
        BrugerFactory brugerF = new BrugerFactory(superBogListe);
        brugerF.hentBruger();

    }
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            Thread thread = new Thread(new Server(port));
            thread.start();

            while(true){
                LoginValidation logVal = new LoginValidation();
                System.out.println(singleBrugerListe);


<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
=======
=======
>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java

>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
                String input = (String) inputStream.readObject();
<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
                inputStream.
=======

                String input = (String) inputStream.readObject();

>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java
=======

>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java
                clientAddress = socket.getInetAddress();

                System.out.println(input);
                for (String s:input.split("\\.")) {
                    System.out.println(s);
                }

                ArrayList<String> order = new ArrayList<String>(Arrays.asList(input.split("\\.")));

                System.out.println("Order Size:" + order.size());
                if (!clientList.contains(clientAddress)){
                    System.out.println((order.size()> 0) && order.get(0) == "login");
                    if((order.size()> 0) && order.get(0).equals("login")){
                        pending = ("Forbindelse oprettet");
                        clientList.add(clientAddress);
                        System.out.println("Forbindelse oprettet");
                        if (order.size() > 1){
                            valBruger = logVal.validerLogin(new LoginInf(order.get(1),order.get(2)));
                            if(valBruger != 0){
                                newUserHost(brugerListe.getBruger(valBruger));
                                System.out.println("New User Host Created");
                                pending = ("1");
                            } else{
                                pending = ("0");
                                System.out.println("Login Failed");
                            }
                        }
                    }
                }else if(clientList.contains(clientAddress)){
<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
                    if((order.size()> 0) && order.get(0).equals("hail")){
=======
=======
>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java
                    if((order.size()> 0)){
>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java
                        System.out.println("Besked modtaget: "+input);
                        userT.message(input);
                    }
                }else{

                }
                System.out.println("Inet: " + clientList.size());

                if (pending != null){
                    outputStream.writeObject(pending);
                    System.out.println("Sending: "+ pending);
                    pending = null;
                }else{
                    System.out.println("null pending");
                }
<<<<<<< HEAD:src/main/java/ServerPackage/Server.java
<<<<<<< HEAD:src/main/java/ServerPackage/Server.java

=======
>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java
=======
>>>>>>> parent of b02d782... Client-branch Merge:src/main/java/ServerPackage/Server.java
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
    }

    public SuperBogListe getSuperBogliste(){
        return superBogListe;
    }
}
