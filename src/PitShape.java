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



    }

    public int getIconWidth() {
        return width;
    }

    public int getIconHeight() {
        return height;
    }
}
