package deathBotOne;

import java.util.Random;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class Navigate {
	public static void buggy(RobotController rc) throws GameActionException{
		Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
		if(rc.canMove(dir)){
			rc.move(dir);
		}
		
		else{
			Random rand = new Random(rc.getID());
			int lr = rand.nextInt(10);
			if(lr>5){
				while(!rc.canMove(dir)){
					dir.rotateLeft();
				}
			}
			
			else{
				while(!rc.canMove(dir)){
					dir.rotateLeft();
				}
			}
		}
	}
	
	public static void ant(RobotController rc){
	}
}
