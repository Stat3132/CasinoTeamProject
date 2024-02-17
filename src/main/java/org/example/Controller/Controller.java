package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.View.CasinoInterface;

public class Controller {
    //TODO: method to detect if player exists from an arraylist
    //TODO: logic behind username for players, easter eggs, "guest", "test", "broke", etc.
    //TODO: logic behind PLAY & LEADERBOARD options for each game menu
    
    CasinoInterface UI = new CasinoInterface();
    private boolean userExists;
    public boolean doesUserExists() {
        return userExists;
    }
    public void setUserExists(boolean oneUserExists) {
        this.userExists = oneUserExists;
    }
    //Main method used to display all main information

    public void casinoOutput(){
        // games that are assigned designated integers.
        int slots = 1;
        int roulette = 2;
        int blackjack = 3;
        int horseRacing = 4;

        //do while loop for game menu
        if(doesUserExists()) {
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
            String username = UI.firstUserPrompt();
            //FIXME cannot run until resolve player creation
        }
    }
    public void gameOutput(int game){
        // do while loop for selected game's menu prompts
            switch(game){ //switch for game int provided on casinoOutput function
                case 1: //slots
                    do {
                        System.out.println("\n-- Slots Machine! --");
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
                        System.out.println("\n-- Roulette! --");
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
                        System.out.println("\n-- Black-Jack! --");
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
                        System.out.println("\n-- Horse-Racing! --");
                        switch (UI.horseRacingPrompt()) { //nested switch for horse racing chosen by gameOption
                            case 1: // horse-racing play option
                                //TODO: slots game play
                                break;
                            case 2: // horse-racing leaderboard option
                                //TODO: slots leaderboard
                                break;
                            case 3: // horse names
                                //TODO: horse name list
                                break;
                            case 4: // exit
                                return;
                        }
                    }while (true);
            }
    }


    public void testPerson(){
        CasinoMembers newCasinoAI = new CasinoAI();
        System.out.println(newCasinoAI);
    }
}
