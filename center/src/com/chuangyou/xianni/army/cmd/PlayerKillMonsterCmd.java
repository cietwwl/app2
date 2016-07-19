package com.chuangyou.xianni.army.cmd;

import com.chuangyou.common.protobuf.pb.PlayerKillMonsterProto.PlayerKillMonsterMsg;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.battleMode.manager.BattleModeManager;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

@Cmd(code=Protocol.C_PLAYER_KILL_MONSTER,desc="杀怪通知")
public class PlayerKillMonsterCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		PlayerKillMonsterMsg msg = PlayerKillMonsterMsg.parseFrom(packet.getBytes());
		GamePlayer player = WorldMgr.getPlayer(msg.getPlayerId());
		int monsterId =  msg.getMonsterTemplateId();
		
		MonsterInfo monsterInfo = MonsterInfoTemplateMgr.get(monsterId);
		
		if(player!=null){
			player.notifyListeners(new ObjectEvent(this,monsterId, EventNameType.TASK_KILL_MONSTER));
			if(monsterInfo!=null){
				int addExp = monsterInfo.getBeKilledExp();
				int colour = BattleModeManager.getColour(player.getBasePlayer().getPlayerInfo().getPkVal());
				if(colour == BattleModeCode.yellow)
					addExp =(int) (addExp*0.8);
				else if (colour == BattleModeCode.red) {
					addExp =(int) (addExp*0.5);
				}
				player.getBasePlayer().addExp(addExp);
			}
		}
		
		
	}

}
