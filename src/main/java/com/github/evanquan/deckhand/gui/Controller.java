package com.github.evanquan.deckhand.gui;

import com.github.evanquan.deckhand.game.Game;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
        imageChooser = new DirectoryChooser();
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

    public void printHi() {
        System.out.println("Hi");
    }

    public void undoLastAction() {
        System.out.println("Undo last action");

    }

    public void startNewGame() {

        if (imageDirectory == null) {
            warn(IMAGE_DIRECTORY_NOT_SET_MESSAGE);
            return;
        }
        if (cardInfo == null) {
            warn(CSV_NOT_SET_MESSAGE);
            return;
        }
        try {
            game = new Game(imageDirectory, cardInfo);
        } catch (Exception e) {
            warn(e.getMessage());
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

    private int parseIntWithDefault(String s) {
        return s.matches(POSITIVE_INT_PATTERN) ?
                Integer.parseInt(s) : Controller.DEFAULT_CARD_DRAW;
    }

    private void validateTextField(TextField field) {
        if (!field.getText().matches(POSITIVE_INT_PATTERN)) {
            field.setText(stripNonDigits(field.getText()));
        }
    }

    public void validateMainDeckDrawField() {
        validateTextField(drawMainDeckField);
    }

    public void validateDiscardsDrawField() {
        validateTextField(drawDiscardsField);
    }

    private String stripNonDigits(String s) {
        return s.replaceAll(NON_DIGIT_PATTERN, "");
    }

    /**
     * Open a warning dialog box with a specified warning message. The user
     * cannot continue until they close the dialog box.
     *
     * @param warningMessage of the dialog box
     */
    private void warn(String warningMessage) {
        System.out.println(warningMessage);
        Alert alert = new Alert(Alert.AlertType.ERROR, warningMessage,
                ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }


    public void closeWarningWindow() {
        closeWindow(warningConfirmationButton);
    }

    private void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();

        stage.close();
    }

    /**
     * Open the warning window is a specified message.
     *
     * @param warningMessage to set when the warning window opens
     */
    public void openWarningWindow(String warningMessage) {
        Stage warningWindow = new Stage();
        warningWindow.show();

    }

    private void setWarningLabel(String s) {
        warningLabel.setText(s);
    }

    /**
     * Choose a csv file for card information.
     */
    public void chooseCSVFile() {
        System.out.println("Choose CSV file");
        cardInfo = csvChooser.showOpenDialog(new Stage());
    }

    public void chooseCardImages() {
        System.out.println("Choose card images");
        imageDirectory = imageChooser.showDialog(new Stage());
    }
}
