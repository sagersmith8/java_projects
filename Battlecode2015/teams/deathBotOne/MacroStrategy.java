package deathBotOne;
import battlecode.common.*;

public class MacroStrategy{
    public static void avoid(RobotController rc){
	RobotInfo[] nearbyRobots  = rc.senseNearbyRobots(15, rc.getTeam().opponent());
	MapLocation mp = rc.getLocation();
	Direction dir = Direction.NONE;
	if(nearbyRobots!=null){
	    for(RobotInfo ri : nearbyRobots){
		if(ri.location.distanceSquaredTo(mp)<ri.type.attackRadiusSquared){
		    dir = mp.directionTo(ri.location).opposite();
		    
		}
	    }
	}
    }

    public static void defend(RobotController rc){
    }

    public static void attack(RobotController rc){
    }

}