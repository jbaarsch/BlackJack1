package Gui;

import BlackJackLogic.BlackJackPlayer;
import BlackJackLogic.BlackJackRules;
import GuiAbstractions.PlayerPanel;

import javax.swing.*;
import java.awt.*;

// This manages the GUI for a Player's Title, Hand, and hand status (really just the sum of the cards showing)
public class BlackJackPlayerPanel extends PlayerPanel {

    BlackJackHandPanel handPanel;
    BlackJackPlayer player;
    PlayerStatusPanel statusPanel;
    JPanel namePanel;

    BlackJackRules rules;

    public BlackJackPlayerPanel(BlackJackPlayer player) {
        this.player = player;
        handPanel = new BlackJackHandPanel(player.getHand());
        statusPanel = getStatusPanel();
        namePanel =  getNamePanel();

        setLayout(new BorderLayout());
        setBackground(FormatFactory.getBackgroundColor());

        add(namePanel, BorderLayout.NORTH);
        add(handPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }

    private JPanel getNamePanel() {
        JPanel result = new JPanel();
        JLabel nameLabel = new JLabel(player.getName());
        nameLabel.setFont(FormatFactory.getNameFont());
        result.add(nameLabel);
        result.setBackground(FormatFactory.getBackgroundColor());
        return result;
    }

    private PlayerStatusPanel getStatusPanel() {
            return new PlayerStatusPanel();
    }

    public void update() {
        handPanel.update(player.getHand());
        statusPanel.updateScore();
        revalidate();
        repaint();
    }

    private class PlayerStatusPanel extends JPanel {
        JLabel scoreTextLabel;
        JLabel scoreLabel;

        public PlayerStatusPanel() {
            setBackground(FormatFactory.getBackgroundColor());
            if (!player.isCurrentPlayer())
                scoreTextLabel = new JLabel(player.getName() + " shows: ");
            else
                scoreTextLabel = new JLabel("Your current Hand shows: ");
            scoreLabel = new JLabel();
            updateScore();
            scoreTextLabel.setFont(FormatFactory.getStatusFont());
            scoreLabel.setFont(FormatFactory.getStatusFont());
            add(scoreTextLabel);
            add(scoreLabel);
        }

        public void updateScore() {
            scoreLabel.setText("" + BlackJackRules.readShowingHand(player.getHand()));
        }

        public void setText(String text) {
            scoreLabel.setText(text);
        }
    }


}
