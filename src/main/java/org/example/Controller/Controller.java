package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.UTIL.Console;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.CasinoInterface;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    //TODO: logic behind username for players, easter eggs, "guest", "test", "broke", etc.
    //TODO: logic behind PLAY & LEADERBOARD options for each game menu
    //GAME INSTANCES:
    HorseRace horseRaceControl = new HorseRace();
    CasinoInterface UI = new CasinoInterface();
    boolean userExists, aiEnabled = false;
    ArrayList<CasinoMembers> allCasinoPlayers = new ArrayList<>(); //array of ALL users within the casino!
    Player currentPlayer;
    int slots = 1, roulette = 2, blackjack = 3, horseRacing = 4;

    // getter & setter does atleast ONE user exist functionality.
    public boolean doesUserExists() {
        //loops through array to check if exists, if null = false, if once true, stop loop and return value true;
        for (int i = 0; i < allCasinoPlayers.size(); i++) {
            if(allCasinoPlayers.get(i) == null){
                setUserExists(false);
            } else {
                setUserExists(true);
                return userExists;
            }
        }
        return userExists;
    }
    public void setUserExists(boolean oneUserExists) {
        this.userExists = oneUserExists;
    }
    //create your first OR another user.
    public void createUser(){
        String username = UI.userPrompt(doesUserExists()); //username prompt
        Player newPlayer = new Player(username); //create new player
        currentPlayer = newPlayer; //set new player to current player
        allCasinoPlayers.add(newPlayer); //add new player to array
        setUserExists(true); //atleast one user exists
        System.out.println(username + " Has been created!");
    }
    public void populateAI(){
        aiEnabled = !aiEnabled; //flip the boolean around
        //populates array with "AI" or fake players that have their values randomizes
        UI.populateAI(aiEnabled);
        if(!aiEnabled){ //if boolean is false, delete AI members
            for (int i = 0; i < allCasinoPlayers.size(); i++) {
                if (allCasinoPlayers.get(i).isAI()){
                    allCasinoPlayers.remove(i);
                    Console.write("REMOVED " + allCasinoPlayers.get(i).getName() + " [AI]\n", Console.TextColor.RED);
                }
            }
        } else { //add new AI members
            int totalAI = ProbabilityForValue.randomValues(1,10);
            for (int i = 0; i < totalAI; i++) {
                CasinoMembers playerAI = new CasinoAI();
                allCasinoPlayers.add(playerAI);
                Console.write("ADDED " + allCasinoPlayers.get(i).getName() + "[AI]\n", Console.TextColor.GREEN);
            }
        }
    }

    //gets user bet UI text
    public void getUserBet(){
        UI.getUserBet(currentPlayer.getCurrentMoneyCount(), currentPlayer);
    }


    public void casinoOutput(){
        // games that are assigned designated integers.
        //do while loop for game menu
        doesUserExists(); //checks if a user exists
        if(userExists) {
            do {
                int gameChoosen = UI.casinoMenu();
                switch (gameChoosen) {
                    case 1: // slots
                        gameOutput(slots);
                        break;
                    case 2: // roulette
                        gameOutput(roulette);
                        break;
                    case 3: // black-jack
                        gameOutput(blackjack);
                        break;
                    case 4: // horse-racing
                        gameOutput(horseRacing);
                        break;
                    case 5: // info
                        gameSettings();
                        break;
                    case 6: //exit
                        UI.exitPrompt();
                        return;
                }
            } while (true);
        } else {
            // creates a user as no user has been detected within the array list
            createUser();
            casinoOutput(); //recalls the method to loop
        }
    }
    public void gameOutput(int game){
        // do while loop for selected game's menu prompts
            switch(game){ //switch for game int provided on casinoOutput function
                case 1: //slots
                    do {
                        UI.displayGameHeader(1);
                        switch (UI.gamePrompt()) { //nested switch for slot chosen by gameOption
                            case 1: // slots play option
                                getUserBet();
                                //TODO: slots game play
                                break;
                            case 2: // slots leaderboard option
                                //TODO: slots leaderboard
                                break;
                            case 3: // exit
                                return;
                        }
                    }while (true);
                case 2: //roulette
                    do {
                        UI.displayGameHeader(2);
                        switch (UI.gamePrompt()) { //nested switch for roulette chosen by gameOption
                            case 1: // roulette play option
                                getUserBet();
                                //TODO: roulette game play
                                break;
                            case 2: // roulette leaderboard option
                                //TODO: roulette leaderboard
                                break;
                            case 3: // exit
                                return;
                        }
                    }while (true);
                case 3: //black-jack
                    do {
                        UI.displayGameHeader(3);
                        switch (UI.gamePrompt()) { //nested switch for black-jack chosen by gameOption
                            case 1: // black-jack play option
                                getUserBet();
                                //TODO: black-jack game play
                                break;
                            case 2: // black-jack leaderboard option
                                //TODO: black-jack leaderboard
                                break;
                            case 3: // exit
                                return;
                        }
                    }while (true);
                case 4: //horse-racing
                    do {
                        UI.displayGameHeader(4);
                        switch (UI.horseRacingPrompt()) { //nested switch for horse racing chosen by gameOption
                            case 1: // horse-racing play option
                                //TODO: horse race game play
                                horseRaceControl.play(currentPlayer);
                                break;
                            case 2: // horse-racing leaderboard option
                                //TODO: horse racing leaderboard
                                break;
                            case 3: // horse names
                                horseRaceControl.populatingStable();
                                UI.displayingStable(horseRaceControl.fullStableOfRacerHorses);
                                break;
                            case 4: // exit
                                return;
                        }
                    }while (true);
            }
    }
    public void gameSettings() {
        // game settings functionality
        do {
            switch (UI.casinoSettings(aiEnabled)) {
                case 1: //change current user
                    UI.displayCurrentUser(currentPlayer);
                    UI.displayAllUsers(allCasinoPlayers, currentPlayer);
                    break;
                case 2: //list all users
                    UI.displayAllUsers(allCasinoPlayers, currentPlayer);
                    break;
                case 3: //populate AI
                    populateAI();
                    break;
                case 4: //exit back to gameoutput
                    return;
            }
        } while (true);
    }


}
