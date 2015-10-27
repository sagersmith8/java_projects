
public class Gravity extends Thread {
	Mainframe mainframe;
	Board board;
	
	public Gravity(Board b, Mainframe m){
		mainframe = m;
		board = b;
	}
	
	public void run(){		
		while(board.background.getRGB(mainframe.p1.x, mainframe.p1.y+1)==mainframe.black&&mainframe.callGravity){
			
			mainframe.p1.y++;
			if(board.background.getRGB(mainframe.p1.x, mainframe.p1.y+1)==mainframe.brown|| board.background.getRGB(mainframe.p1.x, mainframe.p1.y+1)==mainframe.grey)
			{
				board.p1Num=0;
			}
			board.repaint();
			
			try{
				Thread.sleep(15);
			}
			catch(Exception ex){
				System.err.print("Error Sleeping");
			}
		}
	}
}
