
package deathBotOne;

import battlecode.common.*;

/**
 *  A type to hold Build Orders from HQ
 * 
 * @author f46b945
 */
public class BuildOrder {
        public MapLocation loc;
        public RobotType type;
        
        public BuildOrder(RobotType inType){
            loc  = null;
            type = inType;
        }
        
        public BuildOrder(MapLocation inLoc, RobotType inType){
            loc  = inLoc;
            type = inType;
        }
    }
