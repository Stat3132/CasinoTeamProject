package org.example.View;

import org.example.UTIL.Console;

public class CasinoInterface {
    Console IO = new Console();

    public String firstUserPrompt(){
        //user prompt to create a user
        return IO.getStringInput("Create a user! ",true);
    }
    public int casinoMenu(){
        //casino menu prompt for game choosing
        System.out.println("\n-- Variables in Vegas! --");
        return IO.getUserInt("1) Slot Machines \n2) Roulette \n3) Black-Jack \n4) Horse-Racing \n5) Casino Settings \n6) EXIT",true);
    }

    public int gamePrompt(){
        // game prompt for every game
        return IO.getUserInt("1) PLAY \n2) LEADERBOARD \n3) EXIT",true);
    }
    public int horseRacingPrompt(){
        //horse racing game prompt that includes list of horses
        return IO.getUserInt("1) PLAY \n2) LEADERBOARD \n3) HORSE LIST \n4) EXIT",true);
    }
    public void exitPrompt(){
        System.out.println("Thanks for visiting \"Variables in Vegas\"!");
    }

}
