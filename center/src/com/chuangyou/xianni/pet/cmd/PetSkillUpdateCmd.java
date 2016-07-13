package com.chuangyou.xianni.pet.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.pet.PetSkill;
import com.chuangyou.xianni.pet.manager.PetAttManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_PET_SKILL_UPDATE, desc="宠物技能信息")
public class PetSkillUpdateCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, PetSkill> petSkillMap = player.getPetInventory().getPetSkillMap();
		List<PetSkill> petSkillList = new ArrayList<>(petSkillMap.values());
		PetAttManager.updateSkill(player, petSkillList.toArray(new PetSkill[petSkillList.size()]));
	}

}
