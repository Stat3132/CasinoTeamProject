package org.example.UTIL;

public enum HorseColor {

    //region ENUM VALUES
    //enums for horse colors
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
    //endregion

    //region CONSTRUCTOR & TOSTRING
    HorseColor(String friendlyString) {
        this.friendlyString = friendlyString;
    }
    final String friendlyString;
    @Override
    public String toString() {
        return friendlyString;
    }
    //endregion
}
