import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainFrame extends JFrame implements KeyListener, MouseListener {
	Utils utils = new Utils(this);
	Board board = new Board(utils, this);
	Gravity g;
	JumpThread jt;
	Timer timer = new Timer();
	
	public MainFrame(){
		g = new Gravity(utils, board);
		jt = new JumpThread(utils, board, g);
		g.start();
		timer.scheduleAtFixedRate(new GenerateEnemies(utils, board), 5000, 10000);
		this.setBounds(0,0,1014,530);
		this.add(board);
		this.setIconImage(board.ninja);
		this.setTitle("Survival");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.addMouseListener(this);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		boolean move = true;
		int amount =5;
		if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A){
			for(int i = utils.y-1; i > utils.y-34; i--)
			{
				try{
					if(board.level[utils.levelNum].getRGB(utils.x-1, i)==utils.black)
						move = false;
				}
				catch(Exception ex){
					move = false;
				}
			}

			if(move)
				utils.x-=amount;
			repaint();
		}

		else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D){
			for(int i = utils.y-1; i > utils.y-34; i--)
			{
				try{
					if(board.level[utils.levelNum].getRGB(utils.x+27, i)==utils.black)
						move = false;
				}
				catch(Exception ex){
					move = false;
				}
			}

			if(move)
				utils.x+=amount;
			repaint();
		}		

		if(!g.isAlive()&& !jt.isAlive())
		{
			g = new Gravity(utils, board);
			g.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!jt.isAlive()&& !g.isAlive())
		{
			jt = new JumpThread(utils, board, g);
			jt.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
