package parkeersimulatie;

import parkeersimulatie.view.CarParkView;

import javax.swing.*;
import java.awt.*;

public class SimulatorFrame extends JFrame {

    private CarParkView carParkView;

    public SimulatorFrame(Simulator simulator) {
        carParkView = new CarParkView(simulator);

        Container contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        updateView();
    }

    public void updateView() {
        carParkView.updateView();
    }
}
