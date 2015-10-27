import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JFrame;


public class Mainframe extends JFrame implements KeyListener{
	
	int wave = 1, score = 0, numPlayers =0, black =-16777216, brown= -4621737,grey = -3947581, direction = -1;
	boolean callGravity = true;
	Point[] platform={new Point(460, 523),new Point(729,228),new Point(454,60),new Point(117,271)};
	boolean [] isOccupied = {false,false,false,false};
	Point p1;; 
	HashMap<Integer,Integer> pressedKeys = new HashMap<Integer,Integer>();
	Board board = new Board(this);	
	Gravity gravity = new Gravity(board, this);
	Flap flap = new Flap(board, this, gravity);
	Move move = new Move(board, this, gravity);
	
	public Mainframe(){
		this.add(board);
		this.setTitle("Joust");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setIconImage(board.player1[0]);
		this.setResizable(false);
		this.setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent e) { 
	              System.out.println(""+ e.getPoint() + board.background.getRGB(e.getX(), e.getY())); 
	            } 
	          });
	}
	
	public static void main(String[] args) {
		new Mainframe();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER && numPlayers< 2){
			numPlayers++;
			new Birththread(board, this).start();
			new BirthEnemies(board, this).start();
		}
		pressedKeys.put(e.getKeyCode(),e.getKeyCode());
		
		if(!pressedKeys.isEmpty()){
			if(pressedKeys.containsKey(KeyEvent.VK_RIGHT)){
				if(!move.isAlive()){
					direction=1;
					move = new Move(board, this, gravity);
					move.start();
				}
				/*direction = 1;
				p1.x+=5;
				board.repaint();
				if(callGravity && !gravity.isAlive()){
					gravity= new Gravity(board, this);
					gravity.start();
				}*/
			}
			else if(pressedKeys.containsKey(KeyEvent.VK_LEFT)){
				if(!move.isAlive())
				{
					direction=-1;
					move = new Move(board, this, gravity);
					move.start();
				}
				/*
				p1.x-=5;
				board.repaint();
				if(callGravity && !gravity.isAlive()){
					gravity= new Gravity(board, this);
					gravity.start();
				}*/
			}
			if(pressedKeys.containsKey(KeyEvent.VK_SPACE)){
				if(!flap.isAlive()){
					flap = new Flap(board, this, gravity);
					flap.start();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.remove(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
