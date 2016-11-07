package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.hero.HeroSkill;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.skill.SkillTempateInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.template.SkillTempMgr;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayerSkill", desc = "玩家查询")
public class GetPlayerSkillRespone implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
		Map<String, Object> data = new HashMap<>();
		if (info != null) {
			long playerId = info.getPlayerId();
			GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
			if (player != null && player.getPlayerState() == PlayerState.ONLINE) {

			}
			int lv = info.getLevel();
			int job = info.getJob();
			data.put("lv", lv);
			data.put("job", job);
			Map<Integer, HeroSkill> heroSkills = DBManager.getHeroSkillDao().getAll(playerId);
			List<Map<String, Object>> list = new ArrayList<>();
			for (Entry<Integer, HeroSkill> heroSkill : heroSkills.entrySet()) {
				SkillTempateInfo temp = SkillTempMgr.getSkillTemp(heroSkill.getValue().getSkillId());// 技能配置
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("skillLv", temp.getLevel());
				m.put("skillName", temp.getTemplateName());
				list.add(m);
			}
			data.put("list", list);
		}
		return HttpResResult.getResult(Code.SUCCESS, data);
	}

}
