package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Board extends JPanel {
	BufferedImage img;
	String string;
	
	public Board() {
		
	}
	
	/**Creates a new JPanel with an image as the background and text in the upper left corner.
	 * 
	 * @param imgName	the name of the background image
	 * @param str		text to be displayed
	 */
	public Board(String imgName, String str){
		string = str;
		try 
		{
			img = ImageIO.read(new File(imgName));
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
	}
	
	/**Creates a new JPanel with an image as the background and text in the upper left corner.
	 * 
	 * @param img	background image
	 * @param str	text to be displayed
	 */
	public Board(BufferedImage img, String str){
		string = str;
		this.img = img;
		this.setPreferredSize(new Dimension(this.img.getWidth(),this.img.getHeight()));
	}
	
	public void setBoard(String imgName, String text) {
		string = text;
		try {
			img = ImageIO.read(new File(imgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
		repaint();
	}
	
	public void setBoard(BufferedImage img, String text) {
		string = text;
		this.img = img;
		this.setPreferredSize(new Dimension(this.img.getWidth(),this.img.getHeight()));
		repaint();
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.magenta);
		g.setFont(new Font("New Times Roman", Font.CENTER_BASELINE, 16));
		g.drawString(string, 50, 50);
	}
}
