package mastodontProject;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class Mastodont extends JFrame{
	
	HeaderDisplay header;
	SidebarDisplay sidebar;
	MainDisplay main;
	
	LinkedList<User> userList;
	//insert graph here to map connections between users
	
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
		sidebar = new SidebarDisplay();
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
		
		loadUsersAndPostsFromFile();
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	           saveUsersAndPostsToFile();
	        }
	    }, "Shutdown-thread"));
	}
	
	public void logIn(User user) {
		this.currentUser = user;
		header.displayUserAppearance(user);
		main.displayCurrentUserPage(user);
		sidebar.displayFollowed(user);
	}
	
 	public User searchForUser(String username) {
 		Iterator<User> it = userList.iterator(); 
 		
 		while(it.hasNext()) {
 			User user = it.next();
 			if(user.GetUsername().equals(username)) {
 				return user;
 			}
		}
		return null;
	}

 	// use this method to not only add the user to the user list but also add it to the graph keeping track of connections
 	public void registerUser(User user) {
 		userList.add(user);
 		//add the method for adding the user to the graph here
 	}
 	
	public static void main(String[] args) {
		// set up a GUI
		Mastodont mastodont = new Mastodont();

		mastodont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mastodont.setSize(800,600);
		mastodont.setVisible(true);
	}
	
	public void saveUsersAndPostsToFile() {
		System.out.println(userList);
		try {
			FileOutputStream fileOutputStream
		      = new FileOutputStream("users.txt");
		    ObjectOutputStream objectOutputStream 
		      = new ObjectOutputStream(fileOutputStream);
		    objectOutputStream.writeObject(userList);
		    objectOutputStream.flush();
		    objectOutputStream.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadUsersAndPostsFromFile() {
		try {
			FileInputStream fileInputStream
		      = new FileInputStream("users.txt");
		    ObjectInputStream objectInputStream
		      = new ObjectInputStream(fileInputStream);
		    userList = (LinkedList<User>) objectInputStream.readObject();
		    objectInputStream.close(); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (userList == null) {
			userList = new LinkedList<User>();
		}
	}
	
}
