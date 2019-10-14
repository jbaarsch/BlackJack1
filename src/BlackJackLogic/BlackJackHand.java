package BlackJackLogic;

import GameAbstractions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackHand implements Hand {
    ArrayList<Card> cards;

    public BlackJackHand (){
        cards = new ArrayList<Card>();
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public List<Card> getCards(Orientation orientation) {
        return cards.stream().filter(c -> c.getOrientation().equals(orientation)).collect(Collectors.toList());
    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        cards.stream().forEach(card -> this.addCard(card));
    }

    // Method not used in BlackJack.  Implemented here, it merely returns the first card in the hand.
    @Override
    public Card play() {
        if (cards.size() > 0)
            return cards.remove(0);
        else
            return null;
    }

    //Method not used in BlackJack.  Implemented here, it merely returns the first card in the hand.
    @Override
    public Card discard() {
        if (cards.size() > 0)
            return cards.remove(0);
        else
            return null;
    }

    // This method isn't used either.  I forgot that I had made it by the time I was flipping the dealer's hole card (
    // even though using it there was my intention when I implemented it here.
    // But since I am documenting now, if I had some time I might go back and change that to use this one.  Then again,
    // I do something differently there, so I will probably stand pat.
    public void turnOver() {
        cards.stream().forEach(card -> card.setOrientation(Orientation.faceUp));
    }
}
