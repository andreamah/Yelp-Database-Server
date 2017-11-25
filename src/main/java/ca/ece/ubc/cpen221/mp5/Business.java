package ca.ece.ubc.cpen221.mp5;

/**
 * Rep Invariant:
 * - url of one Business cannot be the same as the url of any other Business
 * - intersection of latitude and longitude must be located in the city
 * - city must be located in the state of the Business
 * - business_id of one Business cannot be the same as the business_id of any other Business
 * 
 *
 */
public class Business {
	private boolean open;
	private String url;
	private double latitude;
	private double longitude;
	private String[] neighborhoods;
	private String business_id;
	private String name;
	private String[] categories;
	private String state;
	private String type;
	private String city;
	private String full_address;
	
	public Business(boolean open, String url, double latitude, double longitude, 
			String[] neighborhoods,String business_id,String name, String[] categories,
			String state, String type, String city, String full_address)
	{
		this.open=open;
		this.url=url;
		this.latitude=latitude;
		this.longitude=longitude;
		this.neighborhoods=neighborhoods;
		this.business_id=business_id;
		this.name=name;
		this.categories=categories;
		this.state=state;
		this.type=type;
		this.city=city;
		this.full_address=full_address;
	}
	
	/**
	 * 
	 * @return whether open is true or false
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * changes the open field of the business to status
	 * @param open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
}
