package com.harrypotterapp.tesicnorpruebatecnica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HarryPotterMDBApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HarryPotterMDBApp.class.getResource("movieListView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 694, 382);
        scene.getStylesheets().add(getClass().getResource("additionalStyles.css").toExternalForm());
        stage.setResizable(false);
        stage.setTitle("Harry Potter Movie List");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}