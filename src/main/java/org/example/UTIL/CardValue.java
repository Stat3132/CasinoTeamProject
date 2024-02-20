package org.example.UTIL;

public enum CardValue {

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
    ELEVEN("Joker"),
    TWELVE("Queen"),
    THIRTEEN("King");

    String friendlyString;

    CardValue(String friendlyString) {
        this.friendlyString = friendlyString;
    }
}
