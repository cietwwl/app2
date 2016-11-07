package com.chuangyou.xianni.welfare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.welfare.AllWelfareMsgProto.AllWelfareMsg;
import com.chuangyou.common.protobuf.pb.welfare.OneWelfareMsgProto.OneWelfareMsg;
import com.chuangyou.xianni.entity.welfare.WelfareConditionTemplate;
import com.chuangyou.xianni.entity.welfare.WelfareInfo;
import com.chuangyou.xianni.entity.welfare.WelfareTemplate;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.WelfareConditionTemplateDao;
import com.chuangyou.xianni.sql.dao.WelfareTemplateDao;
import com.chuangyou.xianni.welfare.conditionHandle.BaseConditionHandle;
import com.chuangyou.xianni.word.WorldMgr;

public class WelfareManager {
	/**
	 * 配置表所有福利奖励数据，索引是福利类型
	 */
	private static Map<Integer, List<WelfareTemplate>> CONFIG_TYPE = new HashMap<>();
	/**
	 * 配置表所有福利奖励数据，索引是福利ID
	 */
	private static Map<Integer, WelfareTemplate> CONFIG_ID = new HashMap<>();
	/**
	 * 配置表所有福利类型条件，索引是福利类型
	 */
	private static Map<Integer, WelfareConditionTemplate> conditionConfig = new HashMap<>();
	
	public static Map<Integer, WelfareConditionTemplate> getConditionConfig() {
		return conditionConfig;
	}

	public static void setConditionConfig(Map<Integer, WelfareConditionTemplate> conditionConfig) {
		WelfareManager.conditionConfig = conditionConfig;
	}

	public static boolean init() {
		return reload();
	}

	public static boolean reload() {
		//加载福利奖励信息
		WelfareTemplateDao dao = DBManager.getWelfareTemplateDao();
		List<WelfareTemplate> templates = dao.geTemplates();
		for (WelfareTemplate template : templates) {
			CONFIG_ID.put(template.getId(), template);
			int type = template.getType();
			if (CONFIG_TYPE.containsKey(type)) {
				CONFIG_TYPE.get(type).add(template);
			} else {
				List<WelfareTemplate> list = new ArrayList<>();
				list.add(template);
				CONFIG_TYPE.put(type, list);
			}
		}
		
		//加载福利类型条件信息
		WelfareConditionTemplateDao conditionTemplateDao = DBManager.getWelfareConditionTemplateDao();
		List<WelfareConditionTemplate> templates2 = conditionTemplateDao.geTemplates();
		for (WelfareConditionTemplate welfareConditionTemplate : templates2) {
			conditionConfig.put(welfareConditionTemplate.getWelfareType(), welfareConditionTemplate);
		}
		
		return true;
	}
	
	public static Map<Integer, List<WelfareTemplate>> getConfigType() {
		return CONFIG_TYPE;
	}
	
	public static Map<Integer, WelfareTemplate> getConfigId() {
		return CONFIG_ID;
	}
	
	/**
	 * 福利状态改变需要向客户端发送的消息
	 * @param configId
	 * @param state
	 * @param player
	 */
	public static void updateOneWelfare(int configId, int state, GamePlayer player) {
    	OneWelfareMsg.Builder msg = OneWelfareMsg.newBuilder();
    	msg.setId(configId);
    	msg.setStatus(state);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_ONE_WELFARE_INFO, msg.build());
		player.sendPbMessage(pkg);
    }
	
	/**
	 * 玩家登陆时需要推送所有福利信息
	 * @param configId
	 * @param state
	 * @param player
	 */
	public static void sendAllWelfareInfo(GamePlayer player) {
		AllWelfareMsg.Builder builder = AllWelfareMsg.newBuilder();
		WelfareConditionRecordInventory inventory = player.getWelfareConditionRecordInventory();
		builder.setOnlineTime(inventory.getInfo().getOnLineTime());
		for (WelfareInfo welfareInfo : player.getWelfareInventory().getWelfareInfoMap().values()) {
			OneWelfareMsg.Builder builder2 = OneWelfareMsg.newBuilder();
			builder2.setId(welfareInfo.getWelfareId());
			builder2.setStatus(welfareInfo.getStatus());
			builder.addList(builder2.build());
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_ALL_WELFARE_INFO, builder.build());
		player.sendPbMessage(pkg);
    }
	
	
	/**
	 * 登录天数加1相关逻辑
	 */
	public static void newDay() {
		for (GamePlayer player : WorldMgr.getOnLinePlayers()) {
			player.getWelfareConditionRecordInventory().addDay();
		}
	}
	
}
