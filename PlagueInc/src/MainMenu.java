/**
 * @author Sage
 * This class runs. It allows you to pick your
 * disease and is the only class with a main method
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainMenu implements ActionListener
{
	JFrame f1;
	JPanel master = new JPanel(new BorderLayout());
	JPanel [] optionsPanels = new JPanel[3];
	JButton [] optionsButtons = new JButton[3];
	JLabel[] optionsLabels = new JLabel[3];
	Statistics f;
	Data data = new Data();
	String [] pictures = {"virus.png","worm.png","trojanHorse.png"};
	String [] optionsButtonsStrings = {"Virus", "Worm", "Trojan Horse"};
	String[] optionsLabelsStrings = {"Viruses are programs that have /n to run dependently with another program ", "Worms are independent programs that /n can run and spread indepentently", "Trojan Horses fool people into running them"};
	public MainMenu()
	{
		f1 = new JFrame();
		f1.setBounds(0,0,997,530);
		f1.setTitle("Plague Inc.");
		for(int i = 0; i < optionsPanels.length;i++)
		{
			optionsPanels[i] = new JPanel(new GridLayout(1,3));
		}
		for(int i = 0; i < optionsPanels.length;i++)
		{
			optionsButtons[i] = new JButton(optionsButtonsStrings[i]);
			optionsButtons[i].addActionListener(this);
			optionsLabels[i] = new JLabel(optionsLabelsStrings[i]);
			optionsPanels[0].add(optionsLabels[i]);
			optionsPanels[1].add(optionsButtons[i]);
			optionsPanels[2].add(new CenterPanel(pictures[i]));
		}		
		master.add(optionsPanels[0], BorderLayout.NORTH);
		master.add(optionsPanels[1], BorderLayout.SOUTH);
		master.add(optionsPanels[2], BorderLayout.CENTER);
		f1.add(master);
		f1.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				f1.dispose();
				System.exit(0);
			}
		});
		
		f1.setVisible(true);
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(f1, "Welcome to Plague Inc. Prepare your plague and destroy every computer in the world", "Begin Game", JOptionPane.INFORMATION_MESSAGE);
		f = new Statistics(data);
		f1.requestFocus();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		new MainMenu();
		
	}

	public class CenterPanel extends JComponent
	{
		BufferedImage buf = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
		public CenterPanel(String s)
		{
			try
			{
				buf = ImageIO.read(new File(s));
			}
			catch(IOException ioe)
			{
				System.err.println("Could not read image");
			}
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponents(g);
			Graphics g2d = (Graphics2D)g;
			g2d.drawImage(buf,0,0,null);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i = 0; i < optionsButtons.length; i++)
		{
			if(e.getSource() == optionsButtons[i])
			{
				String name = JOptionPane.showInputDialog("Please name your " + optionsButtons[i].getText());
				f.start();
				new VirusGame(optionsButtons[i].getText(), name,data, f);
				f1.dispose();
			}
		}
	}
}
