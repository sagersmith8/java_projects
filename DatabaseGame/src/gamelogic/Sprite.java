package gamelogic;

import java.awt.Point;

import userInterface.Board;
import userInterface.GameFrame;

public class Sprite extends Character {
	God god;
	Board board;
	GameFrame gf;
	private int type;

	public Sprite(GameFrame gf, String img, int type){
		super(img);
		this.setMorality(this.GOOD);
		this.god = gf.god;
		this.board  = gf.board;
		this.gf = gf;
		this.type = type;
		if(god.getDirection()==god.RIGHT){
			this.setLoc(god.getLoc().x-1, god.getLoc().y);
		}
		
		else if(god.getDirection()==god.LEFT){
			this.setLoc(god.getLoc().x+this.getImage().getWidth(), god.getLoc().y);
		}
		
		else if(god.getDirection()==god.DOWN){
			this.setLoc(god.getLoc().x, god.getLoc().y-1);
		}
		
		else if(god.getDirection()==god.UP){
			this.setLoc(god.getLoc().x, god.getLoc().y+this.getImage().getHeight());
		}
	}

	public void run(){
		while(notDead){
			if(god.getSpriteCommand()==god.ATTACK&&this.type==god.getSelectedSprite()||god.getSpriteCommand()==god.ATTACK&&god.ALL==god.getSelectedSprite()){
				goTo(god.getSpriteAttackLocation());
				pause(20);
				board.repaint();
			}

			else if(god.getSpriteCommand()==god.DEFEND){
				goTo(god.getSpriteAttackLocation());
				pause(20);
				board.repaint();
			}
		}
	}

	public void goTo(Point p){
		boolean left=false, right=false, up=false, down=false;
		
		if(p.x>this.getLoc().x){
			if(!occupied(this.getLoc().x+this.getImage().getWidth(),this.getLoc().y)){
				this.setLoc(this.getLoc().x+1, this.getLoc().y);
				right = true;
			}
			
			else if(!occupied(this.getLoc().x,this.getLoc().y+this.getImage().getHeight())){
				up=true;
				this.setLoc(this.getLoc().x, this.getLoc().y+1);
			}

			else if(!occupied(this.getLoc().x,this.getLoc().y-1)){
				down = true;
				this.setLoc(this.getLoc().x, this.getLoc().y-1);
			}
		}
		
		else if(p.x<this.getLoc().x){
			if(!occupied(this.getLoc().x-1,this.getLoc().y)){
				this.setLoc(this.getLoc().x-1, this.getLoc().y);
				left = true;
			}
			
			else if(!occupied(this.getLoc().x,this.getLoc().y+this.getImage().getHeight())){
				up=true;
				this.setLoc(this.getLoc().x, this.getLoc().y+1);
			}

			else if(!occupied(this.getLoc().x,this.getLoc().y-1)){
				down = true;
				this.setLoc(this.getLoc().x, this.getLoc().y-1);
			}
		}
		
		if(p.y>this.getLoc().y&&!down){
			if(!occupied(this.getLoc().x,this.getLoc().y+this.getImage().getHeight())){
				this.setLoc(this.getLoc().x, this.getLoc().y+1);
			}
			
			else if(!occupied(this.getLoc().x-1,this.getLoc().y)&&!left){
				this.setLoc(this.getLoc().x-1, this.getLoc().y);
			}
			
			else if(!occupied(this.getLoc().x+this.getImage().getWidth(),this.getLoc().y)&&!right){
				this.setLoc(this.getLoc().x+1, this.getLoc().y);
			}
		}
			
			
		else if(p.y<this.getLoc().y&&!down){
			if(p.y<this.getLoc().y&& !occupied(this.getLoc().x,this.getLoc().y-1)){
				down = true;
				this.setLoc(this.getLoc().x, this.getLoc().y-1);
			}
			
			else if(!occupied(this.getLoc().x-1,this.getLoc().y)&&!left){
				this.setLoc(this.getLoc().x-1, this.getLoc().y);
			}
			
			else if(!occupied(this.getLoc().x+this.getImage().getWidth(),this.getLoc().y)&&!right){
				this.setLoc(this.getLoc().x+1, this.getLoc().y);
			}
		}		
	}

	public boolean occupied(int x, int y){		
		for(int i = 0; i < gf.sprites.size(); i++){
			if(gf.sprites.get(i).getOccupied().contains(new Point(x,y))){
				return true;
			}			
		}
		
		if(god.getOccupied().contains(new Point(x,y)))
		{
			return true;
		}

		return false;
	}

	public boolean occupied(Point p){		
		for(int i = 0; i < gf.sprites.size(); i++){
			if(gf.sprites.get(i).getOccupied().contains(p)){
				return true;
			}
			
			else if(gf.sprites.get(i).getOccupied().contains(new Point(p.x+this.getImage().getWidth(),p.y))){
				return true;
			}
			
			else if(gf.sprites.get(i).getOccupied().contains(new Point(p.x+this.getImage().getWidth(),p.y+this.getImage().getHeight()))){
				return true;
			}
			
			else if(gf.sprites.get(i).getOccupied().contains(new Point(p.x, p.y+this.getImage().getHeight()))){
				return true;
			}
		}
		
		if(god.getOccupied().contains(p)){
			return true;
		}
		
		else if(god.getOccupied().contains(new Point(p.x+this.getImage().getWidth(),p.y))){
			return true;
		}
		
		else if(god.getOccupied().contains(new Point(p.x+this.getImage().getWidth(),p.y+this.getImage().getHeight()))){
			return true;
		}
		
		else if(god.getOccupied().contains(new Point(p.x, p.y+this.getImage().getHeight()))){
			return true;
		}

		return false;
	}
}
