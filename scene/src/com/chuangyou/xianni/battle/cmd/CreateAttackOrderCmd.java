package com.chuangyou.xianni.battle.cmd;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.battle.AttackOrderProto.AttackOrderMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.OrderFactory;
import com.chuangyou.xianni.battle.action.OrderExecAction;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.constant.BattleModeCode;
import com.chuangyou.xianni.entity.buffer.LivingState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.helper.RoleConstants.RoleType;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.AllSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_ARMY_ATTACK, desc = "输入攻击指令")
public class CreateAttackOrderCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		AttackOrderMsg orderMsg = AttackOrderMsg.parseFrom(packet.toByteArray());
		int skillActionId = orderMsg.getSkillActionId();
		System.out.println(orderMsg);
		// 该玩家是否具有此技能
		Player player = army.getPlayer();
		if (!player.hasSkillId(skillActionId)) {
			Log.error("army has not skill,skillId :" + skillActionId + " armyId:" + army.getPlayerId());
			return;
		}

		Skill skill = player.getSkill(skillActionId);
		if (skill == null) {
			Log.error("skill is null,playerId : " + player.getArmyId() + "  skillActionId:" + skillActionId);
			return;
		}

		Field field = FieldMgr.getIns().getField(army.getFieldId());
		if (field == null) {
			Log.error("army field is empty ,armyId :" + army.getPlayerId() + " fieldId :" + army.getFieldId());
			return;
		}

		int battleMode = player.getBattleMode();
		boolean isFlicker = false;// 是否闪烁名称
		// 技能目标
		List<Living> targets = new ArrayList<>();

		String startTime = field.getFieldInfo().getStartBattleTime();
		String endTime = field.getFieldInfo().getEndBattleTime();

		// System.out.println(field.getFieldInfo().getMapKey());
		for (long targetId : orderMsg.getTargetsList()) {
			Living living = field.getLiving(targetId);
			if (living != null) {
				// System.out.println("living.getType():" + living.getType() + " " + battleMode + " vs " + living.getBattleMode() + " living.ID = " + living.getId());
				if (living.getType() == RoleType.player) {
					if (field.getFieldInfo().isBattle()) {// pk 地图才能攻击
						// if (((Player) living).getSimpleInfo().getLevel() < 35)
						// continue;
						if (player.getBattleMode() == BattleModeCode.sectsBattleMode) {
							if (player.getTeamId() != 0 && player.getTeamId() == ((Player) living).getTeamId())// 队友
								continue;
						}
						if (startTime != null && endTime != null && TimeUtil.checkPeriod(startTime, endTime)) {// 受保护时间
							if (((Player) living).getColour(living.getPkVal()) == BattleModeCode.white) {// 受地图保护
								continue;
							}
						}
					} else {
						continue;
					}
				}
				targets.add(living);
				if (battleMode == BattleModeCode.warBattleMode && living.getBattleMode() == BattleModeCode.peaceBattleMode && living.getType() == RoleType.player)
					isFlicker = true;
			}
		}
		// System.out.println(" targets.size(): " + targets.size());
		if (isFlicker && player.getPkVal() == 0)
			player.changeFlickerName(true);

		long attackId = IDMakerHelper.attackId();
		// 生成战斗指令
		AttackOrder order = OrderFactory.createAttackOrder(player, skill, targets, attackId);

		// System.out.println("orderMsg.getCurrent() = " +
		// orderMsg.getCurrent());
		// System.out.println("orderMsg.getPosition() = " +
		// orderMsg.getPosition());

		// 施法位置
		order.setCurrent(orderMsg.getCurrent());
		// 目标位置
		order.setPostion(orderMsg.getPosition());
		OrderExecAction oaction = new OrderExecAction(player, order);
		player.enqueue(oaction);
		// System.err.println("触发攻击包 - skillActionId - " + skillActionId);
		Vector3 current = Vector3BuilderHelper.get(orderMsg.getCurrent());
		Vector3 target = Vector3BuilderHelper.get(orderMsg.getPosition());
		// System.out.println(current + " - " + target + " - " +
		// Vector3.Equal(current, target));
		// if (!player.checkStatus(LivingState.MOVE)) {
		// ((Monster) living).stop(false);
		// return;
		// }
		if (!Vector3.Equal(current, target) && player.checkStatus(LivingState.ATTACK_MOVE))
			NotifyNearHelper.notifyHelper(field, army, target, new AllSelectorHelper(army.getPlayer()));
		/////////

	}

}
