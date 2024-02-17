package org.example.Model;

public class CasinoAI extends CasinoMembers{

    public CasinoAI(){
        super("",1);
        this.name = randomizingNames().toString();
        this.totalWinnings = probably.randomValues(50,100);
    }
    public CasinoAI(String name,  int currentMoneyCount) {
        super(name, currentMoneyCount);
    }
}
