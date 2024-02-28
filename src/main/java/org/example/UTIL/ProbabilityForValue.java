package org.example.UTIL;

import org.example.Model.Horse;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class ProbabilityForValue {
    //FIXME
    public static ArrayList<Integer> arrayListOfOdds = new ArrayList<Integer>();
    int lastCheck;

    // RANDOM VALUE FROM MIN to MAX. Takes in parameters.
    public static int randomValues(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(min, max + 1);
    }

    //This is creating an array of odds. This is for giving objects or games specific odds but not letting those odds duplicate.
    public static void creatingArrayOfOdds(int max) {
        for (int i = 1; i < max; i++) {
            arrayListOfOdds.add(i);
        }
    }

    //Randomizing odds and if odds are used removing them from an array for no overlapping odds.
    public int randomOddValue() {
        Random rand = new Random();
        int randomOdd = rand.nextInt(0, arrayListOfOdds.size());
        if (arrayListOfOdds.get(randomOdd) == null) {
            randomOdd = rand.nextInt(0, arrayListOfOdds.size());
        }
        if (arrayListOfOdds.get(randomOdd) == null) {
            randomOdd = rand.nextInt(0, arrayListOfOdds.size());
        }
        arrayListOfOdds.remove(randomOdd);
        return randomOdd;
    }

    // Making sure odds contributes to the horses winning but still does not guarantee the win.
    public int makingMathematicalOdds(Horse racerHorse) {
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
    public Horse[] oddsOfOdds(Horse[] finalLineUp) {
        Horse[] tempPlacement = new Horse[7];
        int incrementingTempPlacement = 0;
        for (int i = 0; i < 8; i++) {
            if (i == 7) {
                i = 0;
            }
            switch (incrementingTempPlacement) {
                case 0:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
                case 1:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
                case 2:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
                case 3:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
                case 4:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
                case 5:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
                case 6:
                    lastCheck = makingMathematicalOdds(finalLineUp[i]);
            }
            if (tempPlacement[6] != null) {
                break;
            }
            if (lastCheck == 0) {
                if (finalLineUp[i] == null) {
                    if (i >= 6) {
                        i = -1;
                    }
                    continue;
                }
                tempPlacement[incrementingTempPlacement] = finalLineUp[i];
                finalLineUp[i] = null;
                incrementingTempPlacement++;
            } else {
                if (i >= 6) {
                    i = -1;
                }
                continue;

            }
        }
        return tempPlacement;
    }
}
