package deathBotOne;

import battlecode.common.*;


public class Barracks extends SimpleBuilding {
    public Barracks(RobotController rc) {
        super(rc);
    }

    public void execute() throws GameActionException {
    	/*spawn soldiers and Bashers
    	 * initially 100% soldiers until? --lighter unit than the basher less expensive
    	 * sense friendly soldiers, sense friendly basher
    	 * then 50/50 split until
    	 * then 100% bashers -- beefier and more expensive than the soldier until?
    	*/
        /*
    	Direction dir = getSpawnDirection(RobotType.SOLDIER);
    	if (rc.isCoreReady() && rc.getTeamOre() > 200 && rc.canSpawn(dir, RobotType.SOLDIER) && dir != null) {
    		rc.spawn(dir, RobotType.SOLDIER);
    	}  
        */
        super.execute();
    	
    }       
}