package org.example.Controller;

import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;

public class HorseRace implements Casino {

    Horse[] fullStableOfRacerHorses = new Horse[20];
    Horse[] pickedRacingHorses = new Horse[7];
    Horse[] finalsLineUp = new Horse[7];
    int incrementingHorseArray = 0;
    int incrementingNumberCounter = 1;
    ProbabilityForValue probable = new ProbabilityForValue();
    boolean gameWon;
    //TODO:

    /**
     * For horse racing it will be base $100 entry, with x2 and x3 multipliers.
     *
     * @return
     */
    @Override
    public int betAmount(Player currentPlayer) {
        //TODO: SUDO CODE
        //FIXME: CODE IS NOT DONE!!!
        int playerBet = currentPlayer.getCurrentMoneyCount();
        switch (playerBet) {
            case 100:
                playerBet -= 100;
                break;
            case 200:
                playerBet -= 200;
                break;
            case 300:
                playerBet -= 300;
                break;
        }
        return playerBet;
    }

    /**
     * Cash out is how much money you will get back from it. There is no jack pot for horse racing.So for cash out I think I will keep it simple,
     * It will just be x 2 for the horse that won. There can be more complexity but this is just a rough draft of what these classes ARE.
     *
     * @param betType
     * @param playerBet
     * @return
     */

    //Instead of taking in a player bet it should take in a Person, so it can add the money after the player bet is done.
    @Override
    public int cashOut(int betType, int playerBet) {
        switch (betType) {
            case 1:
                playerBet = 100;

                break;
            case 2:
                break;
            case 3:
                break;
        }
        return playerBet;
    }

    /**
     * NO JACKPOT CONSIDERING ALTERNATIVES!!!
     *
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
    public void populatingStable() {
        for (int i = 0; i < fullStableOfRacerHorses.length; i++) {
            fullStableOfRacerHorses[i] = new Horse();
        }
        inputtingOddsIntoHorseStable();
    }

    /**
     * This is the play method that will be where bet amount, cash and populatedStable will all be in the play method. Instilling LOGIC as this is a controller,
     * an instiller of logic.
     */
    @Override
    public void play(Player currentPlayer) {
        //Something horse racing related
        //TODO:CHECKS!!!
        populatingStable();
        // pickingRaceHorses are the final 7 horses being picked randomly out of the stable of 20 horses. No check for repeating horses
        //FIXME: ADD LOGIC TO PREVENT DUPLICATE HORSES BEING ADDED TO ARRAY
        for (int i = 0; i < pickedRacingHorses.length; i++) {
            pickedRacingHorses[i] = fullStableOfRacerHorses[probable.randomValues(0, 19)];
        }
        for (int i = 0; i < 7; i++) {
            if (pickedRacingHorses[i].getHorseOdds() == incrementingNumberCounter) {
                finalsLineUp[incrementingHorseArray] = pickedRacingHorses[i];
                incrementingHorseArray++;
                incrementingNumberCounter++;
                i = -1;
                continue;
            }
            if (i == 6){
                incrementingNumberCounter++;
                i = -1;
            }
            if (incrementingNumberCounter == 30){
                i = 7;
            }
        }
    }

    /**
     * This method is supposed to be used to have a variable called "horse odds". I want to assign this value to a horse as like a
     *
     * @return
     */
    @Override
    public int whatAreTheOdds() {

        return 0;
    }

    /**
     * This method looks at an array of horses and sets odds to them based on speed and weight. This will make it easier to determine a winner because it simply means "higher number more likely to win"
     * There are 3 Ifs for every switch for every branch of weight it can have, so that determining factors for winning is based on speed and weight.
     */
    public void inputtingOddsIntoHorseStable() {
        for (int i = 0; i < 20; i++) {
            switch (fullStableOfRacerHorses[i].getHorseSpeed()) {
                case 1:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(1);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(2);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(3);
                    }
                    break;
                case 2:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(4);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(5);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(6);
                    }
                    break;
                case 3:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(7);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(8);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(9);
                    }
                    break;
                case 4:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(10);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(11);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(12);
                    }
                    break;
                case 5:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(13);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(14);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(15);
                    }
                    break;
                case 6:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(16);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(17);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(18);
                    }
                    break;
                case 7:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(19);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(20);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(21);
                    }
                    break;
                case 8:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(22);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(23);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(24);
                    }
                    break;
                case 9:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(25);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(26);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(27);
                    }
                    break;
                case 10:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(28);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1100) {
                        fullStableOfRacerHorses[i].setHorseOdds(29);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(30);
                    }
                    break;
            }
        }
    }
}
