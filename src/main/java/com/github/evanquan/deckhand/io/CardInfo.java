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
     * {@link File} name without extension.
     */
    private String fileName;
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
    private String quantity;

    CardInfo(String fileName, String cardName, String cardDescription, String quantity) {
        this.fileName = fileName;
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.quantity = quantity;
    }

    /**
     * @return filename without extension
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return card name
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @return card description
     */
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * @return quantity of card to put in deck
     */
    public String getQuantity() {
        return quantity;
    }
}
