package com.chuangyou.xianni.sql.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.arena.FightData;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.sql.dao.PlayerInfoDao;
import com.chuangyou.xianni.sql.db.BaseDao;
import com.chuangyou.xianni.sql.db.DbParameter;

public class PlayerInfoDaoImpl extends BaseDao implements PlayerInfoDao {

	@Override
	public List<FightData> getArenaOpponent(int myFight, int maxFight, int minFight) {
		String sql = "SELECT playerId,fight FROM tb_u_player_info WHERE fight <= " + myFight + " AND fight >= " + minFight
				+ " LIMIT 10	UNION (SELECT playerId,fight FROM tb_u_player_info WHERE fight >" + myFight + " AND fight <= " + maxFight + " LIMIT 10);";
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		List<FightData> result = new ArrayList<>();
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					FightData data = new FightData();
					data.setFight(rs.getInt("fight"));
					data.setPlayerId(rs.getLong("playerId"));
					result.add(data);
				}
			} catch (SQLException e) {
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return result;
	}

	@Override
	public boolean add(PlayerInfo playerInfo) {
		boolean result = false;
		playerInfo.beginAdd();
		String sql = "INSERT INTO tb_u_player_info (playerId,userId,job,nickname,level,exp,totalExp,money,bindCash,cash,vipLevel"
				+ ",fight,skinId,pBagCount,mountId,magicWeaponId,skillStage,repair,battleMode,pkVal,changeBattleModeTime,fashionId,"

				+ "weaponId,wingId,points,vipTimeLimit,vipInterimTimeLimit,vipExp,equipExp,stateLv,avatarEnergy,createTime) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerInfo.getPlayerId()));
		para.put(2, new DbParameter(Types.BIGINT, playerInfo.getUserId()));
		para.put(3, new DbParameter(Types.INTEGER, playerInfo.getJob()));
		para.put(4, new DbParameter(Types.VARCHAR, playerInfo.getNickName()));
		para.put(5, new DbParameter(Types.INTEGER, playerInfo.getLevel()));
		para.put(6, new DbParameter(Types.BIGINT, playerInfo.getExp()));
		para.put(7, new DbParameter(Types.BIGINT, playerInfo.getTotalExp()));
		para.put(8, new DbParameter(Types.BIGINT, playerInfo.getMoney()));
		para.put(9, new DbParameter(Types.INTEGER, playerInfo.getBindCash()));
		para.put(10, new DbParameter(Types.INTEGER, playerInfo.getCash()));
		para.put(11, new DbParameter(Types.INTEGER, playerInfo.getVipLevel()));
		para.put(12, new DbParameter(Types.INTEGER, playerInfo.getFight()));
		para.put(13, new DbParameter(Types.INTEGER, playerInfo.getSkinId()));
		para.put(14, new DbParameter(Types.INTEGER, playerInfo.getpBagCount()));
		para.put(15, new DbParameter(Types.INTEGER, playerInfo.getMountId()));
		para.put(16, new DbParameter(Types.INTEGER, playerInfo.getMagicWeaponId()));
		para.put(17, new DbParameter(Types.INTEGER, playerInfo.getSkillStage()));
		para.put(18, new DbParameter(Types.INTEGER, playerInfo.getRepair()));
		para.put(19, new DbParameter(Types.INTEGER, playerInfo.getBattleMode()));
		para.put(20, new DbParameter(Types.INTEGER, playerInfo.getPkVal()));
		para.put(21, new DbParameter(Types.BIGINT, playerInfo.getChangeBattleModeTime()));

		para.put(22, new DbParameter(Types.INTEGER, playerInfo.getFashionId()));
		para.put(23, new DbParameter(Types.INTEGER, playerInfo.getWeaponId()));
		para.put(24, new DbParameter(Types.INTEGER, playerInfo.getWingId()));
		para.put(25, new DbParameter(Types.INTEGER, playerInfo.getPoints()));

		para.put(26, new DbParameter(Types.DATE, playerInfo.getVipTimeLimit()));
		para.put(27, new DbParameter(Types.DATE, playerInfo.getVipInterimTimeLimit()));
		para.put(28, new DbParameter(Types.INTEGER, playerInfo.getVipExp()));

		para.put(29, new DbParameter(Types.BIGINT, playerInfo.getEquipExp()));
		para.put(30, new DbParameter(Types.INTEGER, playerInfo.getStateLv()));
		para.put(31, new DbParameter(Types.INTEGER, playerInfo.getAvatarEnergy()));
		para.put(31, new DbParameter(Types.DATE, playerInfo.getCreateTime()));

		result = execNoneQuery(sql, para) > -1 ? true : false;
		playerInfo.commitAdd(result);
		return result;
	}

	@Override
	public boolean update(PlayerInfo playerInfo) {
		boolean result = false;
		playerInfo.beginUpdate();
		String sql = "update tb_u_player_info set nickname=?,level=?,exp=?,totalExp=?,money=?" + ",bindCash=?,cash=?,vipLevel=?,fight=?,skinId=?,pBagCount=?,mountId=?,magicWeaponId=?,skillStage=?,"
				+ "repair=?,battleMode=?,pkVal=?,changeBattleModeTime=?,fashionId=?,weaponId=?,wingId=?,points=?,vipTimeLimit=?,vipInterimTimeLimit=?,vipExp=?,equipExp=?,stateLv=?,avatarEnergy=? where playerId=?";

		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.VARCHAR, playerInfo.getNickName()));
		para.put(2, new DbParameter(Types.INTEGER, playerInfo.getLevel()));
		para.put(3, new DbParameter(Types.BIGINT, playerInfo.getExp()));
		para.put(4, new DbParameter(Types.BIGINT, playerInfo.getTotalExp()));
		para.put(5, new DbParameter(Types.BIGINT, playerInfo.getMoney()));
		para.put(6, new DbParameter(Types.INTEGER, playerInfo.getBindCash()));
		para.put(7, new DbParameter(Types.INTEGER, playerInfo.getCash()));
		para.put(8, new DbParameter(Types.INTEGER, playerInfo.getVipLevel()));
		para.put(9, new DbParameter(Types.INTEGER, playerInfo.getFight()));
		para.put(10, new DbParameter(Types.INTEGER, playerInfo.getSkinId()));
		para.put(11, new DbParameter(Types.INTEGER, playerInfo.getpBagCount()));
		para.put(12, new DbParameter(Types.INTEGER, playerInfo.getMountId()));
		para.put(13, new DbParameter(Types.INTEGER, playerInfo.getMagicWeaponId()));
		para.put(14, new DbParameter(Types.INTEGER, playerInfo.getSkillStage()));
		para.put(15, new DbParameter(Types.BIGINT, playerInfo.getRepair()));

		para.put(16, new DbParameter(Types.INTEGER, playerInfo.getBattleMode()));
		para.put(17, new DbParameter(Types.INTEGER, playerInfo.getPkVal()));
		para.put(18, new DbParameter(Types.BIGINT, playerInfo.getChangeBattleModeTime()));

		para.put(19, new DbParameter(Types.INTEGER, playerInfo.getFashionId()));
		para.put(20, new DbParameter(Types.INTEGER, playerInfo.getWeaponId()));
		para.put(21, new DbParameter(Types.INTEGER, playerInfo.getWingId()));
		para.put(22, new DbParameter(Types.INTEGER, playerInfo.getPoints()));

		para.put(23, new DbParameter(Types.DATE, playerInfo.getVipTimeLimit()));
		para.put(24, new DbParameter(Types.DATE, playerInfo.getVipInterimTimeLimit()));
		para.put(25, new DbParameter(Types.INTEGER, playerInfo.getVipExp()));

		para.put(26, new DbParameter(Types.BIGINT, playerInfo.getEquipExp()));
		para.put(27, new DbParameter(Types.INTEGER, playerInfo.getStateLv()));
		para.put(28, new DbParameter(Types.INTEGER, playerInfo.getAvatarEnergy()));
		para.put(29, new DbParameter(Types.BIGINT, playerInfo.getPlayerId()));

		result = execNoneQuery(sql, para) > -1 ? true : false;

		playerInfo.commitUpdate(result);
		return result;
	}

	@Override
	public PlayerInfo getPlayerInfo(long playerId) {
		String sql = "SELECT * FROM tb_u_player_info WHERE playerId=" + playerId;
		List<PlayerInfo> result = read(sql, null);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public PlayerInfo getPlayerInfo(String nickName) {
		String sql = "SELECT * FROM tb_u_player_info WHERE nickName='" + nickName + "'";
		List<PlayerInfo> result = read(sql, null);
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	private List<PlayerInfo> read(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		List<PlayerInfo> infos = null;
		PlayerInfo info = null;
		if (pstmt != null) {
			infos = new ArrayList<PlayerInfo>();
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new PlayerInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setUserId(rs.getLong("userId"));
					info.setJob(rs.getInt("job"));
					info.setNickName(rs.getString("nickName"));
					info.setLevel(rs.getInt("level"));
					info.setExp(rs.getLong("exp"));
					info.setTotalExp(rs.getLong("totalExp"));
					info.setMoney(rs.getLong("money"));
					info.setBindCash(rs.getInt("bindCash"));
					info.setCash(rs.getInt("cash"));
					info.setVipLevel(rs.getShort("vipLevel"));
					info.setFight(rs.getInt("fight"));
					info.setSkinId(rs.getInt("skinId"));
					info.setpBagCount(rs.getInt("pBagCount"));
					info.setMountId(rs.getInt("mountId"));
					info.setMagicWeaponId(rs.getInt("magicWeaponId"));
					info.setSkillStage(rs.getInt("skillStage"));
					info.setRepair(rs.getInt("repair"));
					info.setBattleMode(rs.getInt("battleMode"));
					info.setPkVal(rs.getInt("pkVal"));
					info.setChangeBattleModeTime(rs.getLong("changeBattleModeTime"));

					info.setFashionId(rs.getInt("fashionId"));
					info.setWeaponId(rs.getInt("weaponId"));
					info.setWingId(rs.getInt("wingId"));
					info.setPoints(rs.getInt("points"));
					info.setEquipExp(rs.getLong("equipExp"));

					info.setVipTimeLimit(rs.getDate("vipTimeLimit"));
					info.setVipInterimTimeLimit(rs.getDate("vipInterimTimeLimit"));
					info.setVipExp(rs.getInt("vipExp"));

					info.setStateLv(rs.getInt("stateLv"));
					info.setAvatarEnergy(rs.getInt("avatarEnergy"));

					info.setOp(Option.None);
					info.setCreateTime(rs.getDate("createDateTime"));
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return infos;
	}

	@Override
	public List<PlayerInfo> getByUserId(long userId) {
		String sql = "SELECT * FROM tb_u_player_info WHERE userId = " + userId;
		return read(sql, null);
	}

	@Override
	public List<PlayerInfo> getByNickName(String nickName) {
		String sql = "select * from tb_u_player_info where nickName = '" + nickName + "'";
		return read(sql, null);
	}

	@Override
	public boolean addJoinInfo(PlayerJoinInfo playerJoinInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		playerJoinInfo.beginAdd();
		String sql = "insert tb_u_player_join_info(playerId,curSoul,curBlood,soul,blood,attack,defence,soulAttack,soulDefence,"
				+ "accurate,dodge,crit,critDefence,critAddtion,critCut,bloodAttackAddtion,bloodAttackCut,"
				+ "soulAttackAddtion,soulAttackCut,regainSoul,regainBlood,metal,wood,water,fire,earth,metalDefence,woodDefence," + "waterDefence,fireDefence,earthDefence,speed,mana) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerJoinInfo.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, playerJoinInfo.getCurSoul()));
		params.put(3, new DbParameter(Types.INTEGER, playerJoinInfo.getCurBlood()));
		params.put(4, new DbParameter(Types.INTEGER, playerJoinInfo.getSoul()));
		params.put(5, new DbParameter(Types.INTEGER, playerJoinInfo.getBlood()));

		params.put(6, new DbParameter(Types.INTEGER, playerJoinInfo.getAttack()));
		params.put(7, new DbParameter(Types.INTEGER, playerJoinInfo.getDefence()));
		params.put(8, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulAttack()));
		params.put(9, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulDefence()));
		params.put(10, new DbParameter(Types.INTEGER, playerJoinInfo.getAccurate()));
		params.put(11, new DbParameter(Types.INTEGER, playerJoinInfo.getDodge()));
		params.put(12, new DbParameter(Types.INTEGER, playerJoinInfo.getCrit()));
		params.put(13, new DbParameter(Types.INTEGER, playerJoinInfo.getCritDefence()));
		params.put(14, new DbParameter(Types.INTEGER, playerJoinInfo.getCritAddtion()));
		params.put(15, new DbParameter(Types.INTEGER, playerJoinInfo.getCritCut()));
		params.put(16, new DbParameter(Types.INTEGER, playerJoinInfo.getAttackAddtion()));
		params.put(17, new DbParameter(Types.INTEGER, playerJoinInfo.getAttackCut()));
		params.put(18, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulAttackAddtion()));
		params.put(19, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulAttackCut()));
		params.put(20, new DbParameter(Types.INTEGER, playerJoinInfo.getRegainSoul()));
		params.put(21, new DbParameter(Types.INTEGER, playerJoinInfo.getRegainBlood()));
		params.put(22, new DbParameter(Types.INTEGER, playerJoinInfo.getMetal()));
		params.put(23, new DbParameter(Types.INTEGER, playerJoinInfo.getWood()));
		params.put(24, new DbParameter(Types.INTEGER, playerJoinInfo.getWater()));
		params.put(25, new DbParameter(Types.INTEGER, playerJoinInfo.getFire()));
		params.put(26, new DbParameter(Types.INTEGER, playerJoinInfo.getEarth()));
		params.put(27, new DbParameter(Types.INTEGER, playerJoinInfo.getMetalDefence()));
		params.put(28, new DbParameter(Types.INTEGER, playerJoinInfo.getWoodDefence()));
		params.put(29, new DbParameter(Types.INTEGER, playerJoinInfo.getWaterDefence()));
		params.put(30, new DbParameter(Types.INTEGER, playerJoinInfo.getFireDefence()));
		params.put(31, new DbParameter(Types.INTEGER, playerJoinInfo.getEarthDefence()));
		params.put(32, new DbParameter(Types.INTEGER, playerJoinInfo.getSpeed()));
		params.put(33, new DbParameter(Types.INTEGER, playerJoinInfo.getMana()));
		result = execNoneQuery(sql, params) > -1 ? true : false;
		playerJoinInfo.commitAdd(result);
		return result;
	}

	@Override
	public boolean updateJoinInfo(PlayerJoinInfo playerJoinInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		playerJoinInfo.beginUpdate();
		String sql = "update tb_u_player_join_info set curSoul=?,curBlood=?,soul=?,blood=?,attack=?,defence=?,soulAttack=?,soulDefence=?,"
				+ "accurate=?,dodge=?,crit=?,critDefence=?,critAddtion=?,critCut=?,bloodAttackAddtion=?,bloodAttackCut=?,"
				+ "soulAttackAddtion=?,soulAttackCut=?,regainSoul=?,regainBlood=?,metal=?,wood=?,water=?,fire=?,earth=?,metalDefence=?,woodDefence=?,"
				+ "waterDefence=?,fireDefence=?,earthDefence=?,speed=?,mana=? where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, playerJoinInfo.getCurSoul()));
		params.put(2, new DbParameter(Types.INTEGER, playerJoinInfo.getCurBlood()));
		params.put(3, new DbParameter(Types.INTEGER, playerJoinInfo.getSoul()));
		params.put(4, new DbParameter(Types.INTEGER, playerJoinInfo.getBlood()));

		params.put(5, new DbParameter(Types.INTEGER, playerJoinInfo.getAttack()));
		params.put(6, new DbParameter(Types.INTEGER, playerJoinInfo.getDefence()));
		params.put(7, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulAttack()));
		params.put(8, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulDefence()));
		params.put(9, new DbParameter(Types.INTEGER, playerJoinInfo.getAccurate()));
		params.put(10, new DbParameter(Types.INTEGER, playerJoinInfo.getDodge()));
		params.put(11, new DbParameter(Types.INTEGER, playerJoinInfo.getCrit()));
		params.put(12, new DbParameter(Types.INTEGER, playerJoinInfo.getCritDefence()));
		params.put(13, new DbParameter(Types.INTEGER, playerJoinInfo.getCritAddtion()));
		params.put(14, new DbParameter(Types.INTEGER, playerJoinInfo.getCritCut()));
		params.put(15, new DbParameter(Types.INTEGER, playerJoinInfo.getAttackAddtion()));
		params.put(16, new DbParameter(Types.INTEGER, playerJoinInfo.getAttackCut()));
		params.put(17, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulAttackAddtion()));
		params.put(18, new DbParameter(Types.INTEGER, playerJoinInfo.getSoulAttackCut()));
		params.put(19, new DbParameter(Types.INTEGER, playerJoinInfo.getRegainSoul()));
		params.put(20, new DbParameter(Types.INTEGER, playerJoinInfo.getRegainBlood()));
		params.put(21, new DbParameter(Types.INTEGER, playerJoinInfo.getMetal()));
		params.put(22, new DbParameter(Types.INTEGER, playerJoinInfo.getWood()));
		params.put(23, new DbParameter(Types.INTEGER, playerJoinInfo.getWater()));
		params.put(24, new DbParameter(Types.INTEGER, playerJoinInfo.getFire()));
		params.put(25, new DbParameter(Types.INTEGER, playerJoinInfo.getEarth()));
		params.put(26, new DbParameter(Types.INTEGER, playerJoinInfo.getMetalDefence()));
		params.put(27, new DbParameter(Types.INTEGER, playerJoinInfo.getWoodDefence()));
		params.put(28, new DbParameter(Types.INTEGER, playerJoinInfo.getWaterDefence()));
		params.put(29, new DbParameter(Types.INTEGER, playerJoinInfo.getFireDefence()));
		params.put(30, new DbParameter(Types.INTEGER, playerJoinInfo.getEarthDefence()));
		params.put(31, new DbParameter(Types.INTEGER, playerJoinInfo.getSpeed()));
		params.put(32, new DbParameter(Types.INTEGER, playerJoinInfo.getMana()));

		params.put(33, new DbParameter(Types.BIGINT, playerJoinInfo.getPlayerId()));
		result = execNoneQuery(sql, params) > -1 ? true : false;
		playerJoinInfo.commitUpdate(result);
		return result;
	}

	@Override
	public PlayerJoinInfo getJoinInfo(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_player_join_info where playerId=?";
		Map<Integer, DbParameter> para = new HashMap<Integer, DbParameter>();
		para.put(1, new DbParameter(Types.BIGINT, playerId));

		return readJoinInfo(sql, para);
	}

	private PlayerJoinInfo readJoinInfo(String sqlText, Map<Integer, DbParameter> para) {
		PreparedStatement pstmt = execQuery(sqlText, para);
		ResultSet rs = null;
		PlayerJoinInfo info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				if (rs.next()) {
					info = new PlayerJoinInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setCurSoul(rs.getInt("curSoul"));
					info.setCurBlood(rs.getInt("curBlood"));
					info.setSoul(rs.getInt("soul"));
					info.setBlood(rs.getInt("blood"));
					info.setAttack(rs.getInt("attack"));
					info.setDefence(rs.getInt("defence"));
					info.setSoulAttack(rs.getInt("soulAttack"));
					info.setSoulDefence(rs.getInt("soulDefence"));
					info.setAccurate(rs.getInt("accurate"));
					info.setDodge(rs.getInt("dodge"));
					info.setCrit(rs.getInt("crit"));
					info.setCritDefence(rs.getInt("critDefence"));
					info.setCritAddtion(rs.getInt("critAddtion"));
					info.setCritCut(rs.getInt("critCut"));
					info.setAttackAddtion(rs.getInt("bloodAttackAddtion"));
					info.setAttackCut(rs.getInt("bloodAttackCut"));
					info.setSoulAttackAddtion(rs.getInt("soulAttackAddtion"));
					info.setSoulAttackCut(rs.getInt("soulAttackCut"));
					info.setRegainSoul(rs.getInt("regainSoul"));
					info.setRegainBlood(rs.getInt("regainBlood"));
					info.setMetal(rs.getInt("metal"));
					info.setWood(rs.getInt("wood"));
					info.setWater(rs.getInt("water"));
					info.setFire(rs.getInt("fire"));
					info.setEarth(rs.getInt("earth"));
					info.setMetalDefence(rs.getInt("metalDefence"));
					info.setWoodDefence(rs.getInt("woodDefence"));
					info.setWaterDefence(rs.getInt("waterDefence"));
					info.setFireDefence(rs.getInt("fireDefence"));
					info.setEarthDefence(rs.getInt("earthDefence"));
					info.setSpeed(rs.getInt("speed"));
					info.setMana(rs.getInt("mana"));

					info.setOp(Option.None);
				}
			} catch (SQLException e) {
				info = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return info;
	}

	@Override
	public boolean addTimeInfo(PlayerTimeInfo playerTimeInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = "replace into tb_u_player_time_info(playerId,sigleCampCount,challengeCampCount,personalTruckerProtCount,presonalTruckerExtReward,presonalTruckerExtExp,addExpByTruckBroken,resetTime,offlineTime) values(?,?,?,?,?,?,?,?,?)";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerTimeInfo.getPlayerId()));
		params.put(2, new DbParameter(Types.INTEGER, playerTimeInfo.getSigleCampCount()));
		params.put(3, new DbParameter(Types.INTEGER, playerTimeInfo.getChallengeCampCount()));
		params.put(4, new DbParameter(Types.INTEGER, playerTimeInfo.getPersonalTruckerProtCount()));
		params.put(5, new DbParameter(Types.INTEGER, playerTimeInfo.getPresonalTruckerExtReward()));
		params.put(6, new DbParameter(Types.INTEGER, playerTimeInfo.getPresonalTruckerExtExp()));
		params.put(7, new DbParameter(Types.INTEGER, playerTimeInfo.getAddExpByTruckBroken()));
		params.put(8, new DbParameter(Types.TIMESTAMP, playerTimeInfo.getResetTime()));
		params.put(9, new DbParameter(Types.TIMESTAMP, playerTimeInfo.getOfflineTime()));
		result = execNoneQuery(sql, params) > -1 ? true : false;
		playerTimeInfo.setOp(Option.None);
		return result;
	}

	@Override
	public boolean updateTimeInfo(PlayerTimeInfo playerTimeInfo) {
		// TODO Auto-generated method stub
		boolean result = false;
		playerTimeInfo.beginUpdate();
		String sql = "update tb_u_player_time_info set sigleCampCount=?,challengeCampCount=?,personalTruckerProtCount=?,presonalTruckerExtReward=?,presonalTruckerExtExp=?,addExpByTruckBroken=?,resetTime=?,offlineTime=? where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.INTEGER, playerTimeInfo.getSigleCampCount()));
		params.put(2, new DbParameter(Types.INTEGER, playerTimeInfo.getChallengeCampCount()));
		params.put(3, new DbParameter(Types.INTEGER, playerTimeInfo.getPersonalTruckerProtCount()));
		params.put(4, new DbParameter(Types.INTEGER, playerTimeInfo.getPresonalTruckerExtReward()));
		params.put(5, new DbParameter(Types.INTEGER, playerTimeInfo.getPresonalTruckerExtExp()));
		params.put(6, new DbParameter(Types.INTEGER, playerTimeInfo.getAddExpByTruckBroken()));
		params.put(7, new DbParameter(Types.TIMESTAMP, playerTimeInfo.getResetTime()));
		params.put(8, new DbParameter(Types.TIMESTAMP, playerTimeInfo.getOfflineTime()));
		params.put(9, new DbParameter(Types.BIGINT, playerTimeInfo.getPlayerId()));
		result = execNoneQuery(sql, params) > -1 ? true : false;
		playerTimeInfo.commitUpdate(result);
		return result;
	}

	@Override
	public PlayerTimeInfo getTimeInfo(long playerId) {
		// TODO Auto-generated method stub
		String sql = "select * from tb_u_player_time_info where playerId=?";
		Map<Integer, DbParameter> params = new HashMap<Integer, DbParameter>();
		params.put(1, new DbParameter(Types.BIGINT, playerId));
		return readTimeInfo(sql, params);
	}

	private PlayerTimeInfo readTimeInfo(String sqlText, Map<Integer, DbParameter> params) {
		PreparedStatement pstmt = execQuery(sqlText, params);
		ResultSet rs = null;
		PlayerTimeInfo info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				if (rs.next()) {
					info = new PlayerTimeInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setSigleCampCount(rs.getInt("sigleCampCount"));
					info.setChallengeCampCount(rs.getInt("challengeCampCount"));
					info.setPersonalTruckerProtCount(rs.getInt("personalTruckerProtCount"));
					info.setPresonalTruckerExtExp(rs.getInt("presonalTruckerExtExp"));
					info.setPresonalTruckerExtReward(rs.getInt("presonalTruckerExtReward"));
					info.setAddExpByTruckBroken(rs.getInt("addExpByTruckBroken"));
					Timestamp time = rs.getTimestamp("resetTime");
					if (time != null) {
						info.setResetTime(new Date(time.getTime()));
					}
					Timestamp offlineTime = rs.getTimestamp("offlineTime");
					if (offlineTime != null) {
						info.setOfflineTime(new Date(offlineTime.getTime()));
					}
					info.setOp(Option.None);
				}
			} catch (SQLException e) {
				info = null;
				Log.error("执行出错" + sqlText, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return info;
	}

	@Override
	public long getMaxPlayerId() {
		String sql = "SELECT MAX(playerId) AS maxid FROM tb_u_player_info";
		long maxId = 0;
		PreparedStatement pstmt = execQuery(sql, null);
		ResultSet rs = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					maxId = rs.getLong("maxid");
					break;
				}
			} catch (SQLException e) {
				Log.error("执行出错" + sql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		return maxId == 0 ? 1 : maxId + 1;
	}

	@Override
	public List<PlayerInfo> getPlayerList(String account, String user, int userType, Date regBeginTime, Date regEndTime, int startLv, int endLv, int page, int pageSize) {
		List<PlayerInfo> infos = new ArrayList<PlayerInfo>();
		StringBuffer strSql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		strSql.append("SELECT * from tb_u_player_info a LEFT JOIN tb_u_user_info b on a.userId=b.userId");
		if (account != null) {
			where.append(" b.userName='" + account + "'");
		}
		if (user != null) {
			if (where.length() > 0) {
				where.append(" and");
			}
			if (userType == 1) {// 昵称查询
				where.append(" a.nickName='" + user + "'");
			} else {
				where.append(" a.playerId=" + user);
			}
		}
		if (regBeginTime != null) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" createTime>='" + TimeUtil.getDateFormat(regBeginTime) + "'");
		}
		if (regEndTime != null) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" createTime<='" + TimeUtil.getDateFormat(regEndTime) + "'");
		}
		if (startLv >= 0) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" level>=" + startLv);
		}
		if (endLv >= 0) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" level<=" + endLv);
		}

		if (where.length() > 0) {
			strSql.append(" where ");
			strSql.append(where);
		}

		strSql.append(" limit " + (page - 1) * pageSize + "," + pageSize);
		strSql.append(";");

		PreparedStatement pstmt = execQuery(strSql.toString(), null);
		ResultSet rs = null;
		PlayerInfo info = null;
		if (pstmt != null) {
			try {
				rs = pstmt.executeQuery();
				while (rs.next()) {
					info = new PlayerInfo();
					info.setPlayerId(rs.getLong("playerId"));
					info.setUserId(rs.getLong("userId"));
					info.setJob(rs.getInt("job"));
					info.setNickName(rs.getString("nickName"));
					info.setLevel(rs.getInt("level"));
					info.setExp(rs.getLong("exp"));
					info.setTotalExp(rs.getLong("totalExp"));
					info.setMoney(rs.getLong("money"));
					info.setBindCash(rs.getInt("bindCash"));
					info.setCash(rs.getInt("cash"));
					info.setVipLevel(rs.getShort("vipLevel"));
					info.setFight(rs.getInt("fight"));
					info.setSkinId(rs.getInt("skinId"));
					info.setpBagCount(rs.getInt("pBagCount"));
					info.setMountId(rs.getInt("mountId"));
					info.setMagicWeaponId(rs.getInt("magicWeaponId"));
					info.setSkillStage(rs.getInt("skillStage"));
					info.setRepair(rs.getInt("repair"));
					info.setBattleMode(rs.getInt("battleMode"));
					info.setPkVal(rs.getInt("pkVal"));
					info.setChangeBattleModeTime(rs.getLong("changeBattleModeTime"));
					info.setFashionId(rs.getInt("fashionId"));
					info.setWeaponId(rs.getInt("weaponId"));
					info.setWingId(rs.getInt("wingId"));
					info.setPoints(rs.getInt("points"));
					info.setEquipExp(rs.getLong("equipExp"));
					info.setVipTimeLimit(rs.getDate("vipTimeLimit"));
					info.setVipInterimTimeLimit(rs.getDate("vipInterimTimeLimit"));
					info.setVipExp(rs.getInt("vipExp"));
					info.setStateLv(rs.getInt("stateLv"));
					info.setCreateTime(rs.getDate("createDateTime"));
					info.setAccount(rs.getString("userName"));
					infos.add(info);
				}
			} catch (SQLException e) {
				infos = null;
				Log.error("执行出错" + strSql, e);
			} finally {
				closeConn(pstmt, rs);
			}
		}
		System.out.println(strSql);
		return infos;
	}

	public int getCount(String account, String user, int userType, Date regBeginTime, Date regEndTime, int startLv, int endLv) {
		StringBuffer strSql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		strSql.append("SELECT count(*) as count from tb_u_player_info a LEFT JOIN tb_u_user_info b on a.userId=b.userId");
		if (account != null) {
			where.append(" b.userName='" + account + "'");
		}
		if (user != null) {
			if (where.length() > 0) {
				where.append(" and");
			}
			if (userType == 1) {// 昵称查询
				where.append(" a.nickName='" + user + "'");
			} else {
				where.append(" a.userId=" + user);
			}
		}
		if (regBeginTime != null) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" createTime>='" + TimeUtil.getDateFormat(regBeginTime) + "'");
		}
		if (regEndTime != null) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" createTime<='" + TimeUtil.getDateFormat(regEndTime) + "'");
		}
		if (startLv >= 0) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" level>=" + startLv);
		}
		if (endLv >= 0) {
			if (where.length() > 0) {
				where.append(" and");
			}
			where.append(" level<=" + endLv);
		}
		if (where.length() > 0) {
			strSql.append(" where ");
			strSql.append(where);
		}
		strSql.append(";");
		PreparedStatement pstmt = execQuery(strSql.toString(), null);
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("count");
			}
		} catch (SQLException e) {
			Log.error("执行出错" + strSql.toString(), e);
		} finally {
			closeConn(pstmt, rs);
		}
		return 0;
	}

}
