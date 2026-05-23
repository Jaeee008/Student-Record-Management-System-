package com.example.studentdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws Exception {

        mainStage = stage;

        changeScene("login.fxml");

        stage.setTitle("Student Record Management System");
        stage.show();
    }

    public static void changeScene(String fxmlFile) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                MainApp.class.getResource("/com/example/studentdb/" + fxmlFile)
        );

        Scene scene = new Scene(loader.load());

        mainStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}