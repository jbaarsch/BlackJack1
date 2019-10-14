package GameAbstractions;

import GameAbstractions.Card;
import GameAbstractions.Orientation;

import java.util.List;

// Many of these methods are not used for blackjack.  But, I want them available in case I add a new game.
public interface Hand {
    List<Card> getCards();
    List<Card> getCards(Orientation orientation);
    void addCard(Card card);
    Card play();
    Card discard();
}
