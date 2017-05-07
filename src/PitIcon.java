import javax.swing.*;
import java.awt.*;

/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 5/3/17.
 */

/**
 * An implementation of the Icon interface that paints pits in the Mancala game.
 */
public class PitIcon implements Icon {
    private int width;
    private int height;
    private int stoneNum;
    private String pitLabel;
    private MancalaPitShape mancalaPitShape;

    /**
     * Create a pitIcon.
     * @param width width of icon
     * @param height height of icon
     * @param stoneNum number of stones to be drawn/displayed in a pit
     * @param pitLabel name of the pit
     * @param mancalaPitShape the style and shape of the pits
     */
    public PitIcon(int width, int height, int stoneNum, String pitLabel, MancalaPitShape mancalaPitShape) {
        this.width = width;
        this.height = height;
        this.stoneNum = stoneNum;
        this.pitLabel = pitLabel;
        this.mancalaPitShape = mancalaPitShape;
    }

    /**
     * Paints the pit icon.
     * @param c the component to be used as the observer if this icon has no image observer
     * @param g the graphics context
     * @param x the X coordinate of the icon's top-left corner
     * @param y the Y coordinate of the icon's top-left corner
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        mancalaPitShape.drawPitShape(g, x, y, width, height, stoneNum, pitLabel);
    }

    /**
     * Gets the width of the icon.
     * @return the width in pixels of this icon
     */
    public int getIconWidth() {
        return width;
    }

    /**
     * Gets the height of the icon.
     * @return the height in pixels of this icon
     */
    public int getIconHeight() {
        return height;
    }

    /**
     * Gets the number of stones in a pit icon.
     * @return the stoneNum property
     */
    public int getStoneNum() {
        return stoneNum;
    }

    /**
     * Sets the number of stones in a pit icon.
     * @param stoneNum the number of stones in a pit icon
     */
    public void setStoneNum(int stoneNum) {
        this.stoneNum = stoneNum;
    }

    /**
     * Gets the name of a pit.
     * @return the pitLabel property
     */
    public String getPitLabel() {
        return pitLabel;
    }

    /**
     * Gets the shape/style of mancalas and pits drawn in the icon.
     * @return the mancalaPitShape property
     */
    public MancalaPitShape getMancalaPitShape() {
        return mancalaPitShape;
    }
}
