package mastodontProject;

import java.io.Serializable;
import javax.swing.*;
import java.awt.*;

/**
 * A post class in which you can pretty much just call an instance of it and call the createPost() method to return a 
 * panel which can then be added straight to the frame or other component.
 */
public class Post implements Serializable{
    private String username;
    private String content;
    private int likes;
    private String title;
    private JPanel panel;
    private GridBagConstraints c;
    private Color colour = Color.yellow; // Can change if you want

    public Post(String Username, String Title, String postText, int Likes) {
        this.username = Username;
        this.title = Title;
        this.content = postText;
        this.likes = Likes;
    }

    /**
     * Returns a JPanel object which can be added straight to a frame or other component. Creates 2 JTextArea objects which both hold the 
     * Username and the other, the text in the post. There is also a title for each post and their respective number of likes which changes 
     * when the like button is clicked.
     * @return JPanel 
     */
    public JPanel createPost() {
        c = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(colour);

        // Title
        JTextArea title = new JTextArea(this.title);
        title.setEditable(false);
        title.setBackground(colour);
        title.setWrapStyleWord(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(title, c);

        // Username
        JTextArea username = new JTextArea(this.username);
        username.setEditable(false);
        username.setBackground(colour); 
        c.fill = GridBagConstraints.REMAINDER;
        c.weightx = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 1, 0);
        panel.add(username, c);
        
        // Content
        JTextArea content = new JTextArea(this.content);
        content.setEditable(false);
        content.setBackground(colour);
        content.setLineWrap(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(1, 0, 0, 0);
        panel.add(content, c);

        // Likes button
        JButton likesButton = new JButton("<3 " + this.likes);
        likesButton.addActionListener(e -> {
            this.addLike();
            likesButton.setText("<3 " + this.likes);
        });
        c.anchor = GridBagConstraints.LAST_LINE_START;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 1;
        c.gridy = 2;
        panel.add(likesButton, c);

        return panel;
    }
    
    /**
     * Getter for the <code>text</code> variable
     * @return String
     */
    public String getText() {
        return this.content;
    }

    /**
     * Getter for the <code>username</code> variable
     * @return String
     */
    public String getUsername() {
        return this.username;
    }

    public void addLike() {
        this.likes += 1;
    }
}