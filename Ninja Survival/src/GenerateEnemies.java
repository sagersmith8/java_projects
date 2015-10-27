import java.awt.image.BufferedImage;
import java.io.File;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class GenerateEnemies extends TimerTask {
	Utils utils;
	Board board;
	BufferedImage [] selectedImage = new BufferedImage[3];

	public GenerateEnemies(Utils u, Board b){
		utils = u;
		board = b;
		try{
			selectedImage[0] =	ImageIO.read(new File("enemy.png"));
			selectedImage[1] =	ImageIO.read(new File("enemy2.png"));
			selectedImage[2] =	ImageIO.read(new File("enemy3.png"));
		}

		catch(Exception ex){
			System.err.print("Error reading in Images Generate Enemies");
		}
	}

	public void run() {
		int x = (int)(Math.random()*500);
		int num = (int)(Math.random()*3);
		Enemy en = new Enemy(x,0,utils, board, selectedImage[num]);
		en.start();
		board.enemyList.add(en);
	}

}
