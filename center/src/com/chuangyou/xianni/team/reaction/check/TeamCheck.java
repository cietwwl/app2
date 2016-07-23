package com.chuangyou.xianni.team.reaction.check;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.struct.Team;

/**
 * 队伍检查器
 * @author laofan
 *
 */
public class TeamCheck {

	/**
	 * 检查一下其人入队是否合法
	 * @return
	 */
	public static boolean check(GamePlayer player,long agreeId,short protocol,Team t){
		if (TeamMgr.getPlayerTeamMap().containsKey(agreeId)) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_SomeBody_Has_Team, protocol, "对方已经有队伍啦");
			return false;
		}
		if (t.isFull()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Team_Members_Full, protocol, "组队人数已满");
			return false;
		}
		return true;
	}
}
