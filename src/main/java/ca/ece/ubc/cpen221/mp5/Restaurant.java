package ca.ece.ubc.cpen221.mp5;

public class Restaurant extends Business{

	private double stars; //represents the rating of the Restaurant's overall quality
	private int review_count; // the number of Yelp reviews this Restaurant has been written
	private String photo_url; // link leading to an image of the Restaurant
	private String[] schools; //collection of schools that this restaurant is local to
	private int price;
	
	public Restaurant(boolean open, String url, double latitude, double longitude, String[] neighborhoods,
			String business_id, String name, String[] categories, String state, String city,
			String full_address, double stars, int review_count,String photo_url,String[] schools, int price ) {
		super(open, url, latitude, longitude, neighborhoods, business_id, name, categories, state, city, full_address);
		this.stars = stars;
		this.review_count = review_count;
		this.setPhoto_url(photo_url);
		this.setSchools(schools);
		this.price = price;
	}
	/**
	 * @return review count for the restaurant
	 */
	public int getReview_count() {
		return review_count;
	}
	
	public double getStars() {
		return stars;
	}
	public int getPrice() {
		return price;
	}
	
	public void increaseReview_count() {
		review_count++;
	}
	
	/**
	 * Returns the distance between this restaurant and the point (x,y)
	 * @param y coordinate of point to measure distance from
	 * @param x coordinate of point to measure distance from
	 * @return distance between this restaurant and (x,y)
	 */
	public double getDistance(double y, double x) {
		return Math.sqrt(Math.pow(y - latitude, 2.0) + Math.pow(x - longitude, 2.0));
	}
	public String[] getSchools() {
		return schools;
	}
	public void setSchools(String[] schools) {
		this.schools = schools;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	
}
