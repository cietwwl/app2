package com.chuangyou.xianni.warfield.cmd;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.action.EnterFieldAction;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_ENTERSCENE, desc = "进入场景")
public class EnterFieldCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {

		Log.error("玩家请求进入场景：playerId : +" + army.getPlayerId());
		ReqChangeMapMsg msg = ReqChangeMapMsg.parseFrom(packet.getBytes());
		PostionMsg pos = msg.getPostionMsg();
		EnterFieldAction action = new EnterFieldAction(army, pos.getMapId(), pos.getMapKey(), Vector3BuilderHelper.get(pos.getPostion()));
		army.enqueue(action);
	}

}
