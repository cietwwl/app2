package com.chuangyou.xianni.entity.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskCfg {

	/** 主线任务 */
	public static final byte MAIN = 1;
	/** 支线任务 */
	public static final byte SUB = 3;
	/** 每日任务 */
	public static final byte DAY = 2;

	private int taskId;
	private String taskName;
	private byte taskType;
	private byte taskLink;
	private short taskLv;
	private int taskPre;
	private int taskNext;
	private int acceptTaskNpcId;
	private String acceptScriptId;
	private byte taskTarget;
	private int targetId;
	private int targetNum;
	private String completeScriptId;
	private int exp;
	private String items;
	private int xiu;
	private long money;
	private int bindCash;
	private int commitNpcId;
	private String commitScriptId;
	private int taskTime;
	/** 任务刷怪坐标点   */
	private String tagPar;
	/**
	 * 任务物品掉落池ID
	 */
	private int dropId;
	

	public Map<Integer, String[]> toItems() {
		Map<Integer, String[]> map = new HashMap<>();

		String[] is = items.split("#");
		for (String str : is) {
			String[] s = str.split(",");
			if (s.length == 3) {
				map.put(Integer.parseInt(s[0]), s);
			}
		}
		return map;
	}

	/**
	 * 获取坐标
	 * @return
	 */
	public ArrayList<Integer> toMapPos(){
		ArrayList<Integer> list = new ArrayList<>();
		String[] is = tagPar.split(",");
		if(is.length == 4){			
			for (String str : is) {
				list.add(Integer.parseInt(str));
			}
		}
		return list;
	}
	
	
	
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public byte getTaskType() {
		return taskType;
	}

	public void setTaskType(byte taskType) {
		this.taskType = taskType;
	}

	public byte getTaskLink() {
		return taskLink;
	}

	public void setTaskLink(byte taskLink) {
		this.taskLink = taskLink;
	}

	public short getTaskLv() {
		return taskLv;
	}

	public void setTaskLv(short taskLv) {
		this.taskLv = taskLv;
	}

	public int getTaskPre() {
		return taskPre;
	}

	public void setTaskPre(int taskPre) {
		this.taskPre = taskPre;
	}

	public int getTaskNext() {
		return taskNext;
	}

	public void setTaskNext(int taskNext) {
		this.taskNext = taskNext;
	}



	public String getAcceptScriptId() {
		return acceptScriptId;
	}

	public void setAcceptScriptId(String acceptScriptId) {
		this.acceptScriptId = acceptScriptId;
	}

	public byte getTaskTarget() {
		return taskTarget;
	}

	public void setTaskTarget(byte taskTarget) {
		this.taskTarget = taskTarget;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public int getTargetNum() {
		return targetNum;
	}

	public void setTargetNum(int targetNum) {
		this.targetNum = targetNum;
	}

	public String getCompleteScriptId() {
		return completeScriptId;
	}

	public void setCompleteScriptId(String completeScriptId) {
		this.completeScriptId = completeScriptId;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public int getXiu() {
		return xiu;
	}

	public void setXiu(int xiu) {
		this.xiu = xiu;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public int getBindCash() {
		return bindCash;
	}

	public void setBindCash(int bindCash) {
		this.bindCash = bindCash;
	}

	

	public String getCommitScriptId() {
		return commitScriptId;
	}

	public void setCommitScriptId(String commitScriptId) {
		this.commitScriptId = commitScriptId;
	}

	public int getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(int taskTime) {
		this.taskTime = taskTime;
	}

	public String getTagPar() {
		return tagPar;
	}

	public void setTagPar(String tagPar) {
		this.tagPar = tagPar;
	}

	public int getAcceptTaskNpcId() {
		return acceptTaskNpcId;
	}

	public void setAcceptTaskNpcId(int acceptTaskNpcId) {
		this.acceptTaskNpcId = acceptTaskNpcId;
	}

	public int getCommitNpcId() {
		return commitNpcId;
	}

	public void setCommitNpcId(int commitNpcId) {
		this.commitNpcId = commitNpcId;
	}

	public int getDropId() {
		return dropId;
	}

	public void setDropId(int dropId) {
		this.dropId = dropId;
	}

}
