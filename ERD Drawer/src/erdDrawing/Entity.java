package erdDrawing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class Entity {
	Point loc = new Point(0,0);
	String name;
	ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	public Entity(){
	}

	public Entity(String name){
		this.name = name;
	}
	
	public Point getCenter(){
		return new Point(loc.x+(150/2),loc.y+((30+(25*attributes.size()))/2)); 
	}

	public void drawEntity(Graphics2D g2d){
		g2d.setColor(Color.white);
		g2d.fillRoundRect(loc.x, loc.y, 150, 30+(25*attributes.size()), 15, 15);
		g2d.setColor(Color.black);
		g2d.drawRoundRect(loc.x, loc.y, 150, 30+(25*attributes.size()), 15, 15);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
		g2d.drawString(getName(), loc.x+50, loc.y+20);
		for(int j = 0; j < attributes.size(); j++){
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			g2d.drawString(attributes.get(j).toString(), loc.x+50, loc.y+20+ ((j+1)*20));
		}
	}

	public boolean contains(Point p){
		if(p.x >= loc.x && p.x <= loc.x+150 && p.y >= loc.y && p.y <= loc.y+30+(25*attributes.size()))
			return true;

		return false;
	}

	public Entity(String name, Attribute[] attribute){
		this.name = name;
		for(int i = 0; i < attribute.length; i++){
			attributes.add(attribute[i]);
		}
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void addAttribute(Attribute a){
		if(attributes.contains(a)){
			System.out.println(a.name + " exists");
		}
		else{
			attributes.add(a);
		}
	}

	public void removeAttribute(Attribute a){
		if(attributes.contains(a)){
			attributes.remove(a);
		}
		else{
			System.out.println(a.name + " never existed");
		}
	}
}
