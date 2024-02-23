package org.example.Model;

import org.example.UTIL.HorseColor;
import org.example.UTIL.Name;
import org.example.UTIL.ProbabilityForValue;

import java.util.ArrayList;

public class CasinoMembers {
    String name;
    int totalWinnings, currentMoneyCount, gamesPlayed, gamesWon, gamesLost;
    int totalHorseMoney, totalRouletteMoney, totalSlotMoney, totalBlackJackMoney;
    boolean isAI = false;

    // getters & setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && name.length() > 2) {
            this.name = name;
        }
        // I set up an extra setter so if the people inputting information decide to keep it blank " " or put numbers
        //It will randomize names
        if (name == " "){
            this.name = randomizingNames().toString();
        }
    }

    public int getTotalWinnings() {
        return totalWinnings;
    }
    public void setTotalWinnings(int totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    public int getCurrentMoneyCount() {
        return currentMoneyCount;
    }
    public void setCurrentMoneyCount(int currentMoneyCount) {
        this.currentMoneyCount = currentMoneyCount;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getTotalHorseMoney() {
    return totalHorseMoney;
}
    public void setTotalHorseMoney(int totalHorseMoney) {
        this.totalHorseMoney = totalHorseMoney;
    }

    public int getTotalRouletteMoney() {
        return totalRouletteMoney;
    }
    public void setTotalRouletteMoney(int totalRouletteMoney) {
        this.totalRouletteMoney = totalRouletteMoney;
    }

    public int getTotalSlotMoney() {
        return totalSlotMoney;
    }
    public void setTotalSlotMoney(int totalSlotMoney) {
        this.totalSlotMoney = totalSlotMoney;
    }

    public int getTotalBlackJackMoney() {
        return totalBlackJackMoney;
    }
    public void setTotalBlackJackMoney(int totalBlackJackMoney) {
            this.totalBlackJackMoney = totalBlackJackMoney;
        }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This is a method that ranodmizes names. This is "casino members" so this logic is extending to other class objects that need their names to be randomized.
     * I have an array and I use a Loop that takes values from the names and assigns them to a new array index.
     * After putting all names into an array I then return a random name by using "probably" Which is a instance of Probability that
     * gives me a random number from lower bound to upper bound.
     * @return
     */

    protected Name randomizingNames() {
            // This is just an array of names;
            Name[] baseNames = new Name[122];
            Name[] numbericalValueOfName = Name.values();
            for (int i = 0; i < baseNames.length; i++) {
                if (i != baseNames.length - 1) {
                    baseNames[i] = numbericalValueOfName[i];
                } else {
                    break;
                }
            }
            return baseNames[ProbabilityForValue.randomValues(0,120)];
        }


    /**
     * This is a duplication of the code above.
     * @return
     */
    //TODO: SUDO PLACEMENT( WILL BE CHANGED)
            //TODO: Probable better method implementation as it is NOT DRY
            protected HorseColor randomizeHorseColor() {
                ArrayList<HorseColor> horseList = new ArrayList<HorseColor>();
                HorseColor[] baseHorseColor = new HorseColor[15];
                // Making an array that takes in Enum values so that i can iterate and add enums into specific spots in an array.
                HorseColor[] numbericalValueOfHorseColor = HorseColor.values();
                for (int i = 0; i < baseHorseColor.length; i++) {
                    if (i != baseHorseColor.length) {
                        baseHorseColor[i] = numbericalValueOfHorseColor[i];
                    } else {
                        break;
                    }
                }
                return baseHorseColor[ProbabilityForValue.randomValues(0, 14)];
            }


    /**
     * This is a constructor for what needs to be implemented for other objects using this constructor.
     * @param name
     * @param currentMoneyCount
     */

    // constructor
        public CasinoMembers(String name, int currentMoneyCount) {
            setName(name);
            setCurrentMoneyCount(currentMoneyCount);
        }

        // toString

    /**
     * Making code cleaner
     * @return
     */
        @Override
        public String toString() {
            StringBuilder casinoMemberBuilder = new StringBuilder();
            casinoMemberBuilder.append("\t\tName: ").append(name);
            casinoMemberBuilder.append("\nTOTAL WINNINGS: ").append(totalWinnings);
            casinoMemberBuilder.append("\nCurrent money: ").append(currentMoneyCount);
            casinoMemberBuilder.append("\n\n\tGames played: ").append(gamesPlayed);
            casinoMemberBuilder.append("\nGames won: ").append(gamesWon).append("  Games lost: ").append(gamesLost);
            casinoMemberBuilder.append("\n");
            return casinoMemberBuilder.toString();
        }

}
