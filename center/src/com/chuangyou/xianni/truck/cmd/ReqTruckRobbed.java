package com.chuangyou.xianni.truck.cmd;

import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckRobbedProto.InnerReqTruckRobbed;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.truck.helper.LevelUpHelper;
import com.chuangyou.xianni.truck.helper.TruckBillHelper;

@Cmd(code = Protocol.C_TRUCK_ROBBED, desc = "镖车被劫")
public class ReqTruckRobbed extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqTruckRobbed msg = InnerReqTruckRobbed.parseFrom(packet.getBytes());
		if(msg.getTruckType() == TruckInventory.TYPE_P)
		{
			Map<Integer, List<TruckSkillConfig>> skills = player.getTruckInventory().getSkillInfos();
			//每次运镖可额外获得镖师经验 每天一次
			if(player.getBasePlayer().getPlayerTimeInfo().getAddExpByTruckBroken() == 0)
			{
				int ext_exp = TruckBillHelper.fixedAdd(skills.get(TruckBillHelper.TRUCK_ROBBED_EXP));
				LevelUpHelper.levelUp(player, TruckInfo.TRUCKER, ext_exp, 0, LevelUpHelper.UPDATE);
				//更新次数
				player.getBasePlayer().getPlayerTimeInfo().setAddExpByTruckBroken(player.getBasePlayer().getPlayerTimeInfo().getAddExpByTruckBroken()+1);
			}
			
		}
	}

}
