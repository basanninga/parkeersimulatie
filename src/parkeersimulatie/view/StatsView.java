package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Time;

import javax.swing.*;

public class StatsView extends AbstractView {

    private Time time;

    private int parkPrice;
    private int currentRevenue;
    private int expectedRevenue;
    String clockString;

    private JLabel current;
    private JLabel expected;
    private JLabel clock;
    private JTextField aids;

    String euro = "\u20ac";


    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public StatsView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        time = carPark.getTime();

        this.parkPrice = 250;

        this.clock = new JLabel("clock");
        this.current = new JLabel("KANKER");
        this.expected = new JLabel("aids");

        add(clock);
        add(current);
        add(expected);












    }

    @Override
    public void updateView() {


        CarPark carPark = (CarPark) super.model;

        currentRevenue = (((carPark.getTotalMinutes() / 60) * parkPrice) / 100);
        expectedRevenue = (((carPark.getStayMinutes() / 60) * parkPrice) / 100);
        clockString = time.getClock();


        current.setText(this.euro + (this.currentRevenue));
        expected.setText(this.euro + (this.expectedRevenue));
        clock.setText(clockString);

        super.updateView();
    }
}
