package erdDrawing;

public class Attribute {
	String name;
	boolean pk =false, mandetory = false;

	public Attribute(String name, boolean mandetory){
		this.name = name;
		this.mandetory = mandetory;
	}
	
	public Attribute(){
	}
	
	public String toString(){
		if(pk)
			return "# "+name;
		
		else if(mandetory)
			return "* " +name;
		
		return "o "+name;
	}
}