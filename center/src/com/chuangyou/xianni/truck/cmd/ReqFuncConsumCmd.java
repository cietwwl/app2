package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckFuncConsumProto.InnerReqTruckFuncConsum;
import com.chuangyou.common.protobuf.pb.truck.InnerRespTruckFuncConsumProto.InnerRespTruckFuncConsum;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_TRUCK_FUNCCONSUM, desc = "请求技能消耗")
public class ReqFuncConsumCmd extends AbstractCommand {

	private static final int SUC = 1;		//成功
	private static final int FAIL = 2;		//未知错误
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqTruckFuncConsum msg = InnerReqTruckFuncConsum.parseFrom(packet.getBytes());
		InnerRespTruckFuncConsum.Builder builder = InnerRespTruckFuncConsum.newBuilder();
		int itemCount = player.getBagInventory().getItemCount(msg.getItemtype());
		if(itemCount < msg.getItemcount())
		{
			//不能使用
			builder.setState(FAIL);	//失败
		}
		else
		{
			boolean result = player.getBagInventory().removeItem(msg.getItemtype(), msg.getItemcount(), ItemRemoveType.TRUCK_SKILL);
			if(result)
				builder.setState(SUC);	//成功
			else
				builder.setState(FAIL);	//失败
		}
		builder.setTruckid(msg.getTruckid());
		builder.setSkillid(msg.getSkillid());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_RESP_TRUCK_FUNCCONSUM, builder);
		player.sendPbMessage(pkg);
	}

}
