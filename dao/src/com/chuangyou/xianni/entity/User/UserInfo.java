package com.chuangyou.xianni.entity.User;

import com.chuangyou.xianni.entity.DataObject;

public class UserInfo extends DataObject {
	private long	userId;
	private String	userName;
	private String	passWord;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
