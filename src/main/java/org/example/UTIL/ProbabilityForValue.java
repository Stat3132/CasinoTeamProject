package org.example.UTIL;

import java.util.ArrayList;
import java.util.Random;

public class ProbabilityForValue {
    //FIXME
    public static ArrayList<Integer> arrayListOfOdds = new ArrayList<Integer>();
    // RANDOM VALUE FROM MIN to MAX. Takes in parameters.
    public static int randomValues(int min, int max){
        Random rand = new Random();
        return rand.nextInt(min, max + 1);
    }
    //This is creating an array of odds. This is for giving objects or games specific odds but not letting those odds duplicate.
    public static void creatingArrayOfOdds(int max){
        for (int i = 1; i < max; i++) {
            arrayListOfOdds.add(i);
        }
    }
    public int randomOddValue(){
        Random rand = new Random();
        int randomOdd = rand.nextInt(0, arrayListOfOdds.size());
        if (arrayListOfOdds.get(randomOdd) == null){
            randomOdd = rand.nextInt(0, arrayListOfOdds.size());
        }
        if (arrayListOfOdds.get(randomOdd) == null){
            randomOdd = rand.nextInt(0, arrayListOfOdds.size());
        }
        arrayListOfOdds.remove(randomOdd);
        return randomOdd;
    }

    //Odds do not fully determine winner this method is the one that will determine the winner, with one more "random" check.
    //TODO: Find a way to loop this switch until a horse has a placement and then removing that placement from the switch statement so that only the remaining slots can be chosen.
    //TODO: PSEUDO CODE NO FULL IMPLEMENTATION
    //FIXME:\
    public int oddsOfOdds(int highestAfterOddFactor) {
        switch (highestAfterOddFactor) {
            case 1:
                if (highestAfterOddFactor == 1){

                }
                return randomValues(0, 2);
            case 2:
                return randomValues(0, 4);
            case 3:
                return randomValues(0, 7);
            case 4:
                return randomValues(0, 13);
            case 5:
                return randomValues(0, 23);
            case 6:
                return randomValues(0,28);
            case 7:
                if(randomValues(0, 1) == 1){
                    return 3;
                }

        }
        //FIXME: TEMPORARY VALUE
        return 1;
    }
}
