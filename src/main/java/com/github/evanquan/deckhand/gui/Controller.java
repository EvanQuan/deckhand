package com.github.evanquan.deckhand.gui;

import com.github.evanquan.deckhand.cards.Card;
import com.github.evanquan.deckhand.cards.Game;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Contains all GUI logic for handling user input.
 *
 * @author Evan Quan
 */
public class Controller {

    private static final String POSITIVE_INT_PATTERN = "\\d+";
    private static final int DEFAULT_CARD_DRAW = 1;
    private static final String NON_DIGIT_PATTERN = "[^\\d]";
    private static final String IMAGE_DIRECTORY_NOT_SET_MESSAGE = "Image " +
            "directory has not been set.";
    private static final String CSV_NOT_SET_MESSAGE = "Card info CSV has not " +
            "been set.";
    private static final FileChooser.ExtensionFilter CSV_FILTER =
            new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");

    /**
     * Current working directory. The program will automatically start off in
     * the current working directory when choosing card info and images for
     * convenience.
     */
    private static final File CURRENT_WORKING_DIRECTORY = new File(System.getProperty("user.dir"));

    public TextField drawMainDeckField;
    public TextField drawDiscardsField;
    public ProgressBar mainDeckProgressBar;
    public Button mainDeckDrawButton;
    public Button mainDeckShuffleButton;
    public Button discardsShuffleButton;
    public Button discardsDrawButton;
    public ProgressBar discardsProgressBar;
    public Button warningConfirmationButton;
    public Label warningLabel;
    public MenuBar menuBar;

    private Game game;
    private File imageDirectory = null;
    /**
     * .csv file containing card information.
     */
    private File cardInfo = null;
    /**
     * Chooses the cardInfo CSV file
     */
    private FileChooser csvChooser;
    /**
     * Chooses the image directory
     */
    private DirectoryChooser imageChooser;

    public Controller() {
        csvChooser = new FileChooser();
        csvChooser.getExtensionFilters().add(CSV_FILTER);
        csvChooser.setInitialDirectory(CURRENT_WORKING_DIRECTORY);
        imageChooser = new DirectoryChooser();
        imageChooser.setInitialDirectory(CURRENT_WORKING_DIRECTORY);
    }

    public void about() {
        System.out.println("About");
    }

    public void howToUse() {
        System.out.println("How to Use");
    }

    public void shuffleMainDeck() {
        System.out.println("Shuffle main deck");
    }

    public void shuffleDiscards() {
        System.out.println("Shuffle discards");
    }

    public void undoLastAction() {
        System.out.println("Undo last action");

    }

    /**
     * Reset the game state of the given card info and images. If the card info
     * and images are not set, then the game will not start and will prompt the
     * user with an error message.
     */
    public void startNewGame() {

        if (imageDirectory == null) {
            error(IMAGE_DIRECTORY_NOT_SET_MESSAGE);
            return;
        }
        if (cardInfo == null) {
            error(CSV_NOT_SET_MESSAGE);
            return;
        }
        try {
            System.out.println("Start new game");
            game = new Game(imageDirectory, cardInfo);
        } catch (Exception e) {
            error(e.getMessage());
            e.printStackTrace();
        }
    }


    public void drawFromMainDeck() {
        int count = parseIntWithDefault(drawMainDeckField.getText());
        System.out.println("Draw " + count + " cards from main deck");

    }

    public void drawFromDiscards() {
        int count = parseIntWithDefault(drawMainDeckField.getText());
        System.out.println("Draw " + count + " cards from discards");
    }

    private void drawCard(Card card) {

    }

    private int parseIntWithDefault(String s) {
        return s.matches(POSITIVE_INT_PATTERN) ?
                Integer.parseInt(s) : Controller.DEFAULT_CARD_DRAW;
    }

    private void validateTextField(TextField field) {
        if (!field.getText().matches(POSITIVE_INT_PATTERN)) {
            field.setText(stripNonDigits(field.getText()));
        }
    }

    /**
     * Check the contents of the main deck draw text field to make sure that
     * only valid characters are contained inside.
     */
    public void validateMainDeckDrawField() {
        validateTextField(drawMainDeckField);
    }

    /**
     * Check the contents of the discards draw text field to make sure that only
     * valid characters are contained inside.
     */
    public void validateDiscardsDrawField() {
        validateTextField(drawDiscardsField);
    }

    private String stripNonDigits(String s) {
        return s.replaceAll(NON_DIGIT_PATTERN, "");
    }

    /**
     * Open a warning dialog box with a specified error message. The user
     * cannot continue until they close the dialog box.
     *
     * @param errorMessage of the dialog box
     */
    private void error(String errorMessage) {
        System.out.println(errorMessage);
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage,
                ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }


    public void closeWarningWindow() {
        closeWindow(warningConfirmationButton);
    }

    /**
     * Close the main stage, and end the application.
     *
     * @param button to close the window.
     */
    private void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();

        stage.close();
    }

    /**
     * Choose a csv file for card information.
     */
    public void chooseCSVFile() {
        System.out.println("Choose CSV file");
        cardInfo = csvChooser.showOpenDialog(new Stage());

        if (cardInfo != null) {
            setCurrentDirectory(cardInfo.getParentFile());
        }
    }

    /**
     * Prompt the user to choose the card images directory with a directory
     * chooser interface.
     */
    public void chooseCardImages() {
        System.out.println("Choose card images");
        imageDirectory = imageChooser.showDialog(new Stage());

        if (imageDirectory != null) {
            setCurrentDirectory(imageDirectory.getParentFile());
        }
    }

    /**
     * Update the current directory for file choosers.
     *
     * @param directory to set as current
     */
    private void setCurrentDirectory(File directory) {
        if (directory != null && directory.isDirectory()) {
            csvChooser.setInitialDirectory(directory);
            imageChooser.setInitialDirectory(directory);
        }
    }

    private void setMainDeckProgressBar(double percent) {
        mainDeckProgressBar.setProgress(percent);
    }

    private void setDiscardsProgressBar(double percent) {
        discardsProgressBar.setProgress(percent);
    }
}
