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

    private JButton start;
    private JButton pause;


    public Controller(AbstractModel model) {
        super(model);

        setLayout(null);

        tickLabel = new JLabel("speed");

        tickField = new JTextField("100", 5);

        start = new JButton("START");
        pause = new JButton("PAUSE");

        pause.addActionListener(this);
        start.addActionListener(this);



        tickField.addActionListener(this);

        tickLabel.setBounds(65,50,200,20);
        tickField.setBounds(10, 50, 50, 20);

        start.setBounds(500, 60, 100, 20);
        pause.setBounds(800, 60, 100, 20);

        add(tickLabel);
        add(tickField);

        add(start);
        add(pause);





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

    private void startPressed() {
        Simulator.running = true;
    }
    private void pausePressed() {
        Simulator.running = false;
    }


    public void actionPerformed(ActionEvent event){
        if(event.getSource() == tickField){
            this.tickSpeed();
        }
        if(event.getSource() == start){
            this.startPressed();
        }
        if(event.getSource() == pause){
            this.pausePressed();
        }
    }





}
