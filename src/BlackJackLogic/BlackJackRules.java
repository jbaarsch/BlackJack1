package BlackJackLogic;

import Exceptions.InvalidValueException;
import GameAbstractions.*;

import java.util.stream.Collectors;

//To encapsulate the rules for BlackJack.  All rules are static and all instance variables are constants.
public class BlackJackRules {

    public static final int MAX_HAND_TOTAL = 21,      // the maximum value for a hand--after which the hand is a bust
            FACE_CARD_VALUE = 10,       // the value given to face cards
            CARD_VALUE_MAX = 13,        // maximum value for a card
            CARD_VALUE_MIN = 1,     //minimum value for a card
            FACE_CARD_FLIP_POINT = 10,  //the value over which face cards are treated the same
            ACE = 1,                // Aces are special because they can have two values
            ACE_DUAL_VALUE_DIFFERENTIAL = 10,       //The difference in value between the two possible ace values
            BLACKJACK_NUMBER_OF_CARDS = 2,          //BlackJack is apparently getting 21 points with only two cards.
            BLACKJACK_TOTAL = 21,                   // The Number of points for a BlackJack
            DEALER_STOP_POINT = 17;                 // The point at which the dealer will no longer hit him/herself (ouch).


    public static boolean isBust(Integer handTotal) {
        if (handTotal > MAX_HAND_TOTAL)
            return true;
        else
            return false;
    }

    // duplicates above, but more intuitive for other classes
    public static boolean isBust(Hand hand) {
        return isBust(readHand(hand));
    }

    public static boolean isBlackJack(Hand hand) {
        if (hand.getCards().size() == BLACKJACK_NUMBER_OF_CARDS && readHand(hand) == BLACKJACK_TOTAL)
            return true;
        else
            return false;
    }

    // Used to determine whether the player is forced to stop playing (without losing).
    public static boolean isMaxHand(Hand hand) {
        if (readHand(hand) == MAX_HAND_TOTAL)
            return true;
        else
            return false;
    }

    // Since the GameState gameOver tracks the options available--and it doesn't care who wins or loses, this
    // method merely returns a message for the user, who may care what the result of the game was.
    public String checkGameResult(Player dealer, Player player) {
        // Game ending possibilities in order of occurring (exit after occurs):
        // 1. Dealer gets BlackJack--and so does player! (tie)
        // 2. Dealer gets BlackJack--and Player does not! (loss)
        // 3. Player gets BlackJack--and Dealer does not! (win)
        // 4. Player Busts (loss)
        // 5. Dealer Busts (win)
        // 6. Compare Dealer Hand with Player Hand; high score wins, tie "Push" if equal.

        Hand dealerHand = dealer.getHand();
        Hand playerHand = player.getHand();

        if (isBlackJack(dealerHand) && !isBlackJack(playerHand))
            return "Loss! " + dealer.getName() + " wins with BlackJack!";
        if (isBlackJack(dealerHand) && isBlackJack(playerHand))
            return "Push!  Both " + dealer.getName() + " and " + player.getName() + " get BlackJack!";
        if (isBlackJack(playerHand) && !isBlackJack(dealerHand))
            return "Win! " + player.getName() + " scores BlackJack!";
        if (isBust(playerHand))
            return "Loss! " + player.getName() + " Busts! " + readHand(playerHand) + " is more than the " + MAX_HAND_TOTAL + " allowed.";
        if (isBust(dealerHand))
            return "Win! " + dealer.getName() + " Busts! ";
        if (compareHand(dealerHand, playerHand) < 0)  {
            return "Win! " + dealer.getName() + ": " + readHand(dealerHand) + ";  " + player.getName() + ": " + readHand(playerHand);
        }
        if (compareHand(dealerHand, playerHand) > 0)  {
            return "Loss! " + dealer.getName() + ": " + readHand(dealerHand) + ";  " + player.getName() + ": " + readHand(playerHand);
        }
        if (compareHand(dealerHand, playerHand) == 0)  {
            return "Push! " + dealer.getName() + ": " + readHand(dealerHand) + ";  " + player.getName() + ": " + readHand(playerHand);
        }
        return "I don't know how to determine the game....";            //Something went wrong here.
    }

    // Okay, I got a little over-zealous here with error-checking.  But this will help translate cards to points.
    // Aces are treated as ones, their safest option.  When the hand is read together, the value of the ace will toggle
    // between one and eleven, depending on the context of the rest of the hand.
    public static Integer readCard(Card card) {
        //if over max, throw exception
        if (card.getValue() > CARD_VALUE_MAX) {
            throw new InvalidValueException("The value on the card: " + card + " was greater than " + CARD_VALUE_MAX + ".");
        }
        //if card is a face card:  Jack, Queen, King, then score the card with the standard FaceCard Value
        if (card.getValue() > FACE_CARD_FLIP_POINT) {
            return FACE_CARD_VALUE;
        }
        //if over or equal to minimum card value (but less than FaceCardFlipPoint); then use the card's regular value.
        if (card.getValue() >=  CARD_VALUE_MIN) {
            return card.getValue();
        }
        //Otherwise, I don't know what the value of the card is.  Probably under 1.
        throw new InvalidValueException("The value on the card: " + card + " did not follow the rules." );
    }

    // This method not used.
    public Integer compareCard(Card card, Card otherCard) {
        return card.getValue() - otherCard.getValue();
    }

    // Reads the Hand and determines the value of an ace.
    public static Integer readHand(Hand hand) {
        Integer sum = 0;
        Integer aceCount = 0;

        for (Card card : hand.getCards()) {
            if (card.getValue() == ACE) {
                aceCount++;
            }
            sum+= readCard(card);
        }
        while (aceCount > 0 && !isBust(sum + ACE_DUAL_VALUE_DIFFERENTIAL) ) {
            sum += ACE_DUAL_VALUE_DIFFERENTIAL;
            aceCount--;
        }
        return sum;
    }

    // Because the player shouldn't get to know what the hole card is.
    public static Integer readShowingHand(Hand hand) {
        BlackJackHand upHand = new BlackJackHand();
        upHand.addCards(hand.getCards().stream().filter(card -> card.getOrientation() == Orientation.faceUp).collect(Collectors.toList()));
        return readHand(upHand);
    }

    //This method is used.
    public Integer compareHand(Hand hand, Hand otherHand) {
       return readHand(hand) - readHand(otherHand);
    }
}
