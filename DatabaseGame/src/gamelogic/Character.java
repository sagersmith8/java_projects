package gamelogic;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character extends Thread {
	public final int LEFT = -2000, RIGHT = 2000, UP = -3000, DOWN =3000, GOOD = 797979, EVIL =123456763;
	private int direction = RIGHT, morality, maxHealth, currentHealth, currentLevel;
	private Point loc = new Point(0,0);
	private BufferedImage image;
	protected boolean notDead = true;
	private Rectangle occupied;
	
	public void pause(long sleepTime){
		try{
			Thread.sleep(sleepTime);
		}
		
		catch(Exception ex){	
		}
	}
	
	public Character(BufferedImage img){
		this.setImage(img);
		occupied = new Rectangle(loc.x,loc.y,image.getWidth(),image.getHeight());
	}
	
	public Character(String img){
		this.setImage(img);
		occupied = new Rectangle(loc.x,loc.y,image.getWidth(),image.getHeight());
	}
	
	public Character(File img){
		this.setImage(img);
		occupied = new Rectangle(loc.x,loc.y,image.getWidth(),image.getHeight());
	}
	
	public Point getLoc() {
		return loc;
	}

	public void setLoc(Point loc) {
		setOccupied(loc.x,loc.y,image.getWidth(),image.getHeight());
		this.loc = loc;
	}
	
	public void setLoc(int x, int y) {
		setOccupied(x,y,image.getWidth(),image.getHeight());
		this.loc = new Point(x,y);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setImage(String image) {
		try{
			this.image = ImageIO.read(new File(image));
		}
		
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void setImage(File image) {
		try{
			this.image = ImageIO.read(image);
		}
		
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public boolean isNotDead() {
		return notDead;
	}

	public void setNotDead(boolean notDead) {
		this.notDead = notDead;
	}


	public Rectangle getOccupied() {
		return occupied;
	}


	public void setOccupied(Rectangle occupied) {
		this.occupied = occupied;
	}
	
	public void setOccupied(int x, int y, int width, int height) {
		this.occupied = new Rectangle(x,y,width,height);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getMorality() {
		return morality;
	}

	public void setMorality(int morality) {
		this.morality = morality;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}
