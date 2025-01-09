


import java.util.*;
import java.io.*;

public class Main {
	//the sentence from the user
	private static String userInput = ""; //the string that the user uses for input
	private static final double maxAgression = 10; //max agression for girlfriend
	
	//arraylists for text documents
	public static void main(String[] args) {
		boolean isTalking = true;
		Scanner input = new Scanner(System.in); //creates a scanner for user input
		System.out.print("Welcome to girlfriend simulator, What would you like to name her? "); //opening statement goes here
		
		userInput = input.nextLine();
		Girlfriend gf = new Girlfriend(userInput);
		
		while (isTalking) {
			System.out.print(gf.askQuestion());
			System.out.println(gf.checkForTriggers(input.nextLine()));
			
			if(gf.getAgression() > maxAgression){
				System.exit(0);
			}
		}
		input.close();
	}
}
