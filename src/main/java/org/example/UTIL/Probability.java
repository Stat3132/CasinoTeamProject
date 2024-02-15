package org.example.UTIL;

import java.util.Random;

public class Probability {

    public int randomValues(int min, int max){
        Random rand = new Random();
        int returnedInt = rand.nextInt(min, max + 1);
        return returnedInt;
    }

    public void randomizingArray(){


    }
    int[][] newArray = new int[6][7];
}
