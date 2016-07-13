package com.chuangyou.xianni.warfield.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

@Cmd(code = Protocol.S_PROPERTY_UPDATE, desc = "玩家属性更新")
public class PlayerPropertyUpdateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerAttUpdateMsg req = PlayerAttUpdateMsg.parseFrom(packet.getBytes());

		// 改变属性的玩家
		ArmyProxy pArmy = WorldMgr.getArmy(req.getPlayerId());

		if (pArmy != null) {
			// 修改玩家属性
			List<PropertyMsg> attList = req.getAttList();
			pArmy.getPlayer().updataProperty(attList);
			// 通知自己
			PBMessage selfPkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, req);
			army.sendPbMessage(selfPkg);
		} else {

		}

	}

}
