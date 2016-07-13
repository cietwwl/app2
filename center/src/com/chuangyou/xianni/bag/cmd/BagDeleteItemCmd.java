package com.chuangyou.xianni.bag.cmd;

import com.chuangyou.common.protobuf.pb.item.ItemDeleteMsgProto.ItemDeleteMsg;
import com.chuangyou.xianni.bag.BaseBag;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_ITEM_DELETE, desc = "物品删除")
public class BagDeleteItemCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ItemDeleteMsg delMsg = ItemDeleteMsg.parseFrom(packet.toByteArray());
		short pos = (short) delMsg.getPos();
		int bagType = delMsg.getBagType();
		BaseBag bag = player.getBagInventory().getBag(bagType);
		if (bag != null) {
			bag.removeByPos(pos, ItemRemoveType.PLAYER_DELETE);
		}
	}

}
