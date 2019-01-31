package parkeersimulatie.controller;
import parkeersimulatie.Main.Simulator;
import parkeersimulatie.logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener {

    private JDialog frame;

    private JTextField tickField;
    private JTextField enterSpeedField;
    private JTextField paymentSpeedField;
    private JTextField exitSpeedField;

    private JLabel tickLabel;

    private JButton start;
    private JButton pause;




    public Controller(AbstractModel model) {
        super(model);

        setLayout(null);

        tickLabel = new JLabel("Snelheid Simulatie");
        tickField = new JTextField("100", 5);

        enterSpeedField = new JTextField("6", 3);
        paymentSpeedField = new JTextField("7", 3);
        exitSpeedField = new JTextField("5", 3);


        start = new JButton("START");
        pause = new JButton("PAUSE");

        pause.addActionListener(this);
        start.addActionListener(this);

        enterSpeedField.addActionListener(this);
        paymentSpeedField.addActionListener(this);
        exitSpeedField.addActionListener(this);
        tickField.addActionListener(this);

        enterSpeedField.setBounds(40, 100, 40, 20);
        paymentSpeedField.setBounds(40, 130, 40, 20);
        exitSpeedField.setBounds(40, 160, 40, 20);

        tickLabel.setBounds(10,40,200,20);
        tickField.setBounds(40, 60, 40, 20);
        start.setBounds(500, 110, 100, 20);
        pause.setBounds(800, 110, 100, 20);
        add(tickLabel);
        add(tickField);
        add(enterSpeedField);
        add(paymentSpeedField);
        add(exitSpeedField);
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
    private void enterSpeed(){CarPark.setEnterSpeed(Integer.parseInt(enterSpeedField.getText()));}
    private void paymentSpeed(){CarPark.setPaymentSpeed(Integer.parseInt(paymentSpeedField.getText()));}
    private void exitSpeed(){CarPark.setExitSpeed(Integer.parseInt(exitSpeedField.getText()));}



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
        if(event.getSource() == enterSpeedField){
            this.enterSpeed();
        }
        if(event.getSource() == paymentSpeedField){
            this.paymentSpeed();
        }
        if(event.getSource() == exitSpeedField){
            this.exitSpeed();
        }
    }





}
