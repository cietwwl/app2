package com.chuangyou.xianni.battle.magicwpban.decorator;

import java.util.Set;

import com.chuangyou.common.protobuf.pb.battle.MagicwpBanExeMsgProto.MagicwpBanExeMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.magicwpban.MagicwpBanBaseDecorator;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.constant.MagicwpBanConstant;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

public class FrozenDecorator extends MagicwpBanBaseDecorator {
	static final int	BUFFER_ID	= 70003;
	private int			fixTime;

	public FrozenDecorator(Player master, int level) {
		super(master, level, MagicwpBanConstant.FROZEN);
		fixTime = Math.min(1000 + (level - 1) * 100, 2500);
	}

	public boolean isEffect() {
		return random.next(100) <= 3;
	}

	public void exe(Living target) {
		// 给target添加一个定身buff
		SkillBufferTemplateInfo temp = BattleTempMgr.getBufferInfo(BUFFER_ID);
		if (temp == null) {
			Log.error("SkillBufferTemplate not find ,the tempId : " + BUFFER_ID);
			return;
		}
		Buffer buffer = BufferFactory.createBuffer(master, target, temp);
		buffer.setAliveTime(System.currentTimeMillis() + fixTime);
		target.addBuffer(buffer);

		MagicwpBanExeMsg.Builder builder = MagicwpBanExeMsg.newBuilder();
		builder.setLivingId(getMaster().getId());
		builder.setBanlv(getLevel());
		builder.setBanSkillId(getTemp().getCode());

		Set<Long> nears = getMaster().getNears(new PlayerSelectorHelper(getMaster()));
		nears.add(getMaster().getArmyId());
		BroadcastUtil.sendBroadcastPacket(nears, Protocol.U_MAGICWP_BAN_EXE, builder.build());
	}
}
