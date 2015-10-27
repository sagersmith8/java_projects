package erdDrawing;


public class Relationship{ 
	Attribute foreignKey;
	boolean mandetory = false, many = false;
	Entity with;
	
	public Relationship(Entity with,Attribute att,boolean mandetory, boolean many){
		this.mandetory = mandetory;
		this.many = many;
		this.with = with;
		this.foreignKey = att;
	}	
}
