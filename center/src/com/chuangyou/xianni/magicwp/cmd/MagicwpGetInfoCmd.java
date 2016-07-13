package com.chuangyou.xianni.magicwp.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetInfoRespProto.MagicwpGetInfoRespMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpInfoBeanProto.MagicwpInfoBeanMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_GETINFO, desc = "获取法宝信息")
public class MagicwpGetInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		MagicwpAtt magicwpAtt = player.getMagicwpInventory().getMagicwpAtt();
		Map<Integer, MagicwpInfo> roleMagicwpInfo = player.getMagicwpInventory().getMagicwpInfoMap();

		MagicwpGetInfoRespMsg.Builder msg = MagicwpGetInfoRespMsg.newBuilder();
		msg.setMagicwpId(magicwpAtt.getCurMagicwpId());
		msg.setUseDanNum(magicwpAtt.getUseDanNum());
		for (MagicwpInfo magicwp : roleMagicwpInfo.values()) {
			MagicwpInfoBeanMsg.Builder bean = MagicwpInfoBeanMsg.newBuilder();
			bean.setId(magicwp.getMagicwpId());
			bean.setLevel(magicwp.getLevel());
			bean.setUpLevelCd(magicwp.getUpLevelCd());
			msg.addInfos(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_GETINFO, msg);
		player.sendPbMessage(p);
	}

}
