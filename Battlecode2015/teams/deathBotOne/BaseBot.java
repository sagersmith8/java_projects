package deathBotOne;

import battlecode.common.*;

/**
 *
 * @author Smooth Criminals
 */
public class BaseBot {
        // Constants

    // Amount of remaining bytecode below which complex tasks will be shelved
    protected static final int LOW_BYTECODE = 650;
    protected static final double SUPPLY_UTIL_HIGH = 1.0;
    protected static final double SUPPLY_UTIL_DANGER_LEVEL = 1.3;
    // Number of units to build on the map
    protected static final int MAX_BEAVERS = 2;
    protected static final int MAX_MINERFACTORIES = 1;
    protected static final int MIN_MINERS = 5;
    protected static final double MINER_RATIO = 0.5;
    protected static final int EXPLORATION_DRONES = 3;
//    // Channel on which to communicate # of miners
//    protected static final int CHANNEL_MINERFACTORIES = 17;
    // Round # on which to begin concentrated attack
    protected static final int ATTACK_ROUND = 1000;
    protected static final int SUPPLY_TRANSFER_CAP = 250;   /////testing testing

    // End constants
    protected Navigation nav;
    protected RobotController rc;
    protected RobotType myType;
    protected MapLocation myLocation;
    protected MapLocation myHQ, theirHQ;
    protected Team myTeam, theirTeam;

    public BaseBot(RobotController rc) {
        nav = new Navigation(rc);
        this.rc = rc;
        this.myType = rc.getType();
        this.myLocation = rc.getLocation();
        this.myHQ = rc.senseHQLocation();
        this.theirHQ = rc.senseEnemyHQLocation();
        this.myTeam = rc.getTeam();
        this.theirTeam = this.myTeam.opponent();
    }

    public Direction[] getDirectionsToward(MapLocation dest) {
        return nav.getDirectionsToward(dest);
    }

    /*
     public Direction getMoveDir(MapLocation dest) {
     Direction[] dirs = getDirectionsToward(dest);
     for (Direction d : dirs) {
     if (rc.canMove(d)) {
     return d;
     }
     }
     return null;
     }
     */
    public Direction getMoveDir(MapLocation dest) throws GameActionException {

        return nav.getMoveDir(dest);
    }

    public Direction getSpawnDirection(RobotType type) {
        Direction[] dirs = getDirectionsToward(this.theirHQ);
        for (Direction d : dirs) {
            if (rc.canSpawn(d, type)) {
                return d;
            }
        }
        return null;
    }

    public Direction getBuildDirection(RobotType type) {
        Direction[] dirs = getDirectionsToward(this.theirHQ);
        for (Direction d : dirs) {
            if (rc.canBuild(d, type)) {
                return d;
            }
        }
        return null;
    }

    public Direction buildOrSpawnDirection(RobotType type) {
        if (rc.getType() == RobotType.BEAVER) {
            return getBuildDirection(type);
        } else {
            return getSpawnDirection(type);
        }
    }

    public RobotInfo[] getAllies() {
        RobotInfo[] allies = rc.senseNearbyRobots(Integer.MAX_VALUE, myTeam);
        return allies;
    }

    public RobotInfo[] getEnemiesInAttackingRange() {
        RobotInfo[] enemies = rc.senseNearbyRobots(myType.attackRadiusSquared, theirTeam);
        return enemies;
    }

    public void attackLeastHealthEnemy(RobotInfo[] enemies) throws GameActionException {
        if (enemies.length == 0) {
            return;
        }

        double minEnergon = Double.MAX_VALUE;
        MapLocation toAttack = null;
        for (RobotInfo info : enemies) {
            if (info.health < minEnergon) {
                toAttack = info.location;
                minEnergon = info.health;
            }
        }

        if (rc.isWeaponReady() && rc.canAttackLocation(toAttack)) {
            rc.attackLocation(toAttack);
        }
    }

    public MapLocation findLeastHealthEnemy(RobotInfo[] enemies) throws GameActionException{
	if(enemies.length == 0){
	    return null;
	}

	double minEnergon = Double.MAX_VALUE;
	MapLocation toAttack = null;
	for(RobotInfo info : enemies){
	    if(info.health < minEnergon){
		toAttack = info.location;
		minEnergon = info.health;
	    }
	}

	return toAttack;
    }

    public void beginningOfTurn() throws GameActionException {
        Communicate.announce(rc);
        this.myLocation = rc.getLocation();
    }

    public void endOfTurn() throws GameActionException {
        if (Clock.getBytecodesLeft() > LOW_BYTECODE) {
            transferSupply();
        }
        rc.yield();
    }

    public void go() throws GameActionException {
        beginningOfTurn();
        execute();
        endOfTurn();
    }

    public void execute() throws GameActionException {
    }

    // Method to transfer supply to the lowest supply nearby ally
    public void transferSupply() throws GameActionException {
        RobotInfo[] chargeable = rc.senseNearbyRobots(GameConstants.SUPPLY_TRANSFER_RADIUS_SQUARED, myTeam);

        // Find the robot with the lowest supply (not more than half current bot's supply)
        double minSupply = rc.getSupplyLevel() / 2;
        RobotInfo target = null;
        for (RobotInfo thisBot : chargeable) {
            if (Clock.getBytecodesLeft() < LOW_BYTECODE) {
                break;
            }
            if (thisBot.supplyLevel < minSupply && thisBot.type != RobotType.MINERFACTORY) {
                minSupply = thisBot.supplyLevel;
                target = thisBot;
            }
        }

        // If we found a suitable target, transfer half of the difference between our supply levels
        if (target != null) {
            double toTransfer;
            if (Clock.getRoundNum() >= ATTACK_ROUND && isCombatUnit(target)) {
                toTransfer = (rc.getSupplyLevel() - minSupply) / 2;
            } else {
                toTransfer = Math.min(rc.getSupplyLevel() - minSupply, SUPPLY_TRANSFER_CAP);
            }
            rc.transferSupplies((int) toTransfer, target.location);
        }
    }

    
    //For a specific unit
    public void transferSupply(RobotType rt) throws GameActionException {
	RobotInfo[] chargeable = rc.senseNearbyRobots(GameConstants.SUPPLY_TRANSFER_RADIUS_SQUARED, myTeam);
	
//	if(chargeable == null){ // Can never be null, only an empty list
//	    break;
//	}

	double toTransfer;

	for(RobotInfo thisBot : chargeable){
	    if(Clock.getBytecodesLeft()< LOW_BYTECODE){
		break;
	    }

	    if(thisBot.type == rt){
		toTransfer = Math.min(rc.getSupplyLevel(), SUPPLY_TRANSFER_CAP);
		rc.transferSupplies((int) toTransfer, thisBot.location);
	    }
	}
    }

    // Method to count the number of friendly units of given type in play
    public int countFriendlyUnits(RobotType typeToCount) {
        int count = 0;
        RobotInfo[] friendlies = rc.senseNearbyRobots(1000000, myTeam);
        for (RobotInfo thisUnit : friendlies) {
            if (thisUnit.type == typeToCount) {
                count++;
            }
        }
        return count;
    }

    // Method to check if a unit is or is not a mining unit.
    public boolean isMiner(RobotInfo thisRobot) {
        return thisRobot.type == RobotType.BEAVER || thisRobot.type == RobotType.MINER;
    }

    // Method to check if a unit is or is not a combat unit.
    public boolean isCombatUnit(RobotInfo thisRobot) {
        switch (thisRobot.type) {
            case TANK:
            case SOLDIER:
            case DRONE:
            case COMMANDER:
            case BASHER:
            case LAUNCHER:
            case MISSILE:
                return true;
            default:
                return false;
        }
    }

    /**
     * method to read the build order from the communicate class
     * 
     * 
     * @return
     * @throws GameActionException 
     */
    protected RobotType readBuildOrder() throws GameActionException {
        BuildOrder[] build = Communicate.readBuildRequest(rc);
        double oreLeft = rc.getTeamOre();
        for (int i = 0; i < build.length && oreLeft > 0; i++) {
            if (build[i] == null) {
                return null;
            }
            RobotType type = build[i].type;
            if (type != null) {
                oreLeft -= type.oreCost;
                Direction dir = buildOrSpawnDirection(type);
                if (dir != null) {
                    Communicate.indicateOrderFilled(rc, i);
                    return type;
                }
            }
        }
        return null;
    }
}
