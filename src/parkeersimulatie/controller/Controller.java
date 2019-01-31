package parkeersimulatie.controller;
import parkeersimulatie.Main.Simulator;
import parkeersimulatie.logic.*;

import javax.swing.*;
import java.awt.event.*;

public class Controller extends AbstractController implements ActionListener {

    private JDialog frame;

    private JTextField tickField;
    private JTextField enterSpeedField;
    private JTextField paymentSpeedField;
    private JTextField exitSpeedField;

    private JLabel enterSpeedLabel;
    private JLabel paymentSpeedLabel;
    private JLabel exitSpeedLabel;

    private JLabel tickLabel;

    private JButton start;
    private JButton pause;


    /**
        Constructor van AbstractController met het model die bij de controller hoort.
     */

    public Controller(AbstractModel model) {
        super(model);

        setLayout(null);

        tickLabel = new JLabel("Simulatie snelheid");

        enterSpeedLabel = new JLabel("Enter snelheid");
        paymentSpeedLabel = new JLabel("Betaal snelheid");
        exitSpeedLabel = new JLabel("Verlaat snelheid");

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

        tickLabel.setBounds(0,70,150,20);
        enterSpeedLabel.setBounds(0,100,150,20);
        paymentSpeedLabel.setBounds(0,130,150,20);
        exitSpeedLabel.setBounds(0,160,150,20);

        tickField.setBounds(140, 70, 40, 20);
        enterSpeedField.setBounds(140, 100, 40, 20);
        paymentSpeedField.setBounds(140, 130, 40, 20);
        exitSpeedField.setBounds(140, 160, 40, 20);

        start.setBounds(500, 260, 100, 20);
        pause.setBounds(700, 260, 100, 20);

        add(enterSpeedLabel);
        add(paymentSpeedLabel);
        add(exitSpeedLabel);
        add(tickLabel);
        add(tickField);
        add(enterSpeedField);
        add(paymentSpeedField);
        add(exitSpeedField);
        add(start);
        add(pause);
    }


    /**
     Methode die de snelheid van de simulatie aanpast
     Verkeerde input wordt afgehandeld met een try catch
     */
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

    /**
     Methode die de simulatie laat runnen
     */
    private void startPressed() {
        Simulator.running = true;
    }

    /**
     Methode die de simulatie laat pauzeren
     */
    private void pausePressed() {
        Simulator.running = false;
    }


    /**
        Methode die de snelheid veranderd van de mensen die de parkeergarage inrijden.
        Verkeerde input wordt afgevangen met een try catch
     */
    private void enterSpeed(){
        try{
            int i = Integer.parseInt(enterSpeedField.getText());
            if(i >= 0) {
                CarPark.setEnterSpeed(i);
            } else{
                JOptionPane.showMessageDialog(frame,
                        "Please enter a positive number.");
            }
        } catch (NumberFormatException e){

            JOptionPane.showMessageDialog(frame,
                    "Please enter a number.");

        }
    }


    /**
     Methode die de snelheid veranderd van de mensen die betalen in de parkeergarage.
     Verkeerde input wordt afgevangen met een try catch
     */
    private void paymentSpeed(){
        try{
            int i = Integer.parseInt(paymentSpeedField.getText());
            if(i >= 0) {
                CarPark.setPaymentSpeed(i);
            } else{
                JOptionPane.showMessageDialog(frame,
                        "Please enter a positive number.");
            }
        } catch (NumberFormatException e){

            JOptionPane.showMessageDialog(frame,
                    "Please enter a number.");

        }
    }


    /**
     Methode die de snelheid van de mensen die uit de parkeergarage willen gaan aanpast.
     Verkeerde input wordt afgevangen met een try catch
     */
    private void exitSpeed(){
        try{
            int i = Integer.parseInt(exitSpeedField.getText());
            if(i >= 0) {
                CarPark.setExitSpeed(i);
            } else{
                JOptionPane.showMessageDialog(frame,
                        "Please enter a positive number.");
            }
        } catch (NumberFormatException e){

            JOptionPane.showMessageDialog(frame,
                    "Please enter a number.");

        }
    }

    /**
     Methode die de events afvangt.
     De source van het event wordt gecontroleerd met de verschillende ActionEvents
     Daarna wordt de desbetreffende functie uitgevoert.
     */

    // Vangt de eventen af en voert een functie uit bij het desbetreffende event
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
