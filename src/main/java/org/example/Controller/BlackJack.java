package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.DeckOfCard;
import org.example.Model.Player;
import org.example.UTIL.Card;
import org.example.UTIL.CardValue;
import org.example.UTIL.ProbabilityForValue;
import org.example.UTIL.Suit;

public class BlackJack extends Card implements Casino{
    //TODO
    @Override
    public CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet) {
        return null;
    }
    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI) {
        populateDeck();
        Card[] playerHand = new Card[1];
        playerHand[0] = populateDeck();
        playerHand[1] = populateDeck();
//        if (playerHand[0].getCardValue() + playerHand[1].getCardValue() == 21)
//
//    }
        return null;
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
    public Card populateDeck(){
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
            int removedCard = ProbabilityForValue.randomValues(0, 51);
            if (shuffledDeck[51] != null) {
                break;
            }
            if (fullDeck[removedCard] != null) {
                shuffledDeck[i] = fullDeck[removedCard];
                fullDeck[removedCard] = null;
                System.out.println(shuffledDeck[i] + "Index" + i);
                i++;
            } else {
                continue;
            }
        }
        return shuffledDeck[ProbabilityForValue.randomValues(0, 51)];
    }

}
