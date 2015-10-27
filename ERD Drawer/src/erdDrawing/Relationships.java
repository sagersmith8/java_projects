package erdDrawing;

import java.awt.Graphics2D;
import java.awt.Point;

public class Relationships {
	Relationship relationship1, relationship2;
	
	Relationships(Relationship r1, Relationship r2){
		relationship1 = r1;
		relationship2 = r2;
	}
	
	public void drawRelationship(Graphics2D g2d){
		Point e1 = relationship1.with.getCenter();
		Point e2 = relationship2.with.getCenter();
		g2d.drawLine(e1.x,e1.y,e2.x,e2.y);
	}
}
