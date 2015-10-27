package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class SimpleFighter extends BaseBot {

    // the radius^2 within which robots should rally to given point
    protected static final int RALLY_RADIUS = 16;

    public SimpleFighter(RobotController rc) {
        super(rc);
    }

    public void execute() throws GameActionException {
        RobotInfo[] enemies = getEnemiesInAttackingRange();

        // Work on calculating our path
        int rallyX = rc.readBroadcast(0);
        int rallyY = rc.readBroadcast(1);
        MapLocation rallyPoint = new MapLocation(rallyX, rallyY);
        
        nav.findPath(rallyPoint);

        if (enemies.length > 0) {
            //attack!
            if (rc.isWeaponReady()) {
                attackLeastHealthEnemy(enemies);
            }
        } else if (rc.isCoreReady()) {
            // Move
            Direction newDir = getMoveDir(rallyPoint);

            if (newDir != null /*&& (newDir == myLocation.directionTo(rallyPoint) || myLocation.distanceSquaredTo(rallyPoint) > RALLY_RADIUS)*/) {
                rc.move(newDir);
            }
        }
    }
    
    
    
}
