package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.UTIL.Card;
import org.example.UTIL.CardValue;
import org.example.UTIL.ProbabilityForValue;
import org.example.UTIL.Suit;
import org.example.View.UI;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class BlackJack extends Card implements Casino {
    UI UI = new UI();
    Card[] shuffledDeck = new Card[52];
    int currentPlayerHand;
    int currentDealerHand;

    //TODO
    //region BLACKJACK LOGIC
    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI) {
        playerBet = playerBet * 2;
        populateDeck();
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();
        playerHand.add(populateDeck());
        playerHand.add(populateDeck());
        dealerHand.add(populateDeck());
        dealerHand.add(populateDeck());
        currentPlayerHand = playerHand.getFirst().getCardValue().ordinal() + 1 + playerHand.get(1).getCardValue().ordinal() + 1;
        currentDealerHand = dealerHand.getFirst().getCardValue().ordinal() + 1 + dealerHand.get(1).getCardValue().ordinal() + 1;
        UI.firstCards(playerHand.getFirst(), playerHand.get(1), false);
        UI.firstCards(playerHand.getFirst(), playerHand.get(1), true);
        //Player natural 21
        if (currentPlayerHand == 21) {
            UI.blackJackWins(1);
            UI.didUserWin(true, playerBet);
            currentPlayer = cashOut(currentPlayer, playerBet);
            //Dealer natural 21
        } else if (currentDealerHand == 21) {
            UI.blackJackWins(3);
            UI.didUserWin(false, playerBet);
            currentPlayer = cashOut(currentPlayer, -playerBet / 2);
        }
        for (int i = 2; i < 30; i++) {
            //First draw or stand
            switch (UI.blackJackPrompt()) {
                case 1:
                    playerHand.add(i, populateDeck());
                    currentPlayerHand = playerHand.get(2).getCardValue().ordinal() + 1 + currentPlayerHand;
                    if (currentPlayerHand > 21) {
                        UI.blackJackWins(5);
                        UI.didUserWin(false, playerBet / 2);
                        currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                        UI.finalCards(playerHand, dealerHand);
                        return currentPlayer;
                    }
                    if (currentPlayerHand < 21) {
                        UI.finalCards(playerHand, dealerHand);
                        continue;
                    }

                    if (currentPlayerHand == 21) {
                        UI.blackJackWins(2);
                        UI.didUserWin(true, playerBet);
                        currentPlayer = cashOut(currentPlayer, playerBet);
                        UI.finalCards(playerHand, dealerHand);
                        return currentPlayer;
                    }


                    if (currentDealerHand <= 16) {
                        dealerHand.add(i, populateDeck());
                        currentDealerHand = playerHand.get(2).getCardValue().ordinal() + 1 + currentDealerHand;

                        if (currentDealerHand == 21) {
                            UI.blackJackWins(4);
                            UI.didUserWin(false, playerBet / 2);
                            currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                            UI.finalCards(playerHand, dealerHand);
                            return currentPlayer;

                        }
                        if (currentDealerHand > 21) {
                            UI.blackJackWins(6);
                            UI.didUserWin(true, playerBet);
                            currentPlayer = cashOut(currentPlayer, playerBet);
                            UI.finalCards(playerHand, dealerHand);
                            return currentPlayer;
                        }
                    }
                    break;
                case 2:
                    if (currentPlayerHand > currentDealerHand) {
                        UI.blackJackWins(2);
                        UI.didUserWin(true, playerBet);
                        currentPlayer = cashOut(currentPlayer, playerBet);
                        UI.finalCards(playerHand, dealerHand);
                        return currentPlayer;
                    }
                    if (currentDealerHand > currentPlayerHand) {
                        UI.blackJackWins(4);
                        UI.didUserWin(false, playerBet / 2);
                        currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                        UI.finalCards(playerHand, dealerHand);
                        return currentPlayer;
                    }
                    break;
            }
            break;
        }
        UI.finalCards(playerHand, dealerHand);

        return currentPlayer;
    }

    @Override
    public CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalBlackJackMoney(currentPlayer.getTotalHorseMoney() + playerBet);
        return currentPlayer;
    }
    //endregion

    //region CREATING SHUFFLED DECK

    //An array of enums that will be inputted into cards to make multiple cards.
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

    //Creates a deck from 1-52 un shuffled, then shuffles the deck.
    public Card populateDeck() {
        makingSuitsAndValueArrays();
        int suitCounter = 0;
        int cardCounter = 0;
        for (int i = 0; i < 100; i++) {
            Card newCard = new Card(allCardValues[i], allSuits[suitCounter]);
            fullDeck[cardCounter] = newCard;
            cardCounter++;
            // System.out.println(newCard);
            if (suitCounter == 3 && i == 12) {
                break;
            }
            if (i == 12) {
                i = -1;
                suitCounter++;
            }
        }

        for (int i = 0; i < 52; ) {
            int removedCard = ProbabilityForValue.randomValues(0, 51);
            if (shuffledDeck[51] != null) {
                break;
            }
            if (fullDeck[removedCard] != null) {
                shuffledDeck[i] = fullDeck[removedCard];
                fullDeck[removedCard] = null;
                i++;
            } else {
                continue;
            }
        }
        return shuffledDeck[ProbabilityForValue.randomValues(0, 51)];
    }
    //endregion

}
