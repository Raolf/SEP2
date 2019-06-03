package main.UI.menu;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.UI.clientUI;

import java.io.IOException;
import java.net.URL;

public class Menu {

    @FXML
    private MenuItem booksMenuBtn;

    @FXML
    private MenuItem signOutBtn;


    @FXML
    void getBooksTab (javafx.event.ActionEvent event){
        try{

            URL homePageUrl = getClass().getResource("../booksTab/Books.fxml");
            AnchorPane homePage = FXMLLoader.load(homePageUrl);

            URL menuBarUrl = getClass().getResource("../menu/Menu.fxml");
            Pane menuBar = FXMLLoader.load(menuBarUrl);

            BorderPane border = clientUI.getRoot();
            border.setCenter(homePage);
            border.setTop(menuBar);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void signOut (javafx.event.ActionEvent event){
        try{

            URL logInUrl = getClass().getResource("../logInTab/LogIn.fxml");
            AnchorPane logInPage = FXMLLoader.load(logInUrl);

            BorderPane border = clientUI.getRoot();
            border.setCenter(logInPage);
            border.setTop(null);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void userSettings (javafx.event.ActionEvent event){
        try{

            URL settingsUrl = getClass().getResource("../userTab/Settings.fxml");
            AnchorPane settingsPage = FXMLLoader.load(settingsUrl);

            BorderPane border = clientUI.getRoot();
            border.setCenter(settingsPage);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void userBooks (javafx.event.ActionEvent event){
        try{

            URL userBooksUrl = getClass().getResource("../userTab/UserBooks.fxml");
            AnchorPane userBooksPage = FXMLLoader.load(userBooksUrl);

            BorderPane border = clientUI.getRoot();
            border.setCenter(userBooksPage);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
