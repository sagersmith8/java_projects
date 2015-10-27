package tableTypes;

import java.awt.Point;

public class Building extends GamePiece {
	private int buildingId;
	
	public Building(int buildingId, int gamePieceId){
		super(gamePieceId, new Point(0,0));
		this.setBuildingId(buildingId);
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
}
