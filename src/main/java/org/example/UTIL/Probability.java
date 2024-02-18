package org.example.UTIL;

import java.util.Random;

public class Probability {
    // RANDOM VALUE FROM MIN to MAX. Takes in parameters.
    public int randomValues(int min, int max){
        Random rand = new Random();
        return rand.nextInt(min, max + 1);
    }
}
