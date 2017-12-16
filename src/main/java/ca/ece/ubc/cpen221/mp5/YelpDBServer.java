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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrLex;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrListenerCollect;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParse;
import ca.ece.ubc.cpen221.mp5.ANTLR.mp5AntlrParseListener;


public class YelpDBServer {

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
/*	public void serve() throws IOException {
		while(true) {
			
			Socket socket = serverSocket.accept();
			try {
				handle(socket);
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} finally {
				socket.close();
			}
			
		}
	}
*/
	public void serve() throws IOException {
		while (true) {
			System.out.println("running");
			final Socket socket = serverSocket.accept();
			System.out.println("socket accepted");
			
			Thread handler = new Thread(new Runnable() {
				public void run() {
					try {
						try {
							System.out.println("trying to handle");
							handle(socket);
						} finally {
							socket.close();
						}
					} catch (IOException ioe) {
						System.out.println("didnt handle");
						ioe.printStackTrace();
					}
				}
			});
			handler.start();
		}
	}
	
	private void handle(Socket socket) throws IOException {
		System.err.println("client connected");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		
		try {
			
			for(String line = in.readLine(); line != null; line = in.readLine()) {
				System.err.println("request: " + line);
				
				// split the request into 2 components: the request handle to identify
				// the type of request, and the request info the type of request is
				// supposed to work with
				String[] request = line.split(" ", 2);
				
				String message;
				
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
				
				// notify the client of the action done
				System.err.println("reply: " + message);
				out.println(message);
				
				out.flush();
				
			}
		} finally {
			out.close();
			in.close();
		}
	}
	
	private String QUERY(String query_id) {
		Set<Restaurant> filtered;
		try {
			filtered = yelpDB.getMatches(query_id);
		} catch (Exception e) {
			return "ERR: INVALID_QUERY";
		}
		
		if (filtered.isEmpty()) {
			return "ERR: NO_MATCH";
		}
		
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for (Restaurant rest : filtered)
		{
			builder.add(Json.createObjectBuilder()
			    	.add("open", rest.isOpen())
			        .add("url", rest.getUrl())
					.add("longitude", BigDecimal.valueOf(rest.getLongitude()))
					.add("neighborhoods", arrayToJson(rest.getNeighborhoods()))
					
					
					.add("business_id",rest.getBusiness_id())
					.add("name", rest.getName())
					.add("categories", arrayToJson(rest.getCategories()))
					.add("state", rest.getState())
					.add("type", "business")
					.add("stars", rest.getStars())
					.add("city", rest.getCity())
					
					.add("full_address", rest.getFull_address())
					.add("review_count", rest.getReview_count())

					.add("photo_url", rest.getPhoto_url())
					.add("schools",  arrayToJson(rest.getSchools()))
					
					.add("latitude", BigDecimal.valueOf(rest.getLatitude()))
					.add("price", rest.getPrice()));
		}
		String jsonString = builder.build().toString();
		return jsonString;
	}

	private String GETRESTAURANT(String business_id) {
		//find restaurant based on restaurant id
		
		ArrayList<Restaurant> Restaurants = yelpDB.getRestaurants();
		
		
		ArrayList<Restaurant> validRestaurants = (ArrayList<Restaurant>)
				Restaurants.stream()
				.filter(restaurant -> 
				(restaurant.getBusiness_id().equals(business_id)))
				.collect(Collectors.toList());
		
		if (validRestaurants.size() == 1)
		{
			Restaurant result = validRestaurants.get(0);
			
			String info = printRestaurant(result);
			
			return info;
		} else if (validRestaurants.isEmpty())
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
	
	
	
	public String printRestaurant(Restaurant rest) {
		JsonObject value = Json.createObjectBuilder()
			    	.add("open", rest.isOpen())
			        .add("url", rest.getUrl())
					.add("longitude", BigDecimal.valueOf(rest.getLongitude()))
					.add("neighborhoods", arrayToJson(rest.getNeighborhoods()))
					
					
					.add("business_id",rest.getBusiness_id())
					.add("name", rest.getName())
					.add("categories", arrayToJson(rest.getCategories()))
					.add("state", rest.getState())
					.add("type", "business")
					.add("stars", rest.getStars())
					.add("city", rest.getCity())
					
					.add("full_address", rest.getFull_address())
					.add("review_count", rest.getReview_count())

					.add("photo_url", rest.getPhoto_url())
					.add("schools",  arrayToJson(rest.getSchools()))
					
					.add("latitude", BigDecimal.valueOf(rest.getLatitude()))
					.add("price", rest.getPrice())
					.build();
		String jsonString = value.toString();
		return jsonString;
	}
	
	private String ADDUSER(String user_information) {
		
		String name ="";
		try {
			JsonObject jsonLine = Json.createReader(new StringReader(user_information)).readObject();
			name =jsonLine.getString("name");
		}
		catch (Exception e) {
			return "ERR: INVALID_USER_STRING";
		}
		
		//{"url": "http://www.yelp.com/user_details?userid=418rK1b_PkXJTWhSRKxjew", "votes": {"funny": 149, "useful": 351, "cool": 159}, "review_count": 189,
		//"type": "user", "user_id": "418rK1b_PkXJTWhSRKxjew", "name": "Kelley L.", "average_stars": 3.73544973544974}
		
		String user_id = Integer.toString(nextUserId);
		String url =  "http://www.yelp.com/user_details?userid=" + nextUserId;
		nextUserId++;
		
		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		votes.put("funny", 0);
		votes.put("useful", 0);
		votes.put("cool", 0);
		
		int review_count = 0;
		double average_stars = 0;
		
		YelpUser user = new YelpUser(url, user_id, name, votes,review_count, average_stars);
		
		
		ArrayList<YelpUser> newYelpUsers =  yelpDB.getYelpUsers();
		newYelpUsers.add(user);
		yelpDB.setYelpUsers(newYelpUsers);
		
		JsonArray value = Json.createArrayBuilder()
			     .add(Json.createObjectBuilder()
			    	.add("url", url)
			    	.add("votes", hashToJson(votes))
			    	.add("review_count", review_count)
			    	.add("user_id", user_id)
			    	.add("name", name)
			    	.add("average_stars", average_stars)
			    		 )
					.build();
		String info = value.toString();
		
		return info ;
	}
	
	private String ADDRESTAURANT(String restaurant_information) {

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
			JsonObject jsonLine = Json.createReader(new StringReader(restaurant_information)).readObject();
			name =jsonLine.getString("name");
			
			
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
		    
		    if (price > 4)
		    {
		    	//price cannot be greater than 4
		    	throw new Exception();
		    }
		}
		catch (Exception e) {
			return "ERR: INVALID_RESTAURANT_STRING";
		}
		
		String business_id = Integer.toString(nextBusinessId);
		String url =  "http://www.yelp.com/biz/" + nextBusinessId + "-" + name;
		nextBusinessId++;
		double stars = 0;
		
		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		votes.put("funny", 0);
		votes.put("useful", 0);
		votes.put("cool", 0);
		
		int review_count = 0;
		
		Restaurant restaurant = new Restaurant(open, url, latitude, longitude, neighborhoods,
				business_id, name, categories, state, city,
				full_address, stars, review_count,photo_url,schools, price );
		
		ArrayList<Restaurant> newRestaurants =  yelpDB.getRestaurants();
		newRestaurants.add(restaurant);
		yelpDB.setRestaurants(newRestaurants);
		
		//{"open": true, "url": "http://www.yelp.com/biz/cafe-3-berkeley", "longitude": -122.260408, "neighborhoods": ["Telegraph Ave", "UC Campus Area"], 
		//"business_id": "gclB3ED6uk6viWlolSb_uA", "name": "Cafe 3", 
		//"categories": ["Cafes", "Restaurants"], "state": "CA", "type": "business", 
		//"stars": 2.0, "city": "Berkeley", "full_address": "2400 Durant Ave\nTelegraph Ave\nBerkeley, CA 94701",
		//"review_count": 9, "photo_url": "http://s3-media1.ak.yelpcdn.com/bphoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg", 
		//"schools": ["University of California at Berkeley"], "latitude": 37.867417, "price": 1}
		JsonArray value = Json.createArrayBuilder()
			     .add(Json.createObjectBuilder()
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
					.add("price", price))
					.build();
		String info = value.toString();
		
		return info;
	}
	
	private String ADDREVIEW(String review_information) {
		try {
			JsonReader reader = Json.createReader(new StringReader(review_information));
			JsonObject object = reader.readObject();
			reader.close();
			
			try {
				String business_id = object.getString("business_id");
				
				ArrayList<Restaurant> Restaurants = yelpDB.getRestaurants();
				ArrayList<Restaurant> restChecker =(ArrayList<Restaurant>) 
						Restaurants.stream()
				.filter(r -> r.getBusiness_id().equals(business_id))
				.collect(Collectors.toList());
				
				if(restChecker.isEmpty()) {
					return "ERR: NO_SUCH_RESTAURANT";
				}
				
				HashMap<String, Integer> votes = new HashMap<String, Integer>();
				votes.put("funny", 0); votes.put("useful", 0); votes.put("cool", 0);
				
				String review_id = Integer.toString(nextReviewId);
				
				String text = object.getString("text");
				
				double stars = object.getJsonNumber("stars").doubleValue();
				
				String user_id = object.getString("user_id");
				
				ArrayList<YelpUser> YelpUsers = yelpDB.getYelpUsers();
				ArrayList<YelpUser> userChecker = (ArrayList<YelpUser>) YelpUsers.stream()
						.filter(u -> u.getUser_id().equals(user_id))
						.collect(Collectors.toList());
				
				if(userChecker.isEmpty()) {
					return "ERR: NO_SUCH_USER";
				}
				
				restChecker.get(0).increaseReview_count();
				userChecker.get(0).increaseReview_count();
				
				HashMap<String, Integer> date = JsonParser.getDateHash("date", object);
				String dateString = object.getString("date");
				
				Review newRev = new Review(business_id, votes, review_id, text,
						stars, user_id, date);
				
				ArrayList<Review> Reviews = yelpDB.getReviews();
				Reviews.add(newRev);
				yelpDB.setReviews(Reviews);
				
				JsonObjectBuilder builder = Json.createObjectBuilder()
						.add("type", "review")
						.add("business_id", business_id)
						.add("votes", hashToJson(votes))
						.add("review_id", review_id)
						.add("text", text)
						.add("stars", BigDecimal.valueOf(stars))
						.add("user_id", user_id)
						.add("date", dateString);
				
				JsonObject added = builder.build();
				String jsonString = added.toString();
				
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
	
	
	private JsonArray hashToJson(HashMap<String,Integer> hash)
	{
		JsonArrayBuilder buildArr = Json.createArrayBuilder();
		
		buildArr.add(Json.createObjectBuilder().add("funny", hash.get("funny")).build());
		buildArr.add(Json.createObjectBuilder().add("useful", hash.get("useful")).build());
		buildArr.add(Json.createObjectBuilder().add("cool", hash.get("cool")).build());
		
		JsonArray resultArr = buildArr.build();
		
		return resultArr;
	}
	
	public static void main(String[] args) {
		try {
			YelpDBServer server = new YelpDBServer(YELPDB_PORT);
			System.out.println("running");
			server.serve();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void useAntlr() throws IOException {
		
		
		
	}
}
