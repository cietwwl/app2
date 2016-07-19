package com.chuangyou.xianni.constant;

public class ChatConstant {

	/**
	 * 频道类型
	 * @author Gxf
	 *
	 */
	public static interface Channel{
		/**
		 * 系统频道
		 */
		public static final short SYSTEM = 1;
		
		/**
		 * 世界频道
		 */
		public static final short WORLD = 2;
		
		/**
		 * 地图频道
		 */
		public static final short SCENE = 3;
		
		/**
		 * 帮派频道
		 */
		public static final short FACTION = 4;
		
		/**
		 * 队伍频道
		 */
		public static final short TEAM = 5;
		
		/**
		 * 组队频道
		 */
		public static final short SEARCH_TEAM = 6;
	}
	
	/**
	 * 各频道发言CD时间(单位：秒)
	 * @author Gxf
	 *
	 */
	public static interface CoolingTime{
		/**
		 * 系统频道
		 */
		public static final short SYSTEM = 0;
		
		/**
		 * 世界频道
		 */
		public static final short WORLD = 45;
		
		/**
		 * 地图频道
		 */
		public static final short SCENE = 30;
		
		/**
		 * 帮派频道
		 */
		public static final short FACTION = 10;
		
		/**
		 * 队伍频道
		 */
		public static final short TEAM = 5;
		
		/**
		 * 组队频道
		 */
		public static final short SEARCH_TEAM = 20;
	}
}
