package com.github.evanquan.deckhand.game;

import com.github.evanquan.deckhand.cards.Board;
import com.github.evanquan.deckhand.cards.Deck;
import com.github.evanquan.deckhand.io.DeckReader;

import java.io.File;

/**
 * Contains all the components and logic for a complete game.
 *
 * @author Evan Quan
 */
public class Game {

    private Board board;

    /**
     * @param imageDirectory containing the card images
     * @param csvPath        containing the card information
     * @throws Exception if there is an I/O problem creating a deck from the
     *                   deck files.
     */
    public Game(String imageDirectory, String csvPath) throws Exception {
        Deck deck = DeckReader.getInstance().getDeck(imageDirectory, csvPath);
        board = new Board(deck);
    }

    /**
     * @param imageDirectory containing the card images
     * @param csvPath        containing the card information
     * @throws Exception if there is an I/O problem creating a deck from the
     *                   deck files.
     */
    public Game(File imageDirectory, File csvPath) throws Exception {
        Deck deck = DeckReader.getInstance().getDeck(imageDirectory, csvPath);
        board = new Board(deck);
    }

    /**
     * Create a new deck from the deck files, and shuffle the cards into the
     * main deck.
     */
    public void reset() {
        board.reset();
    }


}
