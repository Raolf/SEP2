package ClientPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Klient {


    public static void main(String[] args){
        //while (true){
            try{
                System.out.println("1");
                Socket socket = new Socket("localhost",6789);
                System.out.println("2");
                ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
                outToServer.writeObject("hail.hej");
                System.out.println("3");
                ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
                String o = (String) inFromServer.readObject();

                outToServer.writeObject("Bogliste.getBog(4)");

                System.out.println(o+"END");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        //}
    }
}

