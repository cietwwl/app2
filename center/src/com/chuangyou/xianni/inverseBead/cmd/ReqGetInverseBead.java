package com.chuangyou.xianni.inverseBead.cmd;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.inverseBead.InverseBeadMsgProto.InverseBeadMsg;
import com.chuangyou.common.protobuf.pb.inverseBead.ResGetInverseBeadMsgProto.ResGetInverseBeadMsg;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.inverseBead.InverseBeadInventory;
import com.chuangyou.xianni.inverseBead.action.InverseBeadLoopAction;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
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
		InverseBeadLoopAction action = new InverseBeadLoopAction(player, player.getActionQueue(), InverseBeadInventory.getBeadRefreshIdList(), true);
		//player.getActionQueue().enqueue(action);
		action.execute();
		
		ResGetInverseBeadMsg.Builder msg = ResGetInverseBeadMsg.newBuilder();
		for (PlayerInverseBead inverseBead : playerInverseBeadList.values()) {
			InverseBeadMsg.Builder bean = InverseBeadMsg.newBuilder();
			bean.setFiveElements(inverseBead.getFiveElements());
			bean.setMarking(inverseBead.getMarking());
			bean.setStage(inverseBead.getStage());
			bean.setVal(inverseBead.getVal());

			msg.addInverseBead(bean);
		}
		// PlayerTimeInfo playerTimeInfo =
		// player.getBasePlayer().getPlayerTimeInfo();
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		String beadRefreshId = playerTimeInfo.getBeadRefreshId();
		List<Integer> list = InverseBeadManager.getBeadRefreshId(beadRefreshId);
		// System.out.println(list);
		msg.setMonsterNum(list.size());
		msg.setAuraNum(playerTimeInfo.getAuraNum());
		if (playerTimeInfo.getBeadRefreshDateTime() != null)
			msg.setBeadRefreshDateTime(playerTimeInfo.getBeadRefreshDateTime().getTime());
		else
			msg.setBeadRefreshDateTime(0);
		if (playerTimeInfo.getAuraRefreshDateTime() != null)
			msg.setAuraRefreshDateTime(playerTimeInfo.getAuraRefreshDateTime().getTime());
		else
			msg.setAuraRefreshDateTime(0);

		long lastTime = InverseBeadLoopAction.intervalTime - (System.currentTimeMillis() - playerTimeInfo.getBeadRefreshDateTime().getTime());
		if (lastTime < 0)
			lastTime = 0;

		SpawnInfo tem = InverseBeadTemMgr.getSpwanId(playerTimeInfo.getCurrRefreshId());
		MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(tem.getEntityId());
		msg.setMonsterLv(monsterInfo.getLevel());
		msg.setLastTime((int) Math.ceil(lastTime));

		PBMessage p = MessageUtil.buildMessage(Protocol.U_INVERSE_BEAD_DATE, msg);
		player.sendPbMessage(p);
	}
}
