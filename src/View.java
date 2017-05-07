import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 4/23/17.
 */

/**
 * A frame that displays game starting options and a Mancala game once options are selected.
 */
public class View extends JFrame implements ChangeListener {
    private static final int PIT_WIDTH = 70;
    private static final int PIT_HEIGHT = 90;
    private static final int MANCALA_WIDTH = 55;
    private static final int MANCALA_HEIGHT = 150;

    public static final int FRAME_WIDTH = 750;
    public static final int FRAME_HEIGHT = 350;
    public static final String ASK_TO_SELECT_BEFORE_GAME_START_MESSAGE = "Please select stone number in each pit" +
            " and board style in order to start the game.";

    private int initialStoneNum;

    private JTextArea messageArea;
    private MancalaPitShape mancalaPitShape;
    private PitIcon[] pitIcons;
    private JButton[] pitButtons;
    private JButton undoButton;
    private JPanel sixPitsTopPanel;
    private JPanel sixPitsBottomPanel;
    private JPanel twelvePitsPanel;


    private int[] pitsStoneNum;
    private Model model;

    /**
     * Creates a Mancala game starting options GUI.
     * @param model a reference to the Mancala game model
     */
    public View(Model model) {
        this.model = model;

        JLabel initialStoneNumLabel = new JLabel("Initial stone number in each pits:");
        JLabel boardStyleLabel = new JLabel("Board style:");

        JButton initialStoneNumIs3Button = new JButton("3");
        JButton initialStoneNumIs4Button = new JButton("4");
        ActionListener initialStoneActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initialStoneNum = Integer.parseInt( ( (JButton) e.getSource() ).getText() );
            }
        };
        initialStoneNumIs3Button.addActionListener(initialStoneActionListener);
        initialStoneNumIs4Button.addActionListener(initialStoneActionListener);

        JButton ovalAndRoundRecStyleButton = new JButton("1. Oval Pit, Round Rectangle Mancala" +
                ", Circle stones");
        JButton rectangleAndTrapezoidStyleButton = new JButton("2. Rectangle Pit, Trapezoid Mancala" +
                ", Label stones");
        ActionListener styleSelectionActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                char selection = sourceButton.getText().charAt(0);
                switch (selection) {
                    case '1' : mancalaPitShape = new OvalRoundRectangleMancalaPitShape();
                    break;
                    case '2' : mancalaPitShape = new RectangleTrapezoidMancalaPitShape();
                    break;
                }
            }
        };
        ovalAndRoundRecStyleButton.addActionListener(styleSelectionActionListener);
        rectangleAndTrapezoidStyleButton.addActionListener(styleSelectionActionListener);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (initialStoneNum == 0 || mancalaPitShape == null) {
                    messageArea.setText(ASK_TO_SELECT_BEFORE_GAME_START_MESSAGE);
                } else {
                    getContentPane().removeAll();
                    drawGameInterface();
                }
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        add(initialStoneNumLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        add(initialStoneNumIs3Button, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        add(initialStoneNumIs4Button, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        add(boardStyleLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        add(ovalAndRoundRecStyleButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        add(rectangleAndTrapezoidStyleButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        add(startGameButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        messageArea = new JTextArea();
        add(messageArea, gridBagConstraints);
        messageArea.setEditable(false);

        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Display a Mancala game GUI.
     */
    private void drawGameInterface() {
        model.setInitialStoneNum(initialStoneNum);
        messageArea = new JTextArea();

        sixPitsTopPanel = new JPanel(new FlowLayout());
        sixPitsBottomPanel = new JPanel(new FlowLayout());

        pitsStoneNum = model.getPitStoneNumArray();
        pitIcons = new PitIcon[pitsStoneNum.length];
        pitButtons = new JButton[pitsStoneNum.length];
//        removeAll();

        for (int i = Pit.A1.ordinal(); i <= Pit.B6.ordinal(); i++) {
            if (i == Pit.MancalaA.ordinal())
                continue;
            Pit[] pit = Pit.values();
            String pitLabel = pit[i].name();

            pitIcons[i] = new PitIcon(PIT_WIDTH, PIT_HEIGHT, pitsStoneNum[i], pitLabel, mancalaPitShape);
            pitButtons[i] = new JButton(pitIcons[i]);
            pitButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    model.update(pitLabel);
                }
            });
            pitButtons[i].setBorderPainted(false);
            pitButtons[i].setVisible(true);
        }

        for (int i = Pit.B6.ordinal(); i >= Pit.B1.ordinal(); i--) {
            sixPitsTopPanel.add(pitButtons[i]);
        }
        for (int i = Pit.A1.ordinal(); i <= Pit.A6.ordinal(); i++) {
            sixPitsBottomPanel.add(pitButtons[i]);
        }

        twelvePitsPanel = new JPanel();
        twelvePitsPanel.setLayout(new BoxLayout(twelvePitsPanel, BoxLayout.Y_AXIS));
        twelvePitsPanel.add(sixPitsTopPanel);
        twelvePitsPanel.add(sixPitsBottomPanel);

        // making left and right mancalas
        MancalaIcon mA = new MancalaIcon(MANCALA_WIDTH, MANCALA_HEIGHT, model.getNumStoneOf(Pit.MancalaA.name()),
                "A", mancalaPitShape);
        MancalaIcon mB = new MancalaIcon(MANCALA_WIDTH, MANCALA_HEIGHT, model.getNumStoneOf(Pit.MancalaB.name()),
                "B", mancalaPitShape);
        pitIcons[Pit.MancalaA.ordinal()] = mA;
        pitIcons[Pit.MancalaB.ordinal()] = mB;
        JButton mancalaA = new JButton(mA);
        JButton mancalaB = new JButton(mB);
        mancalaA.setEnabled(false);
        mancalaA.setBorderPainted(false);
        mancalaA.setVisible(true);
        mancalaB.setEnabled(false);
        mancalaB.setBorderPainted(false);
        mancalaB.setVisible(true);
        pitButtons[Pit.MancalaA.ordinal()] = mancalaA;
        pitButtons[Pit.MancalaB.ordinal()] = mancalaB;

        JPanel wholeGamePanel = new JPanel();
        wholeGamePanel.add(mancalaB);
        wholeGamePanel.add(twelvePitsPanel);
        wholeGamePanel.add(mancalaA);

        messageArea.setText(model.getCurrentMessage());
        messageArea.setRows(3);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        messageArea.setVisible(true);
        JScrollPane messagePane = new JScrollPane(messageArea);
        messagePane.setMaximumSize(new Dimension(700,0));

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(wholeGamePanel);
        add(messagePane);


        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.undo();
            }
        });
        undoButton.setEnabled(false);
        undoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(undoButton);

        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Updates the number of stones in each pits, enables undo button and sets current description of the state of
     * the game, once the game model has changed.
     * @param e a ChangeEvent from the game model class
     */
    public void stateChanged(ChangeEvent e) {
        undoButton.setEnabled(true);
        messageArea.setText(model.getCurrentMessage());
        pitsStoneNum = model.getPitStoneNumArray();

        for (int i = 0; i < pitIcons.length; i++) {
            pitIcons[i].setStoneNum(pitsStoneNum[i]);
        }

        repaint();
    }
}