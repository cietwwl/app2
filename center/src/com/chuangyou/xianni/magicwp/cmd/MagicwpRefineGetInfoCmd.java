package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineGetInfoReqProto.MagicwpRefineGetInfoReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineGetInfoRespProto.MagicwpRefineGetInfoRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_REFINE_GETINFO, desc = "获取法宝洗炼信息")
public class MagicwpRefineGetInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpRefineGetInfoReqMsg req = MagicwpRefineGetInfoReqMsg.parseFrom(packet.getBytes());
		MagicwpInfo magicwp = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());
		
		if(magicwp == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnGet, packet.getCode());
			return;
		}
		
//		if(magicwp.getGrade() < 1){
//			throw new MXY2Exception(ErrorCode.Magicwp_Refine_Grade_UnOpen, "法宝洗炼未激活");
//		}
		
		MagicwpRefineGetInfoRespMsg.Builder msg = MagicwpRefineGetInfoRespMsg.newBuilder();
		msg.setMagicwpId(magicwp.getMagicwpId());
		msg.setGrade(magicwp.getGrade());
		
		String[] attStrs = magicwp.getRefineAtts().split("_");
		for(String att:attStrs){
			if(att.equals("")) continue;
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			int attNum = Integer.parseInt(att);
			bean.setAttType((int)attNum/1000000);
			bean.setAttValue(attNum%1000000);
			msg.addAttList(bean);
		}
		msg.setHasUnSaveAtt(magicwp.getUnSaveAtt());
		for(String att:magicwp.getTempAtt().split("_")){
			if(att.equals("")) continue;
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			int attNum = Integer.parseInt(att);
			bean.setAttType((int)attNum/1000000);
			bean.setAttValue(attNum%1000000);
			msg.addTmpList(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_REFINE_GETINFO, msg);
		player.sendPbMessage(p);
	}

}
