package parkeersimulatie.controller;
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
        tickField = new JTextField("", 5);

        tickField.addActionListener(this);

        add(tickLabel);
        add(tickField);

    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == tickField){


            tickSpeed = Integer.parseInt(tickField.getText());
        }
    }

    public int getTickSpeed(){
        return tickSpeed;
    }


}
