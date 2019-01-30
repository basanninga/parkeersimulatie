package parkeersimulatie.controller;



import parkeersimulatie.logic.AbstractModel;

import javax.swing.*;


public abstract class AbstractController extends JPanel {

    // A controller should have a certain instance of the AbstractModel
    protected AbstractModel model;




    public AbstractController(AbstractModel model) {
        this.model = model;
    }
}
