package parkeersimulatie.logic;

import parkeersimulatie.view.AbstractView;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractModel
{
    private List<AbstractView> views;

    /**
     Constructor die views in een arraylist stopt
     */
    public AbstractModel() {
        this.views = new ArrayList<>();


    }

    /**
     @param view
     * voegt een view toe aan de Arraylist
     */
    public void addView(AbstractView view) {
        this.views.add(view);
    }


    /**
     * Update de views
     */
    public void notifyViews(){
        for (AbstractView view : this.views) {
            view.updateView();
        }
    }
}
