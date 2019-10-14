package BlackJackLogic;

// Represents the state of the game.  Game will begin with preGame,
//          then move to playerTurn, when game is started.
//              stay in playerTurn until either the player hits and busts (which will change the state to Game Over)
//              or the player reaches 21 or stands then it will
//          move to dealer turn, and
//          from dealer turn to game over.
public enum BlackJackGameState {
    preGame, playerTurn, dealerTurn, gameOver
}
