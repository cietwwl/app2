package com.chuangyou.xianni.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.email.Email;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

/**
 * 邮件数据管理容器
 * 
 * @author laofan
 *
 */
public class EmailInventory extends AbstractEvent implements IInventory {

	/** 过期时间 */
	public static final long	EXPIRATION_TIME	= 10 * 24 * 60 * 60 * 1000;
	/** 邮件的上限是200 */
	private static final int	MAX_EMAIL_NUM	= 200;

	/**
	 * 玩家
	 */
	private GamePlayer			player;

	/**
	 * 玩家邮件列表
	 */
	private List<Email>			emails;

	/** 添加邮件锁 */
	private Object				addEmailLock;

	public EmailInventory(GamePlayer player) {
		this.player = player;
		addEmailLock = new Object();
	}

	/**
	 * 从DB加载数据
	 * 
	 * @return
	 */
	public boolean loadFromDataBase() {
		emails = DBManager.getEmaildao().getAll(player.getPlayerId());
		return true;
	}

	/**
	 * 卸载数据
	 * 
	 * @return
	 */
	public boolean unloadData() {
		this.player = null;
		if (emails != null) {
			this.emails.clear();
			this.emails = null;
		}
		return true;
	}

	/**
	 * 获取玩家邮件列表 会自动将过期邮件清除
	 * 
	 * @return
	 */
	public List<Email> getEmails() {
		Iterator<Email> iter = emails.iterator();
		List<Email> expires = new ArrayList<Email>();
		while (iter.hasNext()) {
			Email s = iter.next();
			if (s.isExpiration(EXPIRATION_TIME)) {
				//this.deleteEmail(s);
				expires.add(s);
			}
		}
		if(expires.size()>0){
			for (Email email : expires) {
				this.deleteEmail(email);
			}
		}
		
		return emails.subList(0, Math.min(emails.size(), MAX_EMAIL_NUM));
	}

	/**
	 * 查找邮件
	 * 
	 * @param privateId
	 * @return
	 */
	public Email getEmailById(int privateId) {
		for (Email email : emails) {
			if (email.getPrivateId() == privateId)
				return email;
		}
		return null;
	}

	/**
	 * 添加一封邮件
	 * 
	 * @param email
	 */
	public boolean addEmail(Email email) {
		if (email == null)
			return false;
		if (email.getRoleId() != this.player.getPlayerId())
			return false;
		if (emails.indexOf(email) != -1)
			return false;

		synchronized (addEmailLock) {
			int id = DBManager.getEmaildao().add(email);
			if (id <= 0) {
				return false;
			}
			email.setPrivateId(id);
			emails.add(0, email);
			int len = emails.size();
			if (emails.size() > MAX_EMAIL_NUM) {
				emails.remove(len - 1);
			}
		}
		return true;
	}

	/**
	 * 删除邮件
	 * 
	 * @param email
	 * @return
	 */
	public boolean deleteEmail(Email email) {
		if (email == null)
			return false;
		if (email.getRoleId() != this.player.getPlayerId())
			return false;
		if (emails.indexOf(email) == -1)
			return false;
		emails.remove(email);
		email.setStatus(Email.DEL_EMAIL);
		email.setDelEmailTime(new Date());
		DBManager.getEmaildao().update(email);
		return true;
	}

	/**
	 * 更新邮件
	 * 
	 * @param email
	 * @return
	 */
	public boolean updateEmail(Email email) {
		if (email == null)
			return false;
		if (email.getRoleId() != this.player.getPlayerId())
			return false;
		email.setOp(Option.Update);
		return true;
	}

	/**
	 * 数据同步数据库
	 * 
	 * @return
	 */
	public boolean saveToDatabase() {
		if (emails == null || emails.size() == 0)
			return true;
		int len = emails.size();
		for (int i = 0; i < len; i++) {
			short option = emails.get(i).getOp();
			if (option == Option.Update) {
				DBManager.getEmaildao().update(emails.get(i));
			}
		}
		return true;
	}

}
