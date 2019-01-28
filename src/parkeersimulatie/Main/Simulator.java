package parkeersimulatie.Main;

import parkeersimulatie.logic.Time;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.view.AbstractView;
import parkeersimulatie.view.CarParkView;


import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame{


    private Time time;
    private CarPark carPark;
    private AbstractView carParkView;


    public int tickPause = 50;

    public Simulator() {
        time = new Time();
        carPark = new CarPark(3, 6, 30, time);

        carParkView = new CarParkView(carPark);

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        carParkView.updateView();


        //simulatorController = new CarQueueController(this);
    }

    public void run() {
        while (true) {
            tick();


        }
    }

    private void tick() {
        time.advanceTime();
        carPark.setArrivals();
        carPark.handleExit();
        carPark.updateViews();
        // Pause.
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        carPark.handleEntrance();
    }


}
