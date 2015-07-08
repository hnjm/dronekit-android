package org.droidplanner.core.MAVLink;

import org.droidplanner.core.model.Drone;

import com.MAVLink.common.msg_command_long;
import com.MAVLink.enums.MAV_CMD;
import com.o3dr.services.android.lib.model.ICommandListener;

public class MavLinkArm {

public static final int EMERGENCY_DISARM_MAGIC_NUMBER = 21196;

	public static void sendArmMessage(Drone drone, boolean arm, boolean emergencyDisarm, ICommandListener listener) {
		msg_command_long msg = new msg_command_long();
		msg.target_system = drone.getSysid();
		msg.target_component = drone.getCompid();

		msg.command = MAV_CMD.MAV_CMD_COMPONENT_ARM_DISARM;
		msg.param1 = arm ? 1 : 0;
		msg.param2 = emergencyDisarm ? EMERGENCY_DISARM_MAGIC_NUMBER : 0;
		msg.param3 = 0;
		msg.param4 = 0;
		msg.param5 = 0;
		msg.param6 = 0;
		msg.param7 = 0;
		msg.confirmation = 0;
		drone.getMavClient().sendMavMessage(msg, listener);
	}

}