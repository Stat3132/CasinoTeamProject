package org.example.Model;

public class Player extends CasinoMembers{

    int defaultMoney = 500;
    int totalHorseMoney;
    int totalRouletteMoney;
    int totalSlotMoney;
    int totalBlackJackMoney;

    /**
     * Here we are setting some values that the player will begin with. These values are set by the user.
     * @param name
     * @param currentMoneyCount
     */
    //TODO: ONE CONSTRUCTOR TAKING IN 2 PARAMETERS AND ONE WITH JUST ONE PARAMETER.
    public Player(String name, int currentMoneyCount) {
        super(name, currentMoneyCount);
    }

    public Player(String name){
        super(name,0);
        this.setCurrentMoneyCount(defaultMoney);
    }

}
