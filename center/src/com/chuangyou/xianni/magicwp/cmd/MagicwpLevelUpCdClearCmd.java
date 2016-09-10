package com.chuangyou.xianni.magicwp.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpLevelUpCdClearReqProto.MagicwpLevelUpCdClearReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpLevelUpCdClearRespProto.MagicwpLevelUpCdClearRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_LEVELUPCD_CLEAR, desc = "清除法宝升级CD")
public class MagicwpLevelUpCdClearCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpLevelUpCdClearReqMsg req = MagicwpLevelUpCdClearReqMsg.parseFrom(packet.getBytes());

		MagicwpInfo magicwpInfo = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());

		if (magicwpInfo == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnGet, packet.getCode());
			return;
		}
		// 不在冷却中，不需要清除
		if (magicwpInfo.getUpLevelCd() <= (new Date()).getTime()) {
			return;
		}

		MagicwpCfg magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(req.getMagicwpId());
		if (magicwpCfg.getIsSpecial() == 1) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Special_NotOpera, packet.getCode());
			return;
		}

		MagicwpLevelCfg magicwpLevel = MagicwpTemplateMgr.getLevelTemps().get(magicwpInfo.getMagicwpId() * 1000 + magicwpInfo.getLevel());
		// 扣钱
		if (player.getBasePlayer().getPlayerInfo().getCash() < magicwpLevel.getClearCdCash()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode());
			return;
		}
		if (!player.getBasePlayer().consumeCash(magicwpLevel.getClearCdCash(), ItemRemoveType.MAGICWP_CLEAR_CD))
			return;

		magicwpInfo.setUpLevelCd(0);
		player.getMagicwpInventory().updateMagicwpInfo(magicwpInfo);

		MagicwpLevelUpCdClearRespMsg.Builder msg = MagicwpLevelUpCdClearRespMsg.newBuilder();
		msg.setMagicwpId(magicwpInfo.getMagicwpId());
		msg.setUpLevelCd(0);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_LEVELUPCD_CLEAR, msg);
		player.sendPbMessage(p);
	}

}
