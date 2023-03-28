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

public class Mastodont extends JFrame{
	
	public HeaderDisplay header;
	public SidebarDisplay sidebar;
	public MainDisplay main;
	
	private Graph g;
	private User currentUser;
	
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
		header = new HeaderDisplay();
		add(header, gbc);
		
		//side bar
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
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
	
	public Graph getGraph() {
		return g;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void logIn(User user) {
		this.currentUser = user;
		header.displayUserAppearance(user);
		main.displayCurrentUserPage();
		sidebar.displayFollowed(user);
	}
	
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
 	
 	public void validateAndRepaint() {
 		validate();
 		repaint();
 	}

 	
 	public void registerUser(User user) {
 		g.addVertex(user);
 	}
 	
 	public void displaySearchFunction() {
 		main.displaySearchPage();
 	}
 	
	public static void main(String[] args) {
		// set up a GUI
		Mastodont mastodont = new Mastodont();

		mastodont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mastodont.setSize(800,600);
		mastodont.setVisible(true);
	}
}
