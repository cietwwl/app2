package com.chuangyou.xianni.space.logic.collection;

import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionReqProto.SetSpaceCollectionReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 删除收藏
 * @author laofan
 *
 */
public class DelCollectionLogic extends AddCollectionLogic {

	@Override
	protected void doExe(GamePlayer player, SetSpaceCollectionReqMsg req) {
		// TODO Auto-generated method stub
		if(message.getIsCollection() == SpaceMessageInfo.NO_COLLECTION){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_COLLECTION,"MessageID"+req.getMessageId()+"已经是未收藏状态");		
			return;
		}
		message.setIsCollection(SpaceMessageInfo.NO_COLLECTION);
		
		doResultClient(player, req);
	}

	
}
