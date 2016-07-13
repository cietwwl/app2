package com.chuangyou.xianni.magicwp.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanGetInfoRespProto.MagicwpBanGetInfoRespMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpBanInfoBeanProto.MagicwpBanInfoBeanMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_BAN_GETINFO, desc = "获取禁制信息")
public class MagicwpBanGetInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		Map<Integer, MagicwpBanInfo> roleMagicwpBanInfo = player.getMagicwpInventory().getBanInfoMap();
		
		MagicwpBanGetInfoRespMsg.Builder msg = MagicwpBanGetInfoRespMsg.newBuilder();
		
		for(MagicwpBanInfo ban:roleMagicwpBanInfo.values()){
			MagicwpBanInfoBeanMsg.Builder bean = MagicwpBanInfoBeanMsg.newBuilder();
			bean.setBanId(ban.getBanId());
			bean.setPosition(ban.getPosition());
			bean.setFragmentStr(ban.getFragmentStr());
			bean.setLevel(ban.getLevel());
			bean.setExp(ban.getExp());
			bean.setAutoUpLev(ban.getAutoUpLevel());
			msg.addBans(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_BAN_GETINFO, msg);
		player.sendPbMessage(p);
	}

}
