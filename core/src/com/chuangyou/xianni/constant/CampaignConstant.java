package com.chuangyou.xianni.constant;

//副本操作
public class CampaignConstant {

	// ----------------副本操作--------------------//
	/** 获取信息 */
	public static final int	GET_INFO	= 1;
	/** 进入 */
	public static final int	JOIN		= 2;
	/** 退出 */
	public static final int	LEAVE		= 3;
	/** 成员进入队伍所在副本 */
	public static final int	JOIN_TEAM	= 4;

	// ----------------scene副本通知center状态--------------------//
	public static class CampaignStatu {
		public static final int	NOTITY2C_OUT			= 0;	// 退出
		public static final int	NOTITY2C_IN				= 1;	// 进入
		public static final int	NOTITY2C_OUT_SUCCESS	= 2;	// 成功结束退出
		public static final int	NOTITY2C_OUT_FAIL		= 3;	// 失败结束退出
		//public static final int	NOTITY2C_SUCCESS		= 4;	// 副本成功结算
	}

	public static class CampaignType {
		public static final int	SINGLE		= 1;	// 单人本
		public static final int	TEAM		= 2;	// 组队副本
		public static final int	BEAD		= 3;	// 天逆珠副本
		public static final int	CHALLENG	= 4;	// 挑战副本
		public static final int	ARENA		= 5;	// 竞技场副本
		public static final int	STATE		= 6;	// 境界副本
		public static final int	PVP_1V1		= 7;	// PVP11副本
		
		public static final int GUILD_SEIZE	= 8;	// 帮派夺权副本

	}
	
	public static class ChallengeResult{
		/** 赢 */
		public static final int WIN			= 1;
		/** 输 */
		public static final int FAIL		= 0;
		
		/** 挑战未开始，玩家已经在副本中，不能开始挑战 */
		public static final int ALREADY_IN_CAMPAIGN = 2;
		
		/** 挑战未开始，未知错误 */
		public static final int START_FAIL = 3;
	}

}
