import java.util.*;
import java.io.*;

public class Girlfriend {
	private double agression; //current agression
	private final String NAME;
	public ArrayList<String> triggerWords;
	
	public Girlfriend(String name){
		agression = 0;
		NAME = name;
		File file = new File("triggerWords.txt");
		triggerWords = fileToArrayList(file);
	}
	
	public String askQuestion(){
		return "question";
	}
	
	public String checkForTriggers(String response){
		for(String word : triggerWords){
			word = word.toLowerCase();
			response = response.toLowerCase();
			
			if(response.indexOf(word) != -1){
				agression += 2;
				return " >:(";
			}
		}
		return ":)";
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
}
