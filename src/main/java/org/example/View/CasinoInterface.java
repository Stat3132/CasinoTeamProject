package org.example.View;

import org.example.Model.CasinoMembers;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.Console;

import java.util.ArrayList;

public class CasinoInterface {

    public String userPrompt(boolean doesUserExist){
        //user prompt to create a user
        String username;
        do {
            if (doesUserExist) {
                username = Console.getStringInput("Create another user! \nEnter a username: ", true);
            } else {
                username = Console.getStringInput("Create Your First user! \nEnter a username: ", true);
            }
            if(!username.isEmpty() && !username.contains(" ")){
                break;
            } else {
                Console.write("Your username cannot be empty or contain spaces!\n\n", Console.TextColor.RED);
            }
        } while(true);
        return username;
    }
    public int casinoMenu(){
        //casino menu prompt for game choosing
        Console.write("\n-- Variables in Vegas! --\n", Console.TextColor.GREEN);
        return Console.getUserInt("1) Slot Machines \n2) Roulette \n3) Black-Jack \n4) Horse-Racing \n5) Casino Settings \n6) EXIT",true);
    }
    public int casinoSettings(){
        //casino menu prompt for game choosing
        Console.write("\n-- CASINO SETTINGS! --\n", Console.TextColor.GREEN);
        return Console.getUserInt("1) Change [CURRENT USER] \n2) List ALL Existing Users! \n3) Populate AI \n4) EXIT",true);
    }
    public int getUserBet(int totalUserMoney, Player currentPlayer){
        int userBet;
        do {
            System.out.println("Total User Money: $" + totalUserMoney);
            userBet = Console.getUserInt("Enter your bet amount!", true);
            if (userBet > totalUserMoney) {
                System.out.println("Your bet is too high! \nTRY AGAIN!");
            } else {
                break;
            }
        } while(true);
        System.out.println("You've bet $" + userBet);
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() - userBet);
        return userBet;
    }
    public void displayAllUsers(ArrayList<CasinoMembers> allUsers, Player currentPlayer){
        System.out.println("--- ALL USERS ---");
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).isAI()) {
                System.out.println((i + 1) + " " + allUsers.get(i).getName() + " [AI]");
            } else if (allUsers.get(i) == currentPlayer){
                System.out.println((i + 1) + " " + allUsers.get(i).getName() + " [CURRENT USER]");
            } else {
                System.out.println((i + 1) + " " + allUsers.get(i).getName());
            }
        }
        System.out.println("--- --------- ---");
    }
    public void populateAI(boolean isAiEnabled){
        if(isAiEnabled){
            Console.write("ENABLING AI", Console.TextColor.GREEN);
        } else {
            Console.write("DISABLING AI", Console.TextColor.RED);
        }
    }
    public void displayCurrentUser(Player currentPlayer){
        System.out.println("[CURRENT USER] " + currentPlayer.getName());
    }
    public void displayGameHeader(int game){
        switch(game){
            case 1: // slots header
                Console.write("-- Slots! --\n", Console.TextColor.BLUE);
                break;
            case 2: // roulette header
                Console.write("-- Roulette! --\n", Console.TextColor.RED);
                break;
            case 3: // black-jack header
                Console.write("-- Black-Jack! --\n", Console.TextColor.PURPLE);
                break;
            case 4: // horse-racing header
                Console.write("-- Horse-Racing! --\n", Console.TextColor.CYAN);
                break;
        }
    }
    public int gamePrompt(){
        // game prompt for every game
        return Console.getUserInt("1) PLAY \n2) LEADERBOARD \n3) EXIT",true);
    }
    public void exitPrompt(){
        Console.write("THANKS for visiting \"Variables in Vegas\"!", Console.TextColor.GREEN);
    }




    //TODO: HORSE UI ///////////////////////////////////////////////////////////////////////////////////////////////////
    public int horseRacingPrompt(){
        //horse racing game prompt that includes list of horses
        return Console.getUserInt("1) PLAY \n2) LEADERBOARD \n3) HORSE LIST \n4) EXIT",true);
    }
    public int bettingOnHorse(Horse[] displayingBetableHorses){
        System.out.println("\t\t\tCURRENT HORSES TO BET ON: \n\n ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        for (int i = 0; i < displayingBetableHorses.length; i++) {
            System.out.println(displayingBetableHorses[i]);
        }
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        return Console.getUserInt("Which horse are you betting on? Pick a number from 1-7", true);
    }
    public void displayingWinner(Horse[] winnerHorse, boolean winnerEvaluation, int betAmount){
        System.out.println("After careful evaluation the winner is: \n\n" + winnerHorse[6]);
        if (winnerEvaluation){
            System.out.println("\nYou have picked the correct horse and you get $" + betAmount);
        }
        if (!winnerEvaluation){
            System.out.println("\nYou have picked the wrong horse and you LOSE $" + betAmount);
        }
    }
    public void displayingHorseBettedOn(Horse[] bettedHorse, int index){
        System.out.println("\tBetting on: \n\n" + bettedHorse[index] + "\n\n Let the fastest horse win! \n\n" + "||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
    }
    public void displayingStable(Horse[] stable){
        for (int i = 0; i < stable.length; i++) {
            System.out.println(stable[i].toString());
        }
    }
    public void finalPromptInHorseRacing(Player currentPlayer){
        System.out.println("You ended with: " + currentPlayer.getCurrentMoneyCount() + "\n\n" + "Money gained from horse racing so far: " + currentPlayer.getTotalHorseMoney());
    }
    //TODO://///////////////////////////////////////////////////////////////////////////////////////////////////////////

}
