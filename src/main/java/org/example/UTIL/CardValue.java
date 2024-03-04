package org.example.UTIL;

public enum CardValue {

    //region ENUM VALUES
    //enums for card values for blackjack
    ACE(11, "ACE"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "JACK"),
    QUEEN(10, "Queen"),
    KING(10, "King");
    int cardCount;
    final String friendlyString;

    //endregion

    //region CONSTRUCTOR & TOSTRING

    public int getCardCount() {
        return cardCount;
    }

    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }

    CardValue(int cardCount, String friendlyString) {
        this.cardCount = cardCount;
        this.friendlyString = friendlyString;
    }


    @Override
    public String toString() {
        return friendlyString;
    }
    //endregion
}
