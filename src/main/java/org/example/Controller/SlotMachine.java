package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.View.UI;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class SlotMachine implements Casino {
    private static final String[] symbols = {"Cherry", "Apple", "Orange", "Banana", "Grapes","Watermelon","Tomato","Kiwi","Avocado","Egg","Corn","Dragonfruit","Eggplant","Mango","Blueberry","7"}; //string array that contains all fruits for the spins
    private static final int totalSymbols = symbols.length, totalSpins = 4; //total number of symbols and total number of slots to play
    private int incremental; //incremental index for each value inside the array
    private static final HashMap<String,Integer> payouts = new HashMap<>(); //hashmap for "payouts"
    private Random slotRandom;
    private String slotsWin = "";
    org.example.View.UI UI = new UI();

    public SlotMachine() {
        slotRandom = new Random();
        incremental = 1; //incremental value for each slot symbol
        for (int i = 0; i < totalSymbols; i++) {
            //for every slot symbol, set the hashmap to index and add to incremental so every value has +1 compared to the other
            payouts.put(symbols[i],i + incremental);
        }
    }

    public int spin(int playerBet, boolean isAI) {
        //spin method that takes in player bet
        String[] result = new String[totalSpins]; //new temp array which contains all total spins
        for (int i = 0; i < totalSpins; i++) {
            //for total spins, get random next int between 1-total symbols and
            result[i] = symbols[slotRandom.nextInt(totalSymbols)]; //index of temp array is set to randomized symbol
            if(!isAI){
                UI.displaySlots(result[i]);
            }
        }
        HashMap<String, Integer> symbolCounts = new HashMap<>(); //temp hashmap for symbolCounts
        for (String symbol : result) {
            //for loop that takes temp hashmap and gets random symbol value.
            symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
        }

        // Calculate payout if 2 or more symbols match
        int pairCount = 0;
        for (String symbol : symbolCounts.keySet()) {
            int count = symbolCounts.get(symbol);
            //get count value from each symbol slot
            switch(count){
                case 2:
                    //if count value is 2 (meaning 2 symbols match), return a payout from incremental count + count / 2
                    pairCount++;
                    if(pairCount == 2){
                        playerBet += payouts.getOrDefault(symbol, 0) + count * 3;
                        slotsWin = "DOUBLE PAIR";
                    } else if(Objects.equals(symbol, symbols[15])){
                        playerBet += payouts.getOrDefault(symbol, 0) + count * 4;
                        slotsWin = "DOUBLE 77";
                    } else {
                        playerBet += payouts.getOrDefault(symbol, 0) + count * 2;
                        slotsWin = "PAIR";
                    }
                    break;
                case 3:
                    //if count value is 3 (meaning 3 symbols match), return a payout from incremental count + default count
                    if(Objects.equals(symbol, symbols[15])){
                        playerBet += payouts.getOrDefault(symbol, 0) + count * 5;
                        slotsWin = "TRIPLE 777";
                    }
                    playerBet += payouts.getOrDefault(symbol, 0) + count * 3;
                    slotsWin = "TRIPLE";
                    break;
                case 4:
                    //if count value is 2 (meaning all symbols match), return a payout from incremental count + count / 2
                    if(Objects.equals(symbol, symbols[15])){
                        playerBet += payouts.getOrDefault(symbol, 0) + count * 6;
                        slotsWin = "7777";
                    }
                    playerBet += payouts.getOrDefault(symbol, 0) + count * 4;
                    slotsWin = "ALL";
                    break;
                default:
                    //no symbols match at all, value is subtracted from bet with more harsh take value. (easier to lose than win)
                    playerBet -= payouts.getOrDefault(symbol, 0) + count * 2;
                    if(slotsWin.isEmpty()){
                        slotsWin = "NONE";
                    }
                    break;
            }
        }
        UI.displaySlotsCheck(slotsWin);
        slotsWin = "";
        return playerBet;
    }


    @Override
    public CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet) {
        //return player cash values and player values
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalSlotMoney(currentPlayer.getTotalSlotMoney() + playerBet);
        return currentPlayer;
    }

    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI) {
        //play method
        int payout = spin(playerBet,isAI); //take in spin and return new money int amount
        if(isAI){
            if (payout <= 0) {
                //player has lost and will only lose the first bet they inserted.
                currentPlayer = cashOut(currentPlayer, -playerBet);
            } else {
                //player has won and gets their payout + their initial bet
                currentPlayer = cashOut(currentPlayer, payout);
            }
        } else {
            if (payout <= 0) {
                //player has lost and will only lose the first bet they inserted.
                currentPlayer = cashOut(currentPlayer, -playerBet);
                UI.didUserWin(false, payout + playerBet);
            } else {
                //player has won and gets their payout + their initial bet
                currentPlayer = cashOut(currentPlayer, payout);
                UI.didUserWin(true, payout + playerBet);
            }
        }
        return currentPlayer;
    }
}
