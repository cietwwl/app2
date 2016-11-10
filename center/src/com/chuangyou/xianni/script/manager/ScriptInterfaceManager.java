package com.chuangyou.xianni.script.manager;

import java.util.List;

import com.chuangyou.common.protobuf.pb.Vector3Proto.PBVector3;
import com.chuangyou.common.protobuf.pb.campaign.CreateCampaignByNpcProto.CreateCampaignByNpcMsg;
import com.chuangyou.common.protobuf.pb.gather.CreatePrivateMonsterInnerProto.CreatePrivateMonsterInnerMsg;
import com.chuangyou.common.protobuf.pb.map.TransferTriggerProto.TransferTriggerMsg;
import com.chuangyou.common.protobuf.pb.npcDialog.HintRespProto.HintRespMsg;
import com.chuangyou.common.util.ChangeCharsetUtil;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.constant.DamageEffecterType;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.npcDialog.NpcCommand;
import com.chuangyou.xianni.npcDialog.manager.NpcDialogManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.word.WorldMgr;

public class ScriptInterfaceManager {

	/**
	 * 发送NPC命令列表给客户端
	 * 
	 * @param playerId
	 * @param list
	 * @param content
	 */
	public static void sendNpcCmdListToClient(long playerId, List<NpcCommand> list, String content) {
		NpcDialogManager.sendResultToClient(playerId, list, content);
	}

	/**
	 * 发送提示消息给客户端
	 * 
	 * @param playerId
	 * @param content
	 */
	public static void sendHintToClient(long playerId, String content) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null)
			return;
		HintRespMsg.Builder resp = HintRespMsg.newBuilder();
		resp.setContent(ChangeCharsetUtil.toUTF_8(content));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SENDHINT, resp);
		player.sendPbMessage(pkg);
	}

	/**
	 * 主角切换地图
	 * 
	 * @param playerId
	 * @param mapId
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void changeMap(long playerId, int mapId, int x, int y, int z, int angle) {
		MapProxyManager.changeMap(playerId, mapId, x, y, z, angle);
	}

	/**
	 * 查询人物身上是否有某个任务
	 * 
	 * @param playerId
	 * @param taskId
	 * @return
	 */
	public static boolean isHasTask(long playerId, int taskId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null)
			return false;
		RealTask info = player.getTaskInventory().getTaskInfos().get(taskId);
		if (info == null) {
			return false;
		} else {
			if (info.getInfo().getState() == TaskInfo.ACCEPT) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 添加物品接口
	 * 
	 * @param playerId
	 * @param templateId
	 * @param num
	 * @param isBind
	 * @return
	 */
	public static boolean addItemFromGather(long playerId, int templateId, int count, boolean isBind) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null)
			return false;
		return player.getBagInventory().addItem(templateId, count, ItemAddType.GATHER_ADD, isBind);
	}

	/**
	 * 开箱子获得物品
	 * 
	 * @param playerId
	 * @param templateId
	 * @param count
	 * @param isBind
	 * @return
	 */
	public static boolean addItemFromOpenItem(long playerId, int templateId, int count, boolean isBind) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null)
			return false;
		return player.getBagInventory().addItem(templateId, count, ItemAddType.OPEN_BOX, isBind);
	}

	/**
	 * 刷私有怪物
	 * 
	 * @param playerId
	 * @param monsterId
	 */
	public static void createPrivateMonster(long playerId, int monsterId, int leaveTime, int mapId) {
		CreatePrivateMonsterInnerMsg.Builder msg = CreatePrivateMonsterInnerMsg.newBuilder();
		msg.setMonsterId(monsterId);
		msg.setPlayerId(playerId);
		msg.setLeaveTime(leaveTime);
		msg.setMapId(mapId);

		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_CREATE_PIRVATE_MONSTER, msg);
		GatewayLinkedSet.send2Server(pkg);
	}

	/**
	 * 刷私有怪物
	 * 
	 * @param playerId
	 * @param monsterId
	 * @param x
	 * @param y
	 * @param z
	 */
	public static void createPrivateMonster(long playerId, int monsterId, int x, int y, int z, int leaveTime, int mapId) {
		CreatePrivateMonsterInnerMsg.Builder msg = CreatePrivateMonsterInnerMsg.newBuilder();
		msg.setMonsterId(monsterId);
		msg.setPlayerId(playerId);
		msg.setLeaveTime(leaveTime);
		msg.setMapId(mapId);
		PBVector3.Builder pos = PBVector3.newBuilder();
		pos.setX(x);
		pos.setY(y);
		pos.setZ(z);
		msg.setPos(pos);

		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_CREATE_PIRVATE_MONSTER, msg);
		GatewayLinkedSet.send2Server(pkg);
	}

	public static void createCampaingByOnceNpc(long playerId, int campaignId, long npcId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null) {
			Log.error("createCampaingByOnceNpc  but not find gamePlayer,playerId :" + playerId);
			return;
		}
		CreateCampaignByNpcMsg.Builder builder = CreateCampaignByNpcMsg.newBuilder();
		builder.setPlayerId(playerId);
		builder.setCampaignId(campaignId);
		builder.setNpcId(npcId);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.S_CREATE_CAMPAIGN_BY_NPC, builder.build());
		player.sendPbMessage(pkg);

	}

	/**
	 * 触发动态传送点
	 * 
	 * @param playerId
	 * @param npcEntityId
	 */
	public static void triggerTransferNpc(long playerId, long npcEntityId) {
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			TransferTriggerMsg.Builder transferMsg = TransferTriggerMsg.newBuilder();
			transferMsg.setId(npcEntityId);

			PBMessage pkg = MessageUtil.buildMessage(Protocol.S_TRANSFER_TRIGGER, transferMsg);
			player.sendPbMessage(pkg);
		}
	}

	/**
	 * 获取玩家等级
	 * 
	 * @param playerId
	 */
	public static int getPlayerLevel(long playerId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null) {
			return 0;
		}
		return player.getLevel();
	}

	/**
	 * 获取玩家名字
	 * 
	 * @param playerId
	 * @return
	 */
	public static String getPlayerName(long playerId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null) {
			return "";
		}
		return player.getNickName();
	}

	/**
	 * 获取玩家职业
	 * 
	 * @param playerId
	 * @return
	 */
	public static int getPlayerJob(long playerId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getBasePlayer() == null || player.getBasePlayer().getPlayerInfo() == null) {
			return 0;
		}
		return player.getBasePlayer().getPlayerInfo().getJob();
	}

	/**
	 * 获取玩家境界等级
	 * 
	 * @param playerId
	 * @return
	 */
	public static int getPlayerState(long playerId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getBasePlayer() == null || player.getBasePlayer().getPlayerInfo() == null) {
			return 0;
		}
		return player.getBasePlayer().getPlayerInfo().getStateLv();
	}

	/**
	 * 获取玩家背包剩余空间
	 * 
	 * @param playerId
	 * @return
	 */
	public static int getPlayerBagSpace(long playerId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getBagInventory() == null) {
			return 0;
		}
		return player.getBagInventory().getEmptyCount();
	}

	/**
	 * 只加气血
	 * 
	 * @param playerId
	 * @param addNum
	 */
	public static void addCurBlood(long playerId, int addNum) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return;
		}
		player.getArmyInventory().addSoulAndBlood(addNum, DamageEffecterType.BLOOD);
	}

	/**
	 * 只加魂血
	 * 
	 * @param playerId
	 * @param addNum
	 */
	public static void addCurSoul(long playerId, int addNum) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return;
		}
		player.getArmyInventory().addSoulAndBlood(addNum, DamageEffecterType.SOUL);
	}

	/**
	 * 加血，先提升元魂，再提升气血
	 * 
	 * @param playerId
	 * @param addNum
	 */
	public static void addCurSoulOrBlood(long playerId, int addNum) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return;
		}
		player.getArmyInventory().addSoulAndBlood(addNum, DamageEffecterType.COMMON);
	}

	/**
	 * 给玩家加buff
	 * 
	 * @param playerId
	 * @param buffTempId
	 */
	public static void addBuff(long playerId, int buffTempId) {
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return;
		}
		player.getArmyInventory().addBuff(buffTempId);
	}

	/**
	 * 发送公告
	 * 
	 * @param channel
	 * @param content
	 */
	public static void sendNotice(int channel, String content) {
		ChatManager.sendSystemChatMsg(channel, content);
	}

	/**
	 * 发送触发者所在场景中才能收到的公告
	 * 
	 * @param playerId
	 * @param channel
	 * @param content
	 */
	public static void sendSceneNotice(long playerId, int channel, String content) {
		ChatManager.sendSceneSystemChatMsg(playerId, channel, content);
	}

	/**
	 * 获取物品颜色
	 * 
	 * @param itemTempId
	 */
	public static byte getItemColor(int itemTempId) {
		ItemTemplateInfo itemTempInfo = ItemManager.findItemTempInfo(itemTempId);
		if (itemTempInfo == null) {
			return 0;
		}
		return itemTempInfo.getItemcolor();
	}

	/**
	 * 获取物品名字
	 * 
	 * @param itemTempId
	 * @return
	 */
	public static String getItemName(int itemTempId) {
		ItemTemplateInfo itemTempInfo = ItemManager.findItemTempInfo(itemTempId);
		if (itemTempInfo == null) {
			return "";
		}
		return itemTempInfo.getName();
	}
	
	
	/**
	 * 激活指定分身
	 * @param playerId
	 * @param tempId
	 * @return
	 */
	public static boolean activeAvatar(long playerId,int tempId){
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return false;
		}
		player.getAvatarInventory().scriptActiveAvatar(tempId);
		return true;
	}
	
	/**
	 * 激活指定宠物
	 * @param playerId
	 * @param tempId
	 * @return
	 */
	public static boolean activePet(long playerId,int tempId){
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return false;
		}
		player.getPetInventory().activePet(tempId);
		return true;
	}
	
	/**
	 * 激活指定法宝
	 * @param playerId
	 * @param tempId
	 * @return
	 */
	public static boolean activeMagicwp(long playerId,int tempId){
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player == null || player.getArmyInventory() == null) {
			return false;
		}
		player.getMagicwpInventory().activeMagicwp(tempId);
		return true;
	}
	
	
}
