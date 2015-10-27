package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Nick
 *
 */
@SuppressWarnings("serial")
public class SpaceInvaders extends Thread implements ActionListener
{
	int shield1=0, shield2=0,shield3=0, shield4=0, shieldColor = -16777043, level = 1, tankColor = -13181201, score = 0, preScore = 0, x = 15, check = 0, lives = 3, speed = 1000, y = 60, tankX= 125, tankY = 425, bulletX1 = 0, bulletY1 = 0, playAgain = 0, counterX = 2, counter = 0;
	/**Holds the values for the positions of the aliens in reference to their order on the screen
	 * 
	 * <ul>
	 * <li>[x][y][0] = x coordinate for specified alien
	 * <li>[x][y][1] = y coordinate for specified alien
	 * </ul>
	 */
	int [][][] alienXYZ = new int [12][3][2];
	/**Hold s the life value of each alien in reference to their order on the screen
	 * 
	 * * <ul>
	 * <b> Life Values:</b>
	 * 	<li>2 = set X,Y of AlienXYZ
	 * 	<li>0 = Alive
	 * 	<li>-1 = just shot
	 * 	<li>-2 = just exploded
	 * 	<li>1 = dead forever
	 * </ul>
	 */
	int [][] alienAlive = new int [12][3];
	boolean start = false, instantiate = true, beatScore = false, shooting = false, c = true;
	Board board = new Board();
	Rectangle nicksGameRect = new Rectangle(0, 30, 310, 500);
	JFrame gameBoard = new JFrame();
	BufferedImage buf = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
	JMenuBar m = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem about = new JMenuItem("About");
	JMenu game = new JMenu("Game");
	JMenuItem newGame = new JMenuItem("New Game");
	JMenu changeSpeed = new JMenu("Change Level");
	ArrayList<JMenuItem>speeds = new ArrayList<JMenuItem>();

	/**
	 * 
	 */
	public SpaceInvaders()
	{
		lives=3;
		gameBoard.setJMenuBar(m);
			m.add(file);
				file.add(about);
				about.addActionListener(this);
				file.add(exit);
				exit.addActionListener(this);
			m.add(game);
				game.add(newGame);
				newGame.addActionListener(this);
				game.add(changeSpeed);
				speeds.add(new JMenuItem("Level" + Integer.toString(score/3600+1)));
				for(int i = 0; i < speeds.size(); i++)
				{
					changeSpeed.add(speeds.get(i));
					speeds.get(i).addActionListener(this);
				}

		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j <3; j++)
			{
				alienAlive[i][j] = 2;
			}
		}

		drawScore(); // write score
		drawLives(); // draw three tanks
		drawScore(score); // draw score as zero
		drawAliens(x,y); // draw the first aliens
		
		gameBoard.setTitle("Space Invaders");
		gameBoard.setBounds(nicksGameRect);
		gameBoard.setVisible(true);
		gameBoard.add(board);
		
		tank(tankX,tankY); // set the initial tank position
		this.start(); // start the aliens moving
		gameBoard.addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
						gameBoard.dispose();
					}
				}
				);
	}

	/*
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == exit)
		{
			System.exit(0);
		}
		else if (e.getSource() == newGame)
		{
			gameBoard.dispose();
			@SuppressWarnings("unused")
			SpaceInvaders game = new SpaceInvaders();
		}
		for(int i=0; i<speeds.size(); i++)
		{
			if(e.getSource() == speeds.get(i))
			{
				speed = (1000-(50*i));
			}
		}
	}

	/**Draws a single alien at the specified coordinates.
	 * 
	 * @param x1	coordinate of the upper left corner of the alien
	 * @param y1	coordinate of the upper left corner of the alien
	 */
	public void alien(int x1, int y1)
	{
		for(int i = x1+3; i <x1+8;i++)
		{
			if(i>x1+5 || i<x1+5)
			{
				buf.setRGB(i, y1+1, 255255255);
			}
		}
		//y row
		for(int i =x1;i <x1+10;i++)
		{
			if(i== x1 || i== x1+2|| i == x1+8|| i== x1+10)
			{
				buf.setRGB(i, y1, 255255255);
			}
		}
		//y-1 row
		for(int i = x1; i < x1+11; i++)
		{
			if(i < x1+1 || i>x1+1 && i<x1+9|| i>x1+9)
			{
				buf.setRGB(i, y1-1, 255255255);
			}
		}
		// y-2 row
		for(int i = x1; i < x1+11; i++)
		{
			buf.setRGB(i, y1-2, 255255255);
		}
		//y-3 row
		for(int i = x1+1; i <x1+10; i++)
		{
			if(i<x1+3||i>x1+3 && i < x1+7|| i>x1+7)
			{
				buf.setRGB(i, y1-3, 255255255);
			}
		}
		//y-4 row
		for(int i = x1+2; i <x1+9; i++)
		{
			buf.setRGB(i, y1-4, 255255255);
		}
		//y-5 row
		for(int i = x1+3; i <x1+8; i++)
		{
			if(i==x1+3 || i == x1+7)
			{
				buf.setRGB(i, y1-5, 255255255);
			}
		}
		//y-6 row
		for(int i = x1+2; i <x1+9; i++)
		{
			if(i == x1+2||i== x1+8)
			{
				buf.setRGB(i, y1-6,255255255);
			}
		}
	}

	/**Erases a single alien at the specified coordinates.
	 * 
	 * @param x1	coordinate of the top left corner of the alien
	 * @param y1	coordinate of the top left corner of the alien
	 */
	public void alienErase(int x1, int y1)
	{
		for(int i = x1+3; i <x1+8;i++)
		{
			if(i>x1+5 || i<x1+5)
			{
				buf.setRGB(i, y1+1, 000000000);
			}
		}
		//y row
		for(int i =x1;i <x1+10;i++)
		{
			if(i== x1 || i== x1+2|| i == x1+8|| i== x1+10)
			{
				buf.setRGB(i, y1, 000000000);
			}
		}
		//y-1 row
		for(int i = x1; i < x1+11; i++)
		{
			if(i < x1+1 || i>x1+1 && i<x1+9|| i>x1+9)
			{
				buf.setRGB(i, y1-1, 000000000);
			}
		}
		// y-2 row
		for(int i = x1; i < x1+11; i++)
		{
			buf.setRGB(i, y1-2, 000000000);
		}
		//y-3 row
		for(int i = x1+1; i <x1+10; i++)
		{
			if(i<x1+3||i>x1+3 && i < x1+7|| i>x1+7)
			{
				buf.setRGB(i, y1-3, 000000000);
			}
		}
		//y-4 row
		for(int i = x1+2; i <x1+9; i++)
		{
			buf.setRGB(i, y1-4, 000000000);
		}
		//y-5 row
		for(int i = x1+3; i <x1+8; i++)
		{
			if(i==x1+3 || i == x1+7)
			{
				buf.setRGB(i, y1-5, 000000000);
			}
		}
		//y-6 row
		for(int i = x1+2; i <x1+9; i++)
		{
			if(i == x1+2||i== x1+8)
			{
				buf.setRGB(i, y1-6,000000000);
			}
		}
		board.repaint();
	}

	/**Draws a bullet shot from the tank(white) at the specified coordinates.
	 * 
	 * @param x1	coordinate for the tail end of the bullet
	 * @param y1	coordinate for the tail end of the bullet
	 */
	public void bullet(int x1,int y1)
	{
		for(int i = y1-3; i < y1; i++)
		{
			buf.setRGB(x1, i, 255255254);
		}
		buf.setRGB(x1+1, y1, 255255254);
		buf.setRGB(x1-1, y1, 255255254);
		pauseThread(7);
		board.repaint();
	}

	/**Draws a bullet shot from an alien(red) at the specified coordinates
	 * 
	 * @param x1	coordinate for the tail end of the bullet
	 * @param y1	coordinate for the tail end of the bullet
	 */
	public void bullet2(int x1,int y1)
	{
		for(int i = y1-3; i < y1; i++)
		{
			buf.setRGB(x1, i, -1237980);
		}
		buf.setRGB(x1+1, y1-4, -1237980);
		buf.setRGB(x1-1, y1-4, -1237980);
		pauseThread(7);
		board.repaint();
	}

	/**Erases a bullet shot from the tank at the specified coordinates.
	 * 
	 * @param x1	coordinate for the tail end of the bullet
	 * @param y1	coordinate for the tail end of the bullet
	 */
	public void bulletErase(int x1,int y1)
	{
		for(int i = y1-3; i < y1; i++)
		{
			buf.setRGB(x1, i, 0000000000);
		}
		buf.setRGB(x1+1, y1, 000000000);
		buf.setRGB(x1-1, y1, 000000000);
		board.repaint();
	}

	/**Erases a bullet shot from an alien at the specified coordinates.
	 * 
	 * @param x1	coordinate for the tail end of the bullet
	 * @param y1	coordinate for the tail end of the bullet
	 */
	public void bulletErase2(int x1,int y1)
	{
		for(int i = y1-3; i < y1; i++)
		{
			buf.setRGB(x1, i, 0000000000);
		}
		buf.setRGB(x1+1, y1-4, 000000000);
		buf.setRGB(x1-1, y1-4, 000000000);
		board.repaint();
	}

	/**
	 * 
	 */
	public void dataBase()
	{
		Statement stmt = null;
		Connection con = null;
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:SpaceInvadersDB";
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
		} catch(Exception x) {
			System.err.println("Could not connect : ERROR " + x.getMessage());
		}
		try {
			beatScore = false;
			boolean d = false;
			ArrayList<String> usernameList = new ArrayList<String>();
			ArrayList<Integer> highScoreList = new ArrayList<Integer>();

			ResultSet rs = stmt.executeQuery("SELECT * FROM HighScoreTable");

			while(rs.next())
			{
				usernameList.add(rs.getString("Username"));
				highScoreList.add(rs.getInt("HighScore"));
			}
			for(int i = 0; i < highScoreList.size() && !d; i++)
			{
				if(highScoreList.get(i) < score)
				{
					highScoreList.add(i,score);
					highScoreList.remove(highScoreList.size()-1);
					usernameList.add(i,JOptionPane.showInputDialog("Input your name:  "));
					usernameList.remove(usernameList.size()-1);
					beatScore = true;
					i = highScoreList.size();
					d = true;
				}
			}
			if(!beatScore)
			{
				JOptionPane.showMessageDialog(null,"Your score was not added to the list.","Failed",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				sort(highScoreList,usernameList);
				stmt.executeUpdate("DELETE FROM HighScoreTable");
				for(int i = 0; i < highScoreList.size(); i++)
				{
					stmt.executeUpdate("INSERT INTO HighScoreTable VALUES('" + usernameList.get(i)+ "','" + highScoreList.get(i) +"')");
				}
				stmt.close();
				con.close();
				JOptionPane.showMessageDialog(null,"Your score of "+ score + " was added to the high score list.","Updated High Score List",JOptionPane.INFORMATION_MESSAGE);
				con.close();
				gameBoard.getContentPane().removeAll();
				gameBoard.getContentPane().repaint();
				gameBoard.setBounds(0, 30, 600, 570);
				gameBoard.getContentPane().setLayout(new GridLayout(11,3));
				gameBoard.getContentPane().setBackground(Color.red);
				gameBoard.getContentPane().add(new JLabel("Rank"));
				gameBoard.getContentPane().add(new JLabel("User Name"));
				gameBoard.getContentPane().add(new JLabel("Score"));
				for(int j = 0; j < highScoreList.size(); j++)
				{
					gameBoard.getContentPane().add(new JLabel(Integer.toString(j)));
					gameBoard.getContentPane().add(new JLabel(usernameList.get(j)));
					gameBoard.getContentPane().add(new JLabel(Integer.toString(highScoreList.get(j))));
				}
				gameBoard.getContentPane().invalidate();
				gameBoard.getContentPane().validate();
			}
		} catch(Exception p) {
			System.err.println("Exception in: SpaceInvaders.dataBase" + p.getMessage());
		}
	}

	/**Draws the aliens onto the board in the position specified by <code>x1</code> and <cpde>y1</code>.
	 * Based on the <code>aliensXYZ</code> the method decides whether to paint an alien, an explosion
	 * or nothing.
	 * 
	 * <ul>
	 * <b> AlienAlive Life Values:</b>
	 * 	<li>2 = set X,Y of AlienXYZ
	 * 	<li>0 = Alive
	 * 	<li>-1 = just shot
	 * 	<li>-2 = just exploded
	 * 	<li>1 = dead forever
	 * </ul>
	 * 
	 * @param x1	coordinate of the upper left corner of the alien block
	 * @param y1	coordinate of the upper left corner of the alien block
	 */
	public void drawAliens(int x1, int y1)
	{		
		/*  AlienAlive Codes:
		 * 	2 = set X,Y of AlienXYZ
		 * 	0 = Alive
		 * 	-1 = just shot
		 * 	-2 = just exploded
		 * 	1 = dead forever
		 */

		if (instantiate == true) // if the game has just begun
		{
			counterX = 2;
			for(int i = 0; i < 12; i++) // set the spacing of the aliens
			{
				for(int j =0; j < 3; j++)
				{
					alienXYZ[i][j][0] = x1+(15*counterX);
				}
				for(int j =0; j < 3; j++)
				{
					alienXYZ[i][j][1] = y1+(15*j);
				}
				counterX++;
			}
			instantiate = false;
		}

		// draw all four shields
		shield(50, 400, shield1);
		shield(100, 400, shield2);
		shield(150, 400, shield3);
		shield(200, 400, shield4);

		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(alienAlive[i][j] == 0)  // alive
				{
					alienErase(alienXYZ[i][j][0], alienXYZ[i][j][1]); // erase the alien in preperation to be redrawn
				}
				if (alienAlive[i][j] == -1)// just got shot
				{
					alienErase(alienXYZ[i][j][0], alienXYZ[i][j][1]); // erase the alien in preperation to draw an explosion
				}
				if (alienAlive[i][j] == -2) // got shot one turn ago
				{
					eraseExplosion(alienXYZ[i][j][0], alienXYZ[i][j][1]); // erase the explosion
				}
			}
		}

		counterX = 2;
		for(int i = 0; i < 12; i++)
		{
			for(int j =0; j < 3; j++)
			{
				alienXYZ[i][j][0] = x1+(15*counterX);
			}
			for(int j =0; j < 3; j++)
			{
				alienXYZ[i][j][1] = y1+(15*j);
			}
			counterX++;
		}

		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(alienAlive[i][j] == 2) // instantiate
				{
					alien(alienXYZ[i][j][0], alienXYZ[i][j][1]);
					counter++;
					alienAlive[i][j] = 0;
				}
				if(alienAlive[i][j] == 0) // alive
				{
					alien(alienXYZ[i][j][0], alienXYZ[i][j][1]);
				}
				if (alienAlive[i][j] == -2)  // dead
				{
					alienAlive[i][j] = 1;    // dead forever
				}
				if(alienAlive[i][j] == -1)  // just got shot
				{
					explosion(alienXYZ[i][j][0], alienXYZ[i][j][1]);
					alienAlive[i][j] = -2;
				}
				if(alienAlive[i][j] == -3) // not used
				{
					alienAlive[i][j] = 2;
				}
			}
		}
	}

	/**Responsible for drawing the tanks in the upper right hand corner representing the users lives.
	 * 
	 */
	public void drawLives()
	{
		if (lives == 3)
		{
			tank(245,30);
			tank(260,30);
			tank(275,30);
		}

		else if(lives == 2)
		{
			tank(245,30);
			tank(260,30);
			tankErase(275,30);
		}
		else if(lives == 1)
		{
			tank(245,30);
			tankErase(260,30);
		}
		else if(lives == 0)
		{
			tankErase(245,30);
		}
	}

	/**Draws the score based upon the <code>Integer</code> provided.
	 * 
	 * @param score		the score to be drawn. A value of -100 erases the last drawn score
	 */
	public void drawScore(int score)
	{
		String color = "000000000";
		int leftSide = 65, width = 10;
		if (score == -100) // sets the color to black and paints over the last number to be drawn
		{
			score = preScore;
			color = "000000000";
		}
		else
		{
			color = "255255255"; // sets the color to white
		}

		String scoreS = Integer.toString(score);

		for(int i = 0; i < scoreS.length(); i++)
		{
			leftSide+=2;
			if(scoreS.charAt(i) == '0')
			{
				for (int x=leftSide; x<=leftSide+width; x++)
				{
					width = 8;
					buf.setRGB(x, 30, Integer.parseInt(color));
					buf.setRGB(x, 29, Integer.parseInt(color));
					buf.setRGB(x, 21, Integer.parseInt(color));
					buf.setRGB(x, 20, Integer.parseInt(color));
					if (x == leftSide || x == leftSide+width) 
					{
						buf.setRGB(x, 28, Integer.parseInt(color));
						buf.setRGB(x, 27, Integer.parseInt(color));
						buf.setRGB(x, 26, Integer.parseInt(color));
						buf.setRGB(x, 25, Integer.parseInt(color));
						buf.setRGB(x, 24, Integer.parseInt(color));
						buf.setRGB(x, 23, Integer.parseInt(color));
						buf.setRGB(x, 22, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '1')
			{
				width = 1;
				for (int p=leftSide; p<=leftSide+width; p++)
				{
					for (int j=20; j<=30; j++)
					{
						buf.setRGB(p, j, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '2') // Draw a two
			{
				width = 8;
				for (int j=leftSide; j<leftSide+width+1; j++)
				{
					if (j == leftSide+width )
					{
						buf.setRGB(j, 22, Integer.parseInt(color));
						buf.setRGB(j, 23, Integer.parseInt(color));
					}
					if (j == leftSide+width-1)
					{
						buf.setRGB(j, 22, Integer.parseInt(color));
						buf.setRGB(j, 23, Integer.parseInt(color));
						buf.setRGB(j, 24, Integer.parseInt(color));
					}
					if (j == leftSide+width-2)
					{
						buf.setRGB(j, 24, Integer.parseInt(color));
						buf.setRGB(j, 25, Integer.parseInt(color));
					}
					if (j == leftSide+width-3)
					{
						buf.setRGB(j, 25, Integer.parseInt(color));
						buf.setRGB(j, 26, Integer.parseInt(color));
					}
					if (j == leftSide+width-4)
					{
						buf.setRGB(j, 26, Integer.parseInt(color));
						buf.setRGB(j, 27, Integer.parseInt(color));
					}
					if (j == leftSide+width-5)
					{
						buf.setRGB(j, 27, Integer.parseInt(color));
						buf.setRGB(j, 28, Integer.parseInt(color));
					}
					if (j == leftSide+width-6)
					{
						buf.setRGB(j, 28, Integer.parseInt(color));
					}
					if (j != leftSide)
					{
						if (j != leftSide+width)
						{
							buf.setRGB(j, 20, Integer.parseInt(color));
						}
						buf.setRGB(j, 21, Integer.parseInt(color));
						buf.setRGB(j, 29, Integer.parseInt(color));
					}

					buf.setRGB(j, 30, Integer.parseInt(color));
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '3') // Draw a three
			{
				width = 8;
				for (int p=leftSide; p<leftSide+width+1; p++)
				{
					buf.setRGB(p, 30,Integer.parseInt(color));
					buf.setRGB(p, 29,Integer.parseInt(color));
					buf.setRGB(p, 25,Integer.parseInt(color));
					buf.setRGB(p, 21,Integer.parseInt(color));
					buf.setRGB(p, 20,Integer.parseInt(color));
					if (p == leftSide+width) 
					{
						buf.setRGB(p, 28, Integer.parseInt(color));
						buf.setRGB(p, 27, Integer.parseInt(color));
						buf.setRGB(p, 26, Integer.parseInt(color));
						buf.setRGB(p, 24, Integer.parseInt(color));
						buf.setRGB(p, 23, Integer.parseInt(color));
						buf.setRGB(p, 22, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '4') // draw a four
			{
				width = 8;
				for (int p=leftSide; p<=leftSide+width; p++)
				{
					buf.setRGB(p, 25, Integer.parseInt(color));
					if (p == leftSide || p == leftSide+width) 
					{
						buf.setRGB(p, 24, Integer.parseInt(color));
						buf.setRGB(p, 23, Integer.parseInt(color));
						buf.setRGB(p, 22, Integer.parseInt(color));
						buf.setRGB(p, 21, Integer.parseInt(color));
						buf.setRGB(p, 20, Integer.parseInt(color));
						if (p == leftSide+width)
						{
							buf.setRGB(p, 30, Integer.parseInt(color));
							buf.setRGB(p, 29, Integer.parseInt(color));
							buf.setRGB(p, 28, Integer.parseInt(color));
							buf.setRGB(p, 27, Integer.parseInt(color));
							buf.setRGB(p, 26, Integer.parseInt(color));
						}
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '5') // draw a six
			{
				width = 8;
				for (int p=leftSide; p<=leftSide+width; p++)
				{
					buf.setRGB(p, 20, Integer.parseInt(color));
					buf.setRGB(p, 21, Integer.parseInt(color));
					buf.setRGB(p, 25, Integer.parseInt(color));
					buf.setRGB(p, 29, Integer.parseInt(color));
					buf.setRGB(p, 30, Integer.parseInt(color));
					if (p == leftSide)
					{
						buf.setRGB(p, 22, Integer.parseInt(color));
						buf.setRGB(p, 23, Integer.parseInt(color));
						buf.setRGB(p, 24, Integer.parseInt(color));
					}
					else if (p == leftSide+width)
					{
						buf.setRGB(p, 28, Integer.parseInt(color));
						buf.setRGB(p, 27, Integer.parseInt(color));
						buf.setRGB(p, 26, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '6') // draw a six
			{
				width = 8;
				for (int p=leftSide; p<leftSide+width+1; p++)
				{
					buf.setRGB(p, 30,Integer.parseInt(color));
					buf.setRGB(p, 29,Integer.parseInt(color));
					buf.setRGB(p, 25,Integer.parseInt(color));
					buf.setRGB(p, 21,Integer.parseInt(color));
					buf.setRGB(p, 20,Integer.parseInt(color));
					if (p == leftSide || p == leftSide+width) 
					{
						if (p != leftSide+width)
						{
							buf.setRGB(p, 24, Integer.parseInt(color));
							buf.setRGB(p, 23, Integer.parseInt(color));
							buf.setRGB(p, 22, Integer.parseInt(color));
						}
						buf.setRGB(p, 28, Integer.parseInt(color));
						buf.setRGB(p, 27, Integer.parseInt(color));
						buf.setRGB(p, 26, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '7') // draw a seven
			{
				width = 8;
				for (int p=leftSide; p<leftSide+width+1; p++)
				{
					buf.setRGB(p, 21,Integer.parseInt(color));
					buf.setRGB(p, 20,Integer.parseInt(color));
					if (p == leftSide+width)
					{
						buf.setRGB(p, 23, Integer.parseInt(color));
						buf.setRGB(p, 22, Integer.parseInt(color));
					}
					if (p == leftSide+width-1)
					{
						buf.setRGB(p, 23, Integer.parseInt(color));
						buf.setRGB(p, 22, Integer.parseInt(color));
						buf.setRGB(p, 24, Integer.parseInt(color));
					}
					if (p == leftSide+width-2)
					{
						buf.setRGB(p, 24, Integer.parseInt(color));
						buf.setRGB(p, 25, Integer.parseInt(color));
					}
					if (p == leftSide+width-3)
					{
						buf.setRGB(p, 25, Integer.parseInt(color));
						buf.setRGB(p, 26, Integer.parseInt(color));
					}
					if (p == leftSide+width-4)
					{
						buf.setRGB(p, 26, Integer.parseInt(color));
						buf.setRGB(p, 27, Integer.parseInt(color));
					}
					if (p == leftSide+width-5)
					{
						buf.setRGB(p, 27, Integer.parseInt(color));
						buf.setRGB(p, 28, Integer.parseInt(color));
					}
					if (p == leftSide+width-6)
					{
						buf.setRGB(p, 28, Integer.parseInt(color));
						buf.setRGB(p, 29, Integer.parseInt(color));
					}
					if (p == leftSide+width-7)
					{
						buf.setRGB(p, 29, Integer.parseInt(color));
						buf.setRGB(p, 30, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '8') // draw an eight
			{
				width = 8;
				for (int p=leftSide; p<leftSide+width+1; p++)
				{
					buf.setRGB(p, 30,Integer.parseInt(color));
					buf.setRGB(p, 29,Integer.parseInt(color));
					buf.setRGB(p, 25,Integer.parseInt(color));
					buf.setRGB(p, 21,Integer.parseInt(color));
					buf.setRGB(p, 20,Integer.parseInt(color));
					if (p == leftSide || p == leftSide+width) 
					{
						buf.setRGB(p, 28, Integer.parseInt(color));
						buf.setRGB(p, 27, Integer.parseInt(color));
						buf.setRGB(p, 26, Integer.parseInt(color));
						buf.setRGB(p, 24, Integer.parseInt(color));
						buf.setRGB(p, 23, Integer.parseInt(color));
						buf.setRGB(p, 22, Integer.parseInt(color));
					}
				}
				leftSide += width+1;
			}
			else if(scoreS.charAt(i) == '9') // draw a nine
			{
				width = 8;
				for (int p=leftSide; p<leftSide+width+1; p++)
				{
					buf.setRGB(p, 30,Integer.parseInt(color));
					buf.setRGB(p, 29,Integer.parseInt(color));
					buf.setRGB(p, 25,Integer.parseInt(color));
					buf.setRGB(p, 21,Integer.parseInt(color));
					buf.setRGB(p, 20,Integer.parseInt(color));
					if (p == leftSide)
					{
						buf.setRGB(p, 22,Integer.parseInt(color));
						buf.setRGB(p, 23,Integer.parseInt(color));
						buf.setRGB(p, 24,Integer.parseInt(color));
					}
					if (p == leftSide+width)
					{
						for (int j=20; j<=30; j++)
						{
							buf.setRGB(p, j,Integer.parseInt(color));
						}
					}
				}

				leftSide += width+1;
			}
		}
		preScore = score;
	}

	/**Draws the word 'SCORE:' on the upper left side of the screen.
	 * 
	 */
	public void drawScore()
	{
		for(int i = 5; i < 14; i++)
		{
			buf.setRGB(i, 30,255255255);
			buf.setRGB(i, 29,255255255);
			buf.setRGB(i, 21,255255255);
			buf.setRGB(i, 20,255255255);
			if(i==5)
			{
				buf.setRGB(i, 22, 255255255);
			}
			if(i==6)
			{
				buf.setRGB(i, 22,255255255);
				buf.setRGB(i, 23,255255255);
			}
			if(i==7)
			{
				buf.setRGB(i, 23,255255255);
				buf.setRGB(i, 24,255255255);
			}
			if(i==8)
			{
				buf.setRGB(i, 24,255255255);
				buf.setRGB(i, 25,255255255);
			}
			if(i==9)
			{
				buf.setRGB(i, 25,255255255);
				buf.setRGB(i, 26,255255255);
			}
			if(i==11)
			{
				buf.setRGB(i, 27,255255255);
				buf.setRGB(i, 28,255255255);
			}
			if(i==12)
			{
				buf.setRGB(i, 28,255255255);
				buf.setRGB(i, 29,255255255);
			}
			if(i==10)
			{
				buf.setRGB(i, 26,255255255);
				buf.setRGB(i, 27,255255255);
			}
			if(i==13)
			{
				buf.setRGB(i, 29,255255255);
				buf.setRGB(i,30,255255255);
			}
			if(i==14)
			{
				buf.setRGB(i, 30,255255255);
			}
		}

		for(int i = 16; i < 26; i++)
		{
			buf.setRGB(i, 30,255255255);
			buf.setRGB(i, 29,255255255);
			buf.setRGB(i, 21,255255255);
			buf.setRGB(i, 20,255255255);
		}
		for(int i = 30; i > 20; i--)
		{
			buf.setRGB(16, i, 255255255);
		}
		for(int i = 30; i > 20; i--)
		{
			buf.setRGB(27, i, 255255255);
		}
		for(int i = 30; i > 20; i--)
		{
			buf.setRGB(36, i, 255255255);
		}
		for(int i = 27; i < 37; i++)
		{
			buf.setRGB(i, 30,255255255);
			buf.setRGB(i, 29,255255255);
			buf.setRGB(i, 21,255255255);
			buf.setRGB(i, 20,255255255);
		}
		for(int i = 30; i > 20; i--)
		{
			buf.setRGB(38, i, 255255255);
		}
		for(int i = 20; i < 26; i++)
		{
			buf.setRGB(47, i, 255255255);
		}
		for(int i = 38; i < 48; i++)
		{
			buf.setRGB(i, 21,255255255);
			buf.setRGB(i, 20,255255255);
			buf.setRGB(i, 25,255255255);
		}
		for(int i = 42; i < 48; i++)
		{
			if(i == 42)
			{
				buf.setRGB(i, 25, 255255255);
				buf.setRGB(i, 26, 255255255);
			}
			if(i ==43)
			{
				buf.setRGB(i, 26, 255255255);
				buf.setRGB(i, 27, 255255255);
			}
			if(i == 44)
			{
				buf.setRGB(i, 27, 255255255);
				buf.setRGB(i, 28, 255255255);
			}
			if(i == 45)
			{
				buf.setRGB(i, 28, 255255255);
				buf.setRGB(i, 29, 255255255);
			}
			if(i ==46)
			{
				buf.setRGB(i, 29, 255255255);
				buf.setRGB(i, 30, 255255255);
			}
			if(i ==47)
			{
				buf.setRGB(i, 30, 255255255);
			}					
		}
		for(int i = 49; i < 58; i++)
		{
			buf.setRGB(i, 30,255255255);
			buf.setRGB(i, 29,255255255);
			buf.setRGB(i, 25,255255255);
			buf.setRGB(i, 21,255255255);
			buf.setRGB(i, 20,255255255);
		}
		for(int i = 30; i > 20; i--)
		{
			buf.setRGB(49, i, 255255255);
		}
		for(int i = 61; i < 63; i++)
		{
			buf.setRGB(i, 30,255255255);
			buf.setRGB(i, 29,255255255);
			buf.setRGB(i, 21,255255255);
			buf.setRGB(i, 20,255255255);
		}
	}


	/**Erases a previously drawn explosion.
	 * @param x1	<code>x</code> coordinate of the top left corner of the explosion to be erased
	 * @param y1	<code>y</code> coordinate of the top left corner of the explosion to be erased
	 * 
	 * @see explosion(int, int)
	 */
	public void eraseExplosion(int x1, int y1)
	{
		buf.setRGB(x1+2, y1, 0000000000);
		buf.setRGB(x1+5, y1, 0000000000);
		buf.setRGB(x1+9, y1, 0000000000);
		buf.setRGB(x1+3, y1-1, 0000000000);
		buf.setRGB(x1+5, y1-1, 0000000000);
		buf.setRGB(x1+8, y1-1, 0000000000);
		buf.setRGB(x1+4, y1-2, 0000000000);
		buf.setRGB(x1+5, y1-2, 0000000000);
		buf.setRGB(x1+7, y1-2, 0000000000);
		buf.setRGB(x1+5, y1-3, 0000000000);
		buf.setRGB(x1+6, y1-3, 0000000000);
		buf.setRGB(x1+7, y1-3, 0000000000);
		buf.setRGB(x1+8, y1-3, 0000000000);
		buf.setRGB(x1+9, y1-3, 0000000000);
		buf.setRGB(x1+2, y1-4, 0000000000);
		buf.setRGB(x1+3, y1-4, 0000000000);
		buf.setRGB(x1+4, y1-4, 0000000000);
		buf.setRGB(x1+5, y1-4, 0000000000);
		buf.setRGB(x1+6, y1-4, 0000000000);
		buf.setRGB(x1+4, y1-5, 0000000000);
		buf.setRGB(x1+6, y1-5, 0000000000);
		buf.setRGB(x1+7, y1-5, 0000000000);
		buf.setRGB(x1+3, y1-6, 0000000000);
		buf.setRGB(x1+6, y1-6, 0000000000);
		buf.setRGB(x1+8, y1-6, 0000000000);
		buf.setRGB(x1+2, y1-7, 0000000000);
		buf.setRGB(x1+6, y1-7, 0000000000);
		buf.setRGB(x1+9, y1-7, 0000000000);
	}

	/**Draws and explosion to represent the destruction of either a tank or an alien.
	 * 
	 * @param x1	<code>x</code> coordinate of the top left corner of where the explosion will be drawn
	 * @param y1	<code>y</code> coordinate of the top left corner of where the explosion will be drawn
	 */
	public void explosion(int x1, int y1)
	{
		buf.setRGB(x1+2, y1, 225225000);
		buf.setRGB(x1+5, y1, 225225000);
		buf.setRGB(x1+9, y1, 225225000);
		buf.setRGB(x1+3, y1-1, 225225000);
		buf.setRGB(x1+5, y1-1, 225225000);
		buf.setRGB(x1+8, y1-1, 225225000);
		buf.setRGB(x1+4, y1-2, 225225000);
		buf.setRGB(x1+5, y1-2, 225225000);
		buf.setRGB(x1+7, y1-2, 225225000);
		buf.setRGB(x1+5, y1-3, 225225000);
		buf.setRGB(x1+6, y1-3, 225225000);
		buf.setRGB(x1+7, y1-3, 225225000);
		buf.setRGB(x1+8, y1-3, 225225000);
		buf.setRGB(x1+9, y1-3, 225225000);
		buf.setRGB(x1+2, y1-4, 225225000);
		buf.setRGB(x1+3, y1-4, 225225000);
		buf.setRGB(x1+4, y1-4, 225225000);
		buf.setRGB(x1+5, y1-4, 225225000);
		buf.setRGB(x1+6, y1-4, 225225000);
		buf.setRGB(x1+4, y1-5, 225225000);
		buf.setRGB(x1+6, y1-5, 225225000);
		buf.setRGB(x1+7, y1-5, 225225000);
		buf.setRGB(x1+3, y1-6, 225225000);
		buf.setRGB(x1+6, y1-6, 225225000);
		buf.setRGB(x1+8, y1-6, 225225000);
		buf.setRGB(x1+2, y1-7, 225225000);
		buf.setRGB(x1+6, y1-7, 225225000);
		buf.setRGB(x1+9, y1-7, 225225000);
	}

	/**Pauses the thread responsible for moving the tank for a period of time while handling any exceptions
	 * 
	 * @param pauseTime		The time in milliseconds to pause
	 */
	public void pauseThread(int pauseTime)
	{
		try
		{
			Thread.sleep(pauseTime);
		}
		catch(Exception ex)
		{
			System.err.println("Could not pause. Line: 1024");
		}
	}

	/**
	 * @param x1
	 * @param y1
	 * @param hits
	 */
	public void shield(int x1, int y1, int hits)
	{	
		if (hits == 0)
		{

			for (int i=y1; i>y1-20; i--)
			{
				buf.setRGB(x1+4, y1-15, 000000255);
				buf.setRGB(x1+20, y1-15, 000000255);
				buf.setRGB(x1+4, y1-16, 000000255);
				buf.setRGB(x1+20, y1-16, 000000255);
				buf.setRGB(x1+3, y1-15, 000000255);
				buf.setRGB(x1+21, y1-15, 000000255);
				buf.setRGB(x1+4, y1-17, 000000255);
				buf.setRGB(x1+20, y1-17, 000000255);
				buf.setRGB(x1+3, y1-16, 000000255);
				buf.setRGB(x1+21, y1-16, 000000255);
				buf.setRGB(x1+2, y1-15, 000000255);
				buf.setRGB(x1+22, y1-15, 000000255);
				buf.setRGB(x1+1, y1-14, 000000255);
				buf.setRGB(x1+23, y1-14, 000000255);
				buf.setRGB(x1+4, y1-18, 000000255);
				buf.setRGB(x1+20, y1-18, 000000255);
				buf.setRGB(x1+3, y1-17, 000000255);
				buf.setRGB(x1+21, y1-17, 000000255);
				buf.setRGB(x1+2, y1-16, 000000255);
				buf.setRGB(x1+22, y1-16, 000000255);
				buf.setRGB(x1+1, y1-15, 000000255);
				buf.setRGB(x1+23, y1-15, 000000255);
				buf.setRGB(x1, y1-14, 000000255);
				buf.setRGB(x1+24, y1-14, 000000255);
				if (i > y1-15)
				{
					for (int j=x1; j<x1+5; j++)
					{
						buf.setRGB(j, i, 000000255);
					}
				}

				if (i<y1-7 && i>y1-20)
				{
					for (int j=x1+5; j<x1+20; j++)
					{
						buf.setRGB(j, i, 000000255);
					}
				}

				if(i > y1-15)
				{
					for (int j=x1+20; j<x1+25; j++)
					{
						buf.setRGB(j, i, 000000255);
					}
				}
			}
		}
		if (hits == 1) // if the number of hits on the shield is 1 randomly erase 25% of the shield
		{
			eraseShield(x1, y1);
			for (int i=y1; i>y1-20; i--) // randomly erases the beveled corners of the shield
			{
				randomErase(x1+4, y1-15, 25);
				randomErase(x1+20, y1-15, 25);
				randomErase(x1+4, y1-16, 25);
				randomErase(x1+20, y1-16, 25);
				randomErase(x1+3, y1-15, 25);
				randomErase(x1+21, y1-15, 25);
				randomErase(x1+4, y1-17, 25);
				randomErase(x1+20, y1-17, 25);
				randomErase(x1+3, y1-16, 25);
				randomErase(x1+21, y1-16, 25);
				randomErase(x1+2, y1-15, 25);
				randomErase(x1+22, y1-15, 25);
				randomErase(x1+1, y1-14, 25);
				randomErase(x1+23, y1-14, 25);
				randomErase(x1+4, y1-18, 25);
				randomErase(x1+20, y1-18, 25);
				randomErase(x1+3, y1-17, 25);
				randomErase(x1+21, y1-17, 25);
				randomErase(x1+2, y1-16, 25);
				randomErase(x1+22, y1-16, 25);
				randomErase(x1+1, y1-15, 25);
				randomErase(x1+23, y1-15, 25);
				randomErase(x1, y1-14, 25);
				randomErase(x1+24, y1-14, 25);
				if (i > y1-15) // erases the left side of the shield
				{
					for (int j=x1; j<x1+5; j++)
					{
						randomErase(j, i, 25);
					}
				}

				if (i<y1-7 && i>y1-20)  // center square section
				{
					for (int j=x1+5; j<x1+20; j++)
					{
						randomErase(j, i, 25);
					}
				}

				if(i > y1-15) // erases the right side
				{
					for (int j=x1+20; j<x1+25; j++)
					{
						randomErase(j, i, 25);
					}
				}
			}
		}
		else if (hits == 2) // if the number of hits on the shield is 2 randomly erase 50% of the shield
		{
			eraseShield(x1, y1);
			for (int i=y1; i>y1-20; i--)
			{
				randomErase(x1+4, y1-15, 50);
				randomErase(x1+20, y1-15, 50);
				randomErase(x1+4, y1-16, 50);
				randomErase(x1+20, y1-16, 50);
				randomErase(x1+3, y1-15, 50);
				randomErase(x1+21, y1-15, 50);
				randomErase(x1+4, y1-17, 50);
				randomErase(x1+20, y1-17, 50);
				randomErase(x1+3, y1-16, 50);
				randomErase(x1+21, y1-16, 50);
				randomErase(x1+2, y1-15, 50);
				randomErase(x1+22, y1-15, 50);
				randomErase(x1+1, y1-14, 50);
				randomErase(x1+23, y1-14, 50);
				randomErase(x1+4, y1-18, 50);
				randomErase(x1+20, y1-18, 50);
				randomErase(x1+3, y1-17, 50);
				randomErase(x1+21, y1-17, 50);
				randomErase(x1+2, y1-16, 50);
				randomErase(x1+22, y1-16, 50);
				randomErase(x1+1, y1-15, 50);
				randomErase(x1+23, y1-15, 50);
				randomErase(x1, y1-14, 50);
				randomErase(x1+24, y1-14, 50);
				if (i > y1-15)
				{
					for (int j=x1; j<x1+5; j++)
					{
						randomErase(j, i, 50);
					}
				}

				if (i<y1-7 && i>y1-20)
				{
					for (int j=x1+5; j<x1+20; j++)
					{
						randomErase(j, i, 50);
					}
				}

				if(i > y1-15)
				{
					for (int j=x1+20; j<x1+25; j++)
					{
						randomErase(j, i, 50);
					}
				}
			}
		}
		else if (hits == 3) // if the number of hits on the shield is 3 randomly erase 75% of the shield
		{
			eraseShield(x1, y1);
			for (int i=y1; i>y1-20; i--)
			{
				randomErase(x1+4, y1-15, 75);
				randomErase(x1+20, y1-15, 75);
				randomErase(x1+4, y1-16, 75);
				randomErase(x1+20, y1-16, 75);
				randomErase(x1+3, y1-15, 75);
				randomErase(x1+21, y1-15, 75);
				randomErase(x1+4, y1-17, 75);
				randomErase(x1+20, y1-17, 75);
				randomErase(x1+3, y1-16, 75);
				randomErase(x1+21, y1-16, 75);
				randomErase(x1+2, y1-15, 75);
				randomErase(x1+22, y1-15, 75);
				randomErase(x1+1, y1-14, 75);
				randomErase(x1+23, y1-14, 75);
				randomErase(x1+4, y1-18, 75);
				randomErase(x1+20, y1-18, 75);
				randomErase(x1+3, y1-17, 75);
				randomErase(x1+21, y1-17, 75);
				randomErase(x1+2, y1-16, 75);
				randomErase(x1+22, y1-16, 75);
				randomErase(x1+1, y1-15, 75);
				randomErase(x1+23, y1-15, 75);
				randomErase(x1, y1-14, 75);
				randomErase(x1+24, y1-14, 75);
				if (i > y1-15)
				{
					for (int j=x1; j<x1+5; j++)
					{
						randomErase(j, i, 75);
					}
				}

				if (i<y1-7 && i>y1-20)
				{
					for (int j=x1+5; j<x1+20; j++)
					{
						randomErase(j, i, 75);
					}
				}

				if(i > y1-15)
				{
					for (int j=x1+20; j<x1+25; j++)
					{
						randomErase(j, i, 75);
					}
				}
			}
		}
		else if (hits == 4) // if there have been 4 hits on the shield erase the whole thing
		{
			eraseShield(x1, y1);
		}
	}

	/**
	 * @param x1
	 * @param y1
	 */
	public void eraseShield(int x1, int y1)
	{
		for (int i=y1; i>y1-20; i--) // beveled corners
		{
			buf.setRGB(x1+4, y1-15, 000000000);
			buf.setRGB(x1+20, y1-15, 000000000);
			buf.setRGB(x1+4, y1-16, 000000000);
			buf.setRGB(x1+20, y1-16, 000000000);
			buf.setRGB(x1+3, y1-15, 000000000);
			buf.setRGB(x1+21, y1-15, 000000000);
			buf.setRGB(x1+4, y1-17, 000000000);
			buf.setRGB(x1+20, y1-17, 000000000);
			buf.setRGB(x1+3, y1-16, 000000000);
			buf.setRGB(x1+21, y1-16, 000000000);
			buf.setRGB(x1+2, y1-15, 000000000);
			buf.setRGB(x1+22, y1-15, 000000000);
			buf.setRGB(x1+1, y1-14, 000000000);
			buf.setRGB(x1+23, y1-14, 000000000);
			buf.setRGB(x1+4, y1-18, 000000000);
			buf.setRGB(x1+20, y1-18, 000000000);
			buf.setRGB(x1+3, y1-17, 000000000);
			buf.setRGB(x1+21, y1-17, 000000000);
			buf.setRGB(x1+2, y1-16, 000000000);
			buf.setRGB(x1+22, y1-16, 000000000);
			buf.setRGB(x1+1, y1-15, 000000000);
			buf.setRGB(x1+23, y1-15, 000000000);
			buf.setRGB(x1, y1-14, 000000000);
			buf.setRGB(x1+24, y1-14, 000000000);
			if (i > y1-15) // left side
			{
				for (int j=x1; j<x1+5; j++)
				{
					buf.setRGB(j, i, 000000000);
				}
			}

			if (i<y1-7 && i>y1-20) // center
			{
				for (int j=x1+5; j<x1+20; j++)
				{
					buf.setRGB(j, i, 000000000);
				}
			}

			if(i > y1-15) // right side
			{
				for (int j=x1+20; j<x1+25; j++)
				{
					buf.setRGB(j, i, 000000000);
				}
			}
		}
	}

	/**
	 * @param x1		<code>x</code> coordinate of the pixel from the top of the canvas
	 * @param y1		<code>y</code> coordinate of the pixel from the left side of canvas
	 * @param percent	percent chance that the pixel will be erased
	 */
	public void randomErase(int x1, int y1, int percent)
	{
		double switcher = Math.random()*100;

		if (switcher <= percent)
		{
			// don't paint
		}
		else
		{
			buf.setRGB(x1, y1, 000000255);
		}
	}

	// controls the movement of the aliens
	public void run()
	{
		board.move();
	}

	/**
	 * @param list
	 * @param userList
	 */
	public void sort(ArrayList<Integer> list,ArrayList<String> userList )
	{
		int temp;
		String tempString;

		for(int j = 0; j < list.size(); j++)
		{
			for(int i = 0; i < list.size()-1; i++)
			{
				if(list.get(i) < list.get(i+1))
				{
					temp = list.get(i);
					tempString = userList.get(i);
					list.set(i,list.get(i+1));
					userList.set(i,userList.get(i+1));
					list.set(i+1,temp);
					userList.set(i+1,tempString);
				}
			}
		}
	}

	/**
	 * @param x1	<code>x</code> coordinate of the left side of the tank
	 * @param y1	<code>y</code> coordinate of the top of the tank
	 */
	public void tank(int x1, int y1)
	{
		for(int i = x1; i <x1+13;i++)
		{
			for(int j = y1-5; j < y1; j++)
			{
				buf.setRGB(i, j, 255254255);
			}
		}

		for(int i = x1+2; i <x1+11; i++)
		{
			for(int j = y1-6; j <y1-4; j++)
			{
				buf.setRGB(i, j, 255254255);
			}
		}
		for(int i = x1+5; i <x1+8; i++)
		{
			for(int j = y1-9; j <y1-6; j++)
			{
				buf.setRGB(i, j, 255254255);
			}
		}
		board.repaint();
	}

	/**
	 * @param x1
	 * @param y1
	 */
	public void tankErase(int x1, int y1)
	{
		for(int i = x1; i <x1+13;i++)
		{
			for(int j = y1-5; j < y1; j++)
			{
				buf.setRGB(i, j, 000000000);
			}
		}

		for(int i = x1+2; i <x1+11; i++)
		{
			for(int j = y1-6; j <y1-4; j++)
			{
				buf.setRGB(i, j, 000000000);
			}
		}
		for(int i = x1+5; i <x1+8; i++)
		{
			for(int j = y1-9; j <y1-6; j++)
			{
				buf.setRGB(i, j, 000000000);
			}
		}
		board.repaint();
	}

	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		SpaceInvaders game = new SpaceInvaders();
	}

	/**
	 * @author Nick
	 *
	 */
	public class Board extends JPanel implements KeyListener, Runnable
	{
		Thread f;
		Timer shootTime = new Timer();

		/**
		 * 
		 */
		public Board()
		{
			try
			{
				buf = ImageIO.read(new File("Background.png"));
			}
			catch(IOException ioe)
			{
				System.err.println("Could not read image. Line: 1417");
			}
			this.addKeyListener(this);
			this.setFocusable(true);
		}

		/* (non-Javadoc)
		 * Overrides the <code>paintComponent</code> method in <code>JPanel</code> to draw directly onto the canvas.
		 * 
		 * @param g	<code>Graphics</code> object to be painted on
		 * 
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);
			Graphics g2d = (Graphics2D)g;
			g2d.drawImage(buf,0,0,null);
		}

		public void keyPressed(KeyEvent vk)
		{
			if(vk.getKeyCode() == KeyEvent.VK_RIGHT) // if the right arroww key is pressed
			{
				if(tankX+5<270)
				{
					tankErase(tankX,tankY);
					tankX+=5;
					tank(tankX,tankY);
				}
			}
			else if(vk.getKeyCode() == KeyEvent.VK_LEFT) // if the left arrow key is pressed
			{
				if(tankX-5>0)
				{
					tankErase(tankX,tankY);
					tankX-=5;
					tank(tankX,tankY);
				}
			}
			else if(vk.getKeyCode() == KeyEvent.VK_D) // if the 'D' key is pressed
			{
				dataBase();
				shield1 = 4;
				shield2 = 4;
				shield3 = 4;
				shield4 = 4;
			}
			else if(vk.getKeyCode() == KeyEvent.VK_SPACE) // if the space bar is pressed
			{
				bulletX1 = tankX + 6;
				bulletY1 = tankY - 10;
				f = new Thread(this);
				f.start();
			}
		}

		public void keyReleased(KeyEvent vk){}

		public void keyTyped(KeyEvent vk){}

		/**Moves aliens back and forth accross board and down the screen at a fixed rate depending on the level of the user.
		 * Also begins the timer that schedules when the aliens shoot.
		 */
		public void move()
		{
			shootTime.scheduleAtFixedRate(new AlienShoot(),0,100);
			boolean don = false;
			while (!don)  // if an alien is killed subtract 1 from the counter
			{				
				while (alienXYZ[11][2][0] < 260)  // move right while the rightmost alien has room
				{
					x+=10;				// move aliens 10 pixels to the right
					drawAliens(x, y);	// reposition aliens
					board.repaint();	// repaint board with new alien positions
					pause(speed-188);//812.5
					don = checkWin();
				}
				y+=20; 				 // move all aliens 20 pixels down
				drawAliens(x, y);
				board.repaint();
				pause(speed);//1000
				don = checkWin();

				while (alienXYZ[0][0][0]+1 > 11)  // left
				{
					x-=10;
					drawAliens(x, y);
					board.repaint();
					pause(speed-188);
					don = checkWin();
				}
				y+=20;
				drawAliens(x, y);
				board.repaint();
				pause(speed);
				don = checkWin();
			}
		}

		/**
		 * Sets the users score based on the level that they are on and the number of remaining aliens alive.
		 * Also checks to see if the user has lost all of their lives.
		 * @return a boolean representing whether or not the user is out of lives
		 */
		public boolean checkWin()
		{
			int prescore = 0;
			score = (level-1)*3600;
			for(int i=0; i<alienAlive.length; i++)
			{
				for(int j=0; j<alienAlive[0].length; j++)
				{
					if(alienAlive[i][j] == 1)
					{
						prescore+=100;  // adds 100 for every live alien
					}
				}
			}
			score += prescore;
			drawScore(-100); // erases the previous score
			drawScore(score); // draws the current score
			boolean d = false;
			if(speed <= 200)
			{
				JOptionPane.showMessageDialog(null, "You beat the game.");
				dataBase();
				gameBoard.dispose();
			}
			if(alienXYZ[0][2][1] >= 380 && c)
			{
				lives--;
				drawLives();
				tankErase(tankX, tankY);
				explosion(tankX, tankY);
				x=15; y=60;
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					System.err.println("Could not pause. Line: 1542");
				}
				eraseExplosion(tankX,tankY);
				tank(tankX,tankY);
				x = 15;
				y = 60;
				drawAliens(x,y);
			}
			if(score == 3600*level && c)
			{
				drawAliens(x,y);
				x=15;
				y=60;
				speed-=25;
				for(int i = 0; i < 12; i++)
				{
					for(int j = 0; j <3; j++)
					{
						alienAlive[i][j] = 2;
					}
				}
				level++;
				speeds.add(new JMenuItem("Level" + Integer.toString(score/3600+1)));
				changeSpeed.add(speeds.get(level-1));
				speeds.get(level-1).addActionListener(SpaceInvaders.this);
				drawAliens(x,y);
				move();

				c = false;
				d = true;
			}

			else if(c&& lives == 0)
			{
				lives=3; 
				JOptionPane.showMessageDialog(null, "You lose");
				c = false;
				d = true;
				playAgain();
			}
			return d;
		}

		/**<code>playAgain</code> triggers the database method to add their score while at the same time prompting the user 
		 * to see if they want to play again.
		 * 
		 */
		public void playAgain()
		{
			dataBase();
			int p = JOptionPane.showConfirmDialog(null, "You made it to level " + level + ".\nDo you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);

			if(p == JOptionPane.YES_OPTION)
			{
				gameBoard.dispose();
				@SuppressWarnings("unused")
				SpaceInvaders game = new SpaceInvaders();
			}
			else if(p == JOptionPane.NO_OPTION)
			{
				System.exit(0);
			}
		}

		/**Pauses the thread responsible for moving the aliens while handling any exceptions produced by sleeping a thread.
		 * 
		 * @param m		length in milliseconds of the pause
		 */
		public void pause(long m)
		{
			try
			{
				Thread.sleep(m);
			}
			catch (InterruptedException e)
			{
				System.err.println("Could not pause. Line: 1611");
			}
		}		

		/* 
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() 
		{
			boolean hit = false, done = false;
			int bulletX = bulletX1 , bulletY = bulletY1;
			while(bulletY-10> 43 && !hit && !done)
			{
				if(buf.getRGB(bulletX, bulletY-4) == -13180201) // if the pixel in front of the bullet is the color of an alien
				{	
					for(int i = 0; i < alienXYZ.length; i++) //         \
					{												//	 Search the alien array for which alien is in the same spot as the bullet
						for(int j = 0; j < alienXYZ[0].length; j++) //  /
						{
							if(alienXYZ[i][j][0] <= bulletX && bulletX <= alienXYZ[i][j][0]+11) // if the alien is in the same column as the bullet
							{
								if((bulletY <= alienXYZ[i][j][1] && bulletY >= alienXYZ[i][j][1]) || (bulletY-1 <= alienXYZ[i][j][1] && bulletY-1 >= alienXYZ[i][j][1]) || (bulletY-2 <= alienXYZ[i][j][1] && bulletY-2 >= alienXYZ[i][j][1]) || (bulletY-3 <= alienXYZ[i][j][1] && bulletY-3 >= alienXYZ[i][j][1]) || (bulletY-4 <= alienXYZ[i][j][1] && bulletY-4 >= alienXYZ[i][j][1]) || (bulletY-5 <= alienXYZ[i][j][1] && bulletY-5 >= alienXYZ[i][j][1]) || (bulletY-6 <= alienXYZ[i][j][1] && bulletY-6 >= alienXYZ[i][j][1]) || (bulletY-7 <= alienXYZ[i][j][1] && bulletY-7 >= alienXYZ[i][j][1]) || (bulletY-8 <= alienXYZ[i][j][1] && bulletY-8 >= alienXYZ[i][j][1]) || (bulletY-9 <= alienXYZ[i][j][1] && bulletY-9 >= alienXYZ[i][j][1]) || (bulletY-10 <= alienXYZ[i][j][1] && bulletY-10 >= alienXYZ[i][j][1]))
								{ // if the alien is in the same row as the bullet
									hit = true; 
									alienAlive[i][j] = -1; // set the value of the alien to "ready to explode"
									counter--; // decrament the number of aliens left
									bulletErase(bulletX, bulletY); // erase the bullet
								}
							}
						}
					}

				}
				else if(buf.getRGB(bulletX, bulletY-4) == shieldColor) // if the bullet hit a shield
				{
					bulletErase(bulletX,bulletY); // erase the bullet
					if(bulletX > 49 && bulletX < 50+25) // if the bullet hit shield 1
					{
						shield1++; // increment the number of times the shield has been hit
					}
					else if(bulletX > 99 && bulletX < 100+25) // if the bullet hit shield 2
					{
						shield2++; // increment the number of times the shield has been hit
					}
					else if(bulletX > 149 && bulletX < 150+25) // if the bullet hit shield 3
					{
						shield3++; // increment the number of times the shield has been hit
					}
					else if(bulletX > 199 && bulletX < 200+25) // if the bullet hit shield 4
					{
						shield4++; // increment the number of times the shield has been hit
					}
					bulletY= 44; // move the bullet to the top of the screen so it will be destroyed

				}
				else
				{

					if(bulletY <= 65) // if the bullet is at the top of the screen 
					{
						bulletErase(bulletX, bulletY); // erase the bullet
						done = true; // jump out of loop 
					}
					else // move the bullet one pixel forward and redrw it
					{
						bulletErase(bulletX, bulletY);
						bulletY--;
						bullet(bulletX, bulletY);
					}
				}
				board.repaint(); // repaints the board with aliens and bullets redrawn
			}

		}

		/**
		 * @author Nick
		 *
		 */
		public class AlienShoot extends TimerTask
		{
			/* 
			 * @see java.util.TimerTask#run()
			 */
			public void run()
			{
				int randX = 0, bulletY2 = 0, bulletX2 = 0;
				boolean done = false;
				do // search for an alien starting with the bottom row and moving up 
				{
					randX = (int)(Math.random() * 12); // chooses a random column
					if(alienAlive[randX][2] == 0) // if the alien in row 3 (bootom) is alive start a new bullet from that alien
					{
						bulletX2 = alienXYZ[randX][2][0];
						bulletY2 = alienXYZ[randX][2][1];
						done = true;
					}
					else if(alienAlive[randX][1] == 0)// if the alien in row 2 (middle) is alive start a new bullet from that alien
					{
						bulletX2 = alienXYZ[randX][1][0];
						bulletY2 = alienXYZ[randX][1][1];
						done = true;
					}
					else if(alienAlive[randX][0] == 0)// if the alien in row 1 (top) is alive start a new bullet from that alien
					{
						bulletX2 = alienXYZ[randX][0][0];
						bulletY2 = alienXYZ[randX][0][1];
						done = true;
					}
				}while(!done); // continue searching until a bullet has been fired
				done = false;
				while(bulletY2 < 499 ) // move the bullet while it has not hit the bottom of the screen
				{
					if(buf.getRGB(bulletX2, bulletY2+1) == -13181201) // if the bullet hits the tank
					{
						lives--;  			// decrement from lives
						check = 3;			
						bulletErase2(bulletX2, bulletY2); // erase the bullet
						drawLives();		// redr
						tankErase(tankX, tankY);
						explosion(tankX, tankY);
						x=15; y=60;
						pause(1000);
						eraseExplosion(tankX,tankY);
						tank(tankX,tankY);
						x = 15;
						y = 60;
						drawAliens(x,y);
						bulletY2= 500;
					}
					else if(buf.getRGB(bulletX2, bulletY2+1) == shieldColor)
					{
						bulletErase2(bulletX2, bulletY2);
						if(bulletX2 > 49 && bulletX2 < 50+25)
						{
							shield1++;
						}
						else if(bulletX2 > 99 && bulletX2 < 100+25)
						{
							shield2++;
						}
						else if(bulletX2 > 149 && bulletX2 < 150+25)
						{
							shield3++;
						}
						else if(bulletX2 > 199 && bulletX2 < 200+25)
						{
							shield4++;
						}
						bulletY2= 500;
					}
					else
					{
						bulletErase2(bulletX2, bulletY2);
						bulletY2++;
						bullet2(bulletX2, bulletY2);
					}
				}
			}
		}
	}	
}