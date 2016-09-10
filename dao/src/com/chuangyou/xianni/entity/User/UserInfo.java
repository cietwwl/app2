package com.chuangyou.xianni.entity.User;

import java.util.Date;
import com.chuangyou.xianni.entity.DataObject;

public class UserInfo extends DataObject {
	private long	userId;		// ID
	private String	userName;	// 用户名
	private String	passWord;	// 密码
	private int		serverId;	// 服务器ID
	private String	serverName;	// 服务器名称
	private Date	loginTime1;	// 登录时间1
	private Date	loginTime2;	// 登录时间2
	private String	loginIP1;	// 登录IP1
	private String	loginIP2;	// 登录IP2
	private String	email;		// 邮件
	private String	qq;			// qq
	private Date	createTime;	// 创建时间

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

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getLoginTime1() {
		return loginTime1;
	}

	public void setLoginTime1(Date loginTime1) {
		this.loginTime1 = loginTime1;
	}

	public Date getLoginTime2() {
		return loginTime2;
	}

	public void setLoginTime2(Date loginTime2) {
		this.loginTime2 = loginTime2;
	}

	public String getLoginIP1() {
		return loginIP1;
	}

	public void setLoginIP1(String loginIP1) {
		this.loginIP1 = loginIP1;
	}

	public String getLoginIP2() {
		return loginIP2;
	}

	public void setLoginIP2(String loginIP2) {
		this.loginIP2 = loginIP2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
