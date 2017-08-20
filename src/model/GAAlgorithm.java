package model;

public class GAAlgorithm{

	private String target;
	private Population population;
	private int generationNum;
	private int populationSize;
	
	public GAAlgorithm(String target) {
		this.target = target;
		generationNum = 1;
		populationSize = 4;
	}
	
	public boolean setTargetString(String target) {
		if(target.matches("^(0|1)*$")){
			this.target = target;
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean setPopulationSize(int populationSize){
		if((populationSize == ((populationSize / 2) * 2))
				&& ((populationSize / 2) % 2) == 0){
			this.populationSize = populationSize;	
			System.out.println((populationSize / 2) % 2);
			return true;
		}
		else{
			return false;
		}
	}
	
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
	
	public boolean checkIfTargetFound(){
		if(population.getFittestMember().getFitness() == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void runGeneration(){
		generationNum++;
		population.createNextGeneration(population.getFittestHalf());
	}
	
	public String foundInMessage(){
		return "Target found in " + generationNum + " generations!";
	}
	
}


