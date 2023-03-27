import javax.swing.*;
import java.awt.*;

/**
 * Im trying to make it so you can just create a new post object, call the create post method and itll add it to the 
 * frame thats passed through but for some reason i cant figure out why its being so annoying. 
 */
public class Post {
    private String username;
    private String text;
    private JPanel panel;
    private GridBagConstraints c;

    public Post(String Username, String postText) {
        this.username = Username;
        this.text = postText;
    }

    public JPanel createPost() {
        c = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.ORANGE); // panel coloourr

        JTextField usernameField = new JTextField(this.username);
        usernameField.setEditable(false);
        usernameField.setBackground(Color.BLUE); // so i can see what area is being used by the UsernameField
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(usernameField, c);
        
        JTextField textField = new JTextField(this.text);
        textField.setEditable(false);
        textField.setBackground(Color.green); // so i can see what area is being used by the textField
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = GridBagConstraints.HORIZONTAL;
        c.weighty = 1;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(textField, c);

        return panel;
    }
    
    public String getText() {
        return this.text;
    }

    public String getUsername() {
        return this.username;
    }
}
