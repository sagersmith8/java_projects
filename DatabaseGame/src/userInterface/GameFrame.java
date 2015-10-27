package userInterface;

import gamelogic.God;
import gamelogic.Sprite;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import database.DatabaseUtil;

public class GameFrame extends JFrame implements ActionListener, Navigator, KeyListener {	
	public God god = new God();
	ImageButton allMinions =new ImageButton("all.png","All Minions");
	ImageButton fireMinion =new ImageButton("fire.png","Fire Minion");
	ImageButton waterMinion =new ImageButton("water.png","Water Minion");
	ImageButton earthMinion =new ImageButton("earth.png","Earth Minion");
	ImageButton airMinion =new ImageButton("air.png","Air Minion");
	
	ImageButton upgradeGod =new ImageButton("god.png","Upggrade Overlord");
	ImageButton upgradeFire =new ImageButton("fire.png","Upgrade Fire Minion");
	ImageButton upgradeWater =new ImageButton("water.png","Upgrade Water Minion");
	ImageButton upgradeEarth =new ImageButton("earth.png","Upgrade Earth Minion");
	ImageButton upgradeAir =new ImageButton("air.png","Upgrade Air Minion");	
	public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	JTabbedPane tabbedPane = new JTabbedPane();
	DatabaseUtil du = new DatabaseUtil();
	public Board board = new Board(this);
	JPanel command = new JPanel(new GridLayout(1,5));
	JPanel upgrade = new JPanel(new GridLayout(1,5));
	JPanel encounteredEnemies = new JPanel(new GridLayout(1,1));
	
	JMenuBar jmb = new JMenuBar();
		JMenu user = new JMenu("User");
			JMenuItem createAccount = new JMenuItem("Create Account");
			JMenuItem login = new JMenuItem("Login");

		
	public GameFrame(){
		allMinions.addActionListener(this);
		fireMinion.addActionListener(this);
		earthMinion.addActionListener(this);
		airMinion.addActionListener(this);
		waterMinion.addActionListener(this);
		du.deleteDatabase();
		du.createDatabase();
		this.setJMenuBar(jmb);
		jmb.add(Box.createHorizontalGlue());
		jmb.add(user);
		user.setIcon(new ImageIcon(god.getImage()));
		user.add(createAccount);
		createAccount.addActionListener(this);
		user.add(login);
		login.addActionListener(this);
		this.add(board, BorderLayout.CENTER);
		this.add(tabbedPane, BorderLayout.SOUTH);
		command.add(allMinions);
		command.add(fireMinion);
		command.add(waterMinion);
		command.add(earthMinion);
		command.add(airMinion);
		upgrade.add(upgradeGod);
		upgrade.add(upgradeFire);
		upgrade.add(upgradeWater);
		upgrade.add(upgradeEarth);
		upgrade.add(upgradeAir);
		tabbedPane.addTab("Command", command);
		tabbedPane.addTab("Upgrade", upgrade);
		tabbedPane.addTab("Encountered", encounteredEnemies);
		this.setTitle("Game");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(0,0,dim.width,dim.height-50);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent e) { 
				if(e.getButton()== MouseEvent.BUTTON1){
					god.setSpriteCommand(god.ATTACK);
					god.setSpriteAttackLocation(e.getPoint());
				}
				
				else if(e.getButton()== MouseEvent.BUTTON3){
					god.setSpriteCommand(god.DEFEND);
					if(god.getDirection()==god.LEFT){
						god.setSpriteAttackLocation(god.getLoc().x-25,god.getLoc().y);
					}
					
					else if(god.getDirection()==god.RIGHT){
						god.setSpriteAttackLocation(god.getLoc().x+25,god.getLoc().y);
					}
					
					else if(god.getDirection()==god.UP){
						god.setSpriteAttackLocation(god.getLoc().x,god.getLoc().y+25);
					}
					
					else if(god.getDirection()==god.DOWN){
						god.setSpriteAttackLocation(god.getLoc().x,god.getLoc().y-25);
					}
				}
	        } 
        }); 
		
		this.getContentPane().addKeyListener(this);
		this.getContentPane().setFocusable(true);
		this.getContentPane().requestFocus();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(createAccount==e.getSource()){
			navigate(this,new CreateUser(this));
		}
		
		else if(login == e.getSource()){
			navigate(this, new Login(this));
		}
		
		else if(allMinions == e.getSource()){
			god.setSelectedSprite(god.ALL);
		}
		
		else if(airMinion == e.getSource()){
			god.setSelectedSprite(god.AIR);
		}
		
		else if(earthMinion == e.getSource()){
			god.setSelectedSprite(god.EARTH);
		}
		
		else if(fireMinion == e.getSource()){
			god.setSelectedSprite(god.FIRE);
		}
		
		else if(waterMinion == e.getSource()){
			god.setSelectedSprite(god.WATER);
		}
		
		this.getContentPane().requestFocus();
	}

	public static void main(String[] args) {
		new GameFrame();
	}

	@Override
	public void navigate(JFrame visible, JFrame invisible) {
		visible.setVisible(false);	
		invisible.setVisible(true);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			try{
				god.setDirection(god.LEFT);
				god.setLoc(god.getLoc().x-1, god.getLoc().y);
				board.repaint();
			}
			
			catch(Exception ex){
			}
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			try{
				god.setDirection(god.RIGHT);
				god.setLoc(god.getLoc().x+1, god.getLoc().y);
				board.repaint();
			}
			
			catch(Exception ex){
			}
		}	

		else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			try{
				god.setDirection(god.DOWN);
				god.setLoc(god.getLoc().x, god.getLoc().y+1);
				board.repaint();
			}
			
			catch(Exception ex){
			}
		}

		else if(e.getKeyCode()==KeyEvent.VK_UP){
			try{
				god.setDirection(god.UP);
				god.setLoc(god.getLoc().x, god.getLoc().y-1);
				board.repaint();
			}
			
			catch(Exception ex){
			}
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_S){
			Sprite temp;
		
			if(god.getSelectedSprite()==god.EARTH){
				temp = new Sprite(this, "earth.png",god.EARTH);
				temp.start();
				sprites.add(temp);
			}
			
			else if(god.getSelectedSprite()==god.FIRE){
				temp = new Sprite(this, "fire.png",god.FIRE);
				temp.start();
				sprites.add(temp);
			}
			
			else if(god.getSelectedSprite()==god.WATER){
				temp = new Sprite(this, "water.png",god.WATER);
				temp.start();
				sprites.add(temp);
			}
			
			else if(god.getSelectedSprite()==god.AIR){
				temp = new Sprite(this, "air.png",god.AIR);
				temp.start();
				sprites.add(temp);
			}
			
			else if(god.getSelectedSprite()==god.ALL){
				temp = new Sprite(this, "air.png",god.AIR);
				temp.start();
				sprites.add(temp);
				temp = new Sprite(this, "fire.png",god.FIRE);
				temp.start();
				sprites.add(temp);
				temp = new Sprite(this, "water.png",god.WATER);
				temp.start();
				sprites.add(temp);
				temp = new Sprite(this, "earth.png",god.EARTH);
				temp.start();
				sprites.add(temp);
			}
			
		}
		
		else if(e.getKeyCode()==KeyEvent.VK_SPACE){
		}
		
		if(god.getSpriteCommand()==god.DEFEND){
			if(god.getDirection()==god.LEFT){
				god.setSpriteAttackLocation(god.getLoc().x+25,god.getLoc().y);
			}
			
			else if(god.getDirection()==god.RIGHT){
				god.setSpriteAttackLocation(god.getLoc().x-25,god.getLoc().y);
			}
			
			else if(god.getDirection()==god.UP){
				god.setSpriteAttackLocation(god.getLoc().x,god.getLoc().y+25);
			}
			
			else if(god.getDirection()==god.DOWN){
				god.setSpriteAttackLocation(god.getLoc().x,god.getLoc().y-25);
			}
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0){}
	@Override
	public void keyTyped(KeyEvent arg0){}

}
