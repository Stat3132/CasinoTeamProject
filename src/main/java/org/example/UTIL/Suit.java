package org.example.UTIL;

public enum Suit {
    SPADE("Spade"),
    CLUB("Club"),
    HEART("Heart"),
    DIAMOND("Diamond");
    String friendlyString;

    Suit(String friendlyString) {
        this.friendlyString = friendlyString;
    }

    @Override
    public String toString() {
        return friendlyString;
    }
}
