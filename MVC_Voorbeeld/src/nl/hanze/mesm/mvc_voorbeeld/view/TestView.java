package nl.hanze.mesm.mvc_voorbeeld.view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.*;
import nl.hanze.mesm.mvc.Model;
import nl.hanze.mesm.mvc.View;
import nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel;
import nl.hanze.mesm.mvc_voorbeeld.model.Test;

public class TestView extends View {
	
	private JPanel panel;
	private Test model;
	private JButton button;
	private JLabel label;

	public TestView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		model = new Test();
		model.setString("moi");
		 // create a new frame to stor text field and button 
        panel = new JPanel(); 
        add(panel);

        // create a label to display text 
        label = new JLabel("panel label"); 

        // create a new buttons 
        button = new JButton("hallo?");

        // add buttons and textfield to panel 
        panel.add(label);
        panel.add(button);  

        // setbackground of panel 
        panel.setBackground(Color.pink); 
        
        update(model);
	}
	
	@Override
	protected void update(Model model) {
		Test test = (Test) model; // cast
		test.getTest();
		System.out.println(test.getTest());

		button.setText(test.getTest());
		
		SwingUtilities.invokeLater(() -> {
			this.repaint();
			Toolkit.getDefaultToolkit().sync();
		});
	}
}
