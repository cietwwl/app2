package com.chuangyou.xianni.entity.email;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.entity.DataObject;

/**  
 * 邮件数据结构
 * */
public class Email extends DataObject{

	/** 0：初始生成状态 */
	public static final byte NORMAL_EMAIL = 0;
	/** 1：已读 */
	public static final byte READED_EMAIL = 1;
	/** 2：已经提取附件+已读  */
	public static final byte READED_GETATTACHMENT_EMAIL = 2;
	/** 3：已经删除   */
	public static final byte DEL_EMAIL = 3;
	
		
	private int privateId;//` INT(11) NOT NULL,
	private long roleId;//` BIGINT(20) NOT NULL,
	private String title = "" ;//` VARCHAR(64) NOT NULL COMMENT '邮件标题',
	private String content = "";//` VARCHAR(512) NOT NULL COMMENT '邮件内容',
	private Date createTime = new Date();//` DATETIME NOT NULL COMMENT '邮件生成日期',
	private Date getAttachmentTime = new Date(0);//` DATETIME NOT NULL COMMENT '获取附件时间',
	private Date delEmailTime = new Date(0);//` DATETIME NOT NULL COMMENT '删除邮件时间',
	private byte status=0;//` TINYINT(4) NOT NULL COMMENT '邮件状态。0：初始生成状态  1：已读  2：已经提取附件+已读  3：已经删除 ',
	private String attachment="";//` VARCHAR(50) NOT NULL COMMENT '邮件附件 格式“ID，数量;ID，数量;ID，数量;ID，数量”'
	
	
	public Email() {
		// TODO Auto-generated constructor stub
	}
	
	public Email(int id,Date d){
		this.privateId = id;
		this.createTime = d;
	}

	/**
	 * 判断邮件是否过期
	 * @param time ：过期时间（单位:豪秒）
	 * @return
	 */
	public boolean isExpiration(Long time){
		if(new Date().getTime() - this.createTime.getTime()>time){
			return true;
		}
		return false;
	}



	public int getPrivateId() {
		return privateId;
	}

	public void setPrivateId(int privateId) {
		this.privateId = privateId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getGetAttachmentTime() {
		return getAttachmentTime;
	}



	public void setGetAttachmentTime(Date getAttachmentTime) {
		this.getAttachmentTime = getAttachmentTime;
	}



	public Date getDelEmailTime() {
		return delEmailTime;
	}



	public void setDelEmailTime(Date delEmailTime) {
		this.delEmailTime = delEmailTime;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public Map<Integer, Integer> toItemsMap(){
		if(StringUtils.isNullOrEmpty(attachment))return null;
		Map<Integer, Integer> map = new HashMap<>();
		String[] list = this.attachment.split(";");
		for (int i = 0; i < list.length; i++) {
			String[] subList = list[i].split(",");
			map.put(Integer.parseInt(subList[0]),Integer.parseInt(subList[1]));
		}
		return map;		
	}
	
	@Override
	public String toString() {
		return "Email [privateId=" + privateId + ", roleId=" + roleId + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + ", getAttachmentTime=" + getAttachmentTime + ", delEmailTime="
				+ delEmailTime + ", status=" + status + ", attachment=" + attachment + "]";
	}












	

	
}
