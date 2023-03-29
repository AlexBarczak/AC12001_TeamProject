package mastodontProject;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class to start off the application and deal with the majority of the gui implementation
 * 
 * @author Aleksander Barczak
 * @author Martyn Bett
 * 
 */

public class Mastodont extends JFrame{
	
	// the main three segments of the display each with their own class files
	// responsible for displaying everything to the user
	public HeaderDisplay header;
	public SidebarDisplay sidebar;
	public MainDisplay main;
	
	//graph to keep track of each user and their follows
	//along with the currently logged in user for the program to keep track of it
	private Graph g;
	private User currentUser;
	
	/**
	 * initialising function for the main frame for the GUI, 
	 * sets up the layout and positioning of each of the three main panels in the gui
	 * those being the header side bar and main panel
	 * 
	 * also handles the saving of all the data to make sure everything is retained between sessions
	 */
	public Mastodont(){
		setTitle("Mastodon't");
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//header
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipady = 64;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		header = new HeaderDisplay(this);
		add(header, gbc);
		
		//side bar
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.2;
		gbc.weighty = 0;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 1;
    
		sidebar = new SidebarDisplay(this);
		add(sidebar, gbc);
		
		//main area
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 4;
		gbc.gridwidth = 4;
		main = new MainDisplay(this);
		
		add(main, gbc);
		
		currentUser = null;
		
		g = new Graph();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	           g.saveGraph();
	        }
	    }, "Shutdown-thread"));
	}
	
	/**
	 * returns the graph holding all the data on users and their connections
	 * 
	 * @return graph containing all user data
	 */
	public Graph getGraph() {
		return g;
	}
	
	/**
	 * returns the currently logged in user
	 * 
	 * @return user who is currently logged in
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * signs in the user passed in as a parameter and makes the three main panels change their appearance
	 * to display the relevant information
	 * 
	 * @param user to log in
	 */
	public void logIn(User user) {
		this.currentUser = user;
		header.displayUserAppearance(user);
		main.displayCurrentUserPage();
		sidebar.displayFollowed(user);
	}
	
	/**
	 * searched for a user in the graph data based on the user name, which is ensured to be unique
	 * 
	 * @param username being searched for 
	 * @return the corresponding user in the graph data
	 */
 	public User searchForUser(String username) {
 		
 		HashSet<User> userList = g.getUsers();
 		Iterator<User> it = userList.iterator(); 
 		
 		while(it.hasNext()) {
 			User user = it.next();
 			if(user.getUsername().equals(username)) {
 				return user;
 			}
		}
		return null;
	}
 	
 	/**
 	 * shorthand for the two functions validate and repaint,
 	 * used mostly when we wanted the whole frame to refresh instead of just the local 
 	 * panel being altered
 	 */
 	public void validateAndRepaint() {
 		validate();
 		repaint();
 	}

 	/**
 	 * registers a new user in the graph data
 	 * 
 	 * @param user to be registered
 	 */
 	public void registerUser(User user) {
 		g.addVertex(user);
 	}
 	
 	/**
 	 * intermediate function from side bar to make the main panel switch to
 	 * the search area
 	 * 
 	 * became deprecated when we switched to have the panels be public fields of Mastodont
 	 * but is still in use in a few places
 	 */
 	public void displaySearchFunction() {
 		main.displaySearchPage();
 	}
 	
 	/**
 	 * starting point for the program
 	 * instantiates Mastodont and creates the default behaviours of the frame 
 	 * along with the default size of the applet
 	 * 
 	 * @param args unused
 	 */
	public static void main(String[] args) {
		// set up a GUI
		Mastodont mastodont = new Mastodont();

		mastodont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mastodont.setSize(800,600);
		mastodont.setVisible(true);
	}
}
