
package deathBotOne;

import battlecode.common.*;
/**
 *
 * @author Smooth Criminals
 */
public class Tower extends BaseBot {
        public Tower(RobotController rc) {
            super(rc);
        }

    /**
     *
     * @throws GameActionException
     */
    @Override
        public void execute() throws GameActionException {
            RobotInfo[] enemies = this.getEnemiesInAttackingRange();
            attackLeastHealthEnemy(enemies);
        }
    }
