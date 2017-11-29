package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.json.*;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

public class Parser {
	
	public static void main(String[] args) throws IOException {
       
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		InputStream file = new FileInputStream("data/restaurants.json");
		
       BufferedReader br = null;
       try {
           br = new BufferedReader(new FileReader("data/restaurants.json"));
          
           //priming read
           String result = br.readLine();
           while (result != null) {
        	   Queue<String> strings = new LinkedList<String>();
        	   Queue<BigDecimal> nums = new LinkedList<BigDecimal>();
        	   Queue<Boolean> booleans = new LinkedList<Boolean>();
        	   Queue<String[]> stringLists = new LinkedList<String[]>();
               try {
            	   
            	   final JsonParser parser1 = Json.createParser(new StringReader(result));
            	    while (parser1.hasNext()) {
            	         final Event event = parser1.next();
            	         switch (event) {
            	            case KEY_NAME:
            	                 break;
            	            case VALUE_STRING:
            	                 String string = parser1.getString();
            	                 strings.add(string);
            	                 break;
            	            case VALUE_NUMBER:
            	                BigDecimal number = parser1.getBigDecimal();
            	                nums.add(number);
            	                break;
            	            case VALUE_TRUE:
            	            	booleans.add(true);
            	                break;
            	            case VALUE_FALSE:
            	            	booleans.add(false);
            	                break;
            	            case START_ARRAY:
            	            	JsonArray stringarr = parser1.getArray();
            	            	String[] returnStr = new String[stringarr.size()];
            	            	
            	            	 for(int i = 0; i < stringarr.size(); i++){
            	            		 returnStr[i]=stringarr.get(i).toString();
            	                 }
            	            	 stringLists.add(returnStr);
            	                break;
            	            }
            	        }
            	    
            	  
            	    //https://www.programcreek.com/java-api-examples/index.php?class=javax.json.Json&method=createReader
            	    
            	    boolean open = booleans.poll();
            	    String url = strings.poll();
            	    double longitude = nums.poll().doubleValue();
            	    String[] neighborhoods = stringLists.poll();
            	    String business_id = strings.poll();
            	    String name =strings.poll();
            	    String[] categories =stringLists.poll();
            	    String state =strings.poll();
            	    
            	    strings.poll();
            	    //business

            	    double stars =nums.poll().doubleValue();
            	    String city =strings.poll();
            	    String full_address =strings.poll();
            	    int review_count =nums.poll().intValue();
            	    String photo_url =strings.poll();
            	    String[] schools =stringLists.poll();
            	    double lattitude =nums.poll().doubleValue();
            	    
            	    Restaurant rest = new Restaurant(open, url, lattitude, longitude, neighborhoods,
            				business_id, name, categories, state, city,
            				full_address, stars, review_count,photo_url,schools );
            	    restaurants.add(rest);
            		System.out.println(open);
            		System.out.println(url);
            		System.out.println(lattitude);
            		System.out.println(longitude);
            		System.out.println(neighborhoods);
            		System.out.println(business_id);
            		System.out.println(name);
            		System.out.println(categories);
            		System.out.println(state);
            		System.out.println(city);
            		System.out.println(full_address);
            		System.out.println(stars);
            		System.out.println(review_count);
            		System.out.println(photo_url);
            		System.out.println(schools);
            		System.out.println();
            		
            	    parser1.close();
                   //String url = parser.getString();
                  // System.out.println(url);
                   
            	   /*
            	   Event event = parser.next(); // START_OBJECT
            	   boolean open = (event).getBoolean("open");
            	   event = parser.next();       // KEY_NAME
            	   event = parser.next();       // VALUE_STRING
            	   parser.getString();          // "John"
            	   
            	   
                   JsonObject obj1 = parser.getObject();
                   obj1 = parser.parse(currentString);
                   JSONObject jsonObject = (JSONObject) obj1;

                   String rel = (String) jsonObject.get("rel");
                   System.out.println(rel);

                   String start = (String) jsonObject.get("start");
                   System.out.println(start);

                   String end = (String) jsonObject.get("end");
                   System.out.println(end);
                   */

               } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
               
               result = br.readLine();
           }

       } catch (Exception e) {
    	   
       }
	    
	

	
	
	}
	
}
