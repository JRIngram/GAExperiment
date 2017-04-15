import java.util.Arrays;

public class Population {
	
	Member[] population;
	String targetString;
	
	public Population(int populationSize, int chromosomeSize, String targetString){
		this.targetString = targetString;
		population = new Member[populationSize];
		//System.out.println("Generating population...");
		for(int i = 0; i < population.length; i++){
			population[i] = new Member(chromosomeSize, targetString);
		}
		//System.out.println("Initial population generated!");
		sortPopulation();
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Member member : population){
			sb.append("\t" + member.toString() + "\n");
		}
		return sb.toString();
	}
	
	public Member[] getPopulation(){
		return population;
	}
	
	public void sortPopulation(){
		Arrays.sort(population);
	}
	
	public Member getFittestMember(){
		return population[0];
	}
	
	public Member getLeastFitMember(){
		return population[population.length - 1];
	}
	
	/**
	 * Sorts the population and returns the fittest half of the population
	 * @return Fittest half of population as a Member array, i.e. population with lowest fitness rating
	 */
	public Member[] getFittestHalf(){
		sortPopulation();
		Member[] fittestHalf = new Member[population.length / 2];
		for(int i = 0; i < (population.length / 2);i++){
			fittestHalf[i] = population[i];
			//System.out.println(population[i].toString());
		}
		return fittestHalf;
	}
	
	/**
	 * Sets the half of the population array with the highest fitness rating to null. 
	 */
	public void clearUnfittestHalf(){
		for(int i = (population.length / 2); i < population.length; i++){
			population[i] = null;
		}
	}
	
	
	public void createNextGeneration(Member[] fittestHalfPrevious){
		Member[] newGen = new Member[population.length];
		
		//Moves fittest half of the previous population into the new population.
		for(int i = 0; i < (population.length / 2); i++){
			newGen[i] = getFittestHalf()[i];
		}
		
		for(int i = 0; i < (newGen.length / 2); i++){
			if(!newGen[i].getBredThisGen()){
				Member[] newMembers = newGen[i].breed(newGen[i+1]);
				
				//THIS IS LOOPING OVER THE SAME BITS OF THE GENERATION
				//It actually loops over the whole new generation and where
				//an element is null, sets it as the first two Member's children.
				//Needs to be fixed to set *ONLY* two.
				for(int c = 0; c < newGen.length; c++){
					if(newGen[c] == null){
						newGen[c] = newMembers[0];
						newGen[c + 1] = newMembers[1];
						break; //Find a more elegant solution?
					}
				}
			}
		}
		
		if(!checkForFullArray(newGen)){
			System.out.println("ARRAY IS NOT NULL");
			for(int i = 0; i < newGen.length; i++){
				System.out.println("\tMember " + (i+1) + ": " + newGen[i].toString());
			}
		}
		
		else{
			population = newGen;
			Arrays.sort(population);
			for(int i = 0; i < population.length; i++){
				population[i].setBredThisGen(false);			
			}
		}
	}
	
	/**
	 * Checks if any of the values in a given array are null
	 * @param array The array to be checked.
	 * @return False if a value within the array is null. True if no value within the array is false.
	 */
	private boolean checkForFullArray(Object[] array){
		for(int i = 0; i < array.length; i++){
			if(array[i] == null){
				return false;
			}
		}
		return true;
	}
}
