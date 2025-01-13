
import java.util.*;
import java.io.*;
/**
 * The main/runer class
 */
public class Main {
	//the sentence from the user
	/**
	 * the string that the user uses for input
	 */ 
	private static String userInput = "";
	/**
	 * max agression for girlfriend
	 */ 
	private static final int maxAgression = 10; 
	/**
	 * Takes in input and gets the responce from Girlfriend and prints it
	 * @param args the main string
	 */ 
	public static void main(String[] args) {
		boolean isTalking = true;
		Scanner input = new Scanner(System.in); //creates a scanner for user input
		System.out.print("Welcome to girlfriend simulator, What would you like to name her? "); //opening statement goes here
		
		userInput = input.nextLine();
		Girlfriend gf = new Girlfriend(userInput);
		
		while (isTalking) {
			System.out.print(gf.askQuestion() + " ");
			System.out.println(gf.checkForTriggers(input.nextLine()));
			
			if(gf.getAgression() >= maxAgression){
				System.out.print("I'm done");
				System.out.print("*Your girlfriend got too mad and stormed off*");
				System.exit(0); //this number can be changed
			}
		}
		input.close();
	}
}
