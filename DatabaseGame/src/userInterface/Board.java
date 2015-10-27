package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Board extends JPanel {
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	GameFrame gameFrame;
	
	public Board(GameFrame gameFrame){
		this.gameFrame = gameFrame;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.green);
		g.fillRect(0,0,dim.width,dim.height);
		g.drawImage(gameFrame.god.getImage(), gameFrame.god.getLoc().x, gameFrame.god.getLoc().y,null);
		
		for(int i = 0; i < gameFrame.sprites.size(); i++){
			g.drawImage(gameFrame.sprites.get(i).getImage(), gameFrame.sprites.get(i).getLoc().x, gameFrame.sprites.get(i).getLoc().y,null);
		}
	}
}
