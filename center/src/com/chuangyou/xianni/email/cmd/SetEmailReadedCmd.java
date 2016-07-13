package com.chuangyou.xianni.email.cmd;

import com.chuangyou.common.protobuf.pb.email.OperationEmailRespProto.OperationEmailRespMsg;
import com.chuangyou.common.protobuf.pb.email.SetEmailReadedReqProto.SetEmailReadedReqMsg;
import com.chuangyou.common.protobuf.pb.email.SetEmailReadedRespProto.SetEmailReadedRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.email.EmailInventory;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
/**
 * 设置邮件为已读
 * @author laofan
 *
 */
@Cmd(code = Protocol.C_REQ_SETEMAILREADER, desc = "设置邮件为已读")
public class SetEmailReadedCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SetEmailReadedReqMsg req = SetEmailReadedReqMsg.parseFrom(packet.getBytes());
		Email email = player.getEmailInventory().getEmailById(req.getPrivateId());
		if(email ==null){
			//todo抛错误码
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_IS_NOT_EXIST, packet.getCode());
			return ;
		}
		if(email.isExpiration(EmailInventory.EXPIRATION_TIME)){
			player.getEmailInventory().deleteEmail(email);
			//todo抛错误码 邮件过期 
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_Is_Expiration, packet.getCode());
			return;
		}
		if(email.getStatus() == Email.NORMAL_EMAIL){
			email.setStatus(Email.READED_EMAIL);
			player.getEmailInventory().updateEmail(email);
		}
		
		OperationEmailRespMsg.Builder notify = OperationEmailRespMsg.newBuilder();
		notify.setType(2);
		notify.addEmails(EmailManager.changeEmail(email));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPERATIONEMAIL, notify);
		player.sendPbMessage(pkg);
		
		SetEmailReadedRespMsg.Builder msg = SetEmailReadedRespMsg.newBuilder();
		msg.setPrivateId(email.getPrivateId());
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_SETEMAILREADER, msg);
		player.sendPbMessage(pkg);
		
		
	}

}
