package nl.hanze.mesm.mvc_voorbeeld.model;

import nl.hanze.mesm.mvc.Model;
import nl.hanze.mesm.mvc_voorbeeld.model.StoplichtModel.Status;

public class Test extends Model {
	
	private String teststring;

	public Test() {
	}
	
	public void setString(String value)
	{
		this.teststring = value;
		notifyView();
	}
	
	public String getTest()
	{
		return teststring;
	}
}
