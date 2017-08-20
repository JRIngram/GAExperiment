package view;

import con.Controller;
import model.GAAlgorithm;

public class View {
	
	/**The controller for the view*/
	protected Controller con;
	
	/**
	 * Set the model for the controller of this view to use.
	 * @param model The model that the controller is going to use.
	 */
	public View(GAAlgorithm model){
		con = new Controller(model);
	}
}
