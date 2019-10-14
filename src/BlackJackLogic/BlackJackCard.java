package BlackJackLogic;

import GameAbstractions.*;

public class BlackJackCard implements Card {

    Suit suit;
    Integer value;
    Orientation orientation;

    public BlackJackCard (Suit suit, Integer value, Orientation orientation) {
        this.suit = suit;
        this.value = value;
        this.orientation = orientation;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public Integer getValue() { return value; }
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public Suit getSuit() { return suit; }
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    //These String methods are used for testing and for the game commentary in the console.
    public String getValueString() {
        switch (value) {
            case 13: return "king";
            case 12: return "queen";
            case 11: return "jack";
            case 1: return "ace";
            default: return ""+ value;
        }
    }

    public String toString() {
        return "The " + getValueString() + " of " + suit;
    }
}
