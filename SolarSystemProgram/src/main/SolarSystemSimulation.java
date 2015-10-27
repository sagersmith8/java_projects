package main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SolarSystemSimulation extends Applet 
{
	Point center;
	int time = 0;
	public void init()
	{
		this.setBackground(Color.BLACK);
		Timer time = new Timer();
		time.scheduleAtFixedRate(new SimulationTimer(), 0, 10);
	}
	
	public void paint(Graphics g)
	{
		center = new Point(this.getWidth()/2,this.getHeight()/2);
		g.setColor(Color.YELLOW);
		g.fillOval(center(4).x, center(4).y, radius(4), radius(4));
		g.setColor(Color.RED);
		g.fillOval(rotationCenter(time,19,3).x, rotationCenter(time,19,3).y, radius(15), radius(15));
		g.setColor(Color.GREEN);
		g.fillOval(rotationCenter(time,19,5).x, rotationCenter(time,19,5).y, radius(12), radius(12));
		g.setColor(Color.BLUE);
		g.fillOval(rotationCenter(time,19,7).x, rotationCenter(time,19,7).y, radius(12), radius(12));
	}
	
	public int radius(int size)
	{
		return (this.getWidth()/size);
	}
	public Point center(int size)
	{
		return new Point(center.x-(radius(size)/2),(center.y-radius(size)/2));
	}
	
	public Point rotationCenter(long time, int size, int distance)
	{
		//(6*calendar.get(Calendar.SECOND))-90;
		return new Point((int)((center(size).x +((radius(size)+distance)*(Math.cos(Math.toRadians((Math.pow(radius(size)+distance)), 3/2)/((360*time)/100)))))))),(int)((center(size).y+((radius(size)+distance)*(Math.sin(Math.toRadians(Math.pow(radius(size+distance)((360*time)/100)), 3/2))))))));
	}
	public class SimulationTimer extends TimerTask
	{
		public void run() 
		{
			time++;			
			repaint();
		}
	}
}
