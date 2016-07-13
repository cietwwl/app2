package com.chuangyou.xianni.requestAction;

import com.chuangyou.common.protobuf.pb.SceneActionReqProto.SceneActionReqMsg;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.requestAction.helper.ActionConstants;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_REQ_ACTION, desc = "一些请求的集合")
public class SmallActionCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SceneActionReqMsg req = SceneActionReqMsg.parseFrom(packet.getBytes());
		int type = req.getType();
		if(type == ActionConstants.Actions.getNears)
		{
			
		}
	}

}
