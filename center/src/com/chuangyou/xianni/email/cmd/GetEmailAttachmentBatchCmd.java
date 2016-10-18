package com.chuangyou.xianni.email.cmd;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.chuangyou.common.protobuf.pb.email.GetEmailAttachmentBatchRespProto.GetEmailAttachmentBatchRespMsg;
import com.chuangyou.common.protobuf.pb.email.OperationEmailRespProto.OperationEmailRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.CommonType;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_GETEMAILATTACHMENTBATCH, desc = "批量提取邮件附件")
public class GetEmailAttachmentBatchCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		List<Email> list = player.getEmailInventory().getEmails();

		boolean flag = false;
		OperationEmailRespMsg.Builder nmsg = OperationEmailRespMsg.newBuilder();
		nmsg.setType(2);
		for (Email email : list) {
			if (email.getStatus() == Email.NORMAL_EMAIL || email.getStatus() == Email.READED_EMAIL) { // 邮件状态正确
				if (!email.getAttachment().equals("")) { // 附件不为空
					// todo 向背包里加物品。
					// todo 判断背包是否满啦。
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
							if (player.getBagInventory().addItem(item.getItemTemplateId(), item.getCount(), ItemAddType.EMAIL_ADD, item.getBind()==BindType.BIND?true:false)) {
								it.remove();
							} else {
								ErrorMsgUtil.sendErrorMsg(player, ErrorCode.BAG_IS_FULL, Protocol.C_REQ_GETEMAILATTACKMENT, "背包已满");
								flag = true;
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
					nmsg.addEmails(EmailManager.changeEmail(email));
					if (flag == true) {
						break;
					}

				}
			}
		}

		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPERATIONEMAIL, nmsg);
		player.sendPbMessage(pkg);

		GetEmailAttachmentBatchRespMsg.Builder msg = GetEmailAttachmentBatchRespMsg.newBuilder();
		if (!flag) {
			msg.setResultType(1);
		} else {
			msg.setResultType(2);
		}
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETEMAILATTACHMENTBATCH, msg);
		player.sendPbMessage(pkg);

	}

}
