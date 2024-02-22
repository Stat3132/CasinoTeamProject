package org.example;

import org.example.Controller.Controller;
import org.example.Model.Player;
import org.example.UTIL.Console;
public class Main {
    public static void main(String[] args) {
        Player randomPerson = new Player("Diego");
//        new Controller().testPerson();
//          new HorseRace().play(randomPerson);
        Console.write("WELCOME to \"Variables in Vegas\"!\n",Console.TextColor.GREEN);
        new Controller().casinoOutput();
    }
}
