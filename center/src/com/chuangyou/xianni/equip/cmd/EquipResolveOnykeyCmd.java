package com.chuangyou.xianni.equip.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.equip.EquipResolveOnekeyReqProto.EquipResolveOnekeyReqMsg;
import com.chuangyou.common.protobuf.pb.equip.EquipResolveOnekeyRespProto.EquipResolveOnekeyRespMsg;
import com.chuangyou.common.protobuf.pb.item.ItemPosProto.ItemPosMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.equip.manager.EquipManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_EQUIP_RESOLVE_ONEKEY, desc = "装备批量分解")
public class EquipResolveOnykeyCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		EquipResolveOnekeyReqMsg req = EquipResolveOnekeyReqMsg.parseFrom(packet.getBytes());
		
		int resolveCount = 0;
		List<ItemPosMsg> itemList = req.getItemsList();
		for(ItemPosMsg itemPos: itemList){
			if(EquipManager.equipResolve(player, itemPos, packet.getCode(), false) == true){
				resolveCount ++;
			}
		}
		
		EquipResolveOnekeyRespMsg.Builder msg = EquipResolveOnekeyRespMsg.newBuilder();
		if(resolveCount > 0){
			msg.setResult(0);
		}else{
			msg.setResult(1);
		}
		PBMessage p = MessageUtil.buildMessage(Protocol.U_EQUIP_RESOLVE_ONEKEY, msg);
		player.sendPbMessage(p);
	}

}
