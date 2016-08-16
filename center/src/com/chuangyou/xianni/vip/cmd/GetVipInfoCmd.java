package com.chuangyou.xianni.vip.cmd;

import com.chuangyou.common.protobuf.pb.vip.ReqGetVipInfoMsgProto.ReqGetVipInfoMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.vip.manager.VipManager;

@Cmd(code = Protocol.C_GET_VIP_INFO, desc = "获取vip 信息")
public class GetVipInfoCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ReqGetVipInfoMsg msg = ReqGetVipInfoMsg.parseFrom(packet.getBytes());
		VipManager.getVipInfo(player);
	}
}
