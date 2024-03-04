package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.UTIL.ProbabilityForValue;
import org.example.View.UI;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller {

    //region VARS
    UI UI = new UI(); //view casino menus
    //GAME INSTANCES:
    SlotMachine slotsClass = new SlotMachine();
    Roulette rouletteClass = new Roulette();
    BlackJack bjClass = new BlackJack();
    HorseRace horseClass = new HorseRace();
    int slots = 1, roulette = 2, blackjack = 3, horseRacing = 4;    //integer assigned values for each game.
    boolean userExists, aiEnabled = false; //boolean to check if AI is Enabled and if least one user exists
    private ArrayList<CasinoMembers> allCasinoPlayers = new ArrayList<>(); //array of ALL users within the casino!
    private Player currentPlayer; //current player property
    //endregion

    //region USER LOGIC
    public void createUser(){
        //create user functionality
        String username = UI.createUser(doesUserExists(),allCasinoPlayers); //username prompt
        if(username != null) {
            Player newPlayer = new Player(username); //create new player
            setCurrentPlayer(newPlayer); //set new player to current player
            allCasinoPlayers.add(newPlayer); //add new player to array
            setUserExists(true); //least one user exists
        }

    }
    public void deleteUser(){
        allCasinoPlayers = UI.deleteUser(allCasinoPlayers, getCurrentPlayer());
        doesUserExists();
    }
    public void deleteUser(CasinoMembers player){
        //overloaded deleteUser method to delete a SPECIFIC user as requested in console.
        if(player.equals(getCurrentPlayer())){
            setCurrentPlayer(null);
            allCasinoPlayers.remove(player);
            if(doesUserExists()){
                changeUser();
            } else {
                createUser();
            }
        } else {
            allCasinoPlayers.remove(player);
        }
        doesUserExists();
    }
    public void changeUser(){
        if(doesUserExists()) {
            do {
                setCurrentPlayer((Player) UI.changeUser(allCasinoPlayers, getCurrentPlayer()));
            } while (UI.changeUser(allCasinoPlayers, getCurrentPlayer()) == null);
        } else {
            createUser();
        }
    }
    public void canUserPlay(){
        //checks if the user has become bankrupt
        for (int i = 0; i < allCasinoPlayers.size(); i++) {
            //loops through all casino members, if AI and is bankrupt, delete AI.
            if(allCasinoPlayers.get(i).isAI()){
                if(allCasinoPlayers.get(i).getCurrentMoneyCount() <= 0){
                    UI.deleteAI(allCasinoPlayers.get(i).getName());
                    allCasinoPlayers.remove(allCasinoPlayers.get(i));
                }
            }
        }
        if(getCurrentPlayer().getCurrentMoneyCount() <= 0){
            UI.bankruptUser(); //if so, call UI dependent text
            deleteUser(getCurrentPlayer()); //delete the user
        }
    }
    public int getUserBet(){
        //gets user bet UI text
        return UI.getUserBet(getCurrentPlayer().getCurrentMoneyCount());
    }
    //endregion

    //region CURRENT-PLAYER LOGIC
    public Player getCurrentPlayer() {
        //returns the current player ONLY if a player is set to exist, otherwise currentPlayer is set to NULL.
        if(!doesUserExists()){
            setCurrentPlayer(null);
        }
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        //updates current player
    }
    public boolean doesUserExists() {
        //loops through array to check if exists, if null = false, if once true, stop loop and return value true;
        if(allCasinoPlayers.isEmpty()){
            setUserExists(false);
        } else {
            setUserExists(false);
            for (CasinoMembers allCasinoPlayer : allCasinoPlayers) {
                if (!allCasinoPlayer.isAI()) {
                    //if user is NOT AI, set true and immediately break as least one user exists
                    setUserExists(true);
                    return userExists;
                }
            }
        }
        return userExists;
    }
    public void setUserExists(boolean oneUserExists) {
        this.userExists = oneUserExists;
    }
    //endregion

    //region AI LOGIC
    public int getAIBet(CasinoMembers AI) {
        //gets an AI randomized bet with a low min of 1-100 and a high min of their total money count
        int lowBet = 1;
        int highBet = (AI.getCurrentMoneyCount() - 1);
        int bet;
        //if checks to ENSURE AI bet is not 'broken' before returned
        if (lowBet > highBet) {
            bet = ProbabilityForValue.randomValues(highBet, lowBet);
        } else {
            bet = ProbabilityForValue.randomValues(lowBet,highBet);
        }
        return bet;
    }
    public void playAI(int game){
        //simulates AI playing the game by looping through every index of array and plays if AI (is AI is inherently enabled)
        if(aiEnabled) {
            for (int i = 0; i < allCasinoPlayers.size() - 1; i++) {
                if (allCasinoPlayers.get(i).isAI()) {
                    canUserPlay();
                    if(allCasinoPlayers.get(i) != null){
                        switch (game) {
                            case 1: //slots
                                slotsClass.play(allCasinoPlayers.get(i), getAIBet(allCasinoPlayers.get(i)), true);
                                break;
                            case 2: //roulette
                                rouletteClass.play(allCasinoPlayers.get(i), getAIBet(allCasinoPlayers.get(i)), true);
                                break;
                            case 3: //black-jack
                                bjClass.play(allCasinoPlayers.get(i), getAIBet(allCasinoPlayers.get(i)), true);
                                break;
                            case 4: //horse-racing
                                horseClass.play(allCasinoPlayers.get(i), getAIBet(allCasinoPlayers.get(i)), true);
                                break;
                        }
                    }
                } else {
                    i++;
                }
            }
        }
    }
    public void populateAI(){
        this.aiEnabled = !aiEnabled; //flip the boolean around
        //populates array with "AI" or fake players that have their values randomizes
        UI.populateAI(aiEnabled);
        if(!aiEnabled){ //if boolean is false, delete AI members
            for (int i = 0; i < allCasinoPlayers.size(); i++) {
                if (allCasinoPlayers.get(i).isAI()){
                    UI.populateAIPrompt(aiEnabled, allCasinoPlayers.get(i).getName());
                    //calls UI with boolean to check if AI is being enabled or disabled, as well as the string name
                    allCasinoPlayers.remove(i);
                    i--;
                }
            }
        } else { //add new AI members
            int totalAI = ProbabilityForValue.randomValues(1,10);
            //randomizes ai values between 1 or 10 random ai
            for (int i = 0; i < totalAI; i++) {
                CasinoMembers playerAI = new CasinoAI();
                allCasinoPlayers.add(playerAI);
                UI.populateAIPrompt(aiEnabled,  playerAI.getName());
            }
        }
    }
    //endregion

    //region GAME LOGIC & OUTPUT
    public void gameOutput(int game){
        // do while loop for selected game's menu prompts
        canUserPlay();
        switch(game){ //switch for game int provided on casinoOutput function
            case 1: //slots
                do {
                    canUserPlay();
                    UI.displayGameHeader(slots, getCurrentPlayer());
                    switch (UI.gamePrompt()) { //nested switch for slot chosen by gameOption
                        case 1: // slots play option
                            setCurrentPlayer((Player) slotsClass.play(getCurrentPlayer(), getUserBet(),false));
                            UI.displayFinalMoney(getCurrentPlayer(),slots);
                            playAI(slots);
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
                    UI.displayGameHeader(roulette, getCurrentPlayer());
                    switch (UI.gamePrompt()) { //nested switch for roulette chosen by gameOption
                        case 1: // roulette play option
                            setCurrentPlayer((Player) rouletteClass.play(getCurrentPlayer(), getUserBet(),false));
                            UI.displayFinalMoney(getCurrentPlayer(),roulette);
                            playAI(roulette);
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
                    UI.displayGameHeader(blackjack, getCurrentPlayer());
                    switch (UI.gamePrompt()) { //nested switch for black-jack chosen by gameOption
                        case 1: // black-jack play option
                            setCurrentPlayer((Player) bjClass.play(getCurrentPlayer(), getUserBet(),false));
                            UI.displayFinalMoney(getCurrentPlayer(),blackjack);
                            playAI(blackjack);
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
                    UI.displayGameHeader(horseRacing, getCurrentPlayer());
                    switch (UI.horseRacingPrompt()) { //nested switch for horse racing chosen by gameOption
                        case 1: // horse-racing play option
                            setCurrentPlayer((Player) horseClass.play(getCurrentPlayer(), getUserBet(),false));
                            UI.displayFinalMoney(getCurrentPlayer(),horseRacing);
                            playAI(horseRacing);
                            break;
                        case 2:
                            break;
                        case 3: // horse-: // horse names
                            horseClass.populatingStable();
                            UI.displayStable(horseClass.fullStableOfRacerHorses);
                            break;
                        case 4: // exit
                            return;
                    }
                }while (true);
        }
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
                    case 5: // settings
                        gameSettings();
                        break;
                    case 6: // exit
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
                case 3: //global leaderboard
                    leaderboard(5);
                    break;
                case 4: //exit back to gameoutput
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
                case 4: //list current user
                    UI.displayCurrentUser(getCurrentPlayer());
                    break;
                case 5: //list current users
                    UI.displayAllUsers(allCasinoPlayers, getCurrentPlayer());
                    break;
                case 6: //exit back to casino settings
                    return;
            }
        } while(true);
    }
    public void leaderboard(int game){
        //displays leaderboard depending on the game provided.
        switch(game){
            case 1: //slots leaderboard
                UI.leaderboardHeader(slots);
                allCasinoPlayers.sort(Comparator.comparingInt(CasinoMembers::getTotalSlotMoney).reversed()); //sorts arraylist, compares the int of all totalSlot Money and reverses it
                for (int i = 0; i < allCasinoPlayers.size(); i++) {
                    if(allCasinoPlayers.get(i).getTotalSlotMoney() <= 0){
                        i++;
                    } else {
                        UI.leaderboard(allCasinoPlayers.get(i),allCasinoPlayers.get(i).getTotalSlotMoney(),i); //calls UI inputting currentPlayer, their money, index, and the game type
                    }
                }
                break;
            case 2: //roulette
                UI.leaderboardHeader(roulette);
                allCasinoPlayers.sort(Comparator.comparingInt(CasinoMembers::getTotalRouletteMoney).reversed());
                for (int i = 0; i < allCasinoPlayers.size(); i++) {
                    if(allCasinoPlayers.get(i).getTotalRouletteMoney() <= 0){
                        i++;
                    } else {
                        UI.leaderboard(allCasinoPlayers.get(i),allCasinoPlayers.get(i).getTotalRouletteMoney(),i);
                    }
                }
                break;
            case 3: //black-jack
                UI.leaderboardHeader(blackjack);
                allCasinoPlayers.sort(Comparator.comparingInt(CasinoMembers::getTotalBlackJackMoney).reversed());
                for (int i = 0; i < allCasinoPlayers.size(); i++) {
                    if(allCasinoPlayers.get(i).getTotalBlackJackMoney() <= 0){
                        i++;
                    } else {
                        UI.leaderboard(allCasinoPlayers.get(i),allCasinoPlayers.get(i).getTotalBlackJackMoney(),i);
                    }
                }
                break;
            case 4: //horse-racing
                UI.leaderboardHeader(horseRacing);
                allCasinoPlayers.sort(Comparator.comparingInt(CasinoMembers::getTotalHorseMoney).reversed());
                for (int i = 0; i < allCasinoPlayers.size(); i++) {
                    if(allCasinoPlayers.get(i).getTotalHorseMoney() <= 0){
                        i++;
                    } else {
                        UI.leaderboard(allCasinoPlayers.get(i),allCasinoPlayers.get(i).getTotalHorseMoney(),i);
                    }
                }
                break;
            default: //casino leaderboard
                UI.leaderboardHeader(5);
                allCasinoPlayers.sort(Comparator.comparingInt(CasinoMembers::getTotalWinnings).reversed());
                for (int i = 0; i < allCasinoPlayers.size(); i++) {
                    if(allCasinoPlayers.get(i).getTotalWinnings() <= 0){
                        i++;
                    } else {
                        UI.leaderboard(allCasinoPlayers.get(i),allCasinoPlayers.get(i).getTotalWinnings(),i);
                    }
                }
                break;
        }
        UI.footer(1);
    }
    //endregion
}
