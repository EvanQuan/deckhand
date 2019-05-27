package com.github.evanquan.deckhand.gui;

import com.github.evanquan.deckhand.cards.Deck;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Program entry point. Starts up the application GUI.
 *
 * @author Evan Quan
 */
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
    private static final int MIN_HEIGHT = 225;
    /**
     * Title of the window.
     */
    private static final String TITLE = "Deckhand: Virtual Deck Manager";
    /**
     * Application icon of the window. Javafx does not accept .ico files.
     */
    private static final String ICON = "/Cheese.png";

    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    //    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON)));
        primaryStage.setScene(SceneManager.getInstance().getScene(SceneManager.SceneType.GAME));
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.show();
    }
}
