package org.example.UTIL;

public class Card {
   protected CardValue[] allCardValues = new CardValue[13];
   protected Suit[] allSuits = new Suit[4];
   CardValue cardValue;
   Suit suit;

   public Card(CardValue cardValue, Suit suit){
    setCardValue(cardValue);
    setSuit(suit);
   }

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
    //Makes array of cardValues and Suits
    @Override
    public String toString() {
       StringBuilder cardBuilder = new StringBuilder();
       cardBuilder.append("\t\tCard:").append("\nCard value: ").append(cardValue);
       cardBuilder.append("\nCard suit: ").append(suit).append("\n\n");
        return cardBuilder.toString();
    }
}
