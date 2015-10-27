/**
 * @author Sage
 * This runs the main game loop. 
 * 	It contains a thread that is used to run the recursive method and
 * 	paint the pixels to the desired color
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VirusGame extends JComponent implements ActionListener, Activator
{
	ArrayList<Point> points = new ArrayList<Point>();
	Data data;
	int couter = 0, times = 1;
	final JFrame vg;
	String type, name;
	Statistics stat;
	BufferedImage buf = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
	Point mouse;
	final int RED = -1237980, PURPLE = -6075996, WHITE = -1, BLACK = -15987180, BLUE = -16476444;;
	boolean started = false, poop = false;
	JMenuItem newGame = new JMenuItem("New Game");
	JMenuItem pause = new JMenuItem("Pause");
	JMenuItem quit = new JMenuItem("Quit");
	JMenuItem transmissions = new JMenuItem("Transmissions");
	JMenuItem symptoms = new JMenuItem("Symptoms");
	JMenuItem about = new JMenuItem("About");
	public VirusGame(String s, String n, Data d, Statistics ss)
	{
		points.add(new Point(128,89));
		points.add(new Point(109,144));
		points.add(new Point(99,189));
		points.add(new Point(59,56));
		points.add(new Point(347,29));
		points.add(new Point(338,30));
		points.add(new Point(267,16));
		points.add(new Point(245,17));
		points.add(new Point(176,39));
		points.add(new Point(162,32));
		points.add(new Point(186,28));
		points.add(new Point(202,37));
		points.add(new Point(219,33));
		points.add(new Point(215,27));
		points.add(new Point(107,203));
		points.add(new Point(136,222));
		points.add(new Point(141,217));
		points.add(new Point(139,230));
		points.add(new Point(143,226));
		points.add(new Point(149,236));
		points.add(new Point(159,250));
		points.add(new Point(180,208));
		points.add(new Point(190,214));
		points.add(new Point(194,212));
		points.add(new Point(203,248));
		points.add(new Point(185,265));
		points.add(new Point(220,288));
		points.add(new Point(229,267));
		points.add(new Point(237,262));
		points.add(new Point(244,266));
		points.add(new Point(168,282));
		points.add(new Point(177,305));
		points.add(new Point(217,337));
		points.add(new Point(228,353));
		points.add(new Point(225,379));
		points.add(new Point(201,363));
		points.add(new Point(247,392));
		points.add(new Point(474,382));
		points.add(new Point(491,380));
		points.add(new Point(546,350));
		points.add(new Point(510,340));
		points.add(new Point(501,339));
		points.add(new Point(480,348));
		points.add(new Point(460,347));
		points.add(new Point(463,326));
		points.add(new Point(483,326));
		points.add(new Point(509,323));
		points.add(new Point(519,297));
		points.add(new Point(472,283));
		points.add(new Point(454,280));
		points.add(new Point(441,281));
		points.add(new Point(445,263));
		points.add(new Point(471,257));
		points.add(new Point(498,240));
		points.add(new Point(526,251));
		points.add(new Point(552,256));
		points.add(new Point(520,223));
		points.add(new Point(466,225));
		points.add(new Point(435,237));
		points.add(new Point(415,242));
		points.add(new Point(402,249));
		points.add(new Point(389,249));
		points.add(new Point(378,256));
		points.add(new Point(371,249));
		points.add(new Point(374,240));
		points.add(new Point(384,231));
		points.add(new Point(366,228));
		points.add(new Point(375,210));
		points.add(new Point(365,195));
		points.add(new Point(388,166));
		points.add(new Point(413,189));
		points.add(new Point(454,188));
		points.add(new Point(437,167));
		points.add(new Point(499,192));
		points.add(new Point(459,222));
		points.add(new Point(518,226));
		points.add(new Point(540,197));
		points.add(new Point(548,222));
		points.add(new Point(577,210));
		points.add(new Point(571,198));
		points.add(new Point(570,160));
		points.add(new Point(534,165));
		points.add(new Point(515,173));
		points.add(new Point(517,159));
		points.add(new Point(513,145));
		points.add(new Point(399,136));
		points.add(new Point(388,138));
		points.add(new Point(418,115));
		points.add(new Point(410,99));
		points.add(new Point(338,26));
		points.add(new Point(368,57));
		points.add(new Point(389,97));
		points.add(new Point(408,94));
		points.add(new Point(434,70));
		points.add(new Point(448,73));
		points.add(new Point(482,57));
		points.add(new Point(496,64));
		points.add(new Point(483,93));
		points.add(new Point(475,88));
		points.add(new Point(477,83));
		points.add(new Point(477,78));
		points.add(new Point(508,119));
		points.add(new Point(593,107));
		points.add(new Point(588,134));
		points.add(new Point(584,145));
		points.add(new Point(572,166));
		points.add(new Point(532,164));
		points.add(new Point(515,171));
		points.add(new Point(517,155));
		points.add(new Point(518,155));
		points.add(new Point(536,198));
		points.add(new Point(549,223));
		points.add(new Point(570,212));
		points.add(new Point(554,198));
		points.add(new Point(568,196));
		points.add(new Point(649,194));
		points.add(new Point(609,182));
		points.add(new Point(599,159));
		points.add(new Point(599,159));
		points.add(new Point(568,164));
		points.add(new Point(580,144));
		points.add(new Point(591,138));
		points.add(new Point(616,145));
		points.add(new Point(616,132));
		points.add(new Point(611,105));
		points.add(new Point(585,134));
		points.add(new Point(690,126));
		points.add(new Point(690,150));
		points.add(new Point(644,206));
		points.add(new Point(657,181));
		points.add(new Point(677,196));
		points.add(new Point(685,186));
		points.add(new Point(693,202));
		points.add(new Point(709,220));
		points.add(new Point(713,209));
		points.add(new Point(720,202));
		points.add(new Point(736,232));
		points.add(new Point(736,232));
		points.add(new Point(723,234));
		points.add(new Point(717,264));
		points.add(new Point(716,280));
		points.add(new Point(653,254));
		points.add(new Point(541,359));
		points.add(new Point(798,375));
		points.add(new Point(818,424));
		points.add(new Point(886,426));
		points.add(new Point(908,413));
		points.add(new Point(846,295));
		points.add(new Point(828,292));
		points.add(new Point(761,278));
		points.add(new Point(755,268));
		points.add(new Point(774,220));
		points.add(new Point(787,251));
		points.add(new Point(803,147));
		points.add(new Point(803,126));
		points.add(new Point(803,127));
		points.add(new Point(773,152));
		points.add(new Point(765,138));
		stat = ss;
		data = d;
		type = s;
		name = n;
		vg = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		vg.setJMenuBar(menuBar);
			JMenu file = new JMenu("File");
			menuBar.add(file);
				file.add(newGame);
				newGame.addActionListener(this);
				file.add(pause);
				pause.addActionListener(this);
				file.add(quit);
				quit.addActionListener(this);
			JMenu game = new JMenu("Game");
			menuBar.add(game);
				game.add(transmissions);
				transmissions.addActionListener(this);
				game.add(symptoms);
				symptoms.addActionListener(this);
			JMenu options = new JMenu("Options");
			menuBar.add(options);
				options.add(about);
				about.addActionListener(this);
		vg.setBounds(0,0,997,530);
		vg.setTitle("Plague: " + s);

		try
		{
			buf = ImageIO.read(new File("map.png"));
		}
		catch(IOException ioe)
		{
			System.err.println("Could not read image");
		}
		
		vg.add(this);
		vg.setVisible(true);
		this.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent arg0)
			{	
				if(started == false)
				{
					GT z = new GT(arg0.getPoint());
					JOptionPane.showMessageDialog(vg, "Congrajulations " + name + " has infected it's first country!", "Congradulations", JOptionPane.INFORMATION_MESSAGE);
					z.start();
					started = true;
				}
			}
		});
		vg.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				vg.dispose();
				System.exit(0);
			}
		});
	}
	public void checkPixels(Point p)
	{
		couter++;
		if(data.getBeginCure() == true)
		{
			data.setCure(data.getCure()+1);
		}
		data.setRemainingComputers(data.getRemainingComputers()-data.getDeathRate());
		if(couter % 100 == 0)
		{
			GT gt = new GT(packet());
			gt.start();
		}
		if(buf.getRGB(p.x, p.y) == WHITE)
		{
			try 
			{
				Thread.sleep(data.getTransmissionRate());
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			data.setFunds(data.getFunds()+1);
			data.setInfectedDevices(data.getInfectedDevices()+1);
			stat.setLabel();
			buf.setRGB(p.x,p.y,RED);
			this.repaint();
			checkPixels(new Point(p.x+1,p.y));
			checkPixels(new Point(p.x-1,p.y));
			checkPixels(new Point(p.x,p.y+1));
			checkPixels(new Point(p.x,p.y-1));
		}									
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics g2d = (Graphics2D)g;
		g2d.drawImage(buf,0,0,null);
	}
	
	public class GT extends Thread
	{
		Point point;
		public GT(Point p)
		{
			point = p;
		}
		public void run()
		{
			checkPixels(point);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(newGame == e.getSource())
		{
			vg.dispose();
			new MainMenu();
		}
		else if(pause == e.getSource())
		{
			
		}
		else if(quit == e.getSource())
		{
			int quitInt = JOptionPane.showConfirmDialog(quit, "Are You sure you want to quit", "Quit?", JOptionPane.YES_NO_OPTION);
			if(quitInt == JOptionPane.YES_OPTION)
			{
				vg.dispose();
				System.exit(0);
			}
		}
		else if(transmissions == e.getSource())
		{
			vg.setVisible(false);
			new Transmisions(this,data,stat);
		}
		else if(symptoms == e.getSource())
		{
			vg.setVisible(false);
			new Symptoms(this,data,stat);
		}
		else if(about == e.getSource())
		{
			JOptionPane.showMessageDialog(about, "Plague was made by Sage Smith 2013", "About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public Point packet()
	{
		int i = (int)(Math.random()*points.size());
		return points.get(i);
	}
	@Override
	public void activate() 
	{
		vg.setVisible(true);
	}
}