package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

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

public class YelpDB implements MP5Db<Restaurant> {
	/**
	 * Rep Invariant:
	 * - if for some review rev, Reviews.contains(rev)
	 *     =>	there exists some restaurant res in Restaurants such that res.getBusiness_id().equals(rev.getBusiness_id())
	 *     	&&	there exists some user u in YelpUsers such that u.getUser_id().equals(rev.getUser_id())
	 * - for each restaurant res, there should be res.getReview_count() reviews in Reviews such that
	 * 		rev.getBusiness_id().equals(res.getBusiness_id())
	 * - for each yelp user u, there should be u.getReview_count() reviews in Reviews such that
	 * 		rev.getBusiness_id().equals(u.getBusiness_id())
	 */
	
	private ArrayList<Restaurant> Restaurants; //list of Restaurants that exist on Yelp
	private ArrayList<Review> Reviews; //list of Reviews that exist on Yelp
	private ArrayList<YelpUser> YelpUsers; //list of YelpUsers that exist on Yelp
	
	public YelpDB(ArrayList<Restaurant> restaurants, ArrayList<Review> reviews, ArrayList<YelpUser> yelpUsers) {
		this.Restaurants = restaurants;
		this.Reviews = reviews;
		this.YelpUsers = yelpUsers;
	}
	
	public YelpDB(String restaurantsFileName, String usersFileName, String reviewsFileName) throws IOException {
		this.Restaurants = RestaurantParser.Parse(restaurantsFileName);
		this.YelpUsers = YelpUserParser.Parse(usersFileName);
		this.Reviews = ReviewParser.Parse(reviewsFileName);
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return new ArrayList<Restaurant>(Restaurants);
	}
	
	public ArrayList<Review> getReviews() {
		return new ArrayList<Review>(Reviews);
	}
	
	public ArrayList<YelpUser> getYelpUsers() {
		return new ArrayList<YelpUser>(YelpUsers);
	}
	
	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		Restaurants = restaurants;
	}

	public void setReviews(ArrayList<Review> reviews) {
		Reviews = reviews;
	}

	public void setYelpUsers(ArrayList<YelpUser> yelpUsers) {
		YelpUsers = yelpUsers;
	}

	/**
	 * Given a YelpUser, return a list of all Reviews in the database
	 * submitted by that YelpUser
	 * @param reviewer
	 * 			YelpUser whose reviews are to be compiled and returned
	 * @return userReviews
	 * 			ArrayList of reviews submitted by the reviewer
	 */
	ArrayList<Review> usersReviews(YelpUser reviewer)
	{
		// filter out the reviews in Reviews that do not have the same
		// user ID as the reviewer
		ArrayList<Review> userReviews = (ArrayList<Review>) Reviews.stream()
				.filter(review -> review.getUser_id().equals(reviewer.getUser_id()))
				.collect(Collectors.toList());
		
		return userReviews;
	}

	/**
	 * given a restaurant, return a List containing its best 
	 * Review in the first entry of the List and its worst Review in the second entry
	 * @param restaurant
	 * 			restaurant whose best and worst reviews are to be returned
	 * @return bestAndWorstReviews
	 * 			array list containing the restaurant's best review in the first entry
	 * 			and the worst review in the second entry
	 */
	public ArrayList<Review> bestAndWorst(Restaurant restaurant)
	{
		// filter out all the reviews from Reviews that are not associcated
		// with the restaurant
		List<Review> restaurantReviews = Reviews.stream()
				.filter(review -> review.getBusiness_id().equals(restaurant.getBusiness_id()))
				.collect(Collectors.toList());
		
		ArrayList<Review> bestAndWorstReviews = new ArrayList<Review>();
		
		Review bestReview = restaurantReviews.get(0);
		Review worstReview = restaurantReviews.get(0);
		
		// search for the reviews with the highest and lowest ratings
		for(Review rev : restaurantReviews) {
			//if rev has more stars than bestReview, rev is the new bestReview
			if(rev.getStars() > bestReview.getStars()) {
				bestReview = rev;
			}
			
			// if rev has less stars than worstReview, rev is the new worstReview
			if(rev.getStars() < worstReview.getStars()) {
				worstReview = rev;
			}
		}
		
		// add those reviews to the list
		bestAndWorstReviews.add(bestReview); bestAndWorstReviews.add(worstReview);
	
		return bestAndWorstReviews;
		
	}
	
	/**
	 * given a latitude and longitude, returns a list that contains all restaurants 
	 * contained in that radius in order of distance to the center
	 * @param latitude
	 * 			latitude of the starting point
	 * @param longitude
	 * 			longitude of the starting point
	 * @param radius
	 * 			maximum distance a restaurant can be from the starting point
	 * 			to qualify being in CloseRestaurants
	 * @return CloseRestaurants
	 * 			ArrayList containing all the restaurants that are at most the
	 * 			radius's distance away from the starting point defined by
	 * 			(longitude, latitude)
	 */
	public ArrayList<Restaurant> closeRestaurants(double latitude, double longitude,
			double radius)
	{
		// filter out all restaurants whose distance to the starting point
		// are greater than the radius
		ArrayList<Restaurant> CloseRestaurants = (ArrayList<Restaurant>)
				Restaurants.stream()
				.filter(restaurant -> 
				(restaurant.getDistance(latitude, longitude) <= radius))
				.collect(Collectors.toList());
				
		return CloseRestaurants;
		
	}
	
	/**
	 * Returns a list of the three best restaurants in YelpDB
	 * @return a List of the three best Restaurants in YelpDB
	 */
	public ArrayList<Restaurant> threeBest()
	{
		ArrayList<Restaurant> threeBestRestaurants = new ArrayList<Restaurant>();
		
		// find the best restaurant 3 times
		for(int i = 0; i < 3; i++) {

			Restaurant bestRestaurant = Restaurants.get(0);
			
			// find the first restaurant in the list that is not already
			// in the threeBestRestaurants
			int j = 0;
			while(threeBestRestaurants.contains(bestRestaurant)) {
				
				if(!threeBestRestaurants.contains(Restaurants.get(j))) {
					bestRestaurant = Restaurants.get(j);
				}
				j++;
			}
		
			// find the restaurant with the most stars that is not already in
			// the list of the threeBestRestaurants
			for(int k = 0; k < Restaurants.size(); k++) {
				if((Restaurants.get(j).getStars() > bestRestaurant.getStars())
						&& !threeBestRestaurants.contains(Restaurants.get(k))) {
					bestRestaurant = Restaurants.get(k);
				}
			
			}
			
			// add the best restaurant to the list
			threeBestRestaurants.add(bestRestaurant);
		}
		return threeBestRestaurants;
	}
	
	/**
	 * Given a restaurant, returns a list of restaurants from the
	 * database that have similar category tags
	 * @param restaurant
	 * 			restaurant whose tags are being compared to the tags
	 * 			of other restaurants
	 * @return RelatedRestaurants
	 * 			ArrayList of restaurants that have a similar category tag
	 * 			to the argument restaurant
	 */
	public ArrayList<Restaurant> relatedRestaurants(Restaurant restaurant)
	{	
		// filters out all the restaurants that do not have a similar category
		// with the argument restaurant
		ArrayList<Restaurant> RelatedRestaurants = (ArrayList<Restaurant>)
				Restaurants.stream()
				.filter(r -> similarCategories(r, restaurant))
				.collect(Collectors.toList());
		
		// remove the restaurant from the list of its related restaurants
		if(RelatedRestaurants.contains(restaurant)) {
			RelatedRestaurants.remove(restaurant);
		}

		return RelatedRestaurants;
	}
	
	/**
	 * Given two restaurants, returns true if they have a similar category tag that
	 * is not "Restaurants"
	 * @param r1
	 * 			first restaurant whose category tags are to be compared
	 * @param r2
	 * 			second restaurant whose category tags are to be compared
	 * @return boolean
	 * 			true if they have at least one tag that is not "Restaurants"
	 * 			false otherwise
	 */
	boolean similarCategories(Restaurant r1, Restaurant r2) {
		ArrayList<String> c1 = new ArrayList<String>(Arrays.asList(r1.getCategories()));
		ArrayList<String> c2 = new ArrayList<String>(Arrays.asList(r2.getCategories()));
		
		c1.retainAll(c2);
		
		// if the intersection of c1 and c2 is at least 2, the two restaurants
		// have a similar tag that is not "Restaurants"
		return c1.size() >= 2;
	}
	
	/**
	 * getMatches filters the current list of restaurants based on a query string
	 * example of query string:
	 * in(Telegraph Ave) && (category(Chinese) || category(Italian)) && price <= 2
	 * 
	 * the keywords using the "keyword(string)" layout can be the following: 
	 * - in: filters by neighborhood field
	 * - category: filters by category field
	 * - name: filters by name field
	 * - open: filters by 'true' or 'false' in open boolean
	 * - state: filters by state field 
	 * - city: filters by city field
	 * - schools: filters by schools field
	 * 
	 * the keywords using the "keyword INEQUALITY number" layout can be the following: 
	 * - rating: filters by stars field
	 * - review_count: filter by review_count field
	 * - price: filter by price field
	 * 
	 * @param queryString such that it specifies the filtering performed on the list of restaurants 
	 * and follows the layout outlined above
	 * 
	 * @return Set<Restaurant> that is queried for based on the queryString
	 */
	
	@Override
	public Set<Restaurant> getMatches(String queryString) {
		//trim the querystring in case of whitespace
		queryString.trim();
		
		//take the querystring into a stream to make a new lexer and parser from it and recieve the tokens from it
		CharStream stream = CharStreams.fromString(queryString);
		mp5AntlrLex lexer = new mp5AntlrLex(stream);
		TokenStream tokens = new CommonTokenStream(lexer);
		mp5AntlrParse parser = new mp5AntlrParse(tokens);
		
		//make a parse tree and walker method
		ParseTree tree = parser.root();
		ParseTreeWalker walker = new ParseTreeWalker();
		
		//make a new listener out of custom lister class
		mp5AntlrParseListener listener = new mp5AntlrListenerCollect(getRestaurants());
		
		//use walker to walk through the tree with the custom listener
		walker.walk(listener, tree);
		
		//retrieve the array list of filtered restaurants and create a new set from it
		ArrayList<Restaurant> filtered = ((mp5AntlrListenerCollect)listener).getFilteredList();
		Set<Restaurant> queriedResults = new HashSet<Restaurant>(filtered);
		
		//return final set
		return queriedResults;
	}
	
	@Override
	/**
	 * Clusters Restaurants in the Yelp database into k clusters based on
	 * location (longitude, latitude)
	 * @param k
	 * 			number of desired non-empty clusters
	 * @return jsonString
	 * 			string representation of an array of json objects, where
	 * 			each object represents a restaurant, detailing which
	 * 			cluster it belongs to
	 */
	public String kMeansClusters_json(int k) {
		
		// call kMeansClusters method to return the list of clusters
		List<Set<Restaurant>> clusters = kMeansClusters(k);
		
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		
		int c = 1;
		
		// for every cluster in the list of clusters, create the cluster's
		// restaurant objects and add it to the jsonArrayBuilder, keeping
		// track of which cluster the restaurant is a part of with c
		for(Set<Restaurant> cluster : clusters) {
			
			for(Restaurant r : cluster) {
				arrBuilder.add(Json.createObjectBuilder()
						.add("x", BigDecimal.valueOf(r.getLongitude()))
						.add("y", BigDecimal.valueOf(r.getLatitude()))
						.add("name", r.getName())
						.add("cluster", BigDecimal.valueOf(c))
						.add("weight", BigDecimal.valueOf(1.0)));	
			}
			
			c++;
		}
		
		// convert jsonArrayBuilder into a jsonArray
		JsonArray arr = arrBuilder.build();
		// get the string representation of the jsonArray
		String jsonString = arr.toString();
		
		//return the string
		return jsonString;
	}
	
	/**
	 * Clusters Restaurants in the Yelp database into k clusters based on
	 * location (longitude, latitude)
	 * @param k
	 * 			number of non-empty clusters
	 * @return clusters
	 * 			List of sets of restaurants, where each set represents a
	 * 			group of restaurants belonging to the same cluster
	 */
	public List<Set<Restaurant>> kMeansClusters(int k) {
		
		// creating a list of k random centroids
		ArrayList<Centroid> Centroids = new ArrayList<Centroid>();
		
		// creating a map detailing if a centroid has moved in the last loop
		HashMap<Centroid, Boolean> centroidMoved = new HashMap<Centroid, Boolean>();
		
		
		// place a centroid on a unique restaurant position k number of times
		for(int i = 0; i < k; i++) {
			Centroid c = new Centroid(Restaurants.get(i).getLatitude(),
					Restaurants.get(i).getLongitude());
			
			Centroids.add(c);
			
			centroidMoved.put(c, true);
		}		

		boolean moving;
		
		// create a hashmap of every restaurant's closest centroid
		HashMap<Restaurant, Centroid> RCPair = new HashMap<Restaurant, Centroid>();
		
		do {
			// clear the map upon every iteration of the loop
			RCPair.clear();
			
			// for each restaurant, find its closest centroid and add the pair
			// in the hashmap
			for(Restaurant r : Restaurants) {
				// initialize the closest centroid as the first centroid in the list
				Centroid closestC = Centroids.get(0);
				
				// compare the distance of each centroid with each other centroid
				// to determine the closest centroid to the restaurant
				for(Centroid c : Centroids) {
					if(r.getDistance(closestC.getY(), closestC.getX()) 
							> r.getDistance(c.getY(), c.getX())) {
						closestC = c;
					}
				}
				
				RCPair.put(r, closestC);
				
			}
			
			// find the average position between all restaurants of each centroid,
			// and reposition the centroid to that position
			for(Centroid c : Centroids) {
				double totalY = 0, totalX = 0;
				int closeRestaurants = 0;
				
				for(Restaurant r : RCPair.keySet()) {
					if(RCPair.get(r) == c) {
						totalY += r.getLatitude();
						totalX += r.getLongitude();
						closeRestaurants++;
					}
				}
				
				if(closeRestaurants != 0) {
					double averageY = totalY / closeRestaurants;
					double averageX = totalX / closeRestaurants;
					
					// if the average position computed is equal to the centroid's
					// original position, state that the centroid did not move
					if((Math.abs(averageY - c.getY()) <= 0.001) && 
							(Math.abs(averageX - c.getX()) <= 0.001)) {
						centroidMoved.put(c, false);
					}
					// else, reposition the centroid and state that it did move
					else {
						centroidMoved.put(c, true);
						c.setY(averageY); c.setX(averageX);
					}
					

				}
				else {
					centroidMoved.put(c, false);
				}
				
			}
			
			moving = false;
			
			// moving is only true if at least one centroid moved in this iteration
			for(Centroid c : centroidMoved.keySet()) {
				if(centroidMoved.get(c)) {
					moving = true;
					break;
				}
			}
			
			// repeat the process while centroids are still moving
		} while (moving);
		
		List<Set<Restaurant>> clusters = new ArrayList<Set<Restaurant>>();
		
		// for each centroid, add its neighbors to a set, and add that set
		// to the list of clusters
		for(Centroid c : Centroids) {
			
			Set<Restaurant> cluster = new HashSet<Restaurant>();
			
			for(Restaurant r : RCPair.keySet()) {
				if(RCPair.get(r) == c) {
					cluster.add(r);
				}
			}
			
			clusters.add(cluster);
		}
		
		return clusters;	
	}
	
	
	
	
	@Override
	/**
	 * create a function that predicts a user's rating of a restaurant based
	 * on the restaurant's price
	 * @param user
	 * 			user ID of the user whose predictor function is to be returned
	 * @return predictRating
	 * 			function that takes a restaurant and a database it is located in,
	 * 			and returns the predicted rating of the user for whom this function
	 * 			was constructed for
	 */
	public ToDoubleBiFunction<MP5Db<Restaurant>, String> getPredictorFunction(String user) {

		// find the YelpUser in the database associated with the user ID
		YelpUser yelpuser = YelpUsers.stream()
				.filter(yelper -> yelper.getUser_id().equals(user))
				.collect(Collectors.toList())
				.get(0);
		
		// get the list of reviews submitted by the yelp user
		ArrayList<Review> userReviews = usersReviews(yelpuser);
		
		double meanx = 0, meany = yelpuser.getAverage_stars();
		int count = userReviews.size();
		
		double Sxx = 0, Syy = 0, Sxy = 0;
		
		// find the average price of the restaurants reviewed by the user
		for(Review rev : userReviews) {
			Restaurant revdRestaurant = Restaurants.stream()
					.filter(rest -> rest.getBusiness_id().equals(rev.getBusiness_id()))
					.collect(Collectors.toList())
					.get(0);
			
			meanx += (double) revdRestaurant.getPrice();
		}
		
		meanx /= count;
		
		// calculate the sum of squares to be used for calculating the
		// regression coefficients
		for(Review rev : userReviews) {
			Restaurant revdRestaurant = Restaurants.stream()
					.filter(rest -> rest.getBusiness_id().equals(rev.getBusiness_id()))
					.collect(Collectors.toList())
					.get(0);
			
			double differencex = revdRestaurant.getPrice() - meanx;
			double differencey = ((double) rev.getStars()) - meany;
			
			Sxx += Math.pow(differencex, 2.0);
			Syy += Math.pow(differencey, 2.0);
			Sxy += differencex * differencey;
		}
		
		// calculate the regression coefficients with the sum of squares
		double b = Sxy / Sxx;   double a = meany - (b * meanx);
		double r_squared = (Math.pow(Sxy, 2.0)) / (Sxx * Syy);
		
		// create a predictor function with the regression coefficients calculated
		ToDoubleBiFunction<MP5Db<Restaurant>, String> predictRating = 
				new PredictorFunction(a, b, r_squared);
		
		//return the predictor function
		return predictRating;
	}
}
