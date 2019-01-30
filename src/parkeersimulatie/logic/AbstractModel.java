package parkeersimulatie.logic;



import parkeersimulatie.view.AbstractView;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractModel
{
    // List of views that are linked to the model
    private List<AbstractView> views;


    public AbstractModel() {
        this.views = new ArrayList<>();


    }


    public void addView(AbstractView view) {
        this.views.add(view);
    }

    public void notifyViews(){
        for (AbstractView view : this.views) {
            view.updateView();
        }
    }
}
