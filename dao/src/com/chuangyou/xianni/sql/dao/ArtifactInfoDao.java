package com.chuangyou.xianni.sql.dao;

import java.util.Map;

import com.chuangyou.xianni.entity.artifact.ArtifactInfo;

public interface ArtifactInfoDao {

	public Map<Integer, ArtifactInfo> getArtifactInfos(long playerId);
	
	public boolean addArtifact(ArtifactInfo info);
	
	public boolean updateArtifact(ArtifactInfo info);
}
