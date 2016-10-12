package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineSaveReqProto.MagicwpRefineSaveReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineSaveRespProto.MagicwpRefineSaveRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_REFINE_SAVE, desc = "保存法宝洗炼属性")
public class MagicwpRefineSaveCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpRefineSaveReqMsg req = MagicwpRefineSaveReqMsg.parseFrom(packet.getBytes());
		
		MagicwpCfg magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(req.getMagicwpId());
		if(magicwpCfg.getIsSpecial() == 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Special_NotOpera, packet.getCode());
			return;
		}
		
		MagicwpInfo magicwp = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());
		
		if(magicwp == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnGet, packet.getCode());
			return;
		}
		if(magicwp.getUnSaveAtt() == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Refine_UnRefine, packet.getCode());
			return;
		}
		
		magicwp.setUnSaveAtt((byte) 0);
		magicwp.setRefineAtts(magicwp.getTempAtt());
		magicwp.setTempAtt("");
		player.getMagicwpInventory().updateMagicwpInfo(magicwp);
		
		MagicwpRefineSaveRespMsg.Builder msg = MagicwpRefineSaveRespMsg.newBuilder();
		msg.setMagicwpId(magicwp.getMagicwpId());
		msg.setHasUnSaveAtt(magicwp.getUnSaveAtt());
		String[] attStrs = magicwp.getRefineAtts().split("_");
		for(String att:attStrs){
			if(att.equals("")) continue;
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			int attNum = Integer.parseInt(att);
			bean.setAttType((int)attNum/1000000);
			bean.setAttValue(attNum%1000000);
			msg.addAtts(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_REFINE_SAVE, msg);
		player.sendPbMessage(p);
		
		//法宝总属性改变
//		MagicwpManager.changeMagicwpAtt(roleId);
		//影响人物属性变更
		player.getMagicwpInventory().updataProperty();
	}

}
