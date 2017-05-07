import java.awt.*;

/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 5/3/17.
 */

/**
 * The MancalaPitShape interface provides definitions for styles and shapes of mancalas and pits in a Mancala game.
 */
public interface MancalaPitShape {
    /**
     * Draw a pit in a Mancala game.
     * @param g the graphics context
     * @param x the X coordinate of the pit's top-left corner
     * @param y the Y coordinate of the pit's top-left corner
     * @param width width of pit
     * @param height height of pit
     * @param stoneNum number of stones in a pit
     * @param pitLabel name label of a pit
     */
    void drawPitShape(Graphics g, int x, int y, int width, int height, int stoneNum, String pitLabel);

    /**
     * Draw a mancala in a Mancala game.
     * @param g the graphics context
     * @param x the X coordinate of the mancala's top-left corner
     * @param y the Y coordinate of the mancala's top-left corner
     * @param width width of mancala
     * @param height height of mancala
     * @param stoneNum number of stones in a mancala
     * @param pitLabel name label of a mancala
     */
    void drawMancalaShape(Graphics g, int x, int y, int width, int height, int stoneNum, String pitLabel);
}