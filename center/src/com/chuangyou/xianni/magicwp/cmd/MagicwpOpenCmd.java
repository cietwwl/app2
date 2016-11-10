package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpOpenReqProto.MagicwpOpenReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_OPEN, desc = "法宝激活")
public class MagicwpOpenCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpOpenReqMsg req = MagicwpOpenReqMsg.parseFrom(packet.getBytes());
		MagicwpCfg magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(req.getMagicwpId());
		
		if(magicwpCfg == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnExist, packet.getCode());
			return;
		}
		
		MagicwpInfo magicwp = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());
		
		if(magicwp != null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_HasGet, packet.getCode());
			return;
		}
		
		if(magicwpCfg.getIsSpecial() == 1){
			//改为特殊法宝使用道具激活
//			throw new MXY2Exception(ErrorCode.Magicwp_Special_NotOpera, "特殊法宝不可激活");
			//消耗道具
			if(player.getBagInventory().getPlayerBagItemCount(magicwpCfg.getItemId()) < 1){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
				return;
			}
			//扣道具
			if(!player.getBagInventory().removeItemFromPlayerBag(magicwpCfg.getItemId(), 1, ItemRemoveType.MAGICWP_ACTIVATE)) return;
		}else{
		
			//激活条件
			magicwpCfg.getNeedDay();
			
			if(player.getBasePlayer().getPlayerInfo().getVipLevel() < magicwpCfg.getNeedVip()){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Vip_UnEnough, packet.getCode());
				return;
			}
		}
		
		player.getMagicwpInventory().activeMagicwp(req.getMagicwpId());
		
	}

}
