package com.chuangyou.xianni.space.cmd;

import com.chuangyou.common.protobuf.pb.space.SetSpaceGiftReqProto.SetSpaceGiftReqMsg;
import com.chuangyou.common.protobuf.pb.space.SetSpaceGiftRespProto.SetSpaceGiftRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_SPACE_SET_GIFT,desc="设置空间礼物")
public class SetSpaceGiftCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SetSpaceGiftReqMsg msg = SetSpaceGiftReqMsg.parseFrom(packet.getBytes());
		int addGiftNum = msg.getAddGiftNum();
		int needMoney = SystemConfigTemplateMgr.getSpaceGiftPrice() * addGiftNum;
		if(player.getBasePlayer().getPlayerInfo().getMoney()<needMoney){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_GIFT,"钱不够");
			return;
		}
		
		if(player.getBasePlayer().consumeMoney(needMoney)){		
			player.getSpaceInventory().addGift(addGiftNum);		
			SetSpaceGiftRespMsg.Builder resp = SetSpaceGiftRespMsg.newBuilder();
			resp.setPlayerID(player.getPlayerId());
			resp.setTotalGiftNum(player.getSpaceInventory().getSpaceInfo().getGift());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SPACE_SET_GIFT,resp);
			player.sendPbMessage(pkg);
		}else{
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_GIFT,"消耗金币失败");
		}
		
	}

}
