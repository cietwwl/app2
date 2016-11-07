package com.chuangyou.xianni.magicwp.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanEquipReqProto.MagicwpBanEquipReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanEquipRespProto.MagicwpBanEquipRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_BAN_EQUIP, desc = "禁制装备")
public class MagicwpBanEquipCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpBanEquipReqMsg req = MagicwpBanEquipReqMsg.parseFrom(packet.getBytes());
		
		MagicwpBanInfo curBan = player.getMagicwpInventory().getBan(req.getBanId());
		if(curBan == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_UnGet, packet.getCode());
			return;
		}
		if(curBan.getPosition() == req.getPosition()) return;
		if (req.getPosition() == 0) {
			curBan.setPosition((byte) 0);
			player.getMagicwpInventory().updateBan(curBan);

			MagicwpBanEquipRespMsg.Builder msg = MagicwpBanEquipRespMsg.newBuilder();
			msg.setBanId(curBan.getBanId());
			msg.setPosition(curBan.getPosition());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_EQUIP, msg);
			player.sendPbMessage(p);
			
			player.getMagicwpInventory().writeMagicwpMsg2Scene();
			return;
		}

		Map<Integer, MagicwpInfo> roleMagicwpMap = player.getMagicwpInventory().getMagicwpInfoMap();

		// 判断栏位是否已开启
		boolean posOpen = false;
		MagicwpCfg magicwpCfg = null;
		for (MagicwpInfo magicwp : roleMagicwpMap.values()) {
			magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(magicwp.getMagicwpId());
			if (magicwpCfg.getIsSpecial() != 0)
				break;

			if (magicwp.getLevel() > 0 && magicwpCfg.getActiveCheck() == req.getPosition()) {
				posOpen = true;
				break;
			}
		}
		if (posOpen == false) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_Pos_UnGet, packet.getCode());
			return;
		}

		byte curPos = curBan.getPosition();
		MagicwpBanInfo targetBan = null;
		// 判断目标位置是否已经装备禁制
		Map<Integer, MagicwpBanInfo> roleBanMap = player.getMagicwpInventory().getBanInfoMap();

		for (MagicwpBanInfo ban : roleBanMap.values()) {
			if (ban.getPosition() == req.getPosition()) {
				targetBan = ban;
				ban.setPosition(curPos);
				break;
			}
		}
		curBan.setPosition((byte)req.getPosition());
		player.getMagicwpInventory().updateBan(curBan);

		MagicwpBanEquipRespMsg.Builder msg = MagicwpBanEquipRespMsg.newBuilder();
		msg.setBanId(curBan.getBanId());
		msg.setPosition(curBan.getPosition());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_EQUIP, msg);
		player.sendPbMessage(p);

		if (targetBan != null) {
			player.getMagicwpInventory().updateBan(targetBan);

			MagicwpBanEquipRespMsg.Builder targetMsg = MagicwpBanEquipRespMsg.newBuilder();
			targetMsg.setBanId(targetBan.getBanId());
			targetMsg.setPosition(targetBan.getPosition());
			PBMessage pTarget = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_EQUIP, targetMsg);
			player.sendPbMessage(pTarget);
		}
		player.getMagicwpInventory().writeMagicwpMsg2Scene();
	}

}
