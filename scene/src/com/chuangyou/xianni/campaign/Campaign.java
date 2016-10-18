package com.chuangyou.xianni.campaign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignInfoMsgProto.CampaignInfoMsg;
import com.chuangyou.common.protobuf.pb.campaign.CampaignStatuMsgProto.CampaignStatuMsg;
import com.chuangyou.common.protobuf.pb.map.SpawnNodeChangeListMsgProto.SpawnNodeChangeListMsg;
import com.chuangyou.common.protobuf.pb.map.SpawnNodeChangeMsgProto.SpawnNodeChangeMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.ThreadSafeRandom;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.action.CampaignEnterAction;
import com.chuangyou.xianni.campaign.action.CampaignLeaveAction;
import com.chuangyou.xianni.campaign.action.CampainOverLeaveAction;
import com.chuangyou.xianni.campaign.action.TransferFieldAction;
import com.chuangyou.xianni.campaign.state.CampaignState;
import com.chuangyou.xianni.campaign.state.OpeningState;
import com.chuangyou.xianni.campaign.state.PrepareState;
import com.chuangyou.xianni.campaign.state.StopState;
import com.chuangyou.xianni.campaign.state.SuccessState;
import com.chuangyou.xianni.campaign.task.CTBaseCondition;
import com.chuangyou.xianni.campaign.task.CampaignTask;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignStatu;
import com.chuangyou.xianni.constant.CampaignConstant.CampaignType;
import com.chuangyou.xianni.entity.campaign.CampaignTaskTemplateInfo;
import com.chuangyou.xianni.entity.campaign.CampaignTemplateInfo;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.spawn.SpawnInfo;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.DelayAction;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.ClientProtocol;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Avatar;
import com.chuangyou.xianni.team.Team;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.spawn.SpwanNode;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

/**
 * 基础副本信息
 */
public class Campaign extends AbstractActionQueue {

	public static final int									BORN_POINT			= 1;	// 出生点
	public static final int									REVIVAL_POINT		= 2;	// 复活点
	public static final int									END_POIN			= 3;	// 副本终点
	public static final int									MONSTER_CALLER		= 4;	// 召唤阵
	public static final int									GROUP_CREATER_NODE	= 5;	// 分组节点
	public static final int									TERMINATOR			= 6;	// 副本终结者

	protected ThreadSafeRandom								random;						// 副本随机
	protected int											id;							// 唯一ID
	protected int											tempId;						// 模板ID
	protected int											teamId;
	protected String										name;						// 副本名称
	protected CampaignTemplateInfo							campaignTemplateInfo;		// 副本信息
	protected long											creater;					// 创建人
	protected Map<Long, ArmyProxy>							armys;						// 副本部队
	protected Set<ArmyProxy>								JoinArmys;					// 所有进入过
	protected Field											starField;					// 起始地图
	protected Map<Integer, Field>							allFields;					// 副本地图
	protected Map<Integer, Field>							tempFieldMapping;			// 模板ID映射
	protected Map<Integer, Integer>							indexMapping;				// 序号地图映射
	protected long											beginTime;					// 开始时间
	protected long											endTime;					// 结束时间
	protected CampaignState									state;						// 当前状态
	protected Map<Integer, SpwanNode>						spwanNodes;					// 副本所有节点
	protected SpwanNode										bornNode;					// 当前副本出生点
	protected SpwanNode										revivalNode;				// 当前副本复活点

	protected Map<Integer, List<SpwanNode>>					changeNodes;				// 发生过状态变更的节点
	// protected long expiredTime; // 副本过期时间

	protected Map<Integer, Map<Integer, List<SpwanNode>>>	teamNodes;					// 将同组的节点分组<所属召唤阵ID,<分组ID,组成员集>>
	protected CampaignTask									task;						// 挑战任务
	private int												taskId;
	private int												progress;					// 副本进度

	public Campaign(CampaignTemplateInfo tempInfo, ArmyProxy creater, int taskId) {
		super(ThreadManager.actionExecutor);
		this.id = IDMakerHelper.campaignId();
		this.tempId = tempInfo.getTemplateId();
		this.campaignTemplateInfo = tempInfo;
		this.armys = new HashMap<>();
		this.JoinArmys = new HashSet<>();
		this.allFields = new HashMap<>();
		this.spwanNodes = new HashMap<>();
		this.state = new PrepareState(this);
		this.tempFieldMapping = new HashMap<>();
		this.changeNodes = new HashMap<>();
		this.indexMapping = new HashMap<>();
		this.teamNodes = new HashMap<>();
		this.creater = creater.getPlayerId();
		this.random = new ThreadSafeRandom();
		this.taskId = taskId;
		// this.avatarIds = new HashSet<>();
	}

	/** 地图开始 */
	public void start() {
		state = new OpeningState(this);
		// 获取副本所有地图
		Map<Integer, FieldInfo> finfos = FieldTemplateMgr.getCFieldInfos(tempId);
		// 创建当前地图
		for (Entry<Integer, FieldInfo> entry : finfos.entrySet()) {
			int index = entry.getKey();
			FieldInfo temp = entry.getValue();
			// 创建地图
			Field f = FieldMgr.getIns().createCampaignField(temp.getMapKey(), temp.getType(), id);
			Map<Integer, SpwanNode> sonNodes = f.getSpawnNodes();
			// 管理节点
			spwanNodes.putAll(sonNodes);
			allFields.put(f.id, f);
			tempFieldMapping.put(f.getMapKey(), f);
			indexMapping.put(temp.getCampaignIndex(), f.id);
			// 设置为初始地图
			if (index == 1) {
				starField = f;
			}
		}
		this.beginTime = System.currentTimeMillis();
		if (campaignTemplateInfo.getOpenTime() == 0) {
			this.endTime = beginTime + 2 * 60 * 60 * 1000;
		} else {
			this.endTime = beginTime + campaignTemplateInfo.getOpenTime() * 60l * 1000;
		}

		CampaignTaskTemplateInfo ttemp = CampaignTaskTempMgr.get(taskId);
		if (ttemp != null) {
			task = new CampaignTask(this, ttemp);
		}

		// 补充分身
		ArmyProxy army = WorldMgr.getArmy(creater);
		if (getTemp().getType() == CampaignType.TEAM) {
			Team team = TeamMgr.getTeam(creater);
			if (team != null && team.getMembers().size() < 4) {
				addAvatars(army.getAvatarData(4 - team.getMembers().size()));
			}
		} else {
			addAvatars(army.getAvatarData(3));
		}
		// expiredTime = endTime;
		CampaignCheckAction action = new CampaignCheckAction(this);
		enDelayQueue(action);

	}

	/**
	 * 进入副本
	 */
	public void onPlayerEnter(ArmyProxy army) {
		CampaignEnterAction enterAction = new CampaignEnterAction(this, army, starField);
		enqueue(enterAction);
		JoinArmys.add(army);
	}

	/**
	 * 进入副本
	 */
	public void onPlayerEnter(ArmyProxy army, int mapId, Vector3 v3) {
		// 简单判断
		Field field = getEnterField(mapId);
		if (field == null) {
			field = starField;
		}
		CampaignEnterAction enterAction = new CampaignEnterAction(this, army, field, v3);
		enqueue(enterAction);
		JoinArmys.add(army);
	}

	/**
	 * 退出副本
	 */
	public void onPlayerLeave(ArmyProxy army) {
		CampaignLeaveAction action = new CampaignLeaveAction(this, army);
		enqueue(action);
	}

	/**
	 * 退出副本
	 */
	public void onOverLeave(ArmyProxy army) {
		CampainOverLeaveAction action = new CampainOverLeaveAction(this, army);
		enqueue(action);
	}

	/**
	 * 副本下线
	 */
	public void unline(ArmyProxy army) {
		CampaignLeaveAction action = new CampaignLeaveAction(this, army, true);
		enqueue(action);
	}

	/** 获取副本当前人数 */
	public int getArmyCount() {
		return armys.size();
	}

	/** 获取副本内所有部队信息 */
	public List<ArmyProxy> getAllArmys() {
		List<ArmyProxy> getAll = new ArrayList<>();
		getAll.addAll(armys.values());
		return getAll;
	}

	/** 通关副本 */
	public void passCampaign() {
		if (state instanceof SuccessState) {
			return;
		}
		state = new SuccessState(this);
		endTime = System.currentTimeMillis() + 60 * 1000;// 60秒后结束副本
		for (ArmyProxy army : getAllArmys()) {
			sendCampaignInfo(army);
			sendCampaignStatu(army);
		}

	}

	// 副本是否已经结束
	public boolean areadyOver() {
		return state.getCode() == CampaignState.STOP;
	}

	/**
	 * 副本结束
	 */
	public void over() {
		// 不会结束第二遍
		if (areadyOver()) {
			return;
		}

		state = new StopState(this);

		for (ArmyProxy army : getAllArmys()) {
			onOverLeave(army);
		}

		for (ArmyProxy army : JoinArmys) {
			sendCampaignInfo(army);
			// 通知center服务器,玩家副本销毁了
			sendCampaignStatu(army);
		}
	}

	/** 清理副本信息 */
	public void clearCampaignData() {
		this.clear();
		this.armys.clear();
		this.armys.clear();
		this.armys = null;
		this.starField = null;
		for (Field f : allFields.values()) {
			f.destroy();
		}
		this.allFields.clear();
		this.allFields = null;
		this.spwanNodes.clear();
		this.spwanNodes = null;
		tempFieldMapping.clear();
		indexMapping.clear();
		changeNodes.clear();
		teamNodes.clear();
		// avatarIds.clear();
		CampaignMgr.remove(id);

	}

	public void addArmy(ArmyProxy army) {
		armys.put(army.getPlayerId(), army);
	}

	public void removeArmy(ArmyProxy army) {
		armys.remove(army.getPlayerId());
		// if (armys.size() == 0) {
		// expiredTime = System.currentTimeMillis() + 30 * 60 * 1000;
		// }
	}

	public void setCreater(long creater) {
		this.creater = creater;
	}

	public boolean isEmpty() {
		return armys == null || armys.size() == 0;
	}

	/* 发送副本信息 */
	public void sendCampaignInfo(ArmyProxy army) {
		CampaignInfoMsg.Builder infoMsg = CampaignInfoMsg.newBuilder();
		infoMsg.setId(id);
		infoMsg.setCount(armys.size());
		infoMsg.setCreaterId(creater);
		infoMsg.setCreateTime(beginTime);
		infoMsg.setState(state.getCode());
		infoMsg.setTempId(tempId);
		infoMsg.setProgress(progress);
		int overTm = (int) (endTime - System.currentTimeMillis());
		infoMsg.setOpenTime(overTm);
		infoMsg.setBackTime(overTm);
		PBMessage message = MessageUtil.buildMessage(Protocol.U_CAMPAIGN_INFO, infoMsg);
		army.sendPbMessage(message);

		// 同步副本信息，同时同步副本任务信息
		if (task != null) {
			task.update(army);
		}
	}

	public void sendCampaignStatu(ArmyProxy army) {
		CampaignStatuMsg.Builder cstatu = CampaignStatuMsg.newBuilder();
		cstatu.setIndexId(getIndexId());
		cstatu.setTempId(tempId);
		cstatu.setTeamId(teamId);// 组队副本向上穿透兼容
		cstatu.setPlayerId(army.getPlayerId());

		if (state instanceof SuccessState) {
			cstatu.setStatu(CampaignStatu.NOTITY2C_SUCCESS);
		}
		if (state instanceof StopState) {
			cstatu.setStatu(CampaignStatu.NOTITY2C_OVER);
		}
		if (task != null) {
			cstatu.setTaskId(task.getTemp().getTaskId());
		}
		PBMessage statuMsg = MessageUtil.buildMessage(Protocol.C_CAMPAIGN_STATU, cstatu);
		army.sendPbMessage(statuMsg);
	}

	public Field getEnterField(int mapId) {
		if (mapId == 0) {
			return starField;
		} else {
			return allFields.get(mapId);
		}
	}

	/** 穿透地图 */
	public Field findField(ArmyProxy army, int mapKey) {
		// 定点传送
		if (mapKey != 0) {
			return tempFieldMapping.get(mapKey);
		}
		// 下一个地图
		Field field = allFields.get(army.getFieldId());
		if (field == null) {
			Log.error("army get next field is null ,army fieldId:" + army.getFieldId());
			return null;
		}

		int index = field.getFieldInfo().getCampaignIndex();
		return allFields.get(indexMapping.get(index));
	}

	// 传送
	public void teleport(ArmyProxy army, SpawnInfo tempinfo) {
		TransferFieldAction action = new TransferFieldAction(this, army, tempinfo);
		enqueue(action);
	}

	/* 更新节点状态 */
	public void updateSpawnInfo(SpwanNode info) {
		SpawnNodeChangeListMsg.Builder list = SpawnNodeChangeListMsg.newBuilder();
		SpawnNodeChangeMsg.Builder builder = SpawnNodeChangeMsg.newBuilder();
		builder.setNodeId(info.getSpwanId());
		builder.setStatu(info.getState().getCode());
		list.addList(builder);
		PBMessage message = MessageUtil.buildMessage(ClientProtocol.U_CAMPAIGN_NODE_INFO, list);

		for (ArmyProxy army : armys.values()) {
			army.sendPbMessage(message);
		}
		int fieldId = info.getField().id;
		List<SpwanNode> nodes = changeNodes.get(fieldId);
		if (nodes == null) {
			nodes = new ArrayList<>();
		}
		nodes.add(info);
		changeNodes.put(fieldId, nodes);
	}

	/**
	 * 进入地图后，通知该地图变更后节点信息
	 */
	public void noticeChangeNode(ArmyProxy army, Field field) {
		List<SpwanNode> nodes = changeNodes.get(field.id);
		if (nodes != null) {
			SpawnNodeChangeListMsg.Builder list = SpawnNodeChangeListMsg.newBuilder();
			for (SpwanNode node : nodes) {
				SpawnNodeChangeMsg.Builder builder = SpawnNodeChangeMsg.newBuilder();
				builder.setNodeId(node.getSpwanId());
				builder.setStatu(node.getState().getCode());
				list.addList(builder);
			}
			PBMessage message = MessageUtil.buildMessage(ClientProtocol.U_CAMPAIGN_NODE_INFO, list);
			army.sendPbMessage(message);
		}
	}

	public void addTask(CampaignTask task) {
		this.task = task;
	}

	public Map<Integer, SpwanNode> getSpwanNodes() {
		return spwanNodes;
	}

	public void changeRevivalNode(SpwanNode revivalNode) {
		SpwanNode older = this.revivalNode;
		if (older != null) {
			older.stateTransition(new com.chuangyou.xianni.warfield.spawn.OverState(older));
		}
		this.revivalNode = revivalNode;
	}

	public SpwanNode getRevivalNode() {
		return revivalNode;
	}

	public void changeBornNode(SpwanNode bornNode) {
		SpwanNode older = this.bornNode;
		if (older != null) {
			older.stateTransition(new com.chuangyou.xianni.warfield.spawn.OverState(older));
		}
		this.bornNode = bornNode;
	}

	public Vector3 getBornNode(ArmyProxy player) {
		if (bornNode != null) {
			return bornNode.getSpawnInfo().getPosition();
		}
		return null;
	}

	/** 添加副本分组管理 */
	public void addTeamNode(SpwanNode teamNode) {
		int callerId = teamNode.getSpawnInfo().getParam2();// 召唤阵id
		int teamId = teamNode.getSpawnInfo().getParam1();
		// 所属该召唤阵的所有刷怪点
		Map<Integer, List<SpwanNode>> callerOwns = teamNodes.get(callerId);
		if (callerOwns == null) {
			callerOwns = new HashMap<>();
			teamNodes.put(callerId, callerOwns);
		}
		List<SpwanNode> teamMembers = callerOwns.get(teamId);
		if (teamMembers == null) {
			teamMembers = new ArrayList<>();
			callerOwns.put(teamId, teamMembers);
		}
		teamMembers.add(teamNode);
	}

	/** 随机某组副本节点 */
	public List<SpwanNode> randomTeamNode(int tagId) {
		Map<Integer, List<SpwanNode>> callerOwns = teamNodes.get(tagId);
		if (callerOwns == null || callerOwns.size() == 0) {
			return null;
		}
		List<Integer> keys = new ArrayList<>();
		keys.addAll(callerOwns.keySet());
		if (keys.size() > 0) {
			return callerOwns.get(keys.get(random.next(keys.size())));
		}
		return null;
	}

	public Field getStarField() {
		return starField;
	}

	public int getIndexId() {
		return this.id;
	}

	public CampaignTemplateInfo getTemp() {
		return campaignTemplateInfo;
	}

	public boolean agreedToEnter(ArmyProxy army) {

		return state.getCode() == CampaignState.PREPARE || state.getCode() == CampaignState.OPENING;
	}

	public boolean isExpried() {
		return isEmpty() && state.getCode() != CampaignState.STOP && System.currentTimeMillis() >= endTime;
	}

	public boolean isClear() {
		return state.getCode() == CampaignState.STOP;
	}

	public CampaignState getState() {
		return state;
	}

	public void changeEndTime(long changeTime) {
		this.endTime = changeTime;
		for (ArmyProxy army : armys.values()) {
			sendCampaignInfo(army);
		}
	}

	public class CampaignCheckAction extends DelayAction {
		public CampaignCheckAction(Campaign campaign) {
			super(campaign, 1000);
		}

		@Override
		public void execute() {
			notifyTaskEvent(CTBaseCondition.PASS_TIME_LIMIT, 1);

			if (state.getCode() == CampaignState.STOP) {
				return;
			}
			if (System.currentTimeMillis() >= endTime) {
				over();
				return;
			}
			this.execTime = System.currentTimeMillis() + 1000;
			this.getActionQueue().enDelayQueue(this);
		}
	}

	public void notifyTaskEvent(int event, int param) {
		if (task == null) {
			return;
		}
		if (task.getConditionType() != event) {
			return;
		}
		if (state instanceof SuccessState) {
			task.notityEvent(param, true);
		} else {
			task.notityEvent(param, false);
		}
	}

	public CampaignTask getTask() {
		return task;
	}

	public SpwanNode getNode(int nodeId) {
		return spwanNodes.get(nodeId);
	}

	public void updataProgress(int progress) {
		if (progress != 0 && progress >= this.progress) {
			this.progress = progress;
			for (ArmyProxy army : getAllArmys()) {
				sendCampaignInfo(army);
			}
		}
	}

	public int getProgress() {
		return this.progress;
	}

	// 添加分身进入副本
	public void addAvatars(List<RobotInfoMsg> avatars) {
		if (avatars != null && avatars.size() > 0) {
			for (RobotInfoMsg msg : avatars) {
				createAvatar(msg);
			}
		}
	}

	private void createAvatar(RobotInfoMsg msg) {
		Avatar robot = null;
		ArmyProxy army = WorldMgr.getArmy(msg.getSimpInfo().getPlayerId());
		if (army == null) {
			return;
		}
		if (army.getAvatars(msg.getSimpInfo().getSkinId()) == null) {
			robot = new Avatar();
			robot.instill(msg);
			army.addAvatar(robot);
		} else {
			robot = army.getAvatars(msg.getSimpInfo().getSkinId());
		}
		robot.setCampaignId(getIndexId());
	}
}
