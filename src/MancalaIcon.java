import java.awt.*;

/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 4/24/17.
 */

/**
 * An extension of the PitIcon class that paints mancala icons in the Mancala game.
 */
public class MancalaIcon extends PitIcon {
    /**
     * Create a MancalaIcon.
     * @param width width of icon
     * @param height height of icon
     * @param stoneNum number of stones to be drawn/displayed in a mancalas
     * @param pitLabel name of the pit
     * @param mancalaPitShape the style and shape of the mancalas
     */
    public MancalaIcon(int width, int height, int stoneNum, String pitLabel, MancalaPitShape mancalaPitShape) {
        super(width, height, stoneNum, pitLabel, mancalaPitShape);
    }

    /**
     * Paints the mancala icon.
     * @param c the component to be used as the observer if this icon has no image observer
     * @param g the graphics context
     * @param x the X coordinate of the icon's top-left corner
     * @param y the Y coordinate of the icon's top-left corner
     */
    @Override
    // Is subclassing PitIcon and overriding paintIcon() correct?
    public void paintIcon(Component c, Graphics g, int x, int y) {
        getMancalaPitShape().drawMancalaShape(g, x, y, getIconWidth(), getIconHeight(), getStoneNum(), getPitLabel());
    }
}
