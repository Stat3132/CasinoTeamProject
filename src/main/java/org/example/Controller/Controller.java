package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.CasinoInterface;

import java.util.ArrayList;

public class Controller {
    //TODO: logic behind username for players, easter eggs, "guest", "test", "broke", etc.
    //TODO: logic behind PLAY & LEADERBOARD options for each game menu
    //GAME INSTANCES:
    HorseRace horseRaceControl = new HorseRace();
    CasinoInterface UI = new CasinoInterface(); //view casino menus
    boolean userExists, aiEnabled = false; //boolean to check if ai is Enabled and if atleast one user exists
    ArrayList<CasinoMembers> allCasinoPlayers = new ArrayList<>(); //array of ALL users within the casino!
    private Player currentPlayer; //current player property
    int slots = 1, roulette = 2, blackjack = 3, horseRacing = 4;    //integer assigned values for each game.

    // does ATLEAST ONE user exist logic
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

    //current player logic
    public Player getCurrentPlayer() {
        //returns the current player ONLY if a player is set to exist, otherwise currentPlayer is set to NULL.
        if(!doesUserExists()){
            currentPlayer = null;
        }
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        //updates current player
    }

    //user & AI creation logic
    public void createUser(){
        //create user functionality
        String username = UI.createUser(doesUserExists(),allCasinoPlayers); //username prompt
        if(username != null) {
            Player newPlayer = new Player(username); //create new player
            currentPlayer = newPlayer; //set new player to current player
            allCasinoPlayers.add(newPlayer); //add new player to array
            setUserExists(true); //atleast one user exists
        }

    }
    public void deleteUser(){
        allCasinoPlayers = UI.deleteUser(allCasinoPlayers, currentPlayer);
    }
    public void changeUser(){
        setCurrentPlayer((Player) UI.changeUser(allCasinoPlayers, currentPlayer));
    }
    public void populateAI(){
        this.aiEnabled = !aiEnabled; //flip the boolean around
        //populates array with "AI" or fake players that have their values randomizes
        UI.populateAI(aiEnabled);
        if(!aiEnabled){ //if boolean is false, delete AI members
            for (int i = 0; i < allCasinoPlayers.size(); i++) {
                if(allCasinoPlayers.get(i) != null){
                    if (allCasinoPlayers.get(i).isAI()){
                        UI.populateAIPrompt(aiEnabled, allCasinoPlayers.get(i).getName());
                        //calls UI with boolean to check if ai is being enabled or disabled, as well as the string name
                        allCasinoPlayers.remove(i);
                        i--;
                    }
                }
            }
        } else { //add new AI members
            int totalAI = ProbabilityForValue.randomValues(1,10);
            for (int i = 0; i < totalAI; i++) {
                CasinoAI playerAI = new CasinoAI();
                UI.populateAIPrompt(aiEnabled,  playerAI.getName());
                allCasinoPlayers.add(playerAI);
            }
        }
    }


    // menu prompts logic for games, settings, and user creation
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
    public void gameSettings() {
        // game settings functionality
        do {
            switch (UI.casinoSettings(aiEnabled)) {
                case 1: //user settings
                    userSettings();
                    break;
                case 2:  //populate AI
                    populateAI();
                    break;
                case 3: //exit back to gameoutput
                    return;
            }
        } while (true);
    }
    public void userSettings(){
        //user settings menu prompts
        do {
            switch (UI.userSettings()){
                case 1: //change current user
                    changeUser();
                    break;
                case 2: //delete a user
                    deleteUser();
                    break;
                case 3: //add a new user
                    createUser();
                    break;
                case 4: //list all users
                    UI.displayAllUsers(allCasinoPlayers, getCurrentPlayer());
                    break;
                case 5: //exit back to casino settings
                    return;
            }
        } while(true);
    }


    // game creation and simulation logic
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
                            horseRaceControl.play(getCurrentPlayer());
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


    public void getUserBet(){
        //gets user bet UI text
        UI.getUserBet(getCurrentPlayer().getCurrentMoneyCount(), getCurrentPlayer());
    }


}
