package org.example.Controller;

import org.example.Model.CasinoAI;
import org.example.Model.CasinoMembers;
import org.example.View.InterfaceForCasino;

public class MyController {

    InterfaceForCasino UICasino = new InterfaceForCasino();
    //Main method used to display all main information

    public void casinoOutput(){
        do {
            int gameChoosen = UICasino.casinoInterfacePrompt();
            switch (gameChoosen) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    UICasino.exitPrompt();
                    return;
            }
        } while (true);

    }


    public void testIng(){
        CasinoMembers newCasinoAI = new CasinoAI();
        System.out.println(newCasinoAI);
    }
}
