
package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class Launcher extends SimpleFighter {
    
    public Launcher(RobotController rc) {
        super(rc);
    }

    public void passive() throws GameActionException{
	//rc.readBroadcast(BROADCAST_INT);
	//getDirectionsToward(BROADCAST_SQUARE);
	//getMoveDir(BROADCAST_SQUARE);
	transferSupply(RobotType.TOWER);
}

    public void agressive() throws GameActionException{
	//rc.readBroadcast(BROADCAST_INT);
	//getDirectionsToward(BROADCAST_SQUARE);
	//getMoveDir(BROADCAST_SQUARE);
	//recieveSupply();
	launchMissile();
    }

    public void launchMissile() throws GameActionException{
	RobotInfo[] ri = rc.senseNearbyRobots(RobotType.LAUNCHER.sensorRadiusSquared);
	MapLocation toShoot;
	if(this.rc.getMissileCount()>0 && (toShoot = findLeastHealthEnemy(ri))!=null){
	    rc.launchMissile(rc.getLocation().directionTo(toShoot));
	}
    }
}
