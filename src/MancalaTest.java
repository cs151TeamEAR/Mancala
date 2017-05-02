import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaTest {
    public static void main(String[] args) {
        Model model = new Model(4);
        View view = new View(model);
        model.attach(view);

    }
}
