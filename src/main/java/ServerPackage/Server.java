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
    static ArrayList<InetAddress> clientList;
    Object pending;

    public Server (int port){
        this.port = port;

    }
    public void run() {
        try {
            serverSocket = new ServerSocket(port);


            while(true){

                Socket socket = serverSocket.accept();
                new Server(port);
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                String input = (String) inputStream.readObject();
                InetAddress clientAddress = socket.getInetAddress();
                ArrayList<String> order = new ArrayList<String>(Arrays.asList(input.split(".")));

                if (!clientList.contains(clientAddress)){
                    if(order.get(0) == "hail"){
                        clientList.add(clientAddress);
                        outputStream.writeUTF("Forbindelse oprettet");
                    }
                }else{

                }

                if (pending != null){
                    outputStream.writeObject(pending);
                    pending = null;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public void newUserHost(String userId){
        UserHost userT = new UserHost(userId);
        Thread thread = new Thread(userT);
        thread.start();
    }
    public void newTaskMaster(){

    }
    public synchronized void send(Object obj) {
        pending = obj;
    }
}
