package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Time;

import javax.swing.*;
import java.util.Objects;

public class StatsView extends AbstractView {

    private Time time;

    private int currentDay;
    private int parkPrice;
    private int currentRevenue;
    private int expectedRevenue;
    private int yesterdayRevenue;
    private int totalRevenue;
    private int tempRevenue;


    private JLabel current;
    private JLabel expected;
    private JLabel total;
    private JLabel clock;
    private JLabel day;
    private JLabel yesterday;

    String euro = "\u20ac";
    String clockString;
    String dayString;


    /**
     * Constructor of AbstractView that expects a model belonging to this view.
     *
     * @param model AbstractModel that belongs to this view.
     */
    public StatsView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        time = carPark.getTime();

        currentDay = time.getDay();

        this.parkPrice = 250;

        this.clock = new JLabel("clock");
        this.current = new JLabel("KANKER");
        this.expected = new JLabel("aids");
        this.day = new JLabel("day");
        this.yesterday = new JLabel("day");
        this.total = new JLabel("total");

        add(clock);
        add(current);
        add(total);
        add(expected);
        add(day);
        add(yesterday);

    }

    @Override
    public void updateView() {



        CarPark carPark = (CarPark) super.model;

        currentRevenue = (((carPark.getTotalMinutes() / 60) * parkPrice) / 100);
        expectedRevenue = (((carPark.getStayMinutes() / 60) * parkPrice) / 100);
        clockString = time.getClock();
        dayString = time.getDayString();


        System.out.println(carPark.getTotalMinutes());

        int runningDay = time.getDay();

        if(runningDay > currentDay){
            System.out.println("KANKER");
            yesterdayRevenue = currentRevenue;
            tempRevenue = tempRevenue + yesterdayRevenue;
            carPark.setTotalMinutes(0);
            carPark.setStayMinutes(0);
            currentDay = runningDay;
        }

        totalRevenue = tempRevenue+ currentRevenue;


        current.setText(this.euro + (this.currentRevenue));
        expected.setText(this.euro + (this.expectedRevenue));
        clock.setText(clockString);
        day.setText(dayString);
        yesterday.setText(Objects.toString(yesterdayRevenue));
        total.setText(this.euro + (this.totalRevenue));


        super.updateView();
    }
}
