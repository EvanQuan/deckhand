package com.github.evanquan.deckhand.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class Controller {

    private static final String POSITIVE_INT_PATTERN = "\\d+";
    private static final int DEFAULT_CARD_DRAW = 1;

    public TextField drawMainDeckField;
    public TextField drawDiscardsField;
    public ProgressBar mainDeckProgressBar;
    public Button mainDeckDrawButton;
    public Button mainDeckShuffleButton;
    public Button discardsShuffleButton;
    public Button discardsDrawButton;
    public ProgressBar discardsProgressBar;

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

    public void undo() {

    }

    public void drawFromMainDeck() {
        int count =
                parseIntWithDefault(drawMainDeckField.getCharacters().toString());
        System.out.println("Draw " + count + " cards from main deck");
    }

    public void drawFromDiscards() {
        int count = parseIntWithDefault(drawMainDeckField.getCharacters().toString());
        System.out.println("Draw " + count + " cards from discards");
    }

    private int parseIntWithDefault(String s) {
        return s.matches(POSITIVE_INT_PATTERN) ? Integer.parseInt(s) : Controller.DEFAULT_CARD_DRAW;
    }

}
