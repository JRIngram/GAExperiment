
public class Main {

	public static void main(String[] args) {
		GAAlgorithm ga = new GAAlgorithm("00100101");
		ga.startAlgorithm();
		System.out.println(ga.generationMessage());
	}

}
