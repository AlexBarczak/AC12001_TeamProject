import java.util.Scanner;

/**
 * Basically just a class to demonstrate the functionality.
 * Also presents a potential menu for post-related tasks for the user. 
 */
public class Main {

	PostList L = new PostList();
	
	/**
	 * Initialises the main class and calls the menu method.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Main m = new Main();
		
		m.menu();
	}

	/**
	 * Displays the menu. 
	 */
	public static void displayMenu() {
	
		System.out.println("");
		System.out.println("----- Menu -----");
		System.out.println("[1] Create a post");
		System.out.println("[2] Display all posts");
		System.out.println("[3] Display a specific post");
		System.out.println("[4] Display all of a specific user's posts");
		System.out.println("[5] Delete a specific post");
		System.out.println("[6] Delete all posts");
		System.out.println("[7] Like a post");
		System.out.println("[0] Quit");
		System.out.println("----------------");
		System.out.println("");
		
	}
	
	/**
	 * Calls the method to display the menu, validates and performs the user's input choice.
	 */
	public void menu() {
		
		boolean quit = false;
		Scanner s = new Scanner(System.in);
		
		do {
			displayMenu();
			
			String choice = s.nextLine();
			switch(choice)
			{
			case "1":
				newPost();
				break;
			case "2":
				L.displayAllPosts();
				break;
			case "3":
				displayPost();
				break;
			case "4":
				postsByUser();
				break;
			case "5":
				deletePost();
				break;
			case "6":
				L.deleteAllPosts();
				break;
			case "7":
				likePost();
				break;
			case "0":
				System.out.println("Goodbye");
				quit = true;
				break;
			default: 
				System.out.println("Invalid input. Please try again:");
				break;
			}
		} while (!quit);
		
	}
	
	/**
	 * Creates new post from user input.
	 */
	public void newPost() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("User ID:");
		Integer u = s.nextInt();
		
		System.out.println("Post ID:");
		Integer p = s.nextInt();
		
		System.out.println("Title:");
		String t = s.nextLine();
		
		System.out.println("Content:");
		String c = s.nextLine();
		
		//Adds the post to the list.
		L.addPost(u, p, t, c);
	}
	
	/**
	 * Displays a specific post based on user input.
	 */
	public void displayPost() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Post ID:");
		Integer p = s.nextInt();
		
		//Finds the post in the list and displays it.
		//Displays appropriate message if the post isn't found.
		L.find(p);
	}
	
	/**
	 * Deletes a specific post based on user input.
	 */
	public void deletePost() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Post ID:");
		Integer p = s.nextInt();
		
		//Finds the post in the list and displays it before deleting it from the list.
		//Displays appropriate message if the post isn't found.
		L.deletePost(p);
	}
	
	/**
	 * Increases the Like counter for a specific post by 1 based on user input.
	 */
	public void likePost() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("Post ID:");
		Integer p = s.nextInt();
		
		//Finds the post in the list and displays it after increasing its Like counter by 1.
		//Displays appropriate message if the post isn't found.
		L.likePost(p);
	}
	
	/**
	 * Displays all posts by a specific user based on user input.
	 */
	public void postsByUser() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("User ID:");
		Integer p = s.nextInt();
		
		//Displays all posts with the given posterID.
		//Displays appropriate message if there are no qualifying posts in the list.
		// * Could be used to display all of a user's friends' posts, simulating a feed *
		L.postsByUser(p);
	}
}
