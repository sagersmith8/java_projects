package sage;

import java.util.Random;

import deathBotOne.Navigate;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotType;

public class RobotPlayer {
	static RobotController rc;
	static Random rand;
	
	public static void run(RobotController mrc){
		rc = mrc;
		while(true){
			if(rc.getType() == RobotType.HQ){
				Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				while(!rc.canSpawn(dir, RobotType.BEAVER)){
					dir.rotateLeft();
				}
				
				try {
					rc.spawn(dir, RobotType.BEAVER);
				} catch (GameActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(rc.getType() == RobotType.BEAVER){
				try {
					Navigate.buggy(rc);
				} catch (GameActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}