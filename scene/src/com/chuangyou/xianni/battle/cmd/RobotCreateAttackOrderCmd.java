package com.chuangyou.xianni.battle.cmd;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.battle.RobotAttackOrderProto.RobotAttackOrderMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.battle.AttackOrder;
import com.chuangyou.xianni.battle.OrderFactory;
import com.chuangyou.xianni.battle.action.OrderExecAction;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.entity.buffer.LivingState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Robot;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.AllSelectorHelper;
import com.chuangyou.xianni.world.AbstractCommand;
import com.chuangyou.xianni.world.ArmyProxy;

@Cmd(code = Protocol.S_ROBOT_ATTACK, desc = "输入攻击指令")
public class RobotCreateAttackOrderCmd extends AbstractCommand {

	@Override
	public void execute(ArmyProxy army, PBMessage packet) throws Exception {
		RobotAttackOrderMsg orderMsg = RobotAttackOrderMsg.parseFrom(packet.toByteArray());
		int skillActionId = orderMsg.getSkillActionId();

		// 该玩家是否具有此技能
		Living l = army.getPlayer().getField().getLiving(orderMsg.getRobotId());
		if (l == null || (l.getType() != RoleType.robot && l.getType() != RoleType.avatar && l.getType() != RoleType.plot)) {
			Log.error("RobotCreateAttackOrderCmd but robot is not find ,playerId :" + army.getPlayerId() + " robot :" + orderMsg.getRobotId());
			return;
		}

		Robot robot = (Robot) l;
		if (robot.isDie()) {
			return;
		}
		Skill skill = robot.getSkill(skillActionId);
		if (skill == null) {
			Log.error("skill is null,playerId : " + robot.getArmyId() + "  skillActionId:" + skillActionId);
			return;
		}
		
		// 判断技能是否被冻结
		int type = skill.getSkillTempateInfo().getMasterType();
		if (type == SkillMainType.COMMON_ATTACK && !robot.checkStatus(LivingState.NORMAL_ATTACK)) {
			return;
		}
		if (type == SkillMainType.ACTIVE && !robot.checkStatus(LivingState.SKILL_ATTAK)) {
			return;
		}
		if (type == SkillMainType.PASSIVE && !robot.checkStatus(LivingState.PERKS)) {
			return;
		}
		// 技能是否在CD中
		if (!skill.canUse()) {
			Log.error("client req attack ,but the skill is not in cd,skillId:" + skillActionId);
			return;
		}

		Field field = FieldMgr.getIns().getField(army.getFieldId());
		if (field == null) {
			Log.error("army field is empty ,armyId :" + army.getPlayerId() + " fieldId :" + army.getFieldId());
			return;
		}

		// 技能目标
		List<Living> targets = new ArrayList<>();
		for (long targetId : orderMsg.getTargetsList()) {
			Living living = field.getLiving(targetId);
			if (living != null) {
				targets.add(living);
			}
		}

		long attackId = IDMakerHelper.attackId();
		// 生成战斗指令
		AttackOrder order = OrderFactory.createAttackOrder(robot, skill, targets, attackId);

		// 施法位置
		order.setCurrent(orderMsg.getCurrent());
		// 目标位置
		order.setPostion(orderMsg.getPosition());
		OrderExecAction oaction = new OrderExecAction(robot, order);
		robot.enqueue(oaction);
		// System.err.println("触发攻击包 - skillActionId - " + skillActionId);
		Vector3 current = Vector3BuilderHelper.get(orderMsg.getCurrent());
		Vector3 target = Vector3BuilderHelper.get(orderMsg.getPosition());

		if (!Vector3.Equal(current, target) && robot.checkStatus(LivingState.ATTACK_MOVE))
			NotifyNearHelper.notifyHelper(field, robot, target, new AllSelectorHelper(army.getPlayer()));

	}

}
