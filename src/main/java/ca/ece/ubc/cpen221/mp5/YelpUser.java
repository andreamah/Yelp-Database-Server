package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
/**
 * Rep Invariant:
 * - review_count cannot be negative
 * - photo_url of one Restaurant cannot be the same as the photo_url of another Restaurant
 * - stars cannot be negative
 *
 */
public class YelpUser extends User {
	private HashMap<String, Integer> votes; //represents the distribution of the user's votes earned in their reviews over each type 
	private int review_count; //the number of Yelp reviews this YelpUser has written
	private double average_stars; //average stars this YelpUser has given in their reviews
	
	public YelpUser(String url, String user_id, String name, HashMap<String, Integer> votes,
			int review_count, double average_stars) 
	{
		super(url, user_id, name);
		this.votes = votes;
		this.review_count = review_count;
		this.average_stars = average_stars;
	}

	public double getAverage_stars() {
		return average_stars;
	}

	public void setVotes(HashMap<String, Integer> votes) {
		this.votes = votes;
	}

	public int getReview_count() {
		return review_count;
	}
	
	public void increaseReview_count() {
		review_count++;
	}
	
	public void updateAverage_stars(double reviewStars) {
		this.average_stars = ((average_stars * (review_count - 1)) + reviewStars) / review_count;
	}
	
	public HashMap<String, Integer> getVotes() {
		return new HashMap<String, Integer>(votes);
	}

}
