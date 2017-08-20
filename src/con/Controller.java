package con;
import model.GAAlgorithm;
public class Controller {
	
	private GAAlgorithm model;
	
	public Controller(GAAlgorithm model){
		this.model = model;
	}
	
	public boolean setTargetString(String target){
		return model.setTargetString(target);
	}
}
