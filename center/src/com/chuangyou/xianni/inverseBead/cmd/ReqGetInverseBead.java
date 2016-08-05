package com.chuangyou.xianni.inverseBead.cmd;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.inverseBead.InverseBeadMsgProto.InverseBeadMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.ResGetInverseBeadMsgProto.ResGetInverseBeadMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_INVERSE_BEAD_GET, desc = "获取获取五行数据")
public class ReqGetInverseBead extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		Map<String, PlayerInverseBead> playerInverseBeadList = player.getInverseBeadInventory().getInverseBead();

		ResGetInverseBeadMsg.Builder msg = ResGetInverseBeadMsg.newBuilder();
		for (PlayerInverseBead inverseBead : playerInverseBeadList.values()) {
			InverseBeadMsg.Builder bean = InverseBeadMsg.newBuilder();
			bean.setFiveElements(inverseBead.getFiveElements());
			bean.setMarking(inverseBead.getMarking());
			bean.setStage(inverseBead.getStage());
			bean.setVal(inverseBead.getVal());

			msg.addInverseBead(bean);
		}
		PlayerTimeInfo playerTimeInfo = player.getBasePlayer().getPlayerTimeInfo();
		String beadRefreshId = playerTimeInfo.getBeadRefreshId();
		List<Integer> list = InverseBeadManager.getBeadRefreshId(beadRefreshId);
		msg.setMonsterNum(list.size());
		msg.setBeadRefreshDateTime(playerTimeInfo.getBeadRefreshDateTime().getTime());
		msg.setAuraNum(playerTimeInfo.getAuraNum());
		msg.setAuraRefreshDateTime(playerTimeInfo.getAuraRefreshDateTime().getTime());

		PBMessage p = MessageUtil.buildMessage(Protocol.U_INVERSE_BEAD_DATE, msg);
		player.sendPbMessage(p);
	}
}
