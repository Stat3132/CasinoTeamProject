package org.example.Controller;

import org.example.Model.Horse;

public class HorseRace implements Casino {
    Horse[] racingHorses = new Horse[20];
    //TODO:

    /**
     * For horse racing it will be base $100 entry, with x2 and x3 multipliers.
     * @return
     */
    @Override
    public int betAmount() {
        return 0;
    }

    /**
     * Cash out is how much money you will get back from it. There is no jack pot for horse racing.So for cash out I think I will keep it simple,
     * It will just be x 2 for the horse that won. There can be more complexity but this is just a rough draft of what these classes ARE.
     * @param betType
     * @param playerBet
     * @return
     */
    @Override
    public int cashOut(int betType, int playerBet) {
        return 0;
    }

    /**
     * NO JACKPOT CONSIDERING ALTERNATIVES!!!
     * @return
     */
    @Override
    public int jackPot() {
        return 0;
    }

    /**
     * Populating a stable. Starting at 20 horses then maybe in future implementation maybe the ability to add horses.
     * We begin with 20 horses displayed
     */
    public void populatingStable(){
        for (int i = 0; i < racingHorses.length; i++) {
                    racingHorses[i] = new Horse();
                    System.out.println(racingHorses[i].toString());
        }
    }

    /**
     * This is the play method that will where bet amount, cash and populatedStable will all be in the play method. Instilling LOGIC as this is a controller
     * an instiller of logic.
     */
    @Override
    public void play() {

    }
}
