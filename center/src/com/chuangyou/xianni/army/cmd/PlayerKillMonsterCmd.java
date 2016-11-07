package com.chuangyou.xianni.army.cmd;

import com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterListProto.PlayerKillMonsterListMsg;
import com.chuangyou.common.protobuf.pb.scene.PlayerKillMonsterProto.PlayerKillMonsterMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.battleMode.manager.BattleModeManager;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.constant.BattleSettlementConstant;
import com.chuangyou.xianni.constant.BattleSettlementConstant.MonsterDropType;
import com.chuangyou.xianni.constant.BattleSettlementConstant.RoleType;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.welfare.WelfareConditionHandleFactory;
import com.chuangyou.xianni.welfare.WelfareManager;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_PLAYER_KILL_MONSTER, desc = "杀怪通知")
public class PlayerKillMonsterCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {

		PlayerKillMonsterListMsg list = PlayerKillMonsterListMsg.parseFrom(packet.getBytes());
		for (PlayerKillMonsterMsg msg : list.getKillInfosList()) {
			GamePlayer player = WorldMgr.getPlayer(msg.getPlayerId());
			if (player == null) {
				continue;
			}
			if (msg.getType() == RoleType.monster) {// 怪物
				MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get((int) msg.getBeKillId());
				if (monsterInfo == null) {
					Log.error("can not find monstertemp : " + msg.getBeKillId());
					continue;
				}
				// 所有类型怪物，均计算任务进度
				player.notifyListeners(new ObjectEvent(this, monsterInfo.getMonsterId(), EventNameType.TASK_KILL_MONSTER));
				if (monsterInfo.getDropType() == MonsterDropType.ONLY_KILLER && msg.getJoinType() != BattleSettlementConstant.KILLER) {
					continue;
				}
				int addExp = monsterInfo.getBeKilledExp();
				int colour = BattleModeManager.getColour(player.getBasePlayer().getPlayerInfo().getPkVal());
				if (colour == BattleModeCode.yellow)
					addExp = (int) (addExp * 0.8);
				else if (colour == BattleModeCode.red) {
					addExp = (int) (addExp * 0.5);
				}
				player.getBasePlayer().addExp(addExp);
			}

			if (msg.getType() == RoleType.player) {// 人物
				GamePlayer beKiller = WorldMgr.getPlayer(msg.getBeKillId());
				if (beKiller == null) {
					continue;
				}
				PlayerInfo beKillerInfo = beKiller.getBasePlayer().getPlayerInfo();
				long exp = 0;// 损失经验
				// 黄名损失
				if (BattleModeManager.getColour(beKillerInfo.getPkVal()) == BattleModeCode.yellow) {
					exp = (int) (beKillerInfo.getExp() * (beKillerInfo.getPkVal() / 500000f));
				}
				// 红名损失
				if (BattleModeManager.getColour(beKillerInfo.getPkVal()) == BattleModeCode.red) {
					exp = (int) (beKillerInfo.getExp() * (beKillerInfo.getPkVal() / 100000f));
				}
				if (exp > 0) {
					player.getBasePlayer().addExp(-exp);
				}
			}
		}
	}

}
