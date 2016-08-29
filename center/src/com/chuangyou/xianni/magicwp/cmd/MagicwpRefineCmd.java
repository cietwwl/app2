package com.chuangyou.xianni.magicwp.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineReqProto.MagicwpRefineReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpRefineRespProto.MagicwpRefineRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpGradeCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpRefineCfg;
import com.chuangyou.xianni.magicwp.manager.MagicwpRefineManager;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_REFINE, desc = "法宝洗炼")
public class MagicwpRefineCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpRefineReqMsg req = MagicwpRefineReqMsg.parseFrom(packet.getBytes());
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
		
		if(magicwp.getGrade() < 1){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Refine_Grade_UnOpen, packet.getCode());
			return;
		}
		
		int useItem = SystemConfigTemplateMgr.getIntValue("magicwp.refine.prop.id");
		int useItemNum = 1 + req.getLockAttTypeCount();
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(useItem) < useItemNum){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(useItem, useItemNum, ItemRemoveType.MAGICWP_REFINE)) return;
		
		MagicwpGradeCfg gradeCfg = MagicwpTemplateMgr.getGradeTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getGrade());
		Map<Integer, Integer> curAttMap = MagicwpRefineManager.getRefineAttMap(magicwp.getRefineAtts());
		MagicwpRefineCfg refineCfg = MagicwpTemplateMgr.getRefineTemps().get(magicwp.getMagicwpId());
		
		Map<Integer, Integer> resultAttMap = MagicwpRefineManager.getRefineResultMap(gradeCfg, curAttMap, refineCfg, req.getLockAttTypeList());
		
		magicwp.setUnSaveAtt((byte) 1);
		magicwp.setTempAtt(MagicwpRefineManager.getRefineAttStr(resultAttMap));
		player.getMagicwpInventory().updateMagicwpInfo(magicwp);
		
		MagicwpRefineRespMsg.Builder msg = MagicwpRefineRespMsg.newBuilder();
		msg.setMagicwpId(magicwp.getMagicwpId());
		msg.setHasUnSaveAtt(magicwp.getUnSaveAtt());
		for(int attType:resultAttMap.keySet()){
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			bean.setAttType(attType);
			bean.setAttValue(resultAttMap.get(attType));
			msg.addAtts(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_REFINE, msg);
		player.sendPbMessage(p);
	}

}
