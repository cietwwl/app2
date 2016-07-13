package com.chuangyou.xianni.npcDialog.manager;

import java.util.List;

import com.chuangyou.common.protobuf.pb.npcDialog.OpenNpcDialogRespProto.OpenNpcDialogRespMsg;
import com.chuangyou.common.util.ChangeCharsetUtil;
import com.chuangyou.xianni.npcDialog.NpcCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;


public class NpcDialogManager {

	/**
	 * 
	 * @param list :命令列表
	 * @param showContent ：显示内容
	 */
	public static void sendResultToClient(long roleId,List<NpcCommand> list,String showContent){
		GamePlayer player = WorldMgr.getPlayer(roleId);
		if(player==null)return;
		OpenNpcDialogRespMsg.Builder resp = OpenNpcDialogRespMsg.newBuilder();
		resp.setContent(ChangeCharsetUtil.toUTF_8(showContent));
		for (NpcCommand npcCommand : list) {
			com.chuangyou.common.protobuf.pb.npcDialog.NpcCommandProto.NpcCommand.Builder c =  com.chuangyou.common.protobuf.pb.npcDialog.NpcCommandProto.NpcCommand.newBuilder();
			c.setCommandDes(ChangeCharsetUtil.toUTF_8(npcCommand.getCommandDes()));
			c.setCommandId(npcCommand.getCommandId());
			c.setCommandParam(npcCommand.getCommandParam());
			resp.addCommands(c);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPENNPCDIALOG, resp);
		player.sendPbMessage(pkg);
	}
	
}
