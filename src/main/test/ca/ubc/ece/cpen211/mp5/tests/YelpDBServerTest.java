package ca.ubc.ece.cpen211.mp5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;

import org.junit.Test;

import ca.ece.ubc.cpen221.mp5.YelpDBClient;
import ca.ece.ubc.cpen221.mp5.YelpDBServer;

public class YelpDBServerTest {
	
	@Test
	public void serverTest() throws InterruptedException {
		Thread serverThread = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBServer testServer = new YelpDBServer(YelpDBServer.YELPDB_PORT);
					testServer.serve();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		// illegal request; no space
		Thread noSpace = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "INVALID";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: ILLEGAL_REQUEST";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal request; request handle not recognized
		Thread space = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "INVALID REQUEST";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: ILLEGAL_REQUEST";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal GETRESTAURANT request
		Thread getRestaurant1 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "GETRESTAURANT h_we4E3zofRTf4G0JTEF0A";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "{\"open\":true,\"url\":\"http://www.yelp.com/"
							+ "biz/fondue-fred-berkeley\",\"longitude\":-122.25894,"
							+ "\"neighborhoods\":[\"UC Campus Area\"],\"business_id\":"
							+ "\"h_we4E3zofRTf4G0JTEF0A\",\"name\":\"Fondue Fred\","
							+ "\"categories\":[\"Fondue\",\"Restaurants\"],\"state\":"
							+ "\"CA\",\"type\":\"business\",\"stars\":3.0,\"city\":"
							+ "\"Berkeley\",\"full_address\":\"2556 Telegraph Ave"
							+ "\\nUC Campus Area\\nBerkeley, CA 94704\",\"review_count\":"
							+ "172,\"photo_url\":\"http://s3-media1.ak.yelpcdn.com/bphoto/"
							+ "07PJUIzisU--faHrNi3vTw/ms.jpg\",\"schools\":[\"University "
							+ "of California at Berkeley\"],\"latitude\":37.863919,"
							+ "\"price\":3}";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal GETRESTAURANT request; expecting ILLEGAL_REQUEST error
		Thread getRestaurant2 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "GETRESTAURANT 1";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: ILLEGAL_REQUEST";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal ADDUSER request
		Thread addUser1 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDUSER {\"name\": \"Sathish\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "{\"url\":\"http://www.yelp.com/user_details?"
							+ "userid=1\",\"votes\":{\"cool\":0,\"useful\":0,"
							+ "\"funny\":0},\"review_count\":0,\"user_id\":\"1\","
							+ "\"name\":\"Sathish\",\"average_stars\":0.0}";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDUSER request; expecting INVALID_USER_STRING error
		Thread addUser2 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDUSER Sathish";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_USER_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		ArrayList<String> userIdChecker = new ArrayList<String>();
		
		// legal ADDUSER request, used to test thread safety
		Thread addUser3 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDUSER {\"name\": \"Andrea\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String user_id = Json.createReader(new StringReader(reply))
							.readObject().getString("user_id");
					userIdChecker.add(user_id);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal ADDUSER request, used to test thread safety
		Thread addUser4 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDUSER {\"name\": \"Robin\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String user_id = Json.createReader(new StringReader(reply))
							.readObject().getString("user_id");
					userIdChecker.add(user_id);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal ADDRESTAURANT request
		Thread addRestaurant1 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDRESTAURANT {\"name\": \"ANR\",\"open\": true,"
							+ "\"longitude\": -122.260408,\"neighborhoods\":"
							+ "[\"Ladner\", \"Richmond\"],\"categories\": [\"Chinese\","
							+ "\"Filipino\"],\"state\":\"BC\",\"stars\": 2.0,"
							+ "\"city\": \"Vancouver\",\"full_address\": \"whocares\","
							+ "\"review_count\":9,\"photo_url\":\"whocares2\",\"schools\":"
							+ "[\"UBC\"], \"latitude\": 37.867417, \"price\": 1}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "{\"open\":true,\"url\":\"http://www.yelp.com/"
							+ "biz/1-ANR\",\"longitude\":-122.260408,\"neighborhoods\":"
							+ "[\"Ladner\",\"Richmond\"],\"business_id\":\"1\",\"name\":"
							+ "\"ANR\",\"categories\":[\"Chinese\",\"Filipino\"],\"state\":"
							+ "\"BC\",\"type\":\"business\",\"stars\":0.0,\"city\":"
							+ "\"Vancouver\",\"full_address\":\"whocares\","
							+ "\"review_count\":0,\"photo_url\":\"whocares2\","
							+ "\"schools\":[\"UBC\"],\"latitude\":37.867417,\"price\":1}";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDRESTAURANT request; violates restaurant's rep invariant
		Thread addRestaurant2 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDRESTAURANT {\"name\": \"ANR\",\"open\": true,"
							+ "\"longitude\": -180.260408,\"neighborhoods\":"
							+ "[\"Ladner\", \"Richmond\"],\"categories\": [\"Chinese\","
							+ "\"Filipino\"],\"state\":\"BC\",\"stars\": 6.0,"
							+ "\"city\": \"Vancouver\",\"full_address\": \"whocares\","
							+ "\"review_count\":9,\"photo_url\":\"whocares2\",\"schools\":"
							+ "[\"UBC\"], \"latitude\": 37.867417, \"price\": 1}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_RESTAURANT_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDRESTAURANT request; missing required information
		Thread addRestaurant3 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDRESTAURANT {\"open\": true,"
							+ "\"longitude\": -180.260408,\"neighborhoods\":"
							+ "[\"Ladner\", \"Richmond\"],\"categories\": [\"Chinese\","
							+ "\"Filipino\"],\"state\":\"BC\",\"stars\": 6.0,"
							+ "\"city\": \"Vancouver\",\"full_address\": \"whocares\","
							+ "\"review_count\":9,\"photo_url\":\"whocares2\",\"schools\":"
							+ "[\"UBC\"], \"latitude\": 37.867417, \"price\": 1}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_RESTAURANT_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDRESTAURANT request; missing required information
		Thread addRestaurant4 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDRESTAURANT ANR";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_RESTAURANT_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		ArrayList<String> businessIdChecker = new ArrayList<String>();
		
		// legal ADDRESTAURANT request, used to test thread safety
		Thread addRestaurant5 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDRESTAURANT {\"name\": \"rest1\",\"open\": true,"
							+ "\"longitude\": -122.260408,\"neighborhoods\":"
							+ "[\"Ladner\", \"Richmond\"],\"categories\": [\"Chinese\","
							+ "\"Filipino\"],\"state\":\"BC\",\"stars\": 2.0,"
							+ "\"city\": \"Vancouver\",\"full_address\": \"whocares\","
							+ "\"review_count\":9,\"photo_url\":\"whocares2\",\"schools\":"
							+ "[\"UBC\"], \"latitude\": 37.867417, \"price\": 1}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String business_id = Json.createReader(new StringReader(reply))
							.readObject().getString("business_id");
					businessIdChecker.add(business_id);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal ADDRESTAURANT request, used to test thread safety
		Thread addRestaurant6 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String request = "ADDRESTAURANT {\"name\": \"rest2\",\"open\": true,"
							+ "\"longitude\": -122.260408,\"neighborhoods\":"
							+ "[\"Ladner\", \"Richmond\"],\"categories\": [\"Chinese\","
							+ "\"Filipino\"],\"state\":\"BC\",\"stars\": 2.0,"
							+ "\"city\": \"Vancouver\",\"full_address\": \"whocares\","
							+ "\"review_count\":9,\"photo_url\":\"whocares2\",\"schools\":"
							+ "[\"UBC\"], \"latitude\": 37.867417, \"price\": 1}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String business_id = Json.createReader(new StringReader(reply))
							.readObject().getString("business_id");
					businessIdChecker.add(business_id);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal ADDREVIEW request
		Thread addReview1 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					String getRestaurant = "GETRESTAURANT 1";
					
					testClient.sendRequest(getRestaurant);
					
					String restaurantString0 = testClient.getReply();
					
					int review_count0 = Json.createReader(new StringReader(restaurantString0))
							.readObject().getInt("review_count");
					double stars0 = Json.createReader(new StringReader(restaurantString0))
							.readObject().getJsonNumber("stars").doubleValue();
					
					String request = "ADDREVIEW {\"business_id\":\"1\",\"text\":\"hi\","
							+ "\"stars\": 2,\"user_id\":\"1\",\"date\":\"2006-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.sendRequest(getRestaurant);
					
					String restaurantString1 = testClient.getReply();
					
					int review_count1 = Json.createReader(new StringReader(restaurantString1))
							.readObject().getInt("review_count");
					double stars1 = Json.createReader(new StringReader(restaurantString1))
							.readObject().getJsonNumber("stars").doubleValue();
					
					testClient.close();
					
					String expectedReply = "{\"type\":\"review\",\"business_id\":\"1\","
							+ "\"votes\":{\"cool\":0,\"useful\":0,\"funny\":0},"
							+ "\"review_id\":\"1\",\"text\":\"hi\",\"stars\":2,"
							+ "\"user_id\":\"1\",\"date\":\"2006-07-26\"}";
					assertEquals(reply, expectedReply);
					
					assertEquals(review_count0 + 1, review_count1);
					assertEquals(((stars0 * review_count0) + stars1) / review_count1,
							stars1, 0.0);
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDREVIEW request; violates review's rep invariant
		Thread addReview2 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW {\"business_id\":\"1\",\"text\":\"hi\","
							+ "\"stars\": 2,\"user_id\":\"1\",\"date\":\"2022-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_REVIEW_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDREVIEW request; business ID does not belong to a
		// restaurant in yelpDB
		Thread addReview3 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW {\"business_id\":\"55\",\"text\":\"hi\","
							+ "\"stars\": 2,\"user_id\":\"1\",\"date\":\"2006-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: NO_SUCH_RESTAURANT";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDREVIEW request; user ID does not belong to a yelp user
		// in yelpDB
		Thread addReview4 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW {\"business_id\":\"1\",\"text\":\"hi\","
							+ "\"stars\": 2,\"user_id\":\"55\",\"date\":\"2006-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: NO_SUCH_USER";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDREVIEW request; lacking required information
		Thread addReview5 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW {\"text\":\"hi\","
							+ "\"stars\": 2,\"user_id\":\"1\",\"date\":\"2006-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_REVIEW_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal ADDREVIEW request; request info not in json format
		Thread addReview6 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW Sathish is the best!";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_REVIEW_STRING";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		ArrayList<String> reviewIdChecker = new ArrayList<String>();
		
		// legal ADDREVIEW request, used to test thread safety
		Thread addReview7 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW {\"business_id\":\"1\",\"text\":\"rev1\","
							+ "\"stars\": 2,\"user_id\":\"1\",\"date\":\"2006-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String review_id = Json.createReader(new StringReader(reply))
							.readObject().getString("review_id");
					reviewIdChecker.add(review_id);

				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal ADDREVIEW request, used to test thread safety
		Thread addReview8 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "ADDREVIEW {\"business_id\":\"1\",\"text\":\"rev2\","
							+ "\"stars\": 2,\"user_id\":\"1\",\"date\":\"2006-07-26\"}";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String review_id = Json.createReader(new StringReader(reply))
							.readObject().getString("review_id");
					reviewIdChecker.add(review_id);

				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal QUERY request
		Thread query1 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "QUERY name(Cafe 3)";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "[{\"open\":true,\"url\":\"http://www.yelp.com"
							+ "/biz/cafe-3-berkeley\",\"longitude\":-122.260408,\"neighbor"
							+ "hoods\":[\"Telegraph Ave\", \"UC Campus Area\"],\"business_id"
							+ "\":\"gclB3ED6uk6viWlolSb_uA\",\"name\":\"Cafe 3\",\"categorie"
							+ "s\":[\"Cafes\",\"Restaurants\"],\"state\":\"CA\",\"type\":\"bu"
							+ "siness\",\"stars\":2.0,\"city\":\"Berkeley\",\"full_address\":"
							+ "\"2400 Durant Ave\\nTelegraph Ave\\nBerkeley, CA 94701\",\"revi"
							+ "ew_count\":9,\"photo_url\":\"http://s3-media1.ak.yelpcdn.com/bp"
							+ "hoto/AaHq1UzXiT6zDBUYrJ2NKA/ms.jpg\",\"schools\":[\"University "
							+ "of California at Berkeley\"],\"latitude\":37.867417,\"price\":1}]";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// legal QUERY request, no matches
		Thread query2 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "QUERY name(Sathish's)";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: NO_MATCH";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// illegal QUERY request, illformed query
		Thread query3 = new Thread(new Runnable() {
			public void run() {
				try {
					YelpDBClient testClient = new YelpDBClient("localhost", 
							YelpDBServer.YELPDB_PORT);
					
					
					String request = "QUERY Sathish's";
					
					testClient.sendRequest(request);
					
					String reply = testClient.getReply();
					
					testClient.close();
					
					String expectedReply = "ERR: INVALID_QUERY";
					assertEquals(reply, expectedReply);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// server start
		serverThread.start();
		
		// request with no space
		noSpace.run();
		
		// unrecognized request handle
		space.run();
		
		// GETRESTAURANT requests: test for proper output
		getRestaurant1.run(); getRestaurant2.run();
		
		// ADDUSER requests: test for proper output
		addUser1.run(); addUser2.run();
		
		// ADDUSER requests: test for thread safety
		addUser3.start(); addUser4.start();
		addUser3.join(); addUser4.join();
		// there should be exactly two user ID's in the arrayList
		assertEquals(userIdChecker.size(), 2);
		// the two user ID's should be unique
		assertNotEquals(userIdChecker.get(0), userIdChecker.get(1));
		
		// ADDRESTAURANT requests: test for proper output
		addRestaurant1.run(); addRestaurant2.run();
		addRestaurant3.run(); addRestaurant4.run();
		
		// ADDRESTAURANT requests: test for thread safety
		addRestaurant5.start(); addRestaurant6.start();
		addRestaurant5.join(); addRestaurant6.join();
		// there should be exactly two business ID's in the arrayList
		assertEquals(businessIdChecker.size(), 2);
		// the two business ID's should be unique
		assertNotEquals(businessIdChecker.get(0), businessIdChecker.get(1));
		
		// ADDREVIEW requests: test for proper output
		addReview1.run(); addReview2.run(); addReview3.run();
		addReview4.run(); addReview5.run(); addReview6.run();
		
		// ADDREVIEW requests: test for thread safety
		addReview7.start(); addReview8.start();
		addReview7.join(); addReview8.join();
		// there should be exactly two review ID's in the arrayList
		assertEquals(reviewIdChecker.size(), 2);
		// the two review ID's should be unique
		assertNotEquals(reviewIdChecker.get(0), reviewIdChecker.get(1));
		
		// server end
		serverThread.join(1);
	}
}
