package com.chuangyou.xianni.entity.activity;

import com.chuangyou.common.protobuf.pb.activity.ActivityInfoProto.ActivityInfoMsg;
import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class ActivityInfo extends DataObject {

	// /**
	// * 活动正常状态
	// */
	// public static final int STATE_NORMAL = 0;
	// /**
	// * 活动结束
	// */
	// public static final int STATE_FINISH = 1;

	private int		id;
	private long	playerId;
	// private int state;
	private int		remainTime;
	private int		param;

	public ActivityInfoMsg.Builder getMsg() {
		ActivityInfoMsg.Builder msg = ActivityInfoMsg.newBuilder();
		msg.setId(id);
		msg.setState(0);
		msg.setRemainTime(remainTime);
		msg.setParam(param);
		return msg;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// public int getState() {
	// return state;
	// }
	// public void setState(int state) {
	// if(this.state != state){
	// this.state = state;
	// this.setOp(Option.Update);
	// }
	// }
	public int getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(int remainTime) {
		if (this.remainTime != remainTime) {
			this.remainTime = remainTime;
			this.setOp(Option.Update);
		}
	}

	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		if (this.param != param) {
			this.param = param;
			this.setOp(Option.Update);
		}
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

}
