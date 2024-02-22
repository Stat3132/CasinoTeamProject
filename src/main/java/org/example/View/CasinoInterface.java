package org.example.View;

import org.example.Model.CasinoMembers;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.Console;

import java.util.ArrayList;

public class CasinoInterface {

    //create your first or a new user.
    public String userPrompt(boolean doesUserExist){
        //user prompt to create a user
        String username; //placeholder username
        do {
            if (doesUserExist) {
                //if passed in boolean (e.g. user exists):
                username = Console.getStringInput("Create another user! \nEnter a username: ", true);
            } else {
                // else create your FIRST user
                username = Console.getStringInput("Create Your First user! \nEnter a username: ", true);
            }
            if(!username.isEmpty() && !username.contains(" ")){
                //if username is not empty and does not contain space, break out of loop
                break;
            } else {
                //else error message
                Console.write("Your username cannot be empty or contain spaces!\n\n", Console.TextColor.RED);
            }
        } while(true);
        return username;
    }
    //menu prompts
    public int casinoMenu(){
        //casino menu prompt for game choosing
        Console.write("\n-- Variables in Vegas! --\n", Console.TextColor.GREEN); //header
        return Console.getUserInt("1) Slot Machines \n2) Roulette \n3) Black-Jack \n4) Horse-Racing \n5) Casino Settings \n6) EXIT",true);
    }
    //settings prompts
    public int casinoSettings(boolean isAiEnabled){
        //casino menu prompt for game choosing
        Console.write("\n-- CASINO SETTINGS! --\n", Console.TextColor.GREEN); //header
        String textAI; //placeholder string
        if(isAiEnabled){
            //enable or disable for AI population check
            textAI = "Enable";
        } else {
            textAI = "Disable";
        }
        return Console.getUserInt("1) Change [CURRENT USER] \n2) List [ALL] Existing Users! \n3) " + textAI + " AI \n4) EXIT",true);
    }

    //prompt when player exits the app
    public void exitPrompt(){
        Console.write("THANKS for visiting \"Variables in Vegas\"!", Console.TextColor.GREEN);
    }

    //gets the user bet as well as the player.
    public int getUserBet(int totalUserMoney, Player currentPlayer){
        int userBet; //placeholder int
        do {
            //loop grabs a bet amount from player:
            System.out.println("Total User Money: $" + totalUserMoney);
            userBet = Console.getUserInt("Enter your bet amount!", true);
            if (userBet > totalUserMoney) {
                //if bet is too high or too low, loop; else break
                System.out.println("Your bet is too high! \nTRY AGAIN!");
            } else if(userBet < 0){
                System.out.println("Your bet is too low! \nTRY AGAIN!");
            } else {
                break;
            }
        } while(true);
        System.out.println("You've bet $" + userBet); //confirmation bet amount
        currentPlayer.setCurrentMoneyCount(currentPlayer.getCurrentMoneyCount() - userBet); //subtract bet from currentuser totalmoney
        return userBet;
    }
    //display the current user that is being controller by the player
    public void displayCurrentUser(Player currentPlayer){
        System.out.println("[CURRENT USER] " + currentPlayer.getName());
    }
    //display ALL users.
    public void displayAllUsers(ArrayList<CasinoMembers> allUsers, Player currentPlayer){
        System.out.println("--- ALL USERS ---"); //header
        for (int i = 0; i < allUsers.size(); i++) {
            //loops through allUsers array
            if(allUsers.get(i).isAI()) {
                //adds 'tags' to each user if boolean AI check is true or matches currentPlayer.
                System.out.println((i + 1) + " " + allUsers.get(i).getName() + " [AI]");
            } else if (allUsers.get(i) == currentPlayer){
                System.out.println((i + 1) + " " + allUsers.get(i).getName() + " [CURRENT USER]");
            } else {
                System.out.println((i + 1) + " " + allUsers.get(i).getName());
            }
        }
        System.out.println("--- --------- ---"); //footer
    }
    //"populates" AI text

    public void populateAI(boolean isAiEnabled){
        //"populate" AI text
        if(isAiEnabled){
            Console.write("ENABLING AI", Console.TextColor.GREEN);
        } else {
            Console.write("DISABLING AI", Console.TextColor.RED);
        }
    }
    //displays different game header depending on the game int provided
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
