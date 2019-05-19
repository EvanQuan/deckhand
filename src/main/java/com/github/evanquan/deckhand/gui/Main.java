package com.github.evanquan.deckhand.gui;

import com.github.evanquan.deckhand.cards.Deck;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

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
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 250;
    private static final int START_WIDTH = MIN_WIDTH + 100;
    private static final int START_HEIGHT = MIN_HEIGHT;

    private static final String TITLE = "Deckhand";
    /**
     * The .fxml file is placed with resources directory as the root.
     */
    private static final String RESOURCE = "/sample.fxml";

    public Main() {
    }

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
        URL resource = getClass().getResource(RESOURCE);
        System.out.println("resource " + (resource == null ? "is null" :
                "was found"));
//        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));
        assert resource != null;

        Parent root = FXMLLoader.load(resource);

//        primaryStage.setScene(new Scene(new Pane(), 800, 600));
//        primaryStage.show();

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root, START_WIDTH, START_HEIGHT));
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
    }
}
