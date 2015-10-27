package buildings;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import characters.Dwarf;

import tableTypes.Building;

public class DwarfCastle extends Building {

	BufferedImage look;
	
	public DwarfCastle() {
		super(1, 1);
		this.setImage("assets/humancastle.png");
	}
	
	public void active(){
		if(isSelected()){
			Dwarf dwarf = new Dwarf();
			dwarf.start();
		}
	}
	
	public void passive(){
		if(isSelected()){
			
		}
	}
}
