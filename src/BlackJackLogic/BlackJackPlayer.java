package BlackJackLogic;

import GameAbstractions.*;

public class BlackJackPlayer implements Player{

    Hand hand;
    String name = "Player";
    Boolean isDealer = false;
    Boolean isCurrentPlayer = false;

    public BlackJackPlayer() {
        hand = new BlackJackHand();
    }

    public Hand getHand() {
        return hand;
    }
    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean isDealer() { return isDealer; }
    public void setDealer(Boolean dealer) { isDealer = dealer; }

    public Boolean isCurrentPlayer() { return isCurrentPlayer; }
    public void setCurrentPlayer(Boolean currentPlayer) { isCurrentPlayer = currentPlayer; }



}
