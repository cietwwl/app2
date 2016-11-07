package com.chuangyou.xianni.robot;

import com.chuangyou.common.protobuf.pb.army.RobotCreateByClientProto.RobotCreateByClientMsg;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.PlotRobot;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_CREATE_PLOT, desc = "创建剧情人物")
public class CreatePlotRobotCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		RobotCreateByClientMsg msg = RobotCreateByClientMsg.parseFrom(packet.getBytes());
		for (Integer robotId : msg.getRobotList()) {
			RobotTemplate robotTemp = RobotManager.getRobotTemplate(robotId);
			if (robotTemp == null) {
				continue;
			} else {
				addRobot(army, robotTemp);
			}
		}
	}

	private void addRobot(ArmyProxy army, RobotTemplate template) {
		PlotRobot robot = new PlotRobot(IDMakerHelper.nextID());
		robot.instill(army, template);
		Field field = army.getPlayer().getField();
		Vector3 v3 = army.getPlayer().getPostion();
		robot.setPostion(v3);
		field.enterField(robot);
		army.addPlotFollower(robot);
	}

}
