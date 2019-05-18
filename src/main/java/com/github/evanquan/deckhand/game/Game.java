package com.github.evanquan.deckhand.game;

import com.github.evanquan.deckhand.cards.Board;
import com.github.evanquan.deckhand.cards.Deck;
import com.github.evanquan.deckhand.io.DeckReader;

/**
 * Contains all the components and logic for a complete game.
 *
 * @author Evan Quan
 */
public class Game {

    private DeckReader reader;
    private Deck deck;
    private Board board;

    /**
     * @param imageDirectory containing the card images
     * @param csvPath        containing the card information
     * @throws Exception if there is an I/O problem creating a deck from the
     *                   deck files.
     */
    private Game(String imageDirectory, String csvPath) throws Exception {
        reader = DeckReader.getInstance();
        deck = reader.getDeck(imageDirectory, csvPath);
        board = new Board(deck);
    }

    /**
     * Create a new deck from the deck files, and shuffle the cards into the
     * main deck.
     *
     * @throws Exception if there is an I/O problem creating a deck from the
     *                   deck files.
     */
    public void startNewGame() throws Exception {
        board.reset();
    }


}
