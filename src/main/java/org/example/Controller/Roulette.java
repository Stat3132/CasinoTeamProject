package org.example.Controller;

import org.example.Model.Player;
import org.example.View.CasinoInterface;

import java.util.HashMap;
import java.util.Random;

public class Roulette implements Casino {
    //FIXME
    private Random rouletteRandom;
    private HashMap<Integer, Integer> payouts;
    protected int betType;
    protected int betAmount;
    CasinoInterface UI = new CasinoInterface();

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



    public int spin(){
        return rouletteRandom.nextInt(36);
    }

    /**
     * The amount that is being bet, maybe this method and roulette should have correlation as it seems like the roulette
     * method is putting the values for payout which is what bet amount should be doing.
     * @return
     */


    /**
     * The determining factor of how much money will be gotten from different bet types that are given.
     * @param playerBet
     * @return
     */
    @Override
    public void cashOut(int playerBet, Player currentPlayer) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
    }

    @Override
    public int jackPot() {
        return 0;
    }

    @Override
    public void play(Player currentPlayer) {
        int selection = UI.gamePrompt();
        switch (selection){
            case 1:
                betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount(),currentPlayer);
                betType = UI.displayRouletteBetTypes();
                spin();
                cashOut(betType,currentPlayer);
                UI.displayRouletteWin(true,betAmount);
                break;
            case 2:
                break;
            case 3:
                break;

        }

    }

    @Override
    public int whatAreTheOdds() {
        return 0;
    }

}
