package org.example.Controller;

import org.example.Model.Horse;

public class HorseRace implements Casino {
    //TODO:
    @Override
    public int betAmount() {
        return 0;
    }

    @Override
    public int cashOut(int betType, int playerBet) {
        return 0;
    }
    @Override
    public int jackPot() {
        return 0;
    }

    public void populatingStable(){
        Horse[] racingHorses = new Horse[20];
        for (int i = 0; i < racingHorses.length; i++) {
                if (i < racingHorses.length - 1){
                    racingHorses[i] = new Horse();
                    System.out.println(racingHorses[i].toString());
                }
                else {
                    return;
                }

        }

    }
    @Override
    public void play() {

    }
}
