

public class Birththread extends Thread {
	Board board;
	Mainframe mainframe;
	int randomInt = (int)(Math.random()*4);
	long sleep = 200;
	
	public Birththread(Board b, Mainframe m){
		board = b;
		mainframe = m;
		mainframe.p1 = mainframe.platform[randomPlatform()];
		mainframe.isOccupied[randomInt]=true;
	}
	
	public int randomPlatform(){		
		while(mainframe.isOccupied[randomInt]){
			randomInt = (int)(Math.random()*4);
		}
		return randomInt;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 25; i++){
			board.p1Num = 1;
			board.repaint();
			try{
				Thread.sleep(sleep);
			}
			catch(Exception ex){
				System.err.println("Problem Sleeping");
			}
			board.p1Num = 0;
			board.repaint();
			try{
				Thread.sleep(sleep);
			}
			catch(Exception ex){
				System.err.println("Problem Sleeping");
			}
			if(i >= 9){
				sleep-=10;
			}
		}
	}

}
