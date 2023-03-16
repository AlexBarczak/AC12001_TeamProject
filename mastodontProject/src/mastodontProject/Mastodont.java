package mastodontProject;
import javax.swing.*;
import java.awt.*;

public class Mastodont extends JFrame{
	
	public Mastodont(){
		setTitle("Mastodon't");
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints componentConstraints = new GridBagConstraints();
		
		//header
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		JPanel header = new JPanel();
		componentConstraints.ipady = 40;
		header.setLayout(new GridBagLayout());
		header.add(new JLabel("mastodont"), componentConstraints);
		header.setBorder(BorderFactory.createMatteBorder(
                1, 5, 1, 1, Color.black));
		add(header, gbc);
		
		//side bar
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 1;
		JPanel sidebar = new JPanel();
		add(sidebar, gbc);
		sidebar.setLayout(new GridBagLayout());
		componentConstraints.fill = GridBagConstraints.VERTICAL;
		componentConstraints.gridy = 0;
		componentConstraints.gridx = 0;
		componentConstraints.ipadx = 40;
		componentConstraints.weighty=1.0;
		sidebar.add(new JButton("freind 1"), componentConstraints);
		componentConstraints.gridy = 1;
		sidebar.add(new JButton("friend 2"), componentConstraints);
		componentConstraints.gridy = 2;
		componentConstraints.weighty = 0;
		sidebar.add(new JButton("friend 3"), componentConstraints);
		componentConstraints.gridy = 3;
		sidebar.add(new JButton("friend 4"), componentConstraints);
		
		//main area
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridheight = 4;
		gbc.gridwidth = 4;
		JPanel main = new JPanel();
		add(main, gbc);
		
	}

	public static void main(String[] args) {
		// set up a GUI
		Mastodont mastodont = new Mastodont();

		mastodont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mastodont.setSize(800,600);
		mastodont.setVisible(true);
	}
	
	
	
}
