package org.example.Controller;

import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.CasinoInterface;

public class HorseRace implements Casino {

    Horse[] fullStableOfRacerHorses = new Horse[20];
    Horse[] finalsLineUp = new Horse[7];
    CasinoInterface UI = new CasinoInterface();
    ProbabilityForValue horseProbabilityFactor = new ProbabilityForValue();

    int betAmount;
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
        switch (UI.horseBetAmount()) {
            case 1:
                 currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() - 100);
                 betAmount = 100;
                break;
            case 2:
                currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() - 200);
                betAmount = 200;
                break;
            case 3:
                currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() - 300);
                betAmount = 300;
                break;
        }
        return currentPlayer.getCurrentMoneyCount();
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
                playerBet = 200;
                break;
            case 3:
                playerBet = 300;
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
        //TODO://////////////////////////////////////SETTING BET////////////////////////////////////////////////////////
        //betAmount(currentPlayer);
         betAmount = UI.getUserBet(currentPlayer.getCurrentMoneyCount());
        //TODO://////////////////////////////////////HORSE POPULATING///////////////////////////////////////////////////
        //This populates an array of 20 with horses. These are all the horses that will be displayed to the user that will then be narrowed down to 7 then put in order from 7th place to first.
        populatingStable();
        // pickingRaceHorses are the final 7 horses being picked randomly out of the stable of 20 horses. No check for repeating horses
        //FIXME: Temporary fix still trying to find a more solidified solution
        Horse[] pickedRacingHorses = new Horse[7];
        for (int i = 0; i < pickedRacingHorses.length; i++) {
            int tempValue = horseProbabilityFactor.randomValues(0, 19);
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
            if (incrementingNumberCounter == 30) {
                break;
            }
        }
        //TODO://///////////////////////////////////////////////////////////////////////////////////////////////////////
        //Selection of 7 horses has been picked, now ordering them by the odds of them winning. Lowest odds to the highest odds
        // of the horses winning.
        //TODO:||||||||||||||||||||||||||USER INTERACTION|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
        switch (UI.bettingOnHorse(pickedRacingHorses)){
            //FIXME: UI
            case 1:
                UI.displayingHorseBettedOn(pickedRacingHorses, 0);
                if (pickedRacingHorses[0] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, 1);
                }
                break;
            case 2:
                UI.displayingHorseBettedOn(pickedRacingHorses, 1);
                if (pickedRacingHorses[1] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, betAmount * 2);
                }
                break;
            case 3:
                UI.displayingHorseBettedOn(pickedRacingHorses, 2);
                if (pickedRacingHorses[2] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, betAmount * 2);
                }
                break;
            case 4:
                UI.displayingHorseBettedOn(pickedRacingHorses, 3);
                if (pickedRacingHorses[3] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, betAmount * 2);
                }
                break;
            case 5:
                UI.displayingHorseBettedOn(pickedRacingHorses, 4);
                if (pickedRacingHorses[4] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, betAmount * 2);
                }
                break;
            case 6:
                UI.displayingHorseBettedOn(pickedRacingHorses, 5);
                if (pickedRacingHorses[5] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, betAmount * 2);
                }
                break;
            case 7:
                UI.displayingHorseBettedOn(pickedRacingHorses, 6);
                if (pickedRacingHorses[6] == finalsLineUp[6]){
                    UI.displayingWinner(finalsLineUp,true, betAmount * 2);
                }
                break;
        }
        UI.displayingWinner(finalsLineUp,false, betAmount);
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
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1110) {
                        fullStableOfRacerHorses[i].setHorseOdds(4);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1010 && fullStableOfRacerHorses[i].getHorseWeight() < 1110) {
                        fullStableOfRacerHorses[i].setHorseOdds(5);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 910 && fullStableOfRacerHorses[i].getHorseWeight() < 1010) {
                        fullStableOfRacerHorses[i].setHorseOdds(6);
                    }
                    break;
                case 3:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1120) {
                        fullStableOfRacerHorses[i].setHorseOdds(7);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1020 && fullStableOfRacerHorses[i].getHorseWeight() < 1120) {
                        fullStableOfRacerHorses[i].setHorseOdds(8);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 920 && fullStableOfRacerHorses[i].getHorseWeight() < 1020) {
                        fullStableOfRacerHorses[i].setHorseOdds(9);
                    }
                    break;
                case 4:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1130) {
                        fullStableOfRacerHorses[i].setHorseOdds(10);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1030 && fullStableOfRacerHorses[i].getHorseWeight() < 1130) {
                        fullStableOfRacerHorses[i].setHorseOdds(11);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 930 && fullStableOfRacerHorses[i].getHorseWeight() < 1030) {
                        fullStableOfRacerHorses[i].setHorseOdds(12);
                    }
                    break;
                case 5:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1140) {
                        fullStableOfRacerHorses[i].setHorseOdds(13);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1040 && fullStableOfRacerHorses[i].getHorseWeight() < 1140) {
                        fullStableOfRacerHorses[i].setHorseOdds(14);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 940 && fullStableOfRacerHorses[i].getHorseWeight() < 1040) {
                        fullStableOfRacerHorses[i].setHorseOdds(15);
                    }
                    break;
                case 6:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(16);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1050 && fullStableOfRacerHorses[i].getHorseWeight() < 1150) {
                        fullStableOfRacerHorses[i].setHorseOdds(17);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 950 && fullStableOfRacerHorses[i].getHorseWeight() < 1050) {
                        fullStableOfRacerHorses[i].setHorseOdds(18);
                    }
                    break;
                case 7:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1160) {
                        fullStableOfRacerHorses[i].setHorseOdds(19);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1060 && fullStableOfRacerHorses[i].getHorseWeight() < 1160) {
                        fullStableOfRacerHorses[i].setHorseOdds(20);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 960 && fullStableOfRacerHorses[i].getHorseWeight() < 1060) {
                        fullStableOfRacerHorses[i].setHorseOdds(21);
                    }
                    break;
                case 8:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1170) {
                        fullStableOfRacerHorses[i].setHorseOdds(22);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1070 && fullStableOfRacerHorses[i].getHorseWeight() < 1170) {
                        fullStableOfRacerHorses[i].setHorseOdds(23);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 970 && fullStableOfRacerHorses[i].getHorseWeight() < 1070) {
                        fullStableOfRacerHorses[i].setHorseOdds(24);
                    }
                    break;
                case 9:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1180) {
                        fullStableOfRacerHorses[i].setHorseOdds(25);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1080 && fullStableOfRacerHorses[i].getHorseWeight() < 1180) {
                        fullStableOfRacerHorses[i].setHorseOdds(26);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 980 && fullStableOfRacerHorses[i].getHorseWeight() < 1080) {
                        fullStableOfRacerHorses[i].setHorseOdds(27);
                    }
                    break;
                case 10:
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1190) {
                        fullStableOfRacerHorses[i].setHorseOdds(28);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 1090 && fullStableOfRacerHorses[i].getHorseWeight() < 1190) {
                        fullStableOfRacerHorses[i].setHorseOdds(29);
                    }
                    if (fullStableOfRacerHorses[i].getHorseWeight() >= 990 && fullStableOfRacerHorses[i].getHorseWeight() < 1090) {
                        fullStableOfRacerHorses[i].setHorseOdds(30);
                    }
                    break;
            }
        }
    }
}
