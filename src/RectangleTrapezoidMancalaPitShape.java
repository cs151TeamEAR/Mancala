import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 5/3/17.
 */

/**
 * An implementation of MancalaPitShape interface that have rectangle pits and trapezoid mancalas with
 * number of stones labels.
 */
public class RectangleTrapezoidMancalaPitShape implements MancalaPitShape {
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
    public void drawPitShape(Graphics g, int x, int y, int width, int height, int stoneNum, String pitLabel) {
        Graphics2D g2 = (Graphics2D) g;

        Rectangle2D pit = new Rectangle2D.Double(x,y, width, height);

        g2.setColor(Color.getColor("Brown"));
        g2.draw(pit);

        g2.setFont(new Font("Helvetica", Font.PLAIN, 30));
        FontMetrics metrics = g2.getFontMetrics();
        // Determine the X coordinate for the text
        String stoneNumString = Integer.toString(stoneNum);
        int verticalPosition = x + (width - metrics.stringWidth(stoneNumString)) / 2;
        g2.drawString(stoneNumString, verticalPosition, height / 2);
        verticalPosition = x + (width - metrics.stringWidth(pitLabel)) / 2;
        g2.drawString(pitLabel, verticalPosition, height);
    }

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
    public void drawMancalaShape(Graphics g, int x, int y, int width, int height, int stoneNum, String pitLabel) {
        Graphics2D g2 = (Graphics2D) g;

        int[] xCoor = {x+width/4, x+width/4*3, x+width, x+0};
        int[] yCoor = {y, y, y+height, y+height};
        Polygon mancala = new Polygon(xCoor, yCoor, xCoor.length);

        g2.draw(mancala);

        g2.setFont(new Font("Helvetica", Font.PLAIN, 30));
        FontMetrics metrics = g2.getFontMetrics();
        // Determine the X coordinate for the text
        String stoneNumString = Integer.toString(stoneNum);
        int horizontalPostition = x + (width - metrics.stringWidth(stoneNumString)) / 2;
        g2.drawString(stoneNumString, horizontalPostition, height / 2);
        horizontalPostition = x + (width - metrics.stringWidth(pitLabel)) / 2;
        g2.drawString(pitLabel, horizontalPostition, height - 5);
    }
}