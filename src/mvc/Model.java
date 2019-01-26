package mvc;

import java.util.ArrayList;

public abstract class Model {

	private ArrayList<View> views = new ArrayList<>();

	/**
	 * Add views to model to be notified by model.
	 * @param view View to be added to list of models that will be notified.
	 */
	public void addView(View view) {
		views.add(view);
	}

	/**
	 * Notify views. Called by subclasses (concrete models)
	 */
	protected void notifyView() {
		for (View view : views) {
			view.notify(this);
		}
	}
}
