package userInterface;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
	BufferedImage img;
	String text;
	
	public ImageButton(String img, String text){
		try{
			this.img = ImageIO.read(new File(img));
		}
		catch(Exception ex){
		}
		
		this.setIcon(new ImageIcon(img));
		this.setText(text);
	}
}
