package com.runner;


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
		int randomInt = (int)(Math.random() * 4);
		int index = questions.get(0).indexOf("-");
		String closestQuestion =  questions.get(0).substring(index + 2);
		int closestScore =  Math.abs(Integer.parseInt(questions.get(0).substring(0, index - 1)) - agression + randomInt);
		int questionIndex = 0;
		
		for (int i = 1; i < questions.size(); i++) {
			index = questions.get(i).indexOf("-");
			
			int testScore = Integer.parseInt(questions.get(i).substring(0, index - 1));
			if(Math.abs(testScore - agression + randomInt) < closestScore){
				closestQuestion = questions.get(i).substring(index + 2);
				closestScore = Math.abs(Integer.parseInt(questions.get(i).substring(0, index - 1)));
				questionIndex = i;
			}
		}
		questions.remove(questionIndex);
		return closestQuestion;
	}
	
	public String checkForTriggers(String response){
		String[] responseList = response.split(" ");
		
		for(String responseWord : responseList){
			for(String word : triggerWords){
				word = word.toLowerCase();
				response = response.toLowerCase();

				if(word.indexOf(",") != -1){
					int firstIndex = word.indexOf(",");
					int secondIndex = word.indexOf("-");
					String comment = word.substring(secondIndex + 2);
					int agressionScore = Integer.parseInt(word.substring(firstIndex + 2, secondIndex - 1));
					word = word.substring(0, firstIndex);
					
					if (responseWord.equalsIgnoreCase(word)){
						agression += agressionScore;
						return comment;
					}
				}else if (responseWord.equalsIgnoreCase(word)){
					if (word.indexOf(",") == -1){
						agression += 1;
						return "I don't want to hear about this " + word + " person. You probably love them more than me";
					}
				}
			}
		}
		return findPositiveResponse();
	}
	
	private String findPositiveResponse(){
		String[][] agressionTable = {{"Hahahaha yes :)", "wow sounds great", "hehehehe yea thanks"},
									{"Okii", "I agree", "good idea"},
									{"Okay", "That's cool"},
									{"ok...", "yep", "nice"},
									{"mhm", "k", "sounds good"},
									{"ok."},
									{"mhmmmmmmmmm"},
									{"I'm done"},
									{"I don't even care what you say at this point.", "Why should I believe what you say."},
									{"Sounds believable.", "I don't believe Liars.", "It's about time we break up"}};
		
		return agressionTable[agression][(int)(Math.random() * agressionTable[agression].length)];
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
