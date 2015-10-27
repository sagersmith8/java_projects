package gaussianFilter;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ValueEditor extends JFrame implements ActionListener {

	JMenuBar mBar = new JMenuBar();
		JMenu file = new JMenu("File");
			JMenuItem about = new JMenuItem("About");
			JMenuItem save = new JMenuItem("Save");
			JMenuItem exit = new JMenuItem("Exit");
		JMenu options = new JMenu("Options");
			JMenuItem gen = new JMenuItem("Auto-generate");
			JMenuItem clear = new JMenuItem("Clear values");
			
	TextField[][] values;
	JPanel p = new JPanel(); // main grid panel
	JPanel bottomP = new JPanel(new GridLayout(1,2)); // arithmatic panel
	JTextField text = new JTextField(); // operand value
	JPanel buttonP = new JPanel(new GridLayout(1, 4)); // holds opperators
	JButton m = new JButton("*");
	JButton d = new JButton("/");
	JButton s = new JButton("-");
	JButton a = new JButton("+");

	float[][] temp;
	
	public ValueEditor(float[][] matrix) {
		setBounds(300, 300, 400, 400);	
		setTitle("Value Editor");
		setJMenuBar(mBar);
		setVisible(true);
		
		mBar.add(file);
			file.add(about);
			about.addActionListener(this);
			file.add(save);
			save.addActionListener(this);
			file.add(exit);
			exit.addActionListener(this);
		mBar.add(options);
			file.add(gen);
			gen.addActionListener(this);
			file.add(clear);
			clear.addActionListener(this);
		
		temp = new float[matrix.length][matrix[0].length];
		values = new TextField[matrix.length][matrix[0].length];
		p.setLayout(new GridLayout(matrix.length, matrix[0].length));
		
		for (int y = 0; y < matrix.length; y++) {
			for (int x = 0; x < matrix[0].length; x++) {
				temp[x][y] = matrix[x][y];
				values[x][y] = new TextField(String.valueOf(temp[x][y]));
				p.add(values[x][y]);
			}
		}
		add(p, BorderLayout.CENTER);
		add(bottomP, BorderLayout.SOUTH);
		
		bottomP.add(buttonP);
		bottomP.add(text);
		
		buttonP.add(m);
		m.addActionListener(this);
		buttonP.add(d);
		d.addActionListener(this);
		buttonP.add(a);
		a.addActionListener(this);
		buttonP.add(s);
		s.addActionListener(this);
		
	}
	
	private void save() {
		for (int y = 0; y < temp.length; y++) {
			for (int x = 0; x < temp[0].length; x++) {
				
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit) {
			int answer = JOptionPane.showConfirmDialog(this, "Do you wan to save your values before exiting.", "Confirm Exit", JOptionPane.YES_NO_OPTION);
			if(answer == JOptionPane.YES_OPTION) {
//				save();
			} else {
				System.exit(0);
			}
		} else if(e.getSource() == save) {
//			save();
		} else if(e.getSource() == about) {
			//TODO
		} else if(e.getSource() == gen) {
			// TODO auto generate values
		} else if(e.getSource() == clear) {
			for (int y = 0; y < temp.length; y++) {
				for (int x = 0; x < temp[0].length; x++) {
					temp[x][y] = 0;
				}
			}
		} else if(e.getSource() == m) {
			double i = Double.parseDouble(text.getText());
			for (int y = 0; y < temp.length; y++) {
				for (int x = 0; x < temp[0].length; x++) {
					temp[x][y] *= i;
				}
			}
		} else if(e.getSource() == d) {
			double i = Double.parseDouble(text.getText());
			for (int y = 0; y < temp.length; y++) {
				for (int x = 0; x < temp[0].length; x++) {
					temp[x][y] /= i;
				}
			}
		} else if(e.getSource() == a) {
			double i = Double.parseDouble(text.getText());
			for (int y = 0; y < temp.length; y++) {
				for (int x = 0; x < temp[0].length; x++) {
					temp[x][y] += i;
				}
			}
		} else if(e.getSource() == s) {
			double i = Double.parseDouble(text.getText());
			for (int y = 0; y < temp.length; y++) {
				for (int x = 0; x < temp[0].length; x++) {
					temp[x][y] -= i;
				}
			}
		}
		for (int y = 0; y < temp.length; y++) {
			for (int x = 0; x < temp[0].length; x++) {
				values[x][y].setText(String.valueOf(temp[x][y]));
			}
		}
	}
	
	public static void main(String[] args) {
		float [][] matrixX = { 
				   {-1f, 0f, 1f,},
				   {-2f, 0f, 2f,},
				   {-1f, 0f, 1f,}
		};
		new ValueEditor(matrixX);
	}
}
