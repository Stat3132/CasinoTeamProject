package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.UTIL.Card;
import org.example.UTIL.CardValue;
import org.example.UTIL.ProbabilityForValue;
import org.example.UTIL.Suit;
import org.example.View.UI;
import java.util.ArrayList;

public class BlackJack extends Card implements Casino {

    //region VARS
    final static int winningNum = 21;
    UI UI = new UI();
    Card[] shuffledDeck = new Card[52];
    int playerHand, dealerHand, increasingCardCount = -1;

    //endregion

    //region BLACKJACK LOGIC
    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI) {
        boolean playerAceChanged = false, dealerAceChanged = false;
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
        this.playerHand = playerHand.get(0).getCardValue().getCardCount() + playerHand.get(1).getCardValue().getCardCount();
        this.dealerHand = dealerHand.get(0).getCardValue().getCardCount() + dealerHand.get(1).getCardValue().getCardCount();

        if(!isAI) {
            //This displays the dealer and the players first hands.
            UI.displayBlackJackCards(playerHand, dealerHand, true, false, false);
        }
        //Player natural 21
        if (this.playerHand == winningNum) {
            if(!isAI) {
                UI.blackJackWins(1, this.playerHand, this.dealerHand);
                UI.didUserWin(true, playerBet * 5);
                UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
            }
            //"Jack pot"
            currentPlayer = cashOut(currentPlayer, playerBet * 5);
            return currentPlayer;
            //Dealer natural 21
        } else if (this.dealerHand == winningNum) {
            if(!isAI) {
                UI.blackJackWins(3, this.playerHand, this.dealerHand);
                UI.didUserWin(false, playerBet);
                UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
            }
            currentPlayer = cashOut(currentPlayer, -playerBet / 2);
            return currentPlayer;
        }
        //endregion
        for (int cardIndex = 2; cardIndex < 50; cardIndex++) {
            //First draw or stand
            int choice = 0;
            if (isAI) {
                //if AI, randomize the choice
                choice = ProbabilityForValue.randomValues(1, 2);
            } else {
                choice = UI.blackJackPrompt();
            }
            switch (choice) {
                case 1://region HIT
                    //Gives player one card
                    playerHand.add(cardIndex, randomCard());
                    this.playerHand = playerHand.get(cardIndex).getCardValue().getCardCount() + this.playerHand;
                    //If player went above 21
                    if (this.playerHand > winningNum) {
                        for (int playerAce = 0; playerAce < playerHand.size(); playerAce++) {
                            //If player has an ace his ace will become a 1.
                            if (playerHand.get(playerAce).getCardValue().getCardCount() == CardValue.ACE.getCardCount()) {
                                if (!playerAceChanged) {
                                    playerHand.get(playerAce).getCardValue().setCardCount(1);
                                    this.playerHand = playerHand.get(playerAce).getCardValue().getCardCount() + this.playerHand - 11;
                                    playerAceChanged = true;
                                }
                            }
                        }
                    }
                    //Second check
                    if (this.playerHand > winningNum) {
                        if (!isAI) {
                            UI.blackJackWins(5, this.playerHand, this.dealerHand);
                            UI.didUserWin(false, playerBet / 2);
                            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        }
                        currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                        return currentPlayer;
                    }
                    //If no ace then player busted and loses
                    //Dealer does not have to draw if their total is 17 or above
                    if (this.dealerHand < 17) {
                        //If dealer has less than 17 dealer draws.
                        dealerHand.add(cardIndex, randomCard());
                        this.dealerHand = dealerHand.get(cardIndex).getCardValue().getCardCount() + this.dealerHand;
                        //If dealer has higher than 21 he busts if.
                        if (this.dealerHand > winningNum) {
                            for (int dealerAce = 0; dealerAce < dealerHand.size(); dealerAce++) {
                                //If dealer has an ace his ace will become a 1.
                                if (dealerHand.get(dealerAce).getCardValue().getCardCount() == CardValue.ACE.getCardCount()) {
                                    if (!dealerAceChanged) {
                                        dealerHand.get(dealerAce).getCardValue().setCardCount(1);
                                        this.dealerHand = dealerHand.get(dealerAce).getCardValue().getCardCount() + this.dealerHand - 11;
                                        dealerAceChanged = true;
                                    }
                                }
                            }
                            //Second check
                            //If no ace then dealer busts.
                        }
                        if (this.dealerHand > winningNum) {
                            if (!isAI) {
                                UI.blackJackWins(6, this.playerHand, this.dealerHand);
                                UI.didUserWin(true, playerBet);
                                UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                            }
                            currentPlayer = cashOut(currentPlayer, playerBet);
                            return currentPlayer;
                        }
                    }
                    //If Players total is 21 they win.
                    if (this.playerHand == winningNum) {
                        if (!isAI) {
                            UI.blackJackWins(2, this.playerHand, this.dealerHand);
                            UI.didUserWin(true, playerBet);
                            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        }
                        currentPlayer = cashOut(currentPlayer, playerBet);
                        return currentPlayer;
                    } else if (this.dealerHand == winningNum) {
                        //If dealer has 21 they win and the player loses.
                        if (!isAI) {
                            UI.blackJackWins(4, this.playerHand, this.dealerHand);
                            UI.didUserWin(false, playerBet / 2);
                            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        }
                        currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                        return currentPlayer;
                    }

                    //If player card amount is lower than 21 then they can continue and get another card.
                    if (!isAI) {
                        UI.displayBlackJackCards(playerHand, dealerHand, false, true, false);
                    }
                    continue;
                    //endregion
                case 2://region STAND
                    //Both the dealer and the player have equal cards
                    if (this.dealerHand == this.playerHand) {
                        if (!isAI) {
                            UI.blackJackWins(9, this.playerHand, this.dealerHand);
                            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        }
                        currentPlayer = cashOut(currentPlayer, playerBet / 2);
                        return currentPlayer;
                    }
                    //If player total is greater than dealer hand the player wins.
                    if (this.playerHand > this.dealerHand) {
                        if (!isAI) {
                            UI.blackJackWins(7, this.playerHand, this.dealerHand);
                            UI.didUserWin(true, playerBet);
                            UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                        }
                        currentPlayer = cashOut(currentPlayer, playerBet);
                        return currentPlayer;
                    }
                    //If dealer total is greater than player hand the dealer wins.
                    if (!isAI) {
                        UI.blackJackWins(8, this.playerHand, this.dealerHand);
                        UI.didUserWin(false, playerBet / 2);
                        UI.displayBlackJackCards(playerHand, dealerHand, false, false, true);
                    }
                    currentPlayer = cashOut(currentPlayer, -playerBet / 2);
                    return currentPlayer;
                //endregion
                case 3:
                    //region USER ACE LOGIC
                    boolean hasAce = false;
                    for (int searchingForAce = 0; searchingForAce < playerHand.size(); searchingForAce++) {
                        if (playerHand.get(searchingForAce).getCardValue().getCardCount() == CardValue.ACE.getCardCount()) {
                            switch (UI.changeAce(true)) {
                                case 1:
                                    //Turns ace into a 1
                                    playerHand.get(searchingForAce).getCardValue().setCardCount(1);
                                    this.playerHand = playerHand.get(searchingForAce).getCardValue().getCardCount() + this.playerHand - 11;
                                    hasAce = true;
                                    break;
                                case 2:
                                    //Turns ace into a 11
                                    playerHand.get(searchingForAce).getCardValue().setCardCount(11);
                                    this.playerHand = playerHand.get(searchingForAce).getCardValue().getCardCount() + this.playerHand - 1;
                                    hasAce = true;
                                    break;
                            }
                        }
                    }
                    if (!hasAce) {
                        UI.changeAce(false);
                        cardIndex--;
                        break;
                    }
                    //endregion
                default:
                    //Any other input other than 1-3
                    cardIndex = 1;
            }

        }
        //Returns current players money
        return currentPlayer;

    }
    @Override
    public CasinoMembers cashOut(CasinoMembers player, int playerBet) {
        player.setCurrentMoneyCount(player.getCurrentMoneyCount() + playerBet);
        player.setTotalWinnings(player.getTotalWinnings() + playerBet);
        player.setTotalBlackJackMoney(player.getTotalHorseMoney() + playerBet);
        return player;
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