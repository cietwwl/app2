package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqAddMatToBagProto.InnerReqAddMatToBag;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_TRUCK_ADD_MAT_BAG, desc = "添加物资到背包")
public class ReqAddMaterialsCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqAddMatToBag addMat = InnerReqAddMatToBag.parseFrom(packet.getBytes());
		player.getBagInventory().addItem(addMat.getItemtype(), addMat.getCount(), ItemAddType.TRUCK_DROP_MAT, true);
	}

}
