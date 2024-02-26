package org.example.UTIL;

import javax.xml.namespace.QName;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Card {
   protected CardValue[] allCardValues = new CardValue[13];
   protected Suit[] allSuits = new Suit[4];
   String face;
   int suit;

   public Card(CardValue face, Suit suit){

   }
   public void populateDeck(){
       int suitCounter = 0;
       for (int i = 0; i < 100 ; i++) {
           Card newCard = new Card(allCardValues[i],allSuits[suitCounter]);
           System.out.println(newCard);
           if (suitCounter == 3 && i == 12){
              return;
           }
           if (i == 12){
               i = 0;
               suitCounter++;
           }
       }
   }
    //Makes array of cardValues and Suits
    public void makingEnumArrays() {
        CardValue[] intForCardValues = CardValue.values();
        Suit[] intForSuits = Suit.values();
        for (int i = 0; i < allCardValues.length; i++) {
            if (allCardValues[i] == null) {
                allCardValues[i] = intForCardValues[i];
            }
            if (i == intForCardValues.length - 1) {
                break;
            }
        }
        for (int i = 0; i < allSuits.length; i++) {
            if (allSuits[i] == null) {
                allSuits[i] = intForSuits[i];
            }
            if (i == allSuits.length - 1) {
                break;
            }
        }
        System.out.println(allCardValues[12]);
        System.out.println(allSuits[3]);
    }

    @Override
    public String toString() {
        return "Card{" +
                "face='" + face + '\'' +
                ", suit=" + suit +
                '}';
    }
}
