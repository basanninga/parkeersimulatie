package parkeersimulatie.view;



import parkeersimulatie.logic.AbstractModel;

import javax.swing.*;


public class AbstractView extends JPanel
{
    // Alle views hebben een model
    protected AbstractModel model;

    /**
     * Constructor van AbstractView dat een model verwacht bij deze view.
     *
     * @param model AbstractModel dat hoort bij deze view.
     */
    public AbstractView(AbstractModel model) {
        this.model = model;
        model.addView(this);

        // we use absolute positioning so we can set the layout to null
        setLayout(null);
    }

    /**
     Methode voor het updaten van de view.
     */
    public void updateView() {
        repaint();
    }
}