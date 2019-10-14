BlackJack1
Jon Baarsch (jdbaarsch@gmail.com)
10/14/2019

This game is written entirely in Java.  I used Java jdk-13.
In order to run, you can use the runnable jar in BlackJack1/out/artifacts/BlackJack1_jar/BlackJack1.jar
(if you have the jdk installed, you can run it from the window, or you can navigate to the directory and run
'java -jar BlackJack1.jar'

This version of blackjack does not have betting, and only allows two actions by the player: hit and stand.

Rules
Each player is dealt two cards to begin with.  One of the dealer's cards is face down.
If the Dealer or Player gets BlackJack in the initial deal, then the game ends immediately.
Player plays first.
If the player "Busts" (goes over 21 points), then the dealer wins
If the player gets to 21 or "Stands" before exceeding 21, then the dealer plays.
The dealer will "Hit" until reaching 17 or more points.
If the dealer "Busts", then the player wins.
If the dealer stops before "Busting", then whichever hand is closer to 21 wins.
If the two hands score the same, the game results in a "Push" or tie.
All cards are worth the number of points equal to their face value, with the exceptions:
	Aces, which may count as either 1 or 11,
	Jacks, Queens, and Kings, which count as 10.

Controls
The Hit button calls for a "Hit," the player will receive an additional card.  May be repeated until hand sums to 21 or more total.
The Stand button calls for a "Stand," the player will receive no additional cards.
The New Game button resets the deck and deals two hands.
The End Game Button closes the frame and exits the process.

Navigating the Code:
Packages:
 --GameAbstractions: -- Some very basic abstractions of things like Cards and Hands
 --BlackJackLogic: -- Where the logic and rules of the BlackJack game is stored.  Instantiations of the abstractions
 --GuiAbstractions: -- Under-used package.  Most Gui abstractions are merely in the Swing or AWT libraries (e.g. JPanel)
 --Gui: -- These classes manage the Gui for the game.  
 --Exceptions -- (really just one, and that one used mostly in debugging)
 --Images: -- This is where the images are stored.

Most of the interesting code is in the BlackJackLogic and the Gui Packages.

 --GameAbstractions:
	Card
	Hand
	Player
	Suit (enum)
	Orientation (enum)

 --BlackJackLogic:
	BlackJackGame -- manages the game (players, deck, turns, and gameState)
	BlackJackRules -- Stores the rules of the game and adjudicates decisions (static methods).

	BlackJackCard 
	BlackJackHand
	BlackJackDeck
	BlackJackPlayer
	
	BlackJackAction (enum) -- an action a player can take: hit and stand are the only ones implemented
	BlackJackGameState (enum) -- represents the state of the game, generally: preGame -> playerTurn -> dealerTurn -> gameOver

	BlackJackTester -- only used for early non-gui testing

 --GuiAbstractions:
	CardLabel
	HandPanel
	PlayerPanel

 --Gui:
	BlackJack -- main method
	BlackJackFrame -- the outer frame
	BlackJackPanel -- manages the Gui of the game and provides the interface with the GameLogic
	
	BlackJackControlPanel -- contains the buttons for UI
	BlackJackPlayerPanel -- contains all information related to a player on the screen
	BlackJackHandPanel -- displays the hand on the screen
	BlackJackCardLabel -- displays an individual playing card on the screen
	
	FormatFactory -- attempts to encapsulate most formatting:  fonts, colors, borders, and dimensions.

 --Exceptions:
	InvalidValueException -- Just used in testing

 --Images:
	card image files -- named 1.png through 52.png, along with an image for the card back.


	