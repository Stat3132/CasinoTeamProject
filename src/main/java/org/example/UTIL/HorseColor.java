package org.example.UTIL;

public enum HorseColor {
    PALOMINO("Palimino"),
    BLACK("Black"),
    GRAY("Grey"),
    BAY("Bay"),
    DUN("Dun"),
    Brown("Brown"),
    GRULLO("Grullo"),
    CHESTNUT("Chestnut"),
    OVERO("Overo"),
    ROAN("Roan"),
    SORREL("Sorrel"),
    CREMELLO("Cremello"),
    TOBIANO("Tobiano"),
    BLOODBAY("Blood bay"),
    PERLINO("Perlino");

    String friendlyString;

    HorseColor(String friendlyString) {
        this.friendlyString = friendlyString;
    }

    @Override
    public String toString() {
        return friendlyString;
    }
}
