package com.chuangyou.xianni.player.event;

import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;

/**
 * 更新场景可见属性（需要广播给周围玩家）
 * @author Gxf
 *
 */
public class PlayerSceneAttEvent extends ObjectEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int attType;
	
	private int attValue;
	
	public PlayerSceneAttEvent(Object obj, int attType,int value) {
		// TODO Auto-generated constructor stub
		super(obj, null, EventNameType.UPDATE_PLAYER_PROPERTY_SCENE);
		this.attType = attType;
		this.attValue = value;
	}

	public int getAttType() {
		return attType;
	}

	public int getAttValue() {
		return attValue;
	}
	
}
