package com.github.evanquan.deckhand.cards;

import java.util.Collections;
import java.util.Stack;

/**
 * A deck of {@link Card}s.
 *
 * @author Evan Quan
 */
public class Deck extends Stack<Card> {

    /**
     * Create an empty deck containing no {@link Card}s.
     */
    public Deck() {
    }

    /**
     * Randomly permutes the specified list using a default source of
     * randomness. All permutations occur with approximately equal likelihood.
     * The hedge "approximately" is used in the foregoing description because
     * default source of randomness is only approximately an unbiased source of
     * independently chosen bits. If it were a perfect source of randomly chosen
     * bits, then the algorithm would choose permutations with perfect
     * uniformity. This implementation traverses the list backwards, from the
     * last element up to the second, repeatedly swapping a randomly selected
     * element into the "current position". Elements are randomly selected from
     * the portion of the list that runs from the first element to the current
     * position, inclusive. This method runs in linear time. If the specified
     * list does not implement the RandomAccess interface and is large, this
     * implementation dumps the specified list into an array before shuffling
     * it, and dumps the shuffled array back into the list. This avoids the
     * quadratic behavior that would result from shuffling a "sequential access"
     * list in place.
     */
    public void shuffle() {
        Collections.shuffle(this);
    }

    /**
     * Add another deck to the top of this one.
     *
     * @param other deck to add to the top of this deck. The other deck is then
     *              emptied.
     */
    public void addToTop(Deck other) {
        this.addAll(other);
        other.clear();
    }

    /**
     * Shuffle with deck with another deck.
     *
     * @param other deck to add to this deck before shuffling.
     */
    public void shuffleWith(Deck other) {
        this.addToTop(other);
        this.shuffle();
    }

    /**
     * Draw the topmost card from this deck.
     *
     * @return the top card drawn from this deck.
     */
    public Card drawFromTop() {
        return this.pop();
    }

    /**
     * Add a {@link Card} to the top of this deck.
     *
     * @param card to add to the top of this deck.
     */
    public void addToTop(Card card) {
        this.push(card);
    }
}
