
import java.util.*;
import java.io.*;
/**
 * The main/runner class for managing the girlfriend
 * 
 * @version 1.0
 * @since December 20, 2024
 * @author Alex Larsen
 * @author Alexander Page
 * @author Ethan Freckleton
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
	 * Number of question's asked before loosing
	 */ 
	private static int questionsAsked = 0; 
	/**
	 * Takes in input and gets the responce from Girlfriend and prints it
	 * @param args the main string
	 */ 
	public static void main(String[] args) {
		boolean isTalking = true;
		Scanner input = new Scanner(System.in); //creates a scanner for user input
		System.out.print("Welcome to girlfriend simulator, What would you like to name her? "); //opening statement goes here
		
		userInput = input.nextLine();
		Girlfriend gf = new Girlfriend(userInput);//Create girlfriend object
		
		while (isTalking) {
			System.out.print("(" + gf.getAgression() + ") " + gf.askQuestion() + " ");
			questionsAsked += 1;
			
			String response = input.nextLine();
			if(!response.equalsIgnoreCase("exit")){
				System.out.println(gf.checkForTriggers(response));
				
				if(gf.getAgression() >= maxAgression){
					System.out.println("I'm done");
					System.out.println("*Your girlfriend got too mad and stormed off*");
					System.out.println("You survived: " + questionsAsked + " questions.");
					
					isTalking = false;
				}
			}else{
				isTalking = false;
			}
		}
		input.close();
	}
}
