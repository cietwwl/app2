package com.chuangyou.xianni.space.logic.collection;

import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionReqProto.SetSpaceCollectionReqMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.space.SpaceInventory;

/**
 * 设置收藏上限
 * @author laofan
 *
 */
public class SetCollectionNumLogic extends AddCollectionLogic{

	@Override
	public void doProcess(GamePlayer player, SpaceInfo info, int op, SetSpaceCollectionReqMsg req) {
		// TODO Auto-generated method stub
		this.spaceInfo = info;
		int newNum = info.getMaxCollection() + req.getAddCollectionNum();
		if(newNum>SpaceInventory.MAX_COLLECTION){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_COLLECTION,"收藏总数超过最大上限");					
			return;
		}
		int needMoney = newNum * SystemConfigTemplateMgr.getSpaceCollectionPrice();
		if(player.getBasePlayer().consumeCash(needMoney)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Cash_UnEnough, Protocol.C_REQ_SPACE_SET_COLLECTION,"元宝不够或扣钱失败");					
			return;
		}else{
			info.setMaxCollection(newNum);
		}
		
		this.doResultClient(player, req);
	}

}
