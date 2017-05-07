/**
 * Created by Robin Goh, Arselan Alvi and Erin Yang on 5/3/17.
 */

/**
 * The class MancalaTest contains the main method that will start the Mancala game.
 */
public class MancalaTest {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        model.attach(view);
    }
}
