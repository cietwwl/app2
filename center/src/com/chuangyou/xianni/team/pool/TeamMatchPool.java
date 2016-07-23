package com.chuangyou.xianni.team.pool;

import java.util.Collections;
import java.util.Comparator;

import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 组队匹配池
 * @author laofan
 *
 */
public class TeamMatchPool extends BaseMatchPool<Integer>{

	
	private long lastSortTime = 0;
	
	/**
	 * 排序
	 */
	public void sort(){	
		long time = System.currentTimeMillis(); 
		if(time - lastSortTime>TeamMgr.TEAM_POOL_SORT_TIME){
			lastSortTime = time;			
			Comparator<Integer> com = new Comparator<Integer>() {
				@Override
				public int compare(Integer t1, Integer t2) {
					// TODO Auto-generated method stub
					Team team1 = TeamMgr.getAllTeams().get(t1);
					Team team2 = TeamMgr.getAllTeams().get(t2);
					return team1.getMemberSize() - team2.getMemberSize();
				}
			};
			synchronized (pools) {			
				Collections.sort(this.pools,com);
			}
			
		}
	}
}
