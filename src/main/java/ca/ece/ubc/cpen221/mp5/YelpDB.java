package ca.ece.ubc.cpen221.mp5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

public class YelpDB implements MP5Db{
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
	
	/**
	 * Given a YelpUser, return a list of all Reviews submitted by that YelpUser
	 * @param reviewer
	 * @return
	 */
	ArrayList<Review> usersReviews(YelpUser reviewer)
	{
		return Reviews;
	}
	


	/**
	 * given a restaurant, return a List containing its best 
	 * Review in the first entry of the List and its worst Review in the second entry
	 * @param restaurant
	 * @return
	 */
	ArrayList<Review> bestAndWorst(Restaurant restaurant)
	{
		return Reviews;
		
	}
	
	/**
	 * given a latitude and longitude, returns a list that contains all restaurants 
	 * contained in that radius in order of distance to the center
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	ArrayList<Restaurant> closeRestaurants(double latitude, double longitude, double radius)
	{
		return Restaurants;
		
	}
	
	/**
	 * 
	 * @return a List of the three best Restaurants in YelpDB
	 */
	ArrayList<Restaurant> threeBest()
	{
		return Restaurants;
	}
	
	/**
	 * a List of the three best Restaurants in YelpDB
	 * @param restaurant
	 * @return
	 */
	ArrayList<Restaurant> relatedRestaurants(Restaurant restaurant)
	{
		return Restaurants;
	}
	
	@Override
	public Set getMatches(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String kMeansClusters_json(int k) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ToDoubleBiFunction getPredictorFunction(String user) {
		// TODO Auto-generated method stub
		return null;
	}
}
