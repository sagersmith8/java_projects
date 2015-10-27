package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Mainframe extends JFrame implements ActionListener {
	Board board = new Board();
	JTabbedPane functions = new JTabbedPane();
	JPanel pnlBuildTypes = new JPanel(new GridLayout(1,4));
	ImagePanel [] buildTypes = {new ImagePanel("assets/humancastle.png")};
	
	public Mainframe(){
		this.add(board, BorderLayout.CENTER);
		this.add(functions, BorderLayout.SOUTH);
		functions.addTab("Build", pnlBuildTypes);
		for(int i = 0; i < buildTypes.length; i++){
			pnlBuildTypes.add(buildTypes[i]);
		}
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setTitle("");
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Mainframe();
	}

}
