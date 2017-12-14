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
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

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
			
			JsonArray value = Json.createArrayBuilder()
				     .add(Json.createObjectBuilder()
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
						.add("price", result.getPrice()))
						.build();
			String info = value.toString();
			
			return info;
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
		nextUserId++;
		
		String url =  "http://www.yelp.com/user_details?userid=" + nextUserId;
		
		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		votes.put("funny", 0);
		votes.put("useful", 0);
		votes.put("cool", 0);
		
		int review_count = 0;
		double average_stars = 0;
		
		YelpUser user = new YelpUser(url, user_id, name, votes,review_count, average_stars);
		
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
	
		    neighborhoods = Parser.toArrayString("neighborhoods", jsonLine);
		    
		    categories = Parser.toArrayString("categories", jsonLine);
		    state =jsonLine.getString("state");
		    
		    city =jsonLine.getString("city");
		    full_address =jsonLine.getString("full_address");
		    photo_url =jsonLine.getString("photo_url");
		    schools =Parser.toArrayString("schools", jsonLine);
		    latitude =jsonLine.getJsonNumber("latitude").doubleValue();
		    price = jsonLine.getInt("price");
		    
		    if (price > 4)
		    {
		    	//price cannot be greater than 4
		    	throw new Exception();
		    }
		}
		catch (Exception e) {
			return "ERR: INVALID_USER_STRING";
		}
		
		String business_id = Integer.toString(nextBusinessId);
		nextBusinessId++;
		
		String url =  "http://www.yelp.com/biz/" + nextBusinessId + "-" + name;
		
		double stars = 0;
		
		HashMap<String, Integer> votes = new HashMap<String, Integer>();
		votes.put("funny", 0);
		votes.put("useful", 0);
		votes.put("cool", 0);
		
		int review_count = 0;
		
		Restaurant restaurant = new Restaurant(open, url, latitude, longitude, neighborhoods,
				business_id, name, categories, state, city,
				full_address, stars, review_count,photo_url,schools, price );
		
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
		return review_information;
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
	
}
