package nl.hanze.mesm.mvc_voorbeeld.controller;

import nl.hanze.mesm.mvc.Controller;
import nl.hanze.mesm.mvc.View;
import nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel;
import nl.hanze.mesm.mvc_voorbeeld.model.Test;

public class TestController extends Controller {
	
	public TestController(Test model) {
		super(model);
	}

	@Override
	protected boolean event(View view, int event_id) {
		// TODO Auto-generated method stub
		return false;
	}
}
