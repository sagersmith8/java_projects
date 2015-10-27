import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Sage
 *
 */
public class Snake extends JFrame implements KeyListener, ActionListener {
	JPanel[][] jp = new JPanel[40][40];
	JPanel board = new JPanel(new GridLayout(40,40));
	JPanel info = new JPanel(new GridLayout(1,3));
	Point head = new Point(19,20);
	Point tail = new Point(17,20);
	JLabel lblScore = new JLabel("\tScore:");
	JLabel scores = new JLabel("\t0");
	JButton newGame = new JButton("New Game");
	final int L = -1, U = 2, D = -2, R = 1;
	int dir=R;

	int score = 0;
	boolean done = false;
	int [][]snake = new int[40][40];

	public Snake(){
		this.setLayout(new BorderLayout());

		for(int i = 0; i < jp.length; i++){
			for(int j = 0; j < jp[0].length; j++){
				jp[i][j] = new JPanel();
				jp[i][j].setBackground(new Color(0,0,0));
				board.add(jp[i][j]);
				snake[i][j]=0;
			}
		}

		snake[20][19]=1;
		snake[20][18]=2;
		snake[20][17]=1;

		info.add(lblScore);
		info.add(scores);
		info.add(newGame);
		newGame.addActionListener(this);
		this.add(info, BorderLayout.NORTH);
		this.add(board,BorderLayout.CENTER);
		this.setTitle("Hypnotic Snake");
		this.setBounds(0,0,700,670);
		this.setVisible(true);

		this.addKeyListener(this);
		this.setFocusable(true);
		new SnakeThread().start();
	}

	public void init(){
		for(int i = 0; i < jp.length; i++){
			for(int j = 0; j < jp[0].length; j++){
				jp[i][j].setBackground(new Color(0,0,0));
				snake[i][j]=0;
			}
		}

		snake[20][19]=1;
		snake[20][18]=2;
		snake[20][17]=3;

		done = false;
		scores.setText("0");
		new SnakeThread().start();
	}


	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			dir = L;
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			dir = R;
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			dir = U;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			dir = D;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e){
		init();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Snake();
	}

	public class SnakeThread extends Thread{
		public void run(){
			while(!done){
				
				try{
					Thread.sleep(100);
				}
				catch(Exception ex){

				}
				
				try{
					if(dir==L){

						head.x=head.x-1;
						snake[head.y][head.x]=1;
						move(head.x,head.y,dir);

					}else if(dir==R){

						head.x=head.x+1;
						snake[head.y][head.x]=1;
						move(head.x-1,head.y,dir);


					}else if(dir == U){

						head.y=head.y-1;
						snake[head.y][head.x]=1;
						move(head.x,head.y,dir);


					}else if(dir == D){
						head.y=head.y+1;
						snake[head.y][head.x]=1;
						move(head.x,head.y,dir);

					}
				}catch(Exception ex){
					done=true;
					JOptionPane.showMessageDialog(null, "You Lose");
				}



				Color col = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));	
				for(int i = 0; i < jp.length; i++){
					for(int j = 0; j < jp[0].length; j++){
						if(snake[i][j] >1){
							jp[i][j].setBackground(col);
						}else if(snake[i][j]>0){
							jp[i][j].setBackground(Color.red);
						}
					}
				}

				repaint();

			
			}
		}

		public void move(int x, int y, int d){
			if(d!=L&&x-1>=0&&snake[y][x-1] > 0){
				snake[y][x]=snake[y][x-1];
				if(tail.x == x-1 && tail.y ==y){
					System.out.println("Here");
					jp[tail.y][tail.x].setBackground(Color.white);
					snake[tail.y][tail.x]=0;
					tail.x--;
				}else{
					move(x-1,y,R);
				}

			}

			else if(d!=R&&x+1<snake.length&&snake[y][x+1] > 0){
				snake[y][x]=snake[y][x+1];
				if(tail.x == x+1 && tail.y ==y){
					jp[tail.y][tail.x].setBackground(Color.white);
					snake[tail.y][tail.x]=0;
					tail.x++;
				}else{
					move(x+1,y,L);
				}
			}

			else if(d!=U&&y-1>=0&&snake[y-1][x] > 0){
				snake[y][x]=snake[y-1][x];
				if(tail.x == x && tail.y ==y-1){
					jp[tail.y][tail.x].setBackground(Color.white);
					snake[tail.y][tail.x]=0;
					tail.y--;
				}else{
					move(x,y-1,D);
				}
			}

			else if(d!=D&&y+1<snake.length&&snake[y+1][x] > 0){
				snake[y][x]=snake[y+1][x];
				if(tail.x == x && tail.y ==y+1){
					jp[tail.y][tail.x].setBackground(Color.white);
					snake[tail.y][tail.x]=0;
					tail.y++;
				}else{
					move(x,y+1,U);
				}
			}
		}
	}
}
