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

        // Calculate payout if 2 or more symbols
        double payoutMultiplier = 1.0; // Initialize the payout multiplier to 1.0
        int totalSymbolsMatched = 0; // Count total symbols matched

        for (String symbol : symbolCounts.keySet()) {
            int count = symbolCounts.get(symbol);
            switch(count){
                case 2:
                    //if count value is 2 (meaning 2 symbols match), return a basic payout for doubles
                    if(totalSymbolsMatched == 2){
                        payoutMultiplier += .65;
                        slotsWin = "DOUBLE PAIR";
                    } else if(Objects.equals(symbol, symbols[15])){
                        payoutMultiplier += 0.85;
                        slotsWin = "PAIR 77's";
                    } else {
                        payoutMultiplier += 0.35;
                        slotsWin = "PAIR";
                    }
                    totalSymbolsMatched += count;
                    break;
                case 3:
                    //if count value is 3 (meaning 3 symbols match), return a modifier for triples
                    if(Objects.equals(symbol, symbols[15])){
                        payoutMultiplier += 1.35; // Increase the payout multiplier by 1.0 for each triple
                        slotsWin = "TRIPLE 777's";
                    } else {
                        payoutMultiplier += 1.05; // Increase the payout multiplier by 1.0 for each triple
                        slotsWin = "TRIPLE";
                    }
                    break;
                case 4:
                    //if count value is 4 (meaning all symbols match), return the highest possible modifier whether it's all 7s (SUPER MEGA LUCKY!) or all other symbol
                    if(Objects.equals(symbol, symbols[15])){
                        payoutMultiplier += 1.65;
                        slotsWin = "7777's";
                    } else {
                        payoutMultiplier += 1.35;
                        slotsWin = "QUADRUPLE";
                    }
                    break;
                default:
                    //no symbols match at all, value is subtracted from bet with more harsh take value. (easier to lose than win)
                    payoutMultiplier -= .25;
                    if(slotsWin.isEmpty()){
                        slotsWin = "NONE";
                    }
                    break;
            }
        }
        int payout = (int) (playerBet * payoutMultiplier);
        if(!isAI) {
            UI.displaySlotsCheck(slotsWin);
            slotsWin = "";
        }
        return payout;
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
                UI.didUserWin(false, playerBet);
            } else {
                //player has won and gets their payout + their initial bet
                currentPlayer = cashOut(currentPlayer, payout);
                UI.didUserWin(true, payout + playerBet);
            }
        }
        return currentPlayer;
    }
}
