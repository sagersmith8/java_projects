package gameCode;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel {
	JButton[][] jButtons = new JButton[6][6];
	
	public Board(ActionListener a){
		this.setLayout(new GridLayout(6,6));
		for(int i = 0; i < jButtons.length; i++){
			for(int j = 0; j < jButtons[0].length; j++){
				jButtons[i][j] = new JButton();
				jButtons[i][j].addActionListener(a);
				this.add(jButtons[i][j]);
				if(i==0&&j==0||i==0&&j==5||i==5&&j==0||i==5&&j==5||i==1&&j==0||i==1&&j==5||i==4&&j==0||i==4&&j==5||i==0&&j==1||i==0&&j==4||i==5&&j==1||i==5&&j==4)
					jButtons[i][j].setVisible(false);
			}
		}
	}
}
