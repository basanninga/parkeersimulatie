package parkeersimulatie.controller;

import parkeersimulatie.logic.AbstractModel;
import javax.swing.*;

/**
 * Class AbstractController
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
public abstract class AbstractController extends JPanel {

        protected AbstractModel model;

    /**
     Constructor met een model die bij een controller hoort.
     */
    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
