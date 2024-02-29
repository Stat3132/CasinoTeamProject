package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.UI;

public class HorseRace implements Casino {
    private final int winnerHorse = 6;
    private final int maxHorseWeight = 1150, mediumHorseWeight = 1000, lightHorseWeight = 900;
    Horse[] fullStableOfRacerHorses = new Horse[20], finalsLineUp = new Horse[7];
    UI UI = new UI();
    ProbabilityForValue probabilityForValue = new ProbabilityForValue();

    //An array of 20 horses is created.
    public void populatingStable() {
        for (int i = 0; i < fullStableOfRacerHorses.length; i++) {
            fullStableOfRacerHorses[i] = new Horse();
        }
        inputtingOddsIntoHorseStable();
    }
    // Play method is how the users play the game.
    @Override
    public CasinoMembers play(CasinoMembers currentPlayer, int betAmount, boolean isAI) {
        betAmount = betAmount * 2;
        //populates 20 horses.
        populatingStable();
        //Narrows an array of 20 horses down to 7 horses. (These are the horses the user will bet on)
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
            assert pickedRacingHorses[i] != null;
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
        //Last "random roll" to determine final placements for racer horses.
        finalsLineUp = probabilityForValue.finalOddCheck(finalsLineUp);
        // Switch statement determines the winner and either gives the user money or doesn't.
        int playersChoice;
        if (!isAI) {
            playersChoice = UI.bettingHorse(pickedRacingHorses) - 1;
            for (int i = 0; i < 8; i++) {
                if (playersChoice == i) {
                    UI.displayBettedHorse(pickedRacingHorses, i);
                    if (pickedRacingHorses[i] == finalsLineUp[winnerHorse]) {
                        UI.displayHorseWinner(finalsLineUp, true, betAmount);
                        currentPlayer = cashOut(currentPlayer, betAmount);
                        break;
                    } else {
                        UI.displayHorseWinner(finalsLineUp, false, betAmount / 2);
                        currentPlayer = cashOut(currentPlayer, -betAmount / 2);
                        break;
                    }
                }
            }
        } else {
            playersChoice = ProbabilityForValue.randomValues(0,6);
            for (int i = 0; i < 8; i++) {
                if (playersChoice == i) {
                    if (pickedRacingHorses[i] == finalsLineUp[winnerHorse]) {
                        currentPlayer = cashOut(currentPlayer, betAmount);
                        break;
                    } else {
                        currentPlayer = cashOut(currentPlayer, -betAmount / 2);
                        break;
                    }
                }
            }
        }
        return currentPlayer;
    }
    // Current players money is incremented based on how much they bet.
    @Override
    public CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet) {
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() + playerBet);
        currentPlayer.setTotalHorseMoney(currentPlayer.getTotalHorseMoney() + playerBet);
        return currentPlayer;
    }

    //This adds an odd factor to every horse based on speed and weight.
    public void inputtingOddsIntoHorseStable() {
        int stableCounter = 0;
        probabilityForValue.creatingOddArray();
        for (int horseSpeedCounter = 1; horseSpeedCounter < 11; horseSpeedCounter++) {
            if (fullStableOfRacerHorses[stableCounter].getHorseSpeed() == horseSpeedCounter) {
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= maxHorseWeight) {
                    int horseOddsFactor = probabilityForValue.randomOddValue();
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                }
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= mediumHorseWeight && fullStableOfRacerHorses[stableCounter].getHorseWeight() < maxHorseWeight) {
                    int horseOddsFactor = probabilityForValue.randomOddValue();
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                }
                if (fullStableOfRacerHorses[stableCounter].getHorseWeight() >= lightHorseWeight && fullStableOfRacerHorses[stableCounter].getHorseWeight() < mediumHorseWeight) {
                    int horseOddsFactor = probabilityForValue.randomOddValue();
                    fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                }
                horseSpeedCounter = -1;
                stableCounter++;
                if (stableCounter == 20) {
                    return;
                }
            }
        }
    }
}
