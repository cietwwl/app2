package com.chuangyou.xianni.warfield.spawn;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.entity.spawn.NpcInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.NPC;
import com.chuangyou.xianni.role.template.NpcInfoTemplateMgr;
import com.chuangyou.xianni.warfield.field.Field;

public class NpcSpawnNode extends SpwanNode {

	public NpcSpawnNode(SpawnInfo spwanInfo, Field field) {
		super(spwanInfo, field);
	}

	@Override
	public void start() {
		super.start();
		System.out.println("spawn - skin = " + spwanInfo.getEntityId());
		NpcInfo npcInfo = NpcInfoTemplateMgr.npcInfoTemps.get(spwanInfo.getEntityId());
		if (npcInfo != null) {
			NPC npc = new NPC(IDMakerHelper.nextID(), npcInfo.getName(), this);
			// npc.setSkin(npcInfo.getSkin());
			npc.setSkin(npcInfo.getNpcId());
			npc.setPostion(new Vector3(spwanInfo.getBound_x() / Vector3.Accuracy, spwanInfo.getBound_y() / Vector3.Accuracy, spwanInfo.getBound_z() / Vector3.Accuracy));
			field.enterField(npc);

			System.out.println("npcId :" + npc.getId() + " skinId :" + npc.getSkin());
		} else {
			System.err.println(spwanInfo.getId() + "----------" + spwanInfo.getEntityId() + " 在NpcInfo里面未找到配置");
		}
	}
}
