package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import buildings.DwarfCastle;

import tableTypes.GamePiece;

public class Board extends JPanel {
	private ArrayList<GamePiece> gamePieces = new ArrayList<GamePiece>();
	BufferedImage background, dwarfCastle;
	
	public Board(){
		try {
			background = ImageIO.read(new File("assets/background.png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(background.getWidth(),background.getHeight()));
		DwarfCastle dc = new DwarfCastle();
		gamePieces.add(dc);
		dc.active();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(background, 0, 0, null);
		for(int i = 0; i < gamePieces.size(); i++){
			g2d.drawImage(gamePieces.get(i).getImage(), gamePieces.get(i).getPosition().x,gamePieces.get(i).getPosition().y,null);
		}
	}
	
	public void addGamePiece(GamePiece gamePiece){
		gamePieces.add(gamePiece);
	}
	
	public void removeGamePiece(GamePiece gamePiece){
		gamePieces.remove(gamePiece);
	}
}
