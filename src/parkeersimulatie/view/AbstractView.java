package parkeersimulatie.view;



import parkeersimulatie.logic.AbstractModel;

import javax.swing.*;

/**
 * Class AbstractView
 * This is the abstract view class that all views should extend.
 *
 * @author Koen Hendriks
 * @version 0.1 (04-04-2016)
 */
public abstract class AbstractView extends JPanel
{
    // all views have a certain model
    protected AbstractModel model;

    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public AbstractView(AbstractModel model) {
        this.model = model;
        model.addView(this);

        // we use absolute positioning so we can set the layout to null
        setLayout(null);
    }

    public void updateView() {
        repaint();
    }

}
