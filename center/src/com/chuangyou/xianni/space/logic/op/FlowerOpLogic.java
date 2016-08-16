package com.chuangyou.xianni.space.logic.op;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

/**
 *  送花
 * @author laofan
 *
 */
public class FlowerOpLogic extends LikeOpLogic {

	@Override
	public void doProcess(GamePlayer player, GamePlayer reqPlayer, SpaceInfo info, int op) {
		// TODO Auto-generated method stub
		
		int count = player.getBagInventory().getPlayerBagItemCount(SystemConfigTemplateMgr.getSpaceFlower());		
		if(count<=0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ACTION,"没有鲜花啦");
			return;
		}
		player.getBagInventory().removeItem(SystemConfigTemplateMgr.getSpaceFlower(), 1, ItemRemoveType.SPACE);
		reqPlayer.getSpaceInventory().addFlower();
		doAll(player, reqPlayer, info, 0, op);
	}
}
