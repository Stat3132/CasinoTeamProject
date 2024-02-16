package org.example.Controller;

import org.example.Model.Horse;

import javax.naming.Name;

public class HorseRace implements Casino, Probability{


    public void randomizingHorses(){
        Name[] allNames = new Name[24];
        for (int i = 0; i < allNames.length; i++) {
            if (allNames != null){
                allNames[i] = org.example.UTIL.Name._0;
            }

        }
    }

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
