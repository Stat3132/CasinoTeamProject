package org.example.Controller;

import org.example.UTIL.Probability;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public interface Casino {
    int betAmount();
    int cashOut(int betType, int playerBet);
    int jackPot();
    void play();

}
