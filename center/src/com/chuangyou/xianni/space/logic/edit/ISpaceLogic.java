package com.chuangyou.xianni.space.logic.edit;

import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoReqProto.EditSpaceInfoReqMsg;
import com.chuangyou.xianni.player.GamePlayer;

public interface ISpaceLogic {
	public void doProcess(GamePlayer player,EditSpaceInfoReqMsg req);
}
