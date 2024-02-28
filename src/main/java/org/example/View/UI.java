package org.example.View;

import org.example.Model.CasinoMembers;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.Console;

import java.util.ArrayList;

public class UI {
    //TODO: Slots Prompts
    //TODO: Blackjack prompts

    //CASINO MENU & SETTINGS ////////////////////////////////////////////////////////////////////////////////////////////////
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
        return Console.getUserInt("1) [CHANGE] Current User \n2) [DELETE] Existing User \n3) [ADD] New User \n4) [LIST] Current User \n5) [LIST] All Users \n6) EXIT",true);
    }
    public void exitPrompt(){
        //prompt when player exits the app
        Console.write("Thanks for visiting \"Variables in Vegas\"!", Console.TextColor.GREEN);
    }


    //GAME MENUS & SETTINGS ////////////////////////////////////////////////////////////////////////////////////////////////
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
    public void footer(){
        //basic footer for all code to stay consistent
        System.out.println("--- --------- ---"); //footer
    }

    //AI PROMPTS ////////////////////////////////////////////////////////////////////////////////////////////////
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
    public void populateAIPrompt(boolean isAiEnabled, String name){
        //populate AI prompt used for removing or adding AI
        if(!isAiEnabled){
            Console.write("REMOVED " + name + " [AI]\n", Console.TextColor.RED);
        } else {
            Console.write("ADDED " + name + " [AI]\n", Console.TextColor.GREEN);
        }
    }
    public void deleteAI(String name){
        Console.write(name + " [AI] HAS GONE !! BANKRUPT !!", Console.TextColor.RED);
        Console.write("IT WILL NOW BE DELETED!!", Console.TextColor.RED);
    }


    //LEADERBOARD MENUS ////////////////////////////////////////////////////////////////////////////////////////////////
    public void leaderboard(CasinoMembers player, int money, int i){
        //UI prompt for leaderboard
        if(player.isAI()){
            //if user is ai, add tag
            System.out.println((i + 1) + ". " + player.getName() + " [AI] ($" + money + ")");
        } else {
            //otherwise, print out user as well as money count
            System.out.println((i + 1) + ". " + player.getName() + " ($" + money + ")");
        }
    }
    public void leaderboardHeader(int game){
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
    }


    //USER LOGIC AND OUTPUT ////////////////////////////////////////////////////////////////////////////////////////////////

    /** *
     * This method creates a user by enforces a series of requirements that a player inputting a username must pass into.
     * Passing in specific usernames unlocks "EASTER EGGS", specific usernames that have been matched will give different properties to that user, such as 'test',etc.
     * @param doesUserExist boolean check whether this is the first user being created
     * @param allUsers all total members are listed through to check if they're AI.
     * @return user input username
     */
    public String createUser(boolean doesUserExist, ArrayList<CasinoMembers> allUsers){
        //user prompt to create a first or another user.
        String username; //placeholder username
        do {
            if (doesUserExist) {
                //if passed in boolean (e.g. user exists):
                username = Console.getStringInput("Create another user! \nEnter a username: ", true);
                for (CasinoMembers allMember : allUsers) {
                    if (username.equalsIgnoreCase(allMember.getName())) {
                        Console.write("This user already exists!", Console.TextColor.RED);
                        return null;
                    }
                }
            } else {
                // else create your FIRST user
                username = Console.getStringInput("Create Your First user! \nEnter a username: ", true);
            }
            if(username.isEmpty() || username.contains(" ")){
                //username cannot be empty or contain spaces
                Console.write("Your username cannot be empty or contain spaces!\n\n", Console.TextColor.RED);
            } else if(username.length() > 20){
                //username cannot be greater than 20 chars
                Console.write("Your username is too long!\n\n", Console.TextColor.RED);
            } else if(username.length() < 2){
                //username cannot be less than 2 chars
                Console.write("Your username is too short!\n\n", Console.TextColor.RED);
            } else {
                //else break because username is fine!
                break;
            }
        } while(true);
        System.out.println(username + " has been created!");
        return username;
    }

    /**
     * This method changes the current player by showing a list of all existing players, and updates the current player.
     * DOES NOT allow the user to switch to an AI controlled user.
     * @param allUsers arraylist of all users to display a list for the user to select from
     * @param currentPlayer the current user has an appropriate tag within the list.
     * @return new current player
     */
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

    /**
     * This method deletes a specified user from a list.
     * DOES NOT allow the user to delete an AI.
     * @param allUsers array list of all current users to display a list for the user to select from
     * @param currentPlayer the current user is prevented from being deleted.
     * @return new arraylist with deleted user removed.
     */
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

    /**
     * This method takes in a passed in array and loops through the entire array to display ALL USERS, AI included.
     * @param allUsers an array of all current users to loop through
     * @param currentPlayer the current player so that the appropriate tag is added on.
     */
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
    public void displayCurrentUser(Player player){
        System.out.println("[CURRENT USER]:");
        System.out.println("\"" + player.getName() + "\"");
        System.out.println("{TOTAL CASH}: $" + player.getCurrentMoneyCount());
        footer();
        System.out.println("{TOTAL WINNINGS}: $" + player.getTotalWinnings());
        System.out.println("{SLOTS' EARNINGS}: $" + player.getTotalSlotMoney());
        System.out.println("{ROULETTE EARNINGS}: $" + player.getTotalRouletteMoney());
        System.out.println("{BLACK-JACK EARNINGS}: $" + player.getTotalBlackJackMoney());
        System.out.println("{HORSE-RACING EARNINGS}: $" + player.getTotalHorseMoney());
        footer();
    }

    /**
     * This method gets a proper user bet through required parameters and subtracts the bet from the player.
     * @param totalUserMoney gets the total money available to the user
     * @return int of user
     */
    public int getUserBet(int totalUserMoney){
        //gets the user bet as well as the player.
        int userBet; //placeholder int
        do {
            //loop grabs a bet amount from player:
            System.out.println("Total User Money: $" + totalUserMoney);
            userBet = Console.getUserInt("Enter your bet amount!", true);
            if (userBet > totalUserMoney) {
                //if bet is too high or too low, loop; else break
                Console.write("Your bet is too high! \nTRY AGAIN!\n", Console.TextColor.RED);
            } else if(userBet <= 0){
                Console.write("Your bet is too low! \nTRY AGAIN!\n", Console.TextColor.RED);
            } else {
                break;
            }
        } while(true);
        System.out.println("You've bet $" + userBet); //confirmation bet amount
        footer();
        return userBet;
    }
    public void bankruptUser(){
        //bankrupt text that is displayed when the user has lost all money
        Console.write("!! BANKRUPT !!", Console.TextColor.RED);
        System.out.println("\nUnfortunately, your user must be DELETED! Please create or choose another user!");
    }
    public void didUserWin(boolean didWin, int betAmount){
        if (didWin){
            Console.write("\nCongratulations! You won $" + betAmount + "\n", Console.TextColor.GREEN);
        } else {
            Console.write("\nBad luck! You Lose $" + betAmount + "\n", Console.TextColor.RED);
        }
    }
    public void displayFinalMoney(Player currentPlayer, int game){
        //display final money for game output.
        String gameText = "";
        int money = 0;
        //case and switch statement for game dependent money total and text display
        switch (game) {
            case 1:
                gameText = "Slots";
                money = currentPlayer.getTotalSlotMoney(); //slots
                break;
            case 2:
                gameText = "Roulette";
                money = currentPlayer.getTotalRouletteMoney(); //roulette
                break;
            case 3:
                gameText = "Black-jack";
                money = currentPlayer.getTotalBlackJackMoney(); //black-jack
                break;
            case 4:
                gameText = "Horse-racing";
                money = currentPlayer.getTotalHorseMoney(); //horse-racing
                break;
        }
        if(money <= 0){
            Console.write("\nYou are left with: $" + currentPlayer.getCurrentMoneyCount());
            Console.write("\nTotal Money LOST from " + gameText + ": $" + money + "\n",Console.TextColor.RED);
        } else {
            Console.write("\nYou are left with: $" + currentPlayer.getCurrentMoneyCount());
            Console.write("\nTotal Money GAINED from " + gameText + ": $" + money + "\n", Console.TextColor.GREEN);
        }
    }



    // HORSE RACING LOGIC & OUTPUT ////////////////////////////////////////////////////////////////////////////////////////////////
    public int horseRacingPrompt(){
        //horse racing game prompt that includes list of horses
        return Console.getUserInt("1) PLAY \n2) LEADERBOARD \n3) HORSE LIST \n4) EXIT",true);
    }
    public int bettingHorse(Horse[] displayingBetableHorses){
        System.out.println("\t\t\tCURRENT HORSES TO BET ON: \n\n ");
        for (Horse displayingBetableHors : displayingBetableHorses) {
            System.out.println(displayingBetableHors);
        }
        footer();
        return Console.getUserInt("Which horse are you betting on? Pick a number from 1-7", true);
    }
    public void displayHorseWinner(Horse[] winnerHorse, boolean didWin, int betAmount){
        System.out.println("After careful evaluation, the winner is... \n\n" + winnerHorse[6]+"!!");
        if (didWin){
            System.out.println("\nYou picked the correct horse!");
            didUserWin(didWin,betAmount);
        } else {
            System.out.println("\nYou picked the wrong horse!");
            didUserWin(didWin,betAmount);
        }
    }
    public void displayBettedHorse(Horse[] bettedHorse, int index){
        System.out.println("\tBetting on: \n\n" + bettedHorse[index] + "\n\n Let the fastest horse win! \n\n");
    }
    public void displayStable(Horse[] stable){
        for (Horse horse : stable) {
            System.out.println(horse.toString());
        }
//        for (int i = 0; i < stable.length  ; i++) {
//            System.out.println(stable[i].toString());
//
//        }
    }


    //ROULETTE LOGIC & OUTPUT ////////////////////////////////////////////////////////////////////////////////////////////////
    public int displayRouletteBetTypes(){
        int type;
        do {
            type = Console.getUserInt("\nWhat type of bet will you be making? \n1) ALL RED/ODDS \n2) ALL BLACKS/EVENS \n3) TRIPLES \n4) ONE SINGLE NUMBER", true);
            if(type >=5 || type <= 0){
                Console.write("Please enter a valid number!", Console.TextColor.RED);
            } else {
                break;
            }
        }while (true);
        System.out.println("Thanks for your bet!");
        return type;
    }
    public int displayRouletteSpecificNum(){
        int specNum;
        do {
            specNum = Console.getUserInt("What single number (0-36) will you bet on?", true);
            if (specNum >= 36 || specNum <= 0) {
                Console.write("Please enter a valid number!", Console.TextColor.RED);
            } else {
                break;
            }
        }while (true);
        return specNum;
    }


    //SLOTS LOGIC & OUTPUT ////////////////////////////////////////////////////////////////////////////////////////////////
    public void displaySlots(String slot){
        Console.write("|"+ slot.toUpperCase() + "| ",Console.TextColor.BLUE);
    }
    public void displaySlotsCheck(String check){
        Console.write("\n!! " + check.toUpperCase() + " !!",Console.TextColor.BLUE);
    }
}
