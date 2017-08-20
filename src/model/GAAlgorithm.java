package model;

public class GAAlgorithm{
	
	/**Target String for the Genetic Algorithm*/
	private String target;
	/**The population of the genetic algorithm*/
	private Population population;
	/**Used to keep track of how many generations the algorithm has run*/
	private int generationNum;
	/**How many members the population should contain at the start and end of each generation*/
	private int populationSize;
	
	/**
	 * Sets the default values for the genetic algorithm.
	 */
	public GAAlgorithm() {
		this.target = "1001";
		generationNum = 1;
		populationSize = 4;
	}
	
	/**
	 * Sets the target string for the genetic algorithm.
	 * The target String must only contain 0s and 1s.
	 * @param target Target string for the genetic algorithm
	 * @return If the string only contains 0s and 1s true is returned, else false is returned.
	 */
	public boolean setTargetString(String target) {
		if(target.matches("^(0|1)*$")){
			this.target = target;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Sets the population size for the genetic algorithm to use.
	 * The population must divide by 2 into an even whole number.
	 * @param populationSize size of the population for the genetic algorithm to use.
	 * @return true if the population size is valid, else false.
	 */
	public boolean setPopulationSize(int populationSize){
		if((populationSize == ((populationSize / 2) * 2))
				&& ((populationSize / 2) % 2) == 0){
			this.populationSize = populationSize;	
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Generates the initial population for the genetic algorithm to use.
	 */
	public void startAlgorithm(){
		System.out.println("Generating initial population...");
		this.population = new Population(populationSize, target.length(), target);
		System.out.println("Initial population generated!");
	}
	
	public String generationMessage(){
		String generationMessage = "";
		generationMessage = "Generation: " + generationNum;
		generationMessage += "\n" + population.toString();
		generationMessage += "\n" + "Fittest member: " + population.getFittestMember().toString();
		generationMessage += "\n" + "Least fit member: " + population.getLeastFitMember().toString();
		generationMessage += "\n";
		return generationMessage;
	}
	
	/**
	 * Checks if the target string is present in the population.
	 * @return true if the target string is present in the population, false if it is not.
	 */
	public boolean checkIfTargetFound(){
		if(this.population.getFittestMember().getFitness() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Increments the generation number by 1, and creates a new population by breeding the fittest half.
	 */
	public void runGeneration(){
		generationNum++;
		this.population.createNextGeneration(this.population.getFittestHalf());
	}
	
	/**
	 * Returns a String to state how many generations the target string was found in.
	 */
	public String foundInMessage(){
		return "Target found in " + generationNum + " generations!";
	}
	
}


