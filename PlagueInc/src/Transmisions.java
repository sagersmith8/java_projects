/**
 * 
 * @author Sage
 * This class deals with how fast the computer disease is transmitted
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Transmisions extends JFrame implements ActionListener
{
	JPanel buttonPanel = new JPanel(new GridLayout(3,3));
	JButton returnButton = new JButton("Return");
	JButton [] buttonArray = new JButton[9];
	String [] buttonNames = {"Internet","Email","Flash drive","Atached File","Hacked","Physically put on there","magic","pop-ups","spam"};
	Activator act;
	Data data;
	Statistics stat;
	public Transmisions(Activator a, Data d, Statistics s)
	{
		data = d;
		act = a;
		stat = s;
		this.setLayout(new BorderLayout());
		for(int i = 0; i < buttonArray.length; i++)
		{
			buttonArray[i] = new JButton(buttonNames[i]+ " " + Integer.toString(data.getInactiveTransmission().get(i)));
			buttonArray[i].addActionListener(this);
			buttonPanel.add(buttonArray[i]);
		}
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle("Transmissions");
		this.setBounds(0,0,997,530);
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(returnButton, BorderLayout.SOUTH);
		returnButton.addActionListener(this);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < buttonArray.length; i++)
		{
			if(buttonArray[i] == e.getSource())
			{
				data.setFunds(data.getFunds()-1000*data.getInactiveTransmission().get(i));
				data.getInactiveTransmission().add(i,data.getInactiveTransmission().get(i)+1);
				buttonArray[i].setText(buttonNames[i] + " " +Integer.toString(data.getInactiveTransmission().get(i)));
				data.setTransmissionRate(data.getTransmissionRate()-5);
				data.setTransmitability(100-data.getTransmissionRate()-data.getInactiveTransmission().get(i));
				stat.setLabel();
			}
			else if(returnButton == e.getSource())
			{
				this.dispose();
				act.activate();
			}
		}		
	}
}
