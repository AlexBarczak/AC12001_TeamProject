package mastodontProject;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * class to store the basic details of a user and hold a list of all the posts they've made
 * 
 * @author Aleksander Barczak
 */
public class User implements Serializable{

	private String username;
	private char[] password;
	private LinkedList<Post> userPosts;
	private String workplace;
	private String hometown;
	
	/**
	 * initialising function for a new user setting default values for its fields
	 * 
	 * @param username username of the user
	 * @param password password of the user
	 */
	public User(String username, char[] password) {
		this.username = username;
		this.password = password;
		this.workplace = "empty";
		this.hometown = "empty";
		userPosts = new LinkedList<Post>();
	}
	
	/**
	 * function to get the workplace of the user
	 * @return the user's workplace
	 */
	public String getWorkplace() {
		return workplace;
	}
	
	/**
	 * function to set the user's workplace
	 * @param workplace the user's new workplace
	 */
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	
	/**
	 * function to get the hometown of the user
	 * @return the user's hometown
	 */
	public String getHometown() {
		return hometown;
	}
	
	/**
	 * fucntion to set the user's hometown
	 * @param hometown the user's new hometown
	 */
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	/**
	 * function to get the user's username
	 * @return the user's username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * function to get the user's posts
	 * @return the user's posts
	 */
	public LinkedList<Post> getUserPosts() {
		return this.userPosts;
	}
	
	/**
	 * function for a user to add a post to their post's
	 * @param post post to be added
	 */
	public void addPost(Post post) {
		userPosts.add(post);
	}
	
	/**
	 * function to get the user's password
	 * @return the user's password
	 */
	public char[] getPassword() {
		return password;
	}
	
}
