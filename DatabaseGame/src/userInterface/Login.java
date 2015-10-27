package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.DatabaseUtil;

public class Login extends JFrame implements ActionListener, Navigator{
	JPanel mainContainer = new JPanel(new GridLayout(2,2));
	JLabel lblUsername = new JLabel("User Name");
	JTextField txtUsername = new JTextField(10);
	JLabel lblPassword = new JLabel("Password");
	JPasswordField txtPassword = new JPasswordField(10);
	JPanel buttonPanel = new JPanel(new GridLayout(1,3));
	JButton btnSubmit = new JButton("Submit");
	JButton btnLookup = new JButton("Forgot Password");
	JButton btnCreateUser = new JButton("Create User");
	DatabaseUtil du = new DatabaseUtil();
	GameFrame gameframe;
	
	public Login(GameFrame gameframe){
		this.gameframe=gameframe;
		mainContainer.add(lblUsername);
		mainContainer.add(txtUsername);
		mainContainer.add(lblPassword);
		mainContainer.add(txtPassword);
		this.add(mainContainer, BorderLayout.CENTER);
		btnSubmit.addActionListener(this);
		btnLookup.addActionListener(this);
		btnCreateUser.addActionListener(this);
		buttonPanel.add(btnSubmit);
		buttonPanel.add(btnLookup);
		buttonPanel.add(btnCreateUser);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setTitle("Login");
		this.setSize(500,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==btnSubmit&&!du.getProfile(txtUsername.getText(), txtPassword.getText())){
			txtUsername.setText("");
			txtPassword.setText("");
			JOptionPane.showMessageDialog(this, "Password or Username is incorrect", "Unknown Record", JOptionPane.ERROR_MESSAGE);
		}
		
		else if(e.getSource()==btnSubmit&&du.getProfile(txtUsername.getText(), txtPassword.getText())){
			navigate(this, gameframe);
		}
		
		else if(e.getSource()==btnLookup){
			navigate(this, new LookUpUser(gameframe));
		}
		
		else if(e.getSource()==btnCreateUser){
			navigate(this, new CreateUser(gameframe));
		}
	}

	@Override
	public void navigate(JFrame visible, JFrame invisible) {
		visible.dispose();
		invisible.setVisible(true);
	}
}
