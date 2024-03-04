package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.UTIL.Card;
import org.example.UTIL.CardValue;
import org.example.UTIL.ProbabilityForValue;
import org.example.UTIL.Suit;
import org.example.View.UI;

import java.util.ArrayList;

public class BlackJack extends Card implements Casino {
    //region VAR
    final int WINNINGNUMBER = 21;
    UI UI = new UI();
    Card[] shuffledDeck = new Card[52];
    int currentPlayerHand;
    int currentDealerHand;
    int increasingCardCount = -1;

    //endregion
    //region BLACKJACK LOGIC
    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI) {
        //region START BLACKJACK
        populateDeck();
        CardValue.ACE.setCardCount(11);
        playerBet = playerBet * 2;
        //Player hand
        ArrayList<Card> playerHand = new ArrayList<>();
        //Dealer hand
        ArrayList<Card> dealerHand = new ArrayList<>();
        //Player's first 2 cards.
        playerHand.add(randomCard());
        playerHand.add(randomCard());
        //Dealer's first 2 cards.
        dealerHand.add(randomCard());
        dealerHand.add(randomCard());
        //Keeps the value of both dealer and players cards
        currentPlayerHand = playerHand.getFirst().getCardValue().getCardCount() + playerHand.get(1).getCardValue().getCardCount();
        currentDealerHand = dealerHand.getFirst().getCardValue().getCardCount() + dealerHand.get(1).getCardValue().getCardCount();
        //This displays the dealer and the players first hands.
        UI.displayBlackJackCards(playerHand, dealerHand, true, false, false);
        //Player natural 21
        if (currentPlayerHand == WINNINGNUMBER) {
            UI.blackJackWins(1, currentPlayerHand, currentDealerHand);
            UI.didUserWin(true, playerBet * 5);
            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
            //"Jack pot"
            currentPlayer = cashOut(currentPlayer, playerBet * 5);
            return currentPlayer;
            //Dealer natural 21
        } else if (currentDealerHand == WINNINGNUMBER) {
            UI.blackJackWins(3, currentPlayerHand, currentDealerHand);
            UI.didUserWin(false, playerBet);
            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
            currentPlayer = cashOut(currentPlayer, -playerBet / 2);
            return currentPlayer;
        }
        //endregion
        for (int cardIndex = 2; cardIndex < 50; cardIndex++) {
            //First draw or stand
            switch (UI.blackJackPrompt()) {
                //HIT
                case 1:
                    //region HIT
                    //Gives player one card
                    playerHand.add(cardIndex, randomCard());
                    currentPlayerHand = playerHand.get(cardIndex).getCardValue().getCardCount() + currentPlayerHand;
                    //If player went above 21
                    if (currentPlayerHand > WINNINGNUMBER) {
                        for (int playerAce = 0; playerAce < playerHand.size(); playerAce++) {
                            //If dealer has an ace his ace will become a 1.
                            if (playerHand.get(playerAce).getCardValue().getCardCount() == CardValue.ACE.getCardCount()) {
                                playerHand.get(playerAce).getCardValue().setCardCount(1);
                                currentPlayerHand = playerHand.get(playerAce).getCardValue().getCardCount() + currentPlayerHand - 11;
                                break;
                            }
                        }
                    }
                    //Second check
                    //If no ace then player busted and loses
                    if (currentPlayerHand > WINNINGNUMBER) {
                        UI.blackJackWins(5, currentPlayerHand, currentDealerHand);
                        UI.didUserWin(false, playerBet / 2);
                        currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                        UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        return currentPlayer;
                    }
                    //Dealer does not have to draw if their total is 17 or above
                    if (currentDealerHand >= 17) {

                    } else {
                        //If dealer has less than 17 dealer draws.
                        dealerHand.add(cardIndex, randomCard());
                        currentDealerHand = dealerHand.get(cardIndex).getCardValue().getCardCount() + currentDealerHand;
                        //If dealer has higher than 21 he busts if.
                        if (currentDealerHand > WINNINGNUMBER) {
                            for (int dealerAce = 0; dealerAce < dealerHand.size(); dealerAce++) {
                                //If dealer has an ace his ace will become a 1.
                                if (dealerHand.get(dealerAce).getCardValue().getCardCount() == CardValue.ACE.getCardCount()) {
                                    dealerHand.get(dealerAce).getCardValue().setCardCount(1);
                                    currentDealerHand = dealerHand.get(dealerAce).getCardValue().getCardCount() + currentDealerHand - 11;
                                    break;
                                }
                            }
                            //Second check
                            //If no ace then dealer busts.
                        }
                        if (currentDealerHand > WINNINGNUMBER) {
                            UI.blackJackWins(6, currentPlayerHand, currentDealerHand);
                            UI.didUserWin(true, playerBet);
                            currentPlayer = cashOut(currentPlayer, playerBet);
                            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                            return currentPlayer;
                        }
                    }
                    //If Players total is 21 they win.
                    if (currentPlayerHand == WINNINGNUMBER) {
                        UI.blackJackWins(2, currentPlayerHand, currentDealerHand);
                        UI.didUserWin(true, playerBet);
                        currentPlayer = cashOut(currentPlayer, playerBet);
                        UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        return currentPlayer;

                        //If dealer has 21 they win and the player loses.
                    } else if (currentDealerHand == WINNINGNUMBER) {
                        UI.blackJackWins(4, currentPlayerHand, currentDealerHand);
                        UI.didUserWin(false, playerBet / 2);
                        currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                        UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        return currentPlayer;
                    }

                    //If player card amount is lower than 21 then they can continue and get another card.
                    UI.displayBlackJackCards(playerHand, dealerHand, false, true, false);
                    continue;
                    //endregion
                    //STAND
                case 2:
                    //region STAND
                    //Both the dealer and the player have equal cards
                    if (currentDealerHand == currentPlayerHand) {
                        UI.blackJackWins(9, currentPlayerHand, currentDealerHand);
                        currentPlayer = cashOut(currentPlayer, playerBet / 2);
                        UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        return currentPlayer;
                    }
                    //If player total is greater than dealer hand the player wins.
                    if (currentPlayerHand > currentDealerHand) {
                        UI.blackJackWins(7, currentPlayerHand, currentDealerHand);
                        UI.didUserWin(true, playerBet);
                        currentPlayer = cashOut(currentPlayer, playerBet);
                        UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        return currentPlayer;
                    }
                    //If dealer total is greater than player hand the dealer wins.
                    UI.blackJackWins(8, currentPlayerHand, currentDealerHand);
                    UI.didUserWin(false, playerBet / 2);
                    currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                    UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                    return currentPlayer;
                //endregion
                case 3:
                    //region USER ACE LOGIC
                    boolean hasAce = false;
                    for (int searchingForAce = 0; searchingForAce < playerHand.size(); searchingForAce++) {
                        if (playerHand.get(searchingForAce).getCardValue().getCardCount() == CardValue.ACE.getCardCount()) {
                            switch (UI.changeAce(true)) {
                                case 1:
                                    playerHand.get(searchingForAce).getCardValue().setCardCount(1);
                                    currentPlayerHand = playerHand.get(searchingForAce).getCardValue().getCardCount() + currentPlayerHand - 11;
                                    hasAce = true;
                                    break;
                                case 2:
                                    playerHand.get(searchingForAce).getCardValue().setCardCount(11);
                                    currentPlayerHand = playerHand.get(searchingForAce).getCardValue().getCardCount() + currentPlayerHand - 1;
                                    hasAce = true;
                                    break;
                            }
                        }

                    }
                    if (!hasAce) {
                        UI.changeAce(false);
                        break;
                    }
                    //endregion
                default:
                    //Any other input other than 1-3
                    cardIndex = 1;
                    continue;
            }
        }
        //Returns current players money
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
    public void populateDeck() {
        //Un shuffled deck
        makingSuitsAndValueArrays();
        int suitCounter = 0;
        int cardCounter = 0;
        for (int i = 0; i < 100; i++) {
            //Putting a card value and a suit into every card to create an instance of a card to input into a deck of 52.
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
        //Shuffled deck
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
    }

    public Card randomCard() {
        //Not getting out of card array.
        if (increasingCardCount == 51) {
            increasingCardCount = 0;
        }
        //Picks the next card from the deck of 52 cards.
        increasingCardCount++;
        return shuffledDeck[increasingCardCount];
    }
    //endregion
}
