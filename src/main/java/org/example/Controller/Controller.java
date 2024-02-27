package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.UI;

import java.lang.reflect.Array;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    //TODO: logic behind username for players, easter eggs, "guest", "test", "broke", etc.
    //TODO: logic behind PLAY & LEADERBOARD options for each game menu
    //GAME INSTANCES:
    SlotMachine slotsClass = new SlotMachine();
    Roulette rouletteClass = new Roulette();
    BlackJack bjClass = new BlackJack();
    HorseRace horseClass = new HorseRace();

    UI UI = new UI(); //view casino menus
    boolean userExists, aiEnabled = false; //boolean to check if ai is Enabled and if atleast one user exists
    ArrayList<CasinoMembers> allCasinoPlayers = new ArrayList<>(); //array of ALL users within the casino!
    private Player currentPlayer; //current player property
    int slots = 1, roulette = 2, blackjack = 3, horseRacing = 4;    //integer assigned values for each game.

    // does ATLEAST ONE user exist logic
    public boolean doesUserExists() {
        //loops through array to check if exists, if null = false, if once true, stop loop and return value true;
        if(allCasinoPlayers.isEmpty()){
            setUserExists(false);
        } else {
            for (CasinoMembers allCasinoPlayer : allCasinoPlayers) {
                if (allCasinoPlayer == null) {
                    setUserExists(false);
                } else {
                    if (!allCasinoPlayer.isAI()) {
                        setUserExists(true);
                        return userExists;
                    }
                }
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
            setUserExists(true); //least one user exists
        }

    }
    public void deleteUser(){
        allCasinoPlayers = UI.deleteUser(allCasinoPlayers, currentPlayer);
        doesUserExists();
    }
    public void deleteUser(Player player){
        //overloaded deleteUser method to delete a SPECIFIC user as requested in console.
        allCasinoPlayers.remove(player);
        doesUserExists();
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
    public void canUserPlay(){
        //checks if the user has become bankrupt
        if(currentPlayer.getCurrentMoneyCount() <= 0){
            UI.bankruptUser(); //if so, call UI dependent text
            deleteUser(currentPlayer); //delete the user
            currentPlayer = null; //set the user to null
            doesUserExists();
            do { //run loop while currentplayer is null to force player to choose or create another user
                if(userExists){
                    changeUser();
                } else {
                    createUser();
                }
            } while(currentPlayer == null);
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
        //FIXME: TEMP VALUES FOR LEADERBOARD TESTING
//        currentPlayer.setTotalBlackJackMoney(ProbabilityForValue.randomValues(1,1000));
//        currentPlayer.setTotalHorseMoney(ProbabilityForValue.randomValues(1,1000));
//        currentPlayer.setTotalRouletteMoney(ProbabilityForValue.randomValues(1,1000));
//        currentPlayer.setTotalHorseMoney(ProbabilityForValue.randomValues(1,1000));

        canUserPlay();
        switch(game){ //switch for game int provided on casinoOutput function
            case 1: //slots
                do {
                    canUserPlay();
                    UI.displayGameHeader(slots);
                    switch (UI.gamePrompt()) { //nested switch for slot chosen by gameOption
                        case 1: // slots play option
                            playAI(slots);
                            slotsClass.play(getCurrentPlayer(), getUserBet());
                            break;
                        case 2: // slots leaderboard option
                            leaderboard(slots);
                            break;
                        case 3: // exit
                            return;
                    }
                }while (true);
            case 2: //roulette
                do {
                    canUserPlay();
                    UI.displayGameHeader(roulette);
                    switch (UI.gamePrompt()) { //nested switch for roulette chosen by gameOption
                        case 1: // roulette play option
                            playAI(roulette);
                            rouletteClass.play(getCurrentPlayer(), 0);
                            break;
                        case 2: // roulette leaderboard option
                            leaderboard(roulette);
                            break;
                        case 3: // exit
                            return;
                    }
                }while (true);
            case 3: //black-jack
                do {
                    canUserPlay();
                    UI.displayGameHeader(blackjack);
                    switch (UI.gamePrompt()) { //nested switch for black-jack chosen by gameOption
                        case 1: // black-jack play option
                            playAI(blackjack);
                            bjClass.play(getCurrentPlayer(),getUserBet());
                            break;
                        case 2: // black-jack leaderboard option
                            leaderboard(blackjack);
                            break;
                        case 3: // exit
                            return;
                    }
                }while (true);
            case 4: //horse-racing
                do {
                    canUserPlay();
                    UI.displayGameHeader(horseRacing);
                    switch (UI.horseRacingPrompt()) { //nested switch for horse racing chosen by gameOption
                        case 1: // horse-racing play option
                            playAI(horseRacing);
                            horseClass.play(getCurrentPlayer(),getUserBet());
                            break;
                        case 2:
                            break;
                        case 3: // horse-: // horse names
                             horseClass.populatingStable();
                            UI.displayingStable(horseClass.fullStableOfRacerHorses);
                            break;
                        case 4: // exit
                            return;
                    }
                }while (true);
        }
    }

    public int getUserBet(){
        //gets user bet UI text
        return UI.getUserBet(getCurrentPlayer().getCurrentMoneyCount(), getCurrentPlayer());
    }
    public int getAIBet(CasinoMembers AI){
        //gets an ai randomized bet with a low min of 1-100 and a high min of their total money count
        int lowBet = ProbabilityForValue.randomValues(1,100);
        return ProbabilityForValue.randomValues(lowBet,AI.getCurrentMoneyCount());
    }
    public void playAI(int game){
        //simulates AI playing the game by looping through every index of array and plays if AI (if ai is inherently enabled)
        if(aiEnabled) {
            for (CasinoMembers allCasinoPlayer : allCasinoPlayers) {
                if (allCasinoPlayer.isAI()) {
                    switch (game) {
                        case 1: //slots
                            slotsClass.play((Player) allCasinoPlayer, getAIBet(allCasinoPlayer));
                            break;
                        case 2: //roulette
                            rouletteClass.play((Player) allCasinoPlayer, getAIBet(allCasinoPlayer));
                            break;
                        case 3: //black-jack
                            bjClass.play((Player) allCasinoPlayer, getAIBet(allCasinoPlayer));
                            break;
                        case 4: //horse-racing
                            horseClass.play((Player) allCasinoPlayer, getAIBet(allCasinoPlayer));
                            break;
                    }
                }
            }
        }
    }


    public void leaderboard(int game){
        //TODO:
        switch(game){
            case 1: //slots

                break;
            case 2: //roulette

                break;
            case 3: //black-jack

                break;
            case 4: //horse-racing

                break;
        }

    }

}
