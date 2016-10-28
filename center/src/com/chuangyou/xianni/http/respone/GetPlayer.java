package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.User.UserInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayer", desc = "获取玩家数据")
public class GetPlayer implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		Map<String, Object> data = new HashMap<>();
		PlayerInfo playerInfo = null;
		PlayerJoinInfo playerJoinInfo = null;
		int isOnline = 0;
		Guild guild = GuildManager.getIns().getPlayerGuild(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			playerInfo = player.getBasePlayer().getPlayerInfo();
			playerJoinInfo = player.getBasePlayer().getPlayerJoinInfo();
			isOnline = 1;
		} else {
			playerInfo = DBManager.getPlayerInfoDao().getPlayerInfo(playerId);
			playerJoinInfo = DBManager.getPlayerInfoDao().getJoinInfo(playerId);
		}

		UserInfo user = DBManager.getUserDao().getUser(playerInfo.getUserId());
		data.put("nickname", playerInfo.getNickName());
		data.put("createTime", playerInfo.getCreateTime());
		data.put("userName", user.getUserName());
		data.put("accountCreateTime", user.getCreateTime());
		data.put("isOnline", isOnline);
		data.put("lv", playerInfo.getLevel());
		data.put("exp", playerInfo.getExp());
		data.put("vipLv", playerInfo.getVipLevel());
		data.put("vipExp", playerInfo.getVipExp());
		long vipTimeLimit = 0;
		if (playerInfo.getVipTimeLimit() != null) {
			vipTimeLimit = playerInfo.getVipTimeLimit().getTime() - System.currentTimeMillis() < 0 ? 0 : playerInfo.getVipTimeLimit().getTime() - System.currentTimeMillis();
		}
		data.put("vipTimeLimit", vipTimeLimit);
		data.put("cash", playerInfo.getCash());
		data.put("bindCash", playerInfo.getBindCash());
		data.put("money", playerInfo.getMoney());
		data.put("LoginOutTime", user.getLoginTime2());
		data.put("stateLv", playerInfo.getStateLv());
		data.put("fight", playerInfo.getFight());
		data.put("pkVal", playerInfo.getPkVal());
		data.put("soul", playerJoinInfo.getSoul());
		data.put("blood", playerJoinInfo.getBlood());
		data.put("attack", playerJoinInfo.getAttack());
		data.put("defence", playerJoinInfo.getDefence());
		data.put("soulAttack", playerJoinInfo.getSoulAttack());
		data.put("soulDefence", playerJoinInfo.getSoulDefence());
		data.put("accurate", playerJoinInfo.getAccurate());
		data.put("dodge", playerJoinInfo.getDodge());
		data.put("crit", playerJoinInfo.getCrit());
		data.put("critDefence", playerJoinInfo.getCritDefence());
		data.put("critAddtion", playerJoinInfo.getCritAddtion());
		data.put("critCut", playerJoinInfo.getCritCut());
		data.put("bloodAttackAddtion", playerJoinInfo.getAttackAddtion());
		data.put("bloodAttackCut", playerJoinInfo.getAttackCut());
		data.put("soulAttackAddtion", playerJoinInfo.getSoulAttackAddtion());
		data.put("soulAttackCut", playerJoinInfo.getSoulAttackCut());
		data.put("regainSoul", playerJoinInfo.getRegainSoul());
		data.put("regainBlood", playerJoinInfo.getRegainBlood());
		data.put("metal", playerJoinInfo.getMetal());
		data.put("wood", playerJoinInfo.getWood());
		data.put("water", playerJoinInfo.getWater());
		data.put("fire", playerJoinInfo.getFire());
		data.put("earth", playerJoinInfo.getEarth());
		data.put("metalDefence", playerJoinInfo.getMetalDefence());
		data.put("woodDefence", playerJoinInfo.getWoodDefence());
		data.put("waterDefence", playerJoinInfo.getWaterDefence());
		data.put("fireDefence", playerJoinInfo.getFireDefence());
		data.put("earthDefence", playerJoinInfo.getEarthDefence());
		data.put("speed", playerJoinInfo.getSpeed());
		if (guild != null) {
			data.put("guild", guild.getGuildInfo().getName());
			data.put("job", guild.getMember(playerId).getJob());// 职务 GuildJob
			data.put("contributionTotal", guild.getMember(playerId).getContributionTotal());
			data.put("contribution", guild.getMember(playerId).getContribution());
		} else {
			data.put("guild", "无");
			data.put("job", "无");// 职务 GuildJob
			data.put("contributionTotal", 0);
			data.put("contribution", 0);
		}
		data.put("points", playerInfo.getPoints());
		// info.setSoulAttackAddtion(rs.getInt("soulAttackAddtion"));
		// info.setSoulAttackCut(rs.getInt("soulAttackCut"));
		// info.setRegainSoul(rs.getInt("regainSoul"));
		// info.setRegainBlood(rs.getInt("regainBlood"));
		// info.setMetal(rs.getInt("metal"));
		// info.setWood(rs.getInt("wood"));
		// info.setWater(rs.getInt("water"));
		// info.setFire(rs.getInt("fire"));
		// info.setEarth(rs.getInt("earth"));
		// info.setMetalDefence(rs.getInt("metalDefence"));
		// info.setWoodDefence(rs.getInt("woodDefence"));
		// info.setWaterDefence(rs.getInt("waterDefence"));
		// info.setFireDefence(rs.getInt("fireDefence"));
		// info.setEarthDefence(rs.getInt("earthDefence"));
		// info.setSpeed(rs.getInt("speed"));
		// info.setMana(rs.getInt("mana"));

		return HttpResResult.getResult(Code.SUCCESS, data);
	}

}
