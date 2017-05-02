import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class PitShape implements Icon {
    private int width;
    private int height;
    private int stoneNum;
    private String pitLabel;

    public PitShape(int width, int height, int stoneNum, String pitLabel) {
        this.width = width;
        this.height = height;
        this.stoneNum = stoneNum;
        this.pitLabel = pitLabel;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;

        Ellipse2D pit = new Ellipse2D.Double(x,y, width, height);

        g2.setColor(Color.black);
        g2.draw(pit);

        FontMetrics metrics = g2.getFontMetrics();
        // Determine the X coordinate for the text
        String stoneNum = Integer.toString(this.stoneNum);
        int verticalPosition = x + (width - metrics.stringWidth(stoneNum)) / 2;
        g2.drawString(stoneNum, verticalPosition, 20);
        verticalPosition = x + (width - metrics.stringWidth(pitLabel)) / 2;
        g2.drawString(pitLabel, verticalPosition, height);


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

    public String getPitLabel() {
        return pitLabel;
    }
}
