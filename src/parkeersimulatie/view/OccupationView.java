package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;

import javax.swing.*;
import java.util.Objects;

public class OccupationView extends AbstractView {

    private int adhocCarsPass;
    private int parkingCarsPass;

    private JLabel aantalAdhoc;
    private JLabel aantalParking;

    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */

    public OccupationView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        this.adhocCarsPass = carPark.getAdhocCarsPass();
        this.parkingCarsPass = carPark.getPassCar();

        //this.clock = new JLabel("Time");
        this.aantalAdhoc = new JLabel("Aantal bezoekers: ");
        this.aantalParking = new JLabel("Aantal pass bezoekers: ");


        aantalAdhoc.setBounds(10,20,400,20);
        aantalParking.setBounds(10,40,400,20);

        this.add(aantalAdhoc);
        this.add(aantalParking);

    }

    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;
        adhocCarsPass = carPark.getAdhocCarsPass();
        parkingCarsPass = carPark.getPassCar();

        aantalAdhoc.setText("Aantal bezoekers: " + adhocCarsPass);
        aantalParking.setText("Aantal pass bezoekers: " + parkingCarsPass);

        setVisible(true);
        super.updateView();
    }
}
