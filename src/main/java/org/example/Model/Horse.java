package org.example.Model;

import org.example.UTIL.Probability;

public class Horse extends CasinoObjects {
    String horseName;
    String horseColor;
    int horseSpeed;
    float horseWeight;

    public Horse(String horseName, String horseColor) {
        this.horseName = horseName;
        this.horseColor = horseColor;
        this.horseSpeed = probably.randomValues(0,10);
        this.horseWeight = probably.randomValues(900,1200);
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
