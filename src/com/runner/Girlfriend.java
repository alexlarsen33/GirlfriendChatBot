

import java.util.*;
import java.io.*;

public class Girlfriend {
	private int agression; //current agression
	private final String NAME;
	public ArrayList<String> triggerWords;
	public ArrayList<String> questions;
	
	public Girlfriend(String name){
		agression = 0;
		NAME = name;
		triggerWords = fileToArrayList(new File("triggerWords.txt"));
		questions = fileToArrayList(new File("questions.txt"));
	}
	
	public String askQuestion(){
		int index = questions.get(0).indexOf("-");
		String closestQuestion =  questions.get(0).substring(index + 2);
		int closestScore =  Math.abs(Integer.parseInt(questions.get(0).substring(0, index - 1)) - agression);
		int questionIndex = 0;
		
		for (int i = 1; i < questions.size(); i++) {
			index = questions.get(i).indexOf("-");
			
			int testScore = Integer.parseInt(questions.get(i).substring(0, index - 1));
			if(Math.abs(testScore - agression) < closestScore){
				closestQuestion = questions.get(i).substring(index + 2);
				closestScore = Math.abs(Integer.parseInt(questions.get(i).substring(0, index - 1)));
				questionIndex = i;
			}
		}
		questions.remove(questionIndex);
		return closestQuestion;
	}
	
	public String checkForTriggers(String response){
		for(String word : triggerWords){
			word = word.toLowerCase();
			response = response.toLowerCase();
			
			if (response.indexOf(word) != -1){
				if (word.indexOf(",") == -1){
					agression += 1;
					return "I don't want to hear about this " + word + " person. You probably love them more than me";
				} else {
					System.out.println("hi");
					int firstIndex = word.indexOf(",");
					int secondIndex = word.indexOf("-");
					int agressionScore = Integer.parseInt(word.substring(firstIndex + 1, secondIndex - 1));
					String comment = word.substring(secondIndex + 2);
					
					agression += agressionScore;
					return comment;
				}
			}
		}
		return findPositiveResponse();
	}
	
	private String findPositiveResponse(){
		String[][] agressionTable = {{"Hahahaha yes :)", "wow sounds great", "hehehehe yea thanks"},
									{"I don't care"},
									{"sure"},
									{"ok..."}};
									
		return "Cool!";
	}
	
	private ArrayList<String> fileToArrayList(File file){
		ArrayList<String> newArray = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()){
				String next = sc.nextLine();
				if (!next.equalsIgnoreCase(NAME)) {
					newArray.add(next);
				}
			}
			
			sc.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return newArray;
	}
	
	public int getAgression(){
		return agression;
	}
}
