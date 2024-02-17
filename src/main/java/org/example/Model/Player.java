package org.example.Model;

public class Player extends CasinoMembers{
    //FIXME:
    public Player(String name, int totalWinnings, int currentMoneyCount, int gamesPlayed, int gamesWon, int gamesLost) {
        super(name, totalWinnings, currentMoneyCount, gamesPlayed, gamesWon, gamesLost);
    }
    public Player(String name){
        super(name,0,0,0,0,0);

    }
    public Player(){
        this("NAME",1,1,1,1,1);
    }

}
