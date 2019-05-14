package com.github.evanquan.deckhand.io;

import com.github.evanquan.deckhand.cards.Card;
import com.github.evanquan.deckhand.cards.Deck;

import java.io.File;

/**
 * Card info gathered from input CSV file.
 *
 * @author Evan Quan
 */
class CardInfo {

    /**
     * Image {@link File} name without extension.
     */
    private String imageName;
    /**
     * In-game {@link Card} name.
     */
    private String cardName;
    /**
     * In=game {@link Card} description.
     */
    private String cardDescription;
    /**
     * Quantity of {@link Card} to include in the {@link Deck}.
     */
    private int quantity;

    CardInfo(String imageName, String cardName, String cardDescription,
             int quantity) {
        this.imageName = imageName;
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.quantity = quantity;
    }

    /**
     * @return filename without extension
     */
    String getImageName() {
        return imageName;
    }

    /**
     * @return card name
     */
    String getCardName() {
        return cardName;
    }

    /**
     * @return card description
     */
    String getCardDescription() {
        return cardDescription;
    }

    /**
     * @return quantity of card to put in deck
     */
    int getQuantity() {
        return quantity;
    }
}
