package org.example.UTIL;

public enum Suit {

    //region ENUM VALUES (SUITS)
    //enum values for suits for cards.
    SPADE("Spade"),
    CLUB("Club"),
    HEART("Heart"),
    DIAMOND("Diamond");
    //endregion

    //region CONSTRUCTOR & TOSTRING


    Suit(String friendlyString) {
        this.friendlyString = friendlyString;
    }
    String friendlyString;
    @Override
    public String toString() {
        return friendlyString;
    }
    //endregion
}
