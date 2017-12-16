package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class RestaurantParser extends JsonParser  {
	
	/**
	 * Parses a JSON file to return a list of restaurants objects with the tags as the fields
 	 * @param fileName with the address of the the file containing following tags
	 * - open (boolean)
	 * - url (string)
	 * - latitude (double)
	 * - longitude (double)
	 * - neighborhoods (array of strings)
	 * - business_id (string)
	 * - name (string)
	 * - categories (array of strings)
	 * - state (string)
	 * - city (string)
	 * - full_address (string)
	 * - stars (double)
	 * - review_count (int)
	 * - photo_url (string)
	 * - schools (array of strings)
	 * - price (int)
	 * @return ArrayList of restaurants from the file specified in fileName
	 * @throws IOException
	 */
	
	public static ArrayList<Restaurant> Parse(String fileName) throws IOException {
		//Initialize array list of all restaurants
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
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
	        boolean open = currentLine.getBoolean("open");
		    String url = currentLine.getString("url");
		    double longitude = currentLine.getJsonNumber("longitude").doubleValue();
	
		    String[] neighborhoods = toArrayString("neighborhoods", currentLine);
		    
		    String business_id = currentLine.getString("business_id");
		    String name =currentLine.getString("name");
		    String[] categories = toArrayString("categories", currentLine);
		    String state =currentLine.getString("state");
		    
		    double stars =currentLine.getJsonNumber("stars").doubleValue();
		    String city =currentLine.getString("city");
		    String full_address =currentLine.getString("full_address");
		    int review_count =currentLine.getInt("review_count");
		    String photo_url =currentLine.getString("photo_url");
		    String[] schools =toArrayString("schools", currentLine);
		    double latitude =currentLine.getJsonNumber("latitude").doubleValue();
		    int price = currentLine.getInt("price");
	
		    //create new Restaurant using new fields and add new object to the main array list
		    Restaurant rest = new Restaurant(open, url, latitude, longitude, neighborhoods,
					business_id, name, categories, state, city,
					full_address, stars, review_count,photo_url,schools,price );
		    
		    restaurants.add(rest);
	        result = br.readLine();
	   }
		//close BufferedReader and return the list of Restaurants
	   br.close();
	   return restaurants;
	}

}
