package com.chuangyou.xianni.artifact;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.artifact.manager.ArtifactManager;
import com.chuangyou.xianni.artifact.template.ArtifactTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.artifact.ArtifactInfo;
import com.chuangyou.xianni.entity.artifact.ArtifactInfoCfg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.skill.template.SimpleProperty;
import com.chuangyou.xianni.sql.dao.DBManager;

public class ArtifactInventory extends AbstractEvent implements IInventory {

	private GamePlayer player;
	
	private Map<Integer, ArtifactInfo> artifactMap;
	
	public ArtifactInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	public Map<Integer, ArtifactInfo> getArtifactMap() {
		if(artifactMap == null){
			artifactMap = new HashMap<>();
		}
		Map<Integer, ArtifactInfoCfg> artifactCfgMap = ArtifactTemplateMgr.getArtifactMap();
		for(int artifactId: artifactCfgMap.keySet()){
			if(!artifactMap.containsKey(artifactId)){
				ArtifactInfo info = new ArtifactInfo(player.getPlayerId(), artifactId);
				info.setOp(Option.Insert);
				artifactMap.put(artifactId, info);
			}
		}
		return artifactMap;
	}

	@Override
	public boolean loadFromDataBase() {
		// TODO Auto-generated method stub
		artifactMap = DBManager.getArtifactInfoDao().getArtifactInfos(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		// TODO Auto-generated method stub
		player = null;
		
		if(artifactMap != null){
			artifactMap.clear();
		}
		artifactMap = null;
		
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		// TODO Auto-generated method stub
		if(artifactMap != null && artifactMap.size() > 0){
			for(ArtifactInfo info: artifactMap.values()){
				short option = info.getOp();
				if(option == Option.Insert){
					DBManager.getArtifactInfoDao().addArtifact(info);
				}else if(option == Option.Update){
					DBManager.getArtifactInfoDao().updateArtifact(info);
				}
			}
		}
		return true;
	}
	
	public void computeProperty(BaseProperty artifactData, BaseProperty artifactPer){
		Map<Integer, Integer> attMap = ArtifactManager.computeArtifactAtt(player);
		for(int attType:attMap.keySet()){
			SimpleProperty property = SkillUtil.readPro(attType, attMap.get(attType));
			if(property.isPre()){
				SkillUtil.joinPro(artifactPer, property.getType(), property.getValue());
			}else{
				SkillUtil.joinPro(artifactData, property.getType(), property.getValue());
			}
		}
	}
	
	public void updateProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty artifactData = new BaseProperty();
			BaseProperty artifactPer = new BaseProperty();
			// 加入技能属性
			computeProperty(artifactData, artifactPer);
			player.getArmyInventory().getHero().addArtifact(artifactData, artifactPer);
			player.getArmyInventory().updateProperty();
		}
	}

}
