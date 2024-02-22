package org.example.Controller;

import org.example.Model.Player;

public class SlotMachine implements Casino {
    //TODO:

    /**
     * 10 for base then we will have x 10 x 100 and x 1000.
     * This is the base amount so 10, 100, 1000, 10,000 so slots go as so
     * 4 of a kind will give you your base value maybe, I actually cant make SUDO values as I don't know the complete logic for it
     *
     * @return
     */
    @Override
    public void cashOut(int playerBet, Player currentPlayer) {
    }
    @Override
    public int jackPot() {
        return 0;
    }
    @Override
    /**
     * Implementation of all previously established methods.
     */
    public void play(Player currentPlayer) {

    }

    @Override
    public int whatAreTheOdds() {
        return 0;
    }
}
