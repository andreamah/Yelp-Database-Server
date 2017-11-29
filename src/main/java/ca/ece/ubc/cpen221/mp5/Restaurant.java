package ca.ece.ubc.cpen221.mp5;

public class Restaurant extends Business{
	
	private double stars; //represents the rating of the Restaurant's overall quality
	private int review_count; // the number of Yelp reviews this Restaurant has been written
	private String photo_url; // link leading to an image of the Restaurant
	private String[] schools; //collection of schools that this restaurant is local to
	private int price;
	
	public Restaurant(boolean open, String url, double lattitude, double longitude, String[] neighborhoods,
			String business_id, String name, String[] categories, String state, String city,
			String full_address, double stars, int review_count,String photo_url,String[] schools ) {
		super(open, url, lattitude, longitude, neighborhoods, business_id, name, categories, state, city, full_address);
		this.stars = stars;
		this.review_count = review_count;
		this.photo_url =photo_url;
		this.schools = schools;
	}
	/**
	 * @return review count for the restaurant
	 */
	public int getReview_count() {
		return review_count;
	}

	/**
	 * increments review_count by 1
	 * @param review_count
	 */
	public void plusOneReview() {
		this.review_count = review_count +1;
	}
	
	
}
