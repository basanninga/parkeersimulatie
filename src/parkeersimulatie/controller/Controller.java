package parkeersimulatie.controller;
import parkeersimulatie.Main.Simulator;
import parkeersimulatie.logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener {

    private JDialog frame;
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
            if(speed >= 0) {
                Simulator.tickPause = speed;
            } else{
                JOptionPane.showMessageDialog(frame,
                        "Please enter a positive number.");
            }
        } catch (NumberFormatException e){

            JOptionPane.showMessageDialog(frame,
                    "Please enter a number.");

        }
    }




    public void actionPerformed(ActionEvent event){
        if(event.getSource() == tickField){
            this.tickSpeed();
        }
    }



}
