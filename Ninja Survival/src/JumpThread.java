
public class JumpThread extends Thread {
	Utils utils;
	Board board;
	Gravity g;
	
	public JumpThread(Utils u, Board b, Gravity gr)
	{
		utils = u;
		board = b;
		g = gr;
	}
	
	public void run()
	{
		for(int i =0; i<50; i++){
			try{
			utils.y--;
			}
			catch(Exception ex){}
			
			board.repaint();
			try{
				Thread.sleep(10);
			}
			
			catch(Exception ex){
				System.err.print("Error Sleeping Jump thread");
			}
		}
		
		if(!g.isAlive())
		{
			g = new Gravity(utils, board);
			g.start();
		}
	}
}
