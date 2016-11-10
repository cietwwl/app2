package com.chuangyou.xianni.battle.cmd;

import com.chuangyou.common.protobuf.pb.battle.AddSoulAndBloodProto.AddSoulAndBloodMsg;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_PLAYER_SOUL_BLOOD_ADD, desc = "加血")
public class AddSoulAndBloodCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		AddSoulAndBloodMsg req = AddSoulAndBloodMsg.parseFrom(packet.getBytes());
		if (req.getAddType() == DamageEffecterType.BLOOD) {
			army.getPlayer().addCurBlood(req.getAddNum(), req.getAddType(), 0, 0);
		} else {
			army.getPlayer().addCurSoul(req.getAddNum(), req.getAddType(), 0, 0);
		}
	}

}
