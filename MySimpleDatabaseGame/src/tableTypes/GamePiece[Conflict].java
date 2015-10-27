package tableTypes;

import game.Action;

import java.awt.Image;
import java.awt.Point;
import java.util.TimerTask;

public class GamePiece extends TimerTask implements Action {
	private int gamePieceId;
	private Point position;
	private Image image;
	private int health;
	private int deathrate;
	private int defenceRate;
	private int attackRate;
	
	public GamePiece(int gamePieceId, Point position, Image image){
		this.setGamePieceId(gamePieceId);
		this.setPosition(position);
		this.setImage(image);
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

	public void setImage(Image image) {
		this.image = image;
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
}
