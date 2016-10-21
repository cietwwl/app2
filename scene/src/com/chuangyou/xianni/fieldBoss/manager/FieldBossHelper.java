package com.chuangyou.xianni.fieldBoss.manager;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.boss.BossDieProto.BossDieMsg;
import com.chuangyou.common.protobuf.pb.boss.WorldBossTreasureListProto.WorldBossTreasureListMsg;
import com.chuangyou.common.protobuf.pb.boss.WorldBossTreasureProto.WorldBossTreasureMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.xianni.battle.damage.DamageStatistic;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.fieldBoss.FieldBossDieInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.FieldBoss;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.warfield.spawn.FieldBossSpawnNode;

public class FieldBossHelper {

	/**
	 * 击杀奖励
	 * @param node
	 * @param boss
	 */
	public static void bossKillAward(FieldBossSpawnNode node, FieldBoss boss){
		DamageStatistic statistic = boss.getStatistic();
		
		BossDieMsg.Builder sendMsg = BossDieMsg.newBuilder();
		sendMsg.setMonsterId(boss.getMonsterInfo().getMonsterId());
		sendMsg.setFirstDamagePlayer(statistic.getFirstDamageSender());
		sendMsg.setMostDamagePlayer(statistic.getMostDamageSender());
		sendMsg.setShieldKiller(0);
		
		Iterator<Entry<Long, Long>> iterator = statistic.getDamageMap().entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Long, Long> entry = iterator.next();
			if(entry.getValue() > 0){
				sendMsg.addNormalPlayer(entry.getKey());
			}
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_FIELD_BOSS_DIE_AWARD, sendMsg);
		GatewayLinkedSet.send2Server(pkg);
	}
	
	/**
	 * BOSS死亡和刷新时间记录
	 * @param monsterId
	 * @param deathTime
	 * @param nextTime
	 */
	public static void bossNextTimeUpdate(int monsterId, long deathTime, long nextTime){
//		BossTimeUpdateMsg.Builder sendMsg = BossTimeUpdateMsg.newBuilder();
//		sendMsg.setMonsterId(monsterId);
//		sendMsg.setDeathTime(deathTime);
//		sendMsg.setNextTime(nextTime);
//		
//		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_FIELD_BOSS_TIME_UPDATE, sendMsg);
//		GatewayLinkedSet.send2Server(pkg);
		FieldBossDieInfo info = new FieldBossDieInfo();
		info.setMonsterId(monsterId);
		info.setDeathTime(new Date(deathTime));
		info.setNextTime(new Date(nextTime));
		DBManager.getFieldBossInfoDao().saveOrUpdate(info);
	}
	
	/**
	 * 世界BOSS触发的夺宝结束后发奖
	 * @param treasureNumMap
	 */
	public static void worldBossTreasureAward(Map<Long, Integer> treasureNumMap){
		WorldBossTreasureListMsg.Builder awardMsg = WorldBossTreasureListMsg.newBuilder();
		WorldBossTreasureMsg.Builder infoMsg = null;
		for(long playerId: treasureNumMap.keySet()){
			int count = treasureNumMap.get(playerId);
			if(count <= 0) continue;
			infoMsg = WorldBossTreasureMsg.newBuilder();
			infoMsg.setPlayerId(playerId);
			infoMsg.setTreasureCount(count);
			awardMsg.addInfo(infoMsg);
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_WORLD_BOSS_TREASURE_AWARD, awardMsg);
		GatewayLinkedSet.send2Server(pkg);
	}
	
	/**
	 * 世界BOSS触发的夺宝中，宝箱数量更新通知
	 * @param player
	 * @param count
	 */
	public static void worldBossTreasureNotify(Player player, int count){
		PlayerAttUpdateMsg.Builder attMsg = PlayerAttUpdateMsg.newBuilder();
		attMsg.setPlayerId(player.getId());
		PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.WORLDBOSS_TREASURE.getValue());
		pmsg.setTotalPoint(count);
		attMsg.addAtt(pmsg);
		
		//通知自己和附近玩家
		Set<Long> nears = player.getNears(new PlayerSelectorHelper(player));
		nears.add(player.getId());
		
		NotifyNearHelper.notifyAttrChange(nears, attMsg.build());
	}
}
