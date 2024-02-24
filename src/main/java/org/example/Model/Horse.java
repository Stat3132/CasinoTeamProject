package org.example.Model;

import org.example.UTIL.ProbabilityForValue;

public class Horse extends CasinoMembers {
    String horseName, horseColor;
    int horseSpeed;
    //FIXME: CHANGE THIS TO INT (No need for float)
    float horseWeight;
    int horseOdds;

    /**
     * Horse object is created by the game and not by the player. There can be future implementation for adding horses but that
     * would just use another constructor. That takes in values given from the user.
     */
    public Horse() {
        //FIXME
        super("TEST",1);
        this.horseName = randomizingNames().toString();
        this.horseColor = randomizeHorseColor().toString();
        this.horseSpeed = ProbabilityForValue.randomValues(1,10);
        this.horseWeight = ProbabilityForValue.randomValues(900,1200);
    }

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

    //TO STRING
    @Override
    public String toString() {
        String horseBuilder = "\t\tHorse name: " + horseName +
                "\nHorse coat color: " + horseColor +
                "\nHorse speed: " + horseSpeed +
                "\nHorse weight: " + horseWeight +
                "\n" +
                //FIXME: FOR TESTING ONLY!!!!!!!!
                "Horses odds: " + horseOdds +
                "\n" + "---------------";
       return horseBuilder;
    }
}
