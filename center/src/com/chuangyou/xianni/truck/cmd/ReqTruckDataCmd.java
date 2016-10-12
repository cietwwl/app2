package com.chuangyou.xianni.truck.cmd;

import java.util.Map;

import com.chuangyou.common.protobuf.pb.truck.RespTruckDataProto.RespTruckData;
import com.chuangyou.common.protobuf.pb.truck.TruckDataProto.TruckData;
import com.chuangyou.common.protobuf.pb.truck.TruckSkillProto.TruckSkill;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.truck.TruckInfo;
import com.chuangyou.xianni.entity.truck.TruckSkillInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.helper.TruckDataHelper;

@Cmd(code = Protocol.C_TRUCK_REQTRUCKDATA, desc = "查询运镖信息")
public class ReqTruckDataCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		TruckDataHelper.sendTruckData(player);
	}
	
	

}
