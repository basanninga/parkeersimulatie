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

    private JLabel currentText;
    private JLabel expectedText;
    private JLabel totalText;

    private JLabel yesterdayText;

    private JLabel current;
    private JLabel expected;
    private JLabel total;

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

        this.currentText = new JLabel("Current: ");
        this.expectedText = new JLabel("Expected: ");
        //this.day = new JLabel("Day");
        this.yesterdayText = new JLabel("Yesterday: ");
        this.totalText = new JLabel("Total: ");

        //this.clock = new JLabel("clock");
        this.current = new JLabel("0");
        this.expected = new JLabel("0");
        //this.day = new JLabel("day");
        this.yesterday = new JLabel("0");
        this.total = new JLabel("0");

        expected.setBounds(80,10,50,20);
        current.setBounds(80,30,50,20);
        yesterday.setBounds(80,50,50,20);
        total.setBounds(80,70,50,20);

        expectedText.setBounds(10,10,100,20);
        currentText.setBounds(10,30,100,20);
        yesterdayText.setBounds(10,50,100,20);
        totalText.setBounds(10,70,100,20);

        this.add(currentText);
        this.add(totalText);
        this.add(expectedText);
        this.add(yesterdayText);

        this.add(current);
        this.add(total);
        this.add(expected);

        this.add(yesterday);

    }

    @Override
    public void updateView() {



        CarPark carPark = (CarPark) super.model;

        currentRevenue = (((carPark.getTotalMinutes() / 60) * parkPrice) / 100);
        expectedRevenue = (((carPark.getStayMinutes() / 60) * parkPrice) / 100);
        clockString = time.getClock();
        dayString = time.getDayString();


        int runningDay = time.getRunningDay();


        if(runningDay > currentDay){
            yesterdayRevenue = currentRevenue;
            tempRevenue = tempRevenue + yesterdayRevenue;
            CarPark.setTotalMinutes(0);
            CarPark.setStayMinutes(0);
            currentDay = runningDay;
        }


        totalRevenue = tempRevenue+ currentRevenue;


        current.setText(this.euro + (this.currentRevenue));
        expected.setText(this.euro + (this.expectedRevenue));
        yesterday.setText(Objects.toString(this.euro + yesterdayRevenue));
        total.setText(this.euro + (this.totalRevenue));

        setVisible(true);
        super.updateView();
    }
}
