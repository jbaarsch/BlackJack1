package Gui;

import BlackJackLogic.*;

import javax.swing.*;

// This panel manages the GUI of the game and provides the interface with the actual BlakcJackGame class.
public class BlackJackPanel extends JPanel {

    BlackJackGame game;
    BlackJackPlayerPanel dealerPanel, playerPanel;
    BlackJackControlPanel controlPanel;
    JPanel gameStatusPanel;
    JLabel gameStatusLabel;
    JFrame frame;

    public BlackJackPanel(JFrame frame) {
        this.frame = frame;
        game = new BlackJackGame();
        initializeGameStatusPanel();

        setPreferredSize(FormatFactory.getGamePanelDimension());
        setBackground(FormatFactory.getBackgroundColor());
        setBorder(FormatFactory.getGamePanelBorder());

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(Box.createVerticalGlue());

        dealerPanel = new BlackJackPlayerPanel(game.getDealer());
        add(dealerPanel);

        add(gameStatusPanel);

        playerPanel = new BlackJackPlayerPanel(game.getCurrentPlayer());
        add(playerPanel);

        controlPanel = new BlackJackControlPanel(this);
        add(controlPanel);
        updatePanels();
    }

    public void initializeGameStatusPanel(){
        gameStatusPanel =  new JPanel();
        gameStatusLabel = new JLabel("Welcome to BlackJack!");
        gameStatusLabel.setFont(FormatFactory.getTitleFont());
        gameStatusPanel.add(gameStatusLabel);
    }

    public void dealGame() {
        game.dealGame();
        updatePanels();
    }

    public void endGame() {
        frame.dispose();
    }

    public void updatePanels() {
        playerPanel.update();
        dealerPanel.update();
        updateGameStatusPanel();
        controlPanel.updateControlPanel(game.getGameState());
    }

    public void updateGameStatusPanel() {
        gameStatusLabel.setText(game.getStatusText());
    }

    public void hit() {
         // track whether there has been a state change.  Could implement Observer, but for now, the GUI just overlays the
         // Logic, and I don't have time to implement Observer right now.  Later upgrade.
         BlackJackGameState oldState = game.getGameState();
         BlackJackGameState newState = game.playerTurn(game.getCurrentPlayer(), BlackJackAction.Hit);
         updatePanels();
         switch (newState) {
             case playerTurn:
                 return;
             case dealerTurn:
                 if (oldState != newState)
                     switchPlayers();
                 return;
             case gameOver:
                 updatePanels();
                 return;
             default:
                 return;
         }
    }

    public void stand() {
        game.playerTurn(game.getCurrentPlayer(), BlackJackAction.Stand);
        game.dealerTurn();
        updatePanels();
    }

    // at this point should only be called when changing from player to dealer
    public void switchPlayers() {
         game.dealerTurn();
         updatePanels();
    }

}
