package org.example.UTIL;

public enum Suit {
    //enum values for suits for cards.
    SPADE("Spade"),
    CLUB("Club"),
    HEART("Heart"),
    DIAMOND("Diamond");

    //toString
    String friendlyString;
    @Override
    public String toString() {
        return friendlyString;
    }
    //constructor
    Suit(String friendlyString) {
        this.friendlyString = friendlyString;
    }
}
