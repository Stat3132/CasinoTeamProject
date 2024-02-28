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

    //Odds do not fully determine winner this method is the one that will determine the winner, with one more "random" check.
    //TODO: Find a way to loop this switch until a horse has a placement and then removing that placement from the switch statement so that only the remaining slots can be chosen.
    //TODO: PSEUDO CODE NO FULL IMPLEMENTATION
    //FIXME:\
    public Horse[] oddsOfOdds(Horse[] finalLineUp) {
        Horse[] tempPlacement = new Horse[7];
        int incrementingTempPlacement = 0;
        for (int i = 0; i < 8; i++) {
            if (i == 7){
                i = 0;
            }
            switch (incrementingTempPlacement) {
                case 0:
                    lastCheck = randomValues(0, 7);
                case 1:
                    lastCheck = randomValues(0, 6);
                case 2:
                    lastCheck = randomValues(0, 5);
                case 3:
                    lastCheck = randomValues(0, 4);
                case 4:
                    lastCheck = randomValues(0, 3);
                case 5:
                    lastCheck = randomValues(0, 2);
                case 6:
                    lastCheck = randomValues(0, 1);
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
