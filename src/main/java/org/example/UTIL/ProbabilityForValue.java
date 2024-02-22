package org.example.UTIL;

import java.util.Random;

public class ProbabilityForValue {
    // RANDOM VALUE FROM MIN to MAX. Takes in parameters.
    public static int randomValues(int min, int max){
        Random rand = new Random();
        return rand.nextInt(min, max + 1);
    }

    /**
     * This method is going to be used for horse racing and maybe other games. The main focus for this method is for horse racing.
     * After considering speed, weight, and the ODDS of the horse winning, we have to remember that ODDS do not determine the victor.
     * A horse with a high odds has a high chance of winning, but that does not guarantee they are winning. This method is for this scenario.
     * For case 1 a value will be returned after the game has finished that will determine the victor of the game. Here if it is a horse in first
     * place it still has one more spin it has to do to determine it is the victor. For instance for the first one it would be that from 0 to 2 if they get 1 or 2 they win.
     * In that slim chance of them not getting a 0 or 1 and getting a 2, they are not declared the winner. Instead, they are moved down to the next spin.
     * This way there are 7 switch statements. If it does not win the first one it is moved down until it wins one of the spins with 7th place being the easiest to win
     * as there should be a guarenteed 7th place. If by chance none of the switch statements are played, there will have to be logic that
     * reputs the horse back into the list until it has a place among the 7 without any of the horses "sharing an index.
     * @param highestAfterOddFactor
     * @return
     */

    //TODO: Find a way to loop this switch until a horse has a placement and then removing that placement from the switch statement so that only the remaining slots can be chosen.
    //TODO: SUDO CODE NO FULL IMPLEMENTATION
    //FIXME:
    public int oddsOfOdds(int highestAfterOddFactor) {
        switch (highestAfterOddFactor) {
            case 1:
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
