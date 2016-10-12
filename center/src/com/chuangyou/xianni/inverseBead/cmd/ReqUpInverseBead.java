package com.chuangyou.xianni.inverseBead.cmd;

import com.chuangyou.common.protobuf.pb.inverseBead.ReqUpInverseBeadMsgProto.ReqUpInverseBeadMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.ResUpInverseBeadMsgProto.ResUpInverseBeadMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_INVERSE_BEAD_UP, desc = "升级五行")
public class ReqUpInverseBead extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqUpInverseBeadMsg msg = ReqUpInverseBeadMsg.parseFrom(packet.getBytes());
		int fiveElements = msg.getFiveElements();
		int marking = msg.getMarking();
		boolean res = InverseBeadManager.up(player, fiveElements, marking, packet.getCode());

		if (res) {
			PlayerInverseBead playerInverseBead = player.getInverseBeadInventory().get(fiveElements);
			ResUpInverseBeadMsg.Builder resUpInverseBeadMsg = ResUpInverseBeadMsg.newBuilder();
			resUpInverseBeadMsg.setFiveElements(playerInverseBead.getFiveElements());
			resUpInverseBeadMsg.setMarking(playerInverseBead.getMarking());
			resUpInverseBeadMsg.setStage(playerInverseBead.getStage());
			resUpInverseBeadMsg.setVal(playerInverseBead.getVal());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_INVERSE_BEAD_UP, resUpInverseBeadMsg);
			player.sendPbMessage(p);
		}
	}
}
