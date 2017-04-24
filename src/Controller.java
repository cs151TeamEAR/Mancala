import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */
public class Controller {
    private Model model;
    private ArrayList<View> registeredViews;

    public Controller(Model model, ArrayList<View> registeredViews) {
        this.model = model;
        this.registeredViews = registeredViews;
    }

}
