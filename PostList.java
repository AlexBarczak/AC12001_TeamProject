/**
 * Class to hold a list of all the posts.
 * Idk if we want to change it to be each user gets their own list or if we just have them all together.
 *
 */
public class PostList {

	private Post head;
	
	/**
	 * List constructor
	 */
	public PostList() {
		head = null;
	}
	
	/**
	 * Accessor for the head of the list.
	 * @return head. 
	 */
	public Post getHead() {
		return head;
	}
	/**
	 * Mutator for the head of the list.
	 * @param p What the head will be changed to.
	 */
	public void setHead(Post p) {
		head = p;
	}
	
	/**
	 * Adds a new post to the list.
	 * @param poster The id of the user making the post.
	 * @param post The id of the post itself. 
	 * @param t The title of the post.
	 * @param c The post's content. 
	 */
	public void addPost(Integer poster, Integer post, String t, String c) {
		
		//Creates the post.
		Post p = new Post(poster, post, t, c, 0);
		//Sets the new post to be the head and moves the head to the next list item along. 
		p.setNext(head);
		head = p;
		
		//Displays the new post.
		p.displayPost();
	}
	
	/**
	 * Displays all posts in the list. 
	 */
	public void displayAllPosts() {
		
		//Starts at the head.
		Post marker = head;
		
		//Let the user know if the list is empty.
		if (marker == null) {
			System.out.println("There are no posts to display here yet.");
		}
		
		//While the list is not finished, display the current post and move onto the next one.
		while (marker != null) {
			marker.displayPost();
			marker = marker.getNext();
		}
	}
	
	/**
	 * Display all posts by a specific user.
	 * @param searchID The ID of the user who's posts need displaying.
	 */
	public void postsByUser(Integer searchID) {
		
		//Start at the head and with the fact we have not found what we are looking for. 
		Post marker = head;
		boolean found = false;
		
		//Runs through the entire list looking for all the posts by a specific user.
		while (marker != null) {
			//If the current post is one we're looking for, display it.
			if (marker.getPosterID() == searchID) {
				found = true;
				marker.displayPost();
			}
			//Move onto the next post.
			marker = marker.getNext();
		}
		
		//If we do not find what we're looking for, display an appropriate message to the user. 
		if (found == false) {
			System.out.println("Sorry, this user ID seems to have no associated posts :/");
		}
	}
	
	/**
	 * Finds and displays a specific post.
	 * Displays an appropriate message if the post is not found. 
	 * @param searchID The ID of the post we are looking for.
	 * @return The post if we found it, null if we did not. 
	 */
	public Post find(Integer searchID) {
		
		//Start at the head and with the fact we have not found what we are looking for.
		Post marker = head;
		Post found = null;
		
		//Checks the current post is not null and we have not already found what we are looking for.
		while ((marker != null) && (found == null)) {
			if (marker.getPostID() == searchID) {
				found = marker;
			} else {
				marker = marker.getNext();
			}
		}
		//If we found the correct post, display it. Otherwise display an appropriate message to the user.
		if (found != null) {
			found.displayPost();
		} else {
			System.out.println("Sorry, there is no post here with that ID");
		}
		//Return either the post we were looking for, or null if it did not exist within the list.
		return found;
	}
	
	/**
	 * Finds the post that comes before a specified post. 
	 * Mostly for use with the deletion method.
	 * @param f The post that comes after the one we are looking for.
	 * @return The previous post to the one specified.
	 */
	public Post findPrevious(Post f) {
		
		//Start at the head and with the fact we have not found what we are looking for.
		Post previous = null;
		Post marker = head;
		
		//Checks that neither the current post, nor the next one are null and that we have not found the one we are looking for.
		while ((marker != null) && (marker.getNext() != null) && (previous == null)) {
			//If the post after the current one is the specified post, the current post is the one we are looking for.
			if (marker.getNext().equals(f)) {
				previous = marker;
			} else {
				//Otherwise move onto the next post.
				marker = marker.getNext();
			}
		}
		//Return the post we were looking for if we found it, if not return null.
		return previous;
	}
	
	/**
	 * Deletes a specified post from the list.
	 * @param searchID The ID of the post we want to delete.
	 */
	public void deletePost(Integer searchID) {
		
		//Lets the user know exactly which post we are removing.
		System.out.println("");
		System.out.println("Post to be deleted: ");
		Post postToDelete = find(searchID);
		
		//As long as we found the post the user wanted to delete.
		if (postToDelete != null) {
			//Find the post that comes before the one we want to delete.
			Post previous = findPrevious(postToDelete);
			//If there is no post before the one we want to delete, set the head to the one after.
			if (previous == null) {
				head = postToDelete.getNext();
				//Otherwise set the previous post's Next to the one after the one we want to delete.
			} else {
				previous.setNext(postToDelete.getNext());
			}
		}
	}
	
	/**
	 * Deletes all posts.
	 */
	public void deleteAllPosts() {
		//Make head null so the list is now empty.
		head = null;
	}
	
	/**
	 * Increase the Like counter of a specific post by 1.
	 * @param searchID The ID of the post we want to Like.
	 */
	public void likePost(Integer searchID) {
	
		//Gets the post we want to Like.
		Post p = find(searchID);
		
		//As long as this post exists, increase the number of likes by one then display the post.
		if (p != null) {
			p.setLikes(p.getLikes() + 1);
			p.displayPost();
		}
	}
}
