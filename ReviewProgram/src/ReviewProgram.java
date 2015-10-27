import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ReviewProgram extends JFrame implements ActionListener {
	JPanel panel = new JPanel(new GridLayout(10,10));
	int width = 10, height =10;
	HashMap<String,Color> hm = new HashMap<String,Color>();
	JButton[][] jButs = new JButton[10][10];

	public ReviewProgram(){
		hm.put("red", new Color(255,0,0));
		hm.put("blue", new Color(0,0,255));

		for(int i = 0; i < jButs[0].length/2; i++){
			for(int j = 0; j < jButs.length; j++){
				jButs[i][j] = new JButton();
				jButs[i][j].addActionListener(this);
				panel.add(jButs[i][j]);
				jButs[i][j].setBackground(hm.get("red"));
				if(j%2==0)
				{
					jButs[i][j].setBackground(hm.get("blue"));
				}
			}
		}
		
		for(int i = jButs[0].length/2; i < jButs[0].length; i++){
			for(int j = 0; j < jButs.length; j++){
				jButs[i][j] = new JButton();
				jButs[i][j].addActionListener(this);
				panel.add(jButs[i][j]);
				jButs[i][j].setBackground(hm.get("red"));
				if(i%2==0)
				{
					jButs[i][j].setBackground(hm.get("blue"));
				}
			}
		}

		this.add(panel);
		this.setTitle("Review Program");
		this.setBounds(0,0,500,500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ReviewProgram();

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < jButs[0].length; i++){
			for(int j = 0; j< jButs.length; j++){
				if(jButs[i][j] == e.getSource()){
					String str = JOptionPane.showInputDialog("left, down");
					shift(str, i, j);
				}
			}
		}
	}
	
	public void shift(String direction, int x, int y){
		if(direction.equals("down")){
			for(int j = x; j > 0; j--){
				for(int i = 0; i < width; i++){
					try{
						panel.remove(jButs[j][i]);
						panel.revalidate();
						this.repaint();
					}
					catch(Exception ex){
						System.out.println("done");
					}
				}
			}
		}
		
		else if(direction.equals("left")){
			for(int j = 0; j< height; j++){
				for(int i = y; i < width; i++){
					try{
						jButs[j][i].remove()
					}
					catch(Exception ex){
						System.out.println("done");
					}
				}
			}
		}
	}
}


