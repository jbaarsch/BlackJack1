package BlackJackLogic;

import Exceptions.InvalidValueException;
import GameAbstractions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlackJackDeck {

    List<Card> cards;
    Random cardPicker = new Random();

    public BlackJackDeck() {
        initializeDeck();
    }

    public Card dealCard (){
        return cards.remove(cardPicker.nextInt(cards.size()));
    }

    public Card dealCard(Orientation orientation) {
        Card dealtCard = dealCard();
        dealtCard.setOrientation(orientation);
        if (orientation == Orientation.faceDown) {
            System.out.println("A mystery card is dealt!");
        }
        else
            System.out.println(dealtCard + " is dealt");
        return dealtCard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void resetDeck(){
        initializeDeck();
    }

    private void initializeDeck() {
        System.out.println("The Deck is initialized!");
        cards = new ArrayList<Card>();
        for (int i = 0; i < 52; i++) {
                switch(i/13) {
                case 0: cards.add(new BlackJackCard(Suit.Clubs, i % 13 +1, Orientation.faceDown));
                    break;
                case 1: cards.add(new BlackJackCard(Suit.Spades, i % 13 + 1, Orientation.faceDown));
                    break;
                case 2:  cards.add(new BlackJackCard(Suit.Diamonds, i % 13 + 1, Orientation.faceDown));
                    break;
                case 3:  cards.add(new BlackJackCard(Suit.Hearts, i % 13 + 1, Orientation.faceDown));
                    break;
                default: throw new InvalidValueException("Deck Initialization Failure");
            }
        }
    }
}
