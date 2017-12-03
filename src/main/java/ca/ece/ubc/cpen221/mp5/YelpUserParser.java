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

public class YelpUserParser extends Parser {
	
	/**
	 * Parses a JSON file to return a list of Yelp user objects with the tags as the fields
 	 * @param fileName with the address of the the file containing following tags
	 * - url (string)
	 * - name (string)
	 * - review_count (int)
	 * - votes (array with tags "cool", "useful", and "funny", corresponding to ints)
	 * - user_id (string)
	 * - average_stars (double
	 * @return ArrayList of Yelp users from the file specified in fileName
	 * @throws IOException
	 */
	
	public static ArrayList<YelpUser> Parse(String fileName) throws IOException {
		ArrayList<YelpUser> yelpUsers = new ArrayList<YelpUser>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
      
       //priming read
       String result = br.readLine();
       while (result != null) {
	        JsonReader reader = Json.createReader(new StringReader(result));

	        JsonObject currentLine = reader.readObject();
	        reader.close();
	        
		    String url = currentLine.getString("url");
	    	String user_id =  currentLine.getString("user_id");
		    String name =currentLine.getString("name");
		    HashMap<String, Integer> votes= getVotesHash("votes", currentLine);
		    int review_count =currentLine.getInt("review_count");
		    double average_stars =currentLine.getJsonNumber("average_stars").doubleValue();
		    YelpUser user = new YelpUser(url,  user_id, name, votes, review_count, average_stars) ;
		    yelpUsers.add(user);
	        result = br.readLine();
       }
       br.close();
       
       return yelpUsers;
	}
}

