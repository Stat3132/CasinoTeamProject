package org.example.Model;

public class Player extends CasinoMembers{

    int defaultMoney = 500;

    public Player(String name, int currentMoneyCount) {
        super(name, currentMoneyCount);
    }
    public Player(String name){
        super(name,0);
        this.setCurrentMoneyCount(defaultMoney);
    }

}
