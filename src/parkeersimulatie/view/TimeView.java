package parkeersimulatie.view;

import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Time;
import javax.swing.*;

public class TimeView extends AbstractView {

    private Time time;

    private JLabel day;
    private JLabel clock;

    private JLabel dayLabel;
    private JLabel clockLabel;

    private String dayText;
    private String clockText;


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

        this.dayLabel = new JLabel("Dag:");
        this.clockLabel = new JLabel("Tijd:");

        this.day.setBounds(50,30,100,20);
        this.clock.setBounds(50,50,50,20);

        this.dayLabel.setBounds(10,30,50,20);
        this.clockLabel.setBounds(10,50,50,20);
        this.add(day);
        this.add(clock);
        this.add(dayLabel);
        this.add(clockLabel);

    }

    /**
     Methode voor het updaten van de view.
     */
    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;

        dayText = time.getDayString();
        clockText = time.getClock();

        day.setText(dayText);
        clock.setText(clockText);

        setVisible(true);
        super.updateView();
    }


}
