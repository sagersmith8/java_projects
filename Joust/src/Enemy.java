import java.awt.Point;


public class Enemy extends Thread {
	long sleep = 200;
	boolean dead = false, collected = false, wave=false;
	int type, image = 0, counter =9, direction = -1,randomInt = (int)(Math.random()*4);;
	Board board;
	Mainframe mainframe;
	Point loc = new Point(), newLoc = new Point();

	public Enemy(Board b, int t, Mainframe m){
		board = b;
		type = t;
		mainframe = m;
		board.enemyList.add(this);
		loc = mainframe.platform[randomPlatform()];
		mainframe.isOccupied[randomInt]=true;
	}
	
	public int randomPlatform(){		
		while(mainframe.isOccupied[randomInt]){
			randomInt = (int)(Math.random()*4);
		}
		return randomInt;
	}
	
	public boolean checkPixel(int x, int y){
		if(board.background.getRGB(x, y)==mainframe.black){
			return true;
		}
		return false;
	}
	
	public boolean dead(){
		if(mainframe.p1.x>=loc.x&&mainframe.p1.x<=loc.x+31&&loc.y>=mainframe.p1.y&&loc.y-34<=mainframe.p1.y || mainframe.p1.x+31>=loc.x&&mainframe.p1.x+31<=loc.x+31&&loc.y>=mainframe.p1.y&&loc.y-34<=mainframe.p1.y){
			board.score = true;
			if(!dead){
				mainframe.score+=500;
				dead = true;
			}
			return true;
		}
		return false;
	}
	public boolean collected(){
		if(mainframe.p1.x>=loc.x&&mainframe.p1.x<=loc.x+12&&loc.y>=mainframe.p1.y&&loc.y>=mainframe.p1.y || mainframe.p1.x+31>=loc.x&&mainframe.p1.x+31<=loc.x+12&&loc.y-16>=mainframe.p1.y&&loc.y>=mainframe.p1.y){
			collected = true;
			board.enemyList.remove(this);
			board.enemyList.trimToSize();
			if(board.enemyList.isEmpty()){
				board.nextWave = true;
				if(!wave){
					mainframe.wave++;
					wave =true;
				}
			}
			board.score=true;
			if(!collected){
				mainframe.score+=500;
				collected=true;
			}
			return true;
		}
		return false;
	}

	public void birth(int type){
		for(int i = 0; i < 25; i++){
			image = 2;
			board.repaint();
			try{
				Thread.sleep(sleep);
			}
			catch(Exception ex){
				System.err.println("Problem Sleeping");
			}
			image = 1;
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
		image = 3;
		mainframe.isOccupied[randomInt]=false;
	}
	
	public void loc(){
		newLoc.x = mainframe.p1.x;
		newLoc.y = mainframe.p1.y;
	}
	
	public void egg(){
		loc.y+=10;
		while(checkPixel(loc.x,loc.y-16)&&!collected()){
			loc.y++;
			board.repaint();
			pause(20);
		}
		
		for(int hatch = 0;!collected()||hatch<20; hatch++){
			pause(20);
		}
	}

	public void level0(){
		if(counter%9==0){
			loc();
		}
		
		if(counter%3==0){
			if(image==3)
				image=4;
			else if(image==4)
				image=3;
		}

		if(loc.x<newLoc.x){
			direction = 1;
			if(checkPixel(loc.x+1,loc.y))
				loc.x++;
			board.repaint();
		}

		else if(loc.x>newLoc.x){
			direction = -1;
			if(checkPixel(loc.x-1,loc.y))
				loc.x--;
			board.repaint();
		}
		
		pause(20);

		if(loc.y>newLoc.y){
			if(checkPixel(loc.x,loc.y-33))
				loc.y--;
			board.repaint();
		}
		
		else if(loc.y<newLoc.y){
			if(checkPixel(loc.x, loc.y+1))
				loc.y++;
			board.repaint();
		}

		
		pause(20);

		counter++;
	}

	public void level1(){

	}

	public void level2(){

	}

	public void pause(long pause){
		try{
			Thread.sleep(pause);
		}
		catch(Exception ex){
			System.out.println("Problem sleeping");
		}
	}

	public void run(){
		birth(type);
		while(!dead()){
			if(type == 0){
				level0();
			}
		}
		image = 0;
		board.repaint();
		egg();
	}
}
