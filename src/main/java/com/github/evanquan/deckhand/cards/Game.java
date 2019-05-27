package com.github.evanquan.deckhand.cards;

import com.github.evanquan.deckhand.io.DeckReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Contains all the components and logic for a complete game. The GUI should
 * communicate with this class only for makes changes to the game state.
 *
 * @author Evan Quan
 */
public class Game {

    /**
     * For tracking deck percentages
     */
    private int totalCardCount;
    /**
     * For tracking turn history
     */
    private int turnCount;
    private Stack<Board> boardHistory;

    /**
     * @param imageDirectory containing the card images
     * @param csvPath        containing the card information
     * @throws Exception if there is an I/O problem creating a deck from the
     *                   deck files.
     */
    public Game(File imageDirectory, File csvPath) throws Exception {
        Deck deck = DeckReader.getInstance().getDeck(imageDirectory, csvPath);
        boardHistory = new Stack<>();
        boardHistory.add(new Board(deck));
        totalCardCount = deck.size();
    }

    /**
     * Create a new deck from the deck files, and shuffle the cards into the
     * main deck.
     */
    public void reset() {
    }

    /**
     *
     * @return the percent of cards in the main deck
     */
    public double getMainDeckPercent() {
        return getDeckPercent(boardHistory.peek().getMainDeck());
    }

    /**
     *
     * @return the percent of cards in the discard pile
     */
    public double getDiscardPilePercent() {
        return getDeckPercent(boardHistory.peek().getDiscardPile());
    }

    private double getDeckPercent(Deck deck) {
        return (double) deck.size() / (double) totalCardCount;
    }

    /**
     * Undo the last action. This reverts the {@link Board} state to one turn
     * before.
     */
    public void undoLastMove() {
        boardHistory.pop();
    }

}
