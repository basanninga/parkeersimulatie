package parkeersimulatie.controller;
import parkeersimulatie.Main.Simulator;
import parkeersimulatie.logic.*;

import javax.swing.*;
import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener {

    private JTextField tickField;
    private JLabel tickLabel;

    private int tickSpeed = 100;
    public Controller(AbstractModel model) {
        super(model);

        tickLabel = new JLabel("speed");
        add(tickLabel);

        tickField = new JTextField("", 5);
        tickField.addActionListener(this);

        add(tickField);

    }

    private void tickSpeed(){

        try{
            int speed = Integer.parseInt(tickField.getText());

            Simulator.tickPause = speed;

        } catch (NumberFormatException e){
            // TODO notify user that the field is not a number!
        }
    }




    public void actionPerformed(ActionEvent event){
        if(event.getSource() == tickField){
            this.tickSpeed();
        }
    }



}
