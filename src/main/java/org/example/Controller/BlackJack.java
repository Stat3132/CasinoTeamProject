package org.example.Controller;

public class BlackJack implements Casino {
    //TODO:
    /** * Pretty self-explanatory method "Bet Amount". This is how much money is being bet.
     * /TODO: FOR SUDO AMOUNTS FOR BET AMOUNT
     * Black Jack (Sudo) Amount:
     * Black jack: 50 base, 2x 100, 3x 150. Implementation of DOUBLE OR NOTHING (50 x 2 , 100 x 2, 150 x 2)
     * These are going to be essentially returns the int which is how much they are betting. You can look at this method
     * like a funnel for betting money. It has methods that make sure the amount being bet is correct and if certain things like
     * "multiplier" are set then this will funnel into If statements determining that the bet returned is correct.
     **/
    @Override
    public int betAmount() {
        return 0;
    }

    /**
     * TODO: FOR SUDO CASHOUT AMOUNTS:
     * Black Jack (Sudo) CashOut:
     * Black jack cashout: If Natural 21: JACKPOT METHOD, If Dealer wins: Losing bet amount(If losing streak of 3: Cooldown (Maybe?), If Player wins: 2 x amount betted
     * @param betType I assume this would obviously be the type of win they got or the bet amount for 2x,3x,4x being bet, but I don't get why it's an INT :IDK:.
     * @param playerBet We need to know how much money the player has for certain bets, hence it being a perimeter.
     * @return
     */
    @Override
    public int cashOut(int betType, int playerBet) {
        return 0;
    }


    /**
     * This method would be fully contributing to the "jack pot" for the certain game. This is gonna just be the logic for the Natural 21 as that is our "jack pot" for
     * blackjack. So for just an example if they get a natural 21 they get a jackpot and this will account for information on the games leader board as well
     * as the worldwide leader board if this is there current highest "jackpot"
     * @return
     */
    @Override
    public int jackPot() {
        return 0;
    }

    /**
     * Play method is how the game will play. Essentially extracting all factors of probability and probably will call both bet amount and cash out.
     */
    @Override
    public void play() {

    }

    @Override
    public int whatAreTheOdds() {
        return 0;
    }

}
