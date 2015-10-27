package deathBotOne;

import battlecode.common.*;

/**
 * A class to hold variables and methods for the miner class, as well as common
 * beaver/miner variables and methods
 *
 * @author Smooth Criminals
 */
public class Miner extends BaseBot {

    // Constants dealing with finding the best nearby ore supply
    protected static final int MINE_SEARCH_TURNS = 8;

    public Miner(RobotController rc) {
        super(rc);
    }

    public void execute() throws GameActionException {
        if (rc.isCoreReady()) {
            //mine
            Direction toMine = findBestMine(rc.getLocation(), MINE_SEARCH_TURNS);
            if (toMine == Direction.NONE) {
                rc.mine();
            } else {
                rc.move(toMine);
            }
        }
    }

    // Method to find the square adjacent to the starting point which will produce
    //  the most ore in turns
    protected Direction findBestMine(MapLocation startingPoint, int turns) throws GameActionException {
        Direction currentBest = Direction.NONE;
        double bestValue = amountMineable(rc.senseOre(startingPoint), turns);
        Direction toEnemy = myLocation.directionTo(theirHQ);
        Direction[] priorities = {toEnemy.opposite(),
            toEnemy.opposite().rotateLeft(),
            toEnemy.opposite().rotateRight(),
            toEnemy.opposite().rotateLeft().rotateLeft(),
            toEnemy.opposite().rotateRight().rotateRight(),
            toEnemy.rotateLeft().rotateLeft(),
            toEnemy.rotateRight().rotateRight(),
            toEnemy.rotateLeft(),
            toEnemy.rotateRight(),
            toEnemy};

        // We will determine how much ore can be mined within turns by moving
        //  to each possible direction
        for (Direction thisDirection : priorities) {
            if (Clock.getBytecodesLeft() <= LOW_BYTECODE) {
                break;
            }
            MapLocation thisLocation = startingPoint.add(thisDirection);
            if (nav.isPassable(thisLocation) && rc.canMove(myLocation.directionTo(thisLocation))) {
                // find out how much ore can be mine by moving this direction
                double thisValue = amountMineable(rc.senseOre(thisLocation), turns - myType.movementDelay);
                if (thisValue > bestValue || bestValue <= 1) {
                    bestValue = thisValue;
                    currentBest = thisDirection;
                }
            }
        }
        return currentBest;
    }

    // Method to find out how much ore can be mined from a location with startingOre
    //  ore supply in turnsToMine turns
    protected double amountMineable(double startingOre, int turnsToMine) {
        double totalMined = 0;
        double oreLeft = startingOre;

        for (int i = 0; i < turnsToMine; i += myType.movementDelay) {
            if (Clock.getBytecodesLeft() <= LOW_BYTECODE) {
                break;
            }
            double minedThisRound;
            if (myType == RobotType.MINER) {
                minedThisRound = Math.min(GameConstants.MINER_MINE_RATE * oreLeft, GameConstants.MINER_MINE_MAX);
            } else {
                minedThisRound = Math.min(GameConstants.BEAVER_MINE_RATE * oreLeft, GameConstants.BEAVER_MINE_MAX);
            }
            minedThisRound = Math.max(GameConstants.MINIMUM_MINE_AMOUNT, minedThisRound);
            oreLeft -= minedThisRound;
            totalMined += minedThisRound;
        }

        return totalMined;
    }    
}
