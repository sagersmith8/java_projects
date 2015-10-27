  import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Board extends JPanel {
	int p1Num = 0;
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	Mainframe mainframe;
	BufferedImage background;
	BufferedImage [] enemy = new BufferedImage[5];
	BufferedImage[] player1 = new BufferedImage[6];
	boolean nextWave=true, score=false;
	
	public Board(Mainframe m){
		mainframe = m;
		try{
			background = ImageIO.read(new File("Background.png"));
			for(int i = 0; i < player1.length; i++){
				player1[i] = ImageIO.read(new File("player1."+i+".png"));
			}
			for(int i = 0; i < enemy.length; i++){
				enemy[i] = ImageIO.read(new File("enemy"+i+".png"));
			}
		}
		catch(Exception ex){
			System.err.print("Problem getting image");
		}
		this.setPreferredSize(new Dimension(background.getWidth(),background.getHeight()));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics g2d = (Graphics2D)g;
		g2d.drawImage(background, 0,0, null);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
		g2d.setColor(Color.yellow);
		
		if(mainframe.numPlayers ==0){
			g2d.drawString("Press enter to Join", 328, 556);
			g2d.drawString("Press Enter to Join", 485, 556);
		}
		
		if(mainframe.numPlayers == 1){
			g2d.drawString("Score: "+ mainframe.score, 328, 556);
			g2d.drawString("Press Enter to Join", 485, 556);
			if(mainframe.direction ==-1){
				g2d.drawImage(player1[p1Num],mainframe.p1.x,mainframe.p1.y-32,null);
			}
			else if(mainframe.direction == 1){
				g2d.drawImage(player1[p1Num],mainframe.p1.x+31,mainframe.p1.y-32,-1*player1[p1Num].getWidth(),player1[p1Num].getHeight(),null);
			}
		}
		
		if(mainframe.numPlayers == 2){
			g2d.drawString("Score: "+ mainframe.score, 328, 556);
			g2d.drawString("Score: "+ mainframe.score, 485, 556);
		}
		
		if(enemyList.size()>0){
			for(int i = 0; i < enemyList.size(); i++){
				if(enemyList.get(i).direction==-1){
				g2d.drawImage(enemy[enemyList.get(i).image], enemyList.get(i).loc.x, enemyList.get(i).loc.y-32, null);
				}
				else if(enemyList.get(i).direction==1){
				g2d.drawImage(enemy[enemyList.get(i).image], enemyList.get(i).loc.x+31, enemyList.get(i).loc.y-32,-1*enemy[enemyList.get(i).image].getWidth(),enemy[enemyList.get(i).image].getHeight(), null);
				}
			}
		}
		
		if(score){
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			g2d.setColor(Color.yellow);
			g2d.drawString("500", 328, 556);
			try{
				Thread.sleep(300);
			}
			catch(Exception ex){}
			score = false;
		}
		
		if(nextWave){
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			g2d.setColor(Color.white);
			g2d.drawString("Wave: "+ mainframe.wave, 400, 320);
			try{
				Thread.sleep(300);
			}
			catch(Exception ex){}
			nextWave = false;
		}
	}
}
