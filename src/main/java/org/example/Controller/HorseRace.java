package org.example.Controller;

import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.CasinoInterface;

public class HorseRace implements Casino {

    private final int winnerHorse = 6;
    Horse[] fullStableOfRacerHorses = new Horse[20];
    Horse[] finalsLineUp = new Horse[7];
    CasinoInterface UI = new CasinoInterface();

    int betAmount;
    //TODO:

    /**
     * For horse racing it will be base $100 entry, with x2 and x3 multipliers.
     *
     * @return
     */
    //TODO: NOT CURRENTLY BEING USED!!!!

    /**
     * Cash out is how much money you will get back from it. There is no jack pot for horse racing.So for cash out I think I will keep it simple,
     * It will just be x 2 for the horse that won. There can be more complexity but this is just a rough draft of what these classes ARE.
     *
     * @param playerBet
     * @return
     */

    //Instead of taking in a player bet it should take in a Person, so it can add the money after the player bet is done.
    @Override
    public void cashOut(int playerBet, Player currentPlayer) {
                currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
                currentPlayer.setTotalHorseMoney(currentPlayer.getTotalHorseMoney() + playerBet);
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
        //TODO://////////////////////////////////////SETTING BET////////////////////////////////////////////////////////
        //betAmount(currentPlayer);
        betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount(), currentPlayer);
        //TODO://////////////////////////////////////HORSE POPULATING///////////////////////////////////////////////////
        //This populates an array of 20 with horses. These are all the horses that will be displayed to the user that will then be narrowed down to 7 then put in order from 7th place to first.
        populatingStable();
        // pickingRaceHorses are the final 7 horses being picked randomly out of the stable of 20 horses. No check for repeating horses
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
        //TODO://///////////////////////////////////////////////////////////////////////////////////////////////////////
        //Selection of 7 horses has been picked, now ordering them by the odds of them winning. Lowest odds to the highest odds
        // of the horses winning.
        //TODO:||||||||||||||||||||||||||USER INTERACTION|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        switch (UI.bettingOnHorse(pickedRacingHorses)) {
            //FIXME: UI
            case 1:
                UI.displayingHorseBettedOn(pickedRacingHorses, 0);
                if (pickedRacingHorses[0] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, 1);
                    cashOut(betAmount, currentPlayer);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                }
                break;
            case 2:
                UI.displayingHorseBettedOn(pickedRacingHorses, 1);
                if (pickedRacingHorses[1] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount * 2);
                    cashOut(betAmount, currentPlayer);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                }
                break;
            case 3:
                UI.displayingHorseBettedOn(pickedRacingHorses, 2);
                if (pickedRacingHorses[2] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount * 2);
                    cashOut(betAmount, currentPlayer);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                }
                break;
            case 4:
                UI.displayingHorseBettedOn(pickedRacingHorses, 3);
                if (pickedRacingHorses[3] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount * 2);
                    cashOut(betAmount, currentPlayer);

                } else{
                        UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                    }
                    break;
                    case 5:
                        UI.displayingHorseBettedOn(pickedRacingHorses, 4);
                        if (pickedRacingHorses[4] == finalsLineUp[winnerHorse]) {
                            UI.displayingWinner(finalsLineUp, true, betAmount * 2);
                            cashOut(betAmount, currentPlayer);
                        } else {
                            UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                        }
                        break;
                    case 6:
                        UI.displayingHorseBettedOn(pickedRacingHorses, 5);
                        if (pickedRacingHorses[5] == finalsLineUp[winnerHorse]) {
                            UI.displayingWinner(finalsLineUp, true, betAmount * 2);
                            cashOut(betAmount, currentPlayer);
                        } else {
                            UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                        }
                        break;
                    case 7:
                        UI.displayingHorseBettedOn(pickedRacingHorses, 6);
                        if (pickedRacingHorses[6] == finalsLineUp[winnerHorse]) {
                            UI.displayingWinner(finalsLineUp, true, betAmount * 2);
                            cashOut(betAmount, currentPlayer);
                        } else {
                            UI.displayingWinner(finalsLineUp, false, betAmount * 2);
                        }
                        break;
                }


        UI.finalPromptInHorseRacing(currentPlayer);
        //TODO:|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
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
    //TODO: NEED TO FIND A WAY TO MAKE ODDS COMPLETELY DIFFERENT. SIMILAR ODDS RUINS!!! THE ARRAY!!!!
    public void inputtingOddsIntoHorseStable() {
//        int stableCounter = 0;
//        for (int horseSpeedCounter = 1; horseSpeedCounter < 11; horseSpeedCounter++) {
//            if (fullStableOfRacerHorses[stableCounter].getHorseSpeed() == horseSpeedCounter){
//                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= 1150) {
//                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseProbabilityFactor.randomValues(1,30));
//                }
//                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= 1000 && fullStableOfRacerHorses[stableCounter].getHorseWeight() < 1150) {
//                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseProbabilityFactor.randomValues(31,60));
//                }
//                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= 900 && fullStableOfRacerHorses[stableCounter].getHorseWeight() < 1000) {
//                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseProbabilityFactor.randomValues(61,90));
//                }
//                horseSpeedCounter = -1;
//                stableCounter++;
//                if (stableCounter == 19){
//                    return;
//                }
//            }

        for (int i = 0; i < 20; i++) {


            switch (fullStableOfRacerHorses[i].getHorseSpeed()) {
                case 1:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(1, 3));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(4, 6));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(7, 9));
                    }
                    break;
                case 2:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1020) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(10, 12));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1020) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(13, 16));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(17, 19));
                    }
                    break;
                case 3:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(20, 23));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(24, 26));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(27, 30));
                    }
                    break;
                case 4:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(30, 33));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(34, 36));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(37, 40));
                    }
                    break;
                case 5:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(40, 43));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(44, 46));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(47, 50));
                    }
                    break;
                case 6:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(51, 53));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(54, 56));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(57, 60));
                    }
                    break;
                case 7:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(61, 63));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(64, 66));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(67, 70));
                    }
                    break;
                case 8:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(71, 73));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(74, 76));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(77, 80));
                    }
                    break;
                case 9:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(81, 83));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(84, 86));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(87, 90));
                    }
                    break;
                case 10:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(91, 93));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1000 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(94, 96));
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 900 && fullStableOfRacerHorses[i].getHorseWeight() < 1000) {
                        fullStableOfRacerHorses[i].setHorseOdds(ProbabilityForValue.randomValues(97, 100));
                    }
                    break;
            }
        }
    }

}
