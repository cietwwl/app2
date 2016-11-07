package com.chuangyou.xianni.campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.battle.BattleLivingInfoMsgProto.BattleLivingInfoMsg;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.state.CampaignState;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.NoticeConstant;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.notice.NoticeCfg;
import com.chuangyou.xianni.fieldBoss.manager.FieldBossHelper;
import com.chuangyou.xianni.notice.template.NoticeTemplateMgr;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.Transfer;
import com.chuangyou.xianni.warfield.spawn.GatherSpawnNode;
import com.chuangyou.xianni.warfield.spawn.OverState;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class WorldBossTriggerCampaign extends Campaign {
	
	private Transfer transfer;
	
	/** 玩家拿到的宝物数量 */
	private ConcurrentHashMap<Long, Integer> treasureNumMap = new ConcurrentHashMap<Long, Integer>();
	
	/** 复活点和出生点随机池 */
	private List<SpwanNode> revivalNodeList = new ArrayList<>();
	
	/** 上次发送宝箱刷新提醒公告时间 */
	private long lastNoticeTime = 0;
	
	/** 发送宝箱刷新的提示间隔 */
	private int timeInterval = 0;

	public WorldBossTriggerCampaign(CampaignTemplateInfo tempInfo, Transfer enterTransfer) {
		// TODO Auto-generated constructor stub
		super(tempInfo, null, 0);
		this.transfer = enterTransfer;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		
		lastNoticeTime = System.currentTimeMillis() - 10 * 1000L;
		timeInterval = SystemConfigTemplateMgr.getIntValue("worldBoss.campaign.noticeTime");
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		
		if(transfer != null){
			transfer.getField().leaveField(transfer, true);
			transfer = null;
		}
		
		//结束发奖
		FieldBossHelper.worldBossTreasureAward(treasureNumMap);
	}
	
	@Override
	public void onPlayerLeave(ArmyProxy army, boolean isUnline) {
		// TODO Auto-generated method stub
		this.onPlayerExit(army);
	}
	
	@Override
	public void onOverLeave(ArmyProxy army) {
		// TODO Auto-generated method stub
		this.onPlayerExit(army);
	}
	
	@Override
	public void removeArmy(ArmyProxy army, boolean noBack) {
		// TODO Auto-generated method stub
		super.removeArmy(army, noBack);
		if(state.getCode() != CampaignState.STOP){
			//退出副本时，清除宝箱记录
			this.treasureNumMap.remove(army.getPlayerId());
		}
	}
	
	/**
	 * 采集宝箱
	 */
	@Override
	public void triggerPoint(ArmyProxy army, SpwanNode node) {
		// TODO Auto-generated method stub
		super.triggerPoint(army, node);
		
		if(getState().getCode() == CampaignState.STOP){
			return;
		}
		//宝物数量加1
		if(changeTreasureNum(army.getPlayer(), 1) == false){
			return;
		}
		
		if(node instanceof GatherSpawnNode){
			node.stateTransition(new OverState(node));
		}
	}
	
	/**
	 * 玩家杀死玩家后抢夺宝物
	 */
	@Override
	public void playerDie(Player player, Living source){
		super.playerDie(player, source);
		
		if(getState().getCode() == CampaignState.STOP){
			return;
		}
		
		//不是玩家杀死的
		if(source.getArmyId() <= 0){
			return;
		}
		
		//被杀玩家宝物数量减1
		if(changeTreasureNum(player, -1) == false){
			return;
		}
		
		long killerId = source.getArmyId();
		if(armys.containsKey(killerId)){
			//击杀者加宝物数量加1
			Player killer = null;
			if(source instanceof Player){
				killer = (Player)source;
			}else{
				ArmyProxy killerArmy = WorldMgr.getArmy(source.getArmyId());
				if(killerArmy == null) return;
				killer = killerArmy.getPlayer();
			}
			changeTreasureNum(killer, 1);
		}
	}
	
	/**
	 * 宝物数量变更，count小于0时减少，大于0时增加
	 * @param playerId
	 * @param count
	 * @return
	 */
	private boolean changeTreasureNum(Player player, int count){
		if(count == 0) return true;
		long playerId = player.getArmyId();
		if(treasureNumMap.containsKey(playerId) == false){
			if(count < 0) return false;
			treasureNumMap.put(playerId, 0);
		}
		int treasureNum = treasureNumMap.get(playerId) + count;
		if(treasureNum < 0){
			return false;
		}
		treasureNumMap.put(playerId, treasureNum);
		
		//更新通知
		FieldBossHelper.worldBossTreasureNotify(player, treasureNum);
		
		return true;
	}
	
	@Override
	public void insertCampaignAtt(long playerId, BattleLivingInfoMsg.Builder playerInfo) {
		// TODO Auto-generated method stub
		PropertyMsg.Builder pmsg = PropertyMsg.newBuilder();
		pmsg.setType(EnumAttr.WORLDBOSS_TREASURE.getValue());
		if(treasureNumMap.containsKey(playerId) == true){
			pmsg.setTotalPoint(treasureNumMap.get(playerId));
		}else{
			pmsg.setTotalPoint(0);
		}
		playerInfo.addPropertis(pmsg);
	}
	
	@Override
	public void changeRevivalNode(SpwanNode revivalNode) {
		// TODO Auto-generated method stub
		revivalNodeList.add(revivalNode);
	}
	
	@Override
	public SpwanNode getRevivalNode() {
		// TODO Auto-generated method stub
		if(revivalNodeList == null || revivalNodeList.size() <= 0){
			return null;
		}
		int randomIndex = ThreadSafeRandom.getInstance().next(revivalNodeList.size());
		return revivalNodeList.get(randomIndex);
	}
	
	@Override
	public Vector3 getBornNode(ArmyProxy player) {
		// TODO Auto-generated method stub
		SpwanNode bornNode = getRevivalNode();
		if(bornNode == null) {
			return null;
		}
		return bornNode.getSpawnInfo().getPosition();
	}
	
	@Override
	public void pollingCheck() {
		// TODO Auto-generated method stub
		super.pollingCheck();
		
		if(timeInterval <= 0){
			return;
		}
		if(System.currentTimeMillis() - lastNoticeTime >= timeInterval * 60L * 1000){
			warnNotice();
			lastNoticeTime = System.currentTimeMillis();
		}
	}
	
	private void warnNotice(){
		NoticeCfg noticeCfg = NoticeTemplateMgr.getNoticeCfg(NoticeConstant.WORLDBOSS_CAMPAIGN_TREASURE);
		if(noticeCfg == null || noticeCfg.getNoticeClose() > 0){
			return;
		}
		if(starField != null){
			ChatManager.sendNotice(noticeCfg.getChannel(), noticeCfg.getNotifyRange(), starField, noticeCfg.getContent());
		}
	}
	
	@Override
	public void clearCampaignData() {
		// TODO Auto-generated method stub
		super.clearCampaignData();
		
		if(transfer != null){
			transfer.getField().leaveField(transfer, true);
			transfer = null;
		}
		
		if(treasureNumMap != null){
			treasureNumMap.clear();
			treasureNumMap = null;
		}
		
		if(revivalNodeList != null){
			revivalNodeList.clear();
			revivalNodeList = null;
		}
	}
}
