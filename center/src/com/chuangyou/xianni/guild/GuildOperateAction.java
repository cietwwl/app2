package com.chuangyou.xianni.guild;

public class GuildOperateAction {
	
	/** 获取全服帮派列表 */
	public static final short REQUEST_GUILD_LIST = 1;
	
	/** 获取自己帮派信息 */
	public static final short REQUEST_GUILD_INFO = 2;
	
	/** 获取帮派成员列表 */
	public static final short REQUEST_GUILD_MEMBER = 3;
	
	/** 玩家申请加入帮派 */
	public static final short REQUEST_APPLY_JOIN = 4;
	
	/** 请求申请列表 */
	public static final short REQUEST_APPLY_LIST = 5;
	
	/** 回应申请 */
	public static final short RESPONSE_APPLY = 6;
	
	/** 申请设置 */
	public static final short JOINTYPE_SET = 7;
	
	/** 退出帮派 */
	public static final short GUILD_EXIT = 8;
	
	/** 帮派公告修改 */
	public static final short NOTICE_UPDATE = 9;
	
	/** 踢出门派 */
	public static final short REMOVE_MEMBER = 10;
	
	/** 任命职务 */
	public static final short APPOINT_JOB = 11;
	
	/** 主殿升级 */
	public static final short MAINBUILD_LEVELUP = 12;
	
	/** 蒧经阁升级 */
	public static final short SKILLSHOP_LEVELUP = 13;
	
	/** 商店升级 */
	public static final short SHOP_LEVELUP = 14;
	
	/** 仓库升级 */
	public static final short WAREHOUSE_LEVELUP = 15;
	
	/** 捐献 */
	public static final short GUILD_DONATE = 16;
	
	/** 门派捐献后等级物资改变通知 */
	public static final short GUILD_DONATE_NOTIFY = 17;
	
	/** 门派解散 */
	public static final short GUILD_DISSOLVE = 18;
	
	/** 获取帮派公告 */
	public static final short GUILD_NOTICE_GET = 19;
	
	/** 帮派物资改变广播 */
	public static final short GUILD_SUPPLY_UPDATE = 20;
	
	/** 帮派成员上下线通知 */
	public static final short GUILD_PLAYER_STATE = 21;
	
	/** 批量回应申请 */
	public static final short BAT_RESPONSE_APPLY = 22;
	
	/** 帮贡更新 */
	public static final short CONTRIBUTION_UPDATE = 23;
	
	/** 获取帮派技能列表 */
	public static final short GUILD_SKILL_LIST = 24;
	
	/** 帮派技能升级 */
	public static final short GUILD_SKILL_LEVELUP = 25;
	
	/** 帮派群发邮件 */
	public static final short GUILD_MAIL = 26;
	
	/** 请求帮派日志列表 */
	public static final short GUILD_LOG = 27;
	
	/** 夺权 */
	public static final short GUILD_JOB_SEIZE = 28;
	
	/** 系统门派的成员3天未上线自动退出 */
	public static final short GUILD_SYSTEM_AUTO_EXIT = 29;
	
	/** 请求门派快照 */
	public static final short GUILD_SNAP_REQ = 30;
}
