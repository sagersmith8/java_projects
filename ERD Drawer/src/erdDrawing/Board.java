package erdDrawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import linkedList.SLIndexedListCode;

public class Board extends JPanel {
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Relationships> relationships = new ArrayList<Relationships>();
//	SLIndexedListCode<Relationships> relationships = new SLIndexedListCode<Relationships>();
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.lightGray);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		
		if(relationships.size()>0){
			for(int i = 0; i < relationships.size(); i++){
				Relationships r = relationships.get(i);
				r.drawRelationship(g2d);
			}
		}
		
		if(entities.size()>0){
			for(int i = 0; i < entities.size(); i++){
				Entity e = entities.get(i);
				e.drawEntity(g2d);
			}
		}
		else{
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 65)); 
			g2d.drawString("You currently have no entities", 60, 70);
		}
	}
}
