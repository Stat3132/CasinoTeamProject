package org.example.Controller;

import org.example.Model.Player;

public interface Casino {
    Player play(Player currentPlayer, int playerBet); //actual play method of the game, takes in a player as well as the playerbet and returns the new bet amount after game logic
    Player cashOut(Player currentPlayer, int playerBet);
}
