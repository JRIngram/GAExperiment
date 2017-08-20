package view;

import con.Controller;
import model.GAAlgorithm;

public class View {
	
	protected Controller con;
	
	public View(GAAlgorithm model){
		con = new Controller(model);
	}
}
