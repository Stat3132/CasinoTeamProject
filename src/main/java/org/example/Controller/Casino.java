package org.example.Controller;

import org.example.UTIL.Probability;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Casino {
    /** Bet amount info:
     * Bet amount is the bet amount as all games need to bet money.
     * @return
     */
    int betAmount();

    /**
     * Cash out is cashing out if they win the game. This method will be different for all other classes so its in a interface
     * @param betType
     * @param playerBet
     * @return
     */
    int cashOut(int betType, int playerBet);

    /**
     * Jack pot as many games have jack pots for certain criterias in their games.
     * @return
     */
    int jackPot();

    /**
     * This is for all other games, as they will be able to play and will encapsulate all these other methods.
     */
    void play();

}
