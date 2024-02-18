package org.example.Model;

import org.example.UTIL.Card;

import java.lang.reflect.Array;

public class DeckOfCard extends Card {

    /**
     * This is supposed to be a method that will just insert all cards into a randomized "Deck"
     * The parameters it is recieving are incorrect. In card that is where it will use polymorphism from both card values and suits and make cards
     * that take both of these parameters.
     * @param cardValue
     * @param Suit
     */
    public DeckOfCard(Array cardValue, Array Suit) {
        super(cardValue, Suit);
    }
}
