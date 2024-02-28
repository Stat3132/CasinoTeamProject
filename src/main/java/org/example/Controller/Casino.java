package org.example.Controller;

import org.example.Model.CasinoMembers;
import org.example.Model.Player;

public interface Casino {
    CasinoMembers play(CasinoMembers currentPlayer, int playerBet, boolean isAI); //actual play method of the game, takes in a player as well as the playerbet and returns the new bet amount after game logic
    CasinoMembers cashOut(CasinoMembers currentPlayer, int playerBet);
}
