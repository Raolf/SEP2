package ServerPackage;

import Common.Bruger;
import Common.LoginInf;

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

    public Server (int port){
        this.port = port;
    }
    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            while(true){

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
                    if((order.size()> 0) && order.get(0) == "hail"){
                        clientList.add(clientAddress);
                        pending = ("Forbindelse oprettet");
                        System.out.println("Forbindelse oprettet");
                    }
                }else if(clientList.size() > 0 && clientList.contains(clientAddress)){
                    if((order.size()> 0) && order.get(0).equals("hail")){
                        if ()
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
    public void newUserHost(String userId){
        UserHost userT = new UserHost(userId, this);
        Thread thread = new Thread(userT);
        thread.start();
    }
    public void newTaskMaster(){

    }
    public synchronized void send(Object obj) {
        pending = obj;
    }
}
