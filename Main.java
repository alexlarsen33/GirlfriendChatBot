import java.util.*;
public class Main {
	//the sentence from the user
	private static String userInput = "what can you do?";
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

	
}
