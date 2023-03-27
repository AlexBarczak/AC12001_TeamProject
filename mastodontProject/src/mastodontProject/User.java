package mastodontProject;
import java.io.Serializable;

public class User implements Serializable{

	private String username;
	private char[] password;
	private Post[] userPosts;
	
	public User(String username, char[] password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Post[] getUserPosts() {
		return this.userPosts;
	}
	
	public char[] getPassword() {
		return password;
	}
	
}
