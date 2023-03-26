/**
 * 
 */

/**
 * @author 4mart
 *
 */
public class Post {
	
	//Post data variables.
	private Integer posterID;
	private Integer postID;
	private String title;
	private String content;
	private Integer likes;
	private Post next;
	
	/**
	 * Post constructor.
	 * @param posterID The ID of the user making the post.
	 * @param postID The ID of the post itself.
	 * @param title The title of the post.
	 * @param content The post's content.
	 * @param likes The number of likes the post has.
	 */
	public Post(Integer posterID, Integer postID, String title, String content, Integer likes) {
		this.posterID = posterID;
		this.postID = postID;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.next = null;
	}
	
	/**
	 * Poster ID accessor.
	 * @return Poster's ID.
	 */
	public Integer getPosterID() {
		return posterID;
	}
	/**
	 * Poster ID mutator.
	 * @param id New poster's ID.
	 */
	public void setPosterID(Integer id) {
		this.posterID = id;
	}
	
	/**
	 * Post ID accessor.
	 * @return Post's ID.
	 */
	public Integer getPostID() {
		return postID;
	}
	/**
	 * Post ID mutator.
	 * @param id New post ID.
	 */
	public void setPostID(Integer id) {
		this.postID = id;
	}
	
	/**
	 * Title accessor.
	 * @return Title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Title mutator.
	 * @param t New title.
	 */
	public void setTitle(String t) {
		this.title = t;
	}
	
	/**
	 * Content accessor.
	 * @return Content.
	 */
	public String getContent() {
		return content;
	}
	/**
	 * Content mutator.
	 * @param c New content.
	 */
	public void setContent(String c) {
		this.content = c;
	}
	
	/**
	 * Like counter accessor.
	 * @return Number of likes.
	 */
	public Integer getLikes() {
		return likes;
	}
	/**
	 * Like counter mutator. 
	 * @param l New number of likes.
	 */
	public void setLikes(Integer l) {
		this.likes = l;
	}
	
	/**
	 * Next accessor. 
	 * @return The next post in the list.
	 */
	public Post getNext() {
		return next;
	}
	/**
	 * Next mutator.
	 * @param p The new next post in the list.
	 */
	public void setNext(Post p) {
		this.next = p;
	}
	
	/**
	 * Displays the post's content.
	 */
	public void displayPost() {
		
		System.out.println("");
		System.out.println("----- " + "[" + postID + "] " + title + " -----");
		System.out.println(content);
		System.out.println(likes + " <3");
	}
}
