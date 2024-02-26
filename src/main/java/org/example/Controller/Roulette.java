package org.example.Controller;

import org.example.Model.Player;
import org.example.View.CasinoInterface;

import java.util.HashMap;
import java.util.Random;

public class Roulette implements Casino {
    //FIXME
    private Random rouletteRandom;
    private HashMap<Integer, Integer> payouts;
    protected int betType, betAmount;
    CasinoInterface UI = new CasinoInterface();

    public int spin() {
        return rouletteRandom.nextInt(37);
    }

    public void cashOut(Player currentPlayer, int playerBet) {
        //FIXME
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
    }

    @Override
    public Player play(Player currentPlayer, int playerBet) {
        //FIXME
        betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount(), currentPlayer);
        betType = UI.displayRouletteBetTypes();
        int outcome;
        switch(betType){
            case 1:
         outcome= spin();
        if (betType == 1 && outcome != 0 && outcome % 2 == 1) {
            betAmount += betAmount*payouts.get(1);
            cashOut(currentPlayer, betType);
            UI.displayRouletteWin(true, betAmount);
        }
            break;
            case 2:
                outcome = spin();
                if (betType == 2 && outcome != 0 && outcome % 2 == 0) {
                    betAmount += betAmount*payouts.get(2);
                    cashOut(currentPlayer, betType);
                    UI.displayRouletteWin(true, betAmount);
                }
                break;
            case 3:
                outcome = spin();
                if(betType == 3 && outcome != 0 && outcome % 3 == 0){
                    betAmount += betAmount*payouts.get(3);
                    cashOut(currentPlayer, betType);
                    UI.displayRouletteWin(true, betAmount);

                }
                break;
            case 4:
                int specificNumber = UI.displayRouletteSpecificNum();
                outcome = spin();
                if(betType == 4 && outcome != 0 && outcome == specificNumber){
                    betAmount += betAmount*payouts.get(4);
                    cashOut(currentPlayer, betType);
                    UI.displayRouletteWin(true, betAmount);
                }
                break;
            default:
                UI.displayRouletteWin(false,betAmount);
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
            payouts.put(3, 35);
            //The payout for betting on one single number, 50x payout
            payouts.put(4, 50);
        }
    }

