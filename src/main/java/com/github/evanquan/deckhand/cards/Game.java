package com.github.evanquan.deckhand.cards;

import com.github.evanquan.deckhand.cards.Board;
import com.github.evanquan.deckhand.cards.Deck;
import com.github.evanquan.deckhand.io.DeckReader;

import java.io.File;

/**
 * Contains all the components and logic for a complete game. The GUI should
 * communicate with this class only for makes changes to the game state.
 *
 * @author Evan Quan
 */
public class Game {

    private int totalCardCount;
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
        totalCardCount = deck.size();
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

    public double getMainDeckPercent() {
        return getDeckPercent(board.getMainDeck());
    }

    public double getDiscardPilePercent() {
        return getDeckPercent(board.getDiscardPile());
    }

    private double getDeckPercent(Deck deck) {
        return (double) deck.size() / (double) totalCardCount;
    }

}
