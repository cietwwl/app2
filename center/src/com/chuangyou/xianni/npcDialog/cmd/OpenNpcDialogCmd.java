package com.chuangyou.xianni.npcDialog.cmd;

import com.chuangyou.common.protobuf.pb.npcDialog.OpenNpcDialogReqProto.OpenNpcDialogReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.spawn.NpcInfo;
import com.chuangyou.xianni.npcDialog.NpcInfoTemplateMgr;
import com.chuangyou.xianni.npcDialog.script.INpcDialogTrigger;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_OPENNPCDIALOG,desc="打开NPC对话")
public class OpenNpcDialogCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		OpenNpcDialogReqMsg req = OpenNpcDialogReqMsg.parseFrom(packet.getBytes());
		int npcid = req.getNpcType();
		NpcInfo npc = NpcInfoTemplateMgr.npcInfoTemps.get(npcid); 
		if(npc==null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.NPC_TYPE_IS_NOT_EXISTED, Protocol.C_REQ_OPENNPCDIALOG);
			return ;
		}
		INpcDialogTrigger script = (INpcDialogTrigger) ScriptManager.getScriptById(npc.getScriptId());
		if(script == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.NPC_NO_HAVE_SCRIPT, Protocol.C_REQ_OPENNPCDIALOG);
			return ;
		}
		
		script.showDialog(player.getPlayerId(), npcid);
		//TaskManager.calcNpcDialog(player, npcid);

	}

}
