package com.chuangyou.xianni.space.logic.collection;

import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionReqProto.SetSpaceCollectionReqMsg;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;

/**
 *  4:取消全部收藏
 * @author laofan
 *
 */
public class DelAllCollectionLogic extends AddCollectionLogic {

	@Override
	public void doProcess(GamePlayer player, SpaceInfo info, int op, SetSpaceCollectionReqMsg req) {
		// TODO Auto-generated method stub
		this.spaceInfo = info;
		player.getSpaceInventory().delAllCollection();
		this.doResultClient(player, req);
	}

}
