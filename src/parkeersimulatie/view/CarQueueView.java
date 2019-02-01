package parkeersimulatie.view;

import parkeersimulatie.logic.*;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;


/**
 * Class CarQueueView
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
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


    /**
     *  Constructor van CarQueueView met een model van AbstractModel

     *  @param model AbstractModel dat hoort bij deze view.
     */

    public CarQueueView(AbstractModel model){
        super(model);

        this.size = new Dimension(1200,40);

        this.queueLength = new JLabel("0");
        this.queueLengthText = new JLabel("Rij voor de ingang:");
        this.queuePayLength = new JLabel("0");
        this.queuePayText = new JLabel("Rij voor betaalautomaat:");
        this.queuePassLength = new JLabel("0");
        this.queuePassLengthText = new JLabel("Rij voor abonnementhouders:");
        this.queueExitLengthText = new JLabel("Rij voor uitgang:");
        this.queueExitLength = new JLabel("0");

        queueLength.setBounds(340,5,30,30);
        queueLengthText.setBounds(220,5,200,30);
        queuePayLength.setBounds(550, 5 , 30, 30 );
        queuePayText.setBounds(390, 5, 200,30);
        queuePassLength.setBounds(790,5,30,30);
        queuePassLengthText.setBounds(590,5,200,30);
        queueExitLengthText.setBounds(830,5,200,30);
        queueExitLength.setBounds(940,5, 30,30);
        add(queueLength);
        add(queuePayLength);
        add(queueLengthText);
        add(queuePayText);
        add(queuePassLengthText);
        add(queuePassLength);
        add(queueExitLengthText);
        add(queueExitLength);


    }

    /**
     Methode voor het updaten van de view.
     */

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
