package nl.hanze.mesm.mvc_voorbeeld.model;

import nl.hanze.mesm.mvc.Model;

import static nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel.Status.*;

public class StoplichtModel extends Model {

	public enum Status { ROOD, ORANJE, GROEN };

	private Status status; // Status (null = uitgeschakeld)
	boolean automatisch; // automatisch ja of nee

	public StoplichtModel() {
		status=null;
	}

	// Setters

	public void aan() {
		automatisch=false;
		status=ROOD;
		notifyView();
	}

	public void uit() {
		automatisch=false;
		status=null;
		notifyView();
	}

	public void automatisch_aan() {
		if (status==null) {
			throw new IllegalStateException("Stoplicht is uit.");
		}
		if (automatisch) {
			throw new IllegalStateException("Stoplicht staat al op automatisch");
		}
		status=ROOD;
		automatisch=true;
		notifyView();
		Thread automatischStoplicht = new Thread(new Runnable() {
			@Override
			public void run() {
				while(automatisch) {
					volgende();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		automatischStoplicht.start();
	}

	public void automatisch_uit() {
		automatisch=false;
		notifyView();
	}

	// Getters (worden gebruikt door view)

	public Status getStatus() {
		return status;
	}

	public boolean isAan() {
		return status!=null;
	}

	public boolean isAutomatisch() {
		return automatisch;
	}

	// Business logic: Naar de volgende state
	public void volgende() {
		if (status==null) {
			throw new IllegalStateException("Stoplicht is uit.");
		}
		switch(status) {
			case GROEN:
				status=ORANJE;
				break;
			case ORANJE:
				status=ROOD;
				break;
			case ROOD:
				status=GROEN;
				break;
		}

		notifyView();
	}




}
