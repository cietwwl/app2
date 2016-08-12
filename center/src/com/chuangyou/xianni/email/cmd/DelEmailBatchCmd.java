package com.chuangyou.xianni.email.cmd;

import java.util.Iterator;
import java.util.List;

import com.chuangyou.common.protobuf.pb.email.DelEmailBatchRespProto.DelEmailBatchRespMsg;
import com.chuangyou.common.protobuf.pb.email.OperationEmailRespProto.OperationEmailRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_DELEMAIL_BATCH, desc = "批量删除邮件")
public class DelEmailBatchCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		List<Email> list = player.getEmailInventory().getEmails();
		if (list.size() == 0) {
			// throw new MXY2Exception(ErrorCode.Email_IS_NULL,"邮件列表为空");
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_IS_NULL, packet.getCode());
			return;
		}

		OperationEmailRespMsg.Builder msg = OperationEmailRespMsg.newBuilder();
		msg.setType(3);
		Iterator<Email> it = list.iterator();
		while(it.hasNext()){
			Email email = it.next();
			if (email.getStatus() != Email.READED_GETATTACHMENT_EMAIL && !email.getAttachment().equals("")) { // 有附件未取的邮件
			} else {
				player.getEmailInventory().deleteEmail(email);
				msg.addEmails(EmailManager.changeEmail(email));
			}
		}
		
		if (msg.getEmailsList().size() == 0) {
			// throw new MXY2Exception(ErrorCode.Email_NO_DELETE,"没有可进行删除的邮件");
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_NO_DELETE, packet.getCode());
			return;
		}

		DelEmailBatchRespMsg.Builder msg1 = DelEmailBatchRespMsg.newBuilder();
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_DELEMAIL_BATCH, msg1);

		player.sendPbMessage(pkg);

		pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPERATIONEMAIL, msg);

		player.sendPbMessage(pkg);

	}

}
