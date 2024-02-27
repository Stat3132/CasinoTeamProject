package org.example.Controller;

import org.example.Model.Player;
import org.example.View.UI;

import java.util.HashMap;
import java.util.Random;

public class Roulette implements Casino {
    //FIXME
    private Random rouletteRandom;
    private HashMap<Integer, Integer> payouts;
    protected int betType, betAmount;
    UI UI = new UI();

    public int spin() {
        return rouletteRandom.nextInt(37);
    }

    public void cashOut(Player currentPlayer, int playerBet) {
        //FIXME
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
    }

    @Override
    public Player play(Player currentPlayer, int playerBet) {

        betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount(),currentPlayer);
        betType = UI.displayRouletteBetTypes();
        int outcome;
        switch(betType){
            case 1:
                outcome= spin();
                if (betType == 1 && outcome != 0 && (outcome % 2 == 1)) {
                     betAmount = betAmount*2;
                    cashOut(currentPlayer, betAmount);
                    UI.displayRouletteWin(true, betAmount);
                }else {
                    UI.displayRouletteWin(false, betAmount);
                }
                break;
            case 2:
                outcome = spin();
                if (betType == 2 && outcome != 0 && (outcome % 2 == 0)) {
                    betAmount = betAmount*2;
                    cashOut(currentPlayer, betAmount);
                    UI.displayRouletteWin(true, betAmount);
                }else {
                    UI.displayRouletteWin(false, betAmount);
                }
                break;
            case 3:
                outcome = spin();
                if(betType == 3 && outcome != 0 && (outcome % 3 == 0)){
                    betAmount = betAmount*10;
                    cashOut(currentPlayer, betAmount);
                    UI.displayRouletteWin(true, betAmount);

                }else {
                    UI.displayRouletteWin(false, betAmount);
                }
                break;
            case 4:
                int specificNumber = UI.displayRouletteSpecificNum();
                outcome = spin();
                if(betType == 4 && outcome != 0 && outcome == specificNumber){
                    betAmount = betAmount*50;
                    cashOut(currentPlayer, betAmount);
                    UI.displayRouletteWin(true, betAmount);
                }else {
                    UI.displayRouletteWin(false,betAmount);
                }
                break;


        }
        return currentPlayer;
    }


    //constructor
    public Roulette() {
        this.rouletteRandom = new Random();
        this.payouts = new HashMap<>();
        //The payout for betting on all reds/odds, 2x payout
        payouts.put(1, 2);
        //The payout for betting on all blacks/evens, 2x payout
        payouts.put(2, 2);
        //The payout for betting on all triples, 35x payout
        payouts.put(3, 10);
        //The payout for betting on one single number, 50x payout
        payouts.put(4, 50);
    }


}