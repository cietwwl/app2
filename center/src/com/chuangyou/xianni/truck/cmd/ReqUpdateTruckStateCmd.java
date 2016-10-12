package com.chuangyou.xianni.truck.cmd;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckStateProto.InnerReqTruckState;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.truck.TruckInventory;

@Cmd(code = Protocol.C_TRUCK_UPDATE_TRUCK_STATE, desc = "更新玩家的运镖状态")
public class ReqUpdateTruckStateCmd extends AbstractCommand {

	/** 未任何状态  */
	private static final int STATE_NONE = 0;
	/** 运镖中 */
	private static final int STATE_RUNING = 1;
	/** 护镖中 */
	private static final int STATE_PROTING = 2;
	/** 劫镖中 */
	private static final int STATE_ROBING = 3;
	
	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		InnerReqTruckState msg = InnerReqTruckState.parseFrom(packet.getBytes());
		if(msg.getTrucktype() == 1)	//个人
		{
			if(msg.getState() == STATE_NONE)	//未任何状态 
			{
				player.getTruckInventory().setState(TruckInventory.STATE_NONE);
			}
			else if(msg.getState() == STATE_RUNING)//运镖中
			{
				player.getTruckInventory().setState(TruckInventory.STATE_RUNING_P);
			}
			else if(msg.getState() == STATE_PROTING)//护镖中
			{
				player.getTruckInventory().setState(TruckInventory.STATE_PROTING_P);
			}
			else if(msg.getState() == STATE_ROBING)//劫镖中
			{
				player.getTruckInventory().setState(TruckInventory.STATE_ROBING);
			}
		}
		else	//帮派
		{
			if(msg.getState() == STATE_NONE)	//未任何状态 
			{
				player.getTruckInventory().setState(TruckInventory.STATE_NONE);
			}
			else if(msg.getState() == STATE_RUNING)//运镖中
			{
				player.getTruckInventory().setState(TruckInventory.STATE_RUNING_G);
			}
			else if(msg.getState() == STATE_PROTING)//护镖中
			{
				player.getTruckInventory().setState(TruckInventory.STATE_PROTING_G);
			}
			else if(msg.getState() == STATE_ROBING)//劫镖中
			{
				player.getTruckInventory().setState(TruckInventory.STATE_ROBING);
			}
		}
	}

}
