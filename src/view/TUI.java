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
	
	public void setTargetString() {
		boolean targetSet = false;
		do{
			System.out.println("What is the target string? (Must contain *only* 1s and 0s)");
			targetSet = con.setTargetString(input.nextLine());
			System.out.println(targetSet);
		}while(!targetSet);
	}
}
