package org.example.UTIL;

public class Card {
   protected CardValue[] allCardValues = new CardValue[13];
   protected Suit[] allSuits = new Suit[4];
   protected Card[] fullDeck = new Card[52];
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

    public void populateDeck(){
       makingEnumArrays();
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
       Card[] shuffledDeck = new Card[53];
        for (int i = 0; i < 52; i++) {
            if (shuffledDeck[i] == null){
                shuffledDeck[i] = fullDeck[ProbabilityForValue.randomValues(0,51)];
                System.out.println(shuffledDeck[i]);
            }
        }
   }
    //Makes array of cardValues and Suits
    public void makingEnumArrays() {
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

    @Override
    public String toString() {
       StringBuilder cardBuilder = new StringBuilder();
       cardBuilder.append("\t\tCard:").append("\nCard value: ").append(cardValue);
       cardBuilder.append("\nCard suit: ").append(suit).append("\n\n");
        return cardBuilder.toString();
    }
}
