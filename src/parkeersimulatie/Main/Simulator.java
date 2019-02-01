package parkeersimulatie.Main;

import parkeersimulatie.controller.AbstractController;
import parkeersimulatie.controller.Controller;
import parkeersimulatie.logic.Time;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.view.*;


import javax.swing.*;
import java.awt.*;

/**
 * Class Simulator
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
public class Simulator extends JFrame{

    public static final JFrame SCREEN = new JFrame();

    private Time time;
    private CarPark carPark;
    private AbstractView carParkView;
    private AbstractView statsView;
    private AbstractView timeView;
    private AbstractView occupationView;
    private AbstractView carQueueView;


    private AbstractController controller;

    public static boolean running;


    public static int tickPause = 100;

    public Simulator() {
        time = new Time();
        carPark = new CarPark(3, 6, 30, time);

        this.carParkView = new CarParkView(carPark);
        this.statsView = new StatsView(carPark);
        this.timeView = new TimeView(carPark);
        this.occupationView = new OccupationView(carPark);
        this.carQueueView = new CarQueueView(carPark);


        this.controller = new Controller(carPark);


        SCREEN.setSize(1450, 750);
        SCREEN.setLayout(null);

        SCREEN.getContentPane().add(carParkView);
        SCREEN.getContentPane().add(statsView);
        SCREEN.getContentPane().add(timeView);
        SCREEN.getContentPane().add(occupationView);
        SCREEN.getContentPane().add(carQueueView);

        SCREEN.getContentPane().add(controller);

        carParkView.setBounds(260,145,800,330);
        statsView.setBounds(30,200,150,120);
        timeView.setBounds(1150,200,150,120);
        occupationView.setBounds(1150,400,250,120);
        carQueueView.setBounds(100, 100, 1200,40);



        controller.setBounds(30,320,910,400);

        statsView.setBorder(BorderFactory.createLineBorder(Color.black));
        timeView.setBorder(BorderFactory.createLineBorder(Color.black));
        occupationView.setBorder(BorderFactory.createLineBorder(Color.black));
        carQueueView.setBorder(BorderFactory.createLineBorder(Color.black));
        

        SCREEN.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SCREEN.setVisible(true);
        SCREEN.setResizable(true);


        carParkView.updateView();

        while (true) {
            if(running){
                tick();
            }
            try {
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {
        time.advanceTime();
        carPark.setArrivals();
        carPark.handleExit();
        carPark.updateViews();
        carPark.handleEntrance();
    }


}
