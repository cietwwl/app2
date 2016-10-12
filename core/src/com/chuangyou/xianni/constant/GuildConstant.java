package com.chuangyou.xianni.constant;

public class GuildConstant {

	public static interface GuildType{
		/**
		 * 玩家门派
		 */
		public static final int PLAYER_GUILD = 1;
		
		/**
		 * 系统门派
		 */
		public static final int SYSTEM_GUILD = 2;
	}
	/**
	 * 门派职位
	 * @author Joseph
	 *
	 */
	public static interface GuildJob{
		/** 门派掌门 */
		public static final int LEADER = 1;
		/** 副掌门 */
		public static final int DEPUTY_LEADER = 2;
		/** 长老 */
		public static final int ELDER = 3;
		/** 护法 */
		public static final int CUSTODIAN = 4;
		/** 精英 */
		public static final int ELITE = 5;
		/** 普通成员 */
		public static final int MEMBER = 6;
	}
	
	/**
	 * 门派加申请后加入条件类型
	 * @author Joseph
	 *
	 */
	public static interface JoinType{
		/** 审核批准后加入 */
		public static final short CHECK_JOIN = 1;
		/** 禁止加入 */
		public static final short BAN = 2;
		/** 满足条件自动加入 */
		public static final short AUTO_CONDITION = 3;
	}
	
	/**
	 * 申请加入帮派失败错误码
	 * @author Joseph
	 *
	 */
	public static interface JoinFailCode{
		
		/** 未知原因 */
		public static final int UNKNOW = 1;
		/** 帮派设置禁止加入 */
		public static final int GUILD_BAN = 2;
		/** 等级未达到帮派设置条件 */
		public static final int LEVEL_UNENOUGH = 3;
		/** 战力未达到帮派设置条件 */
		public static final int FIGHT_UNENOUGH = 4;
		/** 帮派人数已满 */
		public static final int GUILD_FULL = 5;
	}
	/**
	 * 回应申请结果
	 * @author Joseph
	 *
	 */
	public static interface ResponseApplyType{
		
		/** 同步给有权限处理申请的玩家，删除该条申请 */
		public static final int NOTIFY_REMOVE_APPLY = 0;
		
		/** 被接受加入帮派成功 */
		public static final int SELF_ACCEPT = 1;
		/** 自己的申请被拒绝 */
		public static final int SELF_REFUSE = 2;
		/** 接受了申请者的申请 */
		public static final int ACCEPT_APPLY = 3;
		/** 拒绝了申请者 */
		public static final int REFUSE_APPLY = 4;
	}
	
	public class GuildLogType {

		/** 创建帮派 */
		public static final short GUILD_CREATE = 1;
		/** 加入帮派 */
		public static final short GUILD_JOIN = 2;
		/** 捐献仙玉 */
		public static final short GUILD_DONATE_MONEY = 3;
		/** 捐献物品 */
		public static final short GUILD_DONATE_ITEM = 4;
		/** 退出帮派 */
		public static final short GUILD_EXIT = 5;
		/** 踢出帮派 */
		public static final short GUILD_REMOVE = 6;
		/** 成员任命 */
		public static final short GUILD_JOB_APPOINT = 7;
	}
}
