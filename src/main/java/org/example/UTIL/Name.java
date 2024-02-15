package org.example.UTIL;

public enum Name {
    HENRY("Henry"),
    OLIVER("Oliver"),
    DUSTIN("Dustin"),
    CONNER("Conner"),
    LIDIYA("Lidiya"),
    ALEX("Alex"),
    ERIC("Eric"),
    JASMIN("Jasmin"),
    JOSE("Jose"),
    CARTER("Carter"),
    WALTER("Walter"),
    KEVIN("Kevin"),
    FRANCISCO("Francisco"),
    HARRISON("Harrison"),
    JACK("Jack"),
    JESS("Jess"),
    JAMES("James"),
    CHESTER("Chester");


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
