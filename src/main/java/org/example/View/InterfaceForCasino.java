package org.example.View;

import org.example.UTIL.MyIo;

public class InterfaceForCasino {
    MyIo casinoIO = new MyIo();

    public int firstPrompt(){
       return casinoIO.getUsersInt(true,"Welcome to the casino! \n\n\n 1) Enter information \n\n 2) Sign up as guest \n\n" +
               "(If signed up as guest the amount of games you can play is LIMITED)");
    }
    public int casinoInterfacePrompt(){
       return casinoIO.getUsersInt(true, " 1) Slot machines \n 2) Roulette \n 3) Black jack \n 4) Horse racing \n 5) Casino info \n 6) Exit");
    }
    public void slotMachinePrompt(){

    }
    public void RoulettePrompt(){

    }
    public void horseRacingPrompt(){

    }
    public void blackJackPrompt(){

    }
    public void exitPrompt(){
        System.out.println("Good bye");
    }

}
