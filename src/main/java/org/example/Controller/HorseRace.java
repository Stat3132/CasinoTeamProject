package org.example.Controller;

import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.UI;

public class HorseRace implements Casino {
    //FIXME:
    private final int winnerHorse = 6;
    private final int maxHorseWeight = 1150, mediumHorseWeight = 1000, lightHorseWeight = 900;
    Horse[] fullStableOfRacerHorses = new Horse[20], finalsLineUp = new Horse[7];
    UI UI = new UI();
    ProbabilityForValue probable = new ProbabilityForValue();

    // Play method is the logic behind horse racing
    @Override
    public Player play(Player currentPlayer, int betAmount) {
        betAmount = betAmount * 2;
        //populates 20 horses.
        populatingStable();
        //Narrows an array of 20 horses down to 7 horses. (These are the horses the user will bet on)
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
        // The array of 7 horses are already given odd factors, here the horses are put from the least odds to the highest odds.
        int incrementingHorseArray = 0;
        int incrementingNumberCounter = 1;
        for (int i = 0; i < 7; i++) {
            if (pickedRacingHorses[i].getHorseOdds() == incrementingNumberCounter) {
                finalsLineUp[incrementingHorseArray] = pickedRacingHorses[i];
                incrementingHorseArray++;
                if (incrementingNumberCounter == 100) {
                    continue;
                }
                incrementingNumberCounter++;
                i = -1;
            }
            if (i == 6) {
                incrementingNumberCounter++;
                i = -1;
            }
            if (incrementingHorseArray == 7) {
                break;
            }
        }
        // Switch statement determines the winner and either gives the user money or doesn't.
        finalsLineUp = probable.oddsOfOdds(finalsLineUp);
        switch (UI.bettingOnHorse(pickedRacingHorses)) {
            //FIXME: UI
            case 1:
                UI.displayingHorseBettedOn(pickedRacingHorses, 0);
                if (pickedRacingHorses[0] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
            case 2:
                UI.displayingHorseBettedOn(pickedRacingHorses, 1);
                if (pickedRacingHorses[1] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
            case 3:
                UI.displayingHorseBettedOn(pickedRacingHorses, 2);
                if (pickedRacingHorses[2] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
            case 4:
                UI.displayingHorseBettedOn(pickedRacingHorses, 3);
                if (pickedRacingHorses[3] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);

                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
            case 5:
                UI.displayingHorseBettedOn(pickedRacingHorses, 4);
                if (pickedRacingHorses[4] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
            case 6:
                UI.displayingHorseBettedOn(pickedRacingHorses, 5);
                if (pickedRacingHorses[5] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
            case 7:
                UI.displayingHorseBettedOn(pickedRacingHorses, 6);
                if (pickedRacingHorses[6] == finalsLineUp[winnerHorse]) {
                    UI.displayingWinner(finalsLineUp, true, betAmount);
                    cashOut(currentPlayer, betAmount);
                } else {
                    UI.displayingWinner(finalsLineUp, false, betAmount / 2);
                }
                break;
        }
        //Last prompt just giving the information of the game and what money the user ended off with.
        UI.displayFinalMoney(currentPlayer,1);
        return null;
    }

    // Current players money is incremented based on how much they bet.
    public void cashOut(Player currentPlayer, int playerBet) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalHorseMoney(currentPlayer.getTotalHorseMoney() + playerBet);
    }


    //An array of 20 horses is created.
    public void populatingStable() {
        for (int i = 0; i < fullStableOfRacerHorses.length; i++) {
            fullStableOfRacerHorses[i] = new Horse();
        }
        inputtingOddsIntoHorseStable();
    }

    //This adds an odd factor to every horse based on speed and weight.
    //TODO: NEED TO FIND A WAY TO MAKE ODDS COMPLETELY DIFFERENT. SIMILAR ODDS RUINS!!! THE ARRAY!!!!
    public void inputtingOddsIntoHorseStable() {
        ProbabilityForValue.creatingArrayOfOdds(100);
        int stableCounter = 0;
        for (int horseSpeedCounter = 1; horseSpeedCounter < 11; horseSpeedCounter++) {
            if (fullStableOfRacerHorses[stableCounter].getHorseSpeed() == horseSpeedCounter) {
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= maxHorseWeight) {
                    int horseOddsFactor = probable.randomOddValue();
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                }

                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[stableCounter].getHorseWeight() < maxHorseWeight) {
                    int horseOddsFactor = probable.randomOddValue();
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                }

                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[stableCounter].getHorseWeight() < mediumHorseWeight) {
                    int horseOddsFactor = probable.randomOddValue();
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);

                }
                horseSpeedCounter = -1;
                stableCounter++;
                if (stableCounter == 20) {
                    ProbabilityForValue.arrayListOfOdds.clear();
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
