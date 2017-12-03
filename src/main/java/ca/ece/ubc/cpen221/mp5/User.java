package ca.ece.ubc.cpen221.mp5;

/**
 * Rep Invariant:
 * - url of one User cannot be the same as the url of another User
 * - user_id of one User cannot be the same as the user_id of any other User
 *
 */
public class User {
	protected String url; //the the link which points to the user's personal page on the web service
	protected String user_id; //string that represents each user's user ID
	protected String name; //string that represents the name associated with the owner of theuser` account

	public User(String url, String user_id, String name) {
		this.url = url;
		this.user_id = user_id;
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getUser_id() {
		return user_id;
	}

}
