package view;
import con.Controller;
import model.GAAlgorithm;
import java.util.Scanner;

public class TUI extends View{
		
	private Controller con;
	private Scanner input;
	
	public TUI(GAAlgorithm model){
		con = new Controller(model);
		input = new Scanner(System.in);
	}
	
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
