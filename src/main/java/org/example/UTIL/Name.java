package org.example.UTIL;

public enum Name {
    _0("Henry"),
    _1("Oliver"),
    _2("Dustin"),
    _3("Conner"),
    _4("Lidiya"),
    _5("Alex"),
    _6("Eric"),
    _7("Jasmin"),
    _8("Jose"),
    _9("Carter"),
    _10("Walter"),
    _11("Kevin"),
    _12("Francisco"),
    _13("Harrison"),
    _14("Jack"),
    _15("Jess"),
    _16("James"),
    _17("Barry"),
    _18("Samuel"),
    _19("Joseph"),
    _20("Jaden"),
    _21("Rose"),
    _22("Trevor"),
    _23("Tommy"),
    _24("Chester"),
    _25("Antonio"),
    _26("Ethan"),
    _27("Mary"),
    _28("Betty"),
    _29("Daniel"),
    _30("Christopher"),
    _31("Donald"),
    _32("Mitas"),
    _33("Theo"),
    _34("Giovanna"),
    _35("Giovanni"),
    _36("George"),
    _37("Alexander"),
    _38("Jerry"),
    _39("Joan"),
    _40("Dylan"),
    _41("Jordan"),
    _42("Lawrence"),
    _43("Sean"),
    _44("Elijah"),
    _45("Mason"),
    _46("Wayne"),
    _47("Valor"),
    _48("Marie"),
    _49("Alexis"),
    _50("Joy"),
    _51("Rider"),
    _52("Lori"),
    _53("Danielle"),
    _54("Bless"),
    _55("Ti"),
    _56("Jean"),
    _57("Eren"),
    _58("Aaron"),
    _59("Billy"),
    _60("Benjamin");



    String friendlyString;
    Name(String friendlyString) {
        this.friendlyString = friendlyString;
    }

    @Override
    public String toString() {
        return friendlyString;
    }
}
