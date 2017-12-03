package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;

import javax.json.*;

public class Parser {
	
	/**
	 * Creates an regular Java array from what is listed after a label in a string in JSON format
	 * @param label of the tag in the JSON string before the array 
	 * @param currentLine is a JsonObject that contains the current line in the JSON file
	 * @return an array of strings that contain all the strings contained in brackets following the label
	 */
	protected static String[] toArrayString(String label, JsonObject currentLine)
	{
		//make a new JsonArray from the file 
		JsonArray JA = currentLine.getJsonArray(label);
		//create new array with the same size as the JsonArray
	    String[] returnArray = new String[JA.size()];
	    
	    //transfer the contents of the JsonArray to the new array
	    for (int i= 0; i < JA.size(); i++)
	    {
	    	returnArray[i] = JA.getString(i);
	    }
	    
	    //return the new array
	    return returnArray;
	}
	
	/**
	 * Creates a hashmap of votes with the categories "cool", "useful", and "funny" from the file in the file location 
	 * specified by fileName
	 * @param label of the tag in the JSON string before the array containing the votes
	 * @param currentLine is a JsonObject that contains the current line in the JSON file
	 * @return a hashmap, where they key is the rating category ("cool", "useful", and "funny") 
	 * and the values are its corresponding numbers
	 */
	
	protected static HashMap<String, Integer> getVotesHash(String label, JsonObject currentLine)
	{
		//create a new hashmap to return
		HashMap<String, Integer> votes= new HashMap<String, Integer>();
		
		//create a JsonObject from the tag's label
    	JsonObject votesObject = currentLine.getJsonObject(label);
    	
    	//take the corresponding ratings from "cool", "useful", and "funny" and assign it to the hashmap to return
        votes.put("cool", votesObject.getInt("cool"));
        votes.put("useful", votesObject.getInt("useful"));
        votes.put("funny", votesObject.getInt("funny"));
        
        //return hashmap of votes
		return votes;
	}
	
	/**
	 * Creates a hashmap expressing the date, with day, month, and year as values in the hashmap
	 * @param label of the tag in the JSON string before the array containing the date
	 * @param currentLine is a JsonObject that contains the current line in the JSON file
	 * @return a hashmap with keys "day", "month, and "year" with their values as integers 
	 * containing day, month, and year information respectively
	 */
	protected static HashMap<String, Integer> getDateHash(String label, JsonObject currentLine)
	{
		//get the string following the tag's label
    	String stringDate = currentLine.getString(label);
    	
    	//parse the day, month, and year from the string
		int day = Integer.parseInt(stringDate.substring(8,10));
		int month = Integer.parseInt(stringDate.substring(5,7)); 
		int year = Integer.parseInt(stringDate.substring(0,4));
		
		//make new hashmap to return the date 
		HashMap<String, Integer> date= new HashMap<String, Integer>();
		//create "day", "month, and "year" hashmap keys and assign their values as integers
        date.put("day", day);
        date.put("month", month);
        date.put("year", year);
        
        //reture date hashmap
		return date;
	}
}
