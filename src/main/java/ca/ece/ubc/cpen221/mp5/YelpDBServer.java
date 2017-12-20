package ca.ece.ubc.cpen221.mp5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

public class YelpDBServer {
	/**
	 * Rep Invariant:
	 * - YelpDB should initially and always have the restaurants, users, and reviews specified 
	 * in restaurants.json, reviews.json, and users.json
	 */

	public static final int YELPDB_PORT = 4949;
	public static final int REQUEST_HANDLE = 0;
	public static final int REQUEST_INFO = 1;
	private ServerSocket serverSocket;
	private YelpDB yelpDB;
	
	public static Integer nextBusinessId = 1;
	public static Integer nextReviewId = 1;
	public static Integer nextUserId = 1;
	
	public YelpDBServer(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		this.yelpDB = new YelpDB("data/restaurants.json","data/users.json", 
				"data/reviews.json");
	}
	
	/**
	 * Runs the YelpDBServer, listening for connections and handling them.
	 * 
	 * @throws IOException
	 *             if the main server socket is broken
	 */
	public void serve() throws IOException {
		while (true) {
			// wait until a client connects
			final Socket socket = serverSocket.accept();
			// have a new thread handle the client's request
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			});
			// start the thread
			handler.start();
		}
	}
	
	/**
	 * Handle one client connection. Returns when client disconnects.
	 * 
	 * @param socket
	 *            socket where client is connected
	 * @throws IOException
	 *             if connection encounters an error
	 */
	public void handle(Socket socket) throws IOException {
		System.err.println("client connected");
		
		// get the request from the client in its input stream
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		
		// set up the output stream to send the reply to the client
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		
		try {
			
			for(String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
			
				String message;
				
				// lack of a space indicates that there is no clear request handle,
				// request info pair
				if(!line.contains(" ")) {
					message = "ERR: ILLEGAL_REQUEST";
				}
				else {
					// split the request into 2 components: the request handle to identify
					// the type of request, and the request info the type of request is
					// supposed to work with
					String[] request = line.split(" ", 2);
					
					// a request is done and a message delivered based upon the
					// request handle
					if(request[REQUEST_HANDLE].equals("GETRESTAURANT")) {
						message = GETRESTAURANT(request[REQUEST_INFO]);
					}
					else if(request[REQUEST_HANDLE].equals("ADDUSER")) {
						message = ADDUSER(request[REQUEST_INFO]);
					}
					else if(request[REQUEST_HANDLE].equals("ADDRESTAURANT")) {
						message = ADDRESTAURANT(request[REQUEST_INFO]);
					}
					else if(request[REQUEST_HANDLE].equals("ADDREVIEW")) {
						message = ADDREVIEW(request[REQUEST_INFO]);
					}
					else if(request[REQUEST_HANDLE].equals("QUERY")) {
						message = QUERY(request[REQUEST_INFO]);
					}
					
					// if the request handle is not recognized, identify it as
					// an illegal request
					else {
						message = "ERR: ILLEGAL_REQUEST";
					}
				}
				
				// notify the client of the action done
				System.err.println("reply: " + message);
				out.println(message);
				
				out.flush();
				
			}
		} finally {
			out.close();
			in.close(); }}

	
	/**
	 * Returns a restaurant from YelpDB's list of restaurants that
	 * corresponds to the business ID provided in Json string format,
	 * or an error message if the given business ID corresponds to no
	 * restaurant in the YelpDB
	 * 
	 * @param business_id
	 * 			business ID of the restaurant to be searched for in YelpDB
	 * @return String
	 * 			Json string format of the restaurant that corresponds to the
	 * 			business ID
	 * 			error message if the restaurant could not be found in YelpDB
	 * 			based on the given business ID
	 */
	private synchronized String GETRESTAURANT(String business_id) {
		// access the server's instance of yelpDB's list of restaurants
		ArrayList<Restaurant> Restaurants = yelpDB.getRestaurants();
		
		// filter out all the restaurants that do not have a matching business ID
		ArrayList<Restaurant> validRestaurants = (ArrayList<Restaurant>)
				Restaurants.stream()
				.filter(restaurant -> 
				(restaurant.getBusiness_id().equals(business_id)))
				.collect(Collectors.toList());
		
		if (validRestaurants.size() == 1)
		{
			Restaurant result = validRestaurants.get(0);
			
			// build a json object out of the fields of the restaurant
			JsonObjectBuilder value = Json.createObjectBuilder()
			    	.add("open", result.isOpen())
			        .add("url", result.getUrl())
					.add("longitude", BigDecimal.valueOf(result.getLongitude()))
					.add("neighborhoods", arrayToJson(result.getNeighborhoods()))
					
					
					.add("business_id",result.getBusiness_id())
					.add("name", result.getName())
					.add("categories", arrayToJson(result.getCategories()))
					.add("state", result.getState())
					.add("type", "business")
					.add("stars", result.getStars())
					.add("city", result.getCity())
					
					.add("full_address", result.getFull_address())
					.add("review_count", result.getReview_count())

					.add("photo_url", result.getPhoto_url())
					.add("schools",  arrayToJson(result.getSchools()))
					
					.add("latitude", BigDecimal.valueOf(result.getLatitude()))
					.add("price", result.getPrice());
			
			// create a string out of the json object
			String info = value.build().toString();
			
			return info;
			
		// return an error if the number of restaurants that matches the business ID
		// is not exactly 1
		} else if (validRestaurants.size() == 0)
		{
			return "ERR: ILLEGAL_REQUEST";
		}
		else
		{
			try {
				throw new DatabaseErrorException();
			} catch (DatabaseErrorException e) {
				return "ERR: ILLEGAL_REQUEST";
			} 
		}
				
	}
	
	/**
	 * Given user information in json format, creates a YelpUser object and adds
	 * it to the YelpDB if possible
	 * 
	 * @param user_information
	 * 			String providing the information to build the new YelpUser object
	 * @return String
	 * 			json format string representing the full object representation of
	 * 			the newly constructed YelpUser
	 * 			or an error message if the user_information was not in json string
	 * 			format, or was in json string format but did not provide the name
	 */
	private synchronized String ADDUSER(String user_information) {
		String name ="";
		
		// attempts to read user information, and returns an error string
		// if it cannot be interpreted as a json string
		try {
			JsonObject jsonLine = Json.createReader(new StringReader(user_information)).readObject();
			name =jsonLine.getString("name");
		}
		catch (Exception e) {
			return "ERR: INVALID_USER_STRING";
		}

		// generate a unique user ID for the new user
		String user_id = Integer.toString(nextUserId);
		// generate a unique url based on the new user ID
		String url =  "http://www.yelp.com/user_details?userid=" + nextUserId;
		
		// increment the user ID for the next generation of a new YelpUser
		nextUserId++;
		
		// generate a fresh hashmap of empty votes
		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		votes.put("funny", 0);
		votes.put("useful", 0);
		votes.put("cool", 0);
		
		// initialize review count as 0
		int review_count = 0;
		// initialize average stars as 0
		double average_stars = 0;
		
		// create a new YelpUser object with the fields
		YelpUser user = new YelpUser(url, user_id, name, votes,review_count, average_stars);
		
		// get the list of YelpUsers from the server's instance of YelpDB
		// and add the new YelpUser to that list
		ArrayList<YelpUser> newYelpUsers =  yelpDB.getYelpUsers();
		newYelpUsers.add(user);
		yelpDB.setYelpUsers(newYelpUsers);
		
		// create a json object based on the fields made
		JsonObject value = Json.createObjectBuilder()
			    	.add("url", url)
			    	.add("votes", hashToJson(votes))
			    	.add("review_count", review_count)
			    	.add("user_id", user_id)
			    	.add("name", name)
			    	.add("average_stars", average_stars)
					.build();
		
		// turn the json object into a string to return
		String info = value.toString();
		
		return info ;
	}
	
	/**
	 * Given restaurant information in json format, creates a Restaurant object and adds
	 * it to the YelpDB if possible
	 * 
	 * @param restaurant_information
	 * 			String providing the information to build the new Restaurant object
	 * @return String
	 * 			json format string representing the full object representation of
	 * 			the newly constructed Restaurant
	 * 			or an error message if the restaurant_information was not in json string
	 * 			format, or was in json string format but did not provide the
	 * 			necessary information	
	 */
	private synchronized String ADDRESTAURANT(String restaurant_information) {

		String name;
		boolean open;
		double longitude;
		String[] neighborhoods;
		String[] categories;
		String state;
		String city;
		String full_address;
		String photo_url;
		String[] schools;
		double latitude;
		int price;
		 
		try {
			// attempt to read the restaurant information as a json string
			JsonObject jsonLine = Json.createReader(new StringReader(restaurant_information)).readObject();
			name =jsonLine.getString("name");
			
			// uses the restaurant information to generate the Restaurant
			// to be constructed's fields
			open = jsonLine.getBoolean("open");
		    longitude = jsonLine.getJsonNumber("longitude").doubleValue();
	
		    neighborhoods = JsonParser.toArrayString("neighborhoods", jsonLine);
		    
		    categories = JsonParser.toArrayString("categories", jsonLine);
		    state =jsonLine.getString("state");
		    
		    city =jsonLine.getString("city");
		    full_address =jsonLine.getString("full_address");
		    photo_url =jsonLine.getString("photo_url");
		    schools =JsonParser.toArrayString("schools", jsonLine);
		    latitude =jsonLine.getJsonNumber("latitude").doubleValue();
		    price = jsonLine.getInt("price");

		}
		catch (Exception e) {
			return "ERR: INVALID_RESTAURANT_STRING";
		}
		
		// create a unique business ID
		String business_id = Integer.toString(nextBusinessId);
		// create a unique url with the new business ID
		String url =  "http://www.yelp.com/biz/" + nextBusinessId + "-" + name;
		
		// initialize stars of restaurant as 0
		double stars = 0;
		
		// create a fresh, empty, set of votes
		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		votes.put("funny", 0); votes.put("useful", 0); votes.put("cool", 0);
		
		// initialize review count as 0
		int review_count = 0;
		
		// create a new restaurant object based on the fields generated for it
		Restaurant restaurant = new Restaurant(open, url, latitude, longitude, neighborhoods,
				business_id, name, categories, state, city,
				full_address, stars, review_count,photo_url,schools, price );
		
		// if the new restaurant has an invalid invariant, return an error string
		if(!restaurant.checkInvariant()) {
			return "ERR: INVALID_RESTAURANT_STRING";
		}
		
		// increment business ID for creation of the next new restaurant
		nextBusinessId++;
		
		// add the new restaurant object to this server's instance of YelpDB's 
		// list of restaurants
		ArrayList<Restaurant> newRestaurants =  yelpDB.getRestaurants();
		newRestaurants.add(restaurant);
		yelpDB.setRestaurants(newRestaurants);
		
		JsonObject value = Json.createObjectBuilder()
			    	.add("open", open)
			        .add("url", url)
					.add("longitude", BigDecimal.valueOf(longitude))
					.add("neighborhoods", arrayToJson(neighborhoods))
					
					
					.add("business_id",business_id)
					.add("name", name)
					.add("categories", arrayToJson(categories))
					.add("state", state)
					.add("type", "business")
					.add("stars", BigDecimal.valueOf(stars))
					.add("city", city)
					
					.add("full_address", full_address)
					.add("review_count", review_count)

					.add("photo_url", photo_url)
					.add("schools",  arrayToJson(schools))
					
					.add("latitude", BigDecimal.valueOf(latitude))
					.add("price", price).build();
		
		String info = value.toString();
		
		return info;
	}
	
	/**
	 * Given review information in json format, creates a Review object and adds
	 * it to the YelpDB if possible
	 * 
	 * @param review_information
	 * 			String providing the information to build the new Review object
	 * @return String
	 * 			json format string representing the full object representation of
	 * 			the newly constructed Review
	 * 			or an error message if the review_information was not in json string
	 * 			format, or was in json string format but did not provide the
	 * 			necessary information, or provides a user ID or business ID that is
	 * 			not present in the YelpDB
	 */
	private synchronized String ADDREVIEW(String review_information) {
		// try to interpret the review_information as a json string
		try {
			JsonReader reader = Json.createReader(new StringReader(review_information));
			JsonObject object = reader.readObject();
			reader.close();
			
			// try to interpret the json string as a representation of a Review
			try {
				String business_id = object.getString("business_id");
				
				// checks this server's instance of YelpDB's list of restaurants
				// for a restaurant corresponding to the given business ID
				ArrayList<Restaurant> Restaurants = yelpDB.getRestaurants();
				ArrayList<Restaurant> restChecker =(ArrayList<Restaurant>) 
						Restaurants.stream()
				.filter(r -> r.getBusiness_id().equals(business_id))
				.collect(Collectors.toList());
				
				// return an error message if no restaurant exists that corresponds
				// to the business ID
				if(restChecker.isEmpty()) {
					return "ERR: NO_SUCH_RESTAURANT";
				}
				
				// create a fresh hashmap of votes
				HashMap<String, Integer> votes = new HashMap<String, Integer>();
				votes.put("cool", 0); votes.put("useful", 0); votes.put("funny", 0);
				
				// create a unique review ID for the new Review
				String review_id = Integer.toString(nextReviewId);
				
				String text = object.getString("text");
				
				double stars = object.getJsonNumber("stars").doubleValue();
				
				String user_id = object.getString("user_id");
				
				// checks this server's instance of YelpDB's list of YelpUser
				// for a restaurant corresponding to the given user ID				
				ArrayList<YelpUser> YelpUsers = yelpDB.getYelpUsers();
				ArrayList<YelpUser> userChecker = (ArrayList<YelpUser>) YelpUsers.stream()
						.filter(u -> u.getUser_id().equals(user_id))
						.collect(Collectors.toList());
				
				// returns an error message if no user exists that corresponds
				// to the user ID
				if(userChecker.isEmpty()) {
					return "ERR: NO_SUCH_USER";
				}
				
				// increase the review counts of both the restaurant and yelp user
				// corresponding to this review
				restChecker.get(0).increaseReview_count();
				userChecker.get(0).increaseReview_count();
				
				// update the average stars of the restaurant and user associated
				// with this review
				restChecker.get(0).updateAverage_stars(stars);
				userChecker.get(0).updateAverage_stars(stars);
				
				HashMap<String, Integer> date = JsonParser.getDateHash("date", object);
				String dateString = object.getString("date");
				
				// create a new Review object
				Review newRev = new Review(business_id, votes, review_id, text,
						stars, user_id, date);
				
				// return an error string if the invariant of the newly made
				// review is not valid
				if(!newRev.checkInvariant()) {
					return "ERR: INVALID_REVIEW_STRING";
				}
				
				// add this review to this server's instance of YelpDB's
				// list of reviews
				ArrayList<Review> Reviews = yelpDB.getReviews();
				Reviews.add(newRev);
				yelpDB.setReviews(Reviews);
				
				// create a json object out of the fields of the newly
				// made review
				JsonObjectBuilder builder = Json.createObjectBuilder()
						.add("type", "review")
						.add("business_id", business_id)
						.add("votes", hashToJson(votes))
						.add("review_id", review_id)
						.add("text", text)
						.add("stars", BigDecimal.valueOf((int)stars))
						.add("user_id", user_id)
						.add("date", dateString);
				
				JsonObject added = builder.build();
				// convert the json object into its json string representation
				String jsonString = added.toString();
				
				// increment nextReviewId for creation of the next new review
				nextReviewId++;
				
				return jsonString;
			//display an error if the json string cannot be recoginized as a review
			} catch(NullPointerException e) {
				return "ERR: INVALID_REVIEW_STRING";
			}
		
		// display an error if the request info cannot be recognized as a json string	
		} catch (JsonException e) {
			return "ERR: INVALID_REVIEW_STRING";
		}
		
	}
	
	/**
	 * Given a query string, returns all restaurants in this server's instance of
	 * the YelpDB's list of restaurants that fit the specifications of the query
	 * string in json format.
	 * 
	 * @param queryString
	 * 			String to specify the kind of restaurants expected to be returned
	 * @return jsonString
	 * 			String in json format that contains all the restaurants
	 * @return error message
	 * 			specifies if the queryString is poorly formatted, or if the queryString
	 * 			returns no restaurants
	 */
	private synchronized String QUERY(String queryString) {
		
		try {
			// obtains the set of restaurants that match the queryString's specifications
			Set<Restaurant> queryResult = yelpDB.getMatches(queryString);
			
			// if no restaurants match the specifications, return a no match error message
			if(queryResult.isEmpty()) {
				return "ERR: NO_MATCH";
			}
			else {
				JsonArrayBuilder builder = Json.createArrayBuilder();
				
				// for every restaurant that meets the queryString's specifications,
				// add it as a JsonObjectBuilder to the JsonArrayBuilder
				for(Restaurant r : queryResult) {
					builder.add(Json.createObjectBuilder()
							.add("open", r.isOpen())
							.add("url", r.getUrl())
							.add("longitude", BigDecimal.valueOf(r.getLongitude()))
							.add("neighborhoods", arrayToJson(r.getNeighborhoods()))
							.add("business_id", r.getBusiness_id())
							.add("name", r.getName())
							.add("categories", arrayToJson(r.getCategories()))
							.add("state", r.getState())
							.add("type", "business")
							.add("stars", BigDecimal.valueOf(r.getStars()))
							.add("city", r.getCity())
							.add("full_address", r.getFull_address())
							.add("review_count", r.getReview_count())
							.add("photo_url", r.getPhoto_url())
							.add("schools", arrayToJson(r.getSchools()))
							.add("latitude", BigDecimal.valueOf(r.getLatitude()))
							.add("price", BigDecimal.valueOf(r.getPrice())));
				}
				
				// turn the JsonArrayBuilder into a string to return
				String jsonString = builder.build().toString();
				return jsonString;
			}
		
		// if the queryString is not in valid format, return an invalid query error string
		} catch(Exception e) {
			return "ERR: INVALID_QUERY";
		}
	}
	
	/**
	 * Helper method that converts an array of strings into a json string
	 * @param stringArr
	 * 			String[] to be converted into a json string
	 * @return resultArr
	 * 			json string representation of stringArr
	 */
	private JsonArray arrayToJson(String[] stringArr)
	{
		JsonArrayBuilder buildArr = Json.createArrayBuilder();
		
		for (String str : stringArr)
		{
			buildArr.add(str);
		}
		JsonArray resultArr = buildArr.build();
		
		return resultArr;
	}
	
	/**
	 * Helper method that converts a hashmap of votes into a jsonObject
	 * 
	 * @param hash
	 * 			votes hashmap to convert to String
	 * 			must have exactly three keys "funny", "useful", and "cool"
	 * @return Json string representation of the hashmap
	 */
	private JsonObject hashToJson(HashMap<String, Integer> hash) {
		JsonObjectBuilder buildObj = Json.createObjectBuilder();
		
		buildObj.add("cool", hash.get("cool"));
		buildObj.add("useful", hash.get("useful"));
		buildObj.add("funny", hash.get("funny"));
		
		return buildObj.build();
	}
	
	
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELPDB_PORT);
			server.serve();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}