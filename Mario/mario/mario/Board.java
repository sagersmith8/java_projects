package mario;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

	public class Board extends JPanel implements KeyListener, ActionListener
	{
		int marioX=300, marioY=225, preX = 300, preY = 225, boardX = 0, boardC = 0, component = 0;
		JMenuBar m = new JMenuBar();
		JMenu file = new JMenu("File");
			JMenuItem exit = new JMenuItem("Exit");
			JMenuItem about = new JMenuItem("About");
		JMenu game = new JMenu("Game");
			JMenuItem newGame = new JMenuItem("New Game");
		JMenu components = new JMenu("Components");
			JMenuItem esc = new JMenuItem("Esc");
			JMenuItem brick = new JMenuItem("Brick");
			JMenu structures = new JMenu("Structures");
				JMenuItem tube = new JMenuItem("Tube");
				JMenuItem castle1 = new JMenuItem("Castle");
				JMenuItem flagPole = new JMenuItem("Flag Pole");
				
			JMenu blocks = new JMenu("Blocks");
				JMenuItem darkBrownB = new JMenuItem("Dark Brown");
				JMenuItem lightBrownB = new JMenuItem("Light Brown");
				JMenuItem redB = new JMenuItem("Red");
				JMenuItem yellowB = new JMenuItem("Yellow");
				JMenuItem darkGreenB = new JMenuItem("Dark Green");
				JMenuItem darkGrayB = new JMenuItem("Dark Gray");
				JMenuItem lightGrayB = new JMenuItem("Light Gray");
				JMenuItem lightBlueB = new JMenuItem("Light Blue");
				JMenuItem darkBlueB = new JMenuItem("Dark Blue");
				JMenuItem purpleB = new JMenuItem("Purple");
				JMenuItem lightGreenB = new JMenuItem("Light Green");
				
				BufferedImage back = new BufferedImage(1024,370,BufferedImage.TYPE_INT_RGB);
				BufferedImage marioI = new BufferedImage(30,47,BufferedImage.TYPE_INT_RGB);
				Rectangle gameBoardRect = new Rectangle(0,30,1024,360);
				JFrame gameBoard = new JFrame();
				Image icon, brickI, dBrownI, lBrownI, redI, yellowI, lBlueI, dBlueI, purpleI, lGreenI, dGreenI, lGrayI, dGrayI;
				Cursor dBrownC, brickC, lBrownC, redC, yellowC, lBlueC, dBlueC, purpleC, lGreenC, dGreenC, lGrayC, dGrayC;
				Music music;
//				Mario marios = new Mario();
				Robot r;

				int[][][] mario = new int[47][30][4];
				@SuppressWarnings("rawtypes")
				ArrayList[] points = new ArrayList[17];
				boolean right = true, left = false;
				String causeOfDeath;
		public Board()
		{			
			try
			{
				back = ImageIO.read(new File("MarioBoard.png"));
			}
			catch(IOException ioe)
			{
				System.err.println("Could not read image. Line: 393");
			}
			this.addKeyListener(this);
			this.setFocusable(true);
			gameBoard.setJMenuBar(m);
			m.add(file);
				file.add(about);
				about.addActionListener(this);
				file.add(exit);
				exit.addActionListener(this);
			m.add(game);
				game.add(newGame);
				newGame.addActionListener(this);
			m.add(components);
				components.add(esc);
				esc.addActionListener(this);
				components.add(brick);
				brick.addActionListener(this);
				components.add(structures);
					structures.add(tube);
					tube.addActionListener(this);
					structures.add(castle1);
					castle1.addActionListener(this);
					structures.add(flagPole);
					flagPole.addActionListener(this);
				components.add(blocks);
					blocks.add(darkBrownB);
					darkBrownB.addActionListener(this);
					blocks.add(lightBrownB);
					lightBrownB.addActionListener(this);
					blocks.add(redB);
					redB.addActionListener(this);
					blocks.add(yellowB);
					yellowB.addActionListener(this);
					blocks.add(darkGreenB);
					darkGreenB.addActionListener(this);
					blocks.add(darkGrayB);
					darkGrayB.addActionListener(this);
					blocks.add(lightGrayB);
					lightGrayB.addActionListener(this);
					blocks.add(lightBlueB);
					lightBlueB.addActionListener(this);
					blocks.add(darkBlueB);
					darkBlueB.addActionListener(this);
					blocks.add(darkBlueB);
					darkBlueB.addActionListener(this);
					blocks.add(purpleB);
					purpleB.addActionListener(this);
					blocks.add(lightGreenB);
					lightGreenB.addActionListener(this);

			try
			{
				marioI = ImageIO.read(new File("Mario.png"));
				icon = ImageIO.read(new File("MarioHead.png"));
				brickI = ImageIO.read(new File("BrickI.png"));
				dBrownI = ImageIO.read(new File("dBrownI.png"));
				lBrownI  = ImageIO.read(new File("lBrownI.png"));
				redI  = ImageIO.read(new File("redI.png"));
				yellowI  = ImageIO.read(new File("yellowI.png"));
				lBlueI  = ImageIO.read(new File("lBlueI.png"));
				dBlueI  = ImageIO.read(new File("dBlueI.png"));
				purpleI  = ImageIO.read(new File("purpleI.png"));
				lGreenI  = ImageIO.read(new File("lGreenI.png"));
				dGreenI  = ImageIO.read(new File("dGreenI.png"));
				lGrayI  = ImageIO.read(new File("lGrayI.png"));
				dGrayI  = ImageIO.read(new File("dGrayI.png"));
				r = new Robot();
			}
			catch(IOException ioe)
			{
				System.err.println("Could not read image. Line: 114 - 127");
			} catch (AWTException awte) {
				System.err.println("Could not create robot");
			}
			Toolkit tk = Toolkit.getDefaultToolkit();
			brickC = tk.createCustomCursor(brickI, new Point(this.getX(), this.getY()+1), "Brick Cursor");
			dBrownC = tk.createCustomCursor(dBrownI, new Point(this.getX(), this.getY()+1), "Brown Block Cursor");
			lBrownC = tk.createCustomCursor(lBrownI, new Point(this.getX(), this.getY()+1), "Brown Block Cursor");
			lBlueC = tk.createCustomCursor(lBlueI, new Point(this.getX(), this.getY()+1), "Brown Block Cursor");
			dBlueC = tk.createCustomCursor(dBlueI, new Point(this.getX(), this.getY()+1), "Brown Block Cursor");
			yellowC = tk.createCustomCursor(yellowI, new Point(this.getX(), this.getY()+1), "Brown Block Cursor");
			redC = tk.createCustomCursor(redI, new Point(this.getX(), this.getY()+1), "Brown Block Cursor");
//			lGrayC = tk.createCustomCursor(lGrayI, new Point(board.getX(), board.getY()+1), "Light Gray Block Cursor");
//			dGrayC = tk.createCustomCursor(dGrayI, new Point(board.getX(), board.getY()+1), "Dark Gray Block Cursor");
//			purpleC = tk.createCustomCursor(purpleI, new Point(board.getX(), board.getY()+1), "Purple Block Cursor");
//			lGreenC = tk.createCustomCursor(lGreenI, new Point(board.getX(), board.getY()+1), "Light Green Block Cursor");
//			dGreenC = tk.createCustomCursor(dGreenI, new Point(board.getX(), board.getY()+1), "Dark Green Block Cursor");

			for(int i=0; i<points.length;i++)
			{
				points[i] = new ArrayList<Integer>();
			}
			for(int y=0; y<mario.length; y++) // Y
			{
				for(int x=0; x<mario[0].length; x++) // X
				{
					mario[y][x][1] = marioX+x;
					mario[y][x][2] = marioY+y;
					mario[y][x][0] = back.getRGB(mario[y][x][1], mario[y][x][2]);
					if(y==0 || x==0 || y==mario.length || x==mario[0].length)
					{
						mario[y][x][0] = -16735512;
					}
				}
			}
			for(int y=0; y< mario.length; y++) // Y
			{
				for(int x=0; x< mario[0].length; x++) // X
				{
					mario[y][x][3] = marioI.getRGB(x, y);
				}
			}

			gameBoard.setIconImage(icon);
			gameBoard.setTitle("Mario");
			gameBoard.setBounds(gameBoardRect);
			gameBoard.setVisible(true);
			gameBoard.add(this);
//			gameBoard.setDefaultCloseOperation(EXIT_ON_CLOSE);
			music = new Music();
			gameBoard.addWindowListener(
					new WindowAdapter()
					{
						public void windowClosing(WindowEvent e)
						{
							System.exit(0);
						}
					});
			this.addMouseListener(
					new MouseAdapter()
					{
						@SuppressWarnings("unchecked")
						public void mouseClicked(MouseEvent e)
						{
							switch(component)
							{
								case 0:
									break;
								case 1:
									MarioComponents.blockDBrown(e.getX()+boardC, e.getY(), back);
									points[1].add(e.getX()+boardC);
									break;
								case 2:
									MarioComponents.blockLBrown(e.getX()+boardC, e.getY(), back);
									points[2].add(e.getX()+boardC);
									break;
								case 3:
									MarioComponents.blockRed(e.getX()+boardC, e.getY(), back);
									points[3].add(e.getX()+boardC);
									break;
								case 4:
									MarioComponents.blockYellow(e.getX()+boardC, e.getY(), back);
									points[4].add(e.getX()+boardC);
									break;
								case 5:
									MarioComponents.blockDGreen(e.getX()+boardC, e.getY(), back);
									points[5].add(e.getX()+boardC);
									break;
								case 6:
									MarioComponents.blockDGray(e.getX()+boardC, e.getY(), back);
									points[6].add(e.getX()+boardC);
									break;
								case 7:
									MarioComponents.blockLGray(e.getX()+boardC, e.getY(), back);
									points[7].add(e.getX()+boardC);
									break;
								case 8:
									MarioComponents.blockLBlue(e.getX()+boardC, e.getY(), back);
									points[8].add(e.getX()+boardC);
									break;
								case 9:
									MarioComponents.blockDBlue(e.getX()+boardC, e.getY(), back);
									points[9].add(e.getX()+boardC);
									break;
								case 10:
									MarioComponents.blockPurple(e.getX()+boardC, e.getY(), back);
									points[10].add(e.getX()+boardC);
									break;
								case 11:
									MarioComponents.blockLGreen(e.getX()+boardC, e.getY(), back);
									points[11].add(e.getX()+boardC);
									break;
								case 12:
									MarioComponents.brick(e.getX()+boardC, e.getY(), back);
									points[12].add(e.getX()+boardC);
									break;
								case 13:
	/*								try {
										MarioComponents.castle1(e.getX()+boardC, e.getY(), back);
									}
									catch(Exception ex) {
										System.err.println("Could not paint castle");
									}
	*/								points[13].add(e.getX()+boardC);
									break;
								case 14:
									try {
									MarioComponents.flagPole(e.getX()+boardC, e.getY(), back);
									}
									catch(Exception ex) {
										System.err.println("Could not paint flag pole");
									}
									points[14].add(e.getX()+boardC);
									break;
								case 15:
									try {
									MarioComponents.flag(e.getX()+boardC, e.getY(), back);
									}
									catch(Exception ex) {
										System.err.println("Could not paint flag");
									}
									points[15].add(e.getX()+boardC);
									break;
							}
							repaint();
						}
					}
					);
			mario();
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);
			Graphics g2d = (Graphics2D)g;
			g2d.drawImage(back,boardX,0,null);
		}

		public void actionPerformed(ActionEvent e)
		{
			String arg = e.getActionCommand();
			if(e.getSource() == exit)
			{
				System.exit(0);
			}
			else if(e.getSource() == newGame)
			{
				music.destroy();
				gameBoard.dispose();
				new Board();
			}
			else if(e.getSource() == about)
			{
				JOptionPane.showMessageDialog(null, "Made by Sage and Nick\nP.6 2013");
			}
			switch(arg)
			{
				case "Esc":
					component = 0;
					this.setCursor(Cursor.getDefaultCursor());
					break;
				case "Dark Brown":
					component = 1;
					this.setCursor(dBrownC);
					break;
				case "Light Brown":
					this.setCursor(lBrownC);
					component = 2;
					break;
				case "Red":
					this.setCursor(redC);
					component = 3;
					break;
				case "Yellow":
					this.setCursor(yellowC);
					component = 4;
					break;
				case "Dark Green":
					this.setCursor(dGreenC);
					component = 5;
					break;
				case "Dark Gray":
					this.setCursor(dGrayC);
					component = 6;
					break;
				case "Light Gray":
					this.setCursor(lGrayC);
					component = 7;
					break;
				case "Light Blue":
					this.setCursor(lBlueC);
					component = 8;
					break;
				case "Dark Blue":
					this.setCursor(dBlueC);
					component = 9;
					break;
				case "Purple":
					this.setCursor(purpleC);
					component = 10;
					break;
				case "Light Green":
					this.setCursor(lGreenC);
					component = 11;
					break;
				case "Brick":
					this.setCursor(brickC);
					component = 12;
					break;
				case "Tube":
					component = 17;
					break;
				case "Flag Pole":
					component = 14;
					break;
				case "Castle":
					component = 13;
					break;
			}
			
		}

		public void keyReleased(KeyEvent kt) {}
		public void keyTyped(KeyEvent arg0) {}
		public void keyPressed(KeyEvent ke)
		{
			if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				marioX+=5;
				pause(4);
				//gravity();
				if(marioX > boardC+714)
				{
					boardC += 5;
					boardX -= 5;
				}
				//mario();
			}
			else if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			{
				if(marioX != 0)
				{
					marioX-=5;
					pause(4);
					//gravity();
					if(marioX < boardC+300)
					{
						if(boardC == 0) {}
						else
						{
							boardC -= 5;
							boardX += 5;
						}
					}
				}
			}
			else if(ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_SPACE)
			{
//				System.out.println("here");
//				marioY-=10;
//				System.out.println(marioY);
				eraseM();
				repaint();
				Thread t = new Mario(this);
				t.start();
			}
			mario();
		}

		public void mario()
		{
			eraseM();
			drawM();
			repaint();
		}

		public void eraseM()
		{
			for(int y=0; y<mario.length; y++) // Y
			{
				for(int x=mario[0].length-1; x>=0; x--) // X
				{
					back.setRGB(mario[y][x][1], mario[y][x][2], mario[y][x][0]);
				}
			}
		}

		public void drawM()
		{
			for(int y=0; y<mario.length; y++) // Y
			{
				for(int x=mario[0].length-1; x>=0; x--) // X
				{
					mario[y][x][1] = marioX+x; // resets all x variables in the array
					mario[y][x][2] = marioY+y; // resets all y variables in the array
					if(y==0 || y==mario.length || x==0 || x==mario[0].length) // if it is a border pixel
					{
						mario[y][x][0] = back.getRGB(mario[y][x][1], mario[y][x][2]);  // get the background on the edge
					}
					else // if it is in the middle
					{
						mario[y][x][0] = back.getRGB(mario[y][x][1], mario[y][x][2]);  // get the background image
						if(mario[y][x][3] == -1) // if the pixel on the image is white
						{
							back.setRGB(mario[y][x][1], mario[y][x][2], mario[y][x][0]); // set the background
						}
						else // if the pixel is not white
						{
							back.setRGB(mario[y][x][1], mario[y][x][2], mario[y][x][3]); // set the background to mario
						}
					}
				}
			}
		}

		public void pause(long m)
		{
			try
			{
				Thread.sleep(m);
			}
			catch (InterruptedException e)
			{
				System.err.println("Could not pause. Line: ");
			}
		}

		/**
		 * This is the part has some serious issues right now
		 */
		public void run()
		{
		}
		public void playAgain()
		{

			int p = JOptionPane.showConfirmDialog(null, causeOfDeath + ".\nDo you want to play again?");

			if(p == JOptionPane.YES_OPTION)
			{
				gameBoard.dispose();
				new Board();
			}
			else if(p == JOptionPane.NO_OPTION)
			{
				System.exit(0);
			}
			else if(p == JOptionPane.CANCEL_OPTION){}
		}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new Board();
	}

}
