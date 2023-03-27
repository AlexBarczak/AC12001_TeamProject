package mastodontProject;
import java.io.Serializable;

public class User implements Serializable{

	private String username;
	private char[] password;
	private Post[] userPosts;
	private String workplace;
	private String hometown;
	
	public User(String username, char[] password) {
		this.username = username;
		this.password = password;
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
	
	public Post[] getUserPosts() {
		return this.userPosts;
	}
	
	public char[] getPassword() {
		return password;
	}
	
}
