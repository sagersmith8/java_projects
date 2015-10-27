import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Board extends JPanel {
	BufferedImage img;
	String string;
	public Board(String img, String str){
		string = str;
		try 
		{
			this.img = ImageIO.read(new File(img));
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(this.img.getWidth(),this.img.getHeight()));
	}
	
	public Board(BufferedImage img, String str){
		string = str;
		this.img = img;
		this.setPreferredSize(new Dimension(this.img.getWidth(),this.img.getHeight()));
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.magenta);
		g.setFont(new Font("New Times Roman", Font.CENTER_BASELINE, 16));
		g.drawString(string, 50, 50);
	}
}
