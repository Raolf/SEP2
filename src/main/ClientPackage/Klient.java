package main.ClientPackage;

import main.UI.clientUI;
import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Klient {



    public static void main(String[] args){
        //while (true){
        System.out.println("før scanner");
        Scanner scanner = new Scanner(System.in);
        clientUI ui = new clientUI();
        System.out.println("efter scanner");
            try{
                System.out.println("start af try");
                Socket socket = new Socket("localhost",6789);
                ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

                outToServer.writeObject("login.bob.123");

                System.out.println("Order sent");
                String o = (String)inFromServer.readObject();
                System.out.println(o);

                outToServer.writeObject("Bruger.getBogListe");

                o = (String) inFromServer.readObject();
                System.out.println(o);


                System.out.println("UI launched");
                Application.launch(ui.getClass());
                System.out.println("UI exited");






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

