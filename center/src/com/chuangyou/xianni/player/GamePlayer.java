package com.chuangyou.xianni.player;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.PlayerInfoMsgProto.PlayerInfoMsg;
import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.activeSystem.ActiveInventory;
import com.chuangyou.xianni.activity.ActivityInventory;
import com.chuangyou.xianni.arena.ArenaInventory;
import com.chuangyou.xianni.army.ArmyInventory;
import com.chuangyou.xianni.artifact.ArtifactInventory;
import com.chuangyou.xianni.avatar.AvatarInventory;
import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.campaign.CampaignInventory;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.EquipConstant;
import com.chuangyou.xianni.constant.NotifyType;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.email.EmailInventory;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.guild.GuildMemberInfo;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.equip.EquipInventory;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.event.ObjectListener;
import com.chuangyou.xianni.exec.AbstractActionQueue;
import com.chuangyou.xianni.exec.AbstractCmdTaskQueue;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.exec.CmdTaskQueue;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.fashion.FashionInventory;
import com.chuangyou.xianni.guild.GuildInventory;
import com.chuangyou.xianni.guild.manager.GuildManager;
import com.chuangyou.xianni.guild.struct.Guild;
import com.chuangyou.xianni.inverseBead.InverseBeadInventory;
import com.chuangyou.xianni.inverseBead.InverseBeadRefreshInventory;
import com.chuangyou.xianni.magicwp.MagicwpInventory;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.mount.MountInventory;
import com.chuangyou.xianni.pet.PetInventory;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;
import com.chuangyou.xianni.player.manager.PlayerManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.RelationInventory;
import com.chuangyou.xianni.shop.ShopInventory;
import com.chuangyou.xianni.skill.SkillInventory;
import com.chuangyou.xianni.soul.SoulInventory;
import com.chuangyou.xianni.space.SpaceInventory;
import com.chuangyou.xianni.state.StateInventory;
import com.chuangyou.xianni.task.TaskInventory;
import com.chuangyou.xianni.truck.TruckInventory;
import com.chuangyou.xianni.vip.PlayerVipInventory;
import com.chuangyou.xianni.welfare.WelfareConditionRecordInventory;
import com.chuangyou.xianni.welfare.WelfareInventory;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

public class GamePlayer extends AbstractEvent {
	

	private CmdTaskQueue					cmdTaskQueue;
	private ActionQueue						actionQueue;

	/** 玩家所有数据 */
	private BasePlayer						basePlayer;

	/*************************** 各模块数据管理 ****************************/
	// <<----** 共享数据，离线保留内存一段时间 ,并且提供离线加载*/---------------->>
	/** 部队数据 */

	private ArmyInventory					armyInventory;

	/** 空间数据 */
	private SpaceInventory					spaceInventory;

	/** 帮派数据 */
	private GuildInventory					guildInventory;

	// <<----** 非共享数据，玩家下线时卸载 */---------------->>
	/** 邮件数据 */
	private EmailInventory					emailInventory;

	/** 坐骑数据 */
	private MountInventory					mountInventory;
	/** 法宝数据 */
	private MagicwpInventory				magicwpInventory;
	/** 宠物数据 */
	private PetInventory					petInventory;

	/** 商店购购买信息 */
	private ShopInventory					shopInventory;

	/** 背包 */
	private BagInventory					bagInventory;

	/** 时装 */
	private FashionInventory				fashionInventory;

	/** 任务 */
	private TaskInventory					taskInventory;

	/** 副本 */
	private CampaignInventory				campaignInventory;
	/** 英雄技能 **/
	private SkillInventory					skillInventory;
	/** 天逆珠 **/
	private InverseBeadInventory			inverseBeadInventory;
	/** vip **/
	private PlayerVipInventory				playerVipInventory;

	/** 天逆珠刷新数据 **/
	private InverseBeadRefreshInventory		inverseBeadRefreshInventory;

	/** 装备 */
	private EquipInventory					equipInventory;
	/**
	 * 魂幡
	 */
	private SoulInventory					soulInventory;

	/** 关系数据 */
	private RelationInventory				relationInventory;

	/** 神器数据 */
	private ArtifactInventory				artifactInventory;
	/**
	 * 日常活动数据
	 */
	private ActivityInventory				activityInventory;

	/** 竞技场信息 */
	private ArenaInventory					arenaInventory;
	/** 分身 */
	private AvatarInventory					avatarInventory;
	/**
	 * 境界
	 */
	private StateInventory					stateInventory;

	/** 镖车信息 */
	private TruckInventory					truckInventory;
	
	/**
	 * 活跃系统
	 */
	private ActiveInventory				activeInventory;

	/** 福利信息 */
	private WelfareInventory				welfareInventory;

	/** 福利条件信息 (比如7天登录需要记录天数，在线奖励需要记录登录时间等等)*/
	private WelfareConditionRecordInventory	welfareConditionRecordInventory;
	
	private Channel							channel;						// 服务器持有连接

	private int								curMapId	= -1;				// 玩家当前地图ID
	private int								curCampaign	= 0;				// 玩家当前地图ID

	public GamePlayer() {
		cmdTaskQueue = new AbstractCmdTaskQueue(ThreadManager.cmdExecutor);
		actionQueue = new AbstractActionQueue(ThreadManager.actionExecutor);
	}

	/**
	 * 保存玩家数据
	 */
	public void save() {

		PlayerManager.save(basePlayer);

		if (emailInventory != null) {
			emailInventory.saveToDatabase();
		}
		if (mountInventory != null) {
			mountInventory.saveToDatabase();
		}
		if (shopInventory != null) {
			shopInventory.saveToDatabase();
		}
		if (magicwpInventory != null) {
			magicwpInventory.saveToDatabase();
		}
		if (petInventory != null) {
			petInventory.saveToDatabase();
		}
		if (fashionInventory != null) {
			fashionInventory.saveToDatabase();
		}
		if (armyInventory != null) {
			armyInventory.saveToDatabase();
		}
		if (bagInventory != null) {
			bagInventory.saveToDatabase();
		}
		if (taskInventory != null) {
			taskInventory.saveToDatabase();
		}
		if (campaignInventory != null) {
			campaignInventory.saveToDatabase();
		}
		if (skillInventory != null) {
			skillInventory.saveToDatabase();
		}
		if (spaceInventory != null) {
			spaceInventory.saveToDatabase();
		}

		if (inverseBeadInventory != null) {
			inverseBeadInventory.saveToDatabase();
		}
		if (inverseBeadRefreshInventory != null) {
			inverseBeadRefreshInventory.saveToDatabase();
		}
		if (playerVipInventory != null) {
			playerVipInventory.saveToDatabase();
		}

		if (equipInventory != null) {
			equipInventory.saveToDatabase();
		}

		if (soulInventory != null) {
			soulInventory.saveToDatabase();
		}

		if (relationInventory != null) {
			relationInventory.saveToDatabase();
		}

		if (artifactInventory != null) {
			artifactInventory.saveToDatabase();
		}

		if (guildInventory != null) {
			soulInventory.saveToDatabase();
		}

		if (activityInventory != null) {
			activityInventory.saveToDatabase();
		}
		if (arenaInventory != null) {
			arenaInventory.saveToDatabase();
		}
		if (truckInventory != null) {
			truckInventory.saveToDatabase();
		}
		if (stateInventory != null) {
			stateInventory.saveToDatabase();
		}
		if (guildInventory != null) {
			guildInventory.saveToDatabase();
		}
		if (avatarInventory != null) {
			avatarInventory.saveToDatabase();
		}
		if (welfareInventory != null) {
			welfareInventory.saveToDatabase();
		}
		if (welfareConditionRecordInventory != null) {
			welfareConditionRecordInventory.saveToDatabase();
		}
		if (activeInventory != null) {
			activeInventory.saveToDatabase();
		}
	}

	// 加载共享数据
	public boolean loadShareData(PlayerInfo playerInfo, PlayerJoinInfo playerJoinInfo, PlayerTimeInfo playerTimeInfo, PlayerPositionInfo playerPositionInfo) {
		this.basePlayer = new BasePlayer(playerInfo, playerJoinInfo, playerTimeInfo, playerPositionInfo);
		spaceInventory = new SpaceInventory(this);
		if (!initData(spaceInventory.loadFromDataBase(), "玩家空间数据加载")) {
			return false;
		}

		equipInventory = new EquipInventory(this);
		if (!initData(equipInventory.loadFromDataBase(), "装备数据")) {
			return false;
		}

		bagInventory = new BagInventory(this);
		if (!initData(bagInventory.loadFromDataBase(), "物品背包")) {
			return false;
		}

		magicwpInventory = new MagicwpInventory(this);
		if (!initData(magicwpInventory.loadFromDataBase(), "玩家法宝数据")) {
			return false;
		}

		mountInventory = new MountInventory(this);
		if (!initData(mountInventory.loadFromDataBase(), "玩家坐骑数据")) {
			return false;
		}

		petInventory = new PetInventory(this);
		if (!initData(petInventory.loadFromDataBase(), "玩家宠物数据")) {
			return false;
		}
		soulInventory = new SoulInventory(this);
		if (!initData(soulInventory.loadFromDataBase(), "魂幡数据")) {
			return false;
		}

		fashionInventory = new FashionInventory(this);
		if (!initData(fashionInventory.loadFromDataBase(), "玩家时装数据")) {
			return false;
		}
		skillInventory = new SkillInventory(this);
		if (!initData(skillInventory.loadFromDataBase(), "英雄技能数据")) {
			return false;
		}
		truckInventory = new TruckInventory(this);
		if (!initData(truckInventory.loadFromDataBase(), "镖车数据")) {
			return false;
		}
		stateInventory = new StateInventory(this);
		if (!initData(stateInventory.loadFromDataBase(), "境界数据加载")) {
			return false;
		}

		artifactInventory = new ArtifactInventory(this);
		if (!initData(artifactInventory.loadFromDataBase(), "神器数据")) {
			return false;
		}

		guildInventory = new GuildInventory(this);
		if (!initData(guildInventory.loadFromDataBase(), "帮派数据")) {
			return false;
		}

		avatarInventory = new AvatarInventory(this);
		if (!initData(avatarInventory.loadFromDataBase(), "分身系统")) {
			return false;
		}

		// 创建时会计算所有属性，所以要在最后面加载
		armyInventory = new ArmyInventory(this);
		if (!initData(armyInventory.loadFromDataBase(), "用户部队")) {
			return false;
		}
		regeditEvent();
		return true;
	}

	// 卸载共享数据
	public boolean unLoadShareData() {
		if (spaceInventory != null) {
			spaceInventory.unloadData();
			spaceInventory = null;
		}
		if (bagInventory != null) {
			bagInventory.unloadData();
			bagInventory = null;
		}

		if (magicwpInventory != null) {
			magicwpInventory.unloadData();
			magicwpInventory = null;
		}
		if (mountInventory != null) {
			mountInventory.unloadData();
			mountInventory = null;
		}
		if (petInventory != null) {
			petInventory.unloadData();
			petInventory = null;
		}

		if (soulInventory != null) {
			soulInventory.unloadData();
			soulInventory = null;
		}
		if (skillInventory != null) {
			skillInventory.unloadData();
			skillInventory = null;
		}

		if (equipInventory != null) {
			equipInventory.unloadData();
			equipInventory = null;
		}

		if (fashionInventory != null) {
			fashionInventory.unloadData();
			fashionInventory = null;
		}

		if (armyInventory != null) {
			armyInventory.unloadData();
			armyInventory = null;
		}
		if (artifactInventory != null) {
			artifactInventory.unloadData();
			artifactInventory = null;
		}
		if (truckInventory != null) {
			truckInventory.unloadData();
			truckInventory = null;
		}
		if (stateInventory != null) {
			stateInventory.unloadData();
			stateInventory = null;
		}

		if (guildInventory != null) {
			guildInventory.unloadData();
			guildInventory = null;
		}
		if (avatarInventory != null) {
			avatarInventory.unloadData();
			avatarInventory = null;
		}
		return true;
	}

	// 加载个人私有数据
	public boolean loadPersonData() {
		emailInventory = new EmailInventory(this);
		if (!initData(emailInventory.loadFromDataBase(), "玩家邮件")) {
			return false;
		}

		shopInventory = new ShopInventory(this);
		if (!initData(shopInventory.loadFromDataBase(), "玩家Npc Shop数据")) {
			return false;
		}

		inverseBeadRefreshInventory = new InverseBeadRefreshInventory(this);
		if (!initData(inverseBeadRefreshInventory.loadFromDataBase(), "天逆珠刷新数据")) {
			return false;
		}
		inverseBeadInventory = new InverseBeadInventory(this);
		if (!initData(inverseBeadInventory.loadFromDataBase(), "英雄天逆珠数据")) {
			return false;
		}
		playerVipInventory = new PlayerVipInventory(this);
		if (!initData(playerVipInventory.loadFromDataBase(), "玩家vip领取记录")) {
			return false;
		}

		taskInventory = new TaskInventory(this);
		if (!initData(taskInventory.loadFromDataBase(), "任务数据")) {
			return false;
		}
		campaignInventory = new CampaignInventory(this);
		if (!initData(campaignInventory.loadFromDataBase(), "副本数据")) {
			return false;
		}

		relationInventory = new RelationInventory(this);
		if (!initData(relationInventory.loadFromDataBase(), "社交关系数据")) {
			return false;
		}

		activityInventory = new ActivityInventory(this);
		if (!initData(activityInventory.loadFromDataBase(), "日常活动数据")) {
			return false;
		}

		// 创建竞技场信息
		arenaInventory = new ArenaInventory(this);
		if (!initData(arenaInventory.loadFromDataBase(), "用户竞技场")) {
			return false;
		}

		// 运镖
		truckInventory = new TruckInventory(this);
		if (!initData(truckInventory.loadFromDataBase(), "镖车数据")) {
			return false;
		}

		// 福利信息
		welfareInventory = new WelfareInventory(this);
		if (!welfareInventory.loadFromDataBase()) {
			return false;
		}

		// 7天礼包信息
		setWelfareConditionRecordInventory(new WelfareConditionRecordInventory(this));
		if (!welfareConditionRecordInventory.loadFromDataBase()) {
			return false;
		}

		// 添加境界任务监听
		if (stateInventory != null) {
			stateInventory.addAllTrigger();
		}
		
		activeInventory = new ActiveInventory(this);
		if (!initData(activeInventory.loadFromDataBase(), "活跃系统数据")) {
			return false;
		}

		return true;
	}

	// 执行一些登录相关的逻辑操作
	public void login() {
		resetPlayerData();
		// 福利登录时的初始化工作
		welfareInventory.login();
		//福利条件登录时需要处理的逻辑
		welfareConditionRecordInventory.login();
	}

	// 卸载个人私有数据
	public boolean unLoadPersonData() {
		PlayerManager.logOut(basePlayer);

		if (taskInventory != null) {
			taskInventory.unloadData();
			taskInventory = null;
		}
		if (emailInventory != null) {
			emailInventory.unloadData();
			emailInventory = null;
		}

		if (shopInventory != null) {
			shopInventory.unloadData();
			shopInventory = null;
		}

		if (campaignInventory != null) {
			campaignInventory.unloadData();
			campaignInventory = null;
		}

		if (relationInventory != null) {
			relationInventory.unloadData();
			relationInventory = null;
		}
		if (activityInventory != null) {
			activityInventory.unloadData();
			activityInventory = null;
		}
		if (inverseBeadInventory != null) {
			inverseBeadInventory.unloadData();
			inverseBeadInventory = null;
		}
		if (playerVipInventory != null) {
			playerVipInventory.unloadData();
			playerVipInventory = null;
		}
		if (inverseBeadRefreshInventory != null) {
			inverseBeadRefreshInventory.unloadData();
			inverseBeadRefreshInventory = null;
		}
		if (arenaInventory != null) {
			arenaInventory.unloadData();
			arenaInventory = null;
		}

		if (stateInventory != null) {
			stateInventory.removeStateTrigger();
		}
		
		if(activeInventory!=null){
			activeInventory.unloadData();
			activeInventory = null;
		}
		if (welfareInventory != null) {
			welfareInventory.unloadData();
			welfareInventory = null;
		}
		if (welfareConditionRecordInventory != null) {
			welfareConditionRecordInventory.unloadData();
			welfareConditionRecordInventory = null;
		}
		return true;
	}

	/**
	 * 注册各种事件
	 */
	public void regeditEvent() {
		if (basePlayer != null) {
			basePlayer.addListener(new ObjectListener() {
				@Override
				public void onEvent(ObjectEvent event) {
					GamePlayer player = WorldMgr.getPlayerFromCache(basePlayer.getPlayerInfo().getPlayerId());
					if (player != null) {
						player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_G_PLAYERINFO, PlayerInfoSendCmd.getProperPacket(player)));
					}
				}
			}, EventNameType.UPDATE_PLAYER_PROPERTY_ALL);

			basePlayer.addListener(new ObjectListener() {

				@Override
				public void onEvent(ObjectEvent event) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					GamePlayer player = WorldMgr.getPlayerFromCache(basePlayer.getPlayerInfo().getPlayerId());
					if (player != null) {
						PlayerPropertyUpdateEvent e = (PlayerPropertyUpdateEvent) event;

						Map<Integer, Long> userMap = new HashMap<>();
						Map<Integer, Long> sceneMap = new HashMap<>();

						for (int attType : e.getChangeMap().keySet()) {
							EnumAttr attr = EnumAttr.getEnumAttrByValue(attType);
							if (attr.getNotifyType() == NotifyType.NOTIFY_USER) {
								userMap.put(attType, e.getChangeMap().get(attType));
							} else {
								sceneMap.put(attType, e.getChangeMap().get(attType));
							}
						}

						if (userMap.size() > 0) {
							PlayerAttUpdateMsg msg = PlayerInfoSendCmd.getPropertyUpdatePacket(userMap, basePlayer.getPlayerInfo().getPlayerId());
							player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, msg));
						}
						if (sceneMap.size() > 0) {
							PlayerAttUpdateMsg msg = PlayerInfoSendCmd.getPropertyUpdatePacket(sceneMap, basePlayer.getPlayerInfo().getPlayerId());
							player.sendPbMessage(MessageUtil.buildMessage(Protocol.S_ATTRIBUTE_UPDATE, msg));
						}

						if (e.getChangeMap().get(EnumAttr.Level.getValue()) != null) {
							player.onLevelUpdate();
						}
					}
				}
			}, EventNameType.UPDATE_PLAYER_PROPERTY);
		}
	}

	/**
	 * 升级处理
	 */
	private void onLevelUpdate() {
		armyInventory.getArmy().getHero().addTemp(PlayerManager.getTempProperty(this));
		armyInventory.updatePropertyOnlevelUp();
	}

	private boolean initData(boolean initResult, String componentName) {
		if (!initResult) {
			Log.error(String.format("用户%s:加载%s模块数据失败", getPlayerId(), componentName));
		}
		return initResult;
	}

	public short getPlayerState() {
		return basePlayer.getOnLineStatus();
	}

	public CmdTaskQueue getCmdTaskQueue() {
		return cmdTaskQueue;
	}

	public void enqueue(CmdTask cmdTask) {
		cmdTaskQueue.enqueue(cmdTask);
	}

	public ActionQueue getActionQueue() {
		return actionQueue;
	}

	public void setActionQueue(ActionQueue actionQueue) {
		this.actionQueue = actionQueue;
	}

	public long getPlayerId() {
		return basePlayer.getPlayerInfo().getPlayerId();
	}

	public void setPlayerState(short online) {
		if (online == PlayerState.OFFLINE) {
			this.setPlayerOfflineTime(new Date());
		}
		boolean isChange = false;
		if (online != this.basePlayer.getOnLineStatus()) {
			isChange = true;
		}
		this.basePlayer.setOnLineStatus(online);

		// 更新帮派玩家在线信息
		if (isChange) {
			GuildManager.getIns().playerStateUpdate(this);
		}
	}

	public void setPlayerOfflineTime(Date offlineTime) {
		this.basePlayer.getPlayerTimeInfo().setOfflineTime(offlineTime);
	}

	public Date getPlayerOfflineTime() {
		return this.basePlayer.getPlayerTimeInfo().getOfflineTime();
	}

	public EmailInventory getEmailInventory() {
		return emailInventory;
	}

	public String getNickName() {
		// TODO Auto-generated method stub
		return this.basePlayer.getPlayerInfo().getNickName();
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public void setCmdTaskQueue(CmdTaskQueue cmdTaskQueue) {
		this.cmdTaskQueue = cmdTaskQueue;
	}

	public void sendPbMessage(PBMessage message) {
		if (this.getPlayerState() == PlayerState.OFFLINE) {
			// Log.error("send msg but player is not onLine, code : " +
			// message.getCode() + " playerId :" + getPlayerId(), new
			// Exception());
			return;
		}

		if (channel == null || channel.isActive() == false) {
			Log.error("send msg but player is not onLine1,code :" + message.getCode() + " playerId :" + getPlayerId());
			return;
		}
		if (message == null) {
			Log.error("send msg but player is not onLine2 , playerId :" + getPlayerId());
			return;
		}
		message.setPlayerId(getPlayerId());
		try {
			channel.write(message);
			channel.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BasePlayer getBasePlayer() {
		return basePlayer;
	}

	public void setBasePlayer(BasePlayer basePlayer) {
		this.basePlayer = basePlayer;
	}

	public MountInventory getMountInventory() {
		return mountInventory;
	}

	public ShopInventory getShopInventory() {
		return shopInventory;
	}

	public MagicwpInventory getMagicwpInventory() {
		return magicwpInventory;
	}

	public PetInventory getPetInventory() {
		return petInventory;
	}

	public SkillInventory getSkillInventory() {
		return skillInventory;
	}

	public BagInventory getBagInventory() {
		return bagInventory;
	}

	public void setBagInventory(BagInventory bagInventory) {
		this.bagInventory = bagInventory;
	}

	public FashionInventory getFashionInventory() {
		return fashionInventory;
	}

	public CampaignInventory getCampaignInventory() {
		return campaignInventory;
	}

	public int getCurMapId() {
		return curMapId;
	}

	public void setCurMapId(int curMapId) {
		this.curMapId = curMapId;
	}

	public Channel getChannel() {
		return channel;
	}

	public ArmyInventory getArmyInventory() {
		return armyInventory;
	}

	public void setArmyInventory(ArmyInventory armyInventory) {
		this.armyInventory = armyInventory;
	}

	public EquipInventory getEquipInventory() {
		return equipInventory;
	}

	public String toString() {
		return "playerId : " + this.getPlayerId() + " NickName :" + this.basePlayer.getPlayerInfo().getNickName();
	}

	public TaskInventory getTaskInventory() {
		return taskInventory;
	}

	public int getCampaignCount() {
		return basePlayer.getPlayerTimeInfo().getSigleCampCount();
	}

	public void dcrCampaignCount(int count) {
		this.basePlayer.getPlayerTimeInfo().setSigleCampCount(getCampaignCount() - count);
	}

	public int getCurCampaign() {
		return curCampaign;
	}

	public void setCurCampaign(int curCampaign) {
		this.curCampaign = curCampaign;
	}

	public int getLevel() {
		return this.basePlayer.getPlayerInfo().getLevel();
	}

	public InverseBeadInventory getInverseBeadInventory() {
		return inverseBeadInventory;
	}

	public InverseBeadRefreshInventory getInverseBeadRefreshInventory() {
		return inverseBeadRefreshInventory;
	}

	public PlayerVipInventory getPlayerVipInventory() {
		return playerVipInventory;
	}

	/** 回到出生点 */
	public void backBornPoint() {
		int born_map = SystemConfigTemplateMgr.getReBorn();
		FieldInfo fieldTemp = MapProxyManager.getFieldTempInfo(born_map);
		ReqChangeMapMsg.Builder reqBuilder = ReqChangeMapMsg.newBuilder();
		PostionMsg.Builder postion = PostionMsg.newBuilder();
		postion.setMapId(born_map);
		postion.setMapKey(born_map);
		postion.setPostion(Vector3BuilderHelper.build(fieldTemp.getPosition()));
		reqBuilder.setPostionMsg(postion);
		PBMessage message = MessageUtil.buildMessage(Protocol.S_ENTERSCENE, getPlayerId(), reqBuilder);
		sendPbMessage(message);
	}

	public SpaceInventory getSpaceInventory() {
		return spaceInventory;
	}

	public SoulInventory getSoulInventory() {
		return soulInventory;
	}

	public RelationInventory getRelationInventory() {
		return relationInventory;
	}

	public ArtifactInventory getArtifactInventory() {
		return artifactInventory;
	}

	public void setArtifactInventory(ArtifactInventory artifactInventory) {
		this.artifactInventory = artifactInventory;
	}

	public ArenaInventory getArenaInventory() {
		return arenaInventory;
	}

	public GuildInventory getGuildInventory() {
		return guildInventory;
	}

	/** 重置玩家数据 */
	public void resetPlayerData() {
		try {
			if (!TimeUtil.dataCompare5(getBasePlayer().getPlayerTimeInfo().getResetTime())) {
				getBasePlayer().getPlayerTimeInfo().reset();
				PlayerInfoSendCmd.sendPlayerTimeData(this);
			}
		} catch (Exception e) {
			Log.error("resetPlayerData", e);
		}
	}

	public ActivityInventory getActivityInventory() {
		return activityInventory;
	}

	public TruckInventory getTruckInventory() {
		return truckInventory;
	}

	public StateInventory getStateInventory() {
		return stateInventory;
	}

	public void writePlayerInfoProto(PlayerInfoMsg.Builder proto) {
		this.getBasePlayer().getPlayerInfo().writeProto(proto, SystemConfigTemplateMgr.getIntValue("bag.initGridNum"));
		// 武器觉醒等级
		BaseItem weapon = this.getBagInventory().getBag(BagType.HeroEquipment).getItemByPos(EquipConstant.EquipPosition.weaponPosition);
		if (weapon != null) {
			proto.setWeaponAwaken(weapon.getItemInfo().getAwaken());
		}

		// 帮派信息
		proto.setGuildId(0);
		proto.setGuildName("");
		proto.setGuildJob(0);
		Guild guild = GuildManager.getIns().getPlayerGuild(this.getPlayerId());
		if (guild != null) {
			GuildMemberInfo member = guild.getMember(this.getPlayerId());
			if (member != null) {
				proto.setGuildId(guild.getGuildInfo().getGuildId());
				proto.setGuildName(guild.getGuildInfo().getName());
				proto.setGuildJob(member.getJob());
			}
		}
	}

	public AvatarInventory getAvatarInventory() {
		return avatarInventory;
	}

	public ActiveInventory getActiveInventory() {
		return activeInventory;
	}

	public WelfareInventory getWelfareInventory() {
		return welfareInventory;
	}

	public void setWelfareInventory(WelfareInventory welfareInventory) {
		this.welfareInventory = welfareInventory;
	}

	public WelfareConditionRecordInventory getWelfareConditionRecordInventory() {
		return welfareConditionRecordInventory;
	}

	public void setWelfareConditionRecordInventory(WelfareConditionRecordInventory welfareConditionRecordInventory) {
		this.welfareConditionRecordInventory = welfareConditionRecordInventory;
	}

	public void setSoulInventory(SoulInventory soulInventory) {
		this.soulInventory = soulInventory;
	}

}
