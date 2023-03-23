package mastodontProject;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class SidebarDisplay extends JPanel{
	
	JPanel friendsPanel;
	JScrollPane scrollPane;
	
	public SidebarDisplay() {
		setLayout(new BorderLayout());
		
		friendsPanel = new JPanel();
		scrollPane = new JScrollPane(friendsPanel);
		
		setPreferredSize(new Dimension(150, Integer.MAX_VALUE));
		
		
		friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
		friendsPanel.add(new JLabel("friends:"));
		friendsPanel.add(new JLabel("kinda empty here..."));
		add(scrollPane, BorderLayout.CENTER);
		
	}

	public void displayFollowed(User user) {
		friendsPanel.removeAll();
		
		friendsPanel.add(new JLabel("friends:"));
		
		friendsPanel.add(new JButton("Obscenely long name for testing"));
		friendsPanel.add(new JButton("Aleksander Barczak"));
		friendsPanel.add(new JButton("Flynn Henderson"));
		friendsPanel.add(new JButton("Lucy Thompson"));
		friendsPanel.add(new JButton("Emma Martin"));
		friendsPanel.add(new JButton("Martyn Bett"));
		
		friendsPanel.validate();
		friendsPanel.repaint();
	}
}
