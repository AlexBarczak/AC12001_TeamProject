package mastodontProject;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.*;

/**#
 * class to display a list of the currently signed in users friends to the side bar
 * or display a default appearance to a guest user
 * 
 * @author Aleksander Barczak
 *
 */
public class SidebarDisplay extends JPanel{

	Mastodont program;
	JPanel friendsPanel;
	JScrollPane scrollPane;
	
	/**
	 * initialising function called when the program first launches to display a default look to 
	 * the guest user
	 * 
	 * @param program reference to the main program running to be able to request other panels in the GUI to be affected by this one
	 */
	public SidebarDisplay(Mastodont program) {
		this.program = program;
		setLayout(new BorderLayout());
		
		friendsPanel = new JPanel();
		scrollPane = new JScrollPane(friendsPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		setPreferredSize(new Dimension(150, Integer.MAX_VALUE));
		
		friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
		friendsPanel.add(new JLabel("friends:"));
		friendsPanel.add(new JLabel("kinda empty here..."));
		add(scrollPane, BorderLayout.CENTER);
		
	}

	/**
	 * function to display all the friends a user has followed in the side bar
	 * 
	 * @param user user whose friends to display in the side bar, most of the time just the logged in user
	 */
	public void displayFollowed(User user) {
		friendsPanel.removeAll();
		
		JButton searchButton = new JButton("Look for people");
		friendsPanel.add(searchButton);
		
		searchButton.addActionListener(e -> {
			program.displaySearchFunction();
		});
		
		friendsPanel.add(new JLabel("friends:"));	
		
		Iterator<User> it = program.getGraph().getAdjVertices(program.getCurrentUser()).iterator();
		
		while(it.hasNext()) {
			User nextUser = it.next();
			
			JButton nextUserButton = new JButton(nextUser.getUsername());
			friendsPanel.add(nextUserButton);
			
			nextUserButton.addActionListener(e -> {
				program.main.displayFollowedUserPage(nextUser);
			});
		}
		
		friendsPanel.validate();
		friendsPanel.repaint();
	}
}
