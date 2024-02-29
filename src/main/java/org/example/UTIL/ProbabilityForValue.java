package org.example.UTIL;

import org.example.Model.Horse;

import java.util.ArrayList;
import java.util.Random;

public class ProbabilityForValue {
    public static ArrayList<Integer> arrayListOfOdds = new ArrayList<Integer>();
    int lastCheck;

    // RANDOM VALUE FROM MIN to MAX. Takes in parameters.
    public static int randomValues(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max + 1);
    }

    //Randomizing odds and if odds are used removing them from an array for no overlapping odds.
    public int randomOddValue() {
        int[] fullIntArray = new int[100];
        for (int i = 0; i < fullIntArray.length; i++) {
            fullIntArray[i] = i;
        }
        int horseOdd = 0;
        for (int i = 0; i < 100; i++) {
            int randomOdd = randomValues(1, fullIntArray.length - 1);
            if (fullIntArray[randomOdd] == 0) {
                continue;
            }
            if (fullIntArray[randomOdd] != 0) {
                fullIntArray[randomOdd] = 0;
                horseOdd = randomOdd;
                break;
            }
        }
        return horseOdd;
    }

    // Making sure odds contributes to the horses winning but still does not guarantee the win.
    public int makingMathematicalHorseOdds(Horse racerHorse) {
        if (racerHorse == null) {
            return 0;
        }
        if (racerHorse.getHorseOdds() <= 100 && racerHorse.getHorseOdds() >= 80) {
            return randomValues(0, 2);
        }
        if (racerHorse.getHorseOdds() <= 79 && racerHorse.getHorseOdds() >= 60) {
            return randomValues(0, 6);
        }
        if (racerHorse.getHorseOdds() <= 59 && racerHorse.getHorseOdds() >= 40) {
            return randomValues(0, 9);
        }
        if (racerHorse.getHorseOdds() <= 39 && racerHorse.getHorseOdds() >= 30) {
            return randomValues(0, 12);
        }
        if (racerHorse.getHorseOdds() <= 29 && racerHorse.getHorseOdds() >= 20) {
            return randomValues(0, 15);
        }
        if (racerHorse.getHorseOdds() <= 19 && racerHorse.getHorseOdds() >= 10) {
            return randomValues(0, 18);
        }
        if (racerHorse.getHorseOdds() <= 9 && racerHorse.getHorseOdds() >= 0) {
            return randomValues(0, 21);
        }
        return 0;
    }

    //Odds do not fully determine winner this method is the one that will determine the winner, with one more "random" check.

    /**
     * This method uses the horses odds and gives it one more random "spin" to truly randomize the odds.
     *
     * @param finalLineUp This is the lineup of horses that have already been ordered from the least odds to the greatest odds
     * @return This returns a temporary array that now becomes the new positionings for the final horses.
     */
    public Horse[] finalOddCheck(Horse[] finalLineUp) {
        Horse[] tempPlacement = new Horse[7];
        int incrementingTempPlacement = 6;
        for (int i = 6; i > -2; i--) {
            if (i == -1) {
                i = 6;
            }
            lastCheck = makingMathematicalHorseOdds(finalLineUp[i]);
            if (tempPlacement[0] != null) {
                break;
            }
            if (lastCheck == 0) {
                if (finalLineUp[i] == null) {
                    if (i <= -1) {
                        i = 6;
                    }
                    continue;
                }
                tempPlacement[incrementingTempPlacement] = finalLineUp[i];
                finalLineUp[i] = null;
                incrementingTempPlacement--;
            } else {
                if (i <= -1) {
                    i = 6;
                }
                continue;
            }
        }
        return tempPlacement;
    }
}
