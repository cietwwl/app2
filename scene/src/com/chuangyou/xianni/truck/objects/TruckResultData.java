package com.chuangyou.xianni.truck.objects;

import java.util.List;

import com.chuangyou.common.protobuf.pb.truck.InnerReqTruckCompleteProto.InnerReqTruckComplete;

/**
 * 镖车结算数据
 * @author wkghost
 *
 */
public class TruckResultData {

	/** 成功完成 */
	public static final int STATE_SUC = 1;
	/** 超时 */
	public static final int STATE_TIMEOUT = 2;
	
	/** 镖头 */
	public static final int LEADER = 1;
	/** 镖师 */
	public static final int MEMBER = 2;
	/** 帮派成员 */
	public static final int GUILDMEMBER = 3;
	
	//镖头
	private long id;
	//镖车类型
	private int trucktype;
	//是否劫镖
	private boolean isRobbed;
	//状态
	private int state;
	//剩余材质
	private int leaveMat;
	//镖师头衔
	private int truckerType;
//	//护镖者列表
//	private List<Long> protectors;
	
	public int getTrucktype() {
		return trucktype;
	}
	public void setTrucktype(int trucktype) {
		this.trucktype = trucktype;
	}
	public boolean isRobbed() {
		return isRobbed;
	}
	public void setRobbed(boolean isRobbed) {
		this.isRobbed = isRobbed;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLeaveMat() {
		return leaveMat;
	}
	public void setLeaveMat(int leaveMat) {
		this.leaveMat = leaveMat;
	}
//	public List<Long> getProtectors() {
//		return protectors;
//	}
//	public void setProtectors(List<Long> protectors) {
//		this.protectors = protectors;
//	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getTruckerType() {
		return truckerType;
	}
	public void setTruckerType(int truckerType) {
		this.truckerType = truckerType;
	}
	
	public InnerReqTruckComplete.Builder getResultBuilder()
	{
		InnerReqTruckComplete.Builder truckCompleteMsg = InnerReqTruckComplete.newBuilder();
		truckCompleteMsg.setTrucktype(trucktype);
		truckCompleteMsg.setRobbed(isRobbed?1:0);
		truckCompleteMsg.setState(state);
		truckCompleteMsg.setMat(leaveMat);
		truckCompleteMsg.setTruckertype(truckerType);
		return truckCompleteMsg;
	}
	
}
