package Gui;

import GameAbstractions.*;
import BlackJackLogic.*;
import GuiAbstractions.HandPanel;

// This class merely manages the card labels in a given hand.
// It has a reference to a hand--but at the moment, it doesn't really need one.
public class BlackJackHandPanel extends HandPanel {

    public BlackJackHandPanel() {
        super(new BlackJackHand());
    }

    public BlackJackHandPanel(Hand hand) {
        super(hand);
        hand.getCards().stream().forEach(card -> this.add(new BlackJackCardLabel(card)));
        setBackground(FormatFactory.getBackgroundColor());
    }

    public void update(Hand hand) {
        this.hand = hand;
        removeAll();
        hand.getCards().stream().forEach(card -> this.add(new BlackJackCardLabel(card)));
        revalidate();
        repaint();
    }








}
