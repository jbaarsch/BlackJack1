package Gui;

import javax.swing.*;

// Could take this out and just use JFrame.  That would probably be better according to some things I read online, but
// I already made it, and it seems to work.  Changing it to use a JFrame would be an easy change to make later.
public class BlackJackFrame extends JFrame {

    BlackJackPanel gamePanel;

    public BlackJackFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new BlackJackPanel(this);

        getContentPane().add(gamePanel);
        pack();
    }



}
