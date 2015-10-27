
public class Flap extends Thread {
	Board board;
	Mainframe mainframe;
	Gravity gravity;
	
	public Flap(Board b, Mainframe m, Gravity g){
		board = b;
		mainframe = m;
		gravity = g;
	}
	
	public void run(){ 
		
		mainframe.callGravity = false;
		for(int i = 0; i<20 ; i++){
			try{
				if(board.background.getRGB(mainframe.p1.x,mainframe.p1.y-35)==mainframe.black)
			mainframe.p1.y--;
			else
				break;
			}
			catch(Exception ex){}
			board.p1Num=2;
			board.repaint();
			try{
				Thread.sleep(20);
			}
			catch(Exception ex){
				System.err.println("Error Sleeping");
			}
		}
		board.p1Num=3;
		board.repaint();
		try{
			Thread.sleep(20);
		}
		catch(Exception ex){
			System.err.println("Error Sleeping");
		}
		mainframe.callGravity = true;
		
		if(!gravity.isAlive()){
			gravity = new Gravity(board, mainframe);
			gravity.start();		
		}
	}

}
