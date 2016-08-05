package com.chuangyou.xianni.space.logic.edit;

import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoReqProto.EditSpaceInfoReqMsg;
import com.chuangyou.xianni.player.GamePlayer;

public class EditSignatureLogic extends EditFaceLogic {



	@Override
	public void doProcess(GamePlayer player, EditSpaceInfoReqMsg req) {
		// TODO Auto-generated method stub
		player.getSpaceInventory().getSpaceInfo().setSignature(req.getValue());
		this.sendMsg(player, req);
	}
	
	
	

}
