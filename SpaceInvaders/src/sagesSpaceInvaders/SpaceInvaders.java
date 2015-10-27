package sagesSpaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@SuppressWarnings("serial")
public class SpaceInvaders extends Thread implements ActionListener
{
	int shield1=0, shield2=0,shield3=0, shield4=0, shieldColor = -16777043, level = 1, tankColor = -13181201, score = 0, preScore = 0, x = 15, check = 0, lives = 3, speed = 1000, y = 60, tankX= 125, tankY = 425, bulletX1 = 0, bulletY1 = 0, playAgain = 0, counterX = 2, counter = 0;
	int [][][] alienXYZ = new int [12][3][2];
	int [][] alienAlive = new int [12][3];
	boolean start = false, instantiate = true, beatScore = false, shooting = false, c = true;
	Board board = new Board();
	Rectangle nicksGameRect = new Rectangle(0, 30, 310, 500);
	JFrame gameBoard = new JFrame();
	BufferedImage buf = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
	Date lastBullet = new Date();
	SimpleDateFormat date = new SimpleDateFormat("ss");
	JMenuBar m = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem about = new JMenuItem("About");
	JMenu game = new JMenu("Game");
	JMenuItem newGame = new JMenuItem("New Game");
	JMenu changeSpeed = new JMenu("Change Level");
	ArrayList<JMenuItem>speeds = new ArrayList<JMenuItem>();

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

		drawScore();
		drawLives();
		drawScore(score);
		drawAliens(x,y);
		gameBoard.setTitle("Space Invaders");
		gameBoard.setBounds(nicksGameRect);
		gameBoard.setVisible(true);
		gameBoard.add(board);
		tank(tankX,tankY);
		this.start();
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

		gameBoard.addMouseListener(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent ea)
					{
						System.out.println("X: " + ea.getX() + ", Y: " + ea.getY() + ", RGB: " + buf.getRGB(ea.getX(),ea.getY()));
					}
				}
				);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == exit)
		{
			System.exit(0);
		}
		else if (e.getSource() == about)
		{
			JOptionPane.showMessageDialog(null, "Made by Sage and Nick\nP.6 2012");
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

	public void bullet2(int x1,int y1)
	{
		for(int i = y1-3; i < y1; i++)
		{
			buf.setRGB(x1, i, 255000000);
		}
		buf.setRGB(x1+1, y1-4, 255000000);
		buf.setRGB(x1-1, y1-4, 255000000);
		pauseThread(7);
		board.repaint();
	}

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

public void dataBase()
{
	if(lives==-1)
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:SnakeDataBase";
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
			ArrayList<String> usernameList = new ArrayList<String>();
			ArrayList<Integer> highScoreList = new ArrayList<Integer>();

			ResultSet rs = stmt.executeQuery("SELECT * FROM HighScoreTable");

			while(rs.next())
			{
				usernameList.add(rs.getString("Username"));
				highScoreList.add(rs.getInt("Highscore"));
			}
			for(int i = 0; i < highScoreList.size(); i++)
			{
				if(highScoreList.get(i) < score)
				{
					highScoreList.add(i,score);
					highScoreList.remove(highScoreList.size()-1);
					usernameList.add(i,JOptionPane.showInputDialog("Input your name:  "));
					usernameList.remove(usernameList.size()-1);
					beatScore = true;
					i = highScoreList.size();
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
		}
		catch(Exception p)
		{
			p.printStackTrace();
		}

		playAgain = JOptionPane.showConfirmDialog(null,"Do you want to play again", "Play Again", JOptionPane.YES_NO_OPTION);
		if(playAgain == 0)
		{
			gameBoard.dispose();
			new SpaceInvaders();
		}
		else
		{
			gameBoard.dispose();
			System.exit(0);
		}
	}
}

public void drawAliens(int x1, int y1)
{
	if (instantiate == true)
	{
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
		instantiate = false;
	}

	shield(50, 400, shield1);
	shield(100, 400, shield2);
	shield(150, 400, shield3);
	shield(200, 400, shield4);

	for(int i = 0; i < 12; i++)
	{
		for(int j = 0; j < 3; j++)
		{
			if(alienAlive[i][j] == 0)
			{
				alienErase(alienXYZ[i][j][0], alienXYZ[i][j][1]);
			}
			if (alienAlive[i][j] == -1)// just got shot
			{
				alienErase(alienXYZ[i][j][0], alienXYZ[i][j][1]);
			}
			if (alienAlive[i][j] == -2) // got shot one turn ago
			{
				eraseExplosion(alienXYZ[i][j][0], alienXYZ[i][j][1]);
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
				alienAlive[i][j] = 1;    // dead
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

public void drawScore(int score)
{
	String color = "000000000";
	int leftSide = 65, width = 10;
	if (score == -100)
	{
		score = preScore;
		color = "000000000";
	}
	else
	{
		color = "255255255";
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
		else if(scoreS.charAt(i) == '2')
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
		else if(scoreS.charAt(i) == '3')
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
		else if(scoreS.charAt(i) == '4')
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
		else if(scoreS.charAt(i) == '5')
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
		else if(scoreS.charAt(i) == '6')
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
		else if(scoreS.charAt(i) == '7')
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
		else if(scoreS.charAt(i) == '8')
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
		else if(scoreS.charAt(i) == '9')
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

public void pauseThread(int pauseTime)
{
	try
	{
		Thread.sleep(pauseTime);
		board.repaint();
	}
	catch(Exception ex){}
}

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
	if (hits == 1)
	{
		eraseShield(x1, y1);
		for (int i=y1; i>y1-20; i--)
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
			if (i > y1-15)
			{
				for (int j=x1; j<x1+5; j++)
				{
					randomErase(j, i, 25);
				}
			}

			if (i<y1-7 && i>y1-20)
			{
				for (int j=x1+5; j<x1+20; j++)
				{
					randomErase(j, i, 25);
				}
			}

			if(i > y1-15)
			{
				for (int j=x1+20; j<x1+25; j++)
				{
					randomErase(j, i, 25);
				}
			}
		}
	}
	else if (hits == 2)
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
	else if (hits == 3)
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
	else if (hits == 4)
	{
		eraseShield(x1, y1);
	}
}

public void eraseShield(int x1, int y1)
{
	for (int i=y1; i>y1-20; i--)
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
		if (i > y1-15)
		{
			for (int j=x1; j<x1+5; j++)
			{
				buf.setRGB(j, i, 000000000);
			}
		}

		if (i<y1-7 && i>y1-20)
		{
			for (int j=x1+5; j<x1+20; j++)
			{
				buf.setRGB(j, i, 000000000);
			}
		}

		if(i > y1-15)
		{
			for (int j=x1+20; j<x1+25; j++)
			{
				buf.setRGB(j, i, 000000000);
			}
		}
	}
}

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

public void run()
{
	board.move();
}

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

public class Board extends JPanel implements KeyListener, Runnable
{
	Thread f;
	Timer shootTime = new Timer();

	public Board()
	{
		try
		{
			buf = ImageIO.read(new File("Background.png"));
		}
		catch(IOException ioe)
		{
			System.err.println("Could not read image");
		}
		this.addKeyListener(this);
		this.setFocusable(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics g2d = (Graphics2D)g;
		g2d.drawImage(buf,0,0,null);
	}
	public void keyPressed(KeyEvent vk)
	{
		Date now = new Date();
		if(vk.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(tankX+5<270)
			{
				tankErase(tankX,tankY);
				tankX+=5;
				tank(tankX,tankY);
			}
		}
		else if(vk.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(tankX-5>0)
			{
				tankErase(tankX,tankY);
				tankX-=5;
				tank(tankX,tankY);
			}
		}
		else if(vk.getKeyCode() == KeyEvent.VK_D)
		{
			shield1 = 4;
			shield2 = 4;
			shield3 = 4;
			shield4 = 4;
		}
		else if(vk.getKeyCode() == KeyEvent.VK_SPACE && date.format(lastBullet) != (date.format(now)))
		{
			bulletX1 = tankX + 6;
			bulletY1 = tankY - 10;
			lastBullet = now;
			f = new Thread(this);
			f.start();
		}
	}

	public void keyReleased(KeyEvent vk){}

	public void keyTyped(KeyEvent vk){}

	public void move()
	{
		shootTime.scheduleAtFixedRate(new AlienShoot(),0,100);
		boolean don = false;
		while (!don)  // if an alien is killed subtract 1 from the counter
		{
			while (alienXYZ[11][2][0] < 260)  // right
			{
				x+=10;
				drawAliens(x, y);
				board.repaint();
				pause(speed-188);//812.5
				don = checkWin();
			}
			y+=20;
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
					prescore+=100;
				}
			}
		}
		score = score+prescore;
		drawScore(-100);
		drawScore(score);
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
				System.err.println("Could not pause");
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
			speed-=50;
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

	public void playAgain()
	{
		int p = JOptionPane.showConfirmDialog(null, "Do you want to play again?");

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
		else if(p == JOptionPane.CANCEL_OPTION){}
	}

	public void pause(long m)
	{
		try
		{
			Thread.sleep(m);
		}
		catch (InterruptedException e)
		{
			System.err.println("Thread was interupted");
		}
	}

	public void run()
	{
		boolean hit = false, done = false;
		int bulletX = bulletX1 , bulletY = bulletY1;
		while(bulletY-10> 43 && !hit && !done)
		{
			if(buf.getRGB(bulletX, bulletY-4) == -13180201)
			{
				for(int i = 0; i < alienXYZ.length; i++)
				{
					for(int j = 0; j < alienXYZ[0].length; j++)
					{
						if(alienXYZ[i][j][0] <= bulletX && bulletX <= alienXYZ[i][j][0]+11)
						{
							if((bulletY <= alienXYZ[i][j][1] && bulletY >= alienXYZ[i][j][1]) || (bulletY-1 <= alienXYZ[i][j][1] && bulletY-1 >= alienXYZ[i][j][1]) || (bulletY-2 <= alienXYZ[i][j][1] && bulletY-2 >= alienXYZ[i][j][1]) || (bulletY-3 <= alienXYZ[i][j][1] && bulletY-3 >= alienXYZ[i][j][1]) || (bulletY-4 <= alienXYZ[i][j][1] && bulletY-4 >= alienXYZ[i][j][1]) || (bulletY-5 <= alienXYZ[i][j][1] && bulletY-5 >= alienXYZ[i][j][1]) || (bulletY-6 <= alienXYZ[i][j][1] && bulletY-6 >= alienXYZ[i][j][1]) || (bulletY-7 <= alienXYZ[i][j][1] && bulletY-7 >= alienXYZ[i][j][1]) || (bulletY-8 <= alienXYZ[i][j][1] && bulletY-8 >= alienXYZ[i][j][1]) || (bulletY-9 <= alienXYZ[i][j][1] && bulletY-9 >= alienXYZ[i][j][1]) || (bulletY-10 <= alienXYZ[i][j][1] && bulletY-10 >= alienXYZ[i][j][1]))
							{
								hit = true;
								alienAlive[i][j] = -1;
								counter--;
								bulletErase(bulletX, bulletY);
							}
						}
					}
				}

			}
			else if(buf.getRGB(bulletX, bulletY-4) == shieldColor)
			{
				bulletErase(bulletX,bulletY);
				if(bulletX > 49 && bulletX < 50+25)
				{
					shield1++;
				}
				else if(bulletX > 99 && bulletX < 100+25)
				{
					shield2++;
				}
				else if(bulletX > 149 && bulletX < 150+25)
				{
					shield3++;
				}
				else if(bulletX > 199 && bulletX < 200+25)
				{
					shield4++;
				}
				bulletY= 44;

			}
			else
			{

				if(bulletY <= 65)
				{
					bulletErase(bulletX, bulletY);
					done = true;
				}
				else
				{
					bulletErase(bulletX, bulletY);
					bulletY--;
					bullet(bulletX, bulletY);
				}
			}
			board.repaint();
		}

	}
	public class AlienShoot extends TimerTask
	{
		public void run()
		{
			int randX = 0, bulletY2 = 0, bulletX2 = 0;
			boolean done = false;
			do
			{
				randX = (int)(Math.random() * 12);
				if(alienAlive[randX][2] == 0)
				{
					bulletX2 = alienXYZ[randX][2][0];
					bulletY2 = alienXYZ[randX][2][1];
					done = true;
				}
				else if(alienAlive[randX][1] == 0)
				{
					bulletX2 = alienXYZ[randX][1][0];
					bulletY2 = alienXYZ[randX][1][1];
					done = true;
				}
				else if(alienAlive[randX][0] == 0)
				{
					bulletX2 = alienXYZ[randX][0][0];
					bulletY2 = alienXYZ[randX][0][1];
					done = true;
				}
			}while(!done);
			done = false;
			while(bulletY2 < 499 )
			{
				if(buf.getRGB(bulletX2, bulletY2+1) == -13181201)
				{
					lives--;
					check = 3;
					bulletErase2(bulletX2, bulletY2);
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
						System.err.println("Could not pause");
					}
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