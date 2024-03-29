package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.View.UI;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class SlotMachine implements Casino {

    //region VARS
    //string array that contains all fruits for the spins
    private static final String[] symbols = {"Cherry", "Apple", "Orange", "Banana", "Grapes","Watermelon","Tomato","Kiwi","Avocado","Egg","Corn","Dragonfruit","Eggplant","Mango","Blueberry","7"};
    private static final int totalSymbols = symbols.length, totalSpins = 4; //total number of symbols and total number of slots to play
    private static final int incremental = 1; //incremental index for each symbol, e.g. Cherry = 1, Apple = 2, etc.
    private static final HashMap<String,Integer> payouts = new HashMap<>(); //hashmap for "payouts"
    private final Random slotRandom;
    private String slotsWin = "";
    org.example.View.UI UI = new UI(); //UI display
    //endregion

    //region SPIN LOGIC
    /**
     *
     * @param playerBet takes in playerBet to return a new payout based on their bet
     * @param isAI tag decides whether to spit out UI elements based if AI
     * @return payout after money is calculated for the randomized spin for "each" slot
     */
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
                        payoutMultiplier += .45;
                        slotsWin = "DOUBLE PAIR";
                    } else if(Objects.equals(symbol, symbols[15])){
                        payoutMultiplier += 0.65;
                        slotsWin = "PAIR 77's";
                    } else {
                        payoutMultiplier += 0.25;
                        slotsWin = "PAIR";
                    }
                    totalSymbolsMatched += count;
                    break;
                case 3:
                    //if count value is 3 (meaning 3 symbols match), return a modifier for triples
                    if(Objects.equals(symbol, symbols[15])){
                        payoutMultiplier += 1.25; // Increase the payout multiplier by 1.0 for each triple
                        slotsWin = "TRIPLE 777's";
                    } else {
                        payoutMultiplier += 1.05; // Increase the payout multiplier by 1.0 for each triple
                        slotsWin = "TRIPLE";
                    }
                    break;
                case 4:
                    //if count value is 4 (meaning all symbols match), return the highest possible modifier whether it's all 7s (SUPER MEGA LUCKY!) or all other symbol
                    if(Objects.equals(symbol, symbols[15])){
                        payoutMultiplier += 1.45;
                        slotsWin = "7777's";
                    } else {
                        payoutMultiplier += 1.25;
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
        }
        return payout;
    }
    //endregion

    //region CASINO INTERFACE IMPLEMENTED METHODS
    @Override
    public CasinoMembers cashOut(CasinoMembers player, int playerBet) {
        //return player cash values and player values
        player.setCurrentMoneyCount(player.getCurrentMoneyCount() + playerBet);
        player.setTotalWinnings(player.getTotalWinnings() + playerBet);
        player.setTotalSlotMoney(player.getTotalSlotMoney() + playerBet);
        return player;
    }
    @Override
    public CasinoMembers play(CasinoMembers player, int playerBet, boolean isAI) {
        //play method
        int payout = spin(playerBet,isAI); //take in spin and return new money int amount
        if(isAI){
            if (payout <= 0) {
                //AI has lost and will only lose the first bet they inserted.
                player = cashOut(player, -playerBet);
            } else {
                //AI has won and gets their payout + their initial bet
                player = cashOut(player, payout);
            }
        } else {
            if (payout <= 0) {
                //player has lost and will only lose the first bet they inserted.
                player = cashOut(player, -playerBet);
                UI.didUserWin(false, playerBet);
            } else {
                //player has won and gets their payout + their initial bet
                player = cashOut(player, payout);
                UI.didUserWin(true, payout + playerBet);
            }
        }
        return player;
    }
    //endregion

    //region CONSTRUCTOR
    public SlotMachine() {
        //constructor that implements an incremental value for each symbol
        slotRandom = new Random();
        for (int i = 0; i < totalSymbols; i++) {
            //for every slot symbol, set the hashmap to index and add to incremental so every value has +1 compared to the other
            payouts.put(symbols[i],i + incremental);
        }
    }
    //endregion
}
