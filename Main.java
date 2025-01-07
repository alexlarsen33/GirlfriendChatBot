import java.util.*;
import java.io.*;
public class Main {
	//the sentence from the user
	private static String userInput = ""; //the string that the user uses for input
	static double agression = 0; //current agression
	static final double maxAgression = 10; //max agression for girlfriend
	
	//arraylists for text documents
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in); //creates a scanner for user input
		System.out.print(""); //opening statement goes here
		
		userInput = input.nextLine();
		
		

		String lowerinput = userInput.toLowerCase();
		if (lowerinput.contains("what") && lowerinput.contains("you") && lowerinput.contains("doing")) {
			System.out.println("nothin much hbu");
		} else if (lowerinput.contains("how") && lowerinput.contains("you") && lowerinput.contains("doing")) {
			System.out.println("good hbu");
		} else if (lowerinput.contains("what") && lowerinput.contains("can") && lowerinput.contains("do")) {
			System.out.println("i can talk or chill or more ;)");
		} else if(lowerinput.contains("what") && lowerinput.contains("is") && lowerinput.contains("your") && lowerinput.contains("favorite") && lowerinput.contains("food")) {
			System.out.println("Grapes");
		}
			
	}
	public static ArrayList<String> textToArray()
	{
		ArrayList<String> triggerWords = FileReader.toStringList("triggerWords.txt");
		ArrayList<String> questions = FileReader.toStringList("questions.txt");
		
		return triggerWords;
		return questions;
	}
	

	
}
