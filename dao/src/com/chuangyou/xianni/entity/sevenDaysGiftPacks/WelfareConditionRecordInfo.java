package com.chuangyou.xianni.entity.sevenDaysGiftPacks;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

/*
 * Welcome to use the TableToBean Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 *  
 * Author:bianj
 * Email:edinsker@163.com
 * Version:3.0.0
 */

/**
 * (SEVEN_DAYS_GIFT_PACKAGE)
 * 
 * @author bianj
 * @version 1.0.0 2016-10-26
 */
public class WelfareConditionRecordInfo extends DataObject implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -4351579831999840198L;
    
    /** 人物ID */
    private long playerId;
    
    /** 玩家触发登录天数的时间(从1970年1月1日凌晨到现在的天数) */
    private int time;
    
    /** 登录天数 */
    private int days;
    
    /** 福利新手礼包达到所需等级以后在线时间(单位：秒)*/
    private int onLineTime;
    
    /**
     * 获取人物ID
     * 
     * @return 人物ID
     */
    public long getPlayerId() {
        return this.playerId;
    }
     
    /**
     * 设置人物ID
     * 
     * @param playerId
     *          人物ID
     */
    public void setPlayerId(long playerId) {
    	if (this.playerId != playerId) {
    		this.playerId = playerId;
    		setOp(Option.Update);
    	}
    }
    
    /**
     * 玩家触发登录天数的时间
     * 
     * @return 玩家触发登录天数的时间
     */
    public int getTime() {
        return this.time;
    }
     
    /**
     * 玩家触发登录天数的时间
     */
    public void setTime(int time) {
    	if (this.time != time) {
    		this.time = time;
    		setOp(Option.Update);
		}
    }

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
    	if (this.days != days) {
    		this.days = days;
    		setOp(Option.Update);
    	}
    }

	public int getOnLineTime() {
		return onLineTime;
	}

	public void setOnLineTime(int onLineTime) {
		if (this.onLineTime != onLineTime) {
    		setOp(Option.Update);
    		this.onLineTime = onLineTime;
    	}
	}

    
}