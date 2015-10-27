

public class Move extends Thread {
	Mainframe mainframe;
	Board board;
	Gravity gravity;

	public Move(Board b, Mainframe m,Gravity g){
		mainframe = m;
		board = b;
		gravity = g;
	}

	public void walking()
	{
		board.p1Num=0; 
		board.repaint();
		try{
			Thread.sleep(50);
		}
		catch(Exception ex){
			System.err.println("Error Sleeping");
		}
		if(mainframe.direction==-1){
			mainframe.p1.x-=5;
		}
		else if(mainframe.direction ==1){
			mainframe.p1.x+=5;
		}
		board.p1Num =4;
		board.repaint();
		try{
			Thread.sleep(50);
		}
		catch(Exception ex){
			System.err.println("Error Sleeping");
		}
		board.p1Num=5;
		board.repaint();
		try{
			Thread.sleep(50);
		}
		catch(Exception ex){
			System.err.println("Error Sleeping");
		}
		if(!gravity.isAlive()){
			gravity = new Gravity(board, mainframe);
			gravity.start();
		}
	}

	public void run(){
		if(board.background.getRGB(mainframe.p1.x, mainframe.p1.y+1)== mainframe.brown||board.background.getRGB(mainframe.p1.x, mainframe.p1.y+1)== mainframe.grey ||board.background.getRGB(mainframe.p1.x+31, mainframe.p1.y+1)== mainframe.brown||board.background.getRGB(mainframe.p1.x+31, mainframe.p1.y+1)== mainframe.grey){
			walking();
		}
		else{
			if(mainframe.direction == 1){
				try{
					if(board.background.getRGB(mainframe.p1.x+36, mainframe.p1.y)== mainframe.black)
						mainframe.p1.x+=5;
				}
				catch(Exception ex){
					mainframe.p1.x=0;
				}
			}
			else if(mainframe.direction==-1){
				try{
					if(board.background.getRGB(mainframe.p1.x-5, mainframe.p1.y)== mainframe.black)
						mainframe.p1.x-=5;
				}
				catch(Exception ex){
					mainframe.p1.x = board.background.getWidth()-35;
				}
			}
		}
	}
}
