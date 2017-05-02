import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */

public class View extends JFrame {
    private static final int PIT_WIDTH = 70;
    private static final int PIT_HEIGHT = 90;
    private static final int MANCALA_WIDTH = 55;
    private static final int MANCALA_HEIGHT = 150;

    public static final int FRAME_WIDTH = 700;
    public static final int FRAME_HEIGHT = 300;
    public static final int PIT_BUTTON_WIDTH_RATIO = 9;
    public static final int PIT_BUTTON_HEIGHT_RATIO = 4;

    private JTextField messageField;
    private JButton pitButton;


    //    private int stoneNum;
    private ArrayList<Integer> pitsStoneNum;
    private Model model;

//    public JPanel drawSixPitsTopPanel() {
//        JPanel sixPitsTopPanel = new JPanel();
//        for (int i = 6; i >= 1; i--) {
//            String pitLabel = "B" + Integer.toString(i);
//
//            // button to hold stoneNum at top, pitShape at middle, and pitLabel at bottom
//            pitShape = new PitShape(PIT_WIDTH, PIT_HEIGHT,
//                    model.getNumStoneOf(pitLabel), pitLabel);
//            pitButton = new JButton(pitShape);
//            pitButton.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    model.update(pitLabel);
//                    System.out.println("hello");
//                }
//            });
//
//            // add whole panel into an array panel
//            sixPitsTopPanel.add(pitButton);
//        }
//        return sixPitsTopPanel;
//    }

    public View(Model model) {
        messageField = new JTextField();
        this.model = model;

        model.attach(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                messageField.setText(model.getCurrentMessage());
                repaint();
            }
        });


//        this.stoneNum = stoneNum;
        setLocation(40,228);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);

        JPanel sixPitsTopPanel = new JPanel(new FlowLayout());
        JPanel sixPitsBottomPanel = new JPanel(new FlowLayout());


        for (int i = 6; i >= 1; i--) {
            String pitLabel = "B" + Integer.toString(i);
            System.out.println("initializing pit" + pitLabel);

            // button to hold stoneNum at top, pitShape at middle, and pitLabel at bottom
            PitShape pitShape = new PitShape(PIT_WIDTH, PIT_HEIGHT,
                    model.getNumStoneOf(pitLabel), pitLabel);
            pitButton = new JButton(pitShape);
            pitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    model.update(pitLabel);
                    System.out.println("updating" + pitLabel);
                }
            });

            sixPitsTopPanel.add(pitButton);
        }
        for (int i = 1; i <= 6; i++) {
            String pitLabel = "A" + Integer.toString(i);
            System.out.println("initializing pit" + pitLabel);

            // button to hold stoneNum at top, pitShape at middle, and pitLabel at bottom
            PitShape pitShape = new PitShape(PIT_WIDTH, PIT_HEIGHT,
                    model.getNumStoneOf(pitLabel), pitLabel);
            pitButton = new JButton(pitShape);
            pitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    model.update(pitLabel);
                    System.out.println("updating" + pitLabel);
                }
            });

            sixPitsBottomPanel.add(pitButton);
        }
//            // panel to hold label at top and pit at bottom
//            JButton pitPanel = new JButton();
//
//
//            // make pit label and pit itself
//            JLabel pitLabel = new JLabel("A" + Integer.toString(i));
//            pitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//            JLabel pit = new JLabel(new PitShape(PIT_WIDTH, PIT_HEIGHT, stoneNum));
//            pit.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//            // make label and pit centered in the panel
//            pitPanel.setLayout(new BoxLayout(pitPanel, BoxLayout.Y_AXIS));
//
//            // add pit and then label into panel
//            pitPanel.add(pit);
//            pitPanel.add(pitLabel);
//
//            // add whole panel into an array panel
//            sixPitsBottomPanel.add(pitPanel);
//        }

        // add top and bottom six pits into a panel
        JPanel twelvePitsPanel = new JPanel();
        twelvePitsPanel.setLayout(new BoxLayout(twelvePitsPanel, BoxLayout.Y_AXIS));
        twelvePitsPanel.add(sixPitsTopPanel);
        twelvePitsPanel.add(sixPitsBottomPanel);

        // making left and right mancalas
        MancalaShape mA = new MancalaShape(MANCALA_WIDTH, MANCALA_HEIGHT,
                model.getNumStoneOf("MancalaA"), "A");
        JButton mancalaA = new JButton(mA);
        JButton mancalaB = new JButton(new MancalaShape(MANCALA_WIDTH, MANCALA_HEIGHT,
                model.getNumStoneOf("MancalaB"), "B"));
        mancalaA.setEnabled(false);
        mancalaA.setBorderPainted(false);
        mancalaB.setEnabled(false);
        mancalaB.setBorderPainted(false);



        JPanel wholePanel = new JPanel();
        wholePanel.add(mancalaB);
        wholePanel.add(twelvePitsPanel);
        wholePanel.add(mancalaA);

        add(wholePanel);

        messageField.setText(model.getCurrentMessage());
        add(messageField, BorderLayout.SOUTH);



        // how to solve missing lines segment around multiple shapes?
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
