package view.logInTab;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import view.clientGUI;

import java.io.IOException;
import java.net.URL;

public class LogIn {

    @FXML Button logInBtn;

    @FXML Pane logInPane;

    @FXML TextField usernameField;

    @FXML TextField passwordField;


    @FXML
    void logIn (javafx.event.ActionEvent event){
        try{
            URL homePageUrl = getClass().getResource("../homeTab/Home.fxml");
            AnchorPane homePage = FXMLLoader.load(homePageUrl);

            URL menuBarUrl = getClass().getResource("../menu/Menu.fxml");
            Pane menuBar = FXMLLoader.load(menuBarUrl);

            BorderPane border = clientGUI.getRoot();
            border.setCenter(homePage);
            border.setTop(menuBar);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
