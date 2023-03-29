package mastodontProject;
import java.awt.*;
import javax.swing.*;
/**
 * 
 * Class to handle the search and add friend functionality
 * 
 * 
 * @author alexb + Flynn Henderson
 *
 */
public class UserSearchArea extends JPanel{
	
	Mastodont program;
	JPanel resultPanel;
	/**
	 * Default constructor of a user search area panel
	 * @param program The mastodon't program
	 */
	public UserSearchArea(Mastodont program) {
		this.program = program;
		display();
	}
	/**
	 * Display method to display searched for user, with the option to add them
	 */
	
	public void display() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		add(new JLabel("friend username:"), gbc);
		
		gbc.gridx = 1;
		JTextField userSearchField = new JTextField(24);
		add(userSearchField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		JButton searchButton = new JButton("Search");
		add(searchButton, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		searchButton.addActionListener(e -> {
			resultPanel = new JPanel();
			
			User searchResult = program.searchForUser(userSearchField.getText());	
			
			if (searchResult == null) {
				gbc.gridy = 3;
				add(new JLabel("no such user found"), gbc);
				program.validateAndRepaint();
				return;
			}
			
			program.main.displayFollowedUserPage(searchResult);						
		});
		
		
		program.validateAndRepaint();		
	}
}
