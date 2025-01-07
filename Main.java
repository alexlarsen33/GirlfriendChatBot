
public class Main {
	//the sentence from the user
	private static String input = "what can you do?";
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in); //creates a scanner for user input
		System.out.print(""); //opening statement goes here
		
		userSentence = input.nextLine();
		
		

		String lowerinput = input.toLowerCase();
		if (lowerinput.contains("what") && lowerinput.contains("you") && lowerinput.contains("doing")) {
			System.out.println("nothin much hbu");
		} else if (lowerinput.contains("how") && lowerinput.contains("you") && lowerinput.contains("doing")) {
			System.out.println("good hbu");
		} else if (lowerinput.contains("what") && lowerinput.contains("can") && lowerinput.contains("do")) {
			System.out.println("i can talk or chill or more ;)");
		}
	}
	//Splits the sentence into each of its words
	
}
