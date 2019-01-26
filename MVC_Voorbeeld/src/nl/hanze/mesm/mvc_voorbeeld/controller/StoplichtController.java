package nl.hanze.mesm.mvc_voorbeeld.controller;

import nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel;
import nl.hanze.mesm.mvc.Controller;
import nl.hanze.mesm.mvc.View;

public class StoplichtController extends Controller {

	public static final int EVENT_ID_AAN = 1;
	public static final int EVENT_ID_UIT = 2;
	public static final int EVENT_ID_VOLGENDE = 3;
	public static final int EVENT_ID_AUTO = 4;

	public StoplichtController(StoplichtModel model) {
		super(model);
	}

	private boolean auto=false;

	@Override
	public boolean event(View view, int event_id) {
		StoplichtModel stoplichtModel = (StoplichtModel)model;
		if (event_id==EVENT_ID_AAN) {
			stoplichtModel.aan();
			return true;
		}
		if (event_id==EVENT_ID_UIT) {
			stoplichtModel.uit();
			return true;
		}
		if (event_id==EVENT_ID_VOLGENDE) {
			stoplichtModel.volgende();
			return true;
		}
		if (event_id==EVENT_ID_AUTO) {
			if (stoplichtModel.isAutomatisch()) {
				stoplichtModel.automatisch_uit();
			} else {
				stoplichtModel.automatisch_aan();
			}
			return true;
		}
		return false;
	}
}
