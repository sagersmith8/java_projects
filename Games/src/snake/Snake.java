package snake;


/**
 * Programmer: Sage Smith
 * Purpose: Snake game, entertains user
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import oracle.jdbc.driver.OracleDriver;

/**
 * @author Sage
 *	purpose: entertain
 */

public class Snake extends JFrame implements KeyListener
{
	int headCol = 35, headRow = 20,snakeLength = 9, mouseRow, mouseCol, score = 0,counter = 0, playAgain, red,red2,red3,red4,blue,blue2,blue3,blue4,green,green2,green3,green4;
	boolean right, left, down, up, winning = true, needMouse = false, done, beatScore,gameStarted = false;
	JPanel[][] snakeBoard = new JPanel[40][60];
	JLabel scoreLabel = new JLabel("Score:  " + score, JLabel.CENTER);
	int[][] snake = new int[40][60];
	JFrame scoreFrame = new JFrame();

	static Snake f1;

	
	/**
	 * @param
	 * @return
	 */
	public Snake()
	{
		this.getContentPane().addKeyListener(this);
		this.getContentPane().setFocusable(true);
		this.setLayout(new GridLayout(40,60));
		for(int i = 0; i < snakeBoard.length; i++)
		{
			for(int x = 0; x < snakeBoard[0].length; x++)
			{
				snakeBoard[i][x] = new JPanel();
				//snakeBoard[i][x].setBackground(new Color(255,255,255));
				this.add(snakeBoard[i][x]);
				snake[i][x] = 0;
			}
		}
		for(int i = 27; i < 36; i++)
		{
			counter++;
			snake[20][i] = counter;
		}
		scoreFrame = new JFrame();
		scoreFrame.add(scoreLabel);
		scoreFrame.setTitle("Score");
		scoreFrame.setBounds(915,5,150,100);
		scoreFrame.setVisible(true);
		pickMouse();
		drawBoard();
		JOptionPane.showMessageDialog(this, "Hit S to start the snake. Control Snake with arrow keys. Eat the 'Mice', while not running into your own tail","Directions", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @param ke
	 * @return void
	 * Takes user input
	 */
	
	public void keyPressed(KeyEvent ke)
	{
		right = false;
		left = false;
		down = false;
		up = false;

		if(ke.getKeyCode() == KeyEvent.VK_S && !gameStarted)
		{
			GameThread t1 = new GameThread();
			t1.start();
			right = true;
			gameStarted = true;
		}
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			right = true;
		}
		else if(ke.getKeyCode() == KeyEvent.VK_LEFT)
		{
			left = true;
		}
		else if(ke.getKeyCode() == KeyEvent.VK_UP)
		{
			up = true;
		}
		else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
		{
			down = true;
		}
	}

	/**
	 * @param ke
	 * @return void
	 */
	
	public void keyReleased(KeyEvent ke){}
	/**
	 * @param ke
	 * @return void
	 */
	public void keyTyped(KeyEvent ke){}

	/**
	 * @param pauseTime
	 * @return void
	 * Pauses program for length taken in
	 */
	public void pauseThread(int pauseTime)
	{
		try
		{
			Thread.sleep(pauseTime);
		}
		catch(Exception ex){}
	}

	/**
	 * @param null
	 * @return void
	 * Randomly places the mouse
	 */
	public void pickMouse()
	{
		done = false;
		while(!done)
		{
			mouseRow = (int)(Math.random() * 40);
			mouseCol = (int)(Math.random() * 60);

			if(snake[mouseRow][mouseCol] == 0)
			{
				done = true;
				snake[mouseRow][mouseCol] = -1;
			}
		}
	}

	/**
	 * @param null
	 * @return void
	 * Draws board
	 */
	public void drawBoard()
	{
		red = (int)(Math.random()*256);
		blue = (int)(Math.random()*256);
		green = (int)(Math.random()*256);
		red2 = (int)(Math.random()*256);
		blue2 = (int)(Math.random()*256);
		green2 = (int)(Math.random()*256);
		red3 = (int)(Math.random()*256);
		blue3 = (int)(Math.random()*256);
		green3= (int)(Math.random()*256);
		red4 = (int)(Math.random()*255);
		blue4= (int)(Math.random()*255);
		green4=(int)(Math.random()*255);
		for(int i = 0; i < snakeBoard.length; i++)
		{
			for(int x = 0; x < snakeBoard[0].length; x++)
			{
				if(snake[i][x] == snakeLength)
				{
					snakeBoard[i][x].setBackground(new Color(red,green,blue));
				}
				else if(snake[i][x] == 1)
				{
					snakeBoard[i][x].setBackground(new Color(red2,green2,blue2));
				}
				else if(snake[i][x] > 0)
				{
					snakeBoard[i][x].setBackground(new Color(red3,green3,blue3));
				}
				else if(snake[i][x] == -1)
				{
					snakeBoard[i][x].setBackground(new Color(red4,green4,blue4));
				}
				else
				{
					snakeBoard[i][x].setBackground(new Color(0,0,0));
				}
			}
		}
		pauseThread(100);
	}

	/**
	 * moves snake 
	 */
	public class GameThread extends Thread
	{
		public void run()
		{
			
			while(winning)
			{
				if(needMouse)
				{
					pickMouse();
					needMouse = false;
				}
				if(right)
				{
					headCol++;
					if(headCol > 59)
					{
						headCol = 0;
					}
					if(snake[headRow][headCol] == snakeLength -1)
					{
						right = false;
						left = true;
					}
				}
				else if(left)
				{
					headCol--;
					if(headCol < 0)
					{
						headCol = 59;
					}
					if(snake[headRow][headCol] == snakeLength -1)
					{
						left = false;
						right = true;
					}
				}
				else if(down)
				{
					headRow++;
					if(headRow > 39)
					{
						headRow = 0;
					}
					if(snake[headRow][headCol] == snakeLength -1)
					{
						down = false;
						up = true;
					}
				}
				else
				{
					headRow--;
					if(headRow < 0)
					{
						headRow = 39;
					}
					if(snake[headRow][headCol] == snakeLength -1)
					{
						up = false;
						down = true;
					}
				}
				if(snake[headRow][headCol] == -1)
				{
					score += 100;
					needMouse = true;
					snakeLength++;
					snake[headRow][headCol] = snakeLength;
					drawBoard();
					scoreLabel.setText("Score:  " + score);
					scoreLabel.invalidate();
					scoreLabel.validate();
					pauseThread(100);
				}
				
				/**
				 * Checks database.
				 * Sorts values.
				 * Adds to database if value fits.
				 * Displays high scores
				 */
				else if(snake[headRow][headCol] != 0 && snake[headRow][headCol] != snakeLength -1 && snake[headRow][headCol] != snakeLength)
				{
					beatScore = false;
					Font loseFont = new Font("Chiller", Font.BOLD, 40);
					JLabel loseLabel = new JLabel("Game Over!", JLabel.CENTER);
					winning = false;
					loseLabel.setFont(loseFont);
					loseLabel.setForeground(Color.red);
					f1.getContentPane().removeAll();
					f1.getContentPane().repaint();
					f1.getContentPane().setBackground(Color.black);;
					f1.getContentPane().setLayout(new BorderLayout());
					f1.getContentPane().add(loseLabel,BorderLayout.CENTER);
					f1.getContentPane().invalidate();
					f1.getContentPane().validate();
					
					try
					{
						DriverManager.registerDriver(new OracleDriver());
						Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "System",  "System");
						Statement stmt = con.createStatement();
						ArrayList<String> usernameList = new ArrayList<String>();
						ArrayList<Integer> highScoreList = new ArrayList<Integer>();
						try{
							stmt.execute("CREATE TABLE HighScoreTable(USERNAME VARCHAR2(20), SCORE NUMBER)");
						}
						catch(Exception ex){
						}
						stmt.execute("INSERT INTO HighScoreTable VALUES('Sage', 0)");
						ResultSet rs = stmt.executeQuery("SELECT * FROM HighScoreTable");
			
						while(rs.next())
						{
							usernameList.add(rs.getString("USERNAME"));
							highScoreList.add(rs.getInt("SCORE"));
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
							f1.getContentPane().removeAll();
							f1.getContentPane().repaint();
							JPanel jp = new JPanel();
							jp.setLayout(new GridLayout(usernameList.size(),3));
							jp.setBackground(Color.red);
							jp.add(new JLabel("Rank"));
							jp.add(new JLabel("User Name"));
							jp.add(new JLabel("Score"));
							for(int j = 0; j < highScoreList.size(); j++)
							{
								jp.add(new JLabel(Integer.toString(j)));
								jp.add(new JLabel(usernameList.get(j)));
								jp.add(new JLabel(Integer.toString(highScoreList.get(j))));
							}
							f1.add(jp);
							f1.getContentPane().invalidate();
							f1.getContentPane().validate();
						}
					}
					catch(Exception p)
					{
						p.printStackTrace();
					}

					playAgain = JOptionPane.showConfirmDialog(null,"Do you want to play again", "Play Again", JOptionPane.YES_NO_OPTION);
					if(playAgain == 0)
					{
						f1.dispose();
						f1 = new Snake();
						f1.setTitle("Snake");
						f1.setBounds(0,0,910,715);
						f1.setVisible(true);
					}
					else
					{
						scoreFrame.dispose();
						System.exit(0);
					}
				}
				else if(snake[headRow][headCol] != snakeLength -1)
				{
					for(int i = 0; i < snake.length; i++)
					{
						for(int j = 0; j < snake[0].length; j++)
						{
							if(snake[i][j] > 0)
							{
								snake[i][j]--;
							}
						}
					}
					snake[headRow][headCol] = snakeLength;
					drawBoard();
				}
			}
		}
	}

	/**
	 * Bubble sort
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

	public static void main(String [] args)
	{
		f1 = new Snake();
		f1.setTitle("Rainbow Snake!");
		f1.setBounds(0,0,910,715);
		f1.setVisible(true);
	}
}