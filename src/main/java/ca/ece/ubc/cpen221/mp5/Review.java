package ca.ece.ubc.cpen221.mp5;

import java.util.HashMap;
/**
 * Rep Invariant:
 * - review_id of one Review cannot be the same as the review_id of another Review
 * - stars cannot be negative
 * - date must be in the format YYYY-MM-DD where 0 <= YYYY <= 2017 , 1 <= MM <= 12 ,
 * 		- if MM == 01 || 03 || 05 || 07 || 08 || 10 || 12 1 <= DD <= 31
 *  	- if MM == 04 || 06 || 09 || 11 1 <= DD <= 30
 * 		- if (MM == 02) && (YYYY % 4 == 0) 1 <= DD <= 29
 * 		- if (MM == 02) && (YYYY % 4 != 0) 1 <= DD <= 28
 *
 */
public class Review {
	private String business_id; //string that represents the Restaurant the Review is associated to
	private HashMap<String, Integer> votes; //represents the distribution of the user's votes on the review over each type of vote (funny, useful, cool)
	private String review_id; //string that represents each review's review ID
	private String text; //string representing the comments by a YelpUser about the Restaurant
	private double stars; //represents the rating out of 5 given to the Restaurant in the Review
	private String user_id; //string that represents the YelpUser that wrote the Review
	private HashMap<String, Integer> date; //string that represents when the Review was written
	
	public Review(String business_id, HashMap<String, Integer> votes, String review_id, String text,
			double stars, String user_id, HashMap<String, Integer> date) {
		this.business_id = business_id;
		this.votes = votes;
		this.review_id = review_id;
		this.text = text;
		this.stars = stars;
		this.user_id = user_id;
		this.date = date;
	}
	
	/**
	 * 
	 * @return votes for the review
	 */
	public HashMap<String, Integer> getVotes() {
		return votes;
	}
	
	public String getText() {
		return text;
	}

	/**
	 * increments the number of votes of a particular type of vote in votes by 1
	 * @param votes
	 */
	public void UpdateVotes(HashMap<String, Integer> votes) {
		this.votes = votes;
	}

	public String getBusiness_id() {
		return business_id;
	}

	public double getStars() {
		return stars;
	}

	public String getUser_id() {
		return user_id;
	}
	
	
}
