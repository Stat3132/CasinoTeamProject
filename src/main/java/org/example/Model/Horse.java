package org.example.Model;

import org.example.UTIL.Probability;

public class Horse extends CasinoMembers {
    String horseName, horseColor;
    int horseSpeed;
    float horseWeight;

    public Horse() {
        //FIXME
        super("TEST",1);
        this.horseName = randomizingNames().toString();
        this.horseColor = randomizeHorseColor().toString();
        this.horseSpeed = probably.randomValues(0,10);
        this.horseWeight = probably.randomValues(900,1200);
    }

    public int getHorseSpeed() {
        return horseSpeed;
    }

    public void setHorseSpeed(int horseSpeed) {
        this.horseSpeed = horseSpeed;
    }

    public float getHorseWeight() {
        return horseWeight;
    }

    public void setHorseWeight(float horseWeight) {
        this.horseWeight = horseWeight;
    }

    @Override
    public String toString() {
        StringBuilder horseBuilder = new StringBuilder();
       horseBuilder.append("\t\tHorse name: ").append(horseName);
       horseBuilder.append("\nHorse coat color: ").append(horseColor);
       horseBuilder.append("\nHorse speed: ").append(horseSpeed);
       horseBuilder.append("\nHorse weight: ").append(horseWeight);
       return horseBuilder.toString();
    }
}
