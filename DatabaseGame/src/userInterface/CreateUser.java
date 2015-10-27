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

public class CreateUser extends JFrame implements ActionListener, Navigator {
	DatabaseUtil du = new DatabaseUtil();
	JPanel mainContent = new JPanel(new GridLayout(6,2));
	JLabel lblFirstName = new JLabel("First Name");
	JLabel lblLastName = new JLabel("Last Name");
	JLabel lblDOB = new JLabel("DOB");
	JLabel lblEmail = new JLabel("Email");
	JLabel lblPassword = new JLabel("Password");
	JLabel lblPasswordConfirm = new JLabel("ConfirmPassword");
	JTextField txtFirstName = new JTextField(10);
	JTextField txtLastName = new JTextField(10);
	JTextField txtDOB = new JTextField(10);
	JTextField txtEmail = new JTextField(10);
	JPasswordField txtPassword = new JPasswordField();
	JPasswordField txtPasswordConfirm = new JPasswordField();
	JButton btnCreateUser = new JButton("Create User");
	GameFrame gameframe;
	
	public CreateUser(GameFrame gameframe){
		this.gameframe=gameframe;
		mainContent.add(lblFirstName);
		mainContent.add(txtFirstName);
		mainContent.add(lblLastName);
		mainContent.add(txtLastName);
		mainContent.add(lblDOB);
		mainContent.add(txtDOB);
		mainContent.add(lblEmail);
		mainContent.add(txtEmail);
		mainContent.add(lblPassword);
		mainContent.add(txtPassword);
		mainContent.add(lblPasswordConfirm);
		mainContent.add(txtPasswordConfirm);
		this.add(mainContent,BorderLayout.CENTER);
		btnCreateUser.addActionListener(this);
		this.add(btnCreateUser,BorderLayout.SOUTH);
		this.setTitle("Create User Account");
		this.setSize(500,300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(txtFirstName.getText()!=null&&txtLastName.getText()!=null&&txtDOB.getText()!=null&&txtEmail.getText()!=null&&txtPassword.getText()!=null&&txtPasswordConfirm.getText()!=null)
		{
			if(txtPassword.getText().equals(txtPasswordConfirm.getText()))
			{
				du.createProfile(txtFirstName.getText(),txtLastName.getText(),txtDOB.getText(),txtEmail.getText(),txtPassword.getText());
				navigate(this, new Login(gameframe));
			}
			
			else{
				JOptionPane.showMessageDialog(this, "Passwords Must Match", "Passwords Don't Match", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else{
			JOptionPane.showMessageDialog(this, "All fields must be filled in", "Error Filling in Fields", JOptionPane.ERROR_MESSAGE);
		}
	}


	@Override
	public void navigate(JFrame visible, JFrame invisible) {
		visible.dispose();
		invisible.setVisible(true);		
	}
}
