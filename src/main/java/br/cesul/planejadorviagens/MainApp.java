package br.cesul.planejadorviagens;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        var loader = new FXMLLoader(getClass().getResource("/br.cesul.planejadorviagens/trip_view.fxml"));

        Parent root = loader.load();

        stage.setTitle("Trip Planner");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
