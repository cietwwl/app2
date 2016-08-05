package com.chuangyou.xianni.space.logic.edit;

import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoReqProto.EditSpaceInfoReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

import io.netty.util.internal.StringUtil;

public class EditBirthdayLogic extends EditFaceLogic {

	

	@Override
	public void doProcess(GamePlayer player, EditSpaceInfoReqMsg req) {
		// TODO Auto-generated method stub
		if(StringUtil.isNullOrEmpty(player.getSpaceInventory().getSpaceInfo().getBirthday())){
			player.getSpaceInventory().getSpaceInfo().setBirthday(req.getValue());
			sendMsg(player, req);
		}else{
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_ENDIT_INFO,"生日已经编辑过。不可再编");		
			return;
		}
	}

	
	
}
