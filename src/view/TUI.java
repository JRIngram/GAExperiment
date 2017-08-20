package view;
import model.GAAlgorithm;
import java.util.Scanner;

public class TUI extends View{
		
	/**Allows the user to enter text input*/
	private Scanner input;
	
	/**
	 * Sets the Scanner field and creates a controller for this TUI view.
	 * @param model
	 */
	public TUI(GAAlgorithm model){
		super(model);
		input = new Scanner(System.in);
	}
	
	/**
	 * Asks the user to input a target string, and only exits the loop if the user enters a valid target String.
	 * This uses the Controller to pass the message to the model, which then checks if the String is valid.
	 */
	public void setTargetString(){
		boolean targetSet = false;
		do{
			System.out.println("What is the target string? (Must contain *only* 1s and 0s)");
			targetSet = con.setTargetString(input.nextLine());
		}while(!targetSet);
	}
	
	public void setPopulationSize(){
		boolean populationSizeSet = false;
		do{
			System.out.println("What population size would you like?");
			populationSizeSet = con.setPopulationSize(input.nextInt());
			if(!populationSizeSet){
				System.out.println("Population size must satisfy the following equations:");
				System.out.println("\tfloor((S / 2) * 2) = S");
				System.out.println("\t((S / 2) % 2) = 0");
			}
		}while(!populationSizeSet);
	}
}
