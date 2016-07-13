package com.chuangyou.xianni.entity.item;

/**
 * 
 * <pre>
 * 临时物品发送邮件信息
 * </pre>
 */
public class AddTempItemMsg {

	/**
	 * 邮件标题
	 */
	private String	dropTitle;

	/**
	 * 邮件来源
	 */
	private boolean	sysFrom;

	/**
	 * 存在邮件中
	 */
	private boolean	mailIn;

	/**
	 * 走邮件发送
	 */
	private boolean	toMail;

	/**
	 * 提示信息
	 */
	private String	msg;

	public AddTempItemMsg() {
		msg = "";
		setToMail(true);
	}

	public void fromDrop(String mailTitle) {

	}

	public void fromShop() {

	}

	public void fromJoin() {

	}

	public void fromShop(String mailTitle) {

	}

	public void setDropTitle(String dropTitle) {
		this.dropTitle = dropTitle;
	}

	public String getDropTitle() {
		return dropTitle;
	}

	public void setMailIn(boolean mailIn) {
		this.mailIn = mailIn;
	}

	public boolean getMailIn() {
		return mailIn;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		if (msg == null) {
			msg = "";
		}
		return msg;
	}

	public void setSysFrom(boolean sysFrom) {
		this.sysFrom = sysFrom;
	}

	public boolean getSysFrom() {
		return sysFrom;
	}

	public void setToMail(boolean toMail) {
		this.toMail = toMail;
	}

	public boolean getToMail() {
		return toMail;
	}

}
