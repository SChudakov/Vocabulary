package com.sschudakov.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class JavaFXApplicationMain extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "fxml/samples.fxml";
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(fxmlFile)));
        stage.setTitle("Databases lab");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
