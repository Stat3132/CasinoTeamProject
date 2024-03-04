package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.UI;

public class HorseRace implements Casino {

    //region VARS
    private static final int winnerHorse = 6;
    Horse[] fullStableOfRacerHorses = new Horse[20], finalsLineUp = new Horse[7];
    UI UI = new UI();
    //Odds that are given to horses
    ProbabilityForValue probabilityForValue = new ProbabilityForValue();
    //endregion

    //region HORSE STABLE LOGIC
    //An array of 20 horses is created.
    public void populatingStable() {
        for (int i = 0; i < fullStableOfRacerHorses.length; i++) {
            fullStableOfRacerHorses[i] = new Horse();
        }
        inputtingOddsIntoHorseStable();
    }
    //end region
    //This adds an odd factor to every horse.
    public void inputtingOddsIntoHorseStable() {
        int stableCounter = 0;
        probabilityForValue.creatingOddArray();
        for (int horseSpeedCounter = 1; horseSpeedCounter < 11; horseSpeedCounter++) {
            if (fullStableOfRacerHorses[stableCounter].getHorseSpeed() == horseSpeedCounter) {
                int horseOddsFactor = probabilityForValue.randomOddValue();
                fullStableOfRacerHorses[stableCounter].setHorseOdds(horseOddsFactor);
                horseSpeedCounter = -1;
                stableCounter++;
                if (stableCounter == 20) {
                    return;
                }
            }
        }
    }
    //endregion

    //region CASINO INTERFACE IMPLEMENTED METHODS
    // Play method is how the users play the game.
    @Override
    public CasinoMembers play(CasinoMembers player, int betAmount, boolean isAI) {
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
                        player = cashOut(player, betAmount);
                    } else {
                        UI.displayHorseWinner(finalsLineUp, false, betAmount / 2);
                        player = cashOut(player, -betAmount / 2);
                    }
                    break;
                }
            }
        } else {
            playersChoice = 6;
            for (int i = 0; i < 8; i++) {
                if (playersChoice == i) {
                    if (pickedRacingHorses[i] == finalsLineUp[winnerHorse]) {
                        player = cashOut(player, betAmount);
                    } else {
                        player = cashOut(player, -betAmount / 2);
                    }
                    break;
                }
            }
        }
        return player;
    }
    // Current players money is incremented based on how much they bet.
    @Override
    public CasinoMembers cashOut(CasinoMembers player, int playerBet) {
        player.setCurrentMoneyCount(player.getCurrentMoneyCount() + playerBet);
        player.setTotalHorseMoney(player.getTotalHorseMoney() + playerBet);
        return player;
    }

    //endregion
}
