package org.example.UTIL;

public class Card {
    //region VAR
   protected CardValue[] allCardValues = new CardValue[13];
   protected Suit[] allSuits = new Suit[4];
   protected Card[] fullDeck = new Card[52];
   CardValue cardValue;
   Suit suit;
   //endregion

    //region CONSTRUCTOR and GETTERS AND SETTERS
   public Card(CardValue cardValue, Suit suit){
    setCardValue(cardValue);
    setSuit(suit);
   }
   public Card(){

   }

    //region REMOVED BRANCH
    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        if (cardValue != null) {
            this.cardValue = cardValue;
        }
    }
    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
       if (suit != null) {
           this.suit = suit;
       }
    }
    //endregion
    //Makes array of cardValues and Suits
    @Override
    public String toString() {
        return cardValue + " of " + suit;
    }
    //endregion
}
