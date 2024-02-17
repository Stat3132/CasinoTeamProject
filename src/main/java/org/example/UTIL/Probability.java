package org.example.UTIL;

import java.util.Random;

public class Probability {

    public int randomValues(int min, int max){
        Random rand = new Random();
        return rand.nextInt(min, max + 1);
    }
}
