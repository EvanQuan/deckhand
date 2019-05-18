package com.github.evanquan.deckhand.cards;

/**
 * @author Evan Quan
 */
public class Board {

    /**
     * @param mainDeck to add
     */
    public Board(Deck mainDeck) {
        this.mainDeck = mainDeck;
        this.discardPile = new Deck();
        this.cardsInPlay = new Deck();
    }
    /**
     * Main deck to drawFromTop from.
     */
    private Deck mainDeck;
    /**
     * Discard pile.
     */
    private Deck discardPile;
    /**
     * Cards drawn and in play.
     */
    private Deck cardsInPlay;

    public Deck getCardsInPlay() {
        return cardsInPlay;
    }

    public void discard(int index) {
        discardPile.addToTop(cardsInPlay.get(index));
    }

    /**
     * Draw a card from the main deck into play.
     */
    public void draw() {
        cardsInPlay.add(mainDeck.drawFromTop());
    }

    public void shuffleMainDeck() {
        mainDeck.shuffle();
    }

    public void shuffleDiscardPileIntoMainDeck() {
        mainDeck.shuffleWith(discardPile);
    }

    public void addShuffledDiscardPileToTopOfMainDeck() {
        discardPile.shuffle();
        mainDeck.addToTop(discardPile);
    }

    /**
     * Undo the last action. This reverts the board state.
     */
    public void undoLastMove() {

    }
}
