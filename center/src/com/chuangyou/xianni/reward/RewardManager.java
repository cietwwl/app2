package com.chuangyou.xianni.reward;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.email.manager.EmailManager;
import com.chuangyou.xianni.email.vo.EmailItemVo;
import com.chuangyou.xianni.entity.item.ItemAddType.RewardType;
import com.chuangyou.xianni.entity.rank.RankCfg;
import com.chuangyou.xianni.entity.reward.RewardTemplate;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.RewardTemplateDao;

public class RewardManager {
	public static Map<Integer, List<RewardTemplate>> rewardTemps = new HashMap<>();

	public static boolean init() {
		return reload();
	}

	public static boolean reload() {
		RewardTemplateDao dao = DBManager.getRewardTemplateDao();
		List<RewardTemplate> rewards = dao.getAll();
		if (rewards != null && rewards.size() > 0) {
			for (RewardTemplate reward : rewards) {
				List<RewardTemplate> types = rewardTemps.get(reward.getType());
				if (types == null) {
					types = new ArrayList<>();
				}
				types.add(reward);
				rewardTemps.put(reward.getType(), types);
			}
		}
		return true;
	}

	/** 发送竞技场奖励 */
	public static boolean sendArenaReward(GamePlayer player, int wins) {
		List<RewardTemplate> rewards = rewardTemps.get(RewardType.ARENA);
		if (rewards == null) {
			return false;
		}
		RewardTemplate get = null;
		for (RewardTemplate reard : rewards) {
			if (reard.getParam1() == wins) {
				get = reard;
				break;
			}
		}
		if (get != null) {
			return sendReward(get, player);
		}
		return false;
	}

	/** 发送竞技场连胜奖励 */
	public static boolean sendPvP1v1WinReward(GamePlayer player, int wins) {
		List<RewardTemplate> rewards = rewardTemps.get(RewardType.PVP_1V1_WIN);
		if (rewards == null) {
			return false;
		}
		RewardTemplate get = null;
		for (RewardTemplate reard : rewards) {
			if (reard.getParam1() == wins) {
				get = reard;
				break;
			}
		}
		if (get != null) {
			return sendReward(get, player);
		}
		return false;
	}

	/** 发送竞技场排名奖励 */
	public static boolean sendPvP1v1RankReward(GamePlayer player, int rank) {
		List<RewardTemplate> rewards = rewardTemps.get(RewardType.PVP_1V1_RANK);
		if (rewards == null) {
			return false;
		}
		RewardTemplate get = null;
		for (RewardTemplate reard : rewards) {
			if (rank >= reard.getParam1() && rank <= reard.getParam2()) {
				get = reard;
				break;
			}
		}
		if (get != null) {
			return sendReward(get, player);
		}
		return false;
	}

	/** 发送普通奖励 */
	public static boolean sendCommonReward(GamePlayer player, int type) {
		List<RewardTemplate> rewards = rewardTemps.get(type);
		if (rewards == null) {
			return false;
		}
		for (RewardTemplate reard : rewards) {
			sendReward(reard, player);
		}
		return true;
	}

	/**
	 * 
	 * @param playerId ：中奖人ID
	 * @param rank ：名次
	 * @param type :榜单代号
	 * @return
	 */
	public static boolean sendRankReward(long playerId,int rank,int type,RankCfg cfg){
		List<RewardTemplate> rewards = rewardTemps.get(type);
		if (rewards == null) {
			return false;
		}
		RewardTemplate get = null;
		for (RewardTemplate reard : rewards) {
			if(reard.getParam1()>reard.getParam2()){
				Log.error("RewardTemplate配置表数据错误:"+reard.toString());
			}
			if (rank >= reard.getParam1() && rank <= reard.getParam2()) {
				get = reard;
				break;
			}
		}
		if (get != null) {
			String title = cfg.getMailTitle();
			String content = cfg.getMailText();
			content = content.replaceAll("@rank@", rank+"");
			sendEmailReward(get,playerId,title,content);
		}
		return true;
	}
	
	public static List<RewardTemplate> getRewardTemps(int type) {
		return rewardTemps.get(type);
	}

	/**
	 * 发送邮件奖励
	 * @param template
	 * @param playerId
	 * @param title
	 * @param content
	 * @return
	 */
	public static void sendEmailReward(RewardTemplate template,long playerId,String title,String content){
		List<EmailItemVo> emailItems = new ArrayList<>();
		
		if (template.getItemTempId1() != 0 && template.getCount1() != 0) {
			emailItems.add(new EmailItemVo(template.getItemTempId1(), template.getCount1()));
		}
		if (template.getItemTempId2() != 0 && template.getCount2() != 0) {
			emailItems.add(new EmailItemVo(template.getItemTempId2(), template.getCount2()));
		}
		if (template.getItemTempId3() != 0 && template.getCount3() != 0) {
			emailItems.add(new EmailItemVo(template.getItemTempId3(), template.getCount3()));
		}
		if (template.getItemTempId4() != 0 && template.getCount4() != 0) {
			emailItems.add(new EmailItemVo(template.getItemTempId4(), template.getCount4()));
		}
		if (template.getItemTempId5() != 0 && template.getCount5() != 0) {
			emailItems.add(new EmailItemVo(template.getItemTempId5(), template.getCount5()));
		}
		EmailManager.insertEmail(playerId, title, content, emailItems);
	}
	
	public static boolean sendReward(RewardTemplate template, GamePlayer player) {
		BagInventory bag = player.getBagInventory();
		if (bag == null) {
			Log.error("sendReward error, template:" + template.getType() + " player:" + player.getPlayerId());
			return false;
		}
		boolean result = true;
		if (template.getItemTempId1() != 0 && template.getCount1() != 0) {
			if (!bag.addItem(template.getItemTempId1(), template.getCount1(), (short) template.getType(), true)) {
				result = false;
			}
		}
		if (template.getItemTempId2() != 0 && template.getCount2() != 0) {
			if (!bag.addItem(template.getItemTempId2(), template.getCount2(), (short) template.getType(), true)) {
				result = false;
			}
		}
		if (template.getItemTempId3() != 0 && template.getCount3() != 0) {
			if (!bag.addItem(template.getItemTempId3(), template.getCount3(), (short) template.getType(), true)) {
				result = false;
			}
		}
		if (template.getItemTempId4() != 0 && template.getCount4() != 0) {
			if (!bag.addItem(template.getItemTempId4(), template.getCount4(), (short) template.getType(), true)) {
				result = false;
			}
		}
		if (template.getItemTempId5() != 0 && template.getCount5() != 0) {
			if (!bag.addItem(template.getItemTempId5(), template.getCount5(), (short) template.getType(), true)) {
				result = false;
			}
		}
		return result;
	}

}
