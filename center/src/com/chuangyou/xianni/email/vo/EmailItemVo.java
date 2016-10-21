package com.chuangyou.xianni.email.vo;

import com.chuangyou.xianni.entity.item.BindType;

/**
 * 邮件物品
 * @author laofan
 *
 */
public class EmailItemVo {
	
	/**
	 * 物品模板ID
	 */
	private int itemTemplateId;
	
	/**
	 * 物品数量
	 */
	private int count;
	
	/**
	 * 是否绑定
	 */
	private int bind = BindType.BIND;

	
	
	public EmailItemVo(int itemTemplateId, int count, int bind) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.count = count;
		this.bind = bind;
	}

	
	
	public EmailItemVo(int itemTemplateId, int count) {
		super();
		this.itemTemplateId = itemTemplateId;
		this.count = count;
	}



	/**
	 *  获取附件字符串
	 * @return
	 */
	public String attachmentStr(){
		return itemTemplateId+","+count+","+bind + ";";
	}
	
	public int getItemTemplateId() {
		return itemTemplateId;
	}

	public void setItemTemplateId(int itemTemplateId) {
		this.itemTemplateId = itemTemplateId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}
	
	
	

}
