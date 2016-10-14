package com.chuangyou.xianni.state.condition;

import java.util.Map;

import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.entity.rank.RankTempInfo;
import com.chuangyou.xianni.entity.state.StateConditionConfig;
import com.chuangyou.xianni.entity.state.StateConditionInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.state.event.MountStateEvent;

/**
 * 坐骑
 * @author laofan
 *
 */
public class MountStateCondition extends BaseStateCondition {

	public MountStateCondition(StateConditionInfo info, StateConditionConfig config, GamePlayer player) {
		super(info, config, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		this.listener = new ObjectListener() {
			
			@Override
			public void onEvent(ObjectEvent event) {
				// TODO Auto-generated method stub
				MountStateEvent e = (MountStateEvent) event;
				int old = info.getProcess();
				if(e!=null){
					if(e.getTargetId()>=1 && e.getTargetId()<=3){						
						if(e.getTargetId() == config.getTargetId()){
							info.setProcess(e.getTargetNum());
						}
					}else if(e.getTargetId() == 4){
						if(e.getTargetId1() == config.getTargetId1()){
							info.setProcess(e.getTargetNum());
						}
						info.setProcess(getCount(player.getMountInventory().getMountEquip(),config.getTargetId1()));
					}else if(e.getTargetId() == 6){ //战力
						RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
						if(rankInfo!=null){
							info.setProcess((int)rankInfo.getMount());
						}
					}
				}
				if(old!=info.getProcess()){
					doNotifyUpdate();
				}
			}
		};
		player.addListener(listener, EventNameType.MOUNT);
	}

	@Override
	public void removeTrigger(GamePlayer player) {
		// TODO Auto-generated method stub
		player.removeListener(listener, EventNameType.MOUNT);
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		int targetId = config.getTargetId();
		if(targetId == 1){
			info.setProcess(player.getMountInventory().getMount().getLevel());
		}else if(targetId==2){
			info.setProcess(player.getMountInventory().getMount().getGrade());
		}else if(targetId == 3){
			info.setProcess(player.getMountInventory().getMount().getWeaponGrade());
		}else if(targetId == 4){
			info.setProcess(player.getMountInventory().getMountEquip().get(config.getTargetNum()).getEquipLevel());
		}else if(targetId == 5){
			info.setProcess(getCount(player.getMountInventory().getMountEquip(),config.getTargetId1()));
		}else if(targetId == 6){
			RankTempInfo rankInfo = RankServerManager.getInstance().getRankTempInfo(player.getPlayerId());
			if(rankInfo!=null){
				info.setProcess((int)rankInfo.getMount());
			}
		}
	}
	
	
	protected int getCount(Map<Integer, MountEquipInfo> map,int num){
		int count=0;
		for (MountEquipInfo mountEquip : map.values()) {
			if(mountEquip.getEquipLevel()>=num){
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

}
