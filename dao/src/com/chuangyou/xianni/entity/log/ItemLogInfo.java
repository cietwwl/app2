package com.chuangyou.xianni.entity.log;

import java.util.Date;

/** 物品日志表 */
public class ItemLogInfo {
	private long	id;				// 日志ID
	private int		templateId;		// 模板ID
	private long	itemId;			// 物品唯一ID
	private long	playerId;		// 玩家ID
	private int		oldCount;		// 旧有数量
	private int		nowCount;		// 修改后数量
	private int		oldPro;			// 装备属性属性
	private int		newPro;			// 装备属性属性
	private int		oldGrow;		// 装备成长系数
	private int		newGrow;		// 装备成长系数
	private int		oldAwaken;		// 武器觉醒等级
	private int		newAwaken;		// 
	private int		oldAwakenPoint;	// 武器觉醒等级突破点
	private int		newAwakenPoint;	//
	private int		oldStone;		// 插入石头模板
	private int		newStone;		//
	private int		changeType;		// 改变类型
	private Date	createDate;		// 创建时间
	
	public ItemLogInfo(){
		this.createDate = new Date();
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getOldCount() {
		return oldCount;
	}

	public void setOldCount(int oldCount) {
		this.oldCount = oldCount;
	}

	public int getNowCount() {
		return nowCount;
	}

	public void setNowCount(int nowCount) {
		this.nowCount = nowCount;
	}

	public int getChangeType() {
		return changeType;
	}

	public void setChangeType(int changeType) {
		this.changeType = changeType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getOldPro() {
		return oldPro;
	}

	public void setOldPro(int oldPro) {
		this.oldPro = oldPro;
	}

	public int getNewPro() {
		return newPro;
	}

	public void setNewPro(int newPro) {
		this.newPro = newPro;
	}

	public int getOldGrow() {
		return oldGrow;
	}

	public void setOldGrow(int oldGrow) {
		this.oldGrow = oldGrow;
	}

	public int getNewGrow() {
		return newGrow;
	}

	public void setNewGrow(int newGrow) {
		this.newGrow = newGrow;
	}

	public int getOldAwaken() {
		return oldAwaken;
	}

	public void setOldAwaken(int oldAwaken) {
		this.oldAwaken = oldAwaken;
	}

	public int getNewAwaken() {
		return newAwaken;
	}

	public void setNewAwaken(int newAwaken) {
		this.newAwaken = newAwaken;
	}

	public int getOldAwakenPoint() {
		return oldAwakenPoint;
	}

	public void setOldAwakenPoint(int oldAwakenPoint) {
		this.oldAwakenPoint = oldAwakenPoint;
	}

	public int getNewAwakenPoint() {
		return newAwakenPoint;
	}

	public void setNewAwakenPoint(int newAwakenPoint) {
		this.newAwakenPoint = newAwakenPoint;
	}

	public int getOldStone() {
		return oldStone;
	}

	public void setOldStone(int oldStone) {
		this.oldStone = oldStone;
	}

	public int getNewStone() {
		return newStone;
	}

	public void setNewStone(int newStone) {
		this.newStone = newStone;
	}

}
