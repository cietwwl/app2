package com.chuangyou.xianni.space.logic.collection;

import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionReqProto.SetSpaceCollectionReqMsg;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 收藏相关逻辑
 * @author laofan
 *
 */
public interface ISpaceCollectionLogic {
	public void doProcess(GamePlayer player,SpaceInfo info,int op,SetSpaceCollectionReqMsg req);
}
