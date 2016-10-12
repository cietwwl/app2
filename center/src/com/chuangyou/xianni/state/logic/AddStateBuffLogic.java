package com.chuangyou.xianni.state.logic;

import com.chuangyou.common.protobuf.pb.state.AddStateBuffProto.AddStateBuffMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.state.ConsumSystemConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.state.template.StateTemplateMgr;

public class AddStateBuffLogic extends BaseStateLogic{
	
	public void addBuff(GamePlayer player,int id){
		ConsumSystemConfig config = StateTemplateMgr.getConsums().get(id);
		if(config == null){
			Log.error("ConsumSystemConfig:配置表错误:"+id);
			return;
		}
		
		//消耗
		if(player.getBagInventory().removeItem(config.getExpendItem(), config.getExpendNum(), ItemRemoveType.STATE) == false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR,Protocol.C_REQ_STATE_OP,"物品不够：config:"+config.toString());		
			return;
		}
		
		//加BUFF
		int buffId = config.getParam1();
		if(buffId>0){
			AddStateBuffMsg.Builder msg = AddStateBuffMsg.newBuilder();
			msg.setBuffId(buffId);
			player.sendPbMessage(MessageUtil.buildMessage(Protocol.S_ADD_STATE_BUFF,msg));
		}
		
		this.sendResult(player, 2, id);
	}
}
