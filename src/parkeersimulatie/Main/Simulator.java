package parkeersimulatie.Main;

import parkeersimulatie.controller.AbstractController;
import parkeersimulatie.controller.Controller;
import parkeersimulatie.logic.Car;
import parkeersimulatie.logic.Time;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.view.AbstractView;
import parkeersimulatie.view.CarParkView;
import parkeersimulatie.view.StatsView;


import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame{

    private Time time;
    private CarPark carPark;
    private AbstractView carParkView;
    private AbstractView statsView;

    private JLabel current;


    private AbstractController controller;


    public static int tickPause = 100;

    public Simulator() {
        time = new Time();
        carPark = new CarPark(3, 6, 30, time);

        carParkView = new CarParkView(carPark);
        this.statsView = new StatsView(carPark);
        this.controller = new Controller(carPark);


        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(carParkView);
        contentPane.add(statsView);
        contentPane.add(controller);
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
