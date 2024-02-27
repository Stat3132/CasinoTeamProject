package org.example.Controller;

import org.example.Model.Player;
import org.example.View.CasinoInterface;

import java.util.HashMap;
import java.util.Random;

public class Roulette implements Casino {
    //FIXME
    private final Random rouletteRandom;
    private HashMap<Integer, Integer> payouts;
    protected int betType, betAmount;
    CasinoInterface UI = new CasinoInterface();

    public int spin(){
        return rouletteRandom.nextInt(36);
    }

    public void cashOut(Player currentPlayer, int playerBet) {
        //FIXME
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
    }
    @Override
    public Player play(Player currentPlayer, int playerBet) {
        //FIXME
//        int selection = UI.gamePrompt();
//        switch (selection){
//            case 1:
//                betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount(),currentPlayer);
//                betType = UI.displayRouletteBetTypes();
//                spin();
//                cashOut(betType,currentPlayer);
//                UI.displayRouletteWin(true,betAmount);
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//        }
        return null;
    }

    //constructor
    public Roulette(){
        this.rouletteRandom = new Random();
        this.payouts = new HashMap<>();
        //The payout for betting on all reds/odds, 2x payout
        payouts.put(1,2);
        //The payout for betting on all blacks/evens, 2x payout
        payouts.put(2,2);
        //The payout for betting on all triples, 35x payout
        payouts.put(3,35);
        //The payout for betting on one single number, 50x payout
        payouts.put(4,50);
    }
}
