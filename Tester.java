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
        Tester t = new Tester();
        JFrame frame = new JFrame();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLayout(gridBag);

        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 0;

        // Create 10 temporary text posts
        for (int i = 0; i < 10; i++) {
            Post post = new Post("User: " + i, "Post text: " + i);
            post.createPost(frame);
            //t.createPost(post.getUsername(), post.getText(), gridBag, c, frame);
        }

        // Show frame at the end
        frame.setVisible(true);
    }
}
