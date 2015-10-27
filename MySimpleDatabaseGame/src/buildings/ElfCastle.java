package buildings;

import tableTypes.Building;
import characters.Elf;

public class ElfCastle extends Building {
	public ElfCastle(int buildingId, int gamePieceId) {
		super(buildingId, gamePieceId);
		this.setImage("assets/elfcastle.png");
		// TODO Auto-generated constructor stub
	}
	
	public void active(){
		if(isSelected()){
			Elf elf = new Elf();
			elf.start();
		}
	}
}
