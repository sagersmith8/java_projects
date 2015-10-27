package mario;

@SuppressWarnings("serial")
public class Mario extends Thread
{	
	Board board;
	public Mario(Board b)
	{
		board = b;
	}	
	public void run()
	{
		
		for(int i=50; i> 0; i--)
		{
			board.marioY--;
			board.mario();
			System.out.println(board.marioY);
			board.pause(4);
		}
		gravity(board.marioY);
	}

	public void gravity(int x)
	{
		if(board.back.getRGB(board.marioX+15, board.marioY+47) == MarioComponents.lightBlue)//back.getRGB(marioX+15, marioY) != -6075996 || back.getRGB(marioX+15, marioY) != -3584 || back.getRGB(marioX+15, marioY) != -14503604 || back.getRGB(marioX+15, marioY) != -4856291 || back.getRGB(marioX+15, marioY) != -7864299 || back.getRGB(marioX+15, marioY) != -4621737)
		{
			x++;
			board.mario();
			System.out.println(board.marioY);
			board.pause(4);
			gravity(x);
		}
	}
}