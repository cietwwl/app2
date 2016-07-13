package com.chuangyou.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

public class ServerConfig {

	private static Map<String, String> configMap;
	private Calendar refreshTime;

	private static int[] castleBuildings = null;
	private static int[] castleBornPos = null;
	private static List<Pair> matchTimes = null;
	private static Pair guildChallengeTime = null;
	private static List<Pair> guildStartTimes = null;
	private static Pair guildWarDay;
	private static Pair guildNewWarTime = null;
	private static Pair guildWarStartTimes = null;
	private static int[] qualitys = null;
	private static int maxQuality = 0;
	private static ConcurrentSkipListMap<Integer, Integer> gesteMap;
	private static List<Pair> matchOfVehicleTimes = null;
	private static Pair vehicleDay;
	private static Date readyOpenDate;
	private static Date readyStopDate;
	private static Date finalOpenDate;
	private static Date finalStopDate;
	private static Pair bottlePrice = null;
	private static Pair mineOfActiveTime;
	private static Pair datiDay;
	private static Date signUpOpenDate;
	private static Date signUpStopDate;

	private static Pair CrossGuildTime;

	private static Pair warOpenMinutes;

	private static Date crossGuildTimeOut;

	public void init(Map<String, String> inputData) {
		configMap = inputData;
		matchTimes = null;
		castleBornPos = null;
		castleBuildings = null;
		qualitys = null;
		guildChallengeTime = null;
		guildStartTimes = null;
		guildWarDay = null;
		guildNewWarTime = null;
		guildWarStartTimes = null;
		readyOpenDate = null;
		finalOpenDate = null;
		readyStopDate = null;
		finalStopDate = null;
		maxQuality = 0;
		refreshTime = Calendar.getInstance();
		vehicleDay = null;
		bottlePrice = null;
		matchOfVehicleTimes = null;
		mineOfActiveTime = null;
		signUpOpenDate = null;
		signUpStopDate = null;
		CrossGuildTime = null;
		warOpenMinutes = null;
		crossGuildTimeOut = null;
	}

	public float sysEdition() {
		return Float.parseFloat(getkey("Sys_Edition", "系统版本号", 0).toString());
	}

	public int initPoint() {
		return Integer.parseInt(getkey("Player_Point", "玩家默认点券", 0).toString());
	}

	public int initCrystals() {
		return Integer.parseInt(getkey("Player_Crystal", "玩家默认紫晶", 0).toString());
	}

	public int initGold() {
		return Integer.parseInt(getkey("Player_Gold", "玩家默认黄金", 0).toString());
	}

	public int initGiftToken() {
		return Integer.parseInt(getkey("Player_GiftToken", "玩家默认礼金", 0).toString());
	}

	public int initResourceMax() {
		return Integer.parseInt(getkey("Player_ResourceMax", "玩家资源上限", 2500).toString());
	}

	public int initCastleOrder() {
		return Integer.parseInt(getkey("Castle_Order", "建筑队列", 2).toString());
	}

	public int initExpOderNeedPay() {
		return Integer.parseInt(getkey("Order_NeedPay", "城堡建堡队扩展需要支付点券或礼金", 100).toString());
	}

	public int initRestSkillNeedPay() {
		return Integer.parseInt(getkey("ResetSkill_NeedPay", "英雄技能洗点需要支付点券或礼金", 200).toString());
	}

	public int getRestSkillFreeGrade() {
		return Integer.parseInt(getkey("ResetSkillFree_Grade", "英雄技能洗点免费等级", 25).toString());
	}

	public short initBagCount() {
		return Short.parseShort(getkey("Player_BagCount", "用户扩展背包格子数", 42).toString());
	}

	public int initPlayerBagPrice() {
		return Integer.parseInt(getkey("Player_BagPrice", "购买领主背包格子价格", 100).toString());
	}

	public int initSmallSpeaker() {
		return Integer.parseInt(getkey("SmallSpeaker", "小喇叭模板", 208003).toString());
	}

	public int initBigSpeaker() {
		return Integer.parseInt(getkey("BigSpeaker", "大喇叭模板", 208002).toString());
	}

	public String initItemBoy() {
		return getkey("Player_ItemBoy", "男性玩家初始化装备", "").toString();
	}

	public String initItemGirl() {
		return getkey("Player_ItemGirl", "女性玩家初始化", "").toString();
	}

	public int[] initCastleBuilding() {
		if (castleBuildings == null) {
			String str = getkey("Castle_Building", "用户初始化建筑", "201,901,1201").toString();
			castleBuildings = SplitUtil.splitToInt(str);
		}
		return castleBuildings;
	}

	public int initPlayerWeary() {
		return Integer.parseInt(getkey("Player_weary", "用户默认最大疲劳值", 200).toString());
	}

	public int initPlayerAttackCount() {
		return Integer.parseInt(getkey("Player_AttackCount", "用户默认攻击次数", 5).toString());
	}

	public short initPlayerBagAdd() {
		return Short.parseShort(getkey("Player_BagAdd", "购买领主格子数量", 7).toString());
	}

	public int initJumpGPAdd() {
		return Integer.parseInt(getkey("Player_JumpGpAdd", "新手跳过获取经验上限", 100).toString());
	}

	public int initJumpItemAdd(int job) {
		String items = getkey("Player_JumpItemAdd", "新手跳过获得物品", "1,2100018|2,2100018|3,2100018").toString();
		String[] heroItems = items.split("\\|");
		for (String set : heroItems) {
			int[] tempIds = SplitUtil.splitToInt(set, ",");
			if (tempIds.length == 2) {
				if (tempIds[0] == job) {
					return tempIds[1];
				}
			}
		}
		return 0;
	}

	public int initHonorTemplate() {
		return Integer.parseInt(getkey("Item_Honor", "勋章模板", 208001).toString());
	}

	public int initMazeCoinTemplate() {
		return Integer.parseInt(getkey("Item_MazeCoin", "迷宫硬币", 208009).toString());
	}

	public int initConsortiaCreateItemTemplate() {
		return Integer.parseInt(getkey("Item_ConsortiaCreate", "创建公会物品", 208010).toString());
	}

	public int initConsortiaClearCDItemTemplate() {
		return Integer.parseInt(getkey("Item_ConsortiaClearCD", "清除退出公会CD物品", 208013).toString());
	}

	public int initLootResAddation() {
		return Integer.parseInt(getkey("LootRes_Addation", "掠夺资源比例", 10).toString());
	}

	public int initConsortiaNeedGrades() {
		return Integer.parseInt(getkey("Consortia_Grade", "创建公会所需等级", 13).toString());
	}

	public int intConsortiaJoinGrades() {
		return Integer.parseInt(getkey("Consortia_Join", "加入公会", 13).toString());
	}

	public int initConsortiaNeedGold() {
		return Integer.parseInt(getkey("Consortia_Gold", "创建公会所需黄金", 50000).toString());
	}

	public int initConsortiaClearCDNeedPoint() {
		return Integer.parseInt(getkey("Consortia_ClearCD_Point", "清除退出公会CD", 50).toString());
	}

	public int initConsortiaRenamePoint() {
		return Integer.parseInt(getkey("Consortia_Rename", "公会改名", 1000).toString());
	}

	public int initConsortiaChangeCount() {
		return Integer.parseInt(getkey("Consortia_Change", "机器人公会转让人数限制", 20).toString());
	}

	public String initConsortiaGood() {
		return getkey("Consortia_Good", "公会祈福一定存在的位置", "").toString();
	}

	public int initConsoritDropCount() {
		return Integer.parseInt(getkey("Consortia_Altar", "公会祈福位置个数", 10).toString());
	}

	public int initConsortiaMemberCount() {
		return Integer.parseInt(getkey("Consortia_Member", "公会等级人数", 20).toString());
	}

	/**
	 * 开启公会祭坛所需消耗的贡献值
	 * 
	 * @param altarType
	 * @return
	 */
	public int initConsortiaOpenAltarOffer(int altarType) {
		switch (altarType) {
		case 0:
			return Integer.parseInt(getkey("Consortia_OpenAltar1_Offer", "开启公会祭坛一所需消耗的贡献值", 50000).toString());
		case 1:
			return Integer.parseInt(getkey("Consortia_OpenAltar2_Offer", "开启公会祭坛二所需消耗的贡献值", 200000).toString());
		default:
			return 0;
		}
	}

	public int initCampaignNeedWeary() {
		return Integer.parseInt(getkey("SingleCampaign_Weary", "进入战役所需疲劳值", 20).toString());
	}

	public int initHeroSkillCount() {
		return Integer.parseInt(getkey("Hero_SkillCount", "英雄技能数", 8).toString());
	}

	public int initHeroMaxPassive() {
		return Integer.parseInt(getkey("Hero_MaxPassive", "英雄主动技能数", 5).toString());
	}

	public int initHeroSkillPrice() {
		return Integer.parseInt(getkey("Hero_SkillPrice", "英雄技能购买价格", 10).toString());
	}

	public int initHeroHaveCount() {
		return Integer.parseInt(getkey("Hero_EngageCount", "领主拥有英雄数量", 6).toString());
	}

	public int initHeroEngagePrice() {
		return Integer.parseInt(getkey("Hero_EngagePrice", "购买英雄格子价格", 20).toString());
	}

	public int initStarBagPrice() {
		return Integer.parseInt(getkey("Star_BagPrice", "购买星格价格", 100).toString());
	}

	public short initPlayerStarAdd() {
		return Short.parseShort(getkey("Star_BagAdd", "购买星格格子数量", 6).toString());
	}

	private int initPlayerStarPoint0() {
		return Integer.parseInt(getkey("StarPoint0", "占第一个星获得的星运积分", 1).toString());
	}

	private int initPlayerStarPoint1() {
		return Integer.parseInt(getkey("StarPoint1", "占第二个星获得的星运积分", 2).toString());
	}

	private int initPlayerStarPoint2() {
		return Integer.parseInt(getkey("StarPoint2", "占第三个星获得的星运积分", 3).toString());
	}

	private int initPlayerStarPoint3() {
		return Integer.parseInt(getkey("StarPoint3", "占第四个星获得的星运积分", 4).toString());
	}

	private int initPlayerStarPoint4() {
		return Integer.parseInt(getkey("StarPoint4", "占第五个星获得的星运积分", 5).toString());
	}

	public int initPlayerDayGesteGet() {
		return Integer.parseInt(getkey("DayAddGeste", "每日获取荣誉上限", 3000).toString());
	}

	/**
	 * 荣誉等级上限
	 * 
	 * @param grade 用户等级
	 * @return
	 */
	public int initGesteUpperLimit(int grade) {
		int data = 100;
		if (gesteMap == null) {
			String limit = getkey("Geste_UpperLimit", "荣誉等级上限", "39,2700|49,8500|54,15700|59,25300|269,32000").toString();
			String[] limits = limit.split("\\|");
			gesteMap = new ConcurrentSkipListMap<Integer, Integer>();
			for (int i = 0; i < limits.length; i++) {
				String tempStr = limits[i];
				if (tempStr == null || tempStr.equals(""))
					continue;
				String[] tempStrs = tempStr.split(",");
				if (tempStrs.length != 2)
					continue;
				try {
					int key = Integer.parseInt(tempStrs[0]);
					int value = Integer.parseInt(tempStrs[1]);
					gesteMap.put(key, value);
				} catch (Exception e) {
					Log.error("Geste Config Error:", e);
				}
			}
		}
		if (gesteMap == null || gesteMap.size() < 1)
			return data;
		for (Entry<Integer, Integer> entry : gesteMap.entrySet()) {
			if (grade <= entry.getKey() && data <= entry.getValue()) {
				data = entry.getValue();
				break;
			}
		}
		return data;
	}

	/**
	 * 获取占星的积分
	 * 
	 * @param site
	 * @return
	 */
	public int getPlayerStarPoint(int site) {
		int integral = 0;

		switch (site) {
		case 0:
			integral = initPlayerStarPoint0();
			break;

		case 1:
			integral = initPlayerStarPoint1();
			break;

		case 2:
			integral = initPlayerStarPoint2();
			break;

		case 3:
			integral = initPlayerStarPoint3();
			break;

		case 4:
			integral = initPlayerStarPoint4();
			break;
		}
		return integral;
	}

	public int initSendMailNeedGold() {
		return Integer.parseInt(getkey("Mail_NeedGold", "发送邮件默认扣除黄金", 100).toString());
	}

	public int initSendMailNeedPoint() {
		return Integer.parseInt(getkey("Mail_NeedPoint", "群发邮件需要砖石", 50).toString());
	}

	public int initInlayJoinOpen() {
		return Integer.parseInt(getkey("Inlay_JoinOpen", "镶嵌开孔扣除点券", 1000).toString());
	}

	public int initInlayJoin() {
		return Integer.parseInt(getkey("Inlay_Join", "镶嵌扣除黄金", 1000).toString());
	}

	public int initInlayOut() {
		return Integer.parseInt(getkey("Inlay_JoinOut", "镶嵌拆除宝石扣除点券", 20).toString());
	}

	// /**
	// * 此值为true表示开启，false表示关闭
	// * @return 已不用
	// */
	// public boolean initAASOpen() {
	// return Boolean.parseBoolean(getkey("AASOpen", "防沉迷是否打开", false).toString());
	// }

	public float initAASGrade1() {
		return Float.parseFloat(getkey("AASGrade1", "防沉迷第一等级", 180).toString());
	}

	public float initAASGrade2() {
		return Float.parseFloat(getkey("AASGrade2", "防沉迷第二等级", 300).toString());
	}

	public Date initAASRefresh() {
		int hour = Integer.parseInt(getkey("AASRefresh", "防沉迷刷新时间", 18).toString());
		if (!TimeUtil.dataCompare5(refreshTime.getTime()) || refreshTime.get(Calendar.HOUR_OF_DAY) != hour || refreshTime.get(Calendar.MINUTE) != 0 || refreshTime.get(Calendar.SECOND) != 0) {
			TimeUtil.setAASRefreshTime(hour, refreshTime);
		}
		return refreshTime.getTime();
	}

	/**
	 * 默认受保护时间(秒)
	 * 
	 * @return
	 */
	public int initDefenceSecTime() {
		return Integer.parseInt(getkey("Defence_Time", "默认受保护时间", 7200).toString());
	}

	public int initNewWroldDefenceSecTime() {
		return Integer.parseInt(getkey("NewWroldDefence_Time", "新手岛默认受保护时间", 712800).toString());
	}

	public int initAltrCount() {
		return Integer.parseInt(getkey("Altar_Count", "祭坛祈福基础次数", 100).toString());
	}

	public int initChallengeTotalCount() {
		return Integer.parseInt(getkey("ChallengeTotalCount", "挑战次数", 10).toString());
	}

	public int initChallengeTotalBuyCount() {
		return Integer.parseInt(getkey("ChallengeTotalBuyCount", "可购买的最大挑战次数", 10).toString());
	}

	public int initStarPickGold(int site) {
		String gold = "StarPickGold" + site;
		return Integer.parseInt(getkey(gold, "位置占星消耗黄金", 100).toString());
	}

	public String initDefaultCompose() {
		return getkey("Compose", "默认合成公式", "").toString();
	}

	public int[] initCastleBornPos() {
		if (castleBornPos == null) {
			String str = getkey("CastleBornPos", "新手岛玩家城堡出现坐标", "26,46").toString();
			castleBornPos = SplitUtil.splitToInt(str);
		}
		return castleBornPos;
	}

	/**
	 * 传送阵能量值,祭坛祈福次数,领主体力值等重置时间点
	 * 
	 * @return
	 */
	public int initResetHourTime() {
		return Integer.parseInt(getkey("Altar_Count", "部分数据重置时间", 5).toString());
	}

	public int initGiveCastleGrade() {
		return Integer.parseInt(getkey("GiveCastle_Grade", "分配城堡所需用户等级", 11).toString());
	}

	public List<Pair> initMatchTime() {
		if (matchTimes == null) {
			matchTimes = new ArrayList<Pair>();
			String str = getkey("MatchTime", "撮合战斗分配时间", "11:53,11:58|12:00,12:10").toString();
			String[] times = str.split("\\|");
			for (String time : times) {
				String[] timeSet = time.split(",");
				int[] openTime = SplitUtil.splitToInt(timeSet[0], ":");
				int[] stopTime = SplitUtil.splitToInt(timeSet[1], ":");
				matchTimes.add(new Pair(openTime[0], openTime[1], stopTime[0], stopTime[1]));
			}
		}
		return matchTimes;
	}

	public List<Pair> initMatchOfVehicleTime() {
		if (matchOfVehicleTimes == null) {
			matchOfVehicleTimes = new ArrayList<Pair>();
			String str = getkey("MatchOfVehicleTime", "撮合载具战斗分配时间", "1:00,14:59|13:00,23:59").toString();
			String[] times = str.split("\\|");
			for (String time : times) {
				String[] timeSet = time.split(",");
				int[] openTime = SplitUtil.splitToInt(timeSet[0], ":");
				int[] stopTime = SplitUtil.splitToInt(timeSet[1], ":");
				matchOfVehicleTimes.add(new Pair(openTime[0], openTime[1], stopTime[0], stopTime[1]));
			}
		}
		return matchOfVehicleTimes;
	}

	public int[] initQualitys() {
		if (qualitys == null) {
			String str = getkey("Qualitys", "悬赏品质", "1,2,3,4,5").toString();
			qualitys = SplitUtil.splitToInt(str);
		}
		return qualitys;
	}

	public int maxQuality() {
		if (maxQuality == 0) {
			int tempQuality = 0;
			for (int i : qualitys) {
				if (tempQuality < i)
					tempQuality = i;
			}
			maxQuality = tempQuality;
		}
		return maxQuality;
	}

	public int getActiveHeroSkill(int job) {
		String heroSkillSet = getkey("HeroSkillSet", "新手5级时自动激活技能", "1,10201|2,20201|3,30301").toString();
		String[] skillSet = heroSkillSet.split("\\|");
		for (String set : skillSet) {
			int[] tempIds = SplitUtil.splitToInt(set, ",");
			if (tempIds.length == 2) {
				if (tempIds[0] == job) {
					return tempIds[1];
				}
			}
		}
		return 0;
	}

	public int getLiveNeedPay() {
		return Integer.parseInt(getkey("Live_Pay", "世界BOSS战复活所需点卷或礼金", 2).toString());
	}

	public int getLiveFightNeedPay() {
		return Integer.parseInt(getkey("Live_Fight_Pay", "立即进入战斗所需点卷或礼金", 5).toString());
	}

	public Object getkey(String name, String desc, Object defaultValue) {
		if (configMap != null) {
			return configMap.get(name) != null ? configMap.get(name) : defaultValue;
		}
		return defaultValue;
	}

	public int getEnteTowerGrade() {
		return Integer.parseInt(getkey("Tower_Grade", "进入无限塔最低等级", 12).toString());
	}

	public int getEnterMatchGrade() {
		return Integer.parseInt(getkey("Match_Grade", "进入撮合最低等级", 22).toString());
	}

	public int getEnterMatchOfVehicleGrade() {
		return Integer.parseInt(getkey("MatchOfVehicle_Grade", "进入撮合最低等级", 35).toString());
	}

	public int getEnterMutilCampGrade() {
		return Integer.parseInt(getkey("MutilCamp_Grade", "进入多人本最低等级", 20).toString());
	}

	public int getEnterChallGrade() {
		return Integer.parseInt(getkey("Challenge_Grade", "进入离线挑战最低等级", 16).toString());
	}

	public int getEnterWorldBossGrade() {
		return Integer.parseInt(getkey("World_Grade", "进入世界BOSS最低等级", 20).toString());
	}

	public int getAttackLimitGrade() {
		return Integer.parseInt(getkey("Attack_Grade", "攻击最低等级", 6).toString());
	}

	public int getAddSkillPointGrade() {
		return Integer.parseInt(getkey("AddSkill_Grade", "开始加技能点最低等级", 6).toString());
	}

	public int getStartChallengeOrderGrade() {
		return Integer.parseInt(getkey("ChallengeOrder_Grade", "离线挑战纪录排名的起始等级", 10).toString());
	}

	public int getChllangeMapId() {
		return Integer.parseInt(getkey("Challenge_MapId", "挑战赛战斗场景地图ID", 992).toString());
	}

	public int getAttackCastleMapId() {
		return Integer.parseInt(getkey("AttackCastle_MapId", "攻城战战斗场景地图ID", 993).toString());
	}

	public int getTowerLivePoint() {
		return Integer.parseInt(getkey("Tower_Live", "无限塔复活所需钻石", 5).toString());
	}

	public int getAddAttckPoint() {
		return Integer.parseInt(getkey("AddAttck_Point", "增加攻击城堡次数所需钻石", 98).toString());
	}

	public int getCanLuckyGrade() {
		return Integer.parseInt(getkey("Lucky_Grades", "可以炼金的等级", 14).toString());
	}

	public int getLuckyCount() {
		return Integer.parseInt(getkey("Lucky_Count", "默认炼金次数", 20).toString());
	}

	public int getLuckyGoldMut() {
		return Integer.parseInt(getkey("Lucky_GoldMut", "炼金黄金兑换倍数", 1000).toString());
	}

	public int getCanRefiningSoulGrade() {
		return Integer.parseInt(getkey("RefiningSoul_Grades", "可以炼魂的等级", 24).toString());
	}

	public int getRefiningSoulMaxCount() {
		return Integer.parseInt(getkey("RefiningSoul_Count", "默认炼魂次数", 20).toString());
	}

	public int getRefiningSoulMut_ArmyHouse() {
		return Integer.parseInt(getkey("RefiningSoul_ArmyHouseMut", "炼魂公式：兵营等级倍数", 500).toString());
	}

	public int getRefiningSoulMut_People() {
		return Integer.parseInt(getkey("RefiningSoul_PeopleMut", "炼魂公式：居民等级倍数", 500).toString());
	}

	// -------农场 start-------
	public int getFarmCrop_Mature_Percent() {
		return Integer.parseInt(getkey("FarmCrop_Mature_Percent", "农作物成熟时间比率", 3).toString());
	}

	public int getFarmCrop_Gains_Percent() {
		return Integer.parseInt(getkey("FarmCrop_Gains_Percent", "农作物成熟奖励比率", 5).toString());
	}

	public int getFarmCrop_Operate_GP() {
		return Integer.parseInt(getkey("FarmCrop_Operate_GP", "驱虫除草获取的经验", 1).toString());
	}

	public int getFarm_TodayFromFriend_GP() {
		return Integer.parseInt(getkey("Farm_TodayFromFriend_GP", "每天能从好友那获取的经验上限", 100).toString());
	}

	public int getFarm_TodayStolen_Count() {
		return Integer.parseInt(getkey("Farm_TodayStolen_Count", "每天偷取次数上限", 100).toString());
	}

	public int getFarm_Accelerate_Point() {
		return Integer.parseInt(getkey("Farm_Accelerate_Point", "作物加速所需钻石", 10).toString());
	}

	// -------农场 end-------

	public int getMatchMedalWinCount() {
		return Integer.parseInt(getkey("MatchMedal_WinCount", "竞技胜利成员获得勋章数", 3).toString());
	}

	public int getMatchMedalFailedCount() {
		return Integer.parseInt(getkey("MatchMedal_FailedCount", "竞技失败成员获得勋章数", 1).toString());
	}

	public int getMultiCampaignMaxCount() {
		return Integer.parseInt(getkey("MultiCampaign_MaxCount", "多人副本进入次数", 3).toString());
	}

	public int getMatchRoomMaxCount() {
		return Integer.parseInt(getkey("MatchRoom_MaxCount", "竞技场进入次数", 10).toString());
	}

	public int getTrialMaxCount() {
		return Integer.parseInt(getkey("MatchTrial_MaxCount", "试炼之塔进入次数", 1).toString());
	}

	public int getMaxEnergy() {
		return Integer.parseInt(getkey("Energy_Max", "最大能量", 500).toString());
	}

	public int getCastleTransNeedEnergy() {
		return Integer.parseInt(getkey("Trans_Energy", "本地图传送所需能量", 100).toString());
	}

	public int getCastleChangMapTransNeedEnergy() {
		return Integer.parseInt(getkey("ChangeTrans_Energy", "跨地图传送所需能量", 200).toString());
	}

	public int getAddEnergyNeedPay() {
		return Integer.parseInt(getkey("AddEnergy_Pay", "充能所需支付", 10).toString());
	}

	public int getActiveGoldProdGrade() {
		return Integer.parseInt(getkey("ActiveGold_Grade", "黄金征收等级", 14).toString());
	}

	public int getImporseMaxCount() {
		return Integer.parseInt(getkey("GoldImporse_Count", "黄金征收最大次数", 5).toString());
	}

	public int getCastleFightGradeDis() {
		return Integer.parseInt(getkey("CastleFight_GradeDis", "攻城战等级差", 10).toString());
	}

	public int getArmyPositionNeedEnergy() {
		return Integer.parseInt(getkey("Position_Energy", "部队定点传送所需能量 ", 100).toString());
	}

	public int initWaterCrystal() {
		return Integer.parseInt(getkey("Player_WaterCrystal", "领主默认光晶数量 ", 500).toString());
	}

	public int getRemoveWorldMapDay() {
		return Integer.parseInt(getkey("RemoveMap_Day", "未登陆天数移至新手岛 ", 7).toString());
	}

	public int getRemoveWorldMapBeyondPersent() {
		return Integer.parseInt(getkey("RemoveMap_Persent", "清理大地图百分比条件", 80).toString());
	}

	public int getEnterWarGrades() {
		return Integer.parseInt(getkey("War_Grades", "进入战场最低等级", 30).toString());
	}

	public int initItemResolve() {
		return Integer.parseInt(getkey("Item_Resolve", "物品分解", 1000).toString());
	}

	public int getPawnSpecialGrade() {
		return Integer.parseInt(getkey("Special_Grades", "兵种特性激活等级", 10).toString());
	}

	public int getPawnSpecialPlayerGrade() {
		return Integer.parseInt(getkey("Special_PlayerGrades", "兵种特性拥有所需玩家等级", 40).toString());
	}

	public int getComprehendCostStretgy() {
		return Integer.parseInt(getkey("Special_Stretgy", "领悟特性所需战魂", 1000000).toString());
	}

	public Pair getGuildChallengeTime() {
		if (guildChallengeTime == null) {
			String str = getkey("GuildChallengeTime", "工会战报名时间", "11,18").toString();
			int[] hours = SplitUtil.splitToInt(str);
			guildChallengeTime = new Pair(hours[0], hours[1]);
		}
		return guildChallengeTime;
	}

	public int getGuildChallengeNeedOffer() {
		return Integer.parseInt(getkey("Challenge_NeedOffer", "挑战公会所需贡献", "50000").toString());
	}

	public List<Pair> getGuildStartTimes() {
		if (guildStartTimes == null) {
			guildStartTimes = new ArrayList<Pair>();
			String str = getkey("GuildStart_Times", "公会战开始时间", "10,18|18,22").toString();
			String[] times = str.split("\\|");
			for (String time : times) {
				int[] hours = SplitUtil.splitToInt(time);
				guildStartTimes.add(new Pair(hours[0], hours[1]));
			}
		}
		return guildStartTimes;
	}

	public String getWorldBossWound(int site) {
		String wound = "WorldBoss_Wound" + site;
		return getkey(wound, "世界Boss伤害累计奖励", 1000).toString();
	}

	public int getWorldBossBufferGiftPrice() {
		return Integer.parseInt(getkey("WorldBoss_Buffer_Gift_Price", "世界bossBuff价格（礼金）", "10").toString());
	}

	public int getWorldBossBufferDiamondPrice() {
		return Integer.parseInt(getkey("WorldBoss_Buffer_Diamond_Price", "世界bossBuffer价格（钻石）", "10").toString());
	}

	public int getFamBufferPrice() {
		return Integer.parseInt(getkey("Fam_Buffer_Price", "秘境Buffer价格（贡献）", "10").toString());
	}

	public int getSecondSkillGrades() {
		return Integer.parseInt(getkey("Second_SkillGrades", "激活二套所需玩家等级", "38").toString());
	}

	public int getSecondSkillNeedPoint() {
		return Integer.parseInt(getkey("Second_SkillPoint", "激活二套所需砖石", "500").toString());
	}

	public int getShopBuyMaxCount() {
		return Integer.parseInt(getkey("ShopBuy_MaxCount", "神秘商店购买次数上限", "20").toString());
	}

	public int getShopFreshNeedDiamond() {
		return Integer.parseInt(getkey("ShopFresh_NeedDiamond", "神秘商店刷新所需钻石", "2").toString());
	}

	public int getReplacementPrice() {
		return Integer.parseInt(getkey("WorldBoss_Replacement_Price", "世界boss替身购买价格", "200").toString());
	}

	public int getFamTreeWaterMax() {
		return Integer.parseInt(getkey("FamTree_WaterMax", "秘境神树充能上限", "30").toString());
	}

	public int getFamGain() {
		return Integer.parseInt(getkey("Fam_Gain", "秘境收益", "1000").toString());
	}

	public String getKeyUrl() {
		// 240测试地址
		return getkey("KEY_URL", "验证Key", "http://10.10.4.28:8080/test").toString();
	}

	public int getVipDailyAddGp() {
		return Integer.parseInt(getkey("Vip_DailyAddGp", "", "10").toString());
	}

	public int getVipGoldDailyAddGp() {
		return Integer.parseInt(getkey("Vip_GoldDailyAddGp", "", "15").toString());
	}

	public int getVipExpirtDailyMinusGp() {
		return Integer.parseInt(getkey("Vip_ExpirtDailyMinusGp", "", "5").toString());
	}

	public float getVipConsumeGp() {
		return Float.parseFloat(getkey("Vip_ConsumeGp", "", "0.01").toString());
	}

	public float getVipYearReduce() {
		return Float.parseFloat(getkey("Vip_YearReduce", "", "0.88").toString());
	}

	public int getOneMonthPoint() {
		return Integer.parseInt(getkey("One_MonthPoint", "", "298").toString());
	}

	public int getThreeMonthPoint() {
		return Integer.parseInt(getkey("Three_MonthPoint", "", "868").toString());
	}

	public int getSixMonthPoint() {
		return Integer.parseInt(getkey("Six_MonthPoint", "", "1588").toString());
	}

	public int getOpenVipRouletteGrade() {
		return Integer.parseInt(getkey("Roulette_Grade", "", "6").toString());
	}

	public int getCrossWarProfitCount() {
		return Integer.parseInt(getkey("CrossWarProfitCount", "2", "2").toString());
	}

	public int getTalentMaxPlayerGrade() {
		return Integer.parseInt(getkey("Talent_MaxPlayerGrade", "70", "100").toString());
	}

	public int getTalentPlayerUpgrade() {
		return Integer.parseInt(getkey("Talent_PlayerUpgrade", "2", "100").toString());
	}

	public int getTalentLiquidDailyCount() {
		return Integer.parseInt(getkey("Talent_LiquidDailyCount", "2", "100").toString());
	}

	public int getBlessingGrade() {
		return Integer.parseInt(getkey("Blessing_Grade", "默认祝福等级", 41).toString());
	}

	public int getBlessingBuffer() {
		return Integer.parseInt(getkey("Blessing_Buffer", "默认祝福效果", 10).toString());
	}

	public int getBlessingCast() {
		return Integer.parseInt(getkey("Blessing_Cast", "默认祝福光晶消耗", 2000).toString());
	}

	public int getBlessingItemId() {
		return Integer.parseInt(getkey("Blessing_ItemId", "默认祝福消耗品ID", 1000).toString());
	}

	public int getBlessingMaxBuff() {
		return Integer.parseInt(getkey("Blessing_Max", "默认祝福最大效果", 200).toString());
	}

	public int getCheckMaxScore() {
		return Integer.parseInt(getkey("Check_MaxScore", "弹出验证码最大积分", 80).toString());
	}

	public int getRewardGrade() {
		return Integer.parseInt(getkey("Reward_Grade", "默认悬赏等级", 17).toString());
	}

	public int getMountEditPoint() {
		return Integer.parseInt(getkey("Mount_Point", "普通驯养一次所需点卷", 10).toString());
	}

	public int getMountStarPoint() {
		return Integer.parseInt(getkey("Mount_Star_Point", "坐骑升星一次点券", 10).toString());
	}

	public int getAllKingContractPoint() {
		return Integer.parseInt(getkey("All_KingContract", "购买全部霸王契约所需钻石", 60).toString());
	}

	// --------副本扫荡 start--------
	public int getTowerSweepTime() {
		return Integer.parseInt(getkey("Tower_Sweep_Time", "迷宫扫荡时间/层", 60).toString());
	}

	public int getTowerSweepGold() {
		return Integer.parseInt(getkey("Tower_Sweep_Gold", "迷宫扫荡所需金钱/层)", 2000).toString());
	}

	public int getCampaignSweepTime() {
		return Integer.parseInt(getkey("Campaign_Sweep_Time", "副本扫荡时间", 300).toString());
	}

	public int getCampaignSweepGold() {
		return Integer.parseInt(getkey("Campaign_Sweep_Gold", "副本扫荡所需金钱/次", 2000).toString());
	}

	// --------副本扫荡 end--------

	public Pair getGuildWarDay() {
		if (guildWarDay == null) {
			String str = getkey("Guild_WarDay", "公会战排期时间", "1,3,5").toString();
			int[] daySet = SplitUtil.splitToInt(str, ",");
			guildWarDay = new Pair(daySet[0], daySet[1], daySet[2]);
		}
		return guildWarDay;
	}

	public Pair getNewWarDayOfweek() {
		if (guildNewWarTime == null) {
			String str = getkey("Guild_NewWarTime", "新一轮公会战召集第几天,小时", "7,5").toString();
			int[] hours = SplitUtil.splitToInt(str);
			guildNewWarTime = new Pair(hours[0], hours[1]);
		}
		return guildNewWarTime;
	}

	public Pair getGuildWarStartTimes() {
		if (guildWarStartTimes == null) {
			String str = getkey("GuildWar_Time", "公会战开始时间", "20:00,21:00").toString();
			String[] times = str.split(",");
			int[] start = SplitUtil.splitToInt(times[0], ":");
			int[] stop = SplitUtil.splitToInt(times[1], ":");
			guildWarStartTimes = new Pair(start[0], start[1], stop[0], stop[1]);
		}
		return guildWarStartTimes;
	}

	public int getOpenStarGrade() {
		return Integer.parseInt(getkey("OpenStar_Grade", "占星开放等级", 18).toString());
	}

	public String getAasUrl() {
		return getkey("AAS_url", "防沉迷URL", "http://fcm.ddshenqu.cn").toString();
	}

	public int getBombNpcDecHp() {
		return Integer.parseInt(getkey("Bomb_DecHp", "炸弹人减血", 2400000).toString());
	}

	public int getDecHpinterval() {
		return Integer.parseInt(getkey("Inv_DecHp", "30秒减血", 50000).toString());
	}

	public int getMainNodeAddHp() {
		return Integer.parseInt(getkey("Buffer_AddHp", "技能回血", 7200000).toString());
	}

	public int getAltarLiveNeedOffer() {
		return Integer.parseInt(getkey("AltarLive_Offer", "魔神祭坛复活所需贡献", 50).toString());
	}

	public String getTrialSkill() {
		return getkey("TrialSkill", "试炼技能BUFF", "").toString();
	}

	public int getPawnChangeNeedStrategy() {
		return Integer.parseInt(getkey("PawnChange_NeedStrategy", "兵种特性替换所需战魂", 2000000).toString());
	}

	public int getPawnChangeNeedPoint() {
		return Integer.parseInt(getkey("PawnChange_NeedPoint", "兵种特性替换所需钻石", 200).toString());
	}

	public int getWarFieldMaxCount() {
		return Integer.parseInt(getkey("WarField_MaxCount", "跨服战场进入次数", 2).toString());
	}

	public int getVehicleEnterMaxCount() {
		return Integer.parseInt(getkey("VehicleEnterMaxCount", "当天载具副本可进入最多次数", 1).toString());
	}

	public int getVehicleMaxWaveNum() {
		return Integer.parseInt(getkey("VehicleMaxWaveNum", "当天载具最大出怪波数", 20).toString());
	}

	public List<String> getVehicleWaveNpcList() {
		int waveNum = getVehicleMaxWaveNum();
		List<String> npcList = new ArrayList<String>();
		for (int i = 0; i < waveNum; i++) {
			String key = getkey("VehicleWaveNpcConfig" + (i + 1), "载具波数怪配置", "").toString();
			if ("".equals(key)) {
				continue;
			}
			npcList.add(key);
		}
		return npcList;
	}

	public int getReviveTrialPoint() {
		return Integer.parseInt(getkey("ReviveTrial", "试炼复活一次所需点卷", 20).toString());
	}

	public int getConsortiaAltarBufferNeedOffer() {
		return Integer.parseInt(getkey("ConsortiaAltarBufferNeedOffer", "购买魔神祭坛Buff所需贡献", "50").toString());
	}

	public int getConsortiaAltarSceneNeedOffer(int sceneType) {
		switch (sceneType) {
		case 1:
			return Integer.parseInt(getkey("ConsortiaAltarSceneNeedOffe_Fix", "魔神祭坛全屏怪定身所需公会财富", "10000").toString());
		case 2:
			return Integer.parseInt(getkey("ConsortiaAltarSceneNeedOffe_Live", "魔神祭坛恢复祭坛血量公会财富", "10000").toString());
		default:
			return 0;
		}
	}

	public int getConsortiaAltarSceneLive() {
		return Integer.parseInt(getkey("ConsortiaAltarSceneLive", "魔神祭坛祭坛恢复血量值", "20").toString());
	}

	public int getDampGrade() {
		return Integer.parseInt(getkey("Damp_Grade", "大于等于该等级时副本获得经验不衰减", "70").toString());
	}

	public int getOpenRuneGrade() {
		return Integer.parseInt(getkey("OpenRuneGrade", "开放符文系统等级", "35").toString());
	}

	public int getOpenTalentGrade() {
		return Integer.parseInt(getkey("OpenTalentGrade", "开放天赋系统等级", "50").toString());
	}

	public int getCarryTwoRuneGrade() {
		return Integer.parseInt(getkey("CarryTwoRune", "携带两种符文等级", "40").toString());
	}

	public int getAddWearyLimit() {
		return Integer.parseInt(getkey("Weary_Limit", "每天使用药水最多可恢复的体力", "300").toString());
	}

	public int getSmallBugleFreeCount() {
		return Integer.parseInt(getkey("SMALL_BUGLE_FREE_COUNT", "小喇叭免费使用次数", 10).toString());
	}

	public int getRuneSwallowMaxCount() {
		return Integer.parseInt(getkey("RuneSwallowMax", "每天符文吞噬的最大符文书", "1000").toString());
	}

	public Date getReadyOpenDate() {
		if (readyOpenDate != null) {
			return readyOpenDate;
		}
		String time = getkey("ReadyOpenDate", "众神之战预赛开启时间", "2012-12-06 19:20:00").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			readyOpenDate = dateformat.parse(time);
			return readyOpenDate;
		} catch (Exception e) {
			Log.error("众神之战时间格式转化错误, time : " + time, e);
			return null;
		}
	}

	public Date getReadyStopDate() {
		if (readyStopDate != null) {
			return readyStopDate;
		}
		String time = getkey("ReadyStopDate", "众神之战预赛结束时间", "2012-12-06 21:30:00").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			readyStopDate = dateformat.parse(time);
			return readyStopDate;
		} catch (Exception e) {
			Log.error("众神之战时间格式转化错误, time : " + time, e);
			return null;
		}
	}

	public Date getFinalOpenDate() {
		if (finalOpenDate != null) {
			return finalOpenDate;
		}
		String time = getkey("FinalOpenDate", "众神之战决赛开启时间", "2012-12-06 21:35:00").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			finalOpenDate = dateformat.parse(time);
			return finalOpenDate;
		} catch (Exception e) {
			Log.error("众神之战时间格式转化错误, time : " + time, e);
			return null;
		}
	}

	public Date getFinalStopDate() {
		if (finalStopDate != null) {
			return finalStopDate;
		}
		String time = getkey("FinalStopDate", "众神之战决赛结束时间", "2012-12-06 21:40:00").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			finalStopDate = dateformat.parse(time);
			return finalStopDate;
		} catch (Exception e) {
			Log.error("众神之战时间格式转化错误, time : " + time, e);
			return null;
		}
	}

	public String getFinalOpenDateString() {
		String openTips = getkey("FinalOpenDate", "众神之战决赛开启时间", "2012-12-06 21:35:00").toString();
		String stopTips = getkey("FinalStopDate", "众神之战决赛结束时间", "2012-12-06 21:40:00").toString();
		return openTips + "~" + stopTips;
	}

	public int getLordsIndex() {
		return Integer.parseInt(getkey("Lords_index", "众神之战届数", "1").toString());
	}

	public Date getCrossGuildSignUpOpenDateString() {
		if (signUpOpenDate != null) {
			return signUpOpenDate;
		}
		String time = getkey("GuildSignOpen", "跨服工会战报名开始时间", "2013-10-23 10:00:01").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			signUpOpenDate = dateformat.parse(time);
			return signUpOpenDate;
		} catch (Exception e) {
			Log.error("跨服工会战时间格式转化错误, time : " + time, e);
			return null;
		}
	}

	public Date getCrossGuildSignUpStopDateString() {
		if (signUpStopDate != null) {
			return signUpStopDate;
		}
		String time = getkey("GuildSignStop", "跨服工会战报名结束时间", "2013-10-23 11:00:00").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			signUpStopDate = dateformat.parse(time);
			return signUpStopDate;
		} catch (Exception e) {
			Log.error("跨服工会战时间格式转化错误, time : " + time, e);
			return null;
		}
	}

	public Pair getGuildHour() {
		if (CrossGuildTime == null) {
			String str = getkey("CrossGuild_Time", "跨服公会战开始时间", "20:00,21:00").toString();
			String[] times = str.split(",");
			int[] start = SplitUtil.splitToInt(times[0], ":");
			int[] stop = SplitUtil.splitToInt(times[1], ":");
			CrossGuildTime = new Pair(start[0], start[1], stop[0], stop[1]);
		}
		return CrossGuildTime;
	}

	public int getRuneCommonCD() {
		return Integer.parseInt(getkey("RuneCommonCD", "符文公用CD", "20000").toString());
	}

	public int getClearDays() {
		return Integer.parseInt(getkey("clearDays", "清除指定天数之前的玩家精彩活动数据", "45").toString());
	}

	public Pair getVehicleDay() {
		if (vehicleDay == null) {
			String str = getkey("Vehicle_Day", "载具时间", "1,3,6,5").toString();
			int[] daySet = SplitUtil.splitToInt(str, ",");
			if (daySet.length == 1) {
				vehicleDay = new Pair(daySet[0], 0, 0, 0);
			} else if (daySet.length == 2) {
				vehicleDay = new Pair(daySet[0], daySet[1], 0, 0);
			} else if (daySet.length == 3) {
				vehicleDay = new Pair(daySet[0], daySet[1], daySet[2], 0);
			} else if (daySet.length == 4) {
				vehicleDay = new Pair(daySet[0], daySet[1], daySet[2], daySet[3]);
			}
		}
		return vehicleDay;
	}

	public int getVehicleRebornTime() {
		String key = getkey("revivalTime", "载具复活时长", "30").toString();
		return Integer.parseInt(key);
	}

	public int getBackPlayerGrades() {
		return Integer.parseInt(getkey("backPlayerGrades", "获得英雄回归Buff的最低等级", "50").toString());
	}

	public int getBackOfflineDays() {
		return Integer.parseInt(getkey("backOfflineDays", "获得英雄回归Buff的最低离线天数", "30").toString());
	}

	public int getBackBufferDays() {
		return Integer.parseInt(getkey("backBufferDays", "英雄回归Buff持续天数", "15").toString());
	}

	public Pair getBottlePrice() {
		if (bottlePrice == null) {
			String str = getkey("Bottle_Price", "魔罐开启价格", "10|95|450").toString();
			int[] prices = SplitUtil.splitToInt(str, "\\|");
			bottlePrice = new Pair(prices[0], prices[1], prices[2]);
		}
		return bottlePrice;
	}

	public int[] get50Coupons() {
		return SplitUtil.splitToInt(getkey("50_coupons", "消费500钻石以上，减少50钻石消耗", "500,50").toString());
	}

	public int[] get200Coupons() {
		return SplitUtil.splitToInt(getkey("200_coupons", "消费1000钻石以上，减少200钻石消耗", "100,200").toString());
	}

	public int[] get2000Coupons() {
		return SplitUtil.splitToInt(getkey("2000_coupons", "消费5000钻石以上，减少2000钻石消耗", "5000,2000").toString());
	}

	public Pair[] getTreasureCoupons() {
		Pair[] pais = new Pair[3];
		String[] values = getkey("props_coupons", "在商城道具栏中满1000减100 消费满2000减400 消费满3000减900", "1000,100|2000,400|3000,900").toString().split("\\|");
		for (int i = 0, j = values.length; i < j; i++) {
			int[] value = SplitUtil.splitToInt(values[i]);
			Pair pair = new Pair(value[0], value[1]);
			pais[i] = pair;
		}
		return pais;
	}

	public Pair[] getGemCoupons() {
		Pair[] pais = new Pair[3];
		String[] values = getkey("gem_coupons", "在商城宝石栏中满1000减100 消费满2000减400 消费满3000减900", "1000,100|2000,400|3000,900").toString().split("\\|");
		for (int i = 0, j = values.length; i < j; i++) {
			int[] value = SplitUtil.splitToInt(values[i]);
			Pair pair = new Pair(value[0], value[1]);
			pais[i] = pair;
		}
		return pais;
	}

	public Pair[] getFashionCoupons() {
		Pair[] pais = new Pair[3];
		String[] values = getkey("fashion_coupons", "在商城时装栏中满1000减100 消费满2000减400 消费满3000减900", "1000,100|2000,400|3000,900").toString().split("\\|");
		for (int i = 0, j = values.length; i < j; i++) {
			int[] value = SplitUtil.splitToInt(values[i]);
			Pair pair = new Pair(value[0], value[1]);
			pais[i] = pair;
		}
		return pais;
	}

	public int getPetAttResetNeedPoint() {
		return Integer.parseInt(getkey("pet_reset_att", "宠物一级属性点重置", "100").toString());
	}

	public int getPetSkillNeedPoint() {
		return Integer.parseInt(getkey("pet_learn_skill", "宠物学习技能", "200").toString());
	}

	public int getUpPetQualityNeedPoint() {
		return Integer.parseInt(getkey("pet_up_quality", "宠物品质提升", "10").toString());
	}

	public int[] getDefaultFateSonType() {
		String str = getkey("Fate_Sontype", "默认的命运守护", "2000").toString();
		return SplitUtil.splitToInt(str);
	}

	public List<Pair> getFateAllRandom() {
		String str = getkey("Fate_AllRandom", "转盘全部概率", "1,400|2,250|3,175|4,125|10,50").toString();
		String[] strSet = str.split("\\|");
		int min = 0;
		List<Pair> pairs = new ArrayList<Pair>();
		for (String random : strSet) {
			int[] resultSet = SplitUtil.splitToInt(random);
			Pair pair = new Pair(resultSet[0], min, resultSet[1] + min);
			pairs.add(pair);
			min += resultSet[1];
		}
		return pairs;
	}

	public List<Pair> getDisFateRandom() {
		String str = getkey("Fate_DisableRandom", "转盘禁用2档后概率", "3,500000|4,357143|10,142857").toString();
		String[] strSet = str.split("\\|");
		int min = 0;
		List<Pair> pairs = new ArrayList<Pair>();
		for (String random : strSet) {
			int[] resultSet = SplitUtil.splitToInt(random);
			Pair pair = new Pair(resultSet[0], min, resultSet[1] + min);
			pairs.add(pair);
			min += resultSet[1];
		}
		return pairs;
	}

	public int getFateTurnFreeCount() {
		return Integer.parseInt(getkey("Fate_FreeCount", "转盘免费次数", "1").toString());
	}

	public Pair getFateTurnPoint() {
		String key = getkey("Fate_Point", "转盘收费信息", "10,300,120,1").toString();
		int[] pointSet = SplitUtil.splitToInt(key);
		Pair pair = new Pair(pointSet[0], pointSet[1], pointSet[2]);
		return pair;
	}

	public int getFlashSaleFreshSeconds() {
		return Integer.parseInt(getkey("FreshSeconds", "限时抢购刷新的总秒数", "14400").toString());
	}

	public int[] getAwakeValue() {
		String data = getkey("Awake_value", "觉醒值的增长", "-150,20,200").toString();
		return SplitUtil.splitToInt(data);
	}

	public int getEnterPetChallengeGrade() {
		return Integer.parseInt(getkey("Pet_Challenge_Grade", "进入英灵挑战最低等级", 50).toString());
	}

	public int getPetBattleOverGrade() {
		return Integer.parseInt(getkey("Pet_BattleOver_Grade", "立即结束战斗的VIP最低等级", 3).toString());
	}

	public int getPetChallengeTotalCount() {
		return Integer.parseInt(getkey("Pet_Challenge_Count", "英灵竞技挑战次数", 10).toString());
	}

	public int getEnterMineFieldGrades() {
		return Integer.parseInt(getkey("MineField", "进入矿场最低等级", "25").toString());
	}

	public Pair getMineOfActiveTime() {
		if (mineOfActiveTime == null) {
			String str = getkey("MineOfActiveTime", "紫金矿场活动时间", "21:00,21:30").toString();
			String[] times = str.split(",");
			int[] start = SplitUtil.splitToInt(times[0], ":");
			int[] stop = SplitUtil.splitToInt(times[1], ":");
			mineOfActiveTime = new Pair(start[0], start[1], stop[0], stop[1]);
		}
		return mineOfActiveTime;
	}

	public int getMineMaxPickCount() {
		return Integer.parseInt(getkey("MineMaxPickCount", "每辆矿车最大采集次数", "5").toString());
	}

	public int getMinePerPickCount() {
		return Integer.parseInt(getkey("MinePerPickCount", "矿车每次采集矿数量", "20").toString());
	}

	public int getMineMaxHandCount() {
		return Integer.parseInt(getkey("MineMaxHandCount", "每天交矿的最大次数", "2").toString());
	}

	public int getMineMaxTramcarCount() {
		return Integer.parseInt(getkey("MineMaxTramcarCount", "每天领取矿车的最大次数", "2").toString());
	}

	public int getMineMaxMineralsCount() {
		return Integer.parseInt(getkey("MineMaxMineralsCount", "每辆矿车的资源量上限", "200").toString());
	}

	public int getMineMinMineralsCount() {
		return Integer.parseInt(getkey("MineMinMineralsCount", "每辆矿车损失的下限", "60").toString());
	}

	public int getMaxMineScore() {
		return Integer.parseInt(getkey("MaxMineScore", "玩家紫晶积分上限", "9999").toString());
	}

	public Map<Integer, String> getMineFieldLevel() {
		String str = getkey("Mine_Level", "紫晶矿场等级段", "25,39|40,10000").toString();
		String[] strSet = str.split("\\|");
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < strSet.length; i++) {
			map.put(i + 1, strSet[i]);
		}
		return map;
	}

	public int getMaxMineCapacity() {
		return Integer.parseInt(getkey("MaxCapacity", "每个紫晶矿场容纳最多人数", "500").toString());
	}

	public int getPetChallengeImmTime() {
		return Integer.parseInt(getkey("Imm_over_battle", "立即结束战斗等待时间", "10000").toString());
	}

	public int getPetChallengeNeed() {
		return Integer.parseInt(getkey("Jion_pet_challenge", "参与竞技所需", "20000").toString());
	}

	public String getAddPetCountNeedPoint() {
		return getkey("add_pet_count", "增加英灵数量上限所需", "100,50,500").toString();
	}

	public int getPetMaxCount() {
		return Integer.parseInt(getkey("pet_maxCount", "英灵数量上限", "12").toString());
	}

	public int getTreasureRefreshNeed() {
		return Integer.parseInt(getkey("treasure_refresh_points", "藏宝图一键刷新所需钻石", "50").toString());
	}

	public int getTreasureRefreshItemCount() {
		return Integer.parseInt(getkey("treasure_refresh_item", "藏宝图一键刷新所需物品数量", "10").toString());
	}

	public int getTreasureImmBattle() {
		return Integer.parseInt(getkey("treasure_imm_battle", "藏宝图立即挖掘所需钻石", "10").toString());
	}

	public String getbegin_date() {
		String begin_date = getkey("AnswerQuestionTime", "答题系统开始时间段", "16:30:00").toString();
		return begin_date;
	}

	public String getFreshMan_date() {
		String begin_date = getkey("FreshManDepositCost", "新手矿脉，参与需要钻石", "1000,2000,3000").toString();
		return begin_date;
	}

	public Pair getDatiDay() {
		if (datiDay == null) {
			String str = getkey("Dati_Day", "答题系统日期段", "1,3,6,5").toString();
			int[] daySet = SplitUtil.splitToInt(str, ",");
			if (daySet.length == 1) {
				datiDay = new Pair(daySet[0], 0, 0, 0);
			} else if (daySet.length == 2) {
				datiDay = new Pair(daySet[0], daySet[1], 0, 0);
			} else if (daySet.length == 3) {
				datiDay = new Pair(daySet[0], daySet[1], daySet[2], 0);
			} else if (daySet.length == 4) {
				datiDay = new Pair(daySet[0], daySet[1], daySet[2], daySet[3]);
			} else {
				datiDay = new Pair(daySet[0], daySet[1], daySet[2], daySet[3]);
			}
		}
		return datiDay;
	}

	public int getJoinTreasureMapCount() {
		return Integer.parseInt(getkey("join_tremap_count", "藏宝图参与次数", "5").toString());
	}

	public int getRewardTreasureMapCount() {
		return Integer.parseInt(getkey("reward_tremap_count", "藏宝图领取奖励次数", "5").toString());
	}

	public int getRecoverPoint() {
		return Integer.parseInt(getkey("RECOVER_POINT", "修行文章价格", "10").toString());
	}

	public int getAllSwallowGpNeedPoints() {
		return Integer.parseInt(getkey("swallow_fullgp_points", "英灵吞噬经验100%转化所需钻石", "50").toString());
	}

	public int getFusePetNeedPoints() {
		return Integer.parseInt(getkey("fuse_pet_points", "英灵融合属性继承所需钻石", "200").toString());
	}

	public int getTollgateCount() {
		return Integer.parseInt(getkey("tollgate_count", "诸神降临基础次数", "10").toString());
	}

	public int getTollgateAttackNeedPoints() {
		return Integer.parseInt(getkey("tollgate_attack_count", "诸神降临花费钻石挑战", "10").toString());
	}

	public int getSinglePassEnterCount() {
		return Integer.parseInt(getkey("SinglePass_Count", "天穹之径挑战次数", "10").toString());
	}

	public int getTollgateRestoreHpTime() {
		return Integer.parseInt(getkey("restore_hp_Time", "诸神降临BOSS血量恢复时间s", "1800").toString());
	}

	public int getTollgateRestoreCountTime() {
		return Integer.parseInt(getkey("restore_count_time", "诸神降临恢复挑战次数时间s", "300").toString());
	}

	public int getTollgateAddCount() {
		return Integer.parseInt(getkey("tollgate_add_count", "诸神降临购买额外挑战次数上限", "10").toString());
	}

	public int getTollgateAddTimeCount() {
		return Integer.parseInt(getkey("tollgate_add_time_count", "诸神降临扣除最大费用对应次数", "10").toString());
	}

	public int getMarriageCancelNeed() {
		return Integer.parseInt(getkey("cancel_engage_need", "取消订婚所需钻石", "500").toString());
	}

	public int getMarriageDivorceNeed() {
		return Integer.parseInt(getkey("divorce_need", "离婚所需钻石", "2000").toString());
	}

	public int getStarAdditionCost() {
		return Integer.parseInt(getkey("Star_Addition_Cost", "星运槽培养消耗黄金", 1000000).toString());
	}

	public int getDragonCampaignWeary() {
		return Integer.parseInt(getkey("Dragon_Campaign_Weary", "魔龙消耗体力", 60).toString());
	}

	public int getDragonCampaignCount() {
		return Integer.parseInt(getkey("Dragon_Campaign_Count", "魔龙次数", 1).toString());
	}

	public int getMarriageTaskCount() {
		return Integer.parseInt(getkey("marriage_task_count", "当天接受婚姻任务次数", 10).toString());
	}

	public int[] getMarriageGift() {
		String gift = getkey("marriage_gift", "递交礼金数量", "100000,500000,1000000").toString();
		return SplitUtil.splitToInt(gift);
	}

	public int getMarriageBuyFireworksNeed() {
		return Integer.parseInt(getkey("marriage_fireworks", "购买婚庆烟花所需黄金", 100000).toString());
	}

	public int getMarriageTreeTrainCount() {
		return Integer.parseInt(getkey("mar_tree_train_count", "姻缘神树培养次数", 10).toString());
	}

	public Pair getWarOpenMinute() {
		String time = getkey("CrossWar_Time", "战场开放的起始,结束分钟", "0,30").toString();
		int[] timeSet = SplitUtil.splitToInt(time, ",");
		if (warOpenMinutes == null) {
			warOpenMinutes = new Pair(timeSet[0], timeSet[1]);
		}
		return warOpenMinutes;
	}

	public int getMarriageTaskAddHappy() {
		return Integer.parseInt(getkey("mar_task_add_happy", "结婚任务额外获得美满度", 50).toString());
	}

	public int getCrossGuildIndex() {
		return Integer.parseInt(getkey("CrossGuild_Index", "跨服工会战届数", 1).toString());
	}

	public int getMarTreeTime() {
		return Integer.parseInt(getkey("mar_tree_time", "姻缘神树培养总时间(m)", 300).toString());
	}

	public int getMarTime() {
		return Integer.parseInt(getkey("mar_time", "礼堂举行时间(m)", 120).toString());
	}

	public int getMarNoticeTime() {
		return Integer.parseInt(getkey("mar_notice_time", "礼堂提前通知交换戒指时间(m)", 20).toString());
	}

	public Date getCrossGuildTimeOutDate() {
		if (crossGuildTimeOut != null) {
			return crossGuildTimeOut;
		}
		String time = getkey("CrossGuild_TimeOutDate", "跨服工会战失效时间", "2013-10-23 10:00:01").toString();
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			crossGuildTimeOut = dateformat.parse(time);
			return crossGuildTimeOut;
		} catch (Exception e) {
			Log.error("跨服工会战失效时间, time : " + time, e);
			return null;
		}
	}

	public int getMarTreeFeedCount() {
		return Integer.parseInt(getkey("mar_feed_count", "姻缘神树充能次数", 2).toString());
	}

	public int getMarRingNeedPoints() {
		return Integer.parseInt(getkey("mar_ring_points", "婚戒淬炼所需钻石", 10).toString());
	}

	public List<Pair> getPetIslandBossTime() {
		List<Pair> bossTimes = new ArrayList<Pair>();
		String str = getkey("petboss_time", "英灵岛BOSS开启时间段", "11:00,11:30|16:30,17:00").toString();
		String[] times = str.split("\\|");
		for (String time : times) {
			String[] timeSet = time.split(",");
			int[] openTime = SplitUtil.splitToInt(timeSet[0], ":");
			int[] stopTime = SplitUtil.splitToInt(timeSet[1], ":");
			bossTimes.add(new Pair(openTime[0], openTime[1], stopTime[0], stopTime[1]));
		}

		return bossTimes;
	}

	public int getMarHeartCount() {
		return Integer.parseInt(getkey("mar_heart_count", "参与心心相印次数", 1).toString());
	}

	public int getMarHeartTime() {
		return Integer.parseInt(getkey("mar_heart_time", "每道题目答题时间ms", 20000).toString());
	}

	public String getPetBossWound() {
		return getkey("petboss_wound", "英灵岛BOSS伤害累计奖励", "0.03,5|0.04,10|0.05,20").toString();
	}

	public Map<Integer, Double> getItemBack(int type) {
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		if (type == 1) {
			String str = getkey("item_back1", "黄金找回收益的折扣", "1,1|2,0.8|3,0.7|4,0.7|5,0.7|6,0.7|7,0.7|8,0.7").toString();
			String[] strSet = str.split("\\|");
			for (String set : strSet) {
				String[] resultSet = set.split(",");
				map.put(Integer.valueOf(resultSet[0]), Double.valueOf(resultSet[1]));
			}
		} else {
			String str = getkey("item_back2", "钻石找回收益的折扣", "1,1|2,0.8|3,0.7|4,0.7|5,0.7|6,0.7|7,0.7|8,0.7").toString();
			String[] strSet = str.split("\\|");
			for (String set : strSet) {
				String[] resultSet = set.split(",");
				map.put(Integer.valueOf(resultSet[0]), Double.valueOf(resultSet[1]));
			}
		}
		return map;
	}
}
