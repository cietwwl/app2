package com.chuangyou.xianni.email.cmd;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.chuangyou.common.protobuf.pb.email.GetEmailAttachmentReqProto.GetEmailAttachmentReqMsg;
import com.chuangyou.common.protobuf.pb.email.GetEmailAttachmentRespProto.GetEmailAttachmentRespMsg;
import com.chuangyou.common.protobuf.pb.email.OperationEmailRespProto.OperationEmailRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.CommonType;
import com.chuangyou.xianni.email.EmailInventory;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_GETEMAILATTACKMENT, desc = "提取邮件附件")
public class GetEmailAttachmentCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		GetEmailAttachmentReqMsg req = GetEmailAttachmentReqMsg.parseFrom(packet.getBytes());

		Email email = player.getEmailInventory().getEmailById(req.getPrivateId());
		if (email == null) {
			// todo抛错误码
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_IS_NOT_EXIST, packet.getCode());
			return;
		}
		if (email.isExpiration(EmailInventory.EXPIRATION_TIME)) {
			player.getEmailInventory().deleteEmail(email);
			// todo抛错误码 邮件过期
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_Is_Expiration, packet.getCode());
			return;
		}
		if (email.getAttachment().equals("")) {
			// throw new
			// MXY2Exception(ErrorCode.Email_Not_hava_Attachment,"此邮件无附件可取");
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_Not_hava_Attachment, packet.getCode());
			return;
		}
		if (email.getStatus() == Email.READED_GETATTACHMENT_EMAIL) {
			// throw new
			// MXY2Exception(ErrorCode.Email_Attackment_Has_Get,"附件已经提取过");
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Email_Attackment_Has_Get, packet.getCode());
			return;
		}

		// todo取附件（如果背包满了抛出背包已满exception）
		List<EmailItemVo> items = EmailManager.getEmailItems(email.getAttachment());
		Iterator<EmailItemVo> it = items.iterator();
		while (it.hasNext()) {
			EmailItemVo item = it.next();
			if (item.getItemTemplateId() == CommonType.CurrencyItemType.MONEY_ITEM) {
				player.getBasePlayer().addMoney(item.getCount(), ItemAddType.EMAIL_ADD);
				it.remove();
			} else if (item.getItemTemplateId() == CommonType.CurrencyItemType.CASH_ITEM) {
				player.getBasePlayer().addCash(item.getCount(), ItemAddType.EMAIL_ADD);
				it.remove();
			} else if (item.getItemTemplateId() == CommonType.CurrencyItemType.CASH_BIND_ITEM) {
				player.getBasePlayer().addBindCash(item.getCount(), ItemAddType.EMAIL_ADD);
				it.remove();
			} else {
				if (player.getBagInventory().addItem(item.getItemTemplateId(), item.getCount(), ItemAddType.EMAIL_ADD, true)) {
					it.remove();
				} else {
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.BAG_IS_FULL, Protocol.C_REQ_GETEMAILATTACKMENT, "背包已满");
					break;
				}
			}
		}
		if (items.size() == 0) {
			email.setGetAttachmentTime(new Date());
			email.setStatus(Email.READED_GETATTACHMENT_EMAIL);
			player.getEmailInventory().updateEmail(email);
		} else {
			StringBuffer str = new StringBuffer("");
			Iterator<EmailItemVo> tempIt = items.iterator();
			while (tempIt.hasNext()) {
				EmailItemVo item = tempIt.next();
				str.append(item.getItemTemplateId()).append(",").append(item.getCount()).append(",").append(item.getBind()).append(";");
			}
			email.setGetAttachmentTime(new Date());
			email.setAttachment(str.toString());
			player.getEmailInventory().updateEmail(email);
		}

		OperationEmailRespMsg.Builder notify = OperationEmailRespMsg.newBuilder();
		notify.setType(2);
		notify.addEmails(EmailManager.changeEmail(email));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPERATIONEMAIL, notify);
		player.sendPbMessage(pkg);

		GetEmailAttachmentRespMsg.Builder msg = GetEmailAttachmentRespMsg.newBuilder();
		msg.setPrivateId(email.getPrivateId());
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETEMAILATTACKMENT, msg);
		player.sendPbMessage(pkg);

	}

}
