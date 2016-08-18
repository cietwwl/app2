package com.chuangyou.xianni.soul.logic.fuse;

import java.util.Map;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 确定使用融合出来的技能
 * @author laofan
 *
 */
public class CommitFuseSkillLogic extends BaseFuseLogic implements IFuseLogic {

	public CommitFuseSkillLogic(int op, GamePlayer player, int index) {
		super(op, player, index);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		Map<Integer, Integer> tempSkillMap = player.getSoulInventory().getTempMap();
		if (!tempSkillMap.containsKey(this.index)) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE, "没有需要确定的融合技能");
			return;
		}
		
		int newSkillID = tempSkillMap.get(this.index);
		this.setNewSkill(newSkillID);
		
		this.sendResult(player);
	
	}

}
