package com.chuangyou.xianni.role.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.chuangyou.common.protobuf.pb.PlayerLeaveGridProto.PlayerLeaveGridMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.objects.ActiveLiving;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.grid.GridItem;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshTriangle;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class UpdatePositionActionUtil {

	public static void updataCurPos(ActiveLiving living) {
		// 移动了多长时间
		long moveTime = System.currentTimeMillis() - living.getMoveCounter();
		// 更新剩余移动时间
		living.setMoveTime(living.getMoveTime() - (int) moveTime);
		// 该时间范围能移动目标位置
		Vector3 target = MathUtils.GetVector3InDistance(living.getPostion(), living.getGoal(), getStep(living.getSpeed() / 100, (int) moveTime));
		// 当前位置不可站立，停止移动
		// if (!isValidPoint(monster, target)) {
		// monster.stop(true);
		// return;
		// }
		// 移动完成移动
		if (living.getMoveTime() <= 0) {
			setPostion(living, living.getGoal());
			living.setMoveCounter(0);
			living.setGoal(Vector3.Invalid);
			living.removeInvincibleBuffer();
		} else {
			setPostion(living, target);
			living.setMoveCounter(System.currentTimeMillis());
		}
	}

	/**
	 * AI对象通知附近的对象
	 */
	protected static void setPostion(ActiveLiving monster, Vector3 cur) {

		Field f = monster.getField();
		if (f == null) {
			Log.error("setPostion but f is null");
			return;
		}
		GridItem curGI = f.getGrid().getGridItem(monster.getPostion());
		GridItem tarGI = f.getGrid().getGridItem(cur);
		if (curGI == null || tarGI == null) {
			return; // 找不到对应的格子， 返回。。
		}
		if (curGI.id == tarGI.id) {
			monster.setPostion(cur);
			return; // 当前格子与目标格子一致
		}

		PlayerSelectorHelper selector = new PlayerSelectorHelper(monster);
		// 获取目前周围的玩家
		Set<Long> oldNears = monster.getNears(selector);
		// 设置位置
		monster.setPostion(cur);
		// 再获取更新后的周围玩家
		Set<Long> newNears = monster.getNears(selector);
		// 交集
		List<Long> intersection = new ArrayList<Long>(oldNears);
		intersection.retainAll(newNears);

		// 离开场景通知 老玩家集合 同移动集合的 差集
		oldNears.removeAll(intersection);

		// 通知离开
		if (oldNears.size() > 0) {
			// System.err.println("有玩家离开视野");
			// notifyLeaveGrid(army, oldNears);
			PlayerLeaveGridMsg.Builder leaveMsg = PlayerLeaveGridMsg.newBuilder();
			for (Long id : oldNears) {
				ArmyProxy oldNearArmy = WorldMgr.getArmy(id);
				if (oldNearArmy == null)
					continue;
				leaveMsg.setId(monster.getId());
				PBMessage leavepkgToOther = MessageUtil.buildMessage(Protocol.U_LEAVE_GRID, leaveMsg);
				oldNearArmy.sendPbMessage(leavepkgToOther);
			}
		}

		// 进入新场景通知 新玩家集合同移动集合的差集
		newNears.removeAll(intersection);
		// 通知进入
		if (newNears.size() > 0) {
			// System.err.println("有玩家进入视野");
			// notifyAttSnap(army, newNears);
			for (Long id : newNears) {
				ArmyProxy newNearArmy = WorldMgr.getArmy(id);
				if (newNearArmy == null)
					continue;
				newNearArmy.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_ATT_SNAP, monster.getAttSnapMsg()));
			}
		}
	}

	/**
	 * 是否为有效的点
	 * 
	 * @param point
	 * @return
	 */
	private static boolean isValidPoint(Monster monster, Vector3 point) {
		if (monster == null || monster.getField() == null) {
			return false;
		}
		NavmeshSeeker seeker = FieldMgr.getIns().GetSeekerTemp(monster.getField().getFieldInfo().getResName());
		NavmeshTriangle tri = seeker.getTriangle(point);
		return tri != null;
	}

	/**
	 * 获取步长(s = vt)
	 * 
	 * @param speed
	 * @return
	 */
	protected static float getStep(float speed, int moveTime) {
		return speed * moveTime * 0.001f;
	}

}
