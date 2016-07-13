package com.chuangyou.xianni.email.cmd;

import com.chuangyou.common.protobuf.pb.email.DelEmailReqProto.DelEmailReqMsg;
import com.chuangyou.common.protobuf.pb.email.DelEmailRespProto.DelEmailRespMsg;
import com.chuangyou.common.protobuf.pb.email.OperationEmailRespProto.OperationEmailRespMsg;
import com.chuangyou.common.util.StringUtils;
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

@Cmd(code = Protocol.C_REQ_DELEMAIL, desc = "删除邮件")
public class DelEmailCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		DelEmailReqMsg req = DelEmailReqMsg.parseFrom(packet.getBytes());
		Email email = player.getEmailInventory().getEmailById(req.getPrivateId());
		
		if(email == null || email.getStatus() == Email.DEL_EMAIL){
			//throw new MXY2Exception(ErrorCode.Email_IS_NOT_EXIST,"邮件不存在");
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_IS_NOT_EXIST, packet.getCode());
			return;
		}
		
		if(!StringUtils.isNullOrEmpty(email.getAttachment()) && email.getStatus()!=Email.READED_GETATTACHMENT_EMAIL){
			//throw new MXY2Exception(ErrorCode.UNKNOW_ERROR, "不能删除有附件的邮件");
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, packet.getCode());
			return;
		}
		
		player.getEmailInventory().deleteEmail(email);
		
		DelEmailRespMsg.Builder msg = DelEmailRespMsg.newBuilder();
		msg.setPrivateId(email.getPrivateId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_DELEMAIL, msg);
		player.sendPbMessage(pkg);
		
		OperationEmailRespMsg.Builder notify = OperationEmailRespMsg.newBuilder();
		notify.setType(3);
		notify.addEmails(EmailManager.changeEmail(email));
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPERATIONEMAIL, notify);
		player.sendPbMessage(pkg);
	
	}

}
