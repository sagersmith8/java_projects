package polygon;
/**
* @author Sage
* Please Just Work.. Please
*/
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import javax.swing.JFrame;


public class WorldWindTest1 extends JFrame 
{
	public WorldWindTest1()
	{
		WorldWindowGLCanvas canvas = new WorldWindowGLCanvas();
		canvas.setModel(new BasicModel());
		this.setBounds(0,0,1000,500);
		this.add(canvas);
		this.setVisible(true);
	}
	public static void main(String[] args) 
	{
		new WorldWindTest1();
	}
}