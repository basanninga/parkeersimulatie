package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;

import javax.swing.*;


/**
 * Class OccupationView
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
public class OccupationView extends AbstractView {

    private int adhocCarsPass;
    private int parkingCarsPass;
    private int reservationCarsPass;

    private JLabel aantalAdhoc;
    private JLabel aantalParking;
    private JLabel aantalreservationCars;

    /**
     *  Constructor van OccupationView met een model van AbstractModel

     *  @param model AbstractModel dat hoort bij deze view.
     */

    public OccupationView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        this.adhocCarsPass = carPark.getAdhocCarsPass();
        this.parkingCarsPass = carPark.getPassCar();
        this.reservationCarsPass = carPark.getReservationCar();


        this.aantalAdhoc = new JLabel("Aantal bezoekers: 0");
        this.aantalParking = new JLabel("Aantal pass bezoekers: 0");
        this.aantalreservationCars = new JLabel("Aantal reservaties: 0");


        aantalAdhoc.setBounds(10,30,400,20);
        aantalParking.setBounds(10,50,400,20);
        aantalreservationCars.setBounds(10,80,400,20);

        this.add(aantalAdhoc);
        this.add(aantalParking);
        this.add(aantalreservationCars);

    }
    /**
     Methode voor het updaten van de view.
     */
    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;
        adhocCarsPass = carPark.getAdhocCarsPass();
        parkingCarsPass = carPark.getPassCar();
        reservationCarsPass = carPark.getReservationCar();


        aantalAdhoc.setText("Aantal bezoekers: " + (adhocCarsPass - carPark.getEntranceCarQueue()));
        aantalParking.setText("Aantal pass bezoekers: " + (parkingCarsPass - carPark.getEntrancePassCarQueue()));
        aantalreservationCars.setText("Aantal gereserveerde bezoekers: " + (reservationCarsPass - carPark.getEntrancePassCarQueue()));

        setVisible(true);
        super.updateView();
    }
}
