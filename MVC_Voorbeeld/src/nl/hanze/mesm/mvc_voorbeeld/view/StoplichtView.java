package nl.hanze.mesm.mvc_voorbeeld.view;

import nl.hanze.mesm.mvc.Model;
import nl.hanze.mesm.mvc_voorbeeld.controller.StoplichtController;
import nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel;
import nl.hanze.mesm.mvc.View;

import javax.swing.*;
import java.awt.*;

import static nl.hanze.mesm.mvc_voorbeeld.controller.StoplichtController.*;
import static nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel.Status.*;

public class StoplichtView extends View {

	private StoplichtTekening stoplichtTekening;
	private JButton knop_aan, knop_uit, knop_volgende, knop_auto;

	public static int AFSTAND = 30;
	public static int STRAAL = 100;
	public static int BREEDTE = AFSTAND+STRAAL+AFSTAND; // Breedte van het gehele stoplicht
	public static int HOOGTE = AFSTAND+STRAAL+AFSTAND+STRAAL+AFSTAND+STRAAL+AFSTAND; // Hoogte van het gehele stoplicht

	class StoplichtTekening extends JPanel {

		private StoplichtModel.Status status;

		public StoplichtTekening() {
			setLayout(null);
			setPreferredSize(new Dimension(AFSTAND+BREEDTE+AFSTAND, AFSTAND+HOOGTE+AFSTAND));
			setBackground(Color.LIGHT_GRAY);
		}

		public void setStatus(StoplichtModel.Status status) {
			this.status = status;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int x=(getWidth()-BREEDTE)/2; // Centreren

			// De behuizing
			g.setColor(Color.BLACK);
			g.fillRoundRect(x, AFSTAND, BREEDTE, HOOGTE, AFSTAND, AFSTAND);

			// Bovenste lamp (rood)
			g.setColor((status==ROOD) ? Color.RED : Color.DARK_GRAY);
			g.fillOval(x+AFSTAND, AFSTAND*2, STRAAL, STRAAL);

			// Middelste lamp (oranje)
			g.setColor((status==ORANJE) ? Color.ORANGE : Color.DARK_GRAY);
			g.fillOval(x+AFSTAND, AFSTAND*2+STRAAL+AFSTAND, STRAAL, STRAAL);

			// Onderste lamp (groen)
			g.setColor((status==GROEN) ? Color.GREEN : Color.DARK_GRAY);
			g.fillOval(x+AFSTAND, AFSTAND*2+STRAAL+AFSTAND+STRAAL+AFSTAND, STRAAL, STRAAL);
		}
	}

	public StoplichtView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Stoplichtekening
		stoplichtTekening=new StoplichtTekening();
		add(stoplichtTekening);
		// Bedieningspaneel met 4 knoppen
		JPanel bediening=new JPanel();
		bediening.setBackground(Color.YELLOW);
		add(bediening);

		knop_aan=new JButton("Aan");
		knop_aan.addActionListener((e) -> notifyController(EVENT_ID_AAN));
		bediening.add(knop_aan);

		knop_uit=new JButton("Uit");
		knop_uit.addActionListener((e) -> notifyController(EVENT_ID_UIT));
		bediening.add(knop_uit);

		knop_volgende=new JButton("Volgende");
		knop_volgende.addActionListener((e) -> notifyController(EVENT_ID_VOLGENDE));
		bediening.add(knop_volgende);

		knop_auto=new JButton("");
		knop_auto.addActionListener((e) -> notifyController(EVENT_ID_AUTO));
		bediening.add(knop_auto);

	}

	@Override
	public void update(Model model) {
		StoplichtModel stoplichtModel = (StoplichtModel) model; // cast
		System.out.println(stoplichtModel.getStatus());
		// Stand van knoppen updaten
		if (stoplichtModel.isAan()) {
			// Stoplicht is aan
			knop_aan.setEnabled(false);
			knop_uit.setEnabled(true);
			knop_volgende.setEnabled(!stoplichtModel.isAutomatisch());
			knop_auto.setEnabled(true);
		} else {
			// Stoplicht is uit
			knop_aan.setEnabled(true);
			knop_uit.setEnabled(false);
			knop_volgende.setEnabled(false);
			knop_auto.setEnabled(false);
		}
		knop_auto.setText(stoplichtModel.isAutomatisch() ? "Stop automatisch" : "Start automatisch");
		// En het stoplicht zelf..
		stoplichtTekening.setStatus(stoplichtModel.getStatus());
		SwingUtilities.invokeLater(() -> {
			this.repaint();
			Toolkit.getDefaultToolkit().sync();
		});
	}


}
