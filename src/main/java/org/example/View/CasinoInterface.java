package org.example.View;

import org.example.UTIL.IO;

public class CasinoInterface {
    IO casinoIO = new IO();

    public String firstUserPrompt(){
        //user prompt to create a user
        return casinoIO.getUserStr("Create a user! ", true);
    }
    public int casinoMenu(){
        //casino menu prompt for game choosing
        System.out.println("\n-- Variables in Vegas! --");
        return casinoIO.getUserInt("1) Slot Machines \n2) Roulette \n3) Black-Jack \n4) Horse-Racing \n5) Casino Settings \n6) EXIT",true);
    }

    public int gamePrompt(){
        // game prompt for every game
        return casinoIO.getUserInt("1) PLAY \n2) LEADERBOARD \n3) EXIT",true);
    }
    public int horseRacingPrompt(){
        //horse racing game prompt that includes list of horses
        return casinoIO.getUserInt("1) PLAY \n2) LEADERBOARD \n3) HORSE LIST \n4) EXIT",true);
    }
    public void exitPrompt(){
        System.out.println("Thanks for visiting \"Variables in Vegas\"!");
    }

}
