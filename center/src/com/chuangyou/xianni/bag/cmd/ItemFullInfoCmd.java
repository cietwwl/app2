package com.chuangyou.xianni.bag.cmd;

import com.chuangyou.common.protobuf.pb.item.ItemFullInfoMsgProto.ItemFullInfoMsg;
import com.chuangyou.common.protobuf.pb.item.ItemReqInfoMsgProto.ItemReqInfoMsg;
import com.chuangyou.xianni.bag.BaseBag;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_ITEM_FULL_INFO, desc = "请求物品详情")
public class ItemFullInfoCmd extends AbstractCommand {
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		ItemReqInfoMsg delMsg = ItemReqInfoMsg.parseFrom(packet.toByteArray());
		short pos = (short) delMsg.getPos();
		int bagType = delMsg.getBagType();
		BaseBag bag = player.getBagInventory().getBag(bagType);
		BaseItem item = bag.getItemByPos(pos);
		
		if (item != null && item.getItemInfo() != null) {
			ItemFullInfoMsg.Builder fullInfo = ItemFullInfoMsg.newBuilder();
			item.getItemInfo().writeProto(fullInfo);
			
			PBMessage message = MessageUtil.buildMessage(Protocol.U_ITEM_FULL_INFO, fullInfo.build());
			player.sendPbMessage(message);
			
		}
		
	}

}
