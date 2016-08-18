package com.chuangyou.xianni.equip.cmd;

import com.chuangyou.common.protobuf.pb.equip.EquipInfoReqProto.EquipInfoReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.equip.EquipOperateAction;
import com.chuangyou.xianni.equip.manager.EquipManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_EQUIP_INFO, desc = "装备信息操作")
public class EquipInfoCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		EquipInfoReqMsg req = EquipInfoReqMsg.parseFrom(packet.getBytes());
		
		switch(req.getAction()){
		case EquipOperateAction.Equip.AWAKEN:
			EquipManager.weaponAwaken(player, req.getEquipPos(), packet.getCode());
			break;
		case EquipOperateAction.Equip.STONE:
			EquipManager.equipStone(player, req.getEquipPos(), req.getStonePos(), packet.getCode());
			break;
		case EquipOperateAction.Equip.RESOLVE:
			EquipManager.equipResolve(player, req.getEquipPos(), packet.getCode(), true);
			break;
		}
	}
	
}
