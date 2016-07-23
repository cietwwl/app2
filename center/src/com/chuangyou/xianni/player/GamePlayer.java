package com.chuangyou.xianni.player;

import com.chuangyou.common.protobuf.pb.PostionMsgProto.PostionMsg;
import com.chuangyou.common.protobuf.pb.ReqChangeMapMsgProto.ReqChangeMapMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerAttUpdateProto.PlayerAttUpdateMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.army.ArmyInventory;
import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.campaign.CampaignInventory;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.email.EmailInventory;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
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
import com.chuangyou.xianni.friend.FriendInventory;
import com.chuangyou.xianni.magicwp.MagicwpInventory;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.mount.MountInventory;
import com.chuangyou.xianni.pet.PetInventory;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;
import com.chuangyou.xianni.player.event.PlayerSceneAttEvent;
import com.chuangyou.xianni.player.manager.PlayerManager;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.shop.ShopInventory;
import com.chuangyou.xianni.skill.SkillInventory;
import com.chuangyou.xianni.task.TaskInventory;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

public class GamePlayer extends AbstractEvent {
	public static final int		BORN_MAP	= 1005;
	private CmdTaskQueue		cmdTaskQueue;
	private ActionQueue			actionQueue;

	/** 玩家所有数据 */
	private BasePlayer			basePlayer;

	/*************************** 各模块数据管理 ****************************/
	// <<----** 共享数据，离线保留内存一段时间 ,并且提供离线加载*/---------------->>
	/** 部队数据 */
	private ArmyInventory		armyInventory;

	// <<----** 非共享数据，玩家下线时卸载 */---------------->>
	/** 邮件数据 */
	private EmailInventory		emailInventory;
	/** 好友数据 */
	private FriendInventory		friendInventory;

	/** 坐骑数据 */
	private MountInventory		mountInventory;
	/** 法宝数据 */
	private MagicwpInventory	magicwpInventory;
	/** 宠物数据 */
	private PetInventory		petInventory;

	/** 商店购购买信息 */
	private ShopInventory	shopInventory;

	/** 背包 */
	private BagInventory		bagInventory;

	/** 时装 */
	private FashionInventory	fashionInventory;

	/** 任务 */
	private TaskInventory		taskInventory;

	/** 副本 */
	private CampaignInventory	campaignInventory;
	/** 英雄技能 **/
	private SkillInventory		skillInventory;

	private Channel				channel;			// 服务器持有连接

	private int					curMapId	= -1;	// 玩家当前地图ID
	private int					curCampaign	= 0;	// 玩家当前地图ID

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
		if (friendInventory != null) {
			friendInventory.saveToDatabase();
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
	}

	// 加载共享数据
	public boolean loadShareData(PlayerInfo playerInfo, PlayerJoinInfo playerJoinInfo, PlayerTimeInfo playerTimeInfo, PlayerPositionInfo playerPositionInfo) {
		this.basePlayer = new BasePlayer(playerInfo, playerJoinInfo, playerTimeInfo, playerPositionInfo);
		regeditEvent();
		return true;
	}

	// 卸载共享数据
	public boolean unLoadShareData() {
		return false;
	}

	// 加载个人私有数据
	public boolean loadPersonData() {

		emailInventory = new EmailInventory(this);
		if (!initData(emailInventory.loadFromDataBase(), "玩家邮件")) {
			return false;
		}
		friendInventory = new FriendInventory(this);
		if (!initData(friendInventory.loadFromDataBase(), "玩家好友")) {
			return false;
		}

		mountInventory = new MountInventory(this);
		if (!initData(mountInventory.loadFromDataBase(), "玩家坐骑数据")) {
			return false;
		}

		shopInventory = new ShopInventory(this);
		if (!initData(shopInventory.loadFromDataBase(), "玩家Npc Shop数据")) {
			return false;
		}

		magicwpInventory = new MagicwpInventory(this);
		if (!initData(magicwpInventory.loadFromDataBase(), "玩家法宝数据")) {
			return false;
		}

		petInventory = new PetInventory(this);
		if (!initData(petInventory.loadFromDataBase(), "玩家宠物数据")) {
			return false;
		}

		bagInventory = new BagInventory(this);
		if (!initData(bagInventory.loadFromDataBase(), "物品背包")) {
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
		armyInventory = new ArmyInventory(this);
		if (!initData(armyInventory.loadFromDataBase(), "用户部队")) {
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

		return true;
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
		if (friendInventory != null) {
			friendInventory.unloadData();
			friendInventory = null;
		}

		if (mountInventory != null) {
			mountInventory.unloadData();
			mountInventory = null;
		}
		if (shopInventory != null) {
			shopInventory.unloadData();
			shopInventory = null;
		}

		if (magicwpInventory != null) {
			magicwpInventory.unloadData();
			magicwpInventory = null;
		}

		if (petInventory != null) {
			petInventory.unloadData();
			petInventory = null;
		}
		if (bagInventory != null) {
			bagInventory.unloadData();
			bagInventory = null;
		}
		if (fashionInventory != null) {
			fashionInventory.unloadData();
			fashionInventory = null;
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
					GamePlayer player = WorldMgr.getPlayerFromCache(basePlayer.getPlayerInfo().getPlayerId());
					if (player != null) {
						PlayerSceneAttEvent e = (PlayerSceneAttEvent) event;

						PlayerAttUpdateMsg msg = PlayerInfoSendCmd.getPropertyUpdatePacket(e.getAttType(), e.getAttValue(), basePlayer.getPlayerInfo().getPlayerId());
						player.sendPbMessage(MessageUtil.buildMessage(Protocol.S_ATTRIBUTE_SCENE_UPDATE, msg));

						if (e.getAttType() == EnumAttr.Level.getValue()) {
							player.onLevelUpdate();
						}
					}
				}
			}, EventNameType.UPDATE_PLAYER_PROPERTY_SCENE);

			basePlayer.addListener(new ObjectListener() {

				@Override
				public void onEvent(ObjectEvent event) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					GamePlayer player = WorldMgr.getPlayerFromCache(basePlayer.getPlayerInfo().getPlayerId());
					if (player != null) {
						PlayerPropertyUpdateEvent e = (PlayerPropertyUpdateEvent) event;

						PlayerAttUpdateMsg msg = PlayerInfoSendCmd.getPropertyUpdatePacket(e.getChangeMap(), basePlayer.getPlayerInfo().getPlayerId());
						player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_PLAYER_ATT_UPDATE, msg));

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
		armyInventory.updateProperty();
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
		this.basePlayer.setOnLineStatus(online);
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
			Log.error("send msg but player is not onLine" + message.getCode());
			return;
		}

		if (channel == null || channel.isActive() == false) {
			Log.error("send msg but player is not onLine1" + message.getCode());
			return;
		}
		if (message == null) {
			Log.error("send msg but player is not onLine2");
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

	public FriendInventory getFriendInventory() {
		return friendInventory;
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

	/** 回到出生点 */
	public void backBornPoint() {
		FieldInfo fieldTemp = MapProxyManager.getFieldTempInfo(BORN_MAP);
		ReqChangeMapMsg.Builder reqBuilder = ReqChangeMapMsg.newBuilder();
		PostionMsg.Builder postion = PostionMsg.newBuilder();
		postion.setMapId(BORN_MAP);
		postion.setMapKey(BORN_MAP);
		postion.setPostion(Vector3BuilderHelper.build(fieldTemp.getPosition()));
		reqBuilder.setPostionMsg(postion);
		PBMessage message = MessageUtil.buildMessage(Protocol.S_ENTERSCENE, getPlayerId(), reqBuilder);
		sendPbMessage(message);
	}
}
