package org.example.Controller;

import java.util.HashMap;
import java.util.Random;

public class Roulette implements Casino {
    //FIXME
    private Random rouletteRandom;
    private HashMap<Integer, Integer> payouts;

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
    @Override
    public int betAmount() {
        return 0;
    }

    /**
     * The determining factor of how much money will be gotten from different bet types that are given.
     * @param betType
     * @param playerBet
     * @return
     */
    @Override
    public int cashOut(int betType,int playerBet) {
        int outcome = spin();
        int payoutMult = payouts.getOrDefault(betType, 0);
        int payout = playerBet * payoutMult;

        if (betType == 1 && outcome != 0 && outcome % 2 == 1 || betType == 2 && outcome != 0 && outcome % 2 == 1) {

            payout = playerBet * payouts.get(1);
        } else if (betType == 3 && outcome == betType) {

            payout = playerBet * payouts.get(3);
        } else if (betType == 4 && outcome == betType) {

            payout = playerBet * payouts.get(4);
            return payout;
        }
        return payout;
    }

    @Override
    public int jackPot() {
        return 0;
    }
    @Override
    public void play() {

    }

    @Override
    public int whatAreTheOdds() {
        return 0;
    }

}
