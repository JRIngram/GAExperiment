
public class Main {

	public static void main(String[] args) {
		//TODO reenable string printing!
		String target = "100000110001001000011000001101110100101111010111";//48-bit
		Double[] results = new Double[100];
		//Number of mutation rates.
		//Section needing rework
		for(int i = (int) Math.floor(target.length() * .7); i <= target.length(); i++){
			double result = 0;
			int numberOfTests = 50000;
			for(int c = 0; c < numberOfTests; c++){
				System.out.println("Test num: " + c);
				result += (double) runAlgorithm(target, i + 1);
			}
			results[i] = result / numberOfTests;
		}
		System.out.println("Started at:" + Math.floor(.7 * target.length()));
		for(int i = 0; i < results.length; i++){
			if(i <= target.length() && i >= Math.floor(.7 * target.length())){
				System.out.println(results[i]);
			}
		}
		System.out.println("Finished at " + target.length());

	}

	private static int runAlgorithm(String target, int mutationRate){
		Population population = new Population(12, target.length(), target, mutationRate);
		System.out.println("Mutation Rate: " + mutationRate);
		System.out.println(population.toString());
		System.out.println("Fittest member: " + population.getFittestMember().toString());
		System.out.println("Least fit member: " + population.getLeastFitMember().toString() +"\n");
		population.getFittestHalf();
		System.out.println("\n");
		int generationNum = 1;
		//System.out.println("Generation " + generationNum + ":");
		//System.out.println(population.toString());
		do{
			generationNum++;
			//System.out.println("Generation " + generationNum + ":");
			population.createNextGeneration(population.getFittestHalf());
			//System.out.println(population.toString());
			//System.out.println("Fittest member: " + population.getFittestMember().toString());
			//System.out.println("Least fit member: " + population.getLeastFitMember().toString() +"\n");
			//System.out.println("**************");
		}while(population.getFittestMember().getFitness() != 0);
		System.out.println("\nSolution found within " + generationNum + " generations!");
		System.out.println(population.toString());
		return generationNum;
	}

}
