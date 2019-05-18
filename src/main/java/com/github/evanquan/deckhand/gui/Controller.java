package com.github.evanquan.deckhand.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Controller {

    public Controller() {
    }
    public Button btn_msg;

    public void pressButton(ActionEvent event) {
        System.out.println("Button pressed!");
    }

    public void shuffleMainDeck(ActionEvent event) {
        System.out.println();
    }

    public void printHi() {
        System.out.println("Hi");
    }

    public void undo() {

    }

}
