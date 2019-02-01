package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Time;
import javax.swing.*;
import java.util.Objects;

public class TimeView extends AbstractView {

    private Time time;

    private JLabel day;
    private JLabel clock;
    private JLabel runningDays;
    private JLabel week;

    private JLabel dayLabel;
    private JLabel clockLabel;
    private JLabel runningDaysLabel;
    private JLabel weekLabel;


    private String dayText;
    private String clockText;
    private String runningDaysText;
    private String weekText;




    /**
     *  Constructor van TimeView met een model van AbstractModel

     *  @param model AbstractModel dat hoort bij deze view.
     */
    public TimeView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        time = carPark.getTime();

        this.day = new JLabel("maandag");
        this.clock = new JLabel("00:00");
        this.runningDays = new JLabel("0");
        this.week = new JLabel("0");

        this.dayLabel = new JLabel("Dag:");
        this.clockLabel = new JLabel("Tijd:");
        this.runningDaysLabel = new JLabel("Dagen:");
        this.weekLabel = new JLabel("weken");

        this.day.setBounds(60,10,100,20);
        this.clock.setBounds(60,25,50,20);
        this.runningDays.setBounds(60,50,50,20);
        this.week.setBounds(60,70,50,20);

        this.dayLabel.setBounds(10,10,50,20);
        this.clockLabel.setBounds(10,25,50,20);
        this.runningDaysLabel.setBounds(10,50,50,20);
        this.weekLabel.setBounds(10,70,50,20);

        this.add(day);
        this.add(clock);
        this.add(runningDays);
        this.add(week);

        this.add(dayLabel);
        this.add(clockLabel);
        this.add(runningDaysLabel);
        this.add(weekLabel);

    }

    /**
     Methode voor het updaten van de view.
     */
    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;

        dayText = time.getDayString();
        clockText = time.getClock();
        runningDaysText = Objects.toString(time.getRunningDay());
        weekText = Objects.toString(time.getWeek());


        day.setText(dayText);
        clock.setText(clockText);
        runningDays.setText(runningDaysText);
        week.setText(weekText);

        setVisible(true);
        super.updateView();
    }


}
