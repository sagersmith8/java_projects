package tableTypes;

import game.Action;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GamePiece extends Thread implements Action {
	private int gamePieceId;
	private Point position;
	private Image image;
	private int health;
	private int deathrate;
	private int defenceRate;
	private int attackRate;
	private boolean isSelected;
	
	public GamePiece(int gamePieceId, Point position){
		this.setGamePieceId(gamePieceId);
		this.setPosition(position);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(String image) {
		try {
			this.image = ImageIO.read(new File(image));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getGamePieceId() {
		return gamePieceId;
	}

	public void setGamePieceId(int gamePieceId) {
		this.gamePieceId = gamePieceId;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDeathrate() {
		return deathrate;
	}

	public void setDeathrate(int deathrate) {
		this.deathrate = deathrate;
	}

	public int getDefenceRate() {
		return defenceRate;
	}

	public void setDefenceRate(int defenceRate) {
		this.defenceRate = defenceRate;
	}

	public int getAttackRate() {
		return attackRate;
	}

	public void setAttackRate(int attackRate) {
		this.attackRate = attackRate;
	}

	@Override
	public void active() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
