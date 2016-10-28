package com.chuangyou.xianni.npcDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.constant.RoleConstants.NpcType;
import com.chuangyou.xianni.entity.spawn.NpcInfo;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.NpcInfoDao;

public class NpcInfoTemplateMgr {
	///npc
	public static Map<Integer, NpcInfo> npcInfoTemps = new HashMap<Integer, NpcInfo>();
	
	///转场点
	public static Map<Integer, NpcInfo> transpointTemps = new HashMap<Integer, NpcInfo>();
	
	public static boolean init()
	{
		return reloadNpcInfoTemp();
	}
	
	public static boolean reloadNpcInfoTemp()
	{
		NpcInfoDao dao = DBManager.getNpcInfoDao();
		List<NpcInfo> infos = dao.getAll();
		for(NpcInfo npc : infos)
		{
			if(npc.getType() == NpcType.TRANSFER) //转场点
			{
				transpointTemps.put(npc.getNpcId(), npc);
			}else{
				npcInfoTemps.put(npc.getNpcId(), npc);
			}
		}
		return true;
	}
}
