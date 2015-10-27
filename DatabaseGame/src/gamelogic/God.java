package gamelogic;

import java.awt.Point;

public class God extends Character {
	public final int ATTACK = 10000000, DEFEND = -10000000, FIRE = -1212, WATER = 1212, EARTH = 9090, AIR = -9090, ALL = 5959;	
	private int spriteCommand = DEFEND;
	private Point spriteAttackLocation = new Point(500,0);
	private int selectedSprite = EARTH;
	
	public God(){
		super("god.png");
		this.setMorality(this.GOOD);
	}
	
	public void run(){
		
	}

	public int getSpriteCommand() {
		return spriteCommand;
	}

	

	public void setSpriteCommand(int spriteCommand){
		this.spriteCommand = spriteCommand;
	}

	public Point getSpriteAttackLocation() {
		return spriteAttackLocation;
	}

	public void setSpriteAttackLocation(int x, int y) {
		this.spriteAttackLocation = new Point(x,y);
	}
	
	public void setSpriteAttackLocation(Point spriteAttackLocation) {
		this.spriteAttackLocation = spriteAttackLocation;
	}

	public int getSelectedSprite() {
		return selectedSprite;
	}

	public void setSelectedSprite(int selectedSprite) {
		this.selectedSprite = selectedSprite;
	}
}
