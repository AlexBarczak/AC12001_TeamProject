package mastodontProject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;

public class HeaderDisplay extends JPanel{
	
	private Mastodont program;
	
	public HeaderDisplay(Mastodont program) {
		this.program = program;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.black));
		displayGuestAppearance();
	}
	
	public void displayGuestAppearance() {
		removeAll();
		
		add(Box.createRigidArea(new Dimension(5,0)));
		add(new JLabel("mastodont"));
		add(Box.createHorizontalGlue());
		
		validate();
		repaint();
	}
	
	public void displayUserAppearance(User user){
		removeAll();
		
		add(Box.createRigidArea(new Dimension(5,0)));
		add(new JLabel("mastodont"));
		add(Box.createHorizontalGlue());
		add(new JLabel(user.getUsername()));

		add(Box.createRigidArea(new Dimension(5,0)));
		
		ImageIcon icon = new ImageIcon(getClass().getResource("../neia.jpg"), "is this missing?");
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		icon = new ImageIcon(newimg);  // transform it back
		
		
		JButton iconButton = new JButton(icon);
		iconButton.setBorder(null);
		iconButton.setBorderPainted(false);
		iconButton.setMargin(new Insets(0,0,0,0));
		
		iconButton.addActionListener(e -> {
			program.main.displayCurrentUserPage();
		});
		
		add(iconButton);
		
		add(Box.createRigidArea(new Dimension(5,0)));
		
		validate();
		repaint();
	}
	
	
	//Source is from https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
}
