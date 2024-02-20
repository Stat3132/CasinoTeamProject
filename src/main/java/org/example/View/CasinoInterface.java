package org.example.View;

import org.example.Model.Horse;
import org.example.UTIL.Console;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CasinoInterface {
    Console IO = new Console();

    public String firstUserPrompt(){
        //user prompt to create a user
        return IO.getStringInput("Create Your First user! \nEnter a username: ",true);
    }
    public int casinoMenu(){
        //casino menu prompt for game choosing
        Console.write("\n-- Variables in Vegas! --\n", Console.TextColor.GREEN);
        return IO.getUserInt("1) Slot Machines \n2) Roulette \n3) Black-Jack \n4) Horse-Racing \n5) Casino Settings \n6) EXIT",true);
    }
    public int casinoSettings(){
        //casino menu prompt for game choosing
        Console.write("\n-- CASINO SETTINGS! --\n", Console.TextColor.GREEN);
        return IO.getUserInt("1) Change [CURRENT USER] \n2) List ALL Existing Users! \n3) List ALL Horses \n4) EXIT",true);
    }
    public int gamePrompt(){
        // game prompt for every game
        return IO.getUserInt("1) PLAY \n2) LEADERBOARD \n3) EXIT",true);
    }
    public int horseRacingPrompt(){
        //horse racing game prompt that includes list of horses
        return IO.getUserInt("1) PLAY \n2) LEADERBOARD \n3) HORSE LIST \n4) EXIT",true);
    }
    public void displayingStable(Horse[] stable){
        for (int i = 0; i < stable.length; i++) {
            System.out.println(stable[i].toString());
        }
    }
    public void exitPrompt(){
        Console.write("THANKS for visiting \"Variables in Vegas\"!", Console.TextColor.GREEN);
    }
}
