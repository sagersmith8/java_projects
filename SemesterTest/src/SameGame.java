import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SameGame extends JFrame implements ActionListener{
	int score = 0;
	JPanel mainPanel = new JPanel(new GridLayout(10,20)), scorePanel = new JPanel(new GridLayout(1,5)), newgamePane = new JPanel(), scorePane = new JPanel(new GridLayout(2,2));
	JButton [][] gameButtons = new JButton[10][20];
	HashMap<Character,Color> colorMap = new HashMap<Character,Color>();
	JButton newGame = new JButton("New Game");
	JLabel[] jLabels = new JLabel[6];
	String[] strings = {"This Total:","0","This Total Score","0", "Score", Integer.toString(score)};

	public SameGame(){
		newGame.setBackground(Color.ORANGE);
		newgamePane.add(newGame);
		newGame.addActionListener(this);
		newgamePane.setBackground(Color.black);
		scorePanel.add(newgamePane);
		for(int i = 0; i < jLabels.length; i++){
			jLabels[i] = new JLabel(strings[i]);
			jLabels[i].setBackground(Color.magenta);
			if(i<4)
				scorePane.add(jLabels[i]);
		}
		scorePanel.add(scorePane);
		scorePanel.add(jLabels[4]);
		scorePanel.add(jLabels[5]);

		colorMap.put('A', Color.red);
		colorMap.put('B', Color.blue);
		colorMap.put('C', Color.green);
		colorMap.put('D', Color.magenta);
		colorMap.put('E', Color.orange);
		colorMap.put(' ', Color.white);

		for(int i = 0; i < gameButtons.length; i++){
			for(int j = 0; j < gameButtons[0].length; j++){
				gameButtons[i][j] = new JButton(Character.toString(randomChar()));
				gameButtons[i][j].setBackground(colorMap.get(gameButtons[i][j].getText().charAt(0)));
				//gameButtons[i][j].setText(colorMap.get(gameButtons[i][i].getBackground()).toString());
				gameButtons[i][j].addActionListener(this);
				mainPanel.add(gameButtons[i][j]);
			}
		}
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(scorePanel, BorderLayout.SOUTH);
		this.setBounds(0,0,1000,600);
		this.setTitle("SameGame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new SameGame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(newGame == e.getSource()){
			this.dispose();
			new SameGame();
		}

		for(int i = 0; i < gameButtons.length; i++){
			for(int j = 0; j < gameButtons[0].length; j++){
				if(gameButtons[i][j]==e.getSource()){
					if(gameButtons[i][j]==e.getSource()){
						if(gameButtons[i][j].getBackground()==Color.GRAY){	
							remove();
							drop();
							drop();
						}
						else{
							reset();
							highlightButtons(i,j,gameButtons[i][j].getBackground());
						}
					}
				}
			}
		}
		repaint();
	}

	public void highlightButtons(int x, int y, Color color){
		if(gameButtons[x][y].getBackground()==color){
			gameButtons[x][y].setBackground(Color.GRAY);
			if(x>0){
				highlightButtons(x-1, y,color);
			}
			if(x<gameButtons.length-1){
				highlightButtons(x+1,y, color);
			}
			if(y<gameButtons[0].length-1){
				highlightButtons(x, y+1,color);
			}
			if(y>0){
				highlightButtons(x, y-1, color);
			}
		}
	}

	public void drop(){
		for(int j = 0; j < gameButtons[0].length; j++){
			for(int i = gameButtons.length-1;i>0; i--){
				if(gameButtons[i][j].getBackground()==Color.white){
					for(int k=i; k>0; k--){
						gameButtons[k][j].setBackground(gameButtons[k-1][j].getBackground());
						gameButtons[k][j].setText(gameButtons[k-1][j].getText());
						gameButtons[k-1][j].setBackground(Color.white);
						gameButtons[k-1][j].setText(" ");
					}
				}
			}
		}
		for(int j = 0; j < gameButtons[0].length; j++){
			int counter=0;
			for(int i = gameButtons.length-1;i>0; i--){
				if(gameButtons[i][j].getBackground()==Color.white){
					counter++;
				}
			}
			if(counter==gameButtons.length-1){
				for(int k=j; k<gameButtons[0].length-1; k++){
					for(int i = 0; i < gameButtons.length; i++){
						gameButtons[i][k].setBackground(gameButtons[i][k+1].getBackground());
						gameButtons[i][k].setText(gameButtons[i][k+1].getText());
						gameButtons[i][k+1].setBackground(Color.white);
						gameButtons[i][k+1].setText(" ");
					}
				}
			}
		}
	}

	public void reset()
	{
		for(int i = 0; i < gameButtons.length; i++){
			for(int j = 0; j < gameButtons[0].length; j++){
				gameButtons[i][j].setBackground(colorMap.get(gameButtons[i][j].getText().charAt(0)));
			}
		}
	}

	public void remove(){
		int counter = 0;
		for(int i = 0; i < gameButtons.length; i++){
			for(int j = 0; j < gameButtons[0].length; j++){
				if(gameButtons[i][j].getBackground()==Color.GRAY){
					counter++;
				}
			}
		}
		if(counter>1){
			for(int i = 0; i < gameButtons.length; i++){
				for(int j = 0; j < gameButtons[0].length; j++){
					if(gameButtons[i][j].getBackground()==Color.GRAY){
						gameButtons[i][j].setBackground(Color.white);
						gameButtons[i][j].setText(" ");
					}
				}
			}
		}
		score+=Math.pow(10,counter);
		jLabels[1].setText(""+counter);
		jLabels[3].setText(""+Math.pow(10,counter));
		jLabels[5].setText(""+score);
	}

	public char randomChar(){
		return (char)((int)(Math.random()*4)+65);
	}
}
