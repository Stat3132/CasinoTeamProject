package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Horse;

public class HorseRace implements Casino {
    Horse fixedHorse = new Horse();
    Horse[] racingHorses = new Horse[20];
    int horseOdds;
    //TODO:

    /**
     * For horse racing it will be base $100 entry, with x2 and x3 multipliers.
     *
     * @return
     */
    @Override
    public int betAmount() {
        //TODO: SUDO CODE
        //FIXME: CODE IS NOT DONE!!!
        int playerBet = 100;
        switch (playerBet) {
            case 100:
                playerBet = playerBet - 100;
                break;
            case 200:
                playerBet = playerBet - 200;
                break;
            case 300:
                playerBet = playerBet - 300;
                break;
        }
        return playerBet;
    }

    /**
     * Cash out is how much money you will get back from it. There is no jack pot for horse racing.So for cash out I think I will keep it simple,
     * It will just be x 2 for the horse that won. There can be more complexity but this is just a rough draft of what these classes ARE.
     *
     * @param betType
     * @param playerBet
     * @return
     */
    @Override
    public int cashOut(int betType, int playerBet) {
        return 0;
    }

    /**
     * NO JACKPOT CONSIDERING ALTERNATIVES!!!
     *
     * @return
     */
    @Override
    public int jackPot() {
        return 0;
    }

    /**
     * Populating a stable. Starting at 20 horses then maybe in future implementation maybe the ability to add horses.
     * We begin with 20 horses displayed
     */
    public void populatingStable() {
        for (int i = 0; i < racingHorses.length; i++) {
            racingHorses[i] = new Horse();
            System.out.println(racingHorses[i].toString());
        }
    }

    /**
     * This is the play method that will be where bet amount, cash and populatedStable will all be in the play method. Instilling LOGIC as this is a controller,
     * an instiller of logic.
     */
    @Override
    public void play() {


    }

    @Override
    public int whatAreTheOdds() {
        switch (fixedHorse.getHorseSpeed()) {
            case 1:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 1;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 2;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 3;
                }
                break;
            case 2:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 4;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 5;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 6;
                }
                break;
            case 3:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 7;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 8;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 9;
                }
                break;
            case 4:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 10;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 11;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 12;
                }
                break;
            case 5:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 13;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 14;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 15;
                }
                break;
            case 6:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 16;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 17;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 18;
                }
                break;
            case 7:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 19;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 20;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 21;
                }
                break;
            case 8:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 22;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 23;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 24;
                }
                break;
            case 9:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 25;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 26;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 27;
                }
                break;
            case 10:
                if (fixedHorse.getHorseWeight() >= 1100) {
                    horseOdds = 28;
                }
                if (fixedHorse.getHorseWeight() >= 1000 && fixedHorse.getHorseWeight() < 1100) {
                    horseOdds = 29;
                }
                if (fixedHorse.getHorseWeight() >= 900 && fixedHorse.getHorseWeight() < 1000){
                    horseOdds = 30;
                }
                break;
        }
        return 0;
    }
}
