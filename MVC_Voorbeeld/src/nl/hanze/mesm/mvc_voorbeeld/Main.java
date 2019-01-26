package nl.hanze.mesm.mvc_voorbeeld;

import nl.hanze.mesm.mvc_voorbeeld.controller.StoplichtController;
import nl.hanze.mesm.mvc_voorbeeld.controller.TestController;
import nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel;
import nl.hanze.mesm.mvc_voorbeeld.model.Test;
import nl.hanze.mesm.mvc_voorbeeld.view.StoplichtView;
import nl.hanze.mesm.mvc_voorbeeld.view.TestView;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setTitle("MVC Voorbeeld");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		// Model, View, Controller maken
//		// Controller krijgt model
//		StoplichtModel model = new StoplichtModel();
//		StoplichtView view = new StoplichtView();
//		StoplichtController controller = new StoplichtController(model);
//
//		// Controller aan view, en view aan model koppen tbv. notify events en updates.
//		view.setController(controller);
//		model.addView(view);

		
		Test model2 = new Test();
		TestView view2 = new TestView();
		TestController controller2 = new TestController(model2);
		
		
		view2.setController(controller2);
		model2.addView(view2);

//		window.setContentPane(view);
		window.setContentPane(view2);
		
		window.pack();
		window.setVisible(true);
	}
}
