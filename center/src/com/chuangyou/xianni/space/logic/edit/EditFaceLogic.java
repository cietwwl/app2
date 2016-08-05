package com.chuangyou.xianni.space.logic.edit;

import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoReqProto.EditSpaceInfoReqMsg;
import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoRespProto.EditSpaceInfoRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;


/**
 * 修改头像
 * @author laofan
 *
 */
public class EditFaceLogic implements ISpaceLogic {

	protected void sendMsg(GamePlayer player, EditSpaceInfoReqMsg req){
		EditSpaceInfoRespMsg.Builder msg = EditSpaceInfoRespMsg.newBuilder();
		msg.setOp(req.getOp());
		if(req.hasValue()){
			msg.setValue(req.getValue());
		}
		if(req.hasChangeValue()){
			msg.setChangeValue(req.getChangeValue());
		}
		msg.setPlayerId(player.getPlayerId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_ENDIT_INFO, msg);
		player.sendPbMessage(pkg);
	}

	@Override
	public void doProcess(GamePlayer player, EditSpaceInfoReqMsg req) {
		// TODO Auto-generated method stub
		player.getSpaceInventory().getSpaceInfo().setFace(req.getValue());
		sendMsg(player,req);
	}

}
