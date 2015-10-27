import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;


public class Gravity extends Thread {

	Utils utils;
	Board board;
	BufferedImage buf;
	boolean fall = false;

	public Gravity(Utils u, Board bp){
		utils = u;
		board = bp;
		buf = board.level[utils.levelNum];
	}

	public void run()
	{
		gravity();
	}

	public void gravity()
	{
		int counter = 0;
		for(int i = utils.x; i <utils.x+27; i++)
		{
			try{
				if(buf.getRGB(i,utils.y)!= utils.black){
					counter++;
				}
			}
			catch (Exception ex){
				utils.ninjaDead = true;
				//JOptionPane.showMessageDialog(board, "You Lost", "You Lost", JOptionPane.PLAIN_MESSAGE);
				board.repaint();
				break;
			}
		}

		if(counter == 27)
		{
			utils.y++;
			board.repaint();
			try{
				Thread.sleep(20);
			}

			catch(Exception ex)
			{
				System.err.print("Problem Sleeping");
			}
			
			gravity();
		}
	}
}
