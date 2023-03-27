import javax.swing.*;
import java.awt.*;

/**
 * Im trying to make it so you can just create a new post object, call the create post method and itll add it to the 
 * frame thats passed through but for some reason i cant figure out why its being so annoying. 
 */
public class Post {
    private String username;
    private String text;
    private GridBagConstraints mainConstraints;
    private GridBagConstraints textConstraints;
    private GridBagLayout mainGridBag;
    private GridBagLayout textGridBag;

    public Post(String Username, String postText) {
        this.username = Username;
        this.text = postText;

        mainConstraints = new GridBagConstraints();
        //textConstraints = new GridBagConstraints();
        mainGridBag = new GridBagLayout();
        //textGridBag = new GridBagLayout();

        mainConstraints.weighty = 1;
        mainConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainConstraints.weightx = GridBagConstraints.HORIZONTAL;
        mainConstraints.gridwidth = GridBagConstraints.REMAINDER;
        mainConstraints.gridx = 0;

        //textConstraints.weighty = 0;
        //textConstraints.fill = GridBagConstraints.HORIZONTAL;
        //textConstraints.weightx = GridBagConstraints.HORIZONTAL;
        //textConstraints.gridwidth = GridBagConstraints.REMAINDER;
        //textConstraints.gridx = 0;
    }

    public String getText() {
        return this.text;
    }

    public String getUsername() {
        return this.username;
    }

    public void createPost(JFrame frame) {
        JTextField usernameField = new JTextField(this.username);
        //JTextField textField = new JTextField(this.text);

        usernameField.setEditable(false);
        usernameField.setBackground(Color.BLUE); // so i can see what area is being used by the UsernameField
        //textField.setEditable(false);
        //textField.setBackground(Color.green); // so i can see what area is being used by the textField

        //usernameField.add(textField);
        //textGridBag.setConstraints(textField, this.textConstraints);
        mainGridBag.setConstraints(usernameField, this.mainConstraints);


        frame.add(usernameField);
    }
}
