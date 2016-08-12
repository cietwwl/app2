package com.chuangyou.xianni.equip;

public class EquipOperateAction {

	/**
	 * 装备栏操作
	 * @author Joseph
	 *
	 */
	public interface EquipBar{
		/**
		 * 所有栏位信息
		 */
		public static final short ALL_INFOS = 1;
		/**
		 * 栏位升级
		 */
		public static final short LEVEL_UP = 2;
		/**
		 * 栏位加持
		 */
		public static final short GRADE_UP = 3;
	}
	
	/**
	 * 装备操作
	 * @author Joseph
	 *
	 */
	public interface Equip{
		/**
		 * 武器觉醒
		 */
		public static final short AWAKEN = 1;
		
		/**
		 * 装备注魂
		 */
		public static final short STONE = 2;
		
		/**
		 * 装备分解
		 */
		public static final short RESOLVE = 3;
	}
}
