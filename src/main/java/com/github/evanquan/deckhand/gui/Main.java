package com.github.evanquan.deckhand.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

//public class Main extends Application {
public class Main {

    private static final String DEFAULT_DIRECTORY = ".";
    private static final String TITLE = "Hello World";
    private static final String RESOURCE = "sample.fxml";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String message;

        do {
            System.out.print("Enter message: ");
            message = in.nextLine();
            System.out.println("\"" + message + "\"");
        } while (!message.isEmpty());
//        launch(args);
    }

    //    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));
        String current = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir: " + current);
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
