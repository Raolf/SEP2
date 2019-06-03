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
        Scanner scanner = new Scanner(System.in);
        clientUI ui = new clientUI();
            try{

                Socket socket = new Socket("localhost",6789);
                ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
                Application.launch(ui.getClass());
                ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());


                String input = scanner.next();
                outToServer.writeObject(input);
                String o = (String)inFromServer.readObject();

                input = scanner.next();
                outToServer.writeObject(input);

                System.out.println(o);

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

