package com.chuangyou.xianni.magicwp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MagicwpInventory extends AbstractEvent implements IInventory {

	/**
	 * 玩家数据
	 */
	private GamePlayer player;
	
	/**
	 * 法宝总属性
	 */
	private MagicwpAtt magicwpAtt;
	
	/**
	 * 法宝信息列表
	 */
	private Map<Integer, MagicwpInfo> magicwpInfoMap;
	
	/**
	 * 法宝禁制信息列表
	 */
	private Map<Integer, MagicwpBanInfo> banInfoMap;
	
	private List<Integer> autoUpBanList = null;
	
	public MagicwpInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 获取玩家法宝总属性
	 * @return
	 */
	public MagicwpAtt getMagicwpAtt() {
		if(magicwpAtt == null){
			magicwpAtt = new MagicwpAtt(player.getPlayerId());
			magicwpAtt.setOp(Option.Insert);
		}
		return magicwpAtt;
	}
	/**
	 * 更新法宝总属性，玩家有多个法宝，总属性只有一个
	 * @param info
	 * @return
	 */
	public boolean updateMagicwpAtt(MagicwpAtt info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	/**
	 * 获取法宝信息
	 * @param magicwpId
	 * @return
	 */
	public MagicwpInfo getMagicwpInfo(int magicwpId){
		return this.magicwpInfoMap.get(magicwpId);
	}
	/**
	 * 激活/添加法宝
	 * @param info
	 * @return
	 */
	public boolean addMagicwpInfo(MagicwpInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		if(this.magicwpInfoMap.get(info.getMagicwpId()) != null){
			info.setOp(Option.Update);
		}else{
			info.setOp(Option.Insert);
		}
		magicwpInfoMap.put(info.getMagicwpId(), info);
		return true;
	}
	/**
	 * 更新法宝信息
	 * @param info
	 * @return
	 */
	public boolean updateMagicwpInfo(MagicwpInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 获取禁制信息
	 * @param banId
	 * @return
	 */
	public MagicwpBanInfo getBan(int banId){
		MagicwpBanInfo ban = banInfoMap.get(banId);
		if(ban == null){
			MagicwpBanCfg banCfg = MagicwpTemplateMgr.getBanTemps().get(banId);
			if(banCfg == null) return null;
			ban = new MagicwpBanInfo(player.getPlayerId(), banId);
			ban.setOp(Option.Insert);
			banInfoMap.put(banId, ban);
		}
		return ban;
	}
	
	/**
	 * 更新禁制信息
	 * @param info
	 * @return
	 */
	public boolean updateBan(MagicwpBanInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	
	/**
	 * 获取已设置自动升级的禁制ID列表
	 * @return
	 */
	public List<Integer> getAutoUpBanList(){
		if(autoUpBanList == null){
			this.refreshAutoUpBanList();
		}
		return autoUpBanList;
	}
	/**
	 * 刷新已设置自动升级的禁制ID列表
	 */
	public void refreshAutoUpBanList(){
		List<Integer> list = new ArrayList<>();
		for(MagicwpBanInfo ban:banInfoMap.values()){
			if(ban.getAutoUpLevel() == 1){
				list.add(ban.getBanId());
			}
		}
		this.autoUpBanList = list;
	}

	/**
	 * 获取玩家已有法宝信息列表
	 * @return
	 */
	public Map<Integer, MagicwpInfo> getMagicwpInfoMap() {
		return magicwpInfoMap;
	}

	/**
	 * 获取玩家禁制信息列表
	 * @return
	 */
	public Map<Integer, MagicwpBanInfo> getBanInfoMap() {
		return banInfoMap;
	}

	public boolean loadFromDataBase(){
		magicwpAtt = DBManager.getMagicwpAttDao().get(player.getPlayerId());
		magicwpInfoMap = DBManager.getMagicwpInfoDao().getAll(player.getPlayerId());
		banInfoMap = DBManager.getMagicwpBanInfoDao().getAll(player.getPlayerId());
		return true;
	}
	
	public boolean unloadData(){
		player = null;
		
		magicwpAtt = null;
		
		if(magicwpInfoMap != null){
			magicwpInfoMap.clear();
		}
		magicwpInfoMap = null;
		
		if(banInfoMap != null){
			banInfoMap.clear();
		}
		banInfoMap = null;
		
		return true;
	}
	
	public boolean saveToDatabase(){
		
		boolean result = false;
		
		if(magicwpAtt != null){
			short option = magicwpAtt.getOp();
			if(option == Option.Update){
				result = DBManager.getMagicwpAttDao().update(magicwpAtt);
			}else if(option == Option.Insert){
				result = DBManager.getMagicwpAttDao().add(magicwpAtt);
			}
		}
		
		if(magicwpInfoMap != null && magicwpInfoMap.size() > 0){
			for(MagicwpInfo info:magicwpInfoMap.values()){
				short option = info.getOp();
				if(option == Option.Update){
					result = DBManager.getMagicwpInfoDao().update(info);
				}else if(option == Option.Insert){
					result = DBManager.getMagicwpInfoDao().add(info);
				}
			}
		}
		
		if(banInfoMap != null && banInfoMap.size() > 0){
			for(MagicwpBanInfo info:banInfoMap.values()){
				short option = info.getOp();
				if(option == Option.Update){
					result = DBManager.getMagicwpBanInfoDao().update(info);
				}else if(option == Option.Insert){
					result = DBManager.getMagicwpBanInfoDao().add(info);
				}
			}
		}
		
		return true;
	}
}
