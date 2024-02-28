package org.example.Controller;

import org.example.Model.Player;
import org.example.View.UI;

import java.util.HashMap;
import java.util.Random;

public class SlotMachine implements Casino {
    private static final String[] symbols = {"Cherry", "Apple", "Orange", "Banana", "Grapes","Watermelon","Tomato","Kiwi","Avocado","Egg"}; //string array that contains all fruits for the spins
    private static final int totalSymbols = symbols.length, totalSpins = 4;
    private int incremental;
    private static final HashMap<String,Integer> payouts = new HashMap<>();
    private Random slotRandom;
    org.example.View.UI UI = new UI();

    public SlotMachine() {
        slotRandom = new Random();
        incremental = 1;
        for (int i = 0; i < totalSymbols; i++) {
            payouts.put(symbols[i],i + incremental);
        }
    }

    public int spin(int playerBet) {
        String[] result = new String[totalSpins];
        for (int i = 0; i < totalSpins; i++) {
            result[i] = symbols[slotRandom.nextInt(totalSymbols)];
            System.out.println(result[i]);
        }
        HashMap<String, Integer> symbolCounts = new HashMap<>();
        for (String symbol : result) {
            symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
        }

        // Calculate payout if 2 or more symbols match
        for (String symbol : symbolCounts.keySet()) {
            int count = symbolCounts.get(symbol);
            if (count == 2) {
                playerBet += payouts.getOrDefault(symbol, 0) + count / 2;
                System.out.println(playerBet);
            } else  if(count == 3) {
                playerBet += payouts.getOrDefault(symbol, 0) + count;
                System.out.println(playerBet);
            } else if(count >= 4) {
                playerBet += payouts.getOrDefault(symbol, 0) + count * 2;
                System.out.println(playerBet);
            } else {
                playerBet -= payouts.getOrDefault(symbol, 0) + count * 2;
                System.out.println(playerBet);
            }
        }
        return playerBet;
    }


    @Override
    public Player cashOut(Player currentPlayer, int playerBet) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalSlotMoney(currentPlayer.getTotalSlotMoney() + playerBet);
        return currentPlayer;
    }

    @Override
    public Player play(Player currentPlayer, int playerBet) {
        int payout = spin(playerBet);
        if(payout <= 0){
            currentPlayer = cashOut(currentPlayer,-playerBet);
            UI.didUserWin(false,payout + playerBet);
        } else {
            currentPlayer = cashOut(currentPlayer,payout + playerBet);
            UI.didUserWin(true,payout + playerBet);
        }
        return currentPlayer;
    }
}
