package com.chuangyou.xianni.bag.cmd;

import com.chuangyou.common.protobuf.pb.item.ItemMoveMsgProto.ItemMoveMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_EQUIMPENT_OPTION, desc = "装备操作")
public class BagMoveReceiveCmd extends AbstractCommand {
	/*
	 * 背包移动接受协议
	 */
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ItemMoveMsg moveReq = ItemMoveMsg.parseFrom(packet.toByteArray());
		int beginBagType = moveReq.getBeginBagType();
		short beginPos = (short) moveReq.getBeginPos();
		// int endBagType = moveReq.getEndBagType();
		// short endPos = (short) moveReq.getEndPos();
		// short count = (short) moveReq.getCount();
		if (player.getBagInventory() == null) {
			return;
		}
		player.getBagInventory().equimentOption(beginBagType, beginPos);
		
	}

}
