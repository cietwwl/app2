package com.chuangyou.xianni.fieldBoss.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.boss.BossTimeUpdateProto.BossTimeUpdateMsg;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossDieInfo;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.sql.dao.DBManager;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_FIELD_BOSS_TIME_UPDATE, desc = "野外BOSS死亡和刷新时间更新")
public class FieldBossTimeUpdateCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		BossTimeUpdateMsg req = BossTimeUpdateMsg.parseFrom(packet.getBytes());
		
		FieldBossDieInfo info = new FieldBossDieInfo();
		info.setMonsterId(req.getMonsterId());
		info.setDeathTime(new Date(req.getDeathTime()));
		info.setNextTime(new Date(req.getNextTime()));
		DBManager.getFieldBossInfoDao().saveOrUpdate(info);
	}

}
