package mastodontProject;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

public class MainDisplay extends JPanel{
	
	public Mastodont program;
	
	//main display area will have the following functions:
	
	//1. display the guest page,
	//2. display the log in page,
	//3. display the sign up page,
	//4. display the posts of the user selected from followed users,
	//5. display a search area to find a user
	//6. display a page to add a new post to your account
	
	public MainDisplay(Mastodont program) {
		setLayout(new GridBagLayout());
		
		this.program = program;
		
		displayGuestPage();		
	}

	public MainDisplay() {
		setLayout(new GridBagLayout());
	}
	public void displayGuestPage() {
		removeAll();
		
		setLayout(new GridBagLayout());
		
		//ask the user to choose whether to log in or sign up
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		JButton login = new JButton("log in");
		add(login);
		
		login.addActionListener(e -> {
			displayLoginPage();
		});
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		JButton signUp = new JButton("sign up");
		add(signUp);
		
		signUp.addActionListener(e -> {
			displaySignUpPage();
		});
		
		validate();
		repaint();
	}
	
	public void displayLoginPage() {
		removeAll();
		
		setLayout(new GridBagLayout());
		
		//need the user name, user password and buttons for logging in or changing choice to signing up
		JTextField userField = new JTextField(24);
		JPasswordField passwordField = new JPasswordField(24);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		add(new JLabel("Username: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		add(userField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		add(new JLabel("Password: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		add(passwordField, gbc);
		
		JPanel buttonPanel = new JPanel();
		
		JButton login = new JButton("log in");
		
		login.addActionListener(e -> {
			attemptSignIn(userField.getText(), passwordField.getPassword());
		});
		
		JButton signUp = new JButton("sign up instead");
		
		signUp.addActionListener(e -> {
			displaySignUpPage();
		});
		
		buttonPanel.add(login);
		buttonPanel.add(signUp);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		add(buttonPanel, gbc);
		
		validate();
		repaint();
	}
	
	public void attemptSignIn(String username, char[] password) {
		User user =  program.searchForUser(username);
		
		if(!(user != null && Arrays.equals(user.getPassword(), password))) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 5;
			
			add(new JLabel("That combination of password and username does not match"),gbc);
			
			validate();
			repaint();
			return;
		}	
		
		program.logIn(user);
	}
	
	public void displaySignUpPage() {
		removeAll();
		
		setLayout(new GridBagLayout());
		
		JTextField userField = new JTextField(24);
		JPasswordField passwordField = new JPasswordField(24);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		add(new JLabel("Username: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		add(userField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		add(new JLabel("Password: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		add(passwordField, gbc);
		
		JPanel buttonPanel = new JPanel();
		
		JButton createAccount = new JButton("create account");
		
		createAccount.addActionListener(e -> {
			User user = program.searchForUser(userField.getText());
			
			if (user != null) {
				// if the user exists, get a different user name
				
				GridBagConstraints errorConstraints = new GridBagConstraints();
				
				errorConstraints.gridx = 0;
				errorConstraints.gridy = 5;
				
				add(new JLabel("That username is in use, pick another"), errorConstraints);
				return;
			}
			
			user = new User(userField.getText(), passwordField.getPassword());
			
			program.registerUser(user);
			program.logIn(user);
		});
		
		JButton signIn = new JButton("sign in instead");
		
		signIn.addActionListener(e -> {
			displayLoginPage();
		});
		
		buttonPanel.add(createAccount);
		buttonPanel.add(signIn);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		add(buttonPanel, gbc);
		
		validate();
		repaint();
	}
	
	public void displayFollowedUserPage(User user) {
		removeAll();
		
		validate();
		repaint();
	}
	
	public void displaySearchPage() {
		removeAll();		
		
		setLayout(new GridBagLayout());
		
		JPanel searchPanel = new UserSearchArea(program);
		
		add(searchPanel);
		
		validate();
		repaint();
	}
	
	public void displayCurrentUserPage() {
		removeAll();
		
		setLayout(new BorderLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		// create area for posts and area for writing a new post
		// in the area for posts create a JPanel and populate the area
		// with all the posts
		// for the area to create a post add a text area for the user and a button to post
		
		JPanel scrollablePanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(scrollablePanel); 
		scrollablePanel.setLayout(new GridBagLayout());
		
		JPanel detailsPanel = new JPanel();
		JPanel postsPanel = new JPanel();		 
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		scrollablePanel.add(detailsPanel, gbc);
		gbc.gridy = 1;
		scrollablePanel.add(postsPanel, gbc);
		
		User user = program.getCurrentUser();
		
		//display basic stuff for user's details
		
		detailsPanel.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 10;
		detailsPanel.add(new JLabel(user.getUsername() + "'s account:"), gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		JLabel hometownLabel = new JLabel("Hometown: " + user.getHometown());
		detailsPanel.add(hometownLabel, gbc);
		gbc.gridx = 1;
		JButton changeHometownButton = new JButton("change hometown");
		detailsPanel.add(changeHometownButton, gbc);
		
		changeHometownButton.addActionListener(e -> {
			String newHometown = JOptionPane.showInputDialog(this, "type in new hometown: ");
			hometownLabel.setText("Hometown: " + newHometown);
			user.setHometown(newHometown);
		});
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		JLabel workplaceLabel = new  JLabel("Workplace: " + user.getWorkplace());
		detailsPanel.add(workplaceLabel, gbc);
		gbc.gridx = 1;
		JButton changeWorkplaceButton = new JButton("change workplace");
		detailsPanel.add(changeWorkplaceButton, gbc);
		
		changeWorkplaceButton.addActionListener(e -> {
			String newWorplace = JOptionPane.showInputDialog(this, "type in new hometown: ");
			workplaceLabel.setText("Workplace: " + newWorplace);
			user.setWorkplace(newWorplace);
		});
		
		//display the users posts alongside a textarea for making a new post
		
		postsPanel.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 60;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		LinkedList<Post> postList = user.getUserPosts();
		
		if (postList != null) {
			Iterator<Post> it = postList.iterator();
			
			while(it.hasNext()) {
				JPanel postPanel = it.next().createPost();
				postsPanel.add(postPanel, gbc);
				gbc.gridy += 1;
			}
		}
		
		
		JTextField newPostTitle = new JTextField("Enter Post Title", 16);		
		JTextArea newPostContent = new JTextArea("Enter Post Content", 10, 24);
		JButton newPostButton = new JButton("Make new Post");
		
		gbc.ipadx = 0;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		postsPanel.add(newPostTitle, gbc);
		gbc.gridx = 1;
		postsPanel.add(newPostButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy += 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		postsPanel.add(newPostContent, gbc);
		
		newPostButton.addActionListener(e -> {
			user.addPost(new Post(user.getUsername(), newPostTitle.getText(), newPostContent.getText(), 0));
			displayCurrentUserPage();
		});
		
		
		
		add(scrollPane, BorderLayout.CENTER);
		
		validate();
		repaint();
	}	

}
