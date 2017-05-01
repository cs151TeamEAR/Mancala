import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by robg on 4/24/17.
 */
public class MancalaShape extends PitShape {
    public MancalaShape(int width, int height, int stoneNum) {
        super(width, height, stoneNum);
    }

    @Override
    // Is subclassing PitShape and overriding paintIcon() correct?
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;

        RoundRectangle2D mancala = new RoundRectangle2D.Double(0,0,getIconWidth(),getIconHeight(),
                50,50);

        g2.draw(mancala);


    }
}
