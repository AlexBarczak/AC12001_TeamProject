package mastodontProject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.*;

/**
 * class for the displaying of the header of the mastodont applet
 * 
 * display the name of the app, the username of the currently logged in user
 * and a profile image though currently set to an unchangeable image as it's 
 * more just a proof of concept and we lack a logo
 * 
 * the profile image does however act as a button to head back to the main page of the
 * logged in user
 * 
 * @author Aleksander Barczak
 *
 */
public class HeaderDisplay extends JPanel{
	
	/**
	 * a reference back to the main program to be able to interact with other 
	 * panels in the GUI
	 */
	private Mastodont program;
	
	/**
	 * initialising function for the header display giving it a default layout manager and appearance
	 * along with a border and setting the main program reference
	 * 
	 * @param program reference to the mastodont program containing all the panels
	 */
	public HeaderDisplay(Mastodont program) {
		this.program = program;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.black));
		displayGuestAppearance();
	}
	
	/**
	 * 
	 * display the default appearance of the header when a user first opens the program
	 * 
	 */
	public void displayGuestAppearance() {
		removeAll();
		
		add(Box.createRigidArea(new Dimension(5,0)));
		add(new JLabel("mastodont"));
		add(Box.createHorizontalGlue());
		
		validate();
		repaint();
	}
	
	/**
	 * changes the appearance of the header to be more suited to the user who is logged in
	 * adds the username of the user in the top right corner and creates a profile image 
	 * serving as a button to return to the logged in user's profile, though it was really just
	 * a proof of concept for displaying images and having it serve a purpose
	 * 
	 * @param user who is currently logged in
	 */
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
