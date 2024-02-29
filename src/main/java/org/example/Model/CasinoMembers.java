package org.example.Model;

import org.example.UTIL.HorseColor;
import org.example.UTIL.Name;
import org.example.UTIL.ProbabilityForValue;
import java.util.Objects;

public class CasinoMembers {
    //region VARS
    String name;
    private int totalWinnings = 0, currentMoneyCount = 0;
    private int totalHorseMoney = 0, totalRouletteMoney = 0, totalSlotMoney = 0, totalBlackJackMoney = 0;
    private boolean isAI = false;
    //endregion

    //region GETTERS & SETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && name.length() > 2) {
            this.name = name;
        }
        // I set up an extra setter so if the people inputting information decide to keep it blank " " or put numbers
        //It will randomize names
        if (Objects.equals(name, " ")){
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

    public int getTotalHorseMoney() {
    return totalHorseMoney;
}
    public void setTotalHorseMoney(int totalHorseMoney) {
        this.totalHorseMoney = totalHorseMoney;
        this.totalWinnings += totalHorseMoney;
    }

    public int getTotalRouletteMoney() {
        return totalRouletteMoney;
    }
    public void setTotalRouletteMoney(int totalRouletteMoney) {
        this.totalRouletteMoney = totalRouletteMoney;
        this.totalWinnings += totalRouletteMoney;
    }

    public int getTotalSlotMoney() {
        return totalSlotMoney;
    }
    public void setTotalSlotMoney(int totalSlotMoney) {
        this.totalSlotMoney = totalSlotMoney;
        this.totalWinnings += totalSlotMoney;
    }

    public int getTotalBlackJackMoney() {
        return totalBlackJackMoney;
    }
    public void setTotalBlackJackMoney(int totalBlackJackMoney) {
            this.totalBlackJackMoney = totalBlackJackMoney;
            this.totalWinnings += totalBlackJackMoney;
        }

    public boolean isAI() {
        return isAI;
    }
    public void setAI(boolean AI) {
        isAI = AI;
    }
    //endregion

    //region RANDOMIZING LOGIC
    //This method randomizes names based on an array of Name enums.
    protected Name randomizingNames() {
        //FIXME
            // This is just an array of names;
            Name[] baseNames = new Name[122];
            Name[] numbericalValueOfName = Name.values();
            for (int i = 0; i < baseNames.length; i++) {
                if (numbericalValueOfName[i] == null)
                    baseNames[i] = numbericalValueOfName[i];
                if (i != baseNames.length - 1) {
                    baseNames[i] = numbericalValueOfName[i];
                    numbericalValueOfName[i] = null;
                } else {
                    break;
                }
            }
            return baseNames[ProbabilityForValue.randomValues(0,120)];
        }
    /**
     * This is a duplication of the code above.
     */
    //TODO: SUDO PLACEMENT( WILL BE CHANGED)
    //TODO: Probable better method implementation as it is NOT DRY
    protected HorseColor randomizeHorseColor() {
        HorseColor[] baseHorseColor = new HorseColor[15];
        // Making an array that takes in Enum values so that I can iterate and add enums into specific spots in an array.
        HorseColor[] numbericalValueOfHorseColor = HorseColor.values();
        System.arraycopy(numbericalValueOfHorseColor, 0, baseHorseColor, 0, baseHorseColor.length);
        return baseHorseColor[ProbabilityForValue.randomValues(0, 14)];
    }
    /**
     * This is a constructor for what needs to be implemented for other objects using this constructor.
     */
    //endregion

    //region CONSTRUCTOR & TOSTRING
    public CasinoMembers(String name, int currentMoneyCount) {
        setName(name);
        setCurrentMoneyCount(currentMoneyCount);
    }

    /**
     * Making code cleaner
     */
    @Override
    public String toString() {
        return "\t\tName: " + name +
                "\nTOTAL WINNINGS: " + totalWinnings +
                "\nCurrent money: " + currentMoneyCount;
    }
    //endregion
}
