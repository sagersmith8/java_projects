package userInterface;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseUtil;

public class LookUpUser extends JFrame implements ActionListener, Navigator {
	DatabaseUtil du = new DatabaseUtil();

	JPanel mainContainer = new JPanel(new GridLayout(3,2));
	JPanel queriedResults = new JPanel(new GridLayout(1,2));
	
	JLabel lblFirstName = new JLabel("First Name");
	JLabel lblLastName = new JLabel("Last Name");
	JLabel lblDOB = new JLabel("DOB");
	JLabel lblUserName = new JLabel("User Name:");
	JLabel lblPassword = new JLabel("Password:");
	
	JTextField txtFirstName = new JTextField();
	JTextField txtLastName = new JTextField();
	JTextField txtDOB = new JTextField();
	
	JButton btnFindUser = new JButton("Find User");
	JButton btnLogin = new JButton("Login");
	
	GameFrame gameframe;
	
	public LookUpUser(GameFrame gameframe){
		this.gameframe = gameframe;
		mainContainer.add(lblFirstName);
		mainContainer.add(txtFirstName);
		mainContainer.add(lblLastName);
		mainContainer.add(txtLastName);
		mainContainer.add(lblDOB);;
		mainContainer.add(txtDOB);
		queriedResults.add(lblUserName);
		queriedResults.add(lblPassword);
		this.setLayout(new GridLayout(4,1));
		this.add(mainContainer);
		this.add(queriedResults);
		this.add(btnFindUser);
		btnFindUser.addActionListener(this);
		this.add(btnLogin);
		btnLogin.addActionListener(this);
		this.setTitle("Look Up User");
		this.setSize(500,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		if(btnFindUser == e.getSource()){
			ArrayList<String> userInfo = du.getProfile(txtFirstName.getText(), txtLastName.getText(), txtDOB.getText());
			if(userInfo.size()>0){
				lblUserName.setText("User Name: " +userInfo.get(0));
				lblPassword.setText("Password: " +userInfo.get(1));
			}
			
			else{
				JOptionPane.showMessageDialog(this, "User does not exist", "null User", JOptionPane.ERROR_MESSAGE);
				txtFirstName.setText("");
				txtLastName.setText("");
				txtDOB.setText("");
			}
		}
		
		else if(btnLogin == e.getSource()){
			navigate(this, new Login(gameframe));
		}
	}

	@Override
	public void navigate(JFrame visible, JFrame invisible) {
		visible.dispose();
		invisible.setVisible(true);
	}
}
