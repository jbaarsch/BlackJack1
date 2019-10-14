package Gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

// An attempt to keep as much of the formatting in a single spot, for ease of changing.  Perhaps a future upgrade can
// make all this configurable within the application.  Just make these qualities instance variables with defaults, and
// then make getters and setters for them.
public class FormatFactory {


    //FONTS
    public static Font getNameFont() {
        return new Font("Serif", Font.BOLD, 30);
    }

    public static Font getStatusFont() {
        return new Font("Serif", Font.BOLD, 20);
    }

    public static Font getTitleFont() { return new Font( "Serif", Font.BOLD, 40); }

    public static Font getButtonFont() { return new Font("Serif", Font.BOLD, 30); }


    // COLORS
    public static Color getBackgroundColor() {
        return new Color(0, 150, 0);
    }

    public static Color getActiveButtonColor() { return new Color(0, 250, 0); }

    public static Color getInactiveButtonColor() { return new Color (205, 0, 0); }

    //DIMENSIONS
    public static Dimension getGamePanelDimension(){ return new Dimension(1300, 830); }

    public static Dimension getButtonDimension() { return new Dimension( 180, 50); }

    //BORDERS
    public static Border getGamePanelBorder() { return BorderFactory.createLineBorder(Color.black, 10); }

    public static Border getPlayerPanelBorder() { return BorderFactory.createLineBorder(Color.black, 3); }

    public static Border getButtonBorder() { return BorderFactory.createBevelBorder(BevelBorder.RAISED); }
}
