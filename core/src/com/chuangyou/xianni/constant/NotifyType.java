package com.chuangyou.xianni.constant;

/**
 * 玩家属性通知类型
 * @author Joseph
 *
 */
public class NotifyType {

	/**
	 * 只通知该玩家
	 */
	public static final byte NOTIFY_USER = 1;
	
	/**
	 * 通知scene服但不广播
	 */
	public static final byte NOTIFY_SCENE = 2;
	
	/**
	 * 通知scene服并广播给附近玩家
	 */
	public static final byte NOTIFY_NEARS = 3;
}
