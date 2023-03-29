package mastodontProject;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;

public class MainDisplay extends JPanel implements ActionListener{
	
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
		
		//display basic stuff for user's details
		
		detailsPanel.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 10;
		detailsPanel.add(new JLabel(user.getUsername() + "'s account:"), gbc);
		
		JButton befriendButton = new JButton("add " + user.getUsername());
		
		befriendButton.addActionListener(e -> {
			program.getGraph().addEdge(user, program.getCurrentUser());
			program.sidebar.displayFollowed(program.getCurrentUser());
		});
		
		gbc.gridx = 1;
		detailsPanel.add(befriendButton,gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		JLabel hometownLabel = new JLabel("Hometown: " + user.getHometown());
		detailsPanel.add(hometownLabel, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		JLabel workplaceLabel = new  JLabel("Workplace: " + user.getWorkplace());
		detailsPanel.add(workplaceLabel, gbc);
		
		gbc.gridy = 3;
		gbc.gridx = 0;
		JButton viewFollowedButton = new JButton("view friends");
		
		viewFollowedButton.addActionListener(e -> {
			displayListOfFriends(user);
		});
		detailsPanel.add(viewFollowedButton, gbc);
		
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
		
		add(scrollPane, BorderLayout.CENTER);
		
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
	
	
	//uncertain about passing in arguments through a performed action so creating fields will have to do
	JPanel listPanel;
	User userToShow;
	
	public void displayListOfFriends(User user) {
		removeAll();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		userToShow = user;
		
		JPanel optionsPanel = new JPanel();
		listPanel = new JPanel();
		
		add(optionsPanel);
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(scrollPane);
		
		optionsPanel.add(new JLabel("showing friends of " + user.getUsername()));
		
		//Create the radio buttons
		JRadioButton allFriends = new JRadioButton("view all friends");
		allFriends.setActionCommand("all");
		allFriends.setSelected(true);
		optionsPanel.add(allFriends);
		
		JRadioButton mutuallyInclusiveFriends = new JRadioButton("view mutually inclusive friends");
		mutuallyInclusiveFriends.setActionCommand("mIn");
		optionsPanel.add(mutuallyInclusiveFriends);
		
		JRadioButton mutuallyExclusiveFriends = new JRadioButton("view mutually exclusive friends");
		mutuallyExclusiveFriends.setActionCommand("mEx");
		optionsPanel.add(mutuallyExclusiveFriends);
		
		JRadioButton sameHometownFriends = new JRadioButton("view friends with same hometown");
		sameHometownFriends.setActionCommand("home");
		optionsPanel.add(sameHometownFriends);
		
		JRadioButton sameWorkplaceFriends = new JRadioButton("view friends with same workplace");
		sameWorkplaceFriends.setActionCommand("work");
		optionsPanel.add(sameWorkplaceFriends);
		
		//Group the radio buttons
		ButtonGroup group = new ButtonGroup();
	    group.add(allFriends);
	    group.add(mutuallyInclusiveFriends);
	    group.add(mutuallyExclusiveFriends);
	    group.add(sameHometownFriends);
	    group.add(sameWorkplaceFriends);
		
	    //Register a listener for the radio buttons
	    allFriends.addActionListener(this);
	    mutuallyInclusiveFriends.addActionListener(this);
	    mutuallyExclusiveFriends.addActionListener(this);
	    sameHometownFriends.addActionListener(this);
	    sameWorkplaceFriends.addActionListener(this);
	    
	    actionPerformed(new ActionEvent(new JLabel(), 1, "all"));
	    
		validate();
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		listPanel.removeAll();
	
		String action = e.getActionCommand();
		
		if(action.equals("all")) {
			HashSet<User> list = program.getGraph().getAdjVertices(userToShow);
			Iterator<User> it = list.iterator();
			
			while(it.hasNext()) {
				User nextUser = it.next();
				
				JButton nextUserButton = new JButton(nextUser.getUsername());
				listPanel.add(nextUserButton);
				
				nextUserButton.addActionListener(f -> {
					program.main.displayFollowedUserPage(nextUser);
				});
			}
		}else if(action.equals("mIn")) {
			HashSet<User> list = program.getGraph().getMutuals(userToShow, program.getCurrentUser());
			Iterator<User> it = list.iterator();
			
			while(it.hasNext()) {
				User nextUser = it.next();
				
				JButton nextUserButton = new JButton(nextUser.getUsername());
				listPanel.add(nextUserButton);
				
				nextUserButton.addActionListener(f -> {
					program.main.displayFollowedUserPage(nextUser);
				});
			}
		}else if(action.equals("mEx")) {
			HashSet<User> list = program.getGraph().getUniques(userToShow, program.getCurrentUser());
			Iterator<User> it = list.iterator();
			
			while(it.hasNext()) {
				User nextUser = it.next();
				
				JButton nextUserButton = new JButton(nextUser.getUsername());
				listPanel.add(nextUserButton);
				
				nextUserButton.addActionListener(f -> {
					program.main.displayFollowedUserPage(nextUser);
				});
			}
		}else if(action.equals("home")) {
			HashSet<User> list = program.getGraph().getAdjVertices(userToShow);
			Iterator<User> it = list.iterator();
			
			String hometownOfCurrentUser = program.getCurrentUser().getHometown();
			
			while(it.hasNext()) {
				User nextUser = it.next();
				
				if(!nextUser.getHometown().equals(hometownOfCurrentUser)) {
					continue;
				}
				
				JButton nextUserButton = new JButton(nextUser.getUsername());
				listPanel.add(nextUserButton);
				
				nextUserButton.addActionListener(f -> {
					program.main.displayFollowedUserPage(nextUser);
				});
			}
		}else if(action.equals("work")) {
			HashSet<User> list = program.getGraph().getAdjVertices(userToShow);
			Iterator<User> it = list.iterator();
			
			String workplaceOfCurrentUser = program.getCurrentUser().getWorkplace();
			
			while(it.hasNext()) {
				User nextUser = it.next();
				
				if(!nextUser.getWorkplace().equals(workplaceOfCurrentUser)) {
					continue;
				}
				
				JButton nextUserButton = new JButton(nextUser.getUsername());
				listPanel.add(nextUserButton);
				
				nextUserButton.addActionListener(f -> {
					program.main.displayFollowedUserPage(nextUser);
				});
			}
		}
		
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
