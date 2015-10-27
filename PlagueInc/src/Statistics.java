/**
 * 
 * @author Sage
 *	This class deals with displaying the statistics about
 *	your computer disease
 */
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Statistics extends Thread
{
	Data data;
	JLabel [] jLabs = new JLabel[12];
	String [] strings = new String[12]; 
	public  Statistics(Data d) 
	{
		data = d;
		strings[0]="Deadlyness";
		strings[1]= Double.toString(data.getDeadlyness());
		strings[2]="Transmitability";
		strings[3]=Double.toString(data.getTransmitability());
		strings[4]="Remaining Devices";
		strings[5]=Long.toString(data.getRemainingComputers());
		strings[6]="Infected Devices";
		strings[7]=Long.toString(data.getInfectedDevices());
		strings[8] = "Funds";
		strings[9] = '$'+Long.toString(data.getFunds());
		strings[10] = "Cure";
		strings[11]= Long.toString(data.getCure());
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(6,2));
		for (int i = 0; i < jLabs.length; i++) 
		{
			jLabs[i] = new JLabel(strings[i]);
			frame.add(jLabs[i]);
		}
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Statistics");
		frame.setBounds(997,0,300,530);
		frame.setVisible(true);
	}
	
	public void setLabel()
	{
		jLabs[1].setText(Double.toString(data.getDeathRate()));
		jLabs[3].setText(Double.toString(data.getTransmitability()));
		jLabs[5].setText(Long.toString(data.getRemainingComputers()));
		jLabs[7].setText(Long.toString(data.getInfectedDevices()));
		jLabs[9].setText('$'+Long.toString(data.getFunds()));
		jLabs[11].setText(Long.toString(data.getCure()));
	}
	
	public void run()
	{
		while(data.getRemainingComputers() > 0 && data.getInfectedDevices() > 0)
		{
			if(data.getCure()==1000000000)
			{
				JOptionPane.showMessageDialog(null, "Your infection has been eradicated");
			}
			if(data.getRemainingComputers()==0)
			{
				JOptionPane.showMessageDialog(null, "You Win");
				this.destroy();
			}
			try 
			{
				Thread.sleep(data.getDeathRate());
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			data.setRemainingComputers(data.getInfectedDevices()-1);
			setLabel();
		}
	}
}
