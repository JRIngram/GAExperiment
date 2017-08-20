package con;
import model.GAAlgorithm;

/**
 * Acts as a controller for View objects.
 * @author JRIngram
 *
 */
public class Controller {
	
	/**The model that the Controller is sending instructions to*/
	private GAAlgorithm model;
	
	/**
	 * Sets the model field in Controller object
	 * @param model The model that the Controller is sending instructions to
	 */
	public Controller(GAAlgorithm model){
		this.model = model;
	}
	
	/**
	 * Sets the target String for the Genetic Algorithm
	 * @param target Target String
	 * @return a boolean stating if the target String has been successfully set by the model.
	 */
	public boolean setTargetString(String target){
		return model.setTargetString(target);
	}
	
	/**
	 * Sets the size of population to be used in the Genetic Algorithm
	 * @param populationSize The size of the population to be used by the Genetic Algorithm.
	 * @return a boolean stating if the population size has been successfully set by the model.
	 */
	public boolean setPopulationSize(int populationSize) {
		return model.setPopulationSize(populationSize);
	}
}
