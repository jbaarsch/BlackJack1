package BlackJackLogic;

import BlackJackLogic.BlackJackDeck;
import BlackJackLogic.BlackJackHand;
import BlackJackLogic.BlackJackRules;

// Early Test-- no longer necessary.  But it might be useful later.
public class BlackJackTester {

    public static void main(String[] args) {

        BlackJackHand hand = new BlackJackHand();
        BlackJackDeck deck = new BlackJackDeck();
        BlackJackRules rules = new BlackJackRules();

        deck.getCards().stream().forEach(card -> System.out.println(card));

        for (int i = 0; i < 3; i++) {
            hand.addCard(deck.dealCard());
        }

        System.out.println();
        hand.getCards().stream().forEach(card -> System.out.println(card));

        System.out.println();

        System.out.println("The GameAbstractions.Hand's total is: " + rules.readHand(hand));







    }





}
