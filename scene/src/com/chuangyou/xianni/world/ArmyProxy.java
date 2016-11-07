package com.chuangyou.xianni.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.chuangyou.common.protobuf.pb.ChangeMapResultMsgProto.ChangeMapResultMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.army.ArmyInfoReloadMsgProto.ArmyInfoReloadMsg;
import com.chuangyou.common.protobuf.pb.army.PetInfoProto.PetInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PlayerPositionInfoProto.PlayerPositionInfoMsg;
import com.chuangyou.common.protobuf.pb.army.PropertyMsgProto.PropertyMsg;
import com.chuangyou.common.protobuf.pb.army.RobotInfoProto.RobotInfoMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerManaUpdateProto.PlayerManaUpdateMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerSomeThingUpdateProto.PlayerSomeThingUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.campaign.Campaign;
import com.chuangyou.xianni.campaign.CampaignMgr;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.common.templete.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnterMapResult;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.AbstractCmdTaskQueue;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.exec.CmdTaskQueue;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.CenterProtocol;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.role.helper.IDMakerHelper;
import com.chuangyou.xianni.role.objects.Avatar;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Pet;
import com.chuangyou.xianni.role.objects.Player;
import com.chuangyou.xianni.role.objects.PlotRobot;
import com.chuangyou.xianni.team.NotifyHelper;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.field.Field;
import com.chuangyou.xianni.warfield.helper.NotifyNearHelper;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;
import com.chuangyou.xianni.warfield.template.FieldTemplateMgr;
import io.netty.channel.Channel;

/**
 * 部队代理
 */
public class ArmyProxy extends AbstractActionQueue {
	private CmdTaskQueue			cmdTaskQueue;
	private long					playerId;							// 用户ID
	private String					site;								// 站点（跨服用）
	private Channel					channel;							// 连接器
	private int						fieldId;							// 地图ID，用户退出，回写到center
	// private Vector3 position; // 主角位置，用户退出，回写到center

	private Player					player;								// 英雄
	private Pet						pet;								// 宠物
	private List<RobotInfoMsg>		avatarDatas	= new ArrayList<>();	// 分身数据

	private Map<Integer, Avatar>	avatars		= new HashMap<>();		// 分身

	private short					onlineStatu	= PlayerState.OFFLINE;

	private ArmyPositionRecord		posRecord;							// 玩家位置信息

	private List<PlotRobot>			followers;							// 副本内跟随的剧情人物

	public ArmyProxy(long playerId, String site, Channel channel, SimplePlayerInfo simplePlayerInfo) {
		super(ThreadManager.actionExecutor);
		this.playerId = playerId;
		this.site = site;
		this.channel = channel;
		this.cmdTaskQueue = new AbstractCmdTaskQueue(ThreadManager.cmdExecutor);
		this.posRecord = new ArmyPositionRecord();
	}

	/**
	 * 像客户端发送数据包
	 * 
	 * @param packet
	 */
	public void sendPbMessage(PBMessage packet) {
		if (packet == null) {
			return;
		}
		packet.setPlayerId(playerId);
		if (channel == null || channel.isActive() == false) {
			return;
		}
		try {
			channel.write(packet);
			channel.flush();
			if (packet.getCode() == Protocol.U_BATTLE_RESULT) {
				System.out.println("packet.getPlayerId() = " + packet.getPlayerId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 切换地图 */
	public void changeField(Field field, Vector3 postion) {
		// 切地图时，销毁剧情角色
		if (followers != null && followers.size() > 0) {
			for (PlotRobot robot : followers) {
				if (robot.getField() != null) {
					robot.getField().leaveField(robot);
				}
				if (robot.getLivingState() != Living.DISTORY) {
					robot.clearData();
				}
			}
			followers.clear();
		}
		leave();
		join(field, postion);
		NotifyHelper.notifyInfo(this);
	}

	private void leave() {
		Field field = FieldMgr.getIns().getField(getFieldId());
		if (field != null) {
			Vector3 v3 = field.getFieldInfo().getPosition();
			player.setPostion(v3);
			field.leaveField(player);
			if (pet != null && pet.getField() != null) {
				pet.getField().leaveField(pet);
			}
			for (Avatar avatar : avatars.values()) {
				if (avatar.getField() != null) {
					avatar.getField().leaveField(avatar);
				}
			}
			setFieldId(0);
		} else {
			Log.error("------是否存在离开地图失败的日志-------" + this.getPlayerId());
		}
	}

	private void join(Field field, Vector3 postion) {
		player.setPostion(postion);
		// 接受进场保护
		player.setProtection(true);
		setFieldId(field.id);
		field.enterField(player);
		if (pet != null) {
			pet.setPostion(postion);
			pet.setProtection(true);
			field.enterField(pet);
		}
		if (player.isCorrespondStatu()) {
			return;
		}
		instillAvatar(field, postion);

	}

	public void cancelProtection() {
		player.setProtection(false);
		if (pet != null) {
			pet.setProtection(false);
		}
		for (Avatar avatar : avatars.values()) {
			avatar.setProtection(false);
		}

	}

	// 添加分身
	public void addAvatar(Avatar avatar) {
		Avatar older = avatars.get(avatar.getSkin());
		if (older != null && older != avatar) {
			older.suicide();
			older.clearData();
		}
		avatars.put(avatar.getSkin(), avatar);
	}

	// 获取玩家分身
	public List<Avatar> getAvatars() {
		List<Avatar> finds = new ArrayList<>();
		finds.addAll(avatars.values());
		return finds;
	}

	// 获取玩家分身
	public Avatar getAvatars(int tempId) {
		return avatars.get(tempId);
	}

	/**
	 * 更新宠物信息
	 * 
	 * @param petInfo
	 */
	public void updatePet(PetInfoMsg petInfo) {

		if (pet == null) {
			pet = new Pet(playerId, IDMakerHelper.nextID());
			pet.readPetInfo(petInfo);
			pet.setPostion(player.getPostion());

			player.getField().enterField(pet);

		} else {
			if (pet.getSkin() != petInfo.getPetTempId()) {
				pet.getField().leaveField(pet);
				pet.destory();
				pet = new Pet(playerId, IDMakerHelper.nextID());
				pet.readPetInfo(petInfo);
				pet.setPostion(player.getPostion());

				player.getField().enterField(pet);;
			} else {
				PlayerAttUpdateMsg.Builder attUpdateMsg = PlayerAttUpdateMsg.newBuilder();
				if (petInfo.getPetSoul() != pet.getPetSoul()) {
					PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
					proMsg.setType(EnumAttr.PetSoul.getValue());
					proMsg.setTotalPoint(petInfo.getPetSoul());
					attUpdateMsg.addAtt(proMsg);
					pet.setPetSoul(petInfo.getPetSoul());
				}
				if (petInfo.getPetPhysique() != pet.getPetPhysique()) {
					PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
					proMsg.setType(EnumAttr.PetPhysique.getValue());
					proMsg.setTotalPoint(petInfo.getPetPhysique());
					attUpdateMsg.addAtt(proMsg);
					pet.setPetPhysique(petInfo.getPetPhysique());
				}
				if (petInfo.getPetQuality() != pet.getPetQuality()) {
					PropertyMsg.Builder proMsg = PropertyMsg.newBuilder();
					proMsg.setType(EnumAttr.PetQuality.getValue());
					proMsg.setTotalPoint(petInfo.getPetQuality());
					attUpdateMsg.addAtt(proMsg);
					pet.setPetQuality(petInfo.getPetQuality());
				}
				if (attUpdateMsg.getAttList() != null && attUpdateMsg.getAttList().size() > 0) {
					attUpdateMsg.setPlayerId(pet.getId());
					Set<Long> nears = pet.getNears(new PlayerSelectorHelper(pet));
					NotifyNearHelper.notifyAttrChange(nears, attUpdateMsg.build());
				}
			}
		}
	}

	public void notifyMana() {
		// 总值回写给center
		PlayerManaUpdateMsg.Builder msg = PlayerManaUpdateMsg.newBuilder();
		msg.setMana(this.getPlayer().getMana());
		PBMessage p = MessageUtil.buildMessage(Protocol.C_PLAYER_MANA_WRITEBACK, msg);
		this.sendPbMessage(p);

		PlayerAttUpdateMsg.Builder resp = PlayerAttUpdateMsg.newBuilder();
		resp.setPlayerId(this.getPlayerId());
		PropertyMsg.Builder propertyMsg = PropertyMsg.newBuilder();
		propertyMsg.setType(EnumAttr.MANA.getValue());
		propertyMsg.setTotalPoint(this.getPlayer().getMana());
		resp.addAtt(propertyMsg);

		// 通知自己
		PBMessage selfPkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, resp);
		this.sendPbMessage(selfPkg);

		// 通知附近玩家
		Set<Long> nears = this.getPlayer().getNears(new PlayerSelectorHelper(this.getPlayer()));
		NotifyNearHelper.notifyAttrChange(nears, resp.build());
	}

	// 仙气改变
	public void notifyAvatarEnergy(int cost) {

		PlayerAttUpdateMsg.Builder resp = PlayerAttUpdateMsg.newBuilder();
		resp.setPlayerId(this.getPlayerId());
		PropertyMsg.Builder propertyMsg = PropertyMsg.newBuilder();
		propertyMsg.setType(EnumAttr.AVATAR_ENERGY.getValue());
		propertyMsg.setTotalPoint(this.getPlayer().getAvatarEnergy());
		resp.addAtt(propertyMsg);
		// 通知自己
		PBMessage selfPkg = MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, resp);
		this.sendPbMessage(selfPkg);

		if (cost != 0) {
			// 消费值回写给center
			PlayerSomeThingUpdateMsg.Builder msg = PlayerSomeThingUpdateMsg.newBuilder();
			msg.setCount(cost);
			PBMessage p = MessageUtil.buildMessage(Protocol.C_AVATAR_ENERGY_COST, msg);
			this.sendPbMessage(p);
		}
	}

	private static final EnumAttr[] reloadAttrs = { EnumAttr.CUR_SOUL, EnumAttr.CUR_BLOOD, EnumAttr.MANA };

	public synchronized void unload() {
		Field field = FieldMgr.getIns().getField(getFieldId());
		try {
			ArmyInfoReloadMsg.Builder armyReload = ArmyInfoReloadMsg.newBuilder();
			for (EnumAttr attr : reloadAttrs) {
				PropertyMsg.Builder pbulider = PropertyMsg.newBuilder();
				pbulider.setType(attr.getValue());
				pbulider.setTotalPoint(player.getProperty(attr.getValue()));
				armyReload.addPropertys(pbulider.build());
			}

			if (field != null && player.getPostion() != null) {
				PostionMsg.Builder postion = PostionMsg.newBuilder();
				postion.setMapId(this.fieldId);
				postion.setMapKey(field.getMapKey());

				Vector3 curPos = player.getPostion();
				PBVector3.Builder pbPos = Vector3BuilderHelper.build(curPos);
				postion.setPostion(pbPos.build());
				armyReload.setPostion(postion.build());

				Campaign campaign = CampaignMgr.getCampagin(field.getCampaignId());
				if (campaign != null) {
					campaign.onPlayerLeave(this, true);
				}
				// 离开地图
				field.leaveField(player);
				if (pet != null && pet.getField() != null) {
					pet.getField().leaveField(pet);
				}
				// 分身离开副本
				for (Avatar avatar : avatars.values()) {
					if (avatar.getField() != null) {
						avatar.getField().leaveField(avatar);
					}
				}

			}
			PBMessage redata = MessageUtil.buildMessage(CenterProtocol.C_PLAYER_RELOAD_SCENCE_DATA, armyReload);
			sendPbMessage(redata);
		} catch (Exception e) {
			Log.error("unload player error,playerId :" + getPlayerId(), e);
		} finally {
			player.clearData();
			if (pet != null) {
				pet.clearData();
			}
			unlodAvatar();
			// 清理跟随剧情
			if (followers != null) {
				for (PlotRobot robot : followers) {
					robot.clearData();
				}
			}
		}
	}

	/** 加载分身数据 */
	public void loadAvatarData(List<RobotInfoMsg> datas) {
		avatarDatas.clear();
		List<Avatar> keeper = new ArrayList<>();
		for (RobotInfoMsg data : datas) {
			avatarDatas.add(data);
			Avatar older = avatars.get(data.getSimpInfo().getSkinId());
			if (older != null) {
				older.instill(data);
				keeper.add(older);
				continue;
			}
			Avatar robot = new Avatar();
			robot.instill(data);
			keeper.add(robot);
			addAvatar(robot);
		}
		List<Avatar> temp = new ArrayList<>(avatars.values());
		for (Avatar beClear : temp) {
			if (!keeper.contains(beClear)) {
				beClear.suicide();
				if (beClear.getField() != null) {
					beClear.getField().leaveField(beClear);
				}
				beClear.clearData();
				avatars.remove(beClear.getSkin());
			}
		}
		instillAvatar(getPlayer().getField(), getPlayer().getPostion());
		keeper.clear();
	}

	/** 获取分身数据 */
	public void instillAvatar(Field field, Vector3 postion) {
		if (field != null && field.getCampaignId() != 0 && !getPlayer().isCorrespondStatu()) {
			Campaign campaign = CampaignMgr.getCampagin(field.getCampaignId());
			if (campaign == null) {
				return;
			}
			if (!campaign.isBuilder(getPlayerId())) {
				return;
			}
			int count = campaign.getJoinAvaterCount();
			count = count > avatars.size() ? avatars.size() : count;
			int i = 1;
			for (Avatar avatar : avatars.values()) {
				if (i > count) {
					break;
				}
				if (avatar.getField() != null || avatar.getField() != field) {
					avatar.setPostion(postion);
					avatar.setProtection(true);
					field.enterField(avatar);
					avatar.setCampaignId(field.getCampaignId());
				}
			}
		}
	}

	/** 卸载分身数据 */
	public void unlodAvatar() {
		for (Avatar avatar : avatars.values()) {
			avatar.clearData();
		}
		avatars.clear();
		avatarDatas.clear();
	}

	public void updataPostion(int tempId, int mapId, Vector3 pos) {
		FieldInfo mapTemp = FieldTemplateMgr.getFieldTemp(posRecord.getMapTempId());
		if (mapTemp != null && mapTemp.getType() == 1) {
			posRecord.setPreMapId(posRecord.getMapTempId());
			posRecord.setPreMapTempId(posRecord.getMapTempId());
			posRecord.setPrePos(posRecord.getPos());
		}

		if (mapId != 0 && tempId != 0) {
			posRecord.setMapId(mapId);
			posRecord.setMapTempId(tempId);
			posRecord.setPos(pos);
		}
	}

	/** 重载位置信息 */
	public void readPositionMsg(PlayerPositionInfoMsg msg) {
		posRecord.setPreMapId(msg.getPrePos().getMapId());
		posRecord.setPreMapTempId(msg.getPrePos().getMapKey());
		Vector3 preV3 = Vector3BuilderHelper.get(msg.getPrePos().getPostion());
		posRecord.setPrePos(preV3);

		posRecord.setMapId(msg.getCurPos().getMapId());
		posRecord.setMapTempId(msg.getCurPos().getMapKey());
		Vector3 V3 = Vector3BuilderHelper.get(msg.getCurPos().getPostion());
		posRecord.setPos(V3);
	}

	public void returnBornMap() {
		Field field = FieldMgr.getIns().getField(SystemConfigTemplateMgr.getInitBorn());
		FieldInfo fieldTemp = FieldTemplateMgr.getFieldTemp(SystemConfigTemplateMgr.getInitBorn());
		Vector3 vector3 = fieldTemp.getPosition();
		changeField(field, vector3);

		ChangeMapResultMsg.Builder cmbuilder = ChangeMapResultMsg.newBuilder();
		cmbuilder.setResult(EnterMapResult.SUCCESS);// 进入成功
		PostionMsg.Builder postionMsg = PostionMsg.newBuilder();
		postionMsg.setMapId(field.id);
		postionMsg.setMapKey(field.getMapKey());
		postionMsg.setPostion(Vector3BuilderHelper.build(vector3));
		cmbuilder.setPostion(postionMsg);
		sendPbMessage(MessageUtil.buildMessage(Protocol.C_ENTER_SENCE_MAP_RESULT, cmbuilder));
	}

	// 添加跟随者
	public void addPlotFollower(PlotRobot robot) {
		if (followers == null) {
			followers = new ArrayList<>();
		}
		followers.add(robot);
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public CmdTaskQueue getCmdTaskQueue() {
		return cmdTaskQueue;
	}

	public void enqueue(CmdTask cmdTask) {
		cmdTaskQueue.enqueue(cmdTask);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public short getOnlineStatu() {
		return onlineStatu;
	}

	public void setOnlineStatu(short onlineStatu) {
		this.onlineStatu = onlineStatu;
	}

	public ArmyPositionRecord getPosRecord() {
		return posRecord;
	}

}
