package org.example.Model;

import org.example.UTIL.ProbabilityForValue;

public class Player extends CasinoMembers{
    //player class that extends from CasinoMembers
    int defaultMoney = 500; //every default player has $500 default money
    String[] names = {"poor","broke","rich","wealthy","test"}; //array for custom easter egg names that modify the values of players

    //Constructor for players, can modify name &/or money
    public Player(String name){
        super(name,0);
        checkName(name);
    }

    public void checkName(String _name){
        //simple test functionality for Easter egg names
        for (int i = 0; i < names.length; i++) {
            if(_name.equalsIgnoreCase(names[0]) || _name.equalsIgnoreCase(names[1])){
                //broke money values
                this.setCurrentMoneyCount(ProbabilityForValue.randomValues(1,100));
                break;
            } else if (_name.equalsIgnoreCase(names[2]) || _name.equalsIgnoreCase(names[3])) {
                //rich money values
                this.setCurrentMoneyCount(ProbabilityForValue.randomValues(1000,100000));
                break;
            } else if(_name.equalsIgnoreCase(names[4])){
                //tester money values
                this.setCurrentMoneyCount(999999999);
                this.setTotalSlotMoney(999999999);
                this.setTotalRouletteMoney(999999999);
                this.setTotalBlackJackMoney(999999999);
                this.setTotalHorseMoney(999999999);
                this.setTotalWinnings(999999999);
                break;
            } else {
                //else default money value
                this.setCurrentMoneyCount(defaultMoney);
                this.setTotalSlotMoney(0);
                this.setTotalRouletteMoney(0);
                this.setTotalBlackJackMoney(0);
                this.setTotalHorseMoney(0);
                this.setTotalWinnings(0);
                }
        }
    }

}
