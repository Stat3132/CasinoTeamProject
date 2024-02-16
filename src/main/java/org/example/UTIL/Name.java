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
    _24("Chester");

    String friendlyString;

    public void randomizeName(){

    }
    Name(String friendlyString) {
        this.friendlyString = friendlyString;
    }

    @Override
    public String toString() {

        return friendlyString;
    }
}
