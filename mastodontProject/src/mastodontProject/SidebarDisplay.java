package mastodontProject;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.*;

public class SidebarDisplay extends JPanel{
	
	Mastodont program;
	JPanel friendsPanel;
	JScrollPane scrollPane;
	
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
		}
		
		friendsPanel.validate();
		friendsPanel.repaint();
	}
}
