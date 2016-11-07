package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.entity.artifact.ArtifactInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.retask.event.ArtifactStateEvent;

/**
 * 神器
 * @author laofan
 *
 */
public class ArtifactStateCondition extends BaseStateCondition {

	public ArtifactStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.initListener();
		player.addListener(listener, EventNameType.ARTIFACT);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.ARTIFACT);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = config.getTargetId();
		if(targetId == 1){
			info.setProcess(player.getArtifactInventory().getArtifactMap().get(config.getTargetNum()).getLevel());
		}else if(targetId == 2){
			info.setProcess(getCountLv(player.getArtifactInventory().getArtifactMap(),config.getTargetId1()));
		}else if(targetId == 3){
			info.setProcess(player.getArtifactInventory().getArtifactMap().get(config.getTargetNum()).getStar());
		}else if(targetId == 4){
			info.setProcess(getCountStar(player.getArtifactInventory().getArtifactMap(),config.getTargetId1()));
		}else if(targetId == 5){
			info.setProcess(player.getArtifactInventory().getArtifactMap().get(config.getTargetNum()).getStoneMaxLevel());
		}else if(targetId == 6){
			info.setProcess(getCountStoneLv(player.getArtifactInventory().getArtifactMap(),config.getTargetId1()));
		}else if(targetId == 7){
			RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
			if(rankInfo!=null){
				info.setProcess((int)rankInfo.getArtifact());
			}
		}
	}

	protected int getCountStar(Map<Integer, ArtifactInfo> map,int num){
		int count=0;
		for (ArtifactInfo mountEquip : map.values()) {
			if(mountEquip.getStar()>=num){
				count ++;
			}
		}
		return count;
	}
	
	
	protected int getCountLv(Map<Integer, ArtifactInfo> map,int num){
		int count=0;
		for (ArtifactInfo mountEquip : map.values()) {
			if(mountEquip.getLevel()>=num){
				count ++;
			}
		}
		return count;
	}
	
	
	protected int getCountStoneLv(Map<Integer, ArtifactInfo> map,int num){
		int count=0;
		for (ArtifactInfo mountEquip : map.values()) {
			if(mountEquip.getStoneMaxLevel()>=num){
				count ++;
			}
		}
		return count;
	}
	
	
	@Override
	public boolean commitProcess() {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public void initListener() {
		// TODO Auto-generated method stub
		if(this.listener!=null)return;
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				ArtifactStateEvent e = (ArtifactStateEvent) event;
				int old = info.getProcess();
				if(e.getTargetId() == 1 || e.getTargetId() == 3 || e.getTargetId() == 5){
					if(config.getTargetNum() == e.getTargetId1()){
						info.setProcess(e.getTargetNum());
					}
					if(e.getTargetId() == 1){						
						info.setProcess(getCountLv(player.getArtifactInventory().getArtifactMap(),config.getTargetId1()));
					}else if(e.getTargetId() == 3){
						info.setProcess(getCountStar(player.getArtifactInventory().getArtifactMap(),config.getTargetId1()));
					}else if(e.getTargetId() == 5){
						info.setProcess(getCountStoneLv(player.getArtifactInventory().getArtifactMap(),config.getTargetId1()));
					}
				}else if(e.getTargetId() == 7){
					RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
					if(rankInfo!=null){
						info.setProcess((int)rankInfo.getArtifact());
					}
				}
				
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
			}
		};
	}

}
