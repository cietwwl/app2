package com.chuangyou.xianni.sql.dao;

import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.artifact.ArtifactGradeupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactInfoCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelLevelCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactJewelSuitCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactLevelupCfg;
import com.chuangyou.xianni.entity.artifact.ArtifactSuitCfg;

public interface ArtifactConfigDao {

	public Map<Integer, ArtifactInfoCfg> getArtifactInfo();
	
	public Map<Long, ArtifactLevelupCfg> getArtifactLevel();
	
	public Map<Long, ArtifactGradeupCfg> getArtifactGrade();
	
	public List<ArtifactSuitCfg> getArtifactSuit();
	
	public Map<Integer, List<ArtifactJewelLevelCfg>> getArtifactJewelLevel();
	
	public List<ArtifactJewelSuitCfg> getArtifactJewelSuit();
}
