package org.example;

import org.example.Controller.HorseRace;
import org.example.Controller.Controller;
import org.example.Model.Horse;
import org.example.UTIL.Console;

public class Main {
    public static void main(String[] args) {
//        new Controller().testPerson();
      new HorseRace().play();
        Console.write("WELCOME to \"Variables in Vegas\"!\n",Console.TextColor.GREEN);
        new Controller().casinoOutput();
    }
}
