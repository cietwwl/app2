package com.chuangyou.xianni.fieldBoss.cmd;

import com.chuangyou.common.protobuf.pb.boss.BossGotoPointProto.BossGotoPointMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.fieldBoss.template.FieldBossTemplateMgr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.manager.ScriptInterfaceScenceManager;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_FIELD_BOSS_GOTO, desc = "前往精英BOSS刷新点")
public class FieldBossGotoReqCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		BossGotoPointMsg req = BossGotoPointMsg.parseFrom(packet.getBytes());
		
		FieldBossCfg cfg = FieldBossTemplateMgr.getFieldBossMap().get(req.getMonsterId());
		if(cfg == null){
			Log.error("配置错误：tb_z_field_boss  monsterId = " +req.getMonsterId());
			return;
		}
		if(cfg.getType() == FieldBossCfg.WORLD_BOSS){
			return;
		}
		
		int spawnId = SpawnTemplateMgr.getSpwanId(cfg.getTagId());
		SpawnInfo spawnInfo = SpawnTemplateMgr.getSpawnInfo(spawnId);
		
		if(spawnInfo == null){
			Log.error("配置错误：spawnInfo  tagId = " + cfg.getTagId() + "  spawnId = " + spawnId);
			return;
		}
		
		ScriptInterfaceScenceManager.changeMap(army.getPlayerId(), spawnInfo.getMapid(), spawnInfo.getBound_x(), spawnInfo.getBound_y(), spawnInfo.getBound_z(), spawnInfo.getAngle());
	}

}
