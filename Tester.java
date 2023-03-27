/*
 * 
 * Just a post thing so we can just take it and plug it right into the final program (if the GUI is used that is)
 * 
 */

import javax.swing.*;
import java.awt.*;

public class Tester {
    public static void main(String[] args) {
        // Initialise objects and frame
        //Tester t = new Tester();
        JFrame frame = new JFrame();
        GridBagConstraints c = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(new GridBagLayout());

        for (int i = 0; i < 10; i++) {
            // Instantiate each post
            Post post = new Post("Username", "woueifbjnvdoifnjkieufbcskjdnvwkjesdbvsdhckjsdcnskasbckjsvdbvskjdhcbksdm,b cksjdbvv");

            // Create and add each post
            JPanel postExample1 = post.createPost();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = GridBagConstraints.HORIZONTAL;
            c.weighty = GridBagConstraints.RELATIVE;
            c.gridwidth = 3;
            c.gridx = 0;
            c.insets = new Insets(2, 0, 2, 0);
            frame.add(postExample1, c);
        }

        // Show frame at the end
        frame.setVisible(true);
    }
}
