package parkeersimulatie.view;

import parkeersimulatie.logic.*;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CarQueueView extends AbstractView {

    private JLabel queueLength;
    private Dimension size;

    private JLabel queueLengthText;

    private JLabel queuePayLength;

    private JLabel queuePayText;

    private JLabel queuePassLength;

    private JLabel queuePassLengthText;

    private JLabel queueExitLengthText;
    private JLabel queueExitLength;

    private boolean Show1 = true;
    private boolean Show2 = true;
    private boolean Show3 = true;
    private boolean Show4 = true;

    public CarQueueView(AbstractModel model){
        super(model);

        this.size = new Dimension(1200,40);

        this.queueLength = new JLabel();
        this.queueLengthText = new JLabel("Rij voor de ingang:");
        this.queuePayLength = new JLabel();
        this.queuePayText = new JLabel("Rij voor betaalautomaat:");
        this.queuePassLength = new JLabel();
        this.queuePassLengthText = new JLabel("Rij voor abonnementhouders:");
        this.queueExitLengthText = new JLabel("Rij voor uitgang:");
        this.queueExitLength = new JLabel();

        queueLength.setBounds(150,5,30,30);
        queueLengthText.setBounds(30,5,200,30);
        queuePayLength.setBounds(360, 5 , 30, 30 );
        queuePayText.setBounds(200, 5, 200,30);
        queuePassLength.setBounds(600,5,30,30);
        queuePassLengthText.setBounds(400,5,200,30);
        queueExitLengthText.setBounds(640,5,200,30);
        queueExitLength.setBounds(750,5, 30,30);
        add(queueLength);
        add(queuePayLength);
        add(queueLengthText);
        add(queuePayText);
        add(queuePassLengthText);
        add(queuePassLength);
        add(queueExitLengthText);
        add(queueExitLength);


    }


    public void updateView(){

        CarPark carPark = (CarPark) super.model;

        queueLength.setText(Objects.toString(carPark.getEntranceCarQueue()));
        queuePayLength.setText(Objects.toString(carPark.getPaymentCarQueue()));
        queuePassLength.setText(Objects.toString(carPark.getEntrancePassCarQueue()));
        queueExitLength.setText(Objects.toString(carPark.getExitCarQueue()));

        String ingang = "Er is een rij van 100 mensen voor de ingang ontstaan.";
        String betaalautomaat = "Er is een rij van 100 mensen voor de betaalautomaat ontstaan.";
        String abonnementhouders = "Er is een rij dan 100 mensen voor de abonnementhouders ontstaan.";
        String uitgang = "Er is een rij van 100 mensen voor de uitgang ontstaan.";

        if(carPark.getEntranceCarQueue() > 100 && Show1) {
            JOptionPane.showMessageDialog(new JFrame(), ingang, "Waarschuwing",
                    JOptionPane.ERROR_MESSAGE);
            Show1 = false;

        }else if(carPark.getEntranceCarQueue() < 100 && !Show1) {
            Show1 = true;
        }
        else if(carPark.getPaymentCarQueue() > 100 && Show2) {
            JOptionPane.showMessageDialog(new JFrame(), betaalautomaat, "Waarschuwing",
                    JOptionPane.ERROR_MESSAGE);
            Show2 = false;

        }else if(carPark.getPaymentCarQueue() < 100 && !Show2) {
            Show2 = true;
        }
        else if(carPark.getEntrancePassCarQueue() > 100 && Show3) {
            JOptionPane.showMessageDialog(new JFrame(), abonnementhouders, "Waarschuwing",
                    JOptionPane.ERROR_MESSAGE);
            Show3 = false;

        }else if(carPark.getEntrancePassCarQueue() < 100 && !Show3) {
            Show3 = true;
        }
        else if(carPark.getExitCarQueue() > 100 && Show4) {
            JOptionPane.showMessageDialog(new JFrame(), uitgang, "Waarschuwing",
                    JOptionPane.ERROR_MESSAGE);
            Show4 = false;

        }else if(carPark.getExitCarQueue() < 100 && !Show4) {
            Show4 = true;
        }


        setVisible(true);
        super.updateView();

    }

}
