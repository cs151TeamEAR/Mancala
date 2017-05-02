import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by robg on 4/24/17.
 */
public class MancalaShape extends PitShape {
    public MancalaShape(int width, int height, int stoneNum, String pitLabel) {
        super(width, height, stoneNum, pitLabel);
    }

    @Override
    // Is subclassing PitShape and overriding paintIcon() correct?
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;

        RoundRectangle2D mancala = new RoundRectangle2D.Double(x,y,getIconWidth(),getIconHeight(),
                50,50);

        g2.draw(mancala);

        FontMetrics metrics = g2.getFontMetrics();
        // Determine the X coordinate for the text
        String stoneNum = Integer.toString(getStoneNum());
        int horizontalPostition =  x + (getIconWidth() - metrics.stringWidth(stoneNum)) / 2;
        g2.drawString(stoneNum, horizontalPostition, 20);
        horizontalPostition =  x + (getIconWidth() - metrics.stringWidth(getPitLabel())) / 2;
        g2.drawString(getPitLabel(), horizontalPostition, getIconHeight());


    }
}
