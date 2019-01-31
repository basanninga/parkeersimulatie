package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;

import javax.swing.*;

public class OccupationView extends AbstractView {

    private int adhocCarsPass;
    private int parkingCarsPass;

    private JLabel aantalAdhoc;
    private JLabel aantalParking;

    /**
     *  Constructor van OccupationView met een model van AbstractModel

     *  @param model AbstractModel dat hoort bij deze view.
     */

    public OccupationView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        this.adhocCarsPass = carPark.getAdhocCarsPass();
        this.parkingCarsPass = carPark.getPassCar();

        this.aantalAdhoc = new JLabel("Aantal bezoekers: 0");
        this.aantalParking = new JLabel("Aantal pass bezoekers: 0");


        aantalAdhoc.setBounds(10,30,400,20);
        aantalParking.setBounds(10,50,400,20);

        this.add(aantalAdhoc);
        this.add(aantalParking);

    }
    /**
     Methode voor het updaten van de view.
     */
    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;
        adhocCarsPass = carPark.getAdhocCarsPass();
        parkingCarsPass = carPark.getPassCar();

        aantalAdhoc.setText("Aantal bezoekers: " + (adhocCarsPass - carPark.getEntranceCarQueue()));
        aantalParking.setText("Aantal pass bezoekers: " + (parkingCarsPass - carPark.getEntrancePassCarQueue()));

        setVisible(true);
        super.updateView();
    }
}
