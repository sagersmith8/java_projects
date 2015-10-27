import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class Enemy extends Thread
{
	boolean notDead = true, move = true;
	int direction = 1;
	Utils utils;
	int x, y;
	Board board;
	BufferedImage enemyImg;

	public Enemy(int xs, int ys, Utils u, Board b, BufferedImage bu)
	{
		x = xs;
		y = ys;
		utils = u;
		board = b;
		enemyImg = bu;
	}

	public void enemyDead()
	{
		if(y-34==utils.y && x>=utils.x && x<=utils.x+27 || y-34==utils.y && x+27>=utils.x && x+27<=utils.x+27)
		{
			notDead = false;
			utils.killCount++;
			if(utils.killCount%10==0)
			{
				utils.levelNum++;
				utils.waveCount++;
			}
		}
		else{
			heroDead();
		}
	}

	public void heroDead()
	{
		if(y<=utils.y && y>=utils.y-33 && x<=utils.x+27 && x>=utils.x || y<=utils.y && y>=utils.y-33 && x+27>=utils.x && x+27<=utils.x+27){
			utils.ninjaDead = true;
			notDead = false;
			//JOptionPane.showMessageDialog(utils.mainFrame, "You have died", "Gameover", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void run()
	{
		while(notDead){			
			for(int i = x; i < x+27; i++)
			{
				try{
					if(board.level[utils.levelNum].getRGB(i, y)==utils.black)
					{
						move = false;
						break;
					}
				}
				catch(Exception ex){
					notDead = false;
				}
			}

			if(move)
			{
				y++;
			}
			else if(direction == 1)
			{
				try{
					if(board.level[utils.levelNum].getRGB(x+27, y+1)==utils.black)
					{
						x++;
					}

					else{
						direction = -1;
					}
				}
				catch(Exception ex){
					direction = 1;
				}
			}
			else if(direction == -1)
			{
				try{
					if(board.level[utils.levelNum].getRGB(x-1, y+1)==utils.black)
					{
						x--;
					}

					else{
						direction = 1;
					}
				}
				catch(Exception ex){
					direction =-1;
				}
			}

			try{
				Thread.sleep(20);
			}

			catch(Exception ex){
				System.err.print("Error sleeping in enemy");
			}
			enemyDead();
			board.repaint();
		}
		board.enemyList.remove(this);		
	}
}
