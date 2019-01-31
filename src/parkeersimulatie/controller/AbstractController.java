package parkeersimulatie.controller;

import parkeersimulatie.logic.AbstractModel;
import javax.swing.*;


public abstract class AbstractController extends JPanel {

        protected AbstractModel model;

    /**
     Constructor met een model die bij een controller hoort.
     */
    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
