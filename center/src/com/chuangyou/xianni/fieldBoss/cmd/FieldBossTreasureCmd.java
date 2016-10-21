package com.chuangyou.xianni.fieldBoss.cmd;

import com.chuangyou.common.protobuf.pb.boss.WorldBossTreasureListProto.WorldBossTreasureListMsg;
import com.chuangyou.common.protobuf.pb.boss.WorldBossTreasureProto.WorldBossTreasureMsg;
import com.chuangyou.common.util.LanguageSet;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_WORLD_BOSS_TREASURE_AWARD, desc = "世界BOSS夺宝奖励发放")
public class FieldBossTreasureCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		WorldBossTreasureListMsg req = WorldBossTreasureListMsg.parseFrom(packet.getBytes());
		
		int awardItem = SystemConfigTemplateMgr.getIntValue("fieldBoss.world.treasure.awardItem");
		ItemTemplateInfo itemInfo = ItemManager.findItemTempInfo(awardItem);
		if(itemInfo == null){
			Log.error("世界BOSS夺宝活动奖励tb_z_system配置错误：fieldBoss.world.treasure.awardItem : " + awardItem + "在物品表中找不到");
			return;
		}
		String emailTitle = LanguageSet.getResource("CenterServer.email.systemTitle");
		String emailContent = LanguageSet.getResource("CenterServer.fieldBoss.worldBossTreasureAward");
		for(WorldBossTreasureMsg info: req.getInfoList()){
			EmailItemVo emailItem = new EmailItemVo(awardItem, info.getTreasureCount(), itemInfo.getBind() == BindType.BIND? BindType.BIND: BindType.NOBIND);
			EmailManager.insertEmail(info.getPlayerId(), emailTitle, emailContent, emailItem);
		}
	}

}
