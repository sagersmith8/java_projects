package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class ComputeBot extends BaseBot {

    // Boundaries of square with HQs at corners
    public static int xMin, xMax, yMin, yMax;
    // Curren location of map scanning
    public static int xpos, ypos;
    // Number of squares of given type found on map so far
    public static int totalNormal, totalVoid, totalProcessed;
    // Integer value assessment fo danger from towers on this map
    public static int towerThreat;
    // Health level of enemy headquarters
    public static double enemyHQHealth;

    // Ratio of void terrain to passable terrain
    public static double percentPassable;
    // Indicates if HQ has finished assessing type of map
    public static boolean isFinished;

    // Strategy related variables
    public static int strategy;         // 0 = "defend", 1 = "build drones", 2 = "build soldiers"
    public static boolean buildingUp;   // Indicates what type of strategy phase we are in - May be more than one
    public static boolean defending;    //  ""
    public static boolean exploring;    // ""
    public static boolean midGame;      // ""
    public static boolean endGame;      // ""
    public static boolean dirty;        // ""    
    public static boolean attacking;    // Are we currently in an attack phase?

    // Supply and ore level indicators
    public static double supplyUtilization;

    // Build level information
    public static int armySize;
    public static int numBeavers;
    public static int numTanks;
    public static int numSoldiers;
    public static int numBarracks;
    public static int numTankFactories;
    public static int numMinerFactories;
    public static int numMiners;
    public static int numHelipads;
    public static int numDrones;
    public static int numTowers;
    public static int numInstitutes;
    public static int numTrainingFields;
    public static int numCommanders;
    public static int numHWStations;
    public static int numBashers;
    public static int numAerospaceLabs;
    public static int numLaunchers;
    public static int numComputers;
    public static int numSupplyDepots;

    public static int requiredTanks;
    public static int requiredSoldiers;
    public static int requiredBarracks;
    public static int requiredTankFactories;
    public static int requiredHelipads;
    public static int requiredDrones;
    public static int requiredBashers;
    public static int requiredAerospaceLabs;
    public static int requiredLaunchers;

    public ComputeBot(RobotController rc) {
        super(rc);
        // Strategy related variables
        strategy = 0;
        buildingUp = true;
        defending = false;
        exploring = true;
        midGame = false;
        endGame = false;
        dirty = false;
        attacking = false;

        supplyUtilization = 0.5;

        requiredTanks = 10;
        requiredSoldiers = 15;
        requiredBarracks = 1;
        requiredTankFactories = 1;
        requiredHelipads = 1;
        requiredDrones = 25;
        requiredBashers = 10;
        requiredAerospaceLabs = 0;
        requiredLaunchers = 0;
    }

    // This method will go through the appropriate channels and update our local
    //  list of how many units we have on the field
    protected void updateArmyCounts() throws GameActionException {
        System.out.println("UAC1 enter:" + Clock.getBytecodeNum());
        numBeavers = Communicate.readAnnounce(rc, RobotType.BEAVER);
        numAerospaceLabs = Communicate.readAnnounce(rc, RobotType.AEROSPACELAB);
        numBarracks = Communicate.readAnnounce(rc, RobotType.BARRACKS);
        numBashers = Communicate.readAnnounce(rc, RobotType.BASHER);
        numCommanders = Communicate.readAnnounce(rc, RobotType.COMMANDER);
        numTowers = Communicate.readAnnounce(rc, RobotType.TOWER);
        numInstitutes = Communicate.readAnnounce(rc, RobotType.TECHNOLOGYINSTITUTE);
        numTankFactories = Communicate.readAnnounce(rc, RobotType.TANKFACTORY);
        numComputers = Communicate.readAnnounce(rc, RobotType.COMPUTER);
        numDrones = Communicate.readAnnounce(rc, RobotType.DRONE);
        numHWStations = Communicate.readAnnounce(rc, RobotType.HANDWASHSTATION);
        numHelipads = Communicate.readAnnounce(rc, RobotType.HELIPAD);
        numLaunchers = Communicate.readAnnounce(rc, RobotType.LAUNCHER);
        numMiners = Communicate.readAnnounce(rc, RobotType.MINER);
        numMinerFactories = Communicate.readAnnounce(rc, RobotType.MINERFACTORY);
        numSoldiers = Communicate.readAnnounce(rc, RobotType.SOLDIER);
        numSupplyDepots = Communicate.readAnnounce(rc, RobotType.SUPPLYDEPOT);
        numTanks = Communicate.readAnnounce(rc, RobotType.TANK);
        numTrainingFields = Communicate.readAnnounce(rc, RobotType.TRAININGFIELD);
        armySize = countArmy();
        System.out.println("UAC1 exit :" + Clock.getBytecodeNum());
    }

    // Test method
    // This method will go through the appropriate channels and update our local
    //  list of how many units we have on the field
    protected void updateArmyCountsSenseVersion() throws GameActionException {
        System.out.println("UAC2 enter:" + Clock.getBytecodeNum());
        RobotInfo[] myTeamMembers = rc.senseNearbyRobots(Integer.MAX_VALUE);
        int beaverCount = 0;
        int labCount = 0;
        int barracksCount = 0;
        int basherCount = 0;
        int commanderCount = 0;
        int towerCount = 0;
        int instituteCount = 0;
        int tankFactoryCount = 0;
        int computerCount = 0;
        int droneCount = 0;
        int hwCount = 0;
        int helipadCount = 0;
        int launcherCount = 0;
        int minerCount = 0;
        int minerFactoryCount = 0;
        int soldierCount = 0;
        int depotCount = 0;
        int tankCount = 0;
        int trainingFieldCount = 0;
        for (RobotInfo robot : myTeamMembers) {
            switch (robot.type) {
            case BEAVER: beaverCount++; break;
            case AEROSPACELAB:labCount++; break;
            case BARRACKS: barracksCount++; break;
            case BASHER: basherCount++; break;
            case COMMANDER: commanderCount++; break;
            case TOWER: towerCount++; break;
            case TECHNOLOGYINSTITUTE: instituteCount++; break;
            case TANKFACTORY: tankFactoryCount++; break;
            case COMPUTER: computerCount++; break;
            case DRONE: droneCount++; break;
            case HANDWASHSTATION: hwCount++; break;
            case HELIPAD: helipadCount++; break;
            case LAUNCHER: launcherCount++; break;
            case MINER: minerCount++; break;
            case MINERFACTORY: minerFactoryCount++; break;
            case SOLDIER: soldierCount++; break;
            case SUPPLYDEPOT:depotCount++; break;
            case TANK: tankCount++; break;
            case TRAININGFIELD: trainingFieldCount++; break;
            }
        }
        numBeavers = beaverCount;
        numAerospaceLabs = labCount;
        numBarracks = barracksCount;
        numBashers = basherCount;
        numCommanders = commanderCount;
        numTowers = towerCount;
        numInstitutes = instituteCount;
        numTankFactories = tankFactoryCount;
        numComputers = computerCount;
        numDrones = droneCount;
        numHWStations = hwCount;
        numHelipads = helipadCount;
        numLaunchers = launcherCount;
        numMiners = minerCount;
        numMinerFactories = minerFactoryCount;
        numSoldiers = soldierCount;
        numSupplyDepots = depotCount;
        numTanks = tankCount;
        numTrainingFields = trainingFieldCount;
        armySize = countArmy();
        System.out.println("UAC2 exit :" + Clock.getBytecodeNum());
    }

    // This method will determine how much of our total supply it costs to
    //  maintain our army in full working order, and how much we are earning each
    //  turn store ratio in an appropriate channel
    protected void supplyRatio() throws GameActionException {
        double supplyCosts = calculateSupplyCost();
        double supplyIncome = GameConstants.SUPPLY_GEN_BASE
                * (GameConstants.SUPPLY_GEN_MULTIPLIER + Math.pow(numSupplyDepots, GameConstants.SUPPLY_GEN_EXPONENT));
        supplyUtilization = supplyCosts / supplyIncome;
    }

    //!!! Not complete
    // This method will analyze the enemies locations and movements and store
    //  key figures about this info in an appropriate range of channels
    protected void enemyActivity() throws GameActionException {
        // Needs to be implemented
    }

    protected double calculateSupplyCost() {
        double totalCost = 0;
        totalCost += numBeavers * RobotType.BEAVER.supplyUpkeep;
        totalCost += numTanks * RobotType.TANK.supplyUpkeep;
        totalCost += numSoldiers * RobotType.SOLDIER.supplyUpkeep;
        totalCost += numMiners * RobotType.MINER.supplyUpkeep;
        totalCost += numDrones * RobotType.DRONE.supplyUpkeep;
        totalCost += numCommanders * RobotType.COMMANDER.supplyUpkeep;
        totalCost += numBashers * RobotType.BASHER.supplyUpkeep;
        totalCost += numLaunchers * RobotType.LAUNCHER.supplyUpkeep;
        totalCost += numComputers * RobotType.COMPUTER.supplyUpkeep;
        return totalCost;
    }

    //!!! Not complete
    // This method will observe the enemy towers and our towers and determine
    //  determine which side is in better shape and by how much this info will
    //  be stored in appropriate channels
    protected void compareTowers() throws GameActionException {
        // needs to be implemented
        MapLocation[] theirTowers = rc.senseEnemyTowerLocations();
        MapLocation[] ourTowers = rc.senseTowerLocations();

        double relativeTowerStrength = ourTowers.length - theirTowers.length;

        if (rc.canSenseLocation(theirHQ)) {
            enemyHQHealth = rc.senseRobotAtLocation(theirHQ).health;
        }

    }

    //!!! Not complete
    // This method will observe our forces. It will look at how many forces we
    // have and determine how they should be grouped into squads.
    protected void analyzeForces() throws GameActionException {
        // needs to be implemented
    }

    //!!! Not complete
    // This method will set destinations and mission paramaters for our squads
    protected void assignMissions() throws GameActionException {
        // needs to be implemented
    }

    // This method will determine a build order for units this turn and write it to the
    //  build channels
    protected void createBuildOrder() throws GameActionException {
        updateArmyCounts();
        supplyRatio();
        BuildOrder[] buildList = new BuildOrder[Communicate.BUILD_LIST_SIZE];
        int index = 0;
        // First things first, we need ore and supply economy
        if (buildingUp && numMinerFactories < MAX_MINERFACTORIES) {
            buildList[index] = new BuildOrder(RobotType.MINERFACTORY);
            index++;
        } else if ((buildingUp && numMiners < MIN_MINERS) || (supplyUtilization < SUPPLY_UTIL_HIGH && ((double) numMiners) / armySize < MINER_RATIO)) {
            buildList[index] = new BuildOrder(RobotType.MINER);
            index++;
        }
        if (buildingUp && supplyUtilization > SUPPLY_UTIL_HIGH) {
            buildList[index] = new BuildOrder(RobotType.SUPPLYDEPOT);
            index++;
        }
        // We need to make sure we're exploring the map
        if (exploring) {
            if (numHelipads < 1) {
                buildList[index] = new BuildOrder(RobotType.HELIPAD);
                index++;
            }
            if (numDrones < EXPLORATION_DRONES) {
                buildList[index] = new BuildOrder(RobotType.DRONE);
            }
        }
        // Next we need to make sure that we are keeping our territory defended
        if (defending) {
            for (int i = 0; i < numBarracks && index < buildList.length; i++) {
                buildList[index] = new BuildOrder(RobotType.BASHER);
                index++;
            }
            for (int i = 0; i < numTankFactories && index < buildList.length; i++) {
                buildList[index] = new BuildOrder(RobotType.TANK);
                index++;
            }
        } else if (numTanks < numTowers * 2 && index < buildList.length) {
            if (numTankFactories < 1) {
                buildList[index] = new BuildOrder(RobotType.TANK);
                index++;
            }
            for (int i = 0; i < numTankFactories && index < buildList.length; i++) {
                buildList[index] = new BuildOrder(RobotType.TANK);
                index++;
            }
        }
        // Then we need to see if our building hierarchy is in good shape
        if ((midGame || endGame) && index < buildList.length) {
            if (numInstitutes < 1 && rc.checkDependencyProgress(RobotType.TECHNOLOGYINSTITUTE) == DependencyProgress.DONE) {
                buildList[index] = new BuildOrder(RobotType.TECHNOLOGYINSTITUTE);
                index++;
            } else if (numTrainingFields < 1) {
                buildList[index] = new BuildOrder(RobotType.TRAININGFIELD);
                index++;
            } else if (numCommanders < 1) {
                buildList[index] = new BuildOrder(RobotType.COMMANDER);
                index++;
            }
        }
        // Then we can maintain and expand our patrols
        if (index < buildList.length) {
            if (numLaunchers < requiredLaunchers && rc.checkDependencyProgress(RobotType.AEROSPACELAB) == DependencyProgress.DONE) {
                if (numAerospaceLabs < requiredAerospaceLabs) {
                    buildList[index] = new BuildOrder(RobotType.AEROSPACELAB);
                    index++;
                }
                for (int i = 0; i < numAerospaceLabs && index < buildList.length; i++) {
                    buildList[index] = new BuildOrder(RobotType.LAUNCHER);
                    index++;
                }
            }
            if (numTanks < requiredTanks && index < buildList.length && rc.checkDependencyProgress(RobotType.TANKFACTORY) == DependencyProgress.DONE) {
                if (numTankFactories < requiredTankFactories) {
                    buildList[index] = new BuildOrder(RobotType.TANKFACTORY);
                    index++;
                }
                for (int i = 0; i < numTankFactories && index < buildList.length; i++) {
                    buildList[index] = new BuildOrder(RobotType.TANK);
                    index++;
                }
            }
            if (numBashers < requiredBashers && index < buildList.length) {
                if (numBarracks < requiredBarracks) {
                    buildList[index] = new BuildOrder(RobotType.BARRACKS);
                    index++;
                }
                for (int i = 0; i < numBarracks && index < buildList.length; i++) {
                    buildList[index] = new BuildOrder(RobotType.BASHER);
                    index++;
                }
            }
            if (numSoldiers < requiredSoldiers && index < buildList.length) {
                if (numBarracks < requiredBarracks) {
                    buildList[index] = new BuildOrder(RobotType.BARRACKS);
                    index++;
                }
                for (int i = 0; i < numBarracks && index < buildList.length; i++) {
                    buildList[index] = new BuildOrder(RobotType.SOLDIER);
                    index++;
                }
            }
            if (numDrones < requiredDrones && index < buildList.length) {
                if (numHelipads < requiredHelipads) {
                    buildList[index] = new BuildOrder(RobotType.AEROSPACELAB);
                    index++;
                }
                for (int i = 0; i < numHelipads && index < buildList.length; i++) {
                    buildList[index] = new BuildOrder(RobotType.DRONE);
                    index++;
                }
            }

        }
        // Maybe some time for fun
        if (dirty) {
            if (numHWStations < armySize / 5) {
                buildList[index] = new BuildOrder(RobotType.HANDWASHSTATION);
                index++;
            }
        }
        Communicate.writeBuildRequest(rc, buildList);
    }

    // Determine the ratio of void to passable terrain in orderto determine
    //  the best strategy to use for this map
    protected void analyzeMap() {
        while (ypos < yMax + 1) {
            TerrainTile t = rc.senseTerrainTile(new MapLocation(xpos, ypos));

            if (t == TerrainTile.NORMAL) {
                totalNormal++;
                totalProcessed++;
            } else if (t == TerrainTile.VOID) {
                totalVoid++;
                totalProcessed++;
            }
            xpos++;
            if (xpos == xMax + 1) {
                xpos = xMin;
                ypos++;
            }

            if (Clock.getBytecodesLeft() < LOW_BYTECODE) {
                return;
            }
        }
        percentPassable = (double) totalNormal / totalProcessed;
        isFinished = true;
    }

    // Determines the danger posed to a frontal assault from the towers
    //  on current map.
    protected void analyzeTowers() {
        MapLocation[] towers = rc.senseEnemyTowerLocations();
        towerThreat = 0;

        for (int i = 0; i < towers.length; ++i) {
            MapLocation towerLoc = towers[i];

            if ((xMin <= towerLoc.x && towerLoc.x <= xMax && yMin <= towerLoc.y && towerLoc.y <= yMax) || towerLoc.distanceSquaredTo(this.theirHQ) <= 50) {
                for (int j = 0; j < towers.length; ++j) {
                    if (towers[j].distanceSquaredTo(towerLoc) <= 50) {
                        towerThreat++;
                    }
                }
            }
        }
    }

    // Uses the tower and map terrain assessments to choose a strategy for
    //  the current map.
    protected void chooseStrategy() throws GameActionException {
//        System.out.println("Ratio: " + percentPassable + "\nTower Threat: " + towerThreat);
        if (towerThreat >= 10) {
            //play defensive
            strategy = 0;
        } else {
            if (percentPassable <= 0.85) {
                //build drones
                strategy = 1;
            } else {
                //build soldiers
                strategy = 2;
            }
        }
        rc.broadcast(100, strategy);
    }

    protected int countArmy() {
        return numBashers + numCommanders
                + numDrones + numLaunchers + numSoldiers
                + numTanks;
    }
}
