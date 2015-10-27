
package deathBotOne;

import battlecode.common.*;

/**
 * Based on example code from most recent lecture
 *  all robot types have been broken into separate classes
 *  run method initial call changed from if/else to switch
 *
 */
public class RobotPlayer {

    public static void run(RobotController rc) {
        BaseBot myself;

       switch (rc.getType()) {
            case HQ: myself = new HQ(rc); break;
            case COMPUTER: myself = new Computer(rc); break;
            case MINER: myself = new Miner(rc); break;
            case BASHER: myself = new Basher(rc); break;
            case BEAVER: myself = new Beaver(rc); break;
            case COMMANDER: myself = new Commander(rc); break;
            case LAUNCHER: myself = new Launcher(rc); break;
            case HELIPAD:
            case BARRACKS:
            case MINERFACTORY:
            case TECHNOLOGYINSTITUTE:
            case TRAININGFIELD:
            case AEROSPACELAB:
            case TANKFACTORY: myself = new SimpleBuilding(rc); break;
            case SOLDIER: myself = new Soldier(rc); break;
            case DRONE: myself = new Drone(rc); break;
            case TANK: myself = new SimpleFighter(rc); break;
            case TOWER: myself = new Tower(rc); break;
            default: myself = new BaseBot(rc);
        }

        while (true) {
            try {
                myself.go();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
