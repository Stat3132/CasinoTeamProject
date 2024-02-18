package org.example.Model;

import org.example.UTIL.HorseColor;
import org.example.UTIL.Name;
import org.example.UTIL.Probability;

public class CasinoMembers {
        String name;
        int totalWinnings, currentMoneyCount, gamesPlayed, gamesWon, gamesLost;
        Probability probably = new Probability();

        // getters & setters
        public String getName() {
            return name;
        }
        public void setName(String name) {
            if (name != null && name.length() > 2) {
                this.name = name;
            }
            if (name == ""){
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

        // name
        protected Name randomizingNames() {
            // This is just an array of names;
            Name[] baseNames = new Name[26];
            Name[] numbericalValueOfName = Name.values();
            for (int i = 0; i < baseNames.length; i++) {
                if (i != baseNames.length - 1) {
                    baseNames[i] = numbericalValueOfName[i];
                    System.out.println(baseNames[i]);
                } else {
                    break;
                }
            }
            return baseNames[probably.randomValues(0,26)];
        }


            //TODO: SUDO PLACEMENT( WILL BE CHANGED)
            //TODO: Probable better method implementation as it is NOT DRY
            protected HorseColor randomizeHorseColor() {
                HorseColor[] baseHorseColor = new HorseColor[26];
                HorseColor[] numbericalValueOfHorseColor = HorseColor.values();
                for (int i = 0; i < baseHorseColor.length; i++) {
                    if (i != baseHorseColor.length - 1) {
                        baseHorseColor[i] = numbericalValueOfHorseColor[i];
                        System.out.println(baseHorseColor[i]);
                    } else {
                        break;
                    }
                }
                return baseHorseColor[probably.randomValues(0, 26)];
            }






        // constructor
        public CasinoMembers(String name, int currentMoneyCount) {
            this.name = name;
            this.currentMoneyCount = currentMoneyCount;
        }

        // toString
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
