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
    Deck deck;
    Board board;

    private Game() {
        reader = DeckReader.getInstance();
    }

    public void reset() {
//        reader.getDeck()
    }


}
