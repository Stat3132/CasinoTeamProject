package org.example.Model;

import org.example.UTIL.Card;
import org.example.UTIL.CardValue;
import org.example.UTIL.Suit;

import java.lang.reflect.Array;

public class DeckOfCard extends Card {
    int numberOfCards;

    public DeckOfCard(CardValue cardValue, Suit suit) {
        super(cardValue, suit);
    }


    /**
     * This is supposed to be a method that will just insert all cards into a randomized "Deck"
     * The parameters it is recieving are incorrect. In card that is where it will use polymorphism from both card values and suits and make cards
     * that take both of these parameters.

     */
    public void populateDeck(){
       // Card newCard = new Card();
    }

    @Override
    public String toString() {
        return "DeckOfCard{}";
    }
}
