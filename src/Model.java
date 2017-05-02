import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */
public class Model {
    private enum Player {
        A, B
    }
    private Player currentPlayer;
    private boolean gameHasEnded;
    private String currentMessage;
    private static final int NUMBER_OF_PITS_MANCALAS = 14;
    private int[] pitMancalaArray;

    private ArrayList<ChangeListener> listeners;

    public int[] getPitStoneNumArray() {
        return pitMancalaArray.clone();
    }

    public Model(int initialStoneNumber) {
        pitMancalaArray = new int[NUMBER_OF_PITS_MANCALAS]; // Mancalas are at arrary[0] & [7], others are pits.
        for (int i = 0; i < NUMBER_OF_PITS_MANCALAS; i++) {
            // skip MancalaA and MancalaB
            if (i == Pit.MancalaA.ordinal() || i == Pit.MancalaB.ordinal()) {
                pitMancalaArray[i] = 0;
            } else
                pitMancalaArray[i] = initialStoneNumber;
        }
        listeners = new ArrayList<>();
        // assume currentPlayer A start first
        currentPlayer = Player.A;
        gameHasEnded = false;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void attach(ChangeListener l) {
        listeners.add(l);
    }

    public void update(String pitName) {
        Pit pit = Pit.valueOf(pitName);
        if (pit == null) {
            currentMessage = "Invalid pit name.";
            System.out.println(currentMessage);
        } else if (gameHasEnded) {
            currentMessage = "Game has ended.";
            System.out.println(currentMessage);
        }
//        if (pit == Pit.MancalaA || pit == Pit.MancalaB) {
//            throw new InvalidParameterException("Selection of Mancala A or B " +
//                    "is invalid.");
//        }
        else {
            int pitNum = pit.ordinal();

            int currentStoneNum = pitMancalaArray[pitNum];
            if (currentStoneNum == 0) {
                currentMessage = "The pit is empty, please select other pits.";
                System.out.println(currentMessage);
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
                if (pitMancalaArray[currentPit] == 1) {
                    if (!currentPitIsMancalaA && !currentPitIsMancalaB) {
                        if (currentPitBelongstoA && currentPlayerIsA) {
                            int oppositePit = goToOppositePit(currentPit);
                            pitMancalaArray[Pit.MancalaA.ordinal()] += pitMancalaArray[oppositePit];
                            pitMancalaArray[oppositePit] = 0;
                        } else if (currentPitBelongstoB && currentPlayerIsB) {
                            int oppositePit = goToOppositePit(currentPit);
                            pitMancalaArray[Pit.MancalaB.ordinal()] += pitMancalaArray[oppositePit];
                            pitMancalaArray[oppositePit] = 0;
                        }
                    }
                }


                if (currentPlayerIsA && currentPitIsMancalaA) {
                    // give one free turn to currentPlayer A
                    setCurrentPlayer(Player.A);
                } else if (currentPlayerIsB && currentPitIsMancalaB) {
                    // give one free turn to currentPlayer B
                    setCurrentPlayer(Player.B);
                } else if (currentPlayerIsA) // give next turn to another currentPlayer
                    setCurrentPlayer(Player.B);
                else if (currentPlayerIsB)
                    setCurrentPlayer(Player.A);


                int sumOfAllPitA = 0, sumOfAllPitB = 0;
                for (int i = Pit.A1.ordinal(); i <= Pit.A6.ordinal(); i++) {
                    sumOfAllPitA += pitMancalaArray[i];
                }
                for (int i = Pit.B1.ordinal(); i <= Pit.B6.ordinal(); i++) {
                    sumOfAllPitB += pitMancalaArray[i];
                }
                if (sumOfAllPitA == 0) {
                    pitMancalaArray[Pit.MancalaB.ordinal()] += sumOfAllPitB;
                    gameHasEnded = true;
                } else if (sumOfAllPitB == 0) {
                    pitMancalaArray[Pit.MancalaA.ordinal()] += sumOfAllPitA;
                    gameHasEnded = true;
                }

                // reset currentMessage
                currentMessage = "";
            }
        }

        for (ChangeListener l : listeners) {
            l.stateChanged(new ChangeEvent(this));
        }
    }

    public int getNumStoneOf(String pitName) {
        Pit pit = Pit.valueOf(pitName);
        return pitMancalaArray[pit.ordinal()];
    }

    public int getScoreOfPlayerA() {
        if (gameHasEnded)
            return pitMancalaArray[Pit.MancalaA.ordinal()];
        else
            return 0;
    }
    public int getScoreOfPlayerB() {
        if (gameHasEnded)
            return pitMancalaArray[Pit.MancalaB.ordinal()];
        else
            return 0;
    }



    private int goToOppositePit(int currentPit) {
        // go to opposite pit
        if (currentPit < 6)
            currentPit += 7;
        else if (currentPit > 6)
            currentPit -= 7;
        else {
            currentMessage = "go to opposite pit has a problem";
            System.out.println(currentMessage);
        }
        return currentPit;
    }




}

