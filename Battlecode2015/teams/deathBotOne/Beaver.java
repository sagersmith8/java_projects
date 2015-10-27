package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class Beaver extends Miner {

    public Beaver(RobotController rc) {
        super(rc);
    }

    public void execute() throws GameActionException {

        RobotType type = readBuildOrder();
        if (type != null) {
            Direction dir = getBuildDirection(type);

            if (rc.isCoreReady()) {
                rc.build(dir, type);
            }
        } else {
            // mine
            super.execute();
        }
    }

}
