package deathBotOne;

import battlecode.common.*;

public class Communicate {
	/*
	 * Constants needed for communication
	 */
	

//Universal Signals
    public static final int DISTRESS = 911;
    public static final int GO_TO = 100;
    public static final int KILL_ENEMY = 111;
    public static final int DEFEND = 222;
    public static final int ENEMY_LOCATED = 333;
    public static final int COVER_UNITS = 444;

    //Build Ordering
    public static final int BUILD_LIST_START = 3950;
    public static final int BUILD_LIST_SIZE = 5;
    public static final int ITEM_BUILT_INDICATOR = 1;

    //Build Instructions
    public static final int BUILD_AEROSPACELAB = 10;
    public static final int BUILD_BARRACKS = 20;
    public static final int BUILD_BASHER = 30;
    public static final int BUILD_BEAVER = 40;
    public static final int BUILD_COMMANDER = 50;
    public static final int BUILD_COMPUTER = 60;
    public static final int BUILD_DRONE = 70;
    public static final int BUILD_HANDWASHSTATION = 80;
    public static final int BUILD_HELIPAD = 90;
    public static final int BUILD_LAUNCHER = 100;
    public static final int BUILD_MINER = 110;
    public static final int BUILD_MINERFACTORY = 120;
    public static final int BUILD_MISSILE = 130;
    public static final int BUILD_SOLDIER = 140;
    public static final int BUILD_SUPPLYDEPOT = 150;
    public static final int BUILD_TANK = 160;
    public static final int BUILD_TANKFACTORY = 170;
    public static final int BUILD_TECHNOLOGYINSTITUTE = 180;
    public static final int BUILD_TRAININGFIELD = 190;

    //Army size Tracking
    // each unit announces to a set channel, ANNOUNCE_START + their indicated offset
    public static final int PUBLIC_ANNOUNCE_START = 37;
    public static final int BEAVER_ANNOUNCE = 0;
    public static final int AEROSPACELAB_ANNOUNCE = 1;
    public static final int BARRACKS_ANNOUNCE = 2;
    public static final int BASHER_ANNOUNCE = 3;
    public static final int COMMANDER_ANNOUNCE = 4;
    public static final int TOWER_ANNOUNCE = 5;
    public static final int TECHNOLOGYINSTITUTE_ANNOUNCE = 6;
    public static final int TANKFACTORY_ANNOUNCE = 7;
    public static final int COMPUTER_ANNOUNCE = 8;
    public static final int DRONE_ANNOUNCE = 9;
    public static final int HANDWASHSTATION_ANNOUNCE = 10;
    public static final int HELIPAD_ANNOUNCE = 11;
    public static final int HQ_ANNOUNCE = 12;
    public static final int LAUNCHER_ANNOUNCE = 13;
    public static final int MINER_ANNOUNCE = 14;
    public static final int MINERFACTORY_ANNOUNCE = 15;
    public static final int MISSILE_ANNOUNCE = 16;
    public static final int SOLDIER_ANNOUNCE = 17;
    public static final int SUPPLYDEPOT_ANNOUNCE = 18;
    public static final int TANK_ANNOUNCE = 19;
    public static final int TRAININGFIELD_ANNOUNCE = 20;
    public static final int PUBLIC_ANNOUNCE_END = PUBLIC_ANNOUNCE_START + TRAININGFIELD_ANNOUNCE; // Keeps track of last channel in chunk
    // A set of private channels for the HQ only to write counts to
    public static final int PRIVATE_ANNOUNCE_START = PUBLIC_ANNOUNCE_END + 1;
    public static final int PRIVATE_ANNOUNCE_END = PRIVATE_ANNOUNCE_START + TRAININGFIELD_ANNOUNCE;
    // A set of channels for the strat Computer to write required build levels onto
    public static final int BUILD_REQUEST_START = PRIVATE_ANNOUNCE_END + 1;
    public static final int BUILD_REQUEST_END = BUILD_REQUEST_START + TRAININGFIELD_ANNOUNCE;

    //UNITS
    public static final int BEAVER = 50000;
    public static final int AEROSPACELAB = 51000;
    public static final int BARRACKS = 52000;
    public static final int BASHER = 53000;
    public static final int COMMANDER = 54000;
    public static final int TOWER = 55000;
    public static final int TECHNOLOGYINSTITUTE = 56000;
    public static final int TANKFACTORY = 57000;
    public static final int COMPUTER = 58000;
    public static final int DRONE = 59000;
    public static final int HANDWASHSTATION = 60000;
    public static final int HELIPAD = 61000;
    public static final int HQ = 62000;
    public static final int LAUNCHER = 63000;
    public static final int MINER = 64000;
    public static final int MINERFACTORY = 45000;
    public static final int MISSILE = 46000;
    public static final int SOLDIER = 47000;
    public static final int SUPPLYDEPOT = 48000;
    public static final int TANK = 49000;

    //For Mining
    public static final int LOTS_ORE = 1111;
    public static final int MEDIUM_ORE = 1222;
    public static final int LITTLE_ORE = 1333;

    //Beavers
    public static final int BEAVER_GO_TO = 4000 + GO_TO;
    public static final int BEAVER_KILL_ENEMY = 4000 + KILL_ENEMY;
    public static final int BEAVER_DEFEND = 4000 + DEFEND;
    public static final int BEAVER_COVER_UNITS = 4000 + COVER_UNITS;

    //Factories
    public static final int SPAWN_SOLDIER = 5020;
    public static final int SPAWN_BASHER = 2020;
    public static final int SPAWN_MINER = 3020;
    public static final int SPAWN_DRONE = 7020;
    public static final int SPAWN_COMPUTER = 8020;
    public static final int SPAWN_TANK = 9020;

    //Tanks
    public static final int TANK_GO_TO = 9000 + GO_TO;
    public static final int TANK_KILL_ENEMY = 9000 + KILL_ENEMY;
    public static final int TANK_DEFEND = 9000 + DEFEND;
    public static final int TANK_COVER_UNITS = 9000 + COVER_UNITS;

    //Drones
    public static final int DRONE_GO_TO = 8000 + GO_TO;
    public static final int DRONE_KILL_ENEMY = 8000 + KILL_ENEMY;
    public static final int DRONE_DEFEND = 8000 + DEFEND;
    public static final int DRONE_COVER_UNITS = 8000 + COVER_UNITS;

    //Miners
    public static final int MINER_GO_TO = 7000 + GO_TO;
    public static final int MINER_KILL_ENEMY = 7000 + KILL_ENEMY;
    public static final int MINER_DEFEND = 7000 + DEFEND;
    public static final int MINER_COVER_UNITS = 7000 + COVER_UNITS;

    /**
     * Method for every robot to announce themselves at the beginning of the
     * turn This allows the HQ to keep tabs easily on the size and composition
     * of the army
     *
     * @param rc The robot which needs to announce itself
     * @throws battlecode.common.GameActionException
     */
    public static void announce(RobotController rc) throws GameActionException {
        int channel = publicAnnounceChannel(rc.getType());
        rc.broadcast(channel, rc.readBroadcast(channel) + 1);
    }

    /**
     *
     * @param rc The robot who is reading the announce channels
     * @param type The type of robot we want to count
     * @return The number of robots of type who have announced
     * @throws GameActionException
     */
    public static int readAnnounce(RobotController rc, RobotType type) throws GameActionException {
        if (rc.getType() == RobotType.HQ) {
            return rc.readBroadcast(publicAnnounceChannel(type));
        } else {
            return rc.readBroadcast(privateAnnounceChannel(type));
        }
    }

    public static int hqGetAnnounce(RobotController rc, RobotType type) throws GameActionException {
        int count = readAnnounce(rc, type);
//        System.out.println(type + " " + count);
        rc.broadcast(privateAnnounceChannel(type), count);
        clearAnnounce(rc, type);
        return count;
    }

    /**
     * Clears out the announces from the previous turn so that we can start
     * again from zero - Done by HQ during upkeep
     *
     * @param rc The robot who is clearing the channels
     * @param type The type of robot whose channels is to be cleared
     * @throws GameActionException
     */
    public static void clearAnnounce(RobotController rc, RobotType type) throws GameActionException {
        rc.broadcast(publicAnnounceChannel(type), 0);
    }

    /**
     * This method takes a RobotType and returns the channel on which robots of
     * that type announce their existence
     *
     * @param type The type of robot we want a channel for
     * @return The channel on which robots of type announce
     */
    public static int publicAnnounceChannel(RobotType type) {
        switch (type) {
            case AEROSPACELAB:
                return PUBLIC_ANNOUNCE_START + AEROSPACELAB_ANNOUNCE;
            case BARRACKS:
                return PUBLIC_ANNOUNCE_START + BARRACKS_ANNOUNCE;
            case BASHER:
                return PUBLIC_ANNOUNCE_START + BASHER_ANNOUNCE;
            case BEAVER:
                return PUBLIC_ANNOUNCE_START + BEAVER_ANNOUNCE;
            case COMMANDER:
                return PUBLIC_ANNOUNCE_START + COMMANDER_ANNOUNCE;
            case COMPUTER:
                return PUBLIC_ANNOUNCE_START + COMPUTER_ANNOUNCE;
            case DRONE:
                return PUBLIC_ANNOUNCE_START + DRONE_ANNOUNCE;
            case HANDWASHSTATION:
                return PUBLIC_ANNOUNCE_START + HANDWASHSTATION_ANNOUNCE;
            case HELIPAD:
                return PUBLIC_ANNOUNCE_START + HELIPAD_ANNOUNCE;
            case LAUNCHER:
                return PUBLIC_ANNOUNCE_START + LAUNCHER_ANNOUNCE;
            case MINER:
                return PUBLIC_ANNOUNCE_START + MINER_ANNOUNCE;
            case MINERFACTORY:
                return PUBLIC_ANNOUNCE_START + MINERFACTORY_ANNOUNCE;
            case MISSILE:
                return PUBLIC_ANNOUNCE_START + MISSILE_ANNOUNCE;
            case SOLDIER:
                return PUBLIC_ANNOUNCE_START + SOLDIER_ANNOUNCE;
            case SUPPLYDEPOT:
                return PUBLIC_ANNOUNCE_START + SUPPLYDEPOT_ANNOUNCE;
            case TANK:
                return PUBLIC_ANNOUNCE_START + TANK_ANNOUNCE;
            case TANKFACTORY:
                return PUBLIC_ANNOUNCE_START + TANKFACTORY_ANNOUNCE;
            case TECHNOLOGYINSTITUTE:
                return PUBLIC_ANNOUNCE_START + TECHNOLOGYINSTITUTE_ANNOUNCE;
            case TRAININGFIELD:
                return PUBLIC_ANNOUNCE_START + TRAININGFIELD_ANNOUNCE;
            default:
                return 65500;
        }
    }

    /**
     * offsets publicAnnounceChannel so that it can be used by HQ/Computers
     *
     * @param type The type of robot we need a channel for
     * @return Channel used by HQ/Computers to count forces of type
     */
    public static int privateAnnounceChannel(RobotType type) {
        return publicAnnounceChannel(type) + PRIVATE_ANNOUNCE_START - PUBLIC_ANNOUNCE_START;
    }

    /**
     * robot sends an encoded message to robots with the same type to the
     * location they are currently at
     *
     * @param rc RobotController whose location is to be encoded and send a
     * message
     * @param signal message to be sent
     * @throws GameActionException
     */
    public static void sendBroadcast(RobotController rc, int signal) throws GameActionException {
        MapLocation rcLoc = rc.getLocation(), hqLoc = rc.senseHQLocation(), loc;
        loc = new MapLocation(rcLoc.x - hqLoc.x, rcLoc.y - hqLoc.y);
        rc.broadcast(chooseStation(rc.getType(), loc), loc.x * 1000000000 + loc.y * 1000000 + signal);
    }

    /**
     * robot sends an encoded message to robots of the same type to the given
     * location
     *
     * @param rc RobotController who is to send a message
     * @param loc MapLocation where message is to be sent to
     * @param signal message to be sent
     * @throws GameActionException
     */
    public static void sendBroadcast(RobotController rc, MapLocation rcLoc, int signal) throws GameActionException {
        MapLocation hqLoc = rc.senseHQLocation(), loc;
        loc = new MapLocation(rcLoc.x - hqLoc.x, rcLoc.y - hqLoc.y);
        rc.broadcast(chooseStation(rc.getType(), loc), loc.x * 1000000000 + loc.y * 1000000 + signal);
    }

    /**
     * robot sends an encoded message to the given type of robot to the location
     * they are currently at
     *
     * @param rc RobotController whose location is to be encoded and send a
     * message
     * @param intendedRecipient The type of robot who should get this message
     * @param signal message to be sent
     * @throws GameActionException
     */
    public static void sendBroadcast(RobotController rc, RobotType intendedRecipient, int signal) throws GameActionException {
        MapLocation rcLoc = rc.getLocation(), hqLoc = rc.senseHQLocation(), loc;
        rc.broadcast(unitToChannel(intendedRecipient), encode(rcLoc, hqLoc, signal));
    }

    /**
     * robot sends an encoded message to the given type of robot to the given
     * location
     *
     * @param rc RobotController who is to send a message
     * @param loc MapLocation where message is to be sent to
     * @param signal message to be sent
     * @throws GameActionException
     */
    public static void sendBroadcast(RobotController rc, MapLocation rcLoc, RobotType robotType, int signal) throws GameActionException {
        MapLocation hqLoc = rc.senseHQLocation(), loc;
        loc = new MapLocation(rcLoc.x - hqLoc.x, rcLoc.y - hqLoc.y);
        rc.broadcast(chooseStation(robotType, loc), loc.x * 1000000000 + loc.y * 1000000 + signal);
    }

    /**
     * Selects a station based on robot type and location
     *
     * @param robotType robot type message is sent to
     * @param loc location the message is sent to
     * @return station
     */
    private static int chooseStation(RobotType robotType, MapLocation loc) {
        if (robotType == RobotType.BEAVER) {
            return BEAVER + loc.x + loc.y;
        } else if (robotType == RobotType.DRONE) {
            return DRONE + loc.x + loc.y;
        } else if (robotType == RobotType.MINER) {
            return MINER + loc.x + loc.y;
        } else if (robotType == RobotType.AEROSPACELAB) {
            return AEROSPACELAB + loc.x + loc.y;
        } else if (robotType == RobotType.BARRACKS) {
            return BARRACKS + loc.x + loc.y;
        } else if (robotType == RobotType.BASHER) {
            return BASHER + loc.x + loc.y;
        } else if (robotType == RobotType.COMMANDER) {
            return COMMANDER + loc.x + loc.y;
        } else if (robotType == RobotType.COMPUTER) {
            return COMPUTER + loc.x + loc.y;
        } else if (robotType == RobotType.TANK) {
            return TANK + loc.x + loc.y;
        } else if (robotType == RobotType.HANDWASHSTATION) {
            return HANDWASHSTATION + loc.x + loc.y;
        } else if (robotType == RobotType.HELIPAD) {
            return HELIPAD + loc.x + loc.y;
        } else if (robotType == RobotType.HQ) {
            return HQ + loc.x + loc.y;
        } else if (robotType == RobotType.LAUNCHER) {
            return LAUNCHER + loc.x + loc.y;
        } else if (robotType == RobotType.MINERFACTORY) {
            return MINERFACTORY + loc.x + loc.y;
        } else if (robotType == RobotType.MISSILE) {
            return MISSILE + loc.x + loc.y;
        } else if (robotType == RobotType.SOLDIER) {
            return SOLDIER + loc.x + loc.y;
        } else if (robotType == RobotType.SUPPLYDEPOT) {
            return SUPPLYDEPOT + loc.x + loc.y;
        } else if (robotType == RobotType.TANKFACTORY) {
            return TANKFACTORY + loc.x + loc.y;
        } else if (robotType == RobotType.TECHNOLOGYINSTITUTE) {
            return TECHNOLOGYINSTITUTE + loc.x + loc.y;
        } else if (robotType == RobotType.TOWER) {
            return TOWER + loc.x + loc.y;
        }

        return 0;
    }

    /**
     * Sends out the build instructions for the current turn to the robot army
     * intended for use by HQ, or other delegated unit.
     *
     * @param rc The robot who is sending this message
     * @param buildList An ordered list of build instructions to be broadcast
     * @throws GameActionException
     */
    public static void writeBuildRequest(RobotController rc, BuildOrder[] buildList) throws GameActionException {
        for (int i = 0; i < buildList.length; i++) {
            if (buildList[i] == null) {
                return;
            }
            if (buildList[i].loc == null) {
                rc.broadcast(BUILD_LIST_START + i, getBuildCode(buildList[i].type));
            } else {
                rc.broadcast(BUILD_LIST_START + i, encode(buildList[i].loc, rc.senseHQLocation(), getBuildCode(buildList[i].type)));
            }
//            System.out.println(i + ": Build " + buildList[i].type);
        }
    }

    /**
     * Returns the proper numeric build code for each robot type
     *
     * @param type Type of robot that needs to be built
     * @return the numeric code for the unit type.
     */
    private static int getBuildCode(RobotType type) {
        switch (type) {
            case AEROSPACELAB:
                return BUILD_AEROSPACELAB;
            case BARRACKS:
                return BUILD_BARRACKS;
            case BASHER:
                return BUILD_BASHER;
            case BEAVER:
                return BUILD_BEAVER;
            case COMMANDER:
                return BUILD_COMMANDER;
            case COMPUTER:
                return BUILD_COMPUTER;
            case DRONE:
                return BUILD_DRONE;
            case HANDWASHSTATION:
                return BUILD_HANDWASHSTATION;
            case HELIPAD:
                return BUILD_HELIPAD;
            case LAUNCHER:
                return BUILD_LAUNCHER;
            case MINER:
                return BUILD_MINER;
            case MINERFACTORY:
                return BUILD_MINERFACTORY;
            case MISSILE:
                return BUILD_MISSILE;
            case SOLDIER:
                return BUILD_SOLDIER;
            case SUPPLYDEPOT:
                return BUILD_SUPPLYDEPOT;
            case TANK:
                return BUILD_TANK;
            case TANKFACTORY:
                return BUILD_TANKFACTORY;
            case TECHNOLOGYINSTITUTE:
                return BUILD_TECHNOLOGYINSTITUTE;
            case TRAININGFIELD:
                return BUILD_TRAININGFIELD;
            default:
                return 0;
        }
    }

    /**
     * readBuildReques() reads the build list from comm and returns it to a unit
     * as a BuildOrder[];
     *
     * @param rc robot who is getting these orders
     * @return
     * @throws GameActionException
     */
    public static BuildOrder[] readBuildRequest(RobotController rc) throws GameActionException {
        BuildOrder[] buildRequest = new BuildOrder[BUILD_LIST_SIZE];
        for (int i = 0; i < BUILD_LIST_SIZE; i++) {
            int thisOrder = rc.readBroadcast(BUILD_LIST_START + i);
            if (thisOrder == 0) {
                break;
            }
            int[] message = decode(rc.senseHQLocation(), thisOrder);
            if (message[0] == 0 && message[1] == 0) {
                if (message[3] == 1) {
                    buildRequest[i] = null;
                } else {
                    buildRequest[i] = new BuildOrder(codeToUnit(message[2]));
                }
            } else {
                buildRequest[i] = new BuildOrder(new MapLocation(message[0], message[1]), codeToUnit(message[2]));
            }
        }
        return buildRequest;
    }

    public static void indicateOrderFilled(RobotController rc, int orderNum) throws GameActionException {
        rc.broadcast(BUILD_LIST_START + orderNum, ITEM_BUILT_INDICATOR);
    }

    /**
     * Method to get a RobotType from a build code
     *
     * @param buildCode the build code we want to interpret
     * @return the RobotType corresponding to our build code
     */
    public static RobotType codeToUnit(int buildCode) {
        switch (buildCode) {
            case BUILD_AEROSPACELAB:
                return RobotType.AEROSPACELAB;
            case BUILD_BARRACKS:
                return RobotType.BARRACKS;
            case BUILD_BASHER:
                return RobotType.BASHER;
            case BUILD_BEAVER:
                return RobotType.BEAVER;
            case BUILD_COMMANDER:
                return RobotType.COMMANDER;
            case BUILD_COMPUTER:
                return RobotType.COMPUTER;
            case BUILD_DRONE:
                return RobotType.DRONE;
            case BUILD_HANDWASHSTATION:
                return RobotType.HANDWASHSTATION;
            case BUILD_HELIPAD:
                return RobotType.HELIPAD;
            case BUILD_LAUNCHER:
                return RobotType.LAUNCHER;
            case BUILD_MINER:
                return RobotType.MINER;
            case BUILD_MINERFACTORY:
                return RobotType.MINERFACTORY;
            case BUILD_MISSILE:
                return RobotType.MISSILE;
            case BUILD_SOLDIER:
                return RobotType.SOLDIER;
            case BUILD_SUPPLYDEPOT:
                return RobotType.SUPPLYDEPOT;
            case BUILD_TANK:
                return RobotType.TANK;
            case BUILD_TANKFACTORY:
                return RobotType.TANKFACTORY;
            case BUILD_TECHNOLOGYINSTITUTE:
                return RobotType.TECHNOLOGYINSTITUTE;
            case BUILD_TRAININGFIELD:
                return RobotType.TRAININGFIELD;
            default:
                return null;
        }
    }

    /**
     * Decodes the encoded message on selected channel
     *
     * @param rc Robot Controller to read broadcast
     * @param channel Channel to be decoded
     * @return int[] x location, y location, message
     * @throws GameActionException
     */
    public static int[] readBroadcast(RobotController rc, int channel) throws GameActionException {
        int signal = rc.readBroadcast(channel);
        return decode(rc.senseHQLocation(), signal);
    }

    private static int unitToChannel(RobotType intendedRecipient) {
        switch (intendedRecipient) {
            case BEAVER:
                return BEAVER;
            case AEROSPACELAB:
                return AEROSPACELAB;
            case BARRACKS:
                return BARRACKS;
            case BASHER:
                return BASHER;
            case COMMANDER:
                return COMMANDER;
            case TOWER:
                return TOWER;
            case TECHNOLOGYINSTITUTE:
                return TECHNOLOGYINSTITUTE;
            case TANKFACTORY:
                return TANKFACTORY;
            case COMPUTER:
                return COMPUTER;
            case DRONE:
                return DRONE;
            case HANDWASHSTATION:
                return HANDWASHSTATION;
            case HELIPAD:
                return HELIPAD;
            case HQ:
                return HQ;
            case LAUNCHER:
                return LAUNCHER;
            case MINER:
                return MINER;
            case MINERFACTORY:
                return MINERFACTORY;
            case MISSILE:
                return MISSILE;
            case SOLDIER:
                return SOLDIER;
            case SUPPLYDEPOT:
                return SUPPLYDEPOT;
            case TANK:
                return TANK;
            default:
                return 78;
        }
    }

    private static int encode(MapLocation loc, MapLocation tower, int signal) {
        return (loc.x - tower.x + 100) * 1000000 + (loc.y - tower.y + 100) * 1000 + signal;
    }

    private static int[] decode(MapLocation tower, int signal) {
        int message = signal % 1000;
        int y = ((signal / 1000) % 1000) + tower.y - 100;
        int x = (signal / 1000000) + tower.x - 100;
        return new int[]{x, y, message};
    }
}
