package org.example.Controller;

import org.example.Model.Horse;

public class HorseRace implements Casino, Probability{


    @Override
    public int betAmount() {
        return 0;
    }

    @Override
    public int cashOut() {
        return 0;
    }

    @Override
    public int jackPot() {
        return 0;
    }

    public void horseStable(){
        Horse newHorse = new Horse("Lucky", "Brown");
        System.out.println(newHorse);
    }


}
