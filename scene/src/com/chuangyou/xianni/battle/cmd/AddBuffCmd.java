package com.chuangyou.xianni.battle.cmd;

import com.chuangyou.common.protobuf.pb.battle.AddBuffProto.AddBuffMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;


@Cmd(code=Protocol.S_ADD_BUFF,desc="添加BUFF")
public class AddBuffCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		AddBuffMsg msg = AddBuffMsg.parseFrom(packet.getBytes());
		
		if(msg.getIsCampaignBuff() > 0){
			//如果是副本buff判断在不在副本中
			Field curField = FieldMgr.getIns().getField(army.getFieldId());
			if (curField == null || curField.getCampaignId() <= 0){
				Log.error("不在副本中。不允许加副本BUFF : " + army.getPlayerId()+"msg:"+msg.toString());
				return;
			}
			
			Campaign campaign = CampaignMgr.getCampagin(curField.getCampaignId());
			if(campaign == null){
				Log.error("不在副本中。不允许加副本BUFF : " + army.getPlayerId()+"msg:"+msg.toString());
				return;
			}
			
			if(msg.getCampaignType() > 0){
				//如果指定了副本类型，判断当前所在副本是不是指定类型
				if(campaign.getTemp() == null || campaign.getTemp().getType() != msg.getCampaignType()){
					Log.error("非进界副本playerId: " + army.getPlayerId() + "campaignMapKey:" + curField.getMapKey());
					return;
				}
			}
		}
		
		SkillBufferTemplateInfo bufferTemp = BattleTempMgr.getBufferInfo(msg.getBuffTempId());
		if (bufferTemp != null) {
			//添加buff
			Buffer buffer = BufferFactory.createBuffer(army.getPlayer(), army.getPlayer(), bufferTemp);
			if(msg.getIsCampaignBuff() > 0){
				army.getPlayer().addCampaignBuff(buffer);
			}else{
				army.getPlayer().addBuffer(buffer);
			}
		}else{
			Log.error("获取不到BUFF模板数据： playerId: " + army.getPlayerId() + "buffId:" + msg.getBuffTempId() );
			return;
		}
		
	}

}
