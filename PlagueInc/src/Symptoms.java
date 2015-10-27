/**
 * 
 * @author Sage
 *	This class deals with how deadly the disease is
 *	it allows you to upgrade your deadliness
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Symptoms extends JFrame implements ActionListener
{
		JPanel buttonPanel = new JPanel(new GridLayout(3,3));
		JButton returnButton = new JButton("Return");
		JButton [] buttonArray = new JButton[9];
		String [] buttonNames = {"Open Multiple Programs","Log-Off","Attack Windows","Attack Macantosh OS X","Hardrive Attack","Erase Files","Use Magic","Break Computer","Open Internet Explorer"};
		Activator act;
		Data data;
		Statistics stat;
		public Symptoms(Activator a, Data d, Statistics s)
		{
			data = d;
			act = a;
			stat = s;
			this.setLayout(new BorderLayout());
			for(int i = 0; i < buttonArray.length; i++)
			{
				buttonArray[i] = new JButton(buttonNames[i] + " " +Integer.toString(data.getInactiveSymptom().get(i)));
				buttonArray[i].addActionListener(this);
				buttonPanel.add(buttonArray[i]);
			}
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			this.setTitle("Symptoms");
			this.setBounds(0,0,997,530);
			this.add(buttonPanel, BorderLayout.CENTER);
			this.add(returnButton, BorderLayout.SOUTH);
			returnButton.addActionListener(this);
			this.setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(data.getBeginCure()==false)
			{
				JOptionPane.showMessageDialog(null,"A new Minor Computer infection has been discovered");
				data.setBeginCure(true);
			}
			for (int i = 0; i < buttonArray.length; i++)
			{
				if(buttonArray[i] == e.getSource())
				{
					data.setFunds(data.getFunds()-1000*data.getInactiveSymptom().get(i));
					data.getInactiveSymptom().add(i,data.getInactiveSymptom().get(i)+1);
					buttonArray[i].setText(buttonNames[i] + " " + Integer.toString(data.getInactiveSymptom().get(i)));
					data.setDeathRate(data.getDeathRate()-5);
					data.setDeadlyness((100-data.getDeadlyness()-data.getInactiveSymptom().get(i)));
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
