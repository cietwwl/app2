package com.chuangyou.xianni.mount.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.mount.MountSpecialListGetRespProto.MountSpecialListGetRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.mount.MountSpecialGet;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_MOUNT_SPECIAL_GET, desc = "已获得的特殊坐骑列表")
public class MountSpecialGetCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> idList = new ArrayList<>();
		
		Map<Integer, MountSpecialGet> roleMountGet = player.getMountInventory().getMountSpecialMap();
		if(roleMountGet != null){
			for(MountSpecialGet mountGet:roleMountGet.values()){
				idList.add(mountGet.getMountId());
			}
		}
		
		MountSpecialListGetRespMsg.Builder msg = MountSpecialListGetRespMsg.newBuilder();
		msg.addAllMountId(idList);
		PBMessage p = MessageUtil.buildMessage(Protocol.U_MOUNT_SPECIAL_GET, msg);
		player.sendPbMessage(p);
	}

}
