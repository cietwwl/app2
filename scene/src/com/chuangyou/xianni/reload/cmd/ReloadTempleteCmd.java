package com.chuangyou.xianni.reload.cmd;

import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.role.template.NpcInfoTemplateMgr;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;

import io.netty.channel.Channel;

@Cmd(code = Protocol.S_TEMPLETE_RELOAD, desc = "重新加载模板数据")
public class ReloadTempleteCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		BattleTempMgr.reloadPb();
		FieldTemplateMgr.reloadFieldInfoTemp();
		SpawnTemplateMgr.reloadSpawnInfoTemp();
		MonsterInfoTemplateMgr.reloadMonsterInfoTemp();
		NpcInfoTemplateMgr.reloadNpcInfoTemp();
		
	}

}
