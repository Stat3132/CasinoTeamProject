package org.example;

import org.example.Controller.Controller;
import org.example.Controller.Roulette;
import org.example.Controller.SlotMachine;
import org.example.Model.Player;
import org.example.UTIL.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Console.write("WELCOME to \"Variables in Vegas\"!\n",Console.TextColor.GREEN); //header for the entire game
        new Controller().casinoOutput(); //calls controller and initializes the game
   //     new Card(CardValue.EIGHT, Suit.CLUB).populateDeck();
    }
}
