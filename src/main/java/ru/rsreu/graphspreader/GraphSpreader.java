package ru.rsreu.graphspreader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphSpreader extends Application {
    public static Stage stageForFile = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view.fxml"));
        stageForFile = stage;
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("Graph Spreader");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}