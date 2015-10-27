package tableTypes;

import java.awt.Image;
import java.awt.Point;

public class Character extends GamePiece {
	private int characterId;
	int classId;
	
	public Character(int characterId, int classId, int gamePieceId){
		super(gamePieceId, new Point(0,0));
		this.setCharacterId(characterId);
		this.classId = classId;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
}
