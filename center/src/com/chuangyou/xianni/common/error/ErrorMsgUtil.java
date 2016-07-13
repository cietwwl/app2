package com.chuangyou.xianni.common.error;

import com.chuangyou.common.protobuf.pb.ErrorRespProto.ErrorRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class ErrorMsgUtil {
	/**
	 * 向客户端发送错误码
	 * @param player
	 * @param errorCode:错误码
	 * @param protocolNum：协议号
	 */
	public static void sendErrorMsg(GamePlayer player,int errorCode,short protocolNum){
		if(player ==null)return;
		ErrorRespMsg.Builder resp = ErrorRespMsg.newBuilder();
		resp.setErrorCode(errorCode);
		resp.setProtocolNum(protocolNum);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_ERROR, resp);
		player.sendPbMessage(pkg);
	}
	
	/**
	 * 向客户端发送错误码
	 * @param player
	 * @param errorCode
	 * @param protocolNum
	 * @param desc
	 */
	public static void sendErrorMsg(GamePlayer player,int errorCode,short protocolNum,String desc){
		Log.error("playerId:"+player.getPlayerId()+"errorCode:"+errorCode+"protocolNum:"+protocolNum+"DESC:"+desc);
		sendErrorMsg(player,errorCode,protocolNum);
	}
	
	
	
	/**
	 * 向客户端发送错误码
	 * @param player
	 * @param errorCode
	 * @param protocolNum
	 * @param desc
	 */
	public static void sendErrorMsg(long playerId,int errorCode,short protocolNum,String desc){
		Log.error("playerId:"+playerId+"errorCode:"+errorCode+"protocolNum:"+protocolNum+"DESC:"+desc);
		sendErrorMsg(WorldMgr.getPlayer(playerId),errorCode,protocolNum);
	}
}
