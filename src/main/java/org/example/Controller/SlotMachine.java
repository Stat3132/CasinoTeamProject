package org.example.Controller;

public class SlotMachine implements Casino {
    //TODO:

    /**
     * 10 for base then we will have x 10 x 100 and x 1000.
     * This is the base amount so 10, 100, 1000, 10,000 so slots go as so
     * 4 of a kind will give you your base value maybe, i actually cant make SUDO values as i don't know the complete logic for it
     *
     * @return
     */
    @Override
    public int betAmount() {
        return 0;
    }
    @Override
    public int cashOut(int betType, int playerBet) {
        return 0;
    }
    @Override
    public int jackPot() {
        return 0;
    }
    @Override
    /**
     * Implementation of all previously established methods.
     */
    public void play() {

    }
}
