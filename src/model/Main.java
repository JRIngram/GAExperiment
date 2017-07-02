package model;
import view.TUI;
public class Main {

	public static void main(String[] args) {
		GAAlgorithm ga = new GAAlgorithm("00100101");
		ga.startAlgorithm();
		System.out.println(ga.generationMessage());
		do{
			ga.runGeneration();
			System.out.println(ga.generationMessage());
		}while(!ga.checkIfTargetFound());
		System.out.println(ga.foundInMessage());
	}

}
