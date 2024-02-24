package org.example;

import org.example.Controller.Controller;
import org.example.Model.Player;
import org.example.UTIL.Console;
public class Main {
    public static void main(String[] args) {
        Console.write("WELCOME to \"Variables in Vegas\"!\n",Console.TextColor.GREEN); //header for the entire game
        new Controller().casinoOutput(); //calls controller and initializes the game
    }
}
