package com.github.evanquan.deckhand.gui;

import com.github.evanquan.deckhand.game.Game;
import javafx.scene.control.*;
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

    public Controller() {
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

    private void warn(String warningMessage) {
        System.out.println(warningMessage);
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

    }

    private void setWarningLabel(String s) {
        warningLabel.setText(s);
    }

    /**
     * Choose a csv file for card information.
     */
    public void chooseCSVFile() {
        System.out.println("Choose CSV file");
        FileChooser fileChooser = new FileChooser();
        cardInfo = fileChooser.showOpenDialog(new Stage());
    }

    public void chooseCardImages() {
        System.out.println("Choose card images");
        FileChooser fileChooser = new FileChooser();
        imageDirectory = fileChooser.showOpenDialog(new Stage());
    }
}
