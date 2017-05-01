import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */

public class View extends JFrame {
    private static final int PIT_WIDTH = 55;
    private static final int PIT_HEIGHT = 70;
    private static final int MANCALA_WIDTH = 55;
    private static final int MANCALA_HEIGHT = 150;
    private int stoneNum;
    private ArrayList<Integer> pitsStoneNum;

    public View(int stoneNum) {
        this.stoneNum = stoneNum;
    }

    public void setStoneNum(int stoneNum) {
        this.stoneNum = stoneNum;
    }

    public void draw() {
        setLocation(328,408);

        JPanel sixPitsTopPanel = new JPanel(new FlowLayout());
        JPanel sixPitsBottomPanel = new JPanel(new FlowLayout());

        // how can I adjust spacing between each two pits?
        for (int i = 6; i >= 1; i--) {
            // panel to hold label at top and pit at bottom
            JPanel pitPanel = new JPanel();

            // make pit label and pit itself
            JLabel pitLabel = new JLabel("B" + Integer.toString(i));
            pitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel pit = new JLabel(new PitShape(PIT_WIDTH, PIT_HEIGHT, stoneNum));
            pit.setAlignmentX(Component.CENTER_ALIGNMENT);

            // make label and pit centered in the panel
            pitPanel.setLayout(new BoxLayout(pitPanel, BoxLayout.Y_AXIS));

            // add label and then pit into panel
            pitPanel.add(pitLabel);
            pitPanel.add(pit);

            // add whole panel into an array panel
            sixPitsTopPanel.add(pitPanel);
        }
        for (int i = 1; i <= 6; i++) {
            // panel to hold label at top and pit at bottom
            JPanel pitPanel = new JPanel();

            // make pit label and pit itself
            JLabel pitLabel = new JLabel("A" + Integer.toString(i));
            pitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel pit = new JLabel(new PitShape(PIT_WIDTH, PIT_HEIGHT, stoneNum));
            pit.setAlignmentX(Component.CENTER_ALIGNMENT);

            // make label and pit centered in the panel
            pitPanel.setLayout(new BoxLayout(pitPanel, BoxLayout.Y_AXIS));

            // add pit and then label into panel
            pitPanel.add(pit);
            pitPanel.add(pitLabel);

            // add whole panel into an array panel
            sixPitsBottomPanel.add(pitPanel);
        }

        // add top and bottom six pits into a panel
        JPanel twelvePitsPanel = new JPanel();
        twelvePitsPanel.setLayout(new BoxLayout(twelvePitsPanel, BoxLayout.Y_AXIS));
        twelvePitsPanel.add(sixPitsTopPanel);
        twelvePitsPanel.add(sixPitsBottomPanel);

        // making left and right mancalas
        JLabel leftMancala = new JLabel(new MancalaShape(MANCALA_WIDTH,MANCALA_HEIGHT, stoneNum));
        leftMancala.setAlignmentY(Component.CENTER_ALIGNMENT);
        // how to move the text to below of MancalaShape icon instead of on the right (current state)?
        leftMancala.setText("A");
        JLabel rightMancala = new JLabel(new MancalaShape(MANCALA_WIDTH,MANCALA_HEIGHT, stoneNum));
        leftMancala.setAlignmentY(Component.CENTER_ALIGNMENT);
        rightMancala.setText("B");

        // adding into frame
        setLayout(new BorderLayout());
        // how to center left and right mancalas around y-axis?
        add(leftMancala, BorderLayout.EAST);
        add(rightMancala, BorderLayout.WEST);
        add(twelvePitsPanel, BorderLayout.CENTER);


        // how to solve missing lines segment around multiple shapes?
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        View view = new View(5);
        view.draw();


    }
}
