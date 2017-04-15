
public class Main {

	public static void main(String[] args) {
		
		String target = "0001001110100110";	
		Population population = new Population(12, target.length(), target);
		System.out.println(population.toString());
		System.out.println("Fittest member: " + population.getFittestMember().toString());
		System.out.println("Least fit member: " + population.getLeastFitMember().toString() +"\n");
		population.getFittestHalf();
		System.out.println("\n");
		int generationNum = 1;
		System.out.println("Generation " + generationNum + ":");
		do{
			generationNum++;
			System.out.println("Generation " + generationNum + ":");
			population.createNextGeneration(population.getFittestHalf());
			System.out.println(population.toString());
			System.out.println("Fittest member: " + population.getFittestMember().toString());
			System.out.println("Least fit member: " + population.getLeastFitMember().toString() +"\n");
			System.out.println("**************");
		}while(population.getFittestMember().getFitness() != 0);
		System.out.println("\nSolution found within " + generationNum + " generations!");
		System.out.println(population.toString());
	}

}
