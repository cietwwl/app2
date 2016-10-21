package com.chuangyou.xianni.fieldBoss.cmd;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.boss.BossDieProto.BossDieMsg;
import com.chuangyou.common.util.LanguageSet;
import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossCfg;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.fieldBoss.template.FieldBossTemplateMgr;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_FIELD_BOSS_DIE_AWARD, desc = "野外BOSS死亡发奖")
public class FieldBossDieCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		BossDieMsg req = BossDieMsg.parseFrom(packet.getBytes());
		
		FieldBossCfg cfg = FieldBossTemplateMgr.getFieldBossMap().get(req.getMonsterId());
		
		if(cfg == null)	return;
		
		MonsterInfo monsterCfg = MonsterInfoTemplateMgr.get(req.getMonsterId());
		if(monsterCfg == null) return;
		
		String emailTitle = LanguageSet.getResource("CenterServer.email.systemTitle");
		String emailContent;
		//第一击奖励
		if(req.getFirstDamagePlayer() > 0){
			emailContent = LanguageSet.getResource("CenterServer.fieldBoss.firstDamageAward");
			emailContent.replace("@bossName@", monsterCfg.getName());
			
			sendAward(req.getFirstDamagePlayer(), cfg.getFirstAwardItem(), cfg.getFirstAwardNum(), emailTitle, emailContent);
		}
		//最高伤害奖励
		if(req.getMostDamagePlayer() > 0){
			emailContent = LanguageSet.getResource("CenterServer.fieldBoss.mostDamageAward");
			emailContent.replace("@bossName@", monsterCfg.getName());
			
			sendAward(req.getMostDamagePlayer(), cfg.getDmgAwardItem(), cfg.getDmgAwardNum(), emailTitle, emailContent);
		}
		//破盾奖励
		if(req.getShieldKiller() > 0){
			emailContent = LanguageSet.getResource("CenterServer.fieldBoss.shieldAward");
			emailContent.replace("@bossName@", monsterCfg.getName());
			
			sendAward(req.getShieldKiller(), cfg.getShieldAwardItem(), cfg.getShieldAwardNum(), emailTitle, emailContent);
		}
		
		//以上奖励都没拿到时，只要对BOSS造成伤害就会有以下奖励
		emailContent = LanguageSet.getResource("CenterServer.fieldBoss.normalAward");
		emailContent.replace("@bossName", monsterCfg.getName());
		for(long playerId: req.getNormalPlayerList()){
			if(playerId <= 0) continue;
			
			//拿到其他奖励后不再重复拿造成任何伤害的保底奖励
			if(playerId == req.getFirstDamagePlayer() || playerId == req.getMostDamagePlayer() || playerId == req.getShieldKiller()){
				continue;
			}
			sendAward(playerId, cfg.getNormalAwardItem(), cfg.getNormalAwardNum(), emailTitle, emailContent);
		}
	}
	
	private void sendAward(long playerId, int itemTempId, int num, String title, String content){
		ItemTemplateInfo itemInfo = ItemManager.findItemTempInfo(itemTempId);
		if(itemInfo == null) return;
		
		EmailItemVo awardItem = new EmailItemVo(itemTempId, num, (itemInfo.getBind() == BindType.BIND? BindType.BIND: BindType.NOBIND));
		List<EmailItemVo> awardList = new ArrayList<>();
		awardList.add(awardItem);
		
		EmailManager.insertEmail(playerId, title, content, awardList);
	}

}
