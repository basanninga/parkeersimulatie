package parkeersimulatie.Main;

import parkeersimulatie.controller.AbstractController;
import parkeersimulatie.controller.Controller;
import parkeersimulatie.logic.Car;
import parkeersimulatie.logic.Time;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.view.AbstractView;
import parkeersimulatie.view.CarParkView;
import parkeersimulatie.view.StatsView;
import parkeersimulatie.view.TimeView;


import javax.swing.*;
import java.awt.*;

public class Simulator extends JFrame{

    public static final JFrame SCREEN = new JFrame();

    private Time time;
    private CarPark carPark;
    private AbstractView carParkView;
    private AbstractView statsView;
    private AbstractView timeView;

    private AbstractController controller;


    public static int tickPause = 100;

    public Simulator() {
        time = new Time();
        carPark = new CarPark(3, 6, 30, time);

        this.carParkView = new CarParkView(carPark);
        this.statsView = new StatsView(carPark);
        this.timeView = new TimeView(carPark);


        this.controller = new Controller(carPark);


        SCREEN.setSize(1400, 750);
        SCREEN.setLayout(null);

        SCREEN.getContentPane().add(carParkView);
        SCREEN.getContentPane().add(statsView);
        SCREEN.getContentPane().add(timeView);

        SCREEN.getContentPane().add(controller);

        carParkView.setBounds(260,30,800,330);
        statsView.setBounds(30,200,150,120);
        timeView.setBounds(1100,200,150,120);


        controller.setBounds(30,320,910,90);

        statsView.setBorder(BorderFactory.createLineBorder(Color.black));
        timeView.setBorder(BorderFactory.createLineBorder(Color.black));

        SCREEN.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SCREEN.setVisible(true);
        SCREEN.setResizable(true);


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
