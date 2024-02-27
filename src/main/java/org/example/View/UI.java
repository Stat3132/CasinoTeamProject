package org.example.View;

import org.example.Controller.Casino;
import org.example.Model.CasinoMembers;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.Console;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class UI {
    //TODO: Horse Racing Prompts
    //TODO: Slots Prompts
    //TODO: Roulette Prompts

    // MENUS AND PROMPTS
    public int casinoMenu(){
        //casino menu prompt for game choosing
        Console.write("\n-- Variables in Vegas! --\n", Console.TextColor.GREEN); //header
        return Console.getUserInt("1) Slot Machines \n2) Roulette \n3) Black-Jack \n4) Horse-Racing \n5) Casino Settings \n6) EXIT",true);
    }
    public int casinoSettings(boolean isAiEnabled){
        //casino settings prompt
        Console.write("\n-- CASINO SETTINGS! --\n", Console.TextColor.GREEN); //header
        String textAI; //placeholder string
        if(isAiEnabled){
            //enable or disable for AI population check
            textAI = "Disable";
        } else {
            textAI = "Enable";
        }
        return Console.getUserInt("1) User Settings \n2) " + textAI + " AI \n3) Global Leaderboard \n4) Exit",true);
    }
    public int userSettings(){
        Console.write("\n-- USER SETTINGS! --\n", Console.TextColor.YELLOW); //header
        return Console.getUserInt("1) [CHANGE] Current User \n2) [DELETE] Existing User \n3) [ADD] New User \n4) [LIST] All Users \n5) EXIT",true);

    }
    public void displayGameHeader(int game, Player currentPlayer){
        //displays different game header depending on the game int provided
        switch(game){
            case 1: // slots header
                Console.write("\n-- Slots! --\n", Console.TextColor.BLUE);
                if(currentPlayer.getTotalSlotMoney() <= 0){
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + 0 + "!\n");
                } else {
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + currentPlayer.getTotalSlotMoney() + "!\n");
                }
                break;
            case 2: // roulette header
                Console.write("\n-- Roulette! --\n", Console.TextColor.RED);
                if(currentPlayer.getTotalRouletteMoney() <= 0){
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + 0 + "!\n");
                } else {
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + currentPlayer.getTotalRouletteMoney() + "!\n");
                }
                break;
            case 3: // black-jack header
                Console.write("\n-- Black-Jack! --\n", Console.TextColor.PURPLE);
                if(currentPlayer.getTotalBlackJackMoney() <= 0){
                    Console.write("[" + currentPlayer.getName() + "] $" + 0 + " WON!\n");
                } else {
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + currentPlayer.getTotalBlackJackMoney() + "!\n");
                }
                break;
            case 4: // horse-racing header
                Console.write("\n-- Horse-Racing! --\n", Console.TextColor.CYAN);
                if(currentPlayer.getTotalHorseMoney() <= 0){
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + 0 + " WON!\n");
                } else {
                    Console.write("[" + currentPlayer.getName() + "] Has won $" + currentPlayer.getTotalHorseMoney() + "!\n");
                }
                break;
        }
        footer();
    }
    public int gamePrompt(){
        // game prompt for every game
        return Console.getUserInt("1) PLAY \n2) LEADERBOARD \n3) EXIT",true);
    }
    public void populateAIPrompt(boolean isAiEnabled, String name){
        if(!isAiEnabled){
            Console.write("REMOVED " + name + " [AI]\n", Console.TextColor.RED);
        } else {
            Console.write("ADDED " + name + " [AI]\n", Console.TextColor.GREEN);
        }
    }
    public void populateAI(boolean isAiEnabled){
        //"populate" AI text
        if(isAiEnabled){
            Console.write("ENABLING AI...\n", Console.TextColor.GREEN);
            footer();
        } else {
            Console.write("DISABLING AI...\n", Console.TextColor.RED);
            footer();
        }
    }
    public void exitPrompt(){
        //prompt when player exits the app
        Console.write("Thanks for visiting \"Variables in Vegas\"!", Console.TextColor.GREEN);
    }
    public void leaderboard(CasinoMembers player, int money, int i, int game){
        //UI prompt for leaderboard
        switch(game){ //headers
            case 1: //slots
                Console.write("-- Slots Leaderboard! --\n", Console.TextColor.BLUE);
                break;
            case 2: //roulette
                Console.write("-- Roulette Leaderboard! --\n", Console.TextColor.RED);
                break;
            case 3: //black-jack
                Console.write("-- Black-Jack Leaderboard! --\n", Console.TextColor.PURPLE);
                break;
            case 4: //horse-racing
                Console.write("-- Horse-Racing! --\n", Console.TextColor.CYAN);
                break;
            default:
                Console.write("\n-- Casino Leaderboard! --\n", Console.TextColor.GREEN);
                break;
        }
        if(player.isAI()){
            //if user is ai, add tag
            System.out.println((i + 1) + ". " + player.getName() + "[AI] ($ " + money + ")");
        } else {
            //otherwise, print out user as well as money count
            System.out.println((i + 1) + ". " + player.getName() + "($ " + money + ")");
        }
    }
    public void footer(){
        //basic footer for all code to stay consistent
        System.out.println("--- --------- ---"); //footer
    }



    //  USER LOGIC AND OUTPUT
    public String createUser(boolean doesUserExist, ArrayList<CasinoMembers> allMembers){
        //user prompt to create a first or another user.
        String username; //placeholder username
        do {
            if (doesUserExist) {
                //if passed in boolean (e.g. user exists):
                username = Console.getStringInput("Create another user! \nEnter a username: ", true);
                for (CasinoMembers allMember : allMembers) {
                    if (username.equalsIgnoreCase(allMember.getName())) {
                        Console.write("This user already exists!", Console.TextColor.RED);
                        return null;
                    }
                }
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
        System.out.println(username + " Has been created!");
        return username;
    }
    public CasinoMembers changeUser(ArrayList<CasinoMembers> allUsers, Player currentPlayer){
        displayAllUsers(allUsers, currentPlayer);
        //pass in the total users array as well as the current player.
        String changeUser = Console.getUserStr("Please type the username or index you would like to change to!",true);
        CasinoMembers newCurrentPlayer = currentPlayer;
        for (int i = 0; i < allUsers.size(); i++) {
            //for loop for array list
            if(allUsers.get(i) != null) {
                // if user is NOT null, and string is equal or is contained by a user as a name, set index to new current player
                try { //trys to parse int, if successful the search is an index, otherwise it is a string
                    if (Integer.parseInt(changeUser)-1 == i) {
                        //if user entered string is parsable and equals index
                        if (allUsers.get(i) != currentPlayer) {
                            //because index is shifted up by 1 to start from 1, we must check the indexes before it.
                            if(allUsers.get(i).isAI()) {
                                Console.write("You cannot control an AI!\n", Console.TextColor.RED);
                                break;
                            } else {
                                //index starts from 1, so we must remove -1 in order to set the new current player.
                                System.out.println("The current user is now: \"" + allUsers.get(i).getName() + "\"");
                                newCurrentPlayer = allUsers.get(i);
                                return newCurrentPlayer;
                            }
                        } else {
                            //else this user is already current user
                            Console.write("This is already the current user!\n", Console.TextColor.RED);
                            break;
                        }
                    } else if ((Integer.parseInt(changeUser) > allUsers.size() || Integer.parseInt(changeUser) <= -1)) {
                        //if index is out of bounds from parsable int
                        Console.write("OUT OF BOUNDS!", Console.TextColor.RED);
                        break;
                    }
                } catch (NumberFormatException ex) {
                    if (changeUser.equalsIgnoreCase(allUsers.get(i).getName()) || allUsers.get(i).getName().equalsIgnoreCase(changeUser)) {
                        if (allUsers.get(i) != currentPlayer) {
                            if(allUsers.get(i).isAI()) {
                                //stops the user from controlling an AI player in the lists
                                Console.write("You cannot control an AI!\n", Console.TextColor.RED);
                                break;
                            } else {
                                //index starts from 1, so we must remove -1 in order to set the new current player.
                                System.out.println("The current user is now: \"" + allUsers.get(i).getName() + "\"");
                                newCurrentPlayer = allUsers.get(i);
                                return newCurrentPlayer;
                            }
                        } else {
                            //check for current player logic, cannot set current player to current player??
                            Console.write("This is already the current user!\n", Console.TextColor.RED);
                            break;
                        }
                    }
                }
            }
        }
        return newCurrentPlayer;
    }
    public ArrayList<CasinoMembers> deleteUser(ArrayList<CasinoMembers> allUsers, Player currentPlayer){
        //delete user logic
        displayAllUsers(allUsers, currentPlayer);
        //pass in the total users array as well as the current player.
        String delUser = Console.getUserStr("Please type the username or index you would like to delete!",true);
        for (int i = 0; i < allUsers.size(); i++) {
            //for loop for array list
            if(allUsers.get(i) != null) {
                // if user is NOT null, and string is equal or is contained by a user as a name, remove index.
                try { //trys to parse an int, if successful search is integer, otherwise it is string.
                    if (Integer.parseInt(delUser)-1 == i) {

                        //if user entered string is parsable and equals index
                        if (allUsers.get(i) != currentPlayer) {
                            //because index is shifted up by 1 to start from 1, we must check the indes before it.
                            System.out.println("Removed user: \"" + allUsers.get(i).getName() + "\"");
                            allUsers.remove(i);
                            return allUsers;
                        } else {
                            Console.write("You CANNOT delete the current user!\n", Console.TextColor.RED);
                            Console.write("Please CHANGE the current user and TRY AGAIN!\n", Console.TextColor.RED);
                            break;
                        }
                    } else if ((Integer.parseInt(delUser) > allUsers.size() || Integer.parseInt(delUser) <= -1)) {
                        //if index is out of bounds from parsable int
                        Console.write("OUT OF BOUNDS!", Console.TextColor.RED);
                        break;
                    }
                } catch (NumberFormatException ex) {
                    if (delUser.equalsIgnoreCase(allUsers.get(i).getName()) || allUsers.get(i).getName().equalsIgnoreCase(delUser)) {
                        if (allUsers.get(i) != currentPlayer) {
                            //index starts from 1, so we must remove -1 in order to properly delete the index.
                            System.out.println("Removed user: \"" + allUsers.get(i).getName() + "\"");
                            allUsers.remove(i);
                            return allUsers;
                        } else {
                            //check for current player logic, if currentplayer you must change so that there is always ONE player existing at all times
                            Console.write("You CANNOT delete the current user!\n", Console.TextColor.RED);
                            Console.write("Please CHANGE the current user and TRY AGAIN!\n", Console.TextColor.RED);
                            break;
                        }
                    } else if(i >= allUsers.size()-1){
                        //if the index in the for loop is over the size of the array then the search could not be found.
                        Console.write("Could not find this username!", Console.TextColor.RED);
                    }
                }
            }
        }
        return allUsers;
    }
    public void displayAllUsers(ArrayList<CasinoMembers> allUsers, Player currentPlayer){
        //display ALL users.
        System.out.println("--- ALL USERS ---"); //header
        for (int i = 0; i < allUsers.size(); i++) {
            //loops through allUsers array
            if(allUsers.get(i).isAI()) {
                //adds 'tags' to each user if boolean AI check is true or matches currentPlayer.
                System.out.println((i + 1) + " " + allUsers.get(i).getName() + " [AI]");
            } else if (allUsers.get(i) == currentPlayer){
                //display the current user that is being controller by the player
                System.out.println((i + 1) + " " + allUsers.get(i).getName() + " [CURRENT USER]");
            } else {
                System.out.println((i + 1) + " " + allUsers.get(i).getName());
            }
        }
        footer();
    }
    public int getUserBet(int totalUserMoney, Player currentPlayer){
        //gets the user bet as well as the player.
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
    public void bankruptUser(){
        Console.write("!! BANKRUPT !!", Console.TextColor.RED);
        System.out.println("\nUnfortunately, your user must be DELETED! Please create or choose another user!");
    }


    // HORSE RACING LOGIC & OUTPUT
    public int horseRacingPrompt(){
        //horse racing game prompt that includes list of horses
        return Console.getUserInt("1) PLAY \n2) LEADERBOARD \n3) HORSE LIST \n4) EXIT",true);
    }
    public int bettingOnHorse(Horse[] displayingBetableHorses){
        System.out.println("\t\t\tCURRENT HORSES TO BET ON: \n\n ");
        for (Horse displayingBetableHors : displayingBetableHorses) {
            System.out.println(displayingBetableHors);
        }
        footer();
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
        System.out.println("\tBetting on: \n\n" + bettedHorse[index] + "\n\n Let the fastest horse win! \n\n");
    }
    public void displayingStable(Horse[] stable){
        for (Horse horse : stable) {
            System.out.println(horse.toString());
        }
//        for (int i = 0; i < stable.length  ; i++) {
//            System.out.println(stable[i].toString());
//
//        }
    }
    public void finalPromptInHorseRacing(Player currentPlayer){
        System.out.println("You ended with: " + currentPlayer.getCurrentMoneyCount() + "\n\n" + "Money gained from horse racing so far: " + currentPlayer.getTotalHorseMoney());
    }

    //ROULETTE LOGIC & OuTPUT
    public int displayRouletteBetTypes(){
        int type;
        do {
            type = Console.getUserInt("\nWhat type of bet will you be making?\n1)ALL RED/ODDS\n2)ALL BLACKS/EVENS\n3)TRIPLES\n4)ONE SINGLE NUMBER", true);
            if(type >=5||type <= 0){
                System.out.println("Please put in a valid option!");
            }
            break;
        }while (true);
        System.out.println("Start the game!");

        return type;
    }
    public int displayRouletteSpecificNum(){
        int specNum;
        do {
            specNum = Console.getUserInt("What single number(from 0-36) will you bet on?", true);
            if (specNum >= 36 || specNum <= 0) {
                System.out.println("Please put in a valid option!");
            }
            break;
        }while (true);
        return specNum;
    }
    public void displayRouletteWin(boolean didWin, int betAmount){
        if (didWin){
            System.out.println("\nYou won and earned $" + betAmount);
        } else if(didWin == false) {
            System.out.println("\nBad luck! you lost $" + betAmount);
        }
    }
    public void displayRouletteFinalMoney(Player currentPlayer){
        System.out.println("You ended with: " + currentPlayer.getCurrentMoneyCount() + "\n\n" + "Money gained from roulette so far: " + currentPlayer.getTotalRouletteMoney());
    }
}
