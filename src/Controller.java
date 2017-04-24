import java.util.ArrayList;

/**
 * Created by robg on 4/23/17.
 */
public class Controller {
    private Model model;
    private ArrayList<View> registeredViews;

    public Controller(Model model, View view) {
        this.model = model;
        this.registeredViews = new ArrayList<>();
        registeredViews.add(view);
    }

    public void attachView(View view) {
        registeredViews.add(view);
    }
}
