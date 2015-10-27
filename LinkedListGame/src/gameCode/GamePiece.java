package gameCode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public abstract class GamePiece extends JButton {
	Point position;
	boolean team;
	BufferedImage buf;
	
	public GamePiece(Point position, boolean team, String image){
		this.position = position;
		this.team = team;
		try{
			buf = ImageIO.read(new File(image));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(buf,0,0,null);
	}
	
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public boolean getTeam() {
		return team;
	}

	public void setTeam(boolean team) {
		this.team = team;
	}

	public abstract void movement();
}
