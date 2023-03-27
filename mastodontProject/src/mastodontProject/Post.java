package mastodontProject;

import java.io.Serializable;

public class Post implements Serializable {

	private Integer posterID;
	private String title;
	private String content;
	
	public Post(Integer posterID, String title, String content) {
		this.posterID = posterID;
		this.title = title;
		this.content = content;
	}
	
}
