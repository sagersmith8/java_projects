import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Map extends JFrame implements ActionListener, ItemListener
{
	JMenuBar jmb = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu about = new JMenu("About");
	JMenuItem newGame = new JMenuItem("New Game");
	JMenuItem highscores = new JMenuItem("High Scores");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem info = new JMenuItem("About");
	int x = 1,y=0, gallons = 150, money = 0, type = 0, spot=0;
	Buttons jButs[][] = new Buttons[40][40];
	Point [] refineLoc = new Point[40];
	int [] inUse = new int[40];
	JPanel buttons = new JPanel(new GridLayout(40,40));
	JPanel statsPanel = new JPanel(new GridLayout(2,2));
	CheckboxGroup chg = new CheckboxGroup();
	JPanel checkPanel = new JPanel(new GridLayout(3,1));
	Checkbox mine = new Checkbox("Mine:", chg, true);
	Checkbox refine = new Checkbox("Build Refinery:", chg, false);
	Checkbox roadB = new Checkbox("Build Road:", chg, false);
	JLabel moneyLab = new JLabel("Assets:");
	JLabel moneys = new JLabel();
	JLabel fuelLab = new JLabel("Gallons:");
	JLabel fuels = new JLabel();
	JLabel errors = new JLabel();
	JPanel southPanel = new JPanel(new GridLayout(1,3));
	BufferedImage background, car, refinery,road, green;
	BufferedImage mined[] = new BufferedImage[6];
	private final String right = "right", left = "left", up = "up", down = "down";

	public void initiator()
	{
		x = 1;
		y=0;
		gallons = 150;
		money = 0;
		type = 0;
		spot=0;
		for(int i = 0; i <40; i++)
		{
			refineLoc[i]= new Point(100,100);
			inUse[i] = 0;
		}
		
		refineLoc[0] = new Point(0,0);
		moneys.setText(""+money);
		fuels.setText(""+gallons);
		
		for(int i = 0; i < 40; i++)
		{
			for(int j = 0; j <40; j++)
			{
				jButs[i][j].setEnabled(true);
				jButs[i][j].setBufferedImage(green);
			}
		}
		
		jButs[0][0].setState(3);
		jButs[0][0].setBufferedImage(refinery);
	}
	
	public class MusicThread extends Thread
	{
		public void run()
		{
			new Music();
		}
	}
	
	public Map()
	{
		MusicThread mt = new MusicThread();
		mt.start();
		statsPanel.add(moneyLab);
		statsPanel.add(moneys);
		statsPanel.add(fuelLab);
		statsPanel.add(fuels);
		
		try
		{
			background = ImageIO.read(new File("board.png"));
			car = ImageIO.read(new File("dumptruck.png"));
			refinery = ImageIO.read(new File("refinery.png"));
			road = ImageIO.read(new File("road.png"));
			green = ImageIO.read(new File("green.png"));
			mined[0] = ImageIO.read(new File("mined.png"));
			mined[1] = ImageIO.read(new File("mined2.png"));
			mined[2] = ImageIO.read(new File("mined3.png"));
			mined[3] = ImageIO.read(new File("mined4.png"));
			mined[4] = ImageIO.read(new File("mined5.png"));
			mined[5] = ImageIO.read(new File("mined6.png"));
		}
		
		catch(IOException ioe)
		{
			System.err.println("Could not read image");
		}

		for(int i = 0; i < 40; i++)
		{
			for(int j = 0; j <40; j++)
			{
				jButs[i][j] = new Buttons();
				jButs[i][j].addActionListener(this);
				buttons.add(jButs[i][j]);
			}
		}
		initiator();
		this.add(buttons, BorderLayout.CENTER);
		mine.addItemListener(this);
		refine.addItemListener(this);
		roadB.addItemListener(this);
		southPanel.add(statsPanel);
		southPanel.add(checkPanel);
		southPanel.add(errors);
		checkPanel.add(mine);
		checkPanel.add(refine);
		checkPanel.add(roadB);
		this.setJMenuBar(jmb);
		jmb.add(file);
		jmb.add(about);
		file.add(newGame);
		newGame.addActionListener(this);
		file.add(highscores);
		highscores.addActionListener(this);
		about.add(info);
		info.addActionListener(this);
		file.add(exit);
		exit.addActionListener(this);
		this.add(southPanel,BorderLayout.SOUTH);
		this.setBounds(0,0,800,600);
		this.setTitle("NAOM: Nick's American Oil Monopoly");
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(this, "Destroy the forest, while keeping profits postitive");
	}
	
	public static void main(String[] args) 
	{
		new Map();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(highscores == e.getSource())
		{
		}
		
		else if(exit == e.getSource())
		{
			System.exit(0);
		}
		
		else if(info == e.getSource())
		{
			JOptionPane.showMessageDialog(null, "Made by Sage for Nick\nP.6 2014");
		}
		
		else if(newGame == e.getSource())
		{
			initiator();
		}
		
		for(int i = 0; i < 40; i++)
		{
			for(int j = 0; j < 40; j++)
			{
				if(e.getSource() == jButs[i][j])
				{
					if(jButs[i][j].getState() == 0)
					{
						if(type == 0)
						{
							if(closest(new Point(i,j))!=100)
							{
								Thread gt = new GameThread(i,j);
								gt.start();
							}
						}

						else if(type == 1)
						{
							spot++;
							refineLoc[spot] = new Point(i,j);
							jButs[i][j].setState(3);
							jButs[i][j].setBufferedImage(refinery);
							money-=4000;
							moneys.setText(""+money);
						}

						else if(type == 2)
						{
							jButs[i][j].setState(1);
							jButs[i][j].setBufferedImage(road);
							money-=500;
							moneys.setText(""+money);
						}
					}
				}
			}
			repaint();
		}
	}

	public class GameThread extends Thread
	{
		int x,y;
		Stack<String> direction = new Stack<String>();
		
		public GameThread(int xs, int ys)
		{
			x=xs;
			y=ys;
		}
		
		public void moveTo(Point to, int whx, int why)
		{
			if(gallons<=0)
			{
				errors.setText("You were charged $4 a gallon");
				money -= ((4*Math.abs(gallons))+ (100*4));
			}
			
			boolean call = false;
			if(to.x>whx)
			{
				call = true;
				whx+=1;
				if(jButs[whx][why].getState()==0)
				{
					money-=500;
				}
				
				jButs[whx][why].setBufferedImage(car);
				repaint();
				
				try
				{
					Thread.sleep(100);
				}
				
				catch(Exception ex)
				{
					System.out.println("Problem Sleeping");
				}
				
				jButs[whx][why].setState(2);
				jButs[whx][why].setBufferedImage(road);
				repaint();
				direction.add(left);
				money-=10;
				gallons-=5;
				moneys.setText(""+money);
				fuels.setText(""+gallons);
			}
			
			else if(to.x<whx)
			{
				call = true;
				whx-=1;
				
				if(jButs[whx][why].getState()==0)
				{
					money-=500;
				}
				
				jButs[whx][why].setBufferedImage(car);
				repaint();
				
				try
				{
					Thread.sleep(100);
				}
				
				catch(Exception ex)
				{
					System.out.println("Problem Sleeping");
				}
				
				jButs[whx][why].setState(2);
				jButs[whx][why].setBufferedImage(road);
				repaint();
				direction.add(right);
				money-=10;
				gallons-=5;
				moneys.setText(""+money);
				fuels.setText(""+gallons);
			}
			
			if(to.y>why)
			{
				call = true;
				why++;
				
				if(jButs[whx][why].getState()==0)
				{
					money-=500;
				}
				
				jButs[whx][why].setBufferedImage(car);
				repaint();
				
				try
				{
					Thread.sleep(100);
				}
				
				catch(Exception ex)
				{
					System.out.println("Problem Sleeping");
				}
				
				jButs[whx][why].setState(2);
				jButs[whx][why].setBufferedImage(road);
				repaint();
				direction.add(up);
				money-=10;
				gallons-=5;
				moneys.setText(""+money);
				fuels.setText(""+gallons);
			}
			
			else if(to.y<why)
			{
				call = true;
				why--;
				
				if(jButs[whx][why].getState()==0)
				{
					money-=500;
				}
				
				jButs[whx][why].setBufferedImage(car);
				repaint();
				
				try
				{
					Thread.sleep(100);
				}
				
				catch(Exception ex)
				{
					System.out.println("Problem Sleeping");
				}
				
				jButs[whx][why].setState(2);
				jButs[whx][why].setBufferedImage(road);
				repaint();
				direction.add(down);
				money-=10;
				gallons-=5;
				moneys.setText(""+money);
				fuels.setText(""+gallons);
			}

			if(call)
			moveTo(to, whx, why);
		}
		
		public void run()
		{
			int k = closest(new Point(x,y));
			Point closest = refineLoc[k];
			inUse[k] =1;
			errors.setText("You are being charged $500 new road");
			
			for(int t = 0; t < 7; t++)
			{	
				moveTo(new Point(x,y), closest.x, closest.y);
				errors.setText("It cost $200 and 20 gallons to refine");
				/*for(int i = closest.x+1; i <x; i++)
				{
					if(jButs[i][closest.y].getState()==0)
					{
						money-=50;
					}
					jButs[i][closest.y].setBufferedImage(car);
					repaint();
					try{
						Thread.sleep(100);
					}
					catch(Exception ex){
						System.out.println("Problem Sleeping");
					}
					jButs[i][closest.y].setState(2);
					jButs[i][closest.y].setBufferedImage(road);
					repaint();
					direction.add(left);
					money--;
					gallons--;
					moneys.setText(""+money);
					fuels.setText(""+gallons);
				}
				for(int j = closest.y; j <y+1; j++)
				{
					if(jButs[x][j].getState()==0)
					{
						money-=50;
					}
					jButs[x][j].setBufferedImage(car);
					repaint();
					try{
						Thread.sleep(100);
					}
					catch(Exception ex){
						System.out.println("Problem Sleeping");
					}
					jButs[x][j].setState(2);
					jButs[x][j].setBufferedImage(road);
					repaint();
					if(j>closest.y)
					{
						direction.add(up);
					}
					money--;
					gallons--;
					moneys.setText(""+money);
					fuels.setText(""+gallons);
				}*/
				int xs = x, ys = y;
				while(!direction.empty())
				{
					if(gallons<=0)
					{
						errors.setText("You were charged $4 a gallon");
						money -= ((4*Math.abs(gallons))+ (100*4));
					}
					money-=10;
					gallons-=10;
					moneys.setText(""+money);
					fuels.setText(""+gallons);
					if(direction.peek().equals(left))
					{
						jButs[xs][ys].setBufferedImage(road);
						
						if(t<6)
							jButs[x][y].setBufferedImage(mined[t]);
						xs--;
						
						try
						{
							Thread.sleep(100);
						}
						
						catch(Exception ex){}
						
						repaint();
						jButs[xs][ys].setBufferedImage(car);
					}
					
					else if(direction.peek().equals(right))
					{
						jButs[xs][ys].setBufferedImage(road);
						if(t<6)
							jButs[x][y].setBufferedImage(mined[t]);
						xs++;
						
						try
						{
							Thread.sleep(100);
						}
						
						catch(Exception ex){}
						
						repaint();
						jButs[xs][ys].setBufferedImage(car);
					}
					
					else if(direction.peek().equals(up))
					{
						jButs[xs][ys].setBufferedImage(road);
						
						if(t<6)
							jButs[x][y].setBufferedImage(mined[t]);
						ys--;
						
						try
						{
							Thread.sleep(100);
						}
						
						catch(Exception ex){}
						
						repaint();
						jButs[xs][ys].setBufferedImage(car);
					}
					else if(direction.peek().equals(down))
					{
						jButs[xs][ys].setBufferedImage(road);
						
						if(t<6)
							jButs[x][y].setBufferedImage(mined[t]);
						ys++;
						
						try
						{
							Thread.sleep(100);
						}
						catch(Exception ex){}
						
						repaint();
						jButs[xs][ys].setBufferedImage(car);
					}
					
					repaint();
					direction.pop();
					
					try
					{
						Thread.sleep(100);
					}
					
					catch(Exception ex)
					{
						System.out.println("Error Sleeping");
					}
				}

				jButs[xs][ys].setBufferedImage(road);

				if(t==6)
				{
					jButs[x][y].setBufferedImage(road);
				}
				
				money-=200;
				gallons-=20;
				jButs[closest.x][closest.y].setBufferedImage(refinery);
				money+=1000;
				moneys.setText(""+money);
				gallons+=100;
				fuels.setText(""+gallons);
				repaint();
			}
			
			errors.setText("You earned $7000 and made 100 gallons");
			checkWin();
			inUse[k] = 0;
		}
	}

	public int closest(Point p)
	{
		Queue<Double> queue = new ArrayDeque<Double>();
		double tempDou;
		Queue<Integer> queue1 = new ArrayDeque<Integer>();
		int tempInt;
		
		for(int i = 0; i < refineLoc.length; i++)
		{
			if(refineLoc[i].x!=100 && inUse[i]==0)
			{
				double d = Math.sqrt(Math.pow((refineLoc[i].x-p.x),2)+Math.pow((refineLoc[i].y-p.y),2));
				queue.add(d);
				queue1.add(i);
			}
		}
		try{
			tempDou = queue.remove();
			tempInt = queue1.remove();
		}
		
		catch(Exception e){
			errors.setText("Build more refineries to get more trucks");
			return 100;
		}

		while(!queue.isEmpty())
		{
			if(queue.peek()<tempDou)
			{
				tempDou = queue.remove();
				tempInt = queue1.remove();
			}
			
			else
			{
				queue.remove();
				queue1.remove();
			}
		}
		return tempInt;
	}

	public void checkWin()
	{
		int num = 0;
		for(int i = 0; i < jButs[0].length; i++)
		{
			for(int j = 0; j < jButs.length; j++)
			{
				if(jButs[i][j].getState()!=0)
				{
					num++;
				}
			}
		}

		if(num==40)
		{
			JOptionPane.showMessageDialog(this, "Congradgulations you have destroyed the forest!");
		}

		else if(money <=0)
		{
			JOptionPane.showMessageDialog(this, "Gameover you loser");
			for(int i = 0; i < jButs[0].length; i++)
			{
				for(int j = 0; j < jButs.length; j++)
				{
					jButs[i][j].setEnabled(false);
				}
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getSource()==mine)
		{
			type=0;
		}
		
		else if(e.getSource()==refine)
		{
			type=1;
		}
		
		else if(e.getSource()==roadB)
		{
			type=2;
		}
	}
}
