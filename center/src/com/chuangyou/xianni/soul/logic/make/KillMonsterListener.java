package com.chuangyou.xianni.soul.logic.make;

import java.util.Date;

import com.chuangyou.xianni.army.template.MonsterInfoTemplateMgr;
import com.chuangyou.xianni.entity.spawn.MonsterInfo;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 杀怪监听器
 * @author laofan
 *
 */
public class KillMonsterListener implements ObjectListener {

	private GamePlayer player;
	/**
	 *杀怪的等级范围
	 */
	final int MONSTER_LV_RANGE = 5;
	
	public KillMonsterListener(GamePlayer player) {
		this.player = player;
	}

	@Override
	public void onEvent(ObjectEvent event) {
		// TODO Auto-generated method stub
		int monsterId = (int) event.getObject();
		MonsterInfo info = MonsterInfoTemplateMgr.get(monsterId);
		if(info==null)return;
		if(player.getBasePlayer().getPlayerInfo().getLevel()>info.getLevel()+MONSTER_LV_RANGE)return;
		if(player.getBasePlayer().getPlayerInfo().getLevel()<info.getLevel()-MONSTER_LV_RANGE)return;
		Date now = new Date();
		if(now.getTime() - player.getSoulInventory().getSoulMake().getStartTime().getTime()<
				player.getSoulInventory().getSoulMake().getTotalTime()*1000){
			int count = player.getSoulInventory().getSoulMake().getKillNum()+1;
			player.getSoulInventory().getSoulMake().setKillNum(count);
		}
		
		//todo通知客户端====
		new SoulMakeTaskLogic().syncSoulMakeTask(player);
	}

}
