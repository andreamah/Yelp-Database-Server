package ca.ece.ubc.cpen221.mp5;

/**
 * Rep Invariant:
 * - url of one User cannot be the same as the url of another User
 * - user_id of one User cannot be the same as the user_id of any other User
 *
 */
public class User {
	private String url; //the the link which points to the user's personal page on the web service
	private String type; //represents the type of datatype in the database
	private String user_id; //string that represents each user's user ID
	private String name; //string that represents the name associated with the owner of theuser` account

	public User(String url, String type, String user_id, String name) {
		this.url = url;
		this.type = type;
		this.user_id = user_id;
		this.name = name;
	}

}
