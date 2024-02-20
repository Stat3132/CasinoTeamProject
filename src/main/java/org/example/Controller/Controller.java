package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.Model.Player;
import org.example.UTIL.Console;
import org.example.View.CasinoInterface;

import java.util.ArrayList;

public class Controller {
    //TODO: method to detect if player exists from an arraylist
    //TODO: logic behind username for players, easter eggs, "guest", "test", "broke", etc.
    //TODO: logic behind PLAY & LEADERBOARD options for each game menu
    //GAME INSTANCES:
    HorseRace horseRaceControl = new HorseRace();
    CasinoInterface UI = new CasinoInterface();
    private boolean userExists;
    ArrayList<CasinoMembers> allCasinoPlayers = new ArrayList<>(); //array of ALL users within the casino!
    Player currentPlayer;

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
    public void createFirstUser(){
        String username = UI.firstUserPrompt();
        Player newPlayer = new Player(username);
        currentPlayer = newPlayer;
        allCasinoPlayers.add(newPlayer);
        setUserExists(true);
        System.out.println(username + " Has been created!");
    }
    public void populateAI(){
        //populates array with "AI" or fake players that have their values randomizes
    }
    //Main method used to display all main information

    public void casinoOutput(){
        // games that are assigned designated integers.
        int slots = 1;
        int roulette = 2;
        int blackjack = 3;
        int horseRacing = 4;
        //FIXME:

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
                        break;
                    case 6: //exit
                        UI.exitPrompt();
                        return;
                }
            } while (true);
        } else {
            // creates a user as no user has been detected within the array list
            createFirstUser();
            casinoOutput(); //recalls the method to loop
        }
    }
    public void gameOutput(int game){
        // do while loop for selected game's menu prompts
            switch(game){ //switch for game int provided on casinoOutput function
                case 1: //slots
                    do {
                        Console.write("-- Slots! --\n", Console.TextColor.BLUE);
                        switch (UI.gamePrompt()) { //nested switch for slot chosen by gameOption
                            case 1: // slots play option
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
                        Console.write("-- Roulette! --\n", Console.TextColor.RED);
                        switch (UI.gamePrompt()) { //nested switch for roulette chosen by gameOption
                            case 1: // roulette play option
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
                        Console.write("-- Black-Jack! --\n", Console.TextColor.PURPLE);
                        switch (UI.gamePrompt()) { //nested switch for black-jack chosen by gameOption
                            case 1: // black-jack play option
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
                        Console.write("-- Horse-Racing! --\n", Console.TextColor.CYAN);
                        switch (UI.horseRacingPrompt()) { //nested switch for horse racing chosen by gameOption
                            case 1: // horse-racing play option
                                //TODO: horse race game play
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

}
