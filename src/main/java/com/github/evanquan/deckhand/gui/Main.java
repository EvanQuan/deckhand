package com.github.evanquan.deckhand.gui;

import com.github.evanquan.deckhand.cards.Deck;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
//public class Main {

    /**
     * All {@link Deck} information must be contained within a directory of a
     * given name, nested within the same directory as the application.
     * <p>
     * The name of this directory must have a specified name for deckhand to
     * know which directory to look in.
     */
    private static final String DECK_DIRECTORY_NAME = "deckhand";
    private static final String CSV_NAME = "deckhand.csv";
    private static final String IMAGE_DIRECTORY_NAME = "images";

    private static final String DECK_PATH = "./" + DECK_DIRECTORY_NAME;
    private static final String IMAGE_PATH = DECK_PATH + "/" + IMAGE_DIRECTORY_NAME;
    private static final String CSV_PATH = DECK_PATH + "/" + CSV_NAME;

    private static final String TITLE = "Hello World";
    private static final String RESOURCE = "sample.fxml";

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String message;

//        do {
//            System.out.print("Enter message: ");
//            message = in.nextLine();
//            System.out.println("\"" + message + "\"");
//        } while (!message.isEmpty());
        launch(args);
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
