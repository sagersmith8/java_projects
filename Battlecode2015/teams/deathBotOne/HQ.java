package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class HQ extends ComputeBot {

    public HQ(RobotController rc) {
        super(rc);

        xMin = Math.min(this.myHQ.x, this.theirHQ.x);
        xMax = Math.max(this.myHQ.x, this.theirHQ.x);
        yMin = Math.min(this.myHQ.y, this.theirHQ.y);
        yMax = Math.max(this.myHQ.y, this.theirHQ.y);

        xpos = xMin;
        ypos = yMin;

        totalNormal = totalVoid = totalProcessed = 0;
        towerThreat = 0;
        strategy = 0;
        isFinished = false;

    }

    public void execute() throws GameActionException {

        if (rc.isCoreReady() && rc.getTeamOre() > 100 && numBeavers < MAX_BEAVERS) {
            Direction newDir = getSpawnDirection(RobotType.BEAVER);
            if (newDir != null) {
                rc.spawn(newDir, RobotType.BEAVER);
            }
        }
        // Attack
        RobotInfo[] enemies = getEnemiesInAttackingRange();
        attackLeastHealthEnemy(enemies);

        // Broadcast a location for attack units to congregate,  communicated wiht SimpleFighter
        MapLocation rallyPoint;
        if (Clock.getRoundNum() >= ATTACK_ROUND && armySize >= 40 || attacking && armySize >= 20) {
            MapLocation[] theirTowers = rc.senseEnemyTowerLocations();
            if (theirTowers.length == 0) {
                rallyPoint = this.theirHQ;
            } else {
                rallyPoint = theirTowers[0];
            }
            attacking = true;
        } else {
            rallyPoint = new MapLocation((this.myHQ.x + this.myHQ.x + this.theirHQ.x) / 3,
                    (this.myHQ.y + this.myHQ.y + this.theirHQ.y) / 3);
            attacking = false;
        }
        rc.broadcast(0, rallyPoint.x);
        rc.broadcast(1, rallyPoint.y);

        createBuildOrder();

        // Assess the map and determine our best strategy
        if (!isFinished) {
            analyzeTowers();
            analyzeMap();
            chooseStrategy();
        }
    }

    /**
     * HQ does not need to announce or get its location at the beginning of the
     * turn. It does, however, need to read and clear the announce channels
     *
     * @throws GameActionException
     */
    @Override
    public void beginningOfTurn() throws GameActionException {
        int roundNum = Clock.getRoundNum();
        if (roundNum > 600) {
            midGame = true;
        }
        if (roundNum > 1500) {
            endGame = true;
        }
        getAnnounceNumbers();
        updateArmyCounts();
        updateArmyCountsSenseVersion();
    }

    /**
     * Reads and clears the public announce channels for each robot type, writes
     * the numbers to the private channels, and stores the counts locally
     * 
     * @throws GameActionException 
     */
    private void getAnnounceNumbers() throws GameActionException {
        System.out.println("getAnnounce in :" + Clock.getBytecodeNum());
        numBeavers          = Communicate.hqGetAnnounce(rc, RobotType.BEAVER);
        numAerospaceLabs    = Communicate.hqGetAnnounce(rc, RobotType.AEROSPACELAB);
        numBarracks         = Communicate.hqGetAnnounce(rc, RobotType.BARRACKS);
        numBashers          = Communicate.hqGetAnnounce(rc, RobotType.BASHER);
        numCommanders       = Communicate.hqGetAnnounce(rc, RobotType.COMMANDER);
        numTowers           = Communicate.hqGetAnnounce(rc, RobotType.TOWER);
        numInstitutes       = Communicate.hqGetAnnounce(rc, RobotType.TECHNOLOGYINSTITUTE);
        numTankFactories    = Communicate.hqGetAnnounce(rc, RobotType.TANKFACTORY);
        numComputers        = Communicate.hqGetAnnounce(rc, RobotType.COMPUTER);
        numDrones           = Communicate.hqGetAnnounce(rc, RobotType.DRONE);
        numHWStations       = Communicate.hqGetAnnounce(rc, RobotType.HANDWASHSTATION);
        numHelipads         = Communicate.hqGetAnnounce(rc, RobotType.HELIPAD);
        numLaunchers        = Communicate.hqGetAnnounce(rc, RobotType.LAUNCHER);
        numMiners           = Communicate.hqGetAnnounce(rc, RobotType.MINER);
        numMinerFactories   = Communicate.hqGetAnnounce(rc, RobotType.MINERFACTORY);
        numSoldiers         = Communicate.hqGetAnnounce(rc, RobotType.SOLDIER);
        numSupplyDepots     = Communicate.hqGetAnnounce(rc, RobotType.SUPPLYDEPOT);
        numTanks            = Communicate.hqGetAnnounce(rc, RobotType.TANK);
        numTrainingFields   = Communicate.hqGetAnnounce(rc, RobotType.TRAININGFIELD);
        armySize            = countArmy();
        System.out.println("getAnnounce out:" + Clock.getBytecodeNum());
    }

    
    /**
     * The HQ watches the announce channels every turn and writes out the
     * private count channels. It is not necessary for HQ to count army at the
     * beginning of a build order
     *
     /
    @Override
    protected void updateArmyCounts() {
        // Not necessary for HQ
    } */
}
