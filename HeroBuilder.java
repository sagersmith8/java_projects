package org.example.heroschema;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class HeroBuilder extends JFrame implements ActionListener, ItemListener//, MouseListener
{
	Hero hero ;
	Hero.Costume costume = new Hero.Costume();
	JTabbedPane attributesPane = new JTabbedPane();
	Color bgColor = new Color(255,0,0);
	BufferedImage buf = new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);
	HeroPicture heroPicture = new HeroPicture("superhero-outline-vector.png");
	String introString = "Welcome to Hero Builder! Fill in the following fields and see your hero come to life";
	JLabel introLabel = new JLabel(introString, JLabel.CENTER);
	JColorChooser colorChooser = new JColorChooser();
	Point[] points ={new Point(293,63),new Point(379,182),new Point(352,365),new Point(355,100),new Point(474,157),new Point(550,77),new Point(128,261),new Point(327,251),new Point(360,218),new Point(207,335),new Point(251,167), new Point(374,313),new Point(263,276),new Point(185,199)};
	JPanel mainPanel = new JPanel(new GridLayout(6,2));
	JLabel nameLabel = new JLabel("Your super hero's name:");
	JTextField nameField = new JTextField(20);
	JLabel genderLabel = new JLabel("Your super hero's gender:");
	JPanel genderPanel = new JPanel(new GridLayout(1,2));
	CheckboxGroup genderGroup = new CheckboxGroup();
	Checkbox maleBox = new Checkbox("Male",genderGroup,false);
	Checkbox femaleBox = new Checkbox("Female",genderGroup,false);
	JLabel nemisisLabel = new JLabel("Nemisis name:");
	JTextField nemisisField = new JTextField("Nemisis");
	JButton addWeaknesses = new JButton("Add weakness");
	JTextField weaknessesField = new JTextField(20);
	JButton addPower = new JButton("Add Power:");
	JTextField powerField = new JTextField(20);
	JLabel sideKickLabel = new JLabel("Does your hero have a Sidekick?");
	String[] yesOrNo = {"No","Yes"};
	JComboBox<String> sideKickBox = new JComboBox<String>(yesOrNo);
	JPanel costumePanel = new JPanel(new GridLayout(9,2));
	JButton [] chooseColorLabels = new JButton[9];
	JTextField [] chooseColorFields = new JTextField[9];
	String [] colorText = {"Hair Color:","Logo Color:", "Boot Color:", "Skin Color:","Costume Color:", "Glove Color:", "Cape Color:", "Underwear Color:", "Belt Color:"};
	JPanel siPanel = new JPanel(new GridLayout(6,2));
	JLabel siNameLabel = new JLabel("Your Hero's Real Name:");
	JTextField siNameField = new JTextField(20);
	JLabel siJobLabel = new JLabel("Your Hero's Job:");
	JTextField siJobField = new JTextField(20);
	JLabel siLayerLabel = new JLabel("Your Hero's Secret Layer:");
	JTextField siLayerField = new JTextField(20);
	JButton siAddClose = new JButton("Add Person on Hero's Secret:");
	JTextField siCloseField = new JTextField(20);
	JLabel origenLabel = new JLabel("Origen:");
	JTextField origenField = new JTextField(20);
	JLabel birthDateLabel = new JLabel("Birthdate:");
	JPanel birthDatePanel = new JPanel(new GridLayout(1,6));
	Calendar todayDate = Calendar.getInstance();
	JLabel birthDayLabel = new JLabel("Day");
	JComboBox<String> birthDayBox = new JComboBox<String>();
	JLabel birthMonthLabel = new JLabel("Month");
	String[] months = {"January","February","March","April","May","June","July","August","October","November","December"};
	JComboBox<String> birthMonthBox = new JComboBox<String>(months);			
	JLabel birthYearLabel = new JLabel("Year");
	JComboBox<String> birthYearBox = new JComboBox<String>();
	JMenuBar jMenuBar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem open = new JMenuItem("Open");
	JMenu about = new JMenu("About");
	JMenuItem info = new JMenuItem("Author: Sage Smith \\n Created: 12/1/13 \\n Artwork: Sage Smith \\n Special Thanks to Clker.com for super hero outline");


	public HeroBuilder()
	{
		file.add(save);
		save.addActionListener(this);
		file.add(open);
		open.addActionListener(this);
		about.add(info);
		jMenuBar.add(file);
		jMenuBar.add(about);
		this.setJMenuBar(jMenuBar);
		this.setLayout(new BorderLayout());
		this.setTitle("Hero Builder");
		this.setBounds(0,0, screenWidth(), screenHeight());
		introLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.add(introLabel, BorderLayout.NORTH);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(genderLabel);
		genderPanel.add(maleBox);
		genderPanel.add(femaleBox);
		mainPanel.add(genderPanel);
		mainPanel.add(nemisisLabel);
		mainPanel.add(nemisisField);
		mainPanel.add(addPower);
		addPower.addActionListener(this);
		powerField.setEditable(false);
		mainPanel.add(powerField);
		mainPanel.add(addWeaknesses);
		mainPanel.add(weaknessesField);
		mainPanel.add(sideKickLabel);
		mainPanel.add(sideKickBox);
		sideKickBox.addActionListener(this);

		for(int i = 0; i < 9; i++)
		{
			chooseColorLabels[i] = new JButton(colorText[i]);
			chooseColorFields[i] = new JTextField(9);
			costumePanel.add(chooseColorLabels[i]);
			chooseColorLabels[i].addActionListener(this);
			costumePanel.add(chooseColorFields[i]);
		}

		siPanel.add(siNameLabel);
		siPanel.add(siNameField);
		siPanel.add(birthDateLabel);
		birthDatePanel.add(birthDayLabel);

		for(int i = 1; i < 32; i++)
		{
			birthDayBox.addItem(Integer.toString(i));
		}

		birthDatePanel.add(birthDayBox);
		birthDatePanel.add(birthMonthLabel);
		birthDatePanel.add(birthMonthBox);
		birthDatePanel.add(birthYearLabel);

		for(int i = todayDate.get(Calendar.YEAR); i > 0; i--)
		{
			birthYearBox.addItem(Integer.toString(i));
		}
		birthDatePanel.add(birthYearBox);
		siPanel.add(birthDatePanel);
		siPanel.add(origenLabel);
		siPanel.add(origenField);
		siPanel.add(siJobLabel);
		siPanel.add(siJobField);
		siPanel.add(siLayerLabel);
		siPanel.add(siLayerField);
		siPanel.add(siAddClose);
		siAddClose.addActionListener(this);
		siPanel.add(siCloseField);

		//heroPicture.addMouseListener(this);
		this.add(heroPicture,BorderLayout.CENTER);
		attributesPane.addTab("General", mainPanel);
		attributesPane.addTab("Secret Identity", siPanel);
		attributesPane.addTab("Costume", costumePanel);
		this.add(attributesPane,BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public static int screenWidth() 
	{
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int screenHeight() 
	{
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(save==e.getSource())
		{
			try
			{
				JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				hero = (Hero) unmarshaller.unmarshal(new File("BasicHero.xml"));// open XML file and read data

				hero.costume.setBelt(chooseColorFields[8].getBackground().toString());
				hero.costume.setBoot(chooseColorFields[2].getBackground().toString());
				hero.costume.setCape(chooseColorFields[6].getBackground().toString());
				hero.costume.setGlove(chooseColorFields[5].getBackground().toString());
				hero.costume.setHair(chooseColorFields[0].getBackground().toString());
				hero.costume.setCostume(chooseColorFields[4].getBackground().toString());
				hero.costume.setLogo(chooseColorFields[1].getBackground().toString());
				hero.costume.setSkin(chooseColorFields[3].getBackground().toString());
				hero.costume.setUnderwear(chooseColorFields[7].getBackground().toString());
				hero.general.setHeroName(nameField.getSelectedText());
				if(genderGroup.getSelectedCheckbox() == maleBox)
					hero.general.setMale(true);
				else
					hero.general.setMale(false);
				hero.general.setNemisis(nemisisField.getText());
				hero.general.setPower(powerField.getText());
				if(sideKickBox.getSelectedIndex() == 0)
					hero.general.setSidekick(true);
				else
					hero.general.setSidekick(false);
				hero.general.setWeakness(weaknessesField.getText());
				String birthDate = months[birthMonthBox.getSelectedIndex()] + "/" + birthDayBox.getSelectedIndex()+ "/" + birthYearBox.getSelectedIndex();
				hero.secretIdentity.setBirthdate(birthDate);
				hero.secretIdentity.setJob(siJobField.getText());
				hero.secretIdentity.setLair(siLayerField.getText());
				hero.secretIdentity.setName(siNameField.getText());
				hero.secretIdentity.setOrigin(origenField.getText());
				hero.secretIdentity.setSecretKeeper(siCloseField.getText());
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.marshal(hero, new File(nameField.getText()+".xml"));
			}
			catch(Exception ex){}
		}
		for(int i = 0; i < 9; i++)
		{
			if(e.getSource()==chooseColorLabels[i])
			{
				bgColor = JColorChooser.showDialog(this, "Choose a Color", Color.white);
				chooseColorFields[i].setBackground(bgColor);
				GT gameThread = new GT(points[i]);
				gameThread.start();
				if(i == 2)
				{
					GT gt = new GT(points[9]);
					gt.start();
				}
				else if(i == 4)
				{
					for(int j = 10; j <13; j++)
					{
						new GT(points[j]).start();
					}
				}
				else if(i ==5)
				{
					GT gt = new GT(points[13]);
					gt.start();
				}
			}
		}
		if(e.getSource()==addPower)
		{
			String power = JOptionPane.showInputDialog(this, "Add Power:");
			if(powerField.getText().equals(""))
				powerField.setText(power);
			else
				powerField.setText(powerField.getText() + ", " +power);
		}

		if(e.getSource()==addWeaknesses)
		{
			String weakness = JOptionPane.showInputDialog(this, "Add Weakness:");
			if(weaknessesField.getText().equals(""))
				weaknessesField.setText(weakness);
			else
				weaknessesField.setText(weaknessesField.getText() + ", " +weakness);
		}

		if(e.getSource()==siAddClose)
		{
			String cp = JOptionPane.showInputDialog(this, "Add Close Person:");
			if(siCloseField.getText().equals(""))
				siCloseField.setText(cp);
			else
				siCloseField.setText(siCloseField.getText() + ", " +cp);
		}
	}

	public static void main(String[] args) 
	{
		new HeroBuilder();
	}

	public class HeroPicture extends JPanel
	{
		public HeroPicture(String s)
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
			Image scaledImage = buf.getScaledInstance(buf.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			g2d.drawImage(scaledImage,(this.getBounds().width/4),0,null);
		}
	}

	public void checkPixels(Point p)
	{
		if(buf.getRGB(p.x, p.y) == -1)
		{
			buf.setRGB(p.x,p.y,bgColor.getRGB());
			this.repaint();
			checkPixels(new Point(p.x+1,p.y));
			checkPixels(new Point(p.x-1,p.y));
			checkPixels(new Point(p.x,p.y-1));
			checkPixels(new Point(p.x,p.y+1));
		}									
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
			try
			{
				checkPixels(point);
				repaint();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	/*public void mouseClicked(MouseEvent e) 
	{
		System.out.println(e.getX() + " " + e.getY()+ " " + buf.getRGB(e.getX(), e.getY()));
	}

	public void mouseEntered(MouseEvent arg0) {		
	}
	public void mouseExited(MouseEvent arg0) {		
	}
	public void mousePressed(MouseEvent arg0) {		
	}
	public void mouseReleased(MouseEvent arg0) {		
	}*/
}
