package org.example.Model;

import org.example.UTIL.ProbabilityForValue;

public class CasinoAI extends CasinoMembers{


    /**
     * This is setting values to AI. AI is not a player, so they have no right to alter values. They are set for them. Here I call name
     * and randomize it ,and then I just establish total winnings to a bot, so it gives life to the casino. As it is an ever moving game rather
     * than just the player being the only moving cog in the casino.
     */
    public CasinoAI(){
        super(" ",1);
        this.setCurrentMoneyCount(ProbabilityForValue.randomValues(500,2000));
        this.setAI(true);
    }

    /**
     * This is something
     * @param name
     * @param currentMoneyCount
     */
    public CasinoAI(String name,  int currentMoneyCount) {
        super(name, currentMoneyCount);
    }
}
