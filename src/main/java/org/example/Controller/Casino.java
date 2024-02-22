package org.example.Controller;

import org.example.Model.Player;

public interface Casino {
    /** Bet amount info:
     * Bet amount is the bet amount as all games need to bet money.
     * @return
     */
    int betAmount(Player currentPlayer);

    /**
     * Cash out is the money received if they win the game. This method will be different for all other classes so its in an interface
     * @param betType
     * @param playerBet
     * @return
     */
    int cashOut(int betType, int playerBet, Player currentPlayer);

    /**
     * Jack pot is the BIG WIN for most of the games. Each of them have different ways to get to this method while some (like horse racing) have no implementation of it.
     * @return
     */
    int jackPot();

    /**
     * Play method is the actual playing of the game. After all logic has been inputted it will go through checks and validation and make sure the game runs how it should. Keep in mind NO UI
     * should be displayed in this or any Controller method.
     */
    void play(Player currentPlayer);

    /**
     * Odds and probability are different for every game. By returning a set "odd" for the games it will make it easier to move through our code and this method will coincide with
     * the play method above. This value will be a big validator for much of the logic in Play.
     * @return
     */
    int whatAreTheOdds();

}
