package parkeersimulatie.view;


import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Time;

import javax.swing.*;
import java.util.Objects;

public class TimeView extends AbstractView {

    private Time time;

    private JLabel day;

    public TimeView(AbstractModel model) {
        super(model);

        CarPark carPark = (CarPark) super.model;

        time = carPark.getTime();

        this.day = new JLabel("maandag");

        this.day.setBounds(10,50,100,100);

        this.add(day);

    }

    @Override
    public void updateView() {

        CarPark carPark = (CarPark) super.model;


        day.setText("check");

        setVisible(true);
        super.updateView();
    }


}
