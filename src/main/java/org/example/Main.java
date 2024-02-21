package org.example;

import org.example.Controller.HorseRace;
import org.example.Controller.Controller;
import org.example.Model.Horse;
import org.example.Model.Player;
import org.example.UTIL.Console;

import javax.print.attribute.standard.PrinterMakeAndModel;

public class Main {
    public static void main(String[] args) {
        Player randomPerson = new Player("Diego");
//        new Controller().testPerson();
          new HorseRace().play(randomPerson);
//        Console.write("WELCOME to \"Variables in Vegas\"!\n",Console.TextColor.GREEN);
//        new Controller().casinoOutput();
    }
}
