package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	BufferedImage image;
	
	public ImagePanel(String string){
		try {
			image = ImageIO.read(new File(string));
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0,0,null);
	}
}
