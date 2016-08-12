package com.chuangyou.xianni.fashion;

import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.fashion.FashionInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.fashion.manager.FashionManager;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;

public class FashionInventory extends AbstractEvent implements IInventory {

	private GamePlayer player;
	
	private Map<Integer, FashionInfo> fashionMap;
	
	public FashionInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	/**
	 * 获取时装信息
	 * @param fashionId
	 * @return
	 */
	public FashionInfo getFashion(int fashionId){
		return fashionMap.get(fashionId);
	}
	
	/**
	 * 激活时装
	 * @param info
	 * @return
	 */
	public boolean addFashion(FashionInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		if(this.fashionMap.get(info.getFashionId()) != null){
			info.setOp(Option.Update);
		}else{
			info.setOp(Option.Insert);
		}
		this.fashionMap.put(info.getFashionId(), info);
		return true;
	}
	
	/**
	 * 更新时装信息
	 * @param info
	 * @return
	 */
	public boolean updateFashion(FashionInfo info){
		if(info.getPlayerId() != player.getPlayerId()) return false;
		info.setOp(Option.Update);
		return true;
	}
	
	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		fashionMap = DBManager.getFashionInfoDao().getAll(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		player = null;
		
		if(fashionMap != null){
			fashionMap.clear();
		}
		fashionMap = null;
		
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(fashionMap != null && fashionMap.size() > 0){
			for(FashionInfo info: fashionMap.values()){
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getFashionInfoDao().add(info);
				}else if(option == Option.Update){
					DBManager.getFashionInfoDao().update(info);
				}
			}
		}
		return true;
	}
	public Map<Integer, FashionInfo> getFashionMap() {
		return fashionMap;
	}
	
	public void computeFashionAtt(BaseProperty fashionData, BaseProperty fashionPer){
		Map<Integer, Integer> attMap = FashionManager.getAttValMap(player);
		for(Integer key: attMap.keySet()){
			SimpleProperty property = SkillUtil.readPro(key, attMap.get(key));
			if(property.isPre()){
				SkillUtil.joinPro(fashionPer, property.getType(), property.getValue());
			}else{
				SkillUtil.joinPro(fashionData, property.getType(), property.getValue());
			}
		}
	}
	
	public void updateProperty(){
		if(player.getArmyInventory() != null){
			BaseProperty fashionData = new BaseProperty();
			BaseProperty fashionPer = new BaseProperty();
			
			computeFashionAtt(fashionData, fashionPer);
			player.getArmyInventory().getHero().addFashion(fashionData, fashionPer);
			player.getArmyInventory().updateProperty();
		}
	}

}
