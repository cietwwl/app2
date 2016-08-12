package com.chuangyou.xianni.space.logic.collection;

import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionReqProto.SetSpaceCollectionReqMsg;
import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionRespProto.SetSpaceCollectionRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.entity.space.SpaceMessageInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 添加收藏
 * @author laofan
 *
 */
public class AddCollectionLogic implements ISpaceCollectionLogic {

	protected SpaceMessageInfo message;
	
	protected SpaceInfo spaceInfo;

	@Override
	public void doProcess(GamePlayer player, SpaceInfo info, int op, SetSpaceCollectionReqMsg req) {
		// TODO Auto-generated method stub
		this.spaceInfo = info;
		message = player.getSpaceInventory().getMessages().get(req.getMessageId());
		if(message==null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_COLLECTION,"无效的MEssageID"+req.getMessageId());		
			return;
		}
		doExe(player,req);
	}
	
	/**
	 * 执行添加收藏操作
	 * @param player
	 * @param req
	 */
	protected void doExe(GamePlayer player,SetSpaceCollectionReqMsg req){
		if(message.getIsCollection() == SpaceMessageInfo.COLLECTIONED){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_COLLECTION,"MessageID"+req.getMessageId()+"已经是收藏状态");		
			return;
		}
		if(spaceInfo.getCurCollection() == spaceInfo.getMaxCollection()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_COLLECTION,"收藏数量已达上限");
			return;
		}
		message.setIsCollection(SpaceMessageInfo.COLLECTIONED);
		doResultClient(player,req);
	}
	
	
	 /**
	  * 执行回复客户端消息
	  * @param player
	  * @param req
	  */
	protected void doResultClient(GamePlayer player,SetSpaceCollectionReqMsg req){
		SetSpaceCollectionRespMsg.Builder resp = SetSpaceCollectionRespMsg.newBuilder();
		resp.setPlayerId(player.getPlayerId());
		resp.setOp(req.getOp());
		resp.setMessageId(req.getMessageId());
		resp.setCurCollection(player.getSpaceInventory().calcCurCollection());
		resp.setMaxCollection(spaceInfo.getMaxCollection());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SPACE_SET_COLLECTION,resp);
		player.sendPbMessage(pkg);
	}

}
