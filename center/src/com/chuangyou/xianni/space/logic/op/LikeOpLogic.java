package com.chuangyou.xianni.space.logic.op;

import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.space.OpSpaceRespProto.OpSpaceRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.space.SpaceActionLogInfo;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.space.script.ISpaceScript;

/**
 * 点赞
 * 
 * @author laofan
 *
 */
public class LikeOpLogic implements ISpaceOpLogic {

	@Override
	public void doProcess(GamePlayer player, GamePlayer reqPlayer, SpaceInfo info, int op) {
		// TODO Auto-generated method stub
		
		final String spaceScript = "spaceScript";
		
		List<SpaceActionLogInfo> list = reqPlayer.getSpaceInventory().getActions();
		Date now = new Date();
		for (SpaceActionLogInfo spaceActionLogInfo : list) {
			if(spaceActionLogInfo.getAction() == 1 && spaceActionLogInfo.getSendPlayerId() == player.getPlayerId()){
				if(spaceActionLogInfo.getCreateTime().getDate() == now.getDate() && 
						spaceActionLogInfo.getCreateTime().getMonth()==now.getMonth()){
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.LIKE_ONLY_ONE, Protocol.C_REQ_SPACE_ACTION,"每天只能点赞一次");
					return;
				}
			}
		}
		
		reqPlayer.getSpaceInventory().addLikes();
		int items= 0;
		if(reqPlayer.getSpaceInventory().consumeGift()){
			ISpaceScript script = (ISpaceScript) ScriptManager.getScriptById(spaceScript);
			if(script == null){
				Log.error("空间随机点赞礼物的脚本缺失");
			}else{
				items = script.getSpaceLikeGift(player.getPlayerId(), info.getGift());
				if(items!=0){
					player.getBagInventory().addItemInBagOrEmail(items, 1, ItemAddType.SPACE_ADD,false);
				}
			}		
		}
		
		
		
		doAll(player, reqPlayer, info, items, op);
	}

	/**
	 * 做所有的回复操作
	 * 
	 * @param player
	 * @param req
	 * @param reqPlayer
	 * @param info
	 * @param items
	 */
	protected void doAll(GamePlayer player, GamePlayer reqPlayer, SpaceInfo info, int items, int op) {
		doLog(player, reqPlayer, op);
		doNotify(reqPlayer, info);
		sendResult(player, reqPlayer, op, items);
	}

	/**
	 * 结果回复
	 * 
	 * @param player
	 * @param req
	 * @param items
	 */
	protected void sendResult(GamePlayer player, GamePlayer reqPlayer, int op, int items) {
		OpSpaceRespMsg.Builder resp = OpSpaceRespMsg.newBuilder();
		resp.setOp(op);
		resp.setPlayerId(reqPlayer.getPlayerId());
		resp.setAwardItem(items);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SPACE_ACTION, resp);
		player.sendPbMessage(pkg);
	}

	/**
	 * 加日志
	 * 
	 * @param player
	 * @param req
	 * @param reqPlayer
	 */
	protected void doLog(GamePlayer player, GamePlayer reqPlayer, int op) {
		SpaceActionLogInfo log = new SpaceActionLogInfo();
		log.setSendPlayerId(player.getPlayerId());
		log.setReceivePlayerId(reqPlayer.getPlayerId());
		log.setCreateTime(new Date());
		log.setAction(op);
		reqPlayer.getSpaceInventory().addAction(log);
	}

	/**
	 * 实时通知空间主人
	 * 
	 * @param reqPlayer
	 * @param info
	 */
	protected void doNotify(GamePlayer reqPlayer, SpaceInfo info) {
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFY_SPACE_CHANGE, info.getChangeAttMsg());
		reqPlayer.sendPbMessage(pkg);
	}

}
