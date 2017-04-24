import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */
public class Model {
    private static final int NUMBER_OF_PITS_MANCALAS = 14;
    private Mancala[] pitMancalaArray;
    private enum initialStoneNumber {
        three, four
    }

    public Model() {
        pitMancalaArray = new Mancala[NUMBER_OF_PITS_MANCALAS]; // Mancalas are at arrary[0] & [7], others are pits.
    }
}
