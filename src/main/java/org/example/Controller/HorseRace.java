package org.example.Controller;

import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.CasinoInterface;

public class HorseRace implements Casino {
    //FIXME:
    private final int winnerHorse = 6;
    private final int maxHorseWeight = 1150, mediumHorseWeight = 1000, lightHorseWeight = 900;
    Horse[] fullStableOfRacerHorses = new Horse[20], finalsLineUp = new Horse[7];
    CasinoInterface UI = new CasinoInterface();

    @Override
    public Player play(Player currentPlayer, int betAmount) {
        // This is the play method that will be where bet amount, cash and populatedStable will all be in the play method. Instilling LOGIC as this is a controller,  * an instiller of logic.
        betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount(), currentPlayer);
//        betAmount = betAmount * 2;

        //populates 20 horses and narrows down to 7 for the user to choose from.
        populatingStable();
        /**
         * Moves horses from an array called "fullStableOfRaceHorses" to a funneled down array called "pickedRacingHorses"
         * Which will be the 7 horses that a user can bet on. Randomizing it out of 20 initial horses
         */
        //FIXME: Temporary fix still trying to find a more solidified solution
        Horse[] pickedRacingHorses = new Horse[7];
        for (int i = 0; i < pickedRacingHorses.length; i++) {
            int tempValue = ProbabilityForValue.randomValues(0, 19);
            pickedRacingHorses[i] = fullStableOfRacerHorses[tempValue];
            fullStableOfRacerHorses[tempValue] = null;
            if (pickedRacingHorses[i] == null) {
                i--;
            }
        }
        /**
         * Here I go through an array of "pickedRacingHorses". These are the horses that have been funneled from the 20
         * total stable horses. These horses are then ordered from the least probable chance of winning to the highest.
         * This is the final line up before the horses are all given "horseOdds" and put in order from least to greatest
         * odds of winning.
         */
        int incrementingHorseArray = 0;
        int incrementingNumberCounter = 1;
        for (int i = 0; i < 7; i++) {
            if (pickedRacingHorses[i].getHorseOdds() == incrementingNumberCounter) {
                finalsLineUp[incrementingHorseArray] = pickedRacingHorses[i];
                incrementingHorseArray++;
                incrementingNumberCounter++;
                i = -1;
                continue;
            }
            if (i == 6) {
                incrementingNumberCounter++;
                i = -1;
            }
            if (incrementingNumberCounter == 100) {
                break;
            }
        }
        //Selection of 7 horses has been picked, now ordering them by the odds of them winning. Lowest odds to the highest odds
        // of the horses winning.
        /**
         * Here is where the winner is determined. The "finalsLineUp" array is horses from the least possible odds of a
         * horse winning to the most probable. So for this switch statement it first asks the user what horse they have bet on 1-7.
         * As it displays all the horses. Then it sees if the horse that the user has picked is correct and if it is
         * wrong it notifies the user and tells them how much money they lost. If they won it tells them how much they won and
         * how much money they have made in the horseRacing game in its entirety.
         *
         */
        switch (UI.bettingOnHorse(pickedRacingHorses)) {
            //FIXME: UI
            case 1:
                UI.displayingHorseBettedOn(pickedRacingHorses, 0);
                if (pickedRacingHorses[0] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
            case 2:
                UI.displayingHorseBettedOn(pickedRacingHorses, 1);
                if (pickedRacingHorses[1] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
            case 3:
                UI.displayingHorseBettedOn(pickedRacingHorses, 2);
                if (pickedRacingHorses[2] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
            case 4:
                UI.displayingHorseBettedOn(pickedRacingHorses, 3);
                if (pickedRacingHorses[3] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);

                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
            case 5:
                UI.displayingHorseBettedOn(pickedRacingHorses, 4);
                if (pickedRacingHorses[4] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
            case 6:
                UI.displayingHorseBettedOn(pickedRacingHorses, 5);
                if (pickedRacingHorses[5] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
            case 7:
                UI.displayingHorseBettedOn(pickedRacingHorses, 6);
                if (pickedRacingHorses[6] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount/2);
                }
                break;
        }
        /**
         * Last prompt just giving the information of the game and what money the user ended off with.
         */
        UI.finalPromptInHorseRacing(currentPlayer);
        //TODO:|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        return null;
    }
    public void cashOut(Player currentPlayer, int playerBet) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalHorseMoney(currentPlayer.getTotalHorseMoney() + playerBet);
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
     * This method looks at an array of horses and sets odds to them based on speed and weight. This will make it easier to determine a winner because it simply means "higher number more likely to win"
     * There are 3 Ifs for every switch for every branch of weight it can have, so that determining factors for winning is based on speed and weight.
     */
    //TODO: NEED TO FIND A WAY TO MAKE ODDS COMPLETELY DIFFERENT. SIMILAR ODDS RUINS!!! THE ARRAY!!!!
    public void inputtingOddsIntoHorseStable() {
        ProbabilityForValue.creatingArrayOfOdds(100);
        int stableCounter = 0;
        for (int horseSpeedCounter = 1; horseSpeedCounter < 11; horseSpeedCounter++) {
            if (fullStableOfRacerHorses[stableCounter].getHorseSpeed() == horseSpeedCounter) {
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= 1150) {
                    int horseOddsFactor = ProbabilityForValue.randomOddValue(0);
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                    ProbabilityForValue.removeFactor(horseOddsFactor);
                }
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= 1000 && fullStableOfRacerHorses[stableCounter].getHorseWeight() < 1150) {
                    int horseOddsFactor = ProbabilityForValue.randomOddValue(0);
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                    ProbabilityForValue.removeFactor(horseOddsFactor);
                }
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= 900 && fullStableOfRacerHorses[stableCounter].getHorseWeight() < 1000) {
                    int horseOddsFactor = ProbabilityForValue.randomOddValue(0);
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                    ProbabilityForValue.removeFactor(horseOddsFactor);
                }
                horseSpeedCounter = -1;
                stableCounter++;
                if (stableCounter == 19) {
                    return;
                }
            }
            //ProbabilityForValue.creatingArrayOfOdds(100);
//        for (int i = 0; i < 20; i++) {
//            switch (fullStableOfRacerHorses[i].getHorseSpeed()) {
//                case 1:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                         int horseOddsFactor = ProbabilityForValue.randomOddValue(1,3);
//                        fullStableOfRacerHorses[i].setHorseOdds(horseOddsFactor);
//                        ProbabilityForValue.removeFactor(horseOddsFactor);
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(4,6);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(7,9);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    break;
//                case 2:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(10,12);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(13,16);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(17,19);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    break;
//                case 3:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(20,23);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        int factor = ProbabilityForValue.randomOddValue(20,23);
//                        fullStableOfRacerHorses[i].setHorseOdds(factor);
//                        ProbabilityForValue.removeFactor(factor);
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(27, 30));
//                    }
//                    break;
//                case 4:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(30, 33));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(34, 36));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(37, 40));
//                    }
//                    break;
//                case 5:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(40, 43));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(44, 46));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(47, 50));
//                    }
//                    break;
//                case 6:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(51, 53));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(54, 56));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(57, 60));
//                    }
//                    break;
//                case 7:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(61, 63));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(64, 66));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(67, 70));
//                    }
//                    break;
//                case 8:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(71, 73));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(74, 76));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(77, 80));
//                    }
//                    break;
//                case 9:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(81, 83));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(84, 86));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(87, 90));
//                    }
//                    break;
//                case 10:
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(91, 93));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < maxHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(94, 96));
//                    }
//                    if (fullStableOfRacerHorses[i].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[i].getHorseWeight() < mediumHorseWeight) {
//                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(97, 100));
//                    }
//                    break;
//            }
//        }
        }
    }
}
