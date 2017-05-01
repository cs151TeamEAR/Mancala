import javax.swing.event.ChangeListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */
public class Model {
    private enum InitialStoneNumber {
        three(3), four(4);
        private int stoneNum;

        private InitialStoneNumber(int stoneNum) {
            this.stoneNum = stoneNum;
        }

        public int getStoneNum() {
            return stoneNum;
        }
    }
    private enum PitName {
        A1, A2, A3, A4, A5, A6, MancalaA, B1, B2, B3, B4, B5, B6, MancalaB
    }
    private static final int NUMBER_OF_PITS_MANCALAS = 14;
    private int[] pitMancalaArray;

    private ArrayList<ChangeListener> listeners;

    public Model(InitialStoneNumber initialStoneNumber) {
        pitMancalaArray = new int[NUMBER_OF_PITS_MANCALAS]; // Mancalas are at arrary[0] & [7], others are pits.
        for (int e : pitMancalaArray) {
            e = initialStoneNumber.getStoneNum();
        }
        listeners = new ArrayList<>();
    }

    public void attach(ChangeListener l) {
        listeners.add(l);
    }

    public void update(PitName pit) {
        // if is A
        if (pit == PitName.MancalaA || pit == PitName.MancalaB) {
            throw new InvalidParameterException("Selection of Mancala A or B " +
                    "is invalid.");
        }
        int pitNum = pit.ordinal();
        boolean currentPitBelongstoMancalaA = false;
        boolean currentPitBelongstoMancalaB = false;
        currentPitBelongstoMancalaA = pitNum <= PitName.A6.ordinal();
        currentPitBelongstoMancalaB = !currentPitBelongstoMancalaA;


        int nextPit = pitNum + 1;
        int currentStoneNum = pitMancalaArray[pitNum];
        pitMancalaArray[pitNum] = 0;
        for (int i = 0, j = 0; i < currentStoneNum; i++, j++) {
            nextPit = (pitNum + 1 + j) % NUMBER_OF_PITS_MANCALAS;
            // skip MancalaB
            if (currentPitBelongstoMancalaA) {
                if (nextPit == PitName.MancalaB.ordinal()) {
                    i--;
                    continue;
                }
            } else if (currentPitBelongstoMancalaB) {
                if (nextPit == PitName.MancalaA.ordinal()) {
                    i--;
                    continue;
                }
            }
            pitMancalaArray[nextPit]++;
        }
        int oppositePit;
        if (nextPit != PitName.MancalaA.ordinal() && nextPit != PitName.MancalaB.ordinal())
            oppositePit = goToOppositePit(nextPit);



    }



    private int goToOppositePit(int nextPit) {
        // go to opposite pit
        if (nextPit < 6)
            nextPit += 7;
        else if (nextPit > 6)
            nextPit -= 7;
        else {
            throw new InvalidParameterException("go to opposite pit has a problem");
        }
        return nextPit;
    }




}

