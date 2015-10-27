package gameCode;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Main extends JFrame implements ActionListener {

	public Main(){
		this.setTitle("Game");
		this.setBounds(new Rectangle(0,0,500,500));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new Board(this));
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
