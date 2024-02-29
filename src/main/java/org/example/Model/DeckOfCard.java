package org.example.Model;

import org.example.UTIL.Card;
import org.example.UTIL.CardValue;
import org.example.UTIL.ProbabilityForValue;
import org.example.UTIL.Suit;

public class DeckOfCard extends Card {
    int numberOfCards;
    Card[] fullDeck = new Card[52];

    public DeckOfCard(CardValue cardValue, Suit suit) {
        super(cardValue, suit);
    }
    public DeckOfCard(){
        super(CardValue.EIGHT,Suit.CLUB);

    }
    public void makingSuitsAndValueArrays() {
        CardValue[] intForCardValues = CardValue.values();
        Suit[] intForSuits = Suit.values();
        for (int i = 0; i < allCardValues.length; i++) {
            if (allCardValues[i] == null) {
                allCardValues[i] = intForCardValues[i];
            }
            if (i == intForCardValues.length - 1) {
                break;
            }
        }
        for (int i = 0; i < allSuits.length; i++) {
            if (allSuits[i] == null) {
                allSuits[i] = intForSuits[i];
            }
            if (i == allSuits.length - 1) {
                break;
            }
        }
    }
    public Card[] populateDeck(){
        makingSuitsAndValueArrays();
        int suitCounter = 0;
        int cardCounter = 0;
        for (int i = 0; i < 100 ; i++) {
            Card newCard = new Card(allCardValues[i],allSuits[suitCounter]);
            fullDeck[cardCounter] = newCard;
            cardCounter++;
            // System.out.println(newCard);
            if (suitCounter == 3 && i == 12){
                break;
            }
            if (i == 12){
                i = -1;
                suitCounter++;
            }
        }
        Card[] shuffledDeck = new Card[52];
        for (int i = 0; i < 52;) {
            int removedCard = ProbabilityForValue.randomValues(0,51);
            if (shuffledDeck[51] != null){
                return shuffledDeck;
            }
            if (fullDeck[removedCard] != null){
                shuffledDeck[i] = fullDeck[removedCard];
                fullDeck[removedCard] = null;
                System.out.println(shuffledDeck[i] + "Index" + i);
                i++;
            } else {
                continue;
            }
        }
        return shuffledDeck;
    }
    /**
     * This is supposed to be a method that will just insert all cards into a randomized "Deck"
     * The parameters it is recieving are incorrect. In card that is where it will use polymorphism from both card values and suits and make cards
     * that take both of these parameters.
     */
    public void fullCardDeck(){
       // Card newCard = new Card();
    }

    @Override
    public String toString() {
        return "DeckOfCard{}";
    }
}
