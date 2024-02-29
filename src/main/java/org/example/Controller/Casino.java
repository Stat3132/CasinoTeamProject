package org.example.Controller;

import org.example.Model.CasinoMembers;

public interface Casino {
    //region INTERFACE METHODS

    /**
     * This method runs each class and implements the required logic to return a bet based off of the game that i sran
     * @param currentPlayer player that must be passed in.
     * @param playerBet playerBet that is grabbed from the player and returns more or less money based off of a win value
     * @param isAI AI determination for UI related prompts
     * @return new Player to be replaced.
     */
    CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI); //actual play method of the game, takes in a player as well as the playerbet and returns the new bet amount after game logic
    CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet);
    //endregion
}
