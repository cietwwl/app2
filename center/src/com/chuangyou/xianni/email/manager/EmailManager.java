package com.chuangyou.xianni.email.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.email.EmailInfoProto.EmailInfo;
import com.chuangyou.common.protobuf.pb.email.OperationEmailRespProto.OperationEmailRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

public class EmailManager {

	/**
	 * 最大附件物品数量
	 */
	private static final int MAX_EMAIL_ITEM_COUNT = 5;

	/**
	 * 封装转换为PB所用的类
	 * 
	 * @param email
	 * @return
	 */
	public static EmailInfo.Builder changeEmail(Email email) {
		EmailInfo.Builder e = EmailInfo.newBuilder();
		e.setPrivateId(email.getPrivateId());
		e.setTitle(email.getTitle());
		e.setContent(email.getContent());
		e.setCreateTime(email.getCreateTime().getTime());
		e.setStatus(email.getStatus());
		e.setAttachment(email.getAttachment());

		return e;
	}

	/**
	 * 获取附件物品列表
	 * 
	 * @param attachment
	 * @return
	 */
	public static List<EmailItemVo> getEmailItems(String attachment) {
		if (StringUtils.isNullOrEmpty(attachment))
			return null;
		List<EmailItemVo> items = new ArrayList<>();
		String[] list = attachment.split(";");
		for (int i = 0; i < list.length; i++) {
			String[] subList = list[i].split(",");
			if (subList.length == 3) {
				items.add(new EmailItemVo(Integer.parseInt(subList[0]), Integer.parseInt(subList[1]), Integer.parseInt(subList[2])));
			}
		}
		return items;
	}

	/**
	 * 插入邮件
	 * 
	 * @param roleId
	 *            ：邮件获得者ID
	 * @param title
	 *            ：邮件标题
	 * @param content
	 *            ：邮件内容
	 * @param attachment
	 *            ：邮件附件
	 * @throws MXY2Exception
	 */
	public static void insertEmail(long playerId, String title, String content, String attachment) {
		Email email = new Email();
		email.setRoleId(playerId);
		email.setTitle(title);
		email.setContent(content);
		email.setCreateTime(new Date());
		email.setStatus(Email.NORMAL_EMAIL);
		email.setAttachment(attachment);
		email.setOp(Option.Insert);

		GamePlayer role = WorldMgr.getPlayer(playerId);
		if (role == null) {
			return;
		}
		if (role.getPlayerState() == PlayerState.ONLINE) { // 在线
			if (role.getEmailInventory().addEmail(email)) {
				// 通知用户有新邮件
				OperationEmailRespMsg.Builder resp = OperationEmailRespMsg.newBuilder();
				resp.setType(1);
				resp.addEmails(changeEmail(email));
				System.out.println("Desc:" + resp.getDescriptorForType());
				PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_OPERATIONEMAIL, resp);
				role.sendPbMessage(pkg);
			} else {
				Log.error("添加邮件失败" + email.toString());
			}
		} else { // 离线
			int id = DBManager.getEmaildao().add(email);
			if (id <= 0) {
				Log.error("添加邮件失败" + email.toString());
			}
		}
	}

	/**
	 * 插入带附件的邮件接品
	 * 
	 * @param playerId
	 * @param title
	 * @param content
	 * @param emailItems
	 */
	public static void insertEmail(long playerId, String title, String content, List<EmailItemVo> emailItems) {
		StringBuffer attachment = new StringBuffer("");

		if (emailItems == null || emailItems.size() == 0) {
			insertEmail(playerId, title, content, attachment.toString());
		} else {
			int len = emailItems.size();
			for (int i = 0; i < len; i++) {
				if (i % MAX_EMAIL_ITEM_COUNT == (MAX_EMAIL_ITEM_COUNT - 1)) {
					insertEmail(playerId, title, content, attachment.toString());
					attachment = new StringBuffer("");
				} else {
					attachment.append(emailItems.get(i).attachmentStr());
				}
			}
			if (!attachment.toString().equals("")) {
				insertEmail(playerId, title, content, attachment.toString());
			}
		}
	}

	/**
	 * 插入邮件
	 * 
	 * @param roleId
	 *            ：邮件获得者ID
	 * @param title
	 *            ：邮件标题
	 * @param content
	 *            ：邮件内容
	 * @throws MXY2Exception
	 */
	public static void insertEmail(long roleId, String title, String content) {
		insertEmail(roleId, title, content, "");
	}

	/** 发送邮件 */
	public static void sendItem4Mail() {

	}
}
