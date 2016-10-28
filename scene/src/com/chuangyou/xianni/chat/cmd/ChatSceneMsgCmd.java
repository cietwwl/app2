package com.chuangyou.xianni.chat.cmd;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_CHAT_INNER_SEND, desc = "通知场景服发送聊天消息至同场景内所有玩家")
public class ChatSceneMsgCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ChatReceiveMsg req = ChatReceiveMsg.parseFrom(packet.getBytes());
		Field field = FieldMgr.getIns().getField(army.getFieldId());
		ChatManager.sendSceneChatMsg(field, req);
	}

}
