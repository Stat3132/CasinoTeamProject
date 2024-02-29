package org.example.Model;

import org.example.UTIL.ProbabilityForValue;

public class Horse extends CasinoMembers {

    //region VARS
    String horseName, horseColor;
    int horseSpeed, horseOdds;
    float horseWeight;
    //endregion

    //region GETTERS & SETTERS
    public int getHorseSpeed() {
        return horseSpeed;
    }
    private void setHorseSpeed(int horseSpeed) {
        this.horseSpeed = horseSpeed;
    }

    public float getHorseWeight() {
        return horseWeight;
    }
    private void setHorseWeight(float horseWeight) {
        this.horseWeight = horseWeight;
    }

    public int getHorseOdds() {
        return horseOdds;
    }
    public void setHorseOdds(int horseOdds) {
        this.horseOdds = horseOdds;
    }
    //endregion

    //region CONSTRUCTOR & TOSTRING
    /**
     * Horse object is created by the game and not by the player. There can be future implementation for adding horses but that
     * would just use another constructor. That takes in values given from the user.
     */
    public Horse() {
        super("TEST",1);
        this.horseName = randomizingNames().toString();
        this.horseColor = randomizeHorseColor().toString();
        this.horseSpeed = ProbabilityForValue.randomValues(1,10);
        this.horseWeight = ProbabilityForValue.randomValues(900,1200);
    }

    @Override
    public String toString() {
        return  "Horse NAME: \"" + horseName + "\"" +
                "\nHorse COLOR: " + horseColor +
                "\nHorse SPEED: " + horseSpeed +
                "\nHorse WEIGHT: " + horseWeight +
                "\nHorses ODDS: " + horseOdds;
    }
    //endregion
}
