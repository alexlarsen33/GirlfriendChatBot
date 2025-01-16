
import java.util.*;
import java.io.*;
/**
 * Interprets Files in order to give proper questions and respond to triggers
 * 
 * @version 1.0
 * @since December 20, 2024
 * @author Alex Larsen
 * @author Alexander Page
 * @author Ethan Freckleton
*/ 
public class Girlfriend {
	/**
	 * current agression for Girlfriend changes based on user input
	 */ 
	private int agression;
	/**
	 * Girfriends name
	 */ 
	private final String NAME;
	/**
	 * A list of trigger words that increases agression based on the users input
	 */ 
	private ArrayList<String> triggerWords;
	/**
	 * An arrayList of questions that your girlfriend asks
	 */ 
	private ArrayList<String> questions;
	/**
	 * The girlfriend constructor
	 * 
	 * @param name the name for the girlfriend
	 */ 
	public Girlfriend(String name){
		agression = 0;
		NAME = name;
		triggerWords = fileToArrayList(new File("triggerWords.txt"));
		questions = fileToArrayList(new File("questions.txt"));
	}
	/**
	 * Method to get the a question to ask the user
	 * 
	 * @return String the question that the method wants to ask the user
	 */ 
	public String askQuestion(){
		//Makes sure the question list isn't empty
		if(questions.size() == 0){
			return "I'm out of questions. You win.";
		}
		
		//Set up stuff for first index because some variables need initial values differently
		int randomInt = (int)(Math.random() * 4); //Random Variance so Question isn't as predictable
		int index = questions.get(0).indexOf("-"); // Separates text line by index
		String closestQuestion =  questions.get(0).substring(index + 2); //Finds the question at current index
		int closestScore =  Math.abs(Integer.parseInt(questions.get(0).substring(0, index - 1)) - agression + randomInt);//Finds agressin score to add at current index
		int questionIndex = 0; //Remembers closest question index
		
		for (int i = 1; i < questions.size(); i++) {
			index = questions.get(i).indexOf("-");
			
			int testScore = Integer.parseInt(questions.get(i).substring(0, index - 1)); // Gets the agression score at the new index
			if(Math.abs(testScore - agression + randomInt) < closestScore){ // Logic to see if the current question is better fit than the old one
				closestQuestion = questions.get(i).substring(index + 2);
				closestScore = Math.abs(Integer.parseInt(questions.get(i).substring(0, index - 1)));
				questionIndex = i;
			}
		}
		questions.remove(questionIndex); // removes the question from the list so questions aren't repeated
		return closestQuestion;
	}
	
	/**
	 * Method to check user response for things that would make the girlfriend mad
	 * 
	 * @param response the thing the user said that should be scanned for triggers
	 * @return String the girlfriend's response to any triggers found
	 */ 
	public String checkForTriggers(String response){
		//Breaks responses into individual Strings
		String[] responseList = response.split(" ");
		
		//Searches through both arrays for anything that mathces
		for(String responseWord : responseList){
			for(String word : triggerWords){
				//Ensure it ignores case
				word = word.toLowerCase();
				response = response.toLowerCase();
				
				//Checks if there is a specified agression value
				if(word.indexOf(",") != -1){
					// Has to divide the word into it's parts: Triggerword and agression
					int firstIndex = word.indexOf(",");
					int secondIndex = word.indexOf("-");
					String comment = word.substring(secondIndex + 2);
					int agressionScore = Integer.parseInt(word.substring(firstIndex + 2, secondIndex - 1));
					word = word.substring(0, firstIndex);
					
					if (responseWord.equalsIgnoreCase(word)){ //Checks if the response contains the trigger
						agression += agressionScore;
						return comment;
					}
				}else if (responseWord.equalsIgnoreCase(word)){//If there is not it is just a female name and it doesnt have to do much work
					if (word.indexOf(",") == -1){ //Checks if the response contains the name
						agression += 1;
						return "I don't want to hear about this " + word + " person. You probably love them more than me";
					}
				}
			}
		}
		//Makes sure the reponse isn't too long or too short
		if(responseList.length < 4){
			agression += 1;
			return "That's really all you have to say? Think of a little more to respond.";
		}else if (responseList.length > 20){
			agression += 1;
			return "I'm not reading all of that";
		}else{
			return findPositiveResponse(); // If everything went right, it returns a basic resopnse
		}
	}
	
	/**
	 * Method for checkForTriggers in the case that no triggers are found
	 * 
	 * @return String a neutral response depending on the agression of the girlfriend
	 */ 
	private String findPositiveResponse(){
		String[][] agressionTable = { 
			{"That's nice to hear.", "Oh, cool.", "Hahahaha yes :)", "wow sounds great", "hehehehe yea thanks"}, 
			{"Okay, sounds good.", "That's good to know.", "Okii", "I agree", "good idea"}, 
			{"Hmm, interesting.", "Hmm, I get you.", "Okay", "That's cool"}, 
			{"Alright, I see.", "Alright, I get it.", "ok...", "yep", "nice"}, 
			{"Oh, really?", "Oh, I see.", "mhm", "k", "sounds good"}, 
			{"Alright, then.", "Okay, I understand.", "Got it.", "Got it, thanks.", "ok."}, 
			{"Understood.", "Alright, I understand.", "mhmmmmmmmmm", "Alright, got it.", "Okay, understood."}, 
			{"Okay, fine.", "Fine, I get it.", "I'm done"}, 
			{"Okay, whatever.", "Alright, if you say so.", "I don't even care what you say at this point.", "Why should I believe what you say."}, 
			{"Sure, if you think so.", "Whatever you say.", "Sounds believable.", "I don't believe Liars.", "It's about time we break up"}};
		
		return agressionTable[agression][(int)(Math.random() * agressionTable[agression].length)]; //Finds a random item from the table at the current agression level
	}
	
	/**
	 * Method to convert text files into ArrayLists
	 * 
	 * @param file the file that should be converted into ArrayList
	 * @return ArrayList<String> the file converted into an array list with each line being an item in the list
	 */ 
	private ArrayList<String> fileToArrayList(File file){
		ArrayList<String> newArray = new ArrayList<String>();
		try { // Tries to open the file
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()){ // Scanner reads all lines and puts each line into the ArrayList
				String next = sc.nextLine();
				if (!next.equalsIgnoreCase(NAME)) { //Doesn't add a female name if it is the same as the girlfriend's name
					newArray.add(next);
				}
			}
			
			sc.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return newArray; // Returns the file as an arraylist
	}
	
	/**
	 * Method for getting the girlfriend's agression
	 * 
	 * @return int the agression of the girlfriend
	 */ 
	public int getAgression(){
		return agression;
	}
}
