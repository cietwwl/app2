package com.chuangyou.xianni.magicwp.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpLevelUpReqProto.MagicwpLevelUpReqMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpLevelUpRespProto.MagicwpLevelUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.magicwp.MagicwpCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_LEVEL_UP, desc = "法宝升级")
public class MagicwpLevelUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpLevelUpReqMsg req = MagicwpLevelUpReqMsg.parseFrom(packet.getBytes());
		MagicwpCfg magicwpCfg = MagicwpTemplateMgr.getMagicwpTemps().get(req.getMagicwpId());
		if (magicwpCfg.getIsSpecial() == 1) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Special_NotOpera, packet.getCode());
			return;
		}

		MagicwpInfo magicwpInfo = player.getMagicwpInventory().getMagicwpInfo(req.getMagicwpId());
		if (magicwpInfo == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_UnGet, packet.getCode());
			return;
		}

		MagicwpLevelCfg targetCfg = MagicwpTemplateMgr.getLevelTemps().get(req.getMagicwpId() * 1000 + magicwpInfo.getLevel() + 1);

		if (targetCfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Level_Max, packet.getCode());
			return;
		}

		if (magicwpInfo.getUpLevelCd() > (new Date()).getTime()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CD_IS_NOT_ENOUGTH, packet.getCode());
			return;
		}

		MagicwpLevelCfg levelCfg = MagicwpTemplateMgr.getLevelTemps().get(req.getMagicwpId() * 1000 + magicwpInfo.getLevel());

		// 扣钱
		if (player.getBasePlayer().getPlayerInfo().getMoney() < levelCfg.getNeedGold()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Money_UnEnough, packet.getCode());
			return;
		}
		if (!player.getBasePlayer().consumeMoney(levelCfg.getNeedGold(), ItemRemoveType.UP_MAGICWP))
			return;

		magicwpInfo.setLevel(magicwpInfo.getLevel() + 1);
		magicwpInfo.setUpLevelCd((new Date()).getTime() + levelCfg.getUpLevCd() * 1000);
		MagicwpLevelCfg nextLevel = MagicwpTemplateMgr.getLevelTemps().get(req.getMagicwpId() * 1000 + magicwpInfo.getLevel() + 1);
		if (nextLevel == null) {
			magicwpInfo.setUpLevelCd(0);
		}
		player.getMagicwpInventory().updateMagicwpInfo(magicwpInfo);

		MagicwpLevelUpRespMsg.Builder msg = MagicwpLevelUpRespMsg.newBuilder();
		msg.setMagicwpId(magicwpInfo.getMagicwpId());
		msg.setLevel(magicwpInfo.getLevel());
		// long leftCd = magicwpInfo.getUpLevelCd() - (new Date()).getTime();
		// if(leftCd < 0){
		// leftCd = 0;
		// }
		msg.setUpLevelCd(magicwpInfo.getUpLevelCd());
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_LEVEL_UP, msg);
		player.sendPbMessage(p);

		// 法宝总属性改变
		// MagicwpManager.changeMagicwpAtt(roleId);

		// 影响人物属性变更
		player.getMagicwpInventory().updataProperty();	
		player.notifyListeners(new ObjectEvent(this, magicwpInfo, EventNameType.MAGICWP));
	}

}
