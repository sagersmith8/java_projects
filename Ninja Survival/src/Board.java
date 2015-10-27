import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Board extends JPanel{
	Utils utils;
	MainFrame mainframe;
	BufferedImage ninja, background;
	BufferedImage [] level = new BufferedImage[10];
	LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	
	public Board(Utils u, MainFrame m){
		try{
			for(int i = 0; i < level.length; i++)
			{
				level[i] = ImageIO.read(new File("level"+i+".png"));
			}
			ninja = ImageIO.read(new File("ninja.png"));
			background = ImageIO.read(new File("background.png"));
		}
		
		catch(Exception ex){
			System.err.print("Error reading in image Board");
		}
		
		utils = u;
		mainframe = m;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics g2d = (Graphics2D)g;/*
		if(utils.display){
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2d.setColor(Color.white);
			g2d.drawImage(background, 0, 0, null);
			g2d.drawString("Wave:"+utils.waveCount,500,500);
			try{
				Thread.sleep(5000);
			}
			catch(Exception ex){
				System.err.print("Error sleeping board");
			}
			utils.display =false;
		}	*/
		g2d.drawImage(level[utils.levelNum], 0, 0, null);    
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
		g2d.setColor(Color.red); 
		g2d.drawString("Kills: "+utils.killCount, 10, 20);
		g2d.drawString("Wave: "+utils.waveCount, 100, 20);
		
		if(!utils.ninjaDead)
		{
			g2d.drawImage(ninja, utils.x, utils.y-34, null);
		}
		else
		{
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 48)); 
			g2d.setColor(Color.red); 
			g2d.drawString("Gameover", 440, 200);
			
			for(int i = 0; i < enemyList.size(); i++)
			{
				enemyList.get(i).notDead = false;
				enemyList.remove(enemyList.get(i));
			}
		}
		
		if(enemyList.size()>0)
		{
			for(int i = 0; i < enemyList.size(); i++)
			{
				Enemy enemy = enemyList.get(i);
				g2d.drawImage(enemy.enemyImg, enemy.x,enemy.y-34,null);
			}
		}
	}
}
