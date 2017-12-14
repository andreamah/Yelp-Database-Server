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
		return null;
	}
	
	private String ADDUSER(String user_information) {
		return null;
	}
	
	private String ADDRESTAURANT(String restaurant_information) {
		return null;
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
				
				HashMap<String, Integer> date = Parser.getDateHash("date", object);
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
	
	private JsonArray hashToJson(HashMap<String,Integer> hash)
	{
		//make array builder object and add the appropriate hashmap keys and values
		JsonArrayBuilder buildArr = Json.createArrayBuilder();
		
		buildArr.add(Json.createObjectBuilder().add("funny", hash.get("funny")).build());
		buildArr.add(Json.createObjectBuilder().add("useful", hash.get("useful")).build());
		buildArr.add(Json.createObjectBuilder().add("cool", hash.get("cool")).build());
		
		//build array and return it
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
