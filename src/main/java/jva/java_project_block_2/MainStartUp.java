package jva.java_project_block_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStartUp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStartUp.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 720);
        stage.setTitle("IMS");
        stage.setScene(scene);
        stage.show();

        // Optionally, test DB connection when app starts

    }

    public static void main(String[] args) {
        // Optionally, test DB connection when app startsz
        Database.testConnection();
        launch();
    }
}
