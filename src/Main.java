
public class Main {

	public static void main(String[] args) {
		//TODO reenable string printing!
		String target = "110110110100000010011011";//32-bit
		Double[] results = new Double[25];
		//Number of mutation rates.
		//Section needing rework
		for(int i = 1; i < 100; i++){
			double result = 0;
			int numberOfTests = 10000;
			for(int c = 0; c < numberOfTests; c++){
				System.out.println("Test num: " + c);
				result += (double) runAlgorithm(target, i + 1);
			}
			results[i] = result / numberOfTests;
		}
		for(int i = 0; i < results.length; i++){
			System.out.println(results[i]);
		}

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
