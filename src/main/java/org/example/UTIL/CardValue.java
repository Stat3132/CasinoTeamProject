package org.example.UTIL;

public enum CardValue {
    //region ENUM VALUES
    //enums for card values for blackjack
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    ELEVEN("Jack"),
    TWELVE("Queen"),
    THIRTEEN("King");
    //endregion

    //region CONSTRUCTOR & TOSTRING
    CardValue(String friendlyString) {
        this.friendlyString = friendlyString;
    }
    final String friendlyString;
    @Override
    public String toString() {
        return friendlyString;
    }
    //endregion
}
