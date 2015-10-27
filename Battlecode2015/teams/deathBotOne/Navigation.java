package deathBotOne;

import battlecode.common.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * A class to hold navigation data and methods for use by robots in
 * Battlecode2015
 *
 * @author Smooth Criminals
 */
public class Navigation {

    // the controller to whom this Navigation object belongs
    private RobotController rc;

    // a "Path" object to direct us to our destination
    private Path myPath;

    // variables to track whether unpathed movement is currently direct or bugging
    private boolean isBugging;
    private int buggingInitialDistance;
    private Direction buggingDirection;

    // Store the locations of enemy towers
    private MapLocation[] enemyTowers;
    private MapLocation enemyHQ;

    public Navigation(RobotController rc) {
        this.rc = rc;
        isBugging = false;
        buggingInitialDistance = 0;
        buggingDirection = Direction.NONE;
        enemyTowers = rc.senseEnemyTowerLocations();
        enemyHQ = rc.senseEnemyHQLocation();
    }

    public void findPath(MapLocation destination) throws GameActionException {
        if (myPath != null && myPath.destination.equals(destination)) {
            myPath.buildPath();
        } else {
            myPath = new Path(destination);
            myPath.buildPath();
        }
    }

    public Direction getMoveDir(MapLocation destination) throws GameActionException {
        MapLocation myLoc = rc.getLocation();
        // If we've finished calculating the path, move try to move along it
        if (myPath.isComplete) {
            MapLocation nextLoc = myPath.getClosestLoc();
            if (nextLoc == null) {
                return bugging(destination);
            }
            // If we're adjacent to our next move and able to make it, then do so
            if (myLoc.isAdjacentTo(nextLoc) && rc.canMove(myLoc.directionTo(nextLoc))) {
                return myPath.makeNextMove();
                // Or if our next move is for some reason impasssable, scrap our path
                //  and start calculating again
            } else if (!isPassable(nextLoc)) {
                myPath = null;
                findPath(destination);
                return getMoveDir(destination);
            } else { // Otherwise, try to find a good detour toward our destination
                return myPath.findDetour();
//                return null; // Return null for debugging purposes
            }
        } else { // If the path isn't done yet, just use the bugging algorithm
            return bugging(destination);
        }
    }

    private Direction bugging(MapLocation destination) {
        MapLocation myLocation = rc.getLocation();
        if (isBugging) { // If we're currently bugging:
            // If we're not along a wall, then stop bugging
            if (rc.canMove(buggingDirection.rotateLeft()) && rc.canMove(buggingDirection.rotateLeft().rotateLeft())) {
                isBugging = false;
                buggingDirection = Direction.NONE;
                buggingInitialDistance = 0;
                return bugging(destination);
            }
            // If we're at least as far away as when we started bugging:
            if (myLocation.distanceSquaredTo(destination) >= buggingInitialDistance) {
                Direction toMove = buggingDirection.rotateLeft();
                int tries = 0;
                while (tries < 8 && !rc.canMove(toMove)) {
                    toMove = toMove.rotateRight();
                    tries++;
                }
                if (tries > 7) { // If there's no path from where we are, just sit tight
                    isBugging = false;
                    buggingInitialDistance = 0;
                    buggingDirection = Direction.NONE;
                    return null;
                } else { // Otherwise we should move along, keeping the obstacle on our left
                    buggingDirection = toMove;
                    return toMove;
                }
                // if we're closer to the destination than when we started bugging,
                // then stop bugging
            } else {
                isBugging = false;
                buggingDirection = Direction.NONE;
                buggingInitialDistance = 0;
                return bugging(destination);
            }
        } else { // If we're not currently bugging:
            Direction toGoal = myLocation.directionTo(destination);
            // If we can move straight to goal, do so
            if (rc.senseTerrainTile(myLocation.add(toGoal)).isTraversable() || rc.getType() == RobotType.DRONE) {
                if (rc.canMove(toGoal)) {
                    return toGoal;
                } else {
                    Direction altDir = bestMoveDir(toGoal);
                    if (altDir == null) {
                        isBugging = true;
                        buggingDirection = toGoal;
                        buggingInitialDistance = myLocation.distanceSquaredTo(destination);
                        return bugging(destination);
                    } else {
                        return altDir;
                    }
                }
            } else { // Otherwise, start bugging around obstacles
                isBugging = true;
                buggingInitialDistance = myLocation.distanceSquaredTo(destination);
                buggingDirection = toGoal.rotateRight();
                return bugging(destination);
                // Try each of 7 more direction, rotating to the right each time
                //  The eighth try is a full circle, so we can't really go that way
            }
        }
    }

    // Method to get a list of directions adjacent to the given direction
    private Direction[] getAdjacentDirections(Direction toGo) {
        Direction[] dirs = {
            toGo.rotateLeft(), toGo.rotateRight(),
            toGo.rotateLeft().rotateLeft(), toGo.rotateRight().rotateRight()};

        return dirs;
    }

    public Direction[] getDirectionsToward(MapLocation dest) {
        Direction toDest = rc.getLocation().directionTo(dest);
        Direction[] dirs = {toDest,
            toDest.rotateLeft(), toDest.rotateRight(),
            toDest.rotateLeft().rotateLeft(), toDest.rotateRight().rotateRight()};

        return dirs;
    }

    // Finds the closest movable direction to the given direction
    private Direction bestMoveDir(Direction toGo) {
        Direction[] dirs = getAdjacentDirections(toGo);
        for (Direction d : dirs) {
            if (rc.canMove(d)) {
                return d;
            }
        }
        return null;
    }

    // returns true if a ground unit can move over the given MapLocation
    public boolean isPassable(MapLocation toTest) throws GameActionException {
        TerrainTile thisTerrain = rc.senseTerrainTile(toTest);
        return thisTerrain == TerrainTile.UNKNOWN
                || (thisTerrain.isTraversable()
                && (!rc.canSenseLocation(toTest) || !isBuilding(rc.senseRobotAtLocation(toTest)))
                && !inEnemyTowerRange(toTest));
    }

    // returns true if a MapLocation toTest is passable, and not occupied by
    //  a ground unit.
    private boolean isClear(MapLocation toTest) throws GameActionException {
        TerrainTile thisTerrain = rc.senseTerrainTile(toTest);
        return thisTerrain == TerrainTile.UNKNOWN
                || (thisTerrain.isTraversable()
                && (!rc.canSenseLocation(toTest)
                || rc.senseRobotAtLocation(toTest) == null
                || rc.senseRobotAtLocation(toTest).type == RobotType.DRONE)
                && !inEnemyTowerRange(toTest));
    }

    // returns true if the given map location is in range of one of the enemy towers
    private boolean inEnemyTowerRange(MapLocation toTest) {
        for (MapLocation enemyTower : enemyTowers) {
            if ((myPath == null || !enemyTower.equals(myPath.destination))
                    && toTest.distanceSquaredTo(enemyTower) < RobotType.TOWER.attackRadiusSquared) {
                return true;
            }
        }
        if (!enemyHQ.equals(toTest)
                && toTest.distanceSquaredTo(enemyHQ) < RobotType.HQ.attackRadiusSquared) {
            return true;
        }
        return false;
    }

    // returns true if the given Robot is a building
    private boolean isBuilding(RobotInfo toTest) {
        return toTest != null && (toTest.type == RobotType.TOWER
                || toTest.type == RobotType.BARRACKS
                || toTest.type == RobotType.MINERFACTORY
                || toTest.type == RobotType.HELIPAD
                || toTest.type == RobotType.SUPPLYDEPOT
                || toTest.type == RobotType.HQ
                || toTest.type == RobotType.TANKFACTORY
                || toTest.type == RobotType.TECHNOLOGYINSTITUTE
                || toTest.type == RobotType.TRAININGFIELD
                || toTest.type == RobotType.AEROSPACELAB
                || toTest.type == RobotType.HANDWASHSTATION);
    }

    private class Path {

        private MapLocation destination; // Where this path is headed
        private Node path;               // List of Nodes with Locations along which to move
        private PriorityQueue<Node> tree;// Complete list of all explored nodes so far in finding this path
        private boolean isComplete;
        private HashMap<Integer, Integer> contents;

        public Path(MapLocation inDestination) {
            destination = inDestination;
            // Need to seed tree with this location;
            tree = new PriorityQueue<Node>(30, new LocNodeComparator());
            tree.add(new Node(inDestination, rc.getLocation().distanceSquaredTo(destination), null));
            path = null;
            contents = new HashMap<Integer, Integer>();
            isComplete = false;
        }

        // Will continue to build the path until it is complete or robot has used
        //  half of its bytecodes
        public void buildPath() throws GameActionException {
            MapLocation myLoc = rc.getLocation();
            Node closest;
            if (!isComplete) {
                while (Clock.getBytecodeNum() < Clock.getBytecodesLeft() && !myLoc.isAdjacentTo(getClosestLoc())) {
                    closest = getClosest();
                    if (closest != null) {
                        addAllAdjacent(closest);
                    }
                }
                closest = getClosest();
                if (closest == null) {
                    tree = new PriorityQueue<Node>(30, new LocNodeComparator());
                    tree.add(new Node(destination, rc.getLocation().distanceSquaredTo(destination), null));
                    path = null;
                    contents = new HashMap<Integer, Integer>();
                    isComplete = false;
                } else if (myLoc.isAdjacentTo(closest.loc)) {
                    path = tree.peek();
                    convertToPath(tree.peek());
                    tree = null;
                    isComplete = true;
                }
            }
        }

        public void convertToPath(Node toAdd) {
            toAdd.rightChild = null;
            toAdd.leftChild = null;
            toAdd.parent = null;
            if (toAdd.origin != null) {
                convertToPath(toAdd.origin);
            }
        }

        //Not yet implemented
        // Will look at each MapLocation adjacent to center, and add them
        //  to the list if they are not on the list, and they are passable
        public void addAllAdjacent(Node centerNode) throws GameActionException {
            MapLocation center = centerNode.loc;
            Direction[] directions = Direction.values();

            // Go through each of the cardinal directions and add the locations to
            //  the tree if necessary
            while (centerNode.directionsChecked < 10) {
                if (Clock.getBytecodeNum() > 3 * Clock.getBytecodesLeft()) {
                    return;
                }
                Direction dir = directions[centerNode.directionsChecked];
                if (dir != Direction.OMNI && dir != Direction.NONE) { // We only want compass directions
                    MapLocation nextLoc = center.add(dir);
                    // We only add it if it is traversable and not in the tree, and if it is traversable,
                    //  not in the tree, and unoccupied if it is closer than 10 units squared
                    if (!inTree(nextLoc)) {
                        contents.put(locToKey(nextLoc), 0);
                        if (centerNode.dist >= 10 && isPassable(nextLoc) || centerNode.dist < 10 && isClear(nextLoc)) {
                            tree.add(new Node(nextLoc, centerNode));
                        }
                    }
                    centerNode.directionsChecked++;
                }
                tree.remove(centerNode);
            }
        }

        // Debugging Method
        public void printTree(Node start, int index) {
            System.out.println(index);
            System.out.println(start.dist);
            if (start.leftChild != null) {
                System.out.println(start.leftChild);
                printTree(start.leftChild, index * 2);
            }
            if (start.rightChild != null) {
                System.out.println(start.rightChild);
                printTree(start.rightChild, index * 2 + 1);
            }
        }

        // Debugging Method
        public void printPath(Node start) {
            if (start.origin == null) {
                System.out.println(start.loc.x + ", " + start.loc.y);
                System.out.println("Done:");
            } else {
                System.out.println(start.loc.x + ", " + start.loc.y);
                printPath(start.origin);
            }
        }

        // Will search through the tree to see if a Node containing the given
        //  location exists
        public boolean inTree(MapLocation toFind) {
            // Return true if the current node contains the given location
            return contents.containsKey(locToKey(toFind));
        }

        // Takes a map location and returns a hash key for inserting/deleting in
        //  a hashmap
        private int locToKey(MapLocation loc) {
            return loc.x * 100000 + loc.y;
        }

        // Will get the node which contains the current closest explored point to
        //  our current position
        private Node getClosest() {
//            System.out.println("isComplete = " + isComplete);
//            System.out.println("path = " + path);
//            System.out.println("tree = " + tree);
            if (isComplete) {
                return path;
            } else {
                return tree.peek();
            }
        }

        // Returns the explored MapLocation closest to robot's location
        public MapLocation getClosestLoc() {
            Node toReturn = getClosest();
//            System.out.println(toReturn);
            if (toReturn == null) {
                return null;
            } else {
                return toReturn.loc;
            }
        }

        // Returns the second location along path
        public MapLocation getSecondLoc() {
            if (!isComplete) {
                return null;
            }
            if (path.origin == null) {
                return null;
            }
            return path.origin.loc;
        }

        // Returns the direction to the first stop along our path, and removes
        //  it from the list of location. May need to check and make sure that a
        //  path is finalized before pulling
        public Direction makeNextMove() {
            if (path == null) {
                return null;
            }
            Direction nextMove = rc.getLocation().directionTo(getClosestLoc());
            path = path.origin;
            return nextMove;
        }

        /**
         * If another robot is blocking our path, try to find a way around it.
         * This method will remove up to the next three steps in our path, and
         * find a new route to our destination via unoccupied squares.
         *
         * @return is apparently always null... Will maybe fix that at some
         * point
         */
        public Direction findDetour() {
            Node closest = getClosest();
            if (closest == null) {
                tree = new PriorityQueue<Node>(30, new LocNodeComparator());
                tree.add(new Node(destination, rc.getLocation().distanceSquaredTo(destination), null));
                path = null;
                contents = new HashMap<Integer, Integer>();
                isComplete = false;
                return null;
            }
            Node second = closest.origin;
            if (second == null) {
                tree = new PriorityQueue<Node>(30, new LocNodeComparator());
                tree.add(new Node(destination, rc.getLocation().distanceSquaredTo(destination), null));
                path = null;
                contents = new HashMap<Integer, Integer>();
                isComplete = false;
                return null;
            }
            Node third = second.origin;
            if (third == null) {
                tree = new PriorityQueue<Node>(30, new LocNodeComparator());
                tree.add(second);
                path = null;
                contents = new HashMap<Integer, Integer>();
                contents.put(locToKey(second.loc), 0);
                isComplete = false;
                return null;
            }
            Node fourth = third.origin;
            if (fourth == null) {
                tree = new PriorityQueue<Node>(30, new LocNodeComparator());
                tree.add(third);
                path = null;
                contents = new HashMap<Integer, Integer>();
                contents.put(locToKey(third.loc), 0);
                isComplete = false;
                return null;
            } else {
                tree = new PriorityQueue<Node>(30, new LocNodeComparator());
                tree.add(fourth);
                path = null;
                contents = new HashMap<Integer, Integer>();
                contents.put(locToKey(fourth.loc), 0);
                isComplete = false;
                return null;
            }

            // No wait... Just have it check for units if the distance is shorter than x
            //  and if we get stuck along the way, just remove the next couple steps and try
            //  some more.
        }
    }

    private class Node {

        public MapLocation loc; // MapLocation represented
        public double dist;     // Distance from Location to origin
        public Node origin;     // The node from which this node was reached
        public Node parent, rightChild, leftChild; // Connected elements of this Node
        public int directionsChecked; // contains the number of directions adjacent to
        // this node which have already been tested for a closer path

        public Node(MapLocation inLoc, double inDist, Node inOrigin) {
            loc = inLoc;
            dist = inDist;
            origin = inOrigin;
            parent = rightChild = leftChild = null;
            directionsChecked = 0;
        }

        public Node(MapLocation inLoc, Node inOrigin) {
            loc = inLoc;
            dist = rc.getLocation().distanceSquaredTo(loc);
            origin = inOrigin;
            parent = rightChild = leftChild = null;
            directionsChecked = 0;
        }
    }

    private class LocNodeComparator implements Comparator<Node> {

        public int compare(Node t, Node t1) {
            return (int) (t.dist - t1.dist);
        }

    }
}
