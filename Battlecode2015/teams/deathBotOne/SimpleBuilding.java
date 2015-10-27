package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class SimpleBuilding extends BaseBot {

    public SimpleBuilding(RobotController rc) {
        super(rc);
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() throws GameActionException {
//        System.out.println("In = " + Clock.getBytecodeNum());
        if (rc.isCoreReady()) {
            RobotType type = readBuildOrder();
            if (type != null) {
                Direction dir = getSpawnDirection(type);
                if (dir != null) {
                    rc.spawn(dir, type);
                }
            }
        }
//        System.out.println("Out = " + Clock.getBytecodeNum());
    }
    
}
