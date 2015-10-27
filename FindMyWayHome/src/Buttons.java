import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;


public class Buttons extends JButton{
	int state = 0;
	BufferedImage buf;
	public Buttons(){
		this.setBackground(Color.GREEN);
	}
	
	public void setBufferedImage(BufferedImage b)
	{
		buf = b;
	}
	
	public void setState(int s)
	{
		state = s;
	}
	
	public int getState()
	{
		return state;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics g2d = (Graphics2D)g;
		g2d.drawImage(buf, 0, 0, null);
	}
}
