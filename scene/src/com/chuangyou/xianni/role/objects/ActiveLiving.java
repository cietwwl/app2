package com.chuangyou.xianni.role.objects;

import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.PlayerMoveBoardcastProto.PlayerMoveBoardcastMsg;
import com.chuangyou.common.protobuf.pb.PlayerStopBoardcastProto.PlayerStopBoardcastMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.helper.selectors.AllSelectorHelper;
import com.chuangyou.xianni.warfield.helper.selectors.MonsterSelectorHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.warfield.navi.NavigationManager;
import com.chuangyou.xianni.warfield.navi.exector.NavigationTask;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeekerStatuCode;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshTriangle;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class ActiveLiving extends Living {

	/// 目标
	protected Vector3 goal;
	/// 路径
	protected List<Vector3> path;
	/// 移动所需要的时间
	protected int moveTime;
	/// 寻路等待中
	protected boolean navWaiting = false;
	private boolean navFail = false;

	public boolean isNavFail() {
		return navFail;
	}

	public void setNavFail(boolean navFail) {
		this.navFail = navFail;
	}

	public boolean isNavWaiting() {
		return navWaiting;
	}

	public ActiveLiving(long id) {
		super(id);
	}

	public ActiveLiving(long playerId, long id) {
		super(playerId, id);
	}

	public Vector3 getGoal() {
		return goal;
	}

	/**
	 * 寻路到指定的点
	 * 
	 * @param position
	 */
	public void navigateto(Vector3 target) {
		navWaiting = true;
		NavigationTask task = new NavigationTask(getField().id, id, getPostion(), target);
		NavigationManager.instance.addTask(task);
	}

	/**
	 * 移动到目标点
	 * 
	 * @param goal
	 */
	public void moveto(Vector3 goal) {
		this.goal = goal;
		this.moveTime = (int) ((Vector3.distance(getPostion(), goal) / getSpeed()) * 1000);
		// if (id == 1000000000033L)
		// System.out.println("moveto " + id + " 目标位：" + goal + " 起始位：" +
		// this.getPostion() + " this.moveTime: " + this.moveTime + " getSpeed:
		// " + getSpeed());

		setTargetPostion(goal);
		Set<Long> nearPlayers = getNears(new PlayerSelectorHelper(this));
		for (Long id : nearPlayers) {
			ArmyProxy neararmy = WorldMgr.getArmy(id);
			if (neararmy == null)
				continue;
			PlayerMoveBoardcastMsg.Builder msg = PlayerMoveBoardcastMsg.newBuilder();
			msg.setId(getId());
			msg.setCur(Vector3BuilderHelper.build(getPostion()));
			msg.setTar(Vector3BuilderHelper.build(this.goal));
			msg.setPreArriveTargetServerTime(System.currentTimeMillis());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_BC_MOVE, msg);
			neararmy.sendPbMessage(pkg);

		}
	}

	public static void main(String[] args) {
		Vector3 invalid = new Vector3(40.41069F, 1.0F, -119.632835F);
		Vector3 invalid2 = new Vector3(40.163776F, 1.0F, -120.601875F);

		float a = Vector3.distance(invalid, invalid2);
		System.out.println(a);
		for (float i = a * 1000; i > 0; i -= 100) {
			invalid = MathUtils.GetVector3InDistance(invalid, invalid2, 0.1F);
			System.out.println(invalid);
		}
	}

	public void arrial() {
		if (path != null && path.size() > 0) {
			moveto(this.path.remove(0));
		} else {
			stop(false);
		}
	}

	/**
	 * 停止移动
	 */
	public void stop(boolean need2Client) {
		this.moveTime = 0;
		this.goal = Vector3.Invalid;
		this.targetPostion = Vector3.Invalid;
		if (this.path != null)
			this.path.clear();
		if (need2Client) {
			Set<Long> nearPlayers = getNears(new PlayerSelectorHelper(this));
			for (Long id : nearPlayers) {
				ArmyProxy neararmy = WorldMgr.getArmy(id);
				if (neararmy == null)
					continue;
				PlayerStopBoardcastMsg.Builder msg = PlayerStopBoardcastMsg.newBuilder();
				msg.setId(getId());
				msg.setCur(Vector3BuilderHelper.build(getPostion()));
				PBMessage pkg = MessageUtil.buildMessage(Protocol.U_BC_STOP, msg);
				neararmy.sendPbMessage(pkg);
			}
		}
	}

	/**
	 * 到达目的地
	 * 
	 * @return
	 */
	public boolean isArrial() {
		// if (id == 1000000000035L)
		// System.out.println("isArrial this.moveTime = "+id+" " +
		// this.moveTime);
		return this.moveTime <= 0;
	}

	public int getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(int moveTime) {
		// System.err.println("setMoveTime = " + moveTime);
		this.moveTime = moveTime;
	}

	/**
	 * 寻路到达目的地
	 */
	public void navigateComplete(NavmeshSeekerStatuCode code, List<Vector3> path) {
		System.err.println("code = " + code + " path.size = " + path.size() + " xxxx " + (code == NavmeshSeekerStatuCode.Success));
		navWaiting = false;
		if (code == NavmeshSeekerStatuCode.Success) {
			this.path = path;
			moveto(this.path.remove(0));
		} else {
			setNavFail(true);
			goal = Vector3.Invalid;
			moveTime = 0;
			if (this.path != null)
				this.path.clear();
		}
	}

	/**
	 * 移动指定的位置
	 * 
	 * @param position
	 */
	@Override
	public void setPostion(Vector3 position) {
		/* 碰撞检测 */
		// 获取需要检测的对象
		// Set<Long> oldNears = this.getNears(new MonsterSelectorHelper(this));
		// Vector3 cur = getPostion();
		// for (Long id : oldNears) {
		// Living living = field.getLiving(id.intValue());
		// if (living == null)
		// continue;
		// Vector3 livingCur = living.getPostion();
		//
		// }
		/* 碰撞检测 */

		if (field != null)
			field.getGrid().moveto(this, position);
		super.setPostion(position);
	}

	/**
	 * 是否为有效的点
	 * 
	 * @param point
	 * @return
	 */
	protected boolean isValidPoint(Vector3 point) {
		if (getField() == null) {
			return false;
		}
		try {
			NavmeshSeeker seeker = FieldMgr.getIns().GetSeekerTemp(getField().getFieldInfo().getResName());
			NavmeshTriangle tri = seeker.getTriangle(point);
			return tri != null;
		} catch (Exception e) {
			Log.error("isValidPoint ", e);
		}
		return false;

	}

}
