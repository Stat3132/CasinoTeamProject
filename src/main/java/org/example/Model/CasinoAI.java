package org.example.Model;

import org.example.UTIL.ProbabilityForValue;

public class CasinoAI extends CasinoMembers{
    /**
     * This is setting values to AI. AI is not a player, so they have no right to alter values. They are set for them. Here I call name
     * and randomize it ,and then I just establish total winnings to a bot, so it gives life to the casino. As it is an ever moving game rather
     * than just the player being the only moving cog in the casino.
     */

    //region CONSTRUCTOR
    public CasinoAI(){
        //constructor for AI users, sets randomized name and randomized money values
        super(" ",ProbabilityForValue.randomValues(100,500));
        this.setAI(true);
    }
    //endregion
}
