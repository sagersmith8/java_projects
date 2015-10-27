package population;

import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Sage
 */
public class Population extends JFrame
{
	JLabel popLab = new JLabel();
	int population = 313933954;
	public Population()
	{
		this.setBounds(0,0,210,100);
		this.setTitle("Population");
		this.add(popLab, BorderLayout.CENTER);
		this.setVisible(true);
		Timer time = new Timer();
		time.scheduleAtFixedRate(new Task1(), 0, 250);
		Timer time2 = new Timer();
		time2.scheduleAtFixedRate(new Task2(),0,500);
	}
	public static void main(String[] args) 
	{
		new Population();
	}
	
	public class Task1 extends TimerTask
	{
		@Override
		public void run() 
		{
			population++;
			popLab.setText(Integer.toString(population));
		}
	}
	public class Task2 extends TimerTask
	{
		@Override
		public void run() 
		{
			population--;
			popLab.setText(Integer.toString(population));
		}
	}
}
