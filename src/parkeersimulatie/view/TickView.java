package parkeersimulatie.view;

import mvc.Model;
import mvc.View;
import parkeersimulatie.Simulator;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TickView extends View implements ChangeListener {

    private Simulator simulator;
    public JSlider slider;
    private int value;


    public TickView(Simulator simulator) {

        this.simulator = simulator;

        /*this.setLayout(new BorderLayout());*/
        setLayout(new GridLayout(2, 1));

        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.setLabelTable(slider.createStandardLabels(10));
        slider.addChangeListener(e -> {

            value = ((JSlider) e.getSource()).getValue();
            System.out.println(value);
            simulator.tickPause = value;

        });

        add(slider);

    }


    @Override
    protected void update(Model model) {
        //
    }


    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
