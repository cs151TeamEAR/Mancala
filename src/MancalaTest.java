import javax.swing.*;

public class MancalaTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLocation(448,365);
        int pitHeight = 500;
        int pitWidth = pitHeight * 9 /10;
        Icon pit = new PitShape(pitWidth,pitHeight,3);
        JLabel label = new JLabel(pit);

        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
