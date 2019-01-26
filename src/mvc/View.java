package mvc;

import javax.swing.JPanel;

public abstract class View extends JPanel {

	private Controller controller;

	/**
	 * Attach controller to view
	 * @param controller
	 */
	public void setController(Controller controller) {
		if (this.controller!=null) {
			throw new IllegalStateException("Controller already set.");
		}
		this.controller = controller;
	}

	/**
	 * Notify controller of event, called by subclasses (concrete views)
	 * @param event_id id of event
	 */
	protected void notifyController(int event_id) {
		if (controller==null) {
			throw new IllegalStateException("View does not have a controller.");
		}
		controller.notify(this, event_id);
	}

	/**
	 * Notify from model. Called by class Model
	 * @param model Model
	 */
	void notify(Model model) {
		update(model);
	}

	/**
	 * Updatehandler
	 * @param model Model
	 */
	protected abstract void update(Model model);

}
