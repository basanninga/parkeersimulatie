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
        setVisible(true);
        super.updateView();

    }

}
