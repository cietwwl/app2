package com.chuangyou.xianni.email.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.email.GetEmailInfoByIndexReqProto.GetEmailInfoByIndexReqMsg;
import com.chuangyou.common.protobuf.pb.email.GetEmailInfoByIndexRespProto.GetEmailInfoByIndexRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_GETEMAILINFOBYINDEX, desc = "请求邮件详细信息")
public class GetEmailInfoByIndexCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetEmailInfoByIndexReqMsg req = GetEmailInfoByIndexReqMsg.parseFrom(packet.getBytes());
		List<Email> list = player.getEmailInventory().getEmails();
		int start = Math.max(req.getStartIndex(), 0);
		int end   = Math.min(req.getEndIndex(), list.size());
		
		GetEmailInfoByIndexRespMsg.Builder msg = GetEmailInfoByIndexRespMsg.newBuilder();
		list = list.subList(start, end);
		for (Email email : list) {
			msg.addEmails(EmailManager.changeEmail(email));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETEMAILINFOBYINDEX, msg);
		player.sendPbMessage(pkg);
	}

}
