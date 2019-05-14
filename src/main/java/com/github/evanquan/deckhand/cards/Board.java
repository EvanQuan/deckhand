package com.github.evanquan.deckhand.cards;

/**
 * @author Evan Quan
 */
public class Board {

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
}
