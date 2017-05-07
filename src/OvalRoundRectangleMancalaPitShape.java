import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;


/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 5/3/17.
 */

/**
 * An implementation of MancalaPitShape interface that have oval pits and round rectangle mancalas with circle stones.
 */
public class OvalRoundRectangleMancalaPitShape implements MancalaPitShape {
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

        Ellipse2D pit = new Ellipse2D.Double(x,y, width, height);

        g2.setColor(Color.black);
        g2.draw(pit);
        drawPitStones(g2, x, width, height, stoneNum);

        FontMetrics metrics = g2.getFontMetrics();
        // Determine the X coordinate for the text
        String stoneNumString = Integer.toString(stoneNum);
        int verticalPosition = x + (width - metrics.stringWidth(stoneNumString)) / 2;
        g2.drawString(stoneNumString, verticalPosition, 20);
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

        RoundRectangle2D mancala = new RoundRectangle2D.Double(x,y,width,height, 50,50);

        g2.draw(mancala);
        drawMancalaStones(g2, x, width, height, stoneNum);

        FontMetrics metrics = g2.getFontMetrics();
        // Determine the X coordinate for the text
        String stoneNumString = Integer.toString(stoneNum);
        int horizontalPostition =  x + (width - metrics.stringWidth(stoneNumString)) / 2;
        g2.drawString(stoneNumString, horizontalPostition, 20);
        horizontalPostition =  x + (width - metrics.stringWidth(pitLabel)) / 2;
        g2.drawString(pitLabel, horizontalPostition, height);
    }

    /**
     * Draw the stones inside a pit
     * @param g2 the graphics context
     * @param xPos the x coordinate of the pit
     * @param width the width of the pit
     * @param height the height of the pit
     * @param stoneNum the number of stones to be drawn in the pit
     */
    private void drawPitStones(Graphics2D g2, int xPos, int width, int height, int stoneNum) {
        double centerX = xPos + width / 2;
        double centerY = height / 1.35;
        centerX -= width / 12;
        centerY -= width / 3;
        int offset = width / 6;
        double angle = 0;

        int j = 0;
        if(stoneNum > 0) {
            Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, width / 6, width / 6);
            g2.fill(stone);
            j++;
        }
        while(j < stoneNum && j < 7) {
            double x = centerX + Math.cos(angle) * offset;
            double y = centerY + Math.sin(angle) * offset;
            Ellipse2D.Double stone = new Ellipse2D.Double(x, y, width / 6, width / 6);

            g2.fill(stone);
            angle += Math.PI / 3;
            j++;
        }
        offset += width / 6;
        while(j < stoneNum && j < 19) {
            double x = centerX + Math.cos(angle) * offset;
            double y = centerY + Math.sin(angle) * offset;
            Ellipse2D.Double stone = new Ellipse2D.Double(x, y, width / 6, width / 6);

            g2.fill(stone);
            angle += Math.PI / 6;
            j++;
        }
    }

    /**
     * Draw the stones inside a mancala
     * @param g2 the graphics context
     * @param xPos the x coordinate of the mancala
     * @param width the width of the mancala
     * @param height the height of the mancala
     * @param stoneNum the number of stones to be drawn in the mancala
     */
    public void drawMancalaStones(Graphics2D g2, int xPos, int width, int height, int stoneNum) {
        double centerX = xPos + width / 2;
        double centerY = height / 2.5;
        centerX -= width / 12;
        centerY -= width / 3;
        int offset = width / 6;
        double angle = 0;

        int j = 0;
        if(stoneNum > 0) {
            Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, width / 6, width / 6);
            g2.fill(stone);
            j++;
        }
        while(j < stoneNum && j < 7) {
            double x = centerX + Math.cos(angle) * offset;
            double y = centerY + Math.sin(angle) * offset;
            Ellipse2D.Double stone = new Ellipse2D.Double(x, y, width / 6, width / 6);

            g2.fill(stone);
            angle += Math.PI / 3;
            j++;
        }
        offset += width / 6;
        while(j < stoneNum && j < 19) {
            double x = centerX + Math.cos(angle) * offset;
            double y = centerY + Math.sin(angle) * offset;
            Ellipse2D.Double stone = new Ellipse2D.Double(x, y, width / 6, width / 6);

            g2.fill(stone);
            angle += Math.PI / 6;
            j++;
        }
        offset -= width / 6;
        centerY = 2 * height / 3;
        centerY += width / 6;
        if(stoneNum > 19) {
            Ellipse2D.Double stone = new Ellipse2D.Double(centerX, centerY, width / 6, width / 6);
            g2.fill(stone);
            j++;
        }
        while(j < stoneNum && j < 26) {
            double x = centerX + Math.cos(angle) * offset;
            double y = centerY + Math.sin(angle) * offset;
            Ellipse2D.Double stone = new Ellipse2D.Double(x, y, width / 6, width / 6);

            g2.fill(stone);
            angle += Math.PI / 3;
            j++;
        }
        offset += width / 6;
        while(j < stoneNum && j < 38) {
            double x = centerX + Math.cos(angle) * offset;
            double y = centerY + Math.sin(angle) * offset;
            Ellipse2D.Double stone = new Ellipse2D.Double(x, y, width / 6, width / 6);

            g2.fill(stone);
            angle += Math.PI / 6;
            j++;
        }
    }
}