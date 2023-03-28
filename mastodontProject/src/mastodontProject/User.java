package mastodontProject;
import java.io.Serializable;
import java.util.LinkedList;

public class User implements Serializable{

	private String username;
	private char[] password;
	private LinkedList<Post> userPosts;
	private String workplace;
	private String hometown;
	
	public User(String username, char[] password) {
		this.username = username;
		this.password = password;
		this.workplace = "empty";
		this.hometown = "empty";
		userPosts = new LinkedList<Post>();
	}
	

	public String getWorkplace() {
		return workplace;
	}
	
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	
	public String getHometown() {
		return hometown;
	}
	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getUsername() {
		return this.username;
	}
	
	public LinkedList<Post> getUserPosts() {
		return this.userPosts;
	}
	
	public void addPost(Post post) {
		userPosts.add(post);
	}
	
	public char[] getPassword() {
		return password;
	}
	
}
