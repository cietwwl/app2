package com.chuangyou.xianni.fieldBoss.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.boss.BossInfoProto.BossInfoMsg;
import com.chuangyou.common.protobuf.pb.boss.BossListRespProto.BossListRespMsg;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.fieldBoss.manager.WorldBossManager;
import com.chuangyou.xianni.fieldBoss.template.FieldBossTemplateMgr;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.spawn.WorkingState;
import com.chuangyou.xianni.warfield.template.SpawnTemplateMgr;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_FIELD_BOSS_LIST, desc = "获取野外BOSS信息列表")
public class FieldBossListReqCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		Map<Integer, FieldBossCfg> bossMap = FieldBossTemplateMgr.getFieldBossMap();
		
		BossListRespMsg.Builder respMsg = BossListRespMsg.newBuilder();
		for(FieldBossCfg cfg: bossMap.values()){
			BossInfoMsg.Builder bossMsg = BossInfoMsg.newBuilder();
			
			SpwanNode spawnNode = null;
			if(cfg.getType() == FieldBossCfg.ELITE_BOSS){
				int spawnId = SpawnTemplateMgr.getSpwanId(cfg.getTagId());
				SpawnInfo spawnInfo = SpawnTemplateMgr.getSpawnInfo(spawnId);
				if(spawnInfo == null) continue;
				
				Field field = FieldMgr.getIns().getField(spawnInfo.getMapid());
				if(field == null || field.getMapKey() != spawnInfo.getMapid()) continue;
				spawnNode = field.getSpawnNode(spawnId);
			}else if(cfg.getType() == FieldBossCfg.WORLD_BOSS){
				spawnNode = WorldBossManager.getCurNode();
			}else{
				continue;
			}
			
			bossMsg.setTempId(cfg.getMonsterId());
			if(spawnNode == null){
				bossMsg.setState(2);
			}else{
				if(spawnNode.getState() instanceof WorkingState){
					bossMsg.setState(1);
				}else{
					bossMsg.setState(2);
				}
			}
			respMsg.addBoss(bossMsg);
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_FIELD_BOSS_LIST, respMsg);
		army.sendPbMessage(pkg);
	}

}
