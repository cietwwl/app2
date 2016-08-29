package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.artifact.ArtifactInfo;
import com.chuangyou.xianni.sql.dao.ArtifactInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class ArtifactInfoDaoImpl extends BaseDao implements ArtifactInfoDao {

	@Override
	public Map<Integer, ArtifactInfo> getArtifactInfos(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_atrifact where playerId = ?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return read(sql, params);
	}

	@Override
	public boolean addArtifact(ArtifactInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		info.beginAdd();
		String sql = "insert tb_u_atrifact(playerId,artifactId,level,starLevel,star,starBless,stoneLevel1,stoneExp1,stoneTotalExp1,"
				+ "stoneLevel2,stoneExp2,stoneTotalExp2,stoneLevel3,stoneExp3,stoneTotalExp3,stoneLevel4,stoneExp4,stoneTotalExp4) "
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, info.getArtifactId()));
		params.put(3, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(4, new DbParameter(Types.INTEGER, info.getStarLevel()));
		params.put(5, new DbParameter(Types.INTEGER, info.getStar()));
		params.put(6, new DbParameter(Types.INTEGER, info.getStarBless()));
		params.put(7, new DbParameter(Types.INTEGER, info.getStoneLevel1()));
		params.put(8, new DbParameter(Types.BIGINT, info.getStoneExp1()));
		params.put(9, new DbParameter(Types.BIGINT, info.getStoneTotalExp1()));
		params.put(10, new DbParameter(Types.INTEGER, info.getStoneLevel2()));
		params.put(11, new DbParameter(Types.BIGINT, info.getStoneExp2()));
		params.put(12, new DbParameter(Types.BIGINT, info.getStoneTotalExp2()));
		params.put(13, new DbParameter(Types.INTEGER, info.getStoneLevel3()));
		params.put(14, new DbParameter(Types.BIGINT, info.getStoneExp3()));
		params.put(15, new DbParameter(Types.BIGINT, info.getStoneTotalExp3()));
		params.put(16, new DbParameter(Types.INTEGER, info.getStoneLevel4()));
		params.put(17, new DbParameter(Types.BIGINT, info.getStoneExp4()));
		params.put(18, new DbParameter(Types.BIGINT, info.getStoneTotalExp4()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitAdd(result);
		return false;
	}

	@Override
	public boolean updateArtifact(ArtifactInfo info) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		info.beginUpdate();
		String sql = "update tb_u_atrifact set level=?,starLevel=?,star=?,starBless=?,stoneLevel1=?,stoneExp1=?,stoneTotalExp1=?,"
				+ "stoneLevel2=?,stoneExp2=?,stoneTotalExp2=?,stoneLevel3=?,stoneExp3=?,stoneTotalExp3=?,stoneLevel4=?,stoneExp4=?,stoneTotalExp4=?"
				+ " where playerId=? and artifactId=?";
		Map<Integer, DbParameter> params = new HashMap<>();
		params.put(1, new DbParameter(Types.INTEGER, info.getLevel()));
		params.put(2, new DbParameter(Types.INTEGER, info.getStarLevel()));
		params.put(3, new DbParameter(Types.INTEGER, info.getStar()));
		params.put(4, new DbParameter(Types.INTEGER, info.getStarBless()));
		params.put(5, new DbParameter(Types.INTEGER, info.getStoneLevel1()));
		params.put(6, new DbParameter(Types.BIGINT, info.getStoneExp1()));
		params.put(7, new DbParameter(Types.BIGINT, info.getStoneTotalExp1()));
		params.put(8, new DbParameter(Types.INTEGER, info.getStoneLevel2()));
		params.put(9, new DbParameter(Types.BIGINT, info.getStoneExp2()));
		params.put(10, new DbParameter(Types.BIGINT, info.getStoneTotalExp2()));
		params.put(11, new DbParameter(Types.INTEGER, info.getStoneLevel3()));
		params.put(12, new DbParameter(Types.BIGINT, info.getStoneExp3()));
		params.put(13, new DbParameter(Types.BIGINT, info.getStoneTotalExp3()));
		params.put(14, new DbParameter(Types.INTEGER, info.getStoneLevel4()));
		params.put(15, new DbParameter(Types.BIGINT, info.getStoneExp4()));
		params.put(16, new DbParameter(Types.BIGINT, info.getStoneTotalExp4()));
		
		params.put(17, new DbParameter(Types.BIGINT, info.getPlayerId()));
		params.put(18, new DbParameter(Types.INTEGER, info.getArtifactId()));
		result = execNoneQuery(sql, params) > -1? true: false;
		info.commitUpdate(result);
		return result;
	}
	
	private Map<Integer, ArtifactInfo> read(String sql, Map<Integer, DbParameter> params){
		PreparedStatement pst = execQuery(sql, params);
		ResultSet rs = null;
		Map<Integer, ArtifactInfo> infos = null;
		ArtifactInfo info = null;
		if(pst != null){
			infos = new HashMap<>();
			try {
				rs = pst.executeQuery();
				while(rs.next()){
					info = new ArtifactInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setArtifactId(rs.getInt("artifactId"));
					info.setLevel(rs.getInt("level"));
					info.setStarLevel(rs.getInt("starLevel"));
					info.setStar(rs.getInt("star"));
					info.setStarBless(rs.getInt("starBless"));
					info.setStoneLevel1(rs.getInt("stoneLevel1"));
					info.setStoneExp1(rs.getLong("stoneExp1"));
					info.setStoneTotalExp1(rs.getLong("stoneTotalExp1"));
					
					info.setStoneLevel2(rs.getInt("stoneLevel2"));
					info.setStoneExp2(rs.getLong("stoneExp2"));
					info.setStoneTotalExp2(rs.getLong("stoneTotalExp2"));
					
					info.setStoneLevel3(rs.getInt("stoneLevel3"));
					info.setStoneExp3(rs.getLong("stoneExp3"));
					info.setStoneTotalExp3(rs.getLong("stoneTotalExp3"));
					
					info.setStoneLevel4(rs.getInt("stoneLevel4"));
					info.setStoneExp4(rs.getLong("stoneExp4"));
					info.setStoneTotalExp4(rs.getLong("stoneTotalExp4"));
					info.setOp(Option.None);
					infos.put(info.getArtifactId(), info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				infos = null;
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pst, rs);
			}
		}
		return infos;
	}

}
