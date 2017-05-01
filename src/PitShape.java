import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PitShape implements Icon {
    private int width;
    private int height;
    private int stoneNum;
    public PitShape(int width, int height, int stoneNum) {
        this.width = width;
        this.height = height;
        this.stoneNum = 4;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;

        Ellipse2D pit = new Ellipse2D.Double(0,0,width,height);

        g2.setColor(Color.black);
        g2.draw(pit);
        // how to not hardcode the following string position?
        g2.drawString(Integer.toString(stoneNum), width/2 - 4, 20);


    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }

    public int getStoneNum() {
        return stoneNum;
    }

    public void setStoneNum(int stoneNum) {
        this.stoneNum = stoneNum;
    }
}
