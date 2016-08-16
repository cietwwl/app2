package com.chuangyou.xianni.space.logic.op;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 鸡蛋
 * @author laofan
 *
 */
public class EggsOpLogic extends LikeOpLogic {

	@Override
	public void doProcess(GamePlayer player, GamePlayer reqPlayer, SpaceInfo info, int op) {
		// TODO Auto-generated method stub
		if(reqPlayer.getSpaceInventory().getSpaceInfo().getPopularity()<=0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ACTION,"不能再砸啦");
			return;
		}
		int count = player.getBagInventory().getPlayerBagItemCount(SystemConfigTemplateMgr.getSpaceEggs());		
		if(count<=0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ACTION,"没有鸡蛋啦");
			return;
		}
		player.getBagInventory().removeItem(SystemConfigTemplateMgr.getSpaceEggs(), 1, ItemRemoveType.SPACE);
		reqPlayer.getSpaceInventory().addEggs();
		doAll(player, reqPlayer, info, 0, op);
	}
	
}
