package main.UI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class clientUI extends Application {

    private static BorderPane root = new BorderPane();

    public static BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        URL logInScreenUrl = getClass().getResource("/main/UI/logInTab/LogIn.fxml");
        AnchorPane logInScreen = FXMLLoader.load(logInScreenUrl);

        root.setCenter(logInScreen);

        Scene scene = new Scene(root, 1500, 900);
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

