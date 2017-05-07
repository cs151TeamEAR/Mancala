import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 4/23/17.
 */

/**
 * A model class that represent the logic of a Mancala game with the option to undo.
 */
public class Model {

    public static final String LAST_STONE_DROP_IN_PIT_IS_EMPTY_ON_YOUR_SIDE_MESSAGE = "Last stone dropped is empty " +
            "pit on your side. Putting everything on opposite pit into your Mancala.\n";
    public static final String INVALID_PIT_NAME_MESSAGE = "Invalid pit name.\n";
    public static final String GAME_HAS_ENDED_MESSAGE = "Game has ended.\n";
    public static final String SELECT_YOUR_OWN_PITS_A_MESSAGE = "Player A, please select your own pits which are " +
            "labeled A.\n";
    public static final String SELECT_YOUR_OWN_PITS_B_MESSAGE = "Player B, please select your own pits which are " +
            "labeled B.\n";
    public static final String THE_PIT_IS_EMPTY_MESSAGE = "The pit is empty, please select other pits.\n";
    public static final String YOU_GET_1_FREE_TURN = "You get 1 free turn!\n";
    public static final String CANNOT_UNDO_ANYMORE_MESSAGE = "Cannot undo anymore.\n";
    public static final String NOW_IS_PLAYER_MESSAGE = "Current player is ";
    public static final String INITIAL_GAME_MESSAGE = "Player A starts first.";

    /**
     * Player A and B with count of undos.
     */
    private enum Player {
        A(0), B(0);

        Player(int countOfUndo) {
            this.countOfUndo = countOfUndo;
        }

        private int countOfUndo;

        public int getCountOfUndo() {
            return countOfUndo;
        }

        /**
         * Increment count of undo by one.
         */
        public void incrementCountOfUndo() {
            countOfUndo++;
        }

        /**
         * Reset count of undo to zero.
         */
        public void resetCountOfUndo() {
            countOfUndo = 0;
        }
    }
    private Player currentPlayer;
    private Player previousPlayer;
    private boolean gameHasEnded;
    private String currentMessage;
    private static final int NUMBER_OF_PITS_MANCALAS = 14;
    private int[] pitMancalaArray;
    private int[] undoPitMancalaArray;
    private static final int UNDO_ALLOWANCE = 3;

    private ArrayList<ChangeListener> listeners;

    /**
     * Get the pitStoneNumArray array.
     * @return a copy of pitStoneNumArray
     */
    public int[] getPitStoneNumArray() {
        return pitMancalaArray.clone();
    }

    /**
     * Create a Mancala model
     */
    public Model() {
        pitMancalaArray = new int[NUMBER_OF_PITS_MANCALAS]; // Mancalas are at arrary[0] & [7], others are pits.
        undoPitMancalaArray = pitMancalaArray;
        listeners = new ArrayList<>();
        // assume currentPlayer A start first
        currentPlayer = Player.A;
        previousPlayer = Player.A;
        gameHasEnded = false;
        currentMessage = INITIAL_GAME_MESSAGE;
    }

    /**
     * Sets the initialStoneNum property, which determines how many stones for the game to start with.
     * @param initialStoneNum the initial number of stones of the game
     */
    public void setInitialStoneNum(int initialStoneNum) {
        for (int i = 0; i < NUMBER_OF_PITS_MANCALAS; i++) {
            // skip MancalaA and MancalaB
            if (i == Pit.MancalaA.ordinal() || i == Pit.MancalaB.ordinal()) {
                pitMancalaArray[i] = 0;
            } else
                pitMancalaArray[i] = initialStoneNum;
        }
    }

    /**
     * Sets the currentPlayer property, which represents the current player
     * @param currentPlayer the current player
     */
    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the previousPlayer property, which represents the player who initiates undo.
     * @param previousPlayer the previous player before current player
     */
    private void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    /**
     * Gets the currentMessage property, which is a description of the current state of the game.
     * @return the currentMessage property
     */
    public String getCurrentMessage() {
        return currentMessage;
    }

    /**
     * Attach a ChangeListener to this game model class.
     * @param l a ChangeListener
     */
    public void attach(ChangeListener l) {
        listeners.add(l);
    }

    /**
     * Updates the number of stones for all pits in game after player has selected one or choose to undo.
     * Sets currentMessage of the game depending on the state of the game. Tracks if the game has ended.
     * @param pitName the name of the pit that player chosen to take the stones from
     */
    public void update(String pitName) {
        if (gameHasEnded)
            return;

        Pit pit = Pit.valueOf(pitName);
        if (pit == null) {
            currentMessage = INVALID_PIT_NAME_MESSAGE;
            System.out.println(currentMessage);
        } else if (pit.ordinal() > Pit.MancalaA.ordinal() && currentPlayer == Player.A) {
            currentMessage = SELECT_YOUR_OWN_PITS_A_MESSAGE;
        } else if (pit.ordinal() < Pit.MancalaA.ordinal() && currentPlayer == Player.B) {
            currentMessage = SELECT_YOUR_OWN_PITS_B_MESSAGE;
        } else {
            undoPitMancalaArray = pitMancalaArray.clone();
            currentMessage = "";
            int pitNum = pit.ordinal();

            int currentStoneNum = pitMancalaArray[pitNum];
            if (currentStoneNum == 0) {
                currentMessage = THE_PIT_IS_EMPTY_MESSAGE;
            } else {

                int currentPit = pitNum + 1;
                pitMancalaArray[pitNum] = 0;
                for (int i = 0, j = 0; i < currentStoneNum; i++, j++) {
                    currentPit = (pitNum + 1 + j) % NUMBER_OF_PITS_MANCALAS;
                    // skip MancalaB
                    if (currentPlayer == Player.A) {
                        if (currentPit == Pit.MancalaB.ordinal()) {
                            i--;
                            continue;
                        }
                    } else if (currentPlayer == Player.B) {
                        if (currentPit == Pit.MancalaA.ordinal()) {
                            i--;
                            continue;
                        }
                    }
                    pitMancalaArray[currentPit]++;
                }

                boolean currentPitBelongstoA = (currentPit <= Pit.A6.ordinal());
                boolean currentPitBelongstoB = (!currentPitBelongstoA);
                boolean currentPitIsMancalaA = (currentPit == Pit.MancalaA.ordinal());
                boolean currentPitIsMancalaB = (currentPit == Pit.MancalaB.ordinal());
                boolean currentPlayerIsA = (currentPlayer == Player.A);
                boolean currentPlayerIsB = (currentPlayer == Player.B);

                // if the last stone player dropped is in an empty pit on your side,
                if (pitMancalaArray[currentPit] == 1 ) {
                    if (!currentPitIsMancalaA && !currentPitIsMancalaB) {
                        if (currentPitBelongstoA && currentPlayerIsA) {
                            int oppositePit = goToOppositePit(currentPit);
                            pitMancalaArray[Pit.MancalaA.ordinal()] += pitMancalaArray[oppositePit] +
                                    pitMancalaArray[currentPit];
                            pitMancalaArray[oppositePit] = 0;
                            pitMancalaArray[currentPit] = 0;
                            currentMessage += LAST_STONE_DROP_IN_PIT_IS_EMPTY_ON_YOUR_SIDE_MESSAGE;
                        } else if (currentPitBelongstoB && currentPlayerIsB) {
                            int oppositePit = goToOppositePit(currentPit);
                            pitMancalaArray[Pit.MancalaB.ordinal()] += pitMancalaArray[oppositePit]+
                                    pitMancalaArray[currentPit];
                            pitMancalaArray[oppositePit] = 0;
                            pitMancalaArray[currentPit] = 0;
                            currentMessage += LAST_STONE_DROP_IN_PIT_IS_EMPTY_ON_YOUR_SIDE_MESSAGE;
                        }
                    }
                }

                if (currentPlayer != previousPlayer && previousPlayer != null)
                    previousPlayer.resetCountOfUndo();


                if (currentPlayerIsA && currentPitIsMancalaA) {
                    // give one free turn to currentPlayer A
                    setCurrentPlayer(Player.A);
                    setPreviousPlayer(Player.A);
                    currentMessage += YOU_GET_1_FREE_TURN;
                } else if (currentPlayerIsB && currentPitIsMancalaB) {
                    // give one free turn to currentPlayer B
                    setCurrentPlayer(Player.B);
                    setPreviousPlayer(Player.B);
                    currentMessage += YOU_GET_1_FREE_TURN;
                } else if (currentPlayerIsA) { // give next turn to another currentPlayer
                    setCurrentPlayer(Player.B);
                    setPreviousPlayer(Player.A);
                } else if (currentPlayerIsB) {
                    setCurrentPlayer(Player.A);
                    setPreviousPlayer(Player.B);
                }

                int sumOfAllPitA = 0, sumOfAllPitB = 0;
                for (int i = Pit.A1.ordinal(); i <= Pit.A6.ordinal(); i++) {
                    sumOfAllPitA += pitMancalaArray[i];
                }
                for (int i = Pit.B1.ordinal(); i <= Pit.B6.ordinal(); i++) {
                    sumOfAllPitB += pitMancalaArray[i];
                }
                if (sumOfAllPitA == 0) {
                    pitMancalaArray[Pit.MancalaB.ordinal()] += sumOfAllPitB;
                    for (int i = Pit.B1.ordinal(); i <= Pit.B6.ordinal(); i++) {
                        pitMancalaArray[i] = 0;
                    }
                    gameHasEnded = true;
                } else if (sumOfAllPitB == 0) {
                    pitMancalaArray[Pit.MancalaA.ordinal()] += sumOfAllPitA;
                    for (int i = Pit.A1.ordinal(); i <= Pit.A6.ordinal(); i++) {
                        pitMancalaArray[i] = 0;
                    }
                    gameHasEnded = true;
                }

                // reset currentMessage
                currentMessage += NOW_IS_PLAYER_MESSAGE + currentPlayer + ".\n";
            }
        }

        if (gameHasEnded) {
            currentMessage += GAME_HAS_ENDED_MESSAGE;
            int playerAScore = getScoreOfPlayerA();
            int playerBScore = getScoreOfPlayerB();
            currentMessage = "Player A score: " + playerAScore + "\nPlayer B score: " + playerBScore + "\n";
            if (playerAScore > playerBScore) {
                currentMessage += "Player " + Player.A.name() + " wins!";
            } else if (playerBScore > playerAScore) {
                currentMessage += "Player " + Player.B.name() + " wins!";
            } else
                currentMessage += "Both player ties!";
        }
        notifyListeners();

    }

    /**
     * Gets number of stones inside a pit.
     * @param pitName the name of the pit
     * @return the number of stones inside the pit
     */
    public int getNumStoneOf(String pitName) {
        Pit pit = Pit.valueOf(pitName);
        return pitMancalaArray[pit.ordinal()];
    }

    /**
     * Gets the final score of Player A.
     * @return the final score number if game has ended; else 0
     */
    public int getScoreOfPlayerA() {
        if (gameHasEnded)
            return pitMancalaArray[Pit.MancalaA.ordinal()];
        else
            return 0;
    }

    /**
     * Gets the final score of Player B.
     * @return the final score number if game has ended; else 0
     */
    public int getScoreOfPlayerB() {
        if (gameHasEnded)
            return pitMancalaArray[Pit.MancalaB.ordinal()];
        else
            return 0;
    }

    /**
     * Notifies all changeListeners attached to this game model class.
     */
    public void notifyListeners() {
        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Goes to the opposite pit of a pit.
     * @param currentPit the current selected pit
     * @return the opposite pit
     */
    private int goToOppositePit(int currentPit) {
        // go to opposite pit
        if (currentPit == Pit.MancalaA.ordinal() || currentPit == Pit.MancalaB.ordinal()) {
            currentMessage = "go to opposite pit has a problem";
            System.out.println(currentMessage);
        } else
            currentPit = 12 - currentPit;

        return currentPit;
    }

    /**
     * Undo the game to one previous state and update current undo status of the game.
     */
    public void undo() {
        gameHasEnded = false;

        if (previousPlayer.getCountOfUndo() < 3) {
            if (undoPitMancalaArray.equals(pitMancalaArray)) {
                currentMessage += CANNOT_UNDO_ANYMORE_MESSAGE;
            } else {
                pitMancalaArray = undoPitMancalaArray;
                previousPlayer.incrementCountOfUndo();
                currentPlayer = previousPlayer;
                currentMessage = "Player " + currentPlayer.name() + " has undo " + currentPlayer.getCountOfUndo() +
                        " times. ";
            currentMessage += "You have " + (UNDO_ALLOWANCE - currentPlayer.getCountOfUndo()) +
                    " undo left.\n";
            }
        } else {
            currentMessage += "You, Player " + previousPlayer.name() + ", cannot undo more than " + UNDO_ALLOWANCE + " times.\n";
        }
        notifyListeners();
    }
}