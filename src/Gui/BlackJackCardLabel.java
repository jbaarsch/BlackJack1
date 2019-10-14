package Gui;

import BlackJackLogic.BlackJackCard;
import GameAbstractions.Card;
import GameAbstractions.Orientation;
import GuiAbstractions.CardLabel;

import javax.swing.*;
import java.awt.*;

// Represents a single card's image.
public class BlackJackCardLabel extends JLabel implements CardLabel {

    final String IMAGES_DIR_PATH = "/Images/",
                CARD_BACK_IMAGE = "cardback.png";
    final Double SCALE_FACTOR = .333;
    ImageIcon cardPic;
    Card card;

    public BlackJackCardLabel() {
        super("Card image to go here");
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //setPreferredSize(new Dimension(, 450));
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);
    }

    public BlackJackCardLabel(Card card) {
        this.card = card;
        cardPic = getCardIcon(card);
        scaleIcon(SCALE_FACTOR);
        setBackground(Color.GRAY);
    }

    public ImageIcon getCardIcon(Card card) {
        if (card.getOrientation() == Orientation.faceDown) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return new ImageIcon(getClass().getResource(IMAGES_DIR_PATH + CARD_BACK_IMAGE));
        }
        else {
            return new ImageIcon(getClass().getResource(IMAGES_DIR_PATH + translateCardToImage(card)));
        }
    }

    private String translateCardToImage(Card card) {
        // Cards images are all png.  They are ordered clubs, spades, diamonds, hearts and numbered 1 to 52.
        // translation will be the ordinal of the suit (0 = clubs, 1 = spades, 2 = diamonds, 3 = hearts) times 13, plus
        // the value of the card.  Three of hearts would be 3 * 13 + 3, or 42.
        int testOrd =  card.getSuit().ordinal();
        return "" + ( ((card.getSuit().ordinal()) * 13) + card.getValue() ) + ".png";
    }

    // Someday I would like these cards to scale automatically.  Put it in a future upgrade.
    private void scaleIcon(Double scaleFactor){
        Integer labelHeight = (int)(((double)cardPic.getIconHeight()) * scaleFactor);
        Integer labelWidth = (int)(((double)cardPic.getIconWidth()) * scaleFactor);
        Image scaledImage =  cardPic.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImage));
    }

    public void paint(Graphics g) {
        super.paint(g);
    }

}
