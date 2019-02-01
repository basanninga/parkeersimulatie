package parkeersimulatie.view;



import parkeersimulatie.logic.AbstractModel;

import javax.swing.*;

/**
 * Class AbstractView
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */

public class AbstractView extends JPanel
{
    protected AbstractModel model;

    /**
     * Constructor van AbstractView dat een model verwacht bij deze view.
     *
     * @param model AbstractModel dat hoort bij deze view.
     */
    public AbstractView(AbstractModel model) {
        this.model = model;
        model.addView(this);

        setLayout(null);
    }

    /**
     Methode voor het updaten van de view.
     */
    public void updateView() {
        repaint();
    }
}