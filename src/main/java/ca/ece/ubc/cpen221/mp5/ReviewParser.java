package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ReviewParser extends Parser  {
	
	/**
	 * Parses a JSON file to return a list of review objects with the tags as the fields
 	 * @param fileName with the address of the the file containing following tags
	 * - business_id (string)
	 * - votes (array with tags "cool", "useful", and "funny", corresponding to ints)
	 * - review_id (string)
	 * - text (string)
	 * - stars (double)
	 * - user_id (string)
	 * - date (string in the format YYYY/MM/DD)
	 * @return ArrayList of reviews from the file specified in fileName
	 * @throws IOException
	 */
	
	public static ArrayList<Review> Parse(String fileName) throws IOException {
		//Initialize array list of all reviews
		ArrayList<Review> reviews = new ArrayList<Review>();
		
		//initialize bufferedReader that reads the contents of the file
		BufferedReader br = new BufferedReader(new FileReader(fileName));
      
		//priming read for first line
		String result = br.readLine();
		//if the there exists a line, do the following
		while (result != null) {
			//create a JsonReader for the line and make a JsonObject out of it
			JsonReader reader = Json.createReader(new StringReader(result));
			JsonObject currentLine = reader.readObject();
			reader.close();

			//assign appropriate tags to fields
			String business_id = currentLine.getString("business_id");
			HashMap<String, Integer> votes= getVotesHash("votes", currentLine);
			String review_id =currentLine.getString("review_id");
			String text =currentLine.getString("text");
			double stars = currentLine.getJsonNumber("stars").doubleValue();
			String user_id =  currentLine.getString("user_id");
			HashMap<String, Integer> date= getDateHash("date", currentLine);
			
			//create new Review using new fields and add new object to the main array list
			Review rv = new Review(business_id, votes, review_id, text,
					stars, user_id, date);
			reviews.add(rv);
			
			//read next line
			result = br.readLine();
		}
		//close BufferedReader and return the list of reviews
		br.close();
		return reviews;
	}
}
