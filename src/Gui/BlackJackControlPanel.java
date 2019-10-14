package Gui;

import BlackJackLogic.BlackJackGameState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This represents the controls for game.  It keeps a reference to its parent (gamePanel), so that when buttons are
// clicked, the appropriate actions can be managed by gamePanel.
public class BlackJackControlPanel extends JPanel {

    BlackJackButton hit, stand, newGame, endGame;
    BlackJackPanel gamePanel;

    public BlackJackControlPanel(BlackJackPanel gamePanel) {
        this.gamePanel = gamePanel;
        hit = new BlackJackButton("Hit");
        hit.addActionListener(new HitListener());
        stand = new BlackJackButton("Stand");
        stand.addActionListener(new StandListener());
        newGame = new BlackJackButton("New Game");
        newGame.addActionListener(new newGameListener());
        endGame = new BlackJackButton("End Game");          //Oh...Snap!
        endGame.addActionListener(new endGameListener());

        JPanel gameControlsPanel = new JPanel();
        gameControlsPanel.setBackground(FormatFactory.getBackgroundColor());
        gameControlsPanel.add(hit);
        gameControlsPanel.add(stand);

        JPanel gameControllerPanel = new JPanel();
        gameControllerPanel.setBackground(FormatFactory.getBackgroundColor());
        gameControllerPanel.add(newGame);
        gameControllerPanel.add(endGame);

        setBackground(FormatFactory.getBackgroundColor());

        add(gameControlsPanel);
        add(gameControllerPanel);
    }

    public void updateControlPanel(BlackJackGameState gameState) {
        switch(gameState) {
            case preGame:
                hit.setEnabled(false);
                stand.setEnabled(false);
                break;
            case playerTurn:
                hit.setEnabled(true);
                stand.setEnabled(true);
                break;
            case dealerTurn:
                hit.setEnabled(false);
                stand.setEnabled(false);
                break;
            case gameOver:                                  // This should not be an issue, but want to be complete.
                hit.setEnabled(false);
                stand.setEnabled(false);
        }
    }

    // This is just so I can manage the appearance of the buttons at once.
    private class BlackJackButton extends JButton {
        public BlackJackButton(String text) {
            super(text);
            setPreferredSize(FormatFactory.getButtonDimension());
            setFont(FormatFactory.getButtonFont());
            setBorder(FormatFactory.getButtonBorder());
        }
    }

    private class HitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.hit();
        }
    }

    private class StandListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.stand();
        }
    }

    private class newGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.dealGame();
        }
    }

    private class endGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.endGame();
        }
    }
}
