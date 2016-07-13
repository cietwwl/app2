package com.chuangyou.xianni.warfield.cmd;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.player.PlayerMountStateReqProto.PlayerMountStateReqMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerMountStateRespProto.PlayerMountStateRespMsg;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_PLAYER_MOUNT_STATE_REQ, desc = "请求上下坐骑")
public class PlayerMountStateCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		PlayerMountStateReqMsg req = PlayerMountStateReqMsg.parseFrom(packet.getBytes());
		if(req.getPlayerId() != army.getPlayerId()) return;
		
		if(army.getPlayer().getMountState() != req.getMountState()){
			
			/**         上坐骑验证,规则出来以后添加            */
			
			army.getPlayer().setMountState(req.getMountState());
			
			//通知自己
			PlayerMountStateRespMsg.Builder msg = PlayerMountStateRespMsg.newBuilder();
			msg.setPlayerId(army.getPlayerId());
			msg.setMountState(army.getPlayer().getMountState());
			PBMessage pmsg = MessageUtil.buildMessage(Protocol.U_PLAYER_MOUNT_STATE_RESP, msg);
			army.sendPbMessage(pmsg);
			
			//通知附近玩家
			Set<Long> nears = army.getPlayer().getNears(new PlayerSelectorHelper(army.getPlayer()));
			BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_PLAYER_MOUNT_STATE_RESP, msg.build());
		}
	}

}
