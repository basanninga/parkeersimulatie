package parkeersimulatie;

import parkeersimulatie.view.*;

import javax.swing.*;
import java.awt.*;

public class SimulatorFrame extends JFrame {

    private CarParkView carParkView;
    private TickView tickView;


    public SimulatorFrame(Simulator simulator) {
        carParkView = new CarParkView(simulator);
        tickView = new TickView(simulator);



        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        contentPane.add(tickView, BorderLayout.EAST);
        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        updateView();
    }

    public void updateView() {
        carParkView.updateView();
    }
}
