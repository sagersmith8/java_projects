package characters;

import tableTypes.Character;

public class Dwarf extends Character {

	public Dwarf() {
		super(1, 1, 1);
		this.setImage("assets/dwarf.png");
	}
	
	@Override
	public void run(){
		System.out.println("Dwarf");
	}
}
