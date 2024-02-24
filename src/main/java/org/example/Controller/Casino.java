package org.example.Controller;

import org.example.Model.Player;

public interface Casino {

    void cashOut(Player currentPlayer, int playerBet); //money received when player has won the game.
    void canUserPlay(Player player, int playerBet); //checks if a user can play based off of their money count
    void play(Player currentPlayer, int playerBet); //actual play method of the game, takes in a player as well as the playerbet and returns the new bet amount after game logic
}
