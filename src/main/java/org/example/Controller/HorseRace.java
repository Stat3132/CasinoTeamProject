package org.example.Controller;

import org.example.Model.Horse;
import org.example.UTIL.Name;

public class HorseRace implements Casino, Probability{


    public void randomizingHorses(){
        Name[] baseNames = new Name[26];
        Name[] numbericalValueOfName = Name.values();
        for (int i = 0; i < baseNames.length; i++) {
            if (i != baseNames.length - 1){
                baseNames[i] = numbericalValueOfName[i];
                System.out.println(baseNames[i]);
            } else {
                return;
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
        Horse newHorse = new Horse( "Brown");
        System.out.println(newHorse);
    }


}
