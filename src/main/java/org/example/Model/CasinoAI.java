package org.example.Model;

public class CasinoAI extends CasinoMembers{

    public CasinoAI(){
        this("",1,1,1,1,1);
        this.name = randomizingNames();
        this.totalWinnings = probably.randomValues(50,100);
    }
    public CasinoAI(String name, int totalWinnings, int currentMoneyCount, int gamesPlayed, int gamesWon, int gamesLost) {
        super(name, totalWinnings, currentMoneyCount, gamesPlayed, gamesWon, gamesLost);
    }
}
