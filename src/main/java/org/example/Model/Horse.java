package org.example.Model;

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
        this.horseSpeed = probably.randomValues(1,10);
        this.horseWeight = probably.randomValues(900,1200);
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
        StringBuilder horseBuilder = new StringBuilder();
       horseBuilder.append("\t\tHorse name: ").append(horseName);
       horseBuilder.append("\nHorse coat color: ").append(horseColor);
       horseBuilder.append("\nHorse speed: ").append(horseSpeed);
       horseBuilder.append("\nHorse weight: ").append(horseWeight);
       horseBuilder.append("\n");
       //FIXME: FOR TESTING ONLY!!!!!!!!
        horseBuilder.append("Horses odds: ").append(horseOdds);
       return horseBuilder.toString();
    }
}
