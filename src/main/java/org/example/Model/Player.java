package org.example.Model;

public class Player extends CasinoMembers{
    //player class that extends from CasinoMembers
    int defaultMoney = 500; //every default player has $500 default money

    //Constructor for players, can modify name &/or money
    public Player(String name, int playerMoney) {
        super(name, playerMoney);
    }
    public Player(String name){
        super(name,0);
        this.setCurrentMoneyCount(defaultMoney);
        this.setTotalWinnings(0);
        this.setTotalSlotMoney(0);
        this.setTotalRouletteMoney(0);
        this.setTotalBlackJackMoney(0);
        this.setTotalHorseMoney(0);
    }

}
