package com.chuangyou.xianni.campaign.cmd;

import com.chuangyou.common.protobuf.pb.state.AddStateBuffProto.AddStateBuffMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.campaign.CampaignTempMgr;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignType;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;


@Cmd(code=Protocol.S_ADD_STATE_BUFF,desc="添加BUFF")
public class AddStateCampaignBuffCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		AddStateBuffMsg msg = AddStateBuffMsg.parseFrom(packet.getBytes());
		
		Field curField = FieldMgr.getIns().getField(army.getFieldId());
		if (curField == null ){
			Log.error("不在副本中。不允许加副本BUFF : " + army.getPlayerId()+"msg:"+msg.toString());
			return;
		}
		
		CampaignTemplateInfo temp = CampaignTempMgr.get(curField.getCampaignId());
		if (temp == null) {
			Log.error("获取不到副本模板playerId: " + army.getPlayerId() + "campaignId:" + curField.getCampaignId());
			return;
		}
		
		if(temp.getType()!=CampaignType.STATE){
			Log.error("非进界副本playerId: " + army.getPlayerId() + "campaignId:" + curField.getCampaignId());
			return;
		}
		SkillBufferTemplateInfo bufferTemp = BattleTempMgr.getBufferInfo(msg.getBuffId());
		if (bufferTemp != null) {
			Buffer buffer = BufferFactory.createBuffer(army.getPlayer(), army.getPlayer(), bufferTemp);
			army.getPlayer().addCampaignBuff(buffer);
		}else{
			Log.error("获取不到BUFF模板数据： playerId: " + army.getPlayerId() + "buffId:" + msg.getBuffId());
			return;
		}
		
	}

}
