package com.chuangyou.xianni.magicwp.cmd;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpDanUseReqProto.MagicwpDanUseReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpDanUseRespProto.MagicwpDanUseRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.magicwp.manager.MagicwpManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_DAN_USE, desc = "使用属性丹")
public class MagicwpDanUseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpDanUseReqMsg req = MagicwpDanUseReqMsg.parseFrom(packet.getBytes());
		MagicwpAtt magicwpAtt = player.getMagicwpInventory().getMagicwpAtt();
		int maxUse = SystemConfigTemplateMgr.getIntValue("magicwp.dan.maxUse");
		if(magicwpAtt.getUseDanNum() + req.getUseNum() > maxUse){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UseDan_max, packet.getCode());
			return;
		}
		
		int itemId = SystemConfigTemplateMgr.getIntValue("magicwp.dan.prop.id");
		//扣物品
		if(player.getBagInventory().getPlayerBagItemCount(itemId) < req.getUseNum()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, packet.getCode());
			return;
		}
		//扣道具
		if(!player.getBagInventory().removeItemFromPlayerBag(itemId, req.getUseNum(), BindType.ALL)) return;
		
		magicwpAtt.setUseDanNum(magicwpAtt.getUseDanNum() + req.getUseNum());
		player.getMagicwpInventory().updateMagicwpAtt(magicwpAtt);
		
		MagicwpDanUseRespMsg.Builder msg = MagicwpDanUseRespMsg.newBuilder();
		msg.setTotalUseNum(magicwpAtt.getUseDanNum());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_DAN_USE, msg);
		player.sendPbMessage(p);
		
		//法宝总属性改变
//		MagicwpManager.changeMagicwpAtt(roleId);
		//影响人物属性变更
		player.getArmyInventory().getArmy().getHero().addMagicwp(MagicwpManager.computeMagicwpAtt(player));
		player.getArmyInventory().updateProperty();
	}

}
