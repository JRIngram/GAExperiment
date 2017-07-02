package model;
import java.util.Random;
/**
 * Member class for genetic algorithm
 * @author JRIngram
 *
 */
public class Member implements Comparable{
	
	private String chromosome;
	private int fitness = 0; //Lower the number the better the fitness
	private String targetString;
	private boolean bredThisGen;
	
	/**
	 * Creates a member with a fixed choromosome length and the goal
	 * @param chromosomeLength Number of bits for the target string.
	 * @param targetString Goal state.
	 */
	public Member(int chromosomeLength, String targetString){
		this.targetString = targetString;
		StringBuilder chromosomeString = new StringBuilder();
		for(int i = 0; i < chromosomeLength; i++){
			chromosomeString.append(generateGene());
		}
		chromosome = chromosomeString.toString();
		setFitness(targetString);
		bredThisGen = false;
	}
	
	/**
	 * Creates a new member based off of the two halves of parents.
	 * Also has the possibility of the chromosome mutating.
	 * @param firstHalf First half of one of the parents.
	 * @param secondHalf Second half of one of the parents.
	 * @param targetString Goal state.
	 */
	public Member(String firstHalf, String secondHalf, String targetString){
		StringBuilder chromosomeString = new StringBuilder();
		chromosomeString.append(firstHalf);
		chromosomeString.append(secondHalf);
		chromosome = chromosomeString.toString();
		this.targetString = targetString;
		mutate();
		setFitness(targetString);
	}
	
	/**
	 * Generates an invididual gene/bit for the Member's chromosome.
	 * @return
	 */
	private String generateGene(){
		Random rnd = new Random();
		return new Integer(rnd.nextInt(2)).toString();
	}
	
	/**
	 * @return The Member's choromsome.
	 */
	public String getChromosome(){
		return chromosome;
	}
	
	/**
	 * Sets the chromosome for the member.
	 * @param newGene
	 */
	public void setChromosome(String newChromosome){
		this.chromosome = newChromosome;
	}
	
	/**
	 * Gets the hamming distance / fitness of the member.
	 * The lower the value, the closer the member is to the goal.
	 * @return Fitness of the member.
	 */
	public int getFitness(){
		return fitness;
	}
	
	/**
	 * Calculates the fitness of the member based off the hamming distance from the target string.
	 * The lower the fitness, the better.
	 * @param targetString
	 */
	private void setFitness(String targetString){
		try{
			if(targetString.length() != chromosome.length()){
				fitness = 0;
			}
			else{
				String[] targetArray = targetString.split("");
				String[] chromosomeArray = chromosome.split("");
				int hammingDistance = 0;
				for(int i = 0; i < chromosomeArray.length; i++){
					if(!targetArray[i].toString().equals(chromosomeArray[i].toString())){
						hammingDistance++;
					}
				}
				fitness = hammingDistance;
			}
		}catch(IndexOutOfBoundsException e){
			System.out.println("Different string sizes! Lowest fitness assummed.");
			fitness = targetString.length();
		}
	}
	
	/**
	 * Prints the Member's chromosome and fitness.
	 */
	public String toString(){
		return "Chromosome: " + chromosome + "; Fitness: " + fitness;
	}
	
	/**
	 * Compares the fitness of two members.
	 */
	@Override
	public int compareTo(Object o) {
		Member comparingMem = (Member) o;
		return this.getFitness() - comparingMem.getFitness();
	}
	
	/**
	 * Causes the Member to breed with another member.
	 * Precondition is that the member hasn't already bred this generation.
	 * @param mem
	 * @return An array of the members two offspring.
	 */
	public Member[] breed(Member mem){
		if(!bredThisGen){
			int mid = this.chromosome.length() / 2;
			
			//Splits this member in half
			String[] thisHalves = new String[2];
			thisHalves[0] = this.chromosome.substring(0, mid);
			thisHalves[1] = this.chromosome.substring(mid, this.chromosome.length());
			
			//Splits other member in half
			String[] memHalves = new String[2];
			memHalves[0] = mem.getChromosome().substring(0, mid);
			memHalves[1] = mem.getChromosome().substring(mid, mem.chromosome.length());
			
			//Generate children
			Member[] children = new Member[2];
			children[0] = new Member(thisHalves[0], memHalves[1], targetString);
			children[1] = new Member(memHalves[0], thisHalves[1], targetString);
			this.bredThisGen = true;
			mem.setBredThisGen(true);
			return children;
		}
		return null;
	}
	
	/**
	 * Sets if the Member has bred this generation, used to ensure it doesn't breed multiple times per generation.
	 * @param bred Boolean to set the state of if it's bred.
	 */
	public void setBredThisGen(boolean bred){
		this.bredThisGen = bred;
	}
	
	/**
	 * Returns if the Member has bred this generation.
	 * @return State of if the member has bred.
	 */
	public boolean getBredThisGen(){
		return this.bredThisGen;
	}
	
	/**
	 * Mutates the Member's chromosome, with a 1 in 100 chance of the chromosome mutating.
	 */
	private void mutate(){
		Random rnd = new Random();
		boolean mutation = false;
		String[] splitChromosome = chromosome.split("");
		for(int i = 0; i < splitChromosome.length; i++){
			if(rnd.nextInt(100 + 1) == 1){
				if(splitChromosome[i].equals("0")){
					splitChromosome[i] = "1";
				}else if (splitChromosome[i].equals("1")){
					splitChromosome[i] = "0";
				}
				mutation = true;
			}
		}
		if(mutation){
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < chromosome.length(); i++){
				sb.append(splitChromosome[i]);
			}
			chromosome = sb.toString();
		}
	}
}
