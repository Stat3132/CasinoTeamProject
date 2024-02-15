package org.example.Model;

import org.example.UTIL.Name;
import org.example.UTIL.Probability;

public class CasinoMembers {
    String name;
    int totalWinnings;
    int currentMoneyCount;
    int gamesPlayed;
    int gamesWon;
    int gamesLost;

    Probability probably = new Probability();


    public CasinoMembers(String name, int totalWinnings, int currentMoneyCount, int gamesPlayed, int gamesWon, int gamesLost) {
        this.name = name;
        this.totalWinnings = totalWinnings;
        this.currentMoneyCount = currentMoneyCount;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    protected String randomizingNames(){
        Name[] nameLess = {Name.ALEX, Name.DUSTIN, Name.LIDIYA, Name.CONNER, Name.HENRY, Name.OLIVER, Name.ERIC, Name.CARTER, Name.JASMIN, Name.CARTER, Name.JOSE};
        return nameLess[probably.randomValues(0, nameLess.length - 1)].toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() > 2) {
            this.name = name;
        }
        if (name == ""){
             this.name = randomizingNames();
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
