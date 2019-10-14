package BlackJackLogic;

import Exceptions.InvalidValueException;
import GameAbstractions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Represents the Game of BlackJack--primarily manages players, turns, and tracks the gameState.
public class BlackJackGame {
    List<BlackJackPlayer> players;
    BlackJackDeck deck;
    BlackJackRules rules = new BlackJackRules();
    BlackJackGameState gameState;

    public BlackJackGame() {
        players = new ArrayList();
        deck = new BlackJackDeck();

        // Create the dealer
        BlackJackPlayer dealer = new BlackJackPlayer();
        dealer.setDealer(true);
        dealer.setName("Dealer");
        players.add(dealer);

        //create the human player
        BlackJackPlayer player = new BlackJackPlayer();
        player.setDealer(false);
        player.setCurrentPlayer(true);
        players.add(player);

        //set game state;
        gameState = BlackJackGameState.preGame;
    }

    public BlackJackGameState getGameState() {
        return gameState;
    }

    // Used at the beginning of the game to initialize deck and deal initial hands.  Also checks for auto-win.
    public BlackJackGameState dealGame() {
        deck.resetDeck();
        players.stream().forEach(player -> dealInitialHand(player));
        // Default gameState here will be player turn--unless the game ends prematurely.
        gameState = BlackJackGameState.playerTurn;
        //Check if anyone got BlackJack right off the bat.
        players.stream().forEach(player -> {
            if (rules.isBlackJack(player.getHand())) {
                if (player.isDealer()) {
                    gameState = BlackJackGameState.dealerTurn;
                    dealerTurn();
                }
                else gameState = BlackJackGameState.gameOver;
            }});
        // If no first round BlackJacks, then play begins!
        return gameState;
    }

    // deals an initial hand to a player.
    public void dealInitialHand(BlackJackPlayer player) {
        BlackJackHand hand = new BlackJackHand();
        player.setHand(hand);
        System.out.println(player.getName() + " is dealt a hand...");
        if (!player.isCurrentPlayer()) {
            hand.addCard(deck.dealCard(Orientation.faceDown));
            hand.addCard(deck.dealCard(Orientation.faceUp));
        }
        else {
            hand.addCard(deck.dealCard(Orientation.faceUp));
            hand.addCard(deck.dealCard(Orientation.faceUp));
        }
    }

    //takes a player and an action and resolves the action and changes the gamestate accordingly.
    // There is a little bleedage with the Rules here.  I wonder if we can separate them more completely.
    // Still this has to do more with managing the Game state and the flow of play (turns) than rules.
    public BlackJackGameState playerTurn(BlackJackPlayer player, BlackJackAction action) {
        Hand hand = player.getHand();
        switch(action) {
            case Hit:
                System.out.println("A Hit! A Palpable Hit!");
                hand.addCard(deck.dealCard(Orientation.faceUp));
                if (rules.isBust(rules.readHand(hand))) {
                    System.out.println("Busted!");
                    gameState = BlackJackGameState.gameOver;
                }
                if (rules.isMaxHand(hand) && !player.isDealer() ) {
                    System.out.println("Maximum!");
                    gameState = BlackJackGameState.dealerTurn;
                }
                System.out.println("Your hand shows " + rules.readHand(hand) + " points.");
                return gameState;
            case Stand:
                System.out.println("Stand in the place where you are!");
                if (!player.isDealer)
                    gameState = BlackJackGameState.dealerTurn;
                else
                    gameState = BlackJackGameState.gameOver;
                return gameState;
            default:
                return gameState;
        }
    }

    // The only reason this will be run is if the player stands before busting. (Or if the dealer is dealt blackjack--
    // primarily to turn the card so the BlackJack is visible.
    public BlackJackGameState dealerTurn() {
        Hand dealerHand = getDealer().getHand();
        // flip the hole card
        dealerHand.getCards().stream().forEach(card -> {
            if (card.getOrientation() == Orientation.faceDown)
                System.out.println("The mystery card is " + card.toString());
                card.setOrientation(Orientation.faceUp);
            });

        // while the dealer's hand scores under 17, deal a new card to the dealer.
        System.out.println(getDealer().getName() + " is showing " + rules.readHand(dealerHand));
        while (rules.readHand(dealerHand) < rules.DEALER_STOP_POINT) {
            dealerHand.addCard(deck.dealCard(Orientation.faceUp));
            System.out.println(getDealer().getName() + " is showing " + rules.readHand(dealerHand));
        }
        gameState = BlackJackGameState.gameOver;
        return gameState;
    }

    // translates Game State into a message for the player.
    public String getStatusText() {
        switch (gameState) {
            case preGame:
                return "Welcome to BlackJack!";
            case playerTurn:
                return "Choose your fate...";
            case dealerTurn:
                return "Dealer is playing";
            case gameOver:
                String result = rules.checkGameResult(getDealer(), getCurrentPlayer());
                System.out.println(result);
                return result;
            default:
                return "I don't know the status of the game....";
        }
    }

    // In the single player game, current player is always the human player.  I used current player in case I want to
    // add more players later (that would be a more difficult task...)
    public BlackJackPlayer getCurrentPlayer() {
        List<BlackJackPlayer> somePlayers = players.stream().filter(player -> player.isCurrentPlayer()).collect(Collectors.toList());
        if (somePlayers.size() != 1)
            throw new InvalidValueException("There is no current player!");
        else
            return somePlayers.get(0);
    }

    // gets the dealer.  I could track currentPlayer and dealer independent variables to the players collection.  However,
    // since I have players as a collection, I think it would be better to keep them stored in one place.
    public BlackJackPlayer getDealer() {
        List<BlackJackPlayer> somePlayers = players.stream().filter(player -> player.isDealer()).collect(Collectors.toList());
        if (somePlayers.size() != 1)
            throw new InvalidValueException("There is no current dealer!");
        else
            return somePlayers.get(0);
    }
}








