package com.chuangyou.xianni.magicwp.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.AttributeBeanProto.AttributeBeanMsg;
import com.chuangyou.common.protobuf.pb.magicwp.MagicwpGetTotalAttRespProto.MagicwpGetTotalAttRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.magicwp.manager.MagicwpManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MAGICWP_GETTOTALATT, desc = "获取法宝总属性")
public class MagicwpGetTotalAttCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		Map<Integer, Integer> attMap = MagicwpManager.computeMagicwpAtt(player);
		
		MagicwpGetTotalAttRespMsg.Builder msg = MagicwpGetTotalAttRespMsg.newBuilder();
		for(int attType:attMap.keySet()){
			AttributeBeanMsg.Builder bean = AttributeBeanMsg.newBuilder();
			bean.setAttType(attType);
			bean.setAttValue(attMap.get(attType));
			msg.addAttList(bean);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MAGICWP_GETTOTALATT, msg);
		player.sendPbMessage(p);
	}

}
