package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.UI;

import java.util.HashMap;
import java.util.Random;

public class Roulette implements Casino {
    private final Random rouletteRandom;
    private HashMap<Integer, Integer> payouts;
    protected int betType, betAmount;
    UI UI = new UI();

    public int spin() {
        return rouletteRandom.nextInt(37);
    }

    @Override
    public CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalRouletteMoney(currentPlayer.getTotalRouletteMoney() + playerBet);
        return currentPlayer;
    }

    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI) {
        betAmount = playerBet;
        int outcome;
        if(isAI){
            betType = ProbabilityForValue.randomValues(1,4);
        } else {
            betType = UI.displayRouletteBetTypes();
        }
        switch(betType){
            case 1:
                outcome = spin();
                if(isAI){
                    if (betType == 1 && (outcome % 2 == 1)) {
                        betAmount = betAmount * 2;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                    } else {
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                } else {
                    if (betType == 1 && (outcome % 2 == 1)) {
                        betAmount = betAmount * 2;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                        UI.didUserWin(true, betAmount);
                    } else {
                        UI.didUserWin(false, betAmount);
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                }
                break;
            case 2:
                outcome = spin();
                if(isAI){
                    if (betType == 2 && outcome != 0 && (outcome % 2 == 0)) {
                        betAmount = betAmount * 2;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                    } else {
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                } else {
                    if (betType == 2 && outcome != 0 && (outcome % 2 == 0)) {
                        betAmount = betAmount * 2;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                        UI.didUserWin(true, betAmount);
                    } else {
                        UI.didUserWin(false, betAmount);
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                }
                break;
            case 3:
                outcome = spin();
                if(isAI){
                    if(betType == 3 && outcome != 0 && (outcome % 3 == 0)){
                        betAmount = betAmount*10;
                        currentPlayer = cashOut(currentPlayer, betAmount);

                    } else {
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                } else {
                    if(betType == 3 && outcome != 0 && (outcome % 3 == 0)){
                        betAmount = betAmount*10;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                        UI.didUserWin(true, betAmount);

                    } else {
                        UI.didUserWin(false, betAmount);
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                }
                break;
            case 4:
                outcome = spin();
                if(isAI){
                    int specificNumber = ProbabilityForValue.randomValues(1,36);
                    if (betType == 4 && outcome != 0 && outcome == specificNumber) {
                        betAmount = betAmount * 50;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                    } else {
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                } else {
                    int specificNumber = UI.displayRouletteSpecificNum();
                    if (betType == 4 && outcome != 0 && outcome == specificNumber) {
                        betAmount = betAmount * 50;
                        currentPlayer = cashOut(currentPlayer, betAmount);
                        UI.didUserWin(true, betAmount);
                    } else {
                        UI.didUserWin(false, betAmount);
                        currentPlayer = cashOut(currentPlayer, -betAmount);
                    }
                }
                break;
        }
        return currentPlayer;
    }
    //constructor
    public Roulette() {
        rouletteRandom = new Random();
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