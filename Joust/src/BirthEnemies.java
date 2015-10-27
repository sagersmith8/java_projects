
public class BirthEnemies extends Thread {
	Board board;
	Mainframe mainframe;
	
	BirthEnemies(Board b, Mainframe m){
		board = b;
		mainframe = m;
	}
	
	public void run(){
		while(board.enemyList.size()<(mainframe.wave*3)+1){
			new Enemy(board, 0, mainframe).start();
			try{
				Thread.sleep(100);
			}
			catch(Exception ex){}
		}
	}
}
