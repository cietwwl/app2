package com.chuangyou.xianni.word;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.chuangyou.common.protobuf.pb.msg.AlertInfoMsgProto.AlertInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.common.Vector3BuilderHelper;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.field.FieldInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.map.MapProxyManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.player.cmd.GamePlayerDisposeCmd;
import com.chuangyou.xianni.player.manager.PlayerManager;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.rank.RankServerManager;
import com.chuangyou.xianni.shop.ShopServerManager;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr.Players.PlayerData;

public class WorldMgr {

	private static Players			players;

	/** 在线(包括缓存)玩家等级排序表 */
	private static List<GamePlayer>	levelRankCache		= new ArrayList<>();
	/** 上次等级排序时间 */
	private static long				lastLevelRankTime	= 0;
	/** 排序最低间隔时间(单位：秒) */
	private static final long		RANK_CD				= 300;

	public static boolean init() {
		players = new Players();
		return true;
	}

	/**
	 * 获取一个玩家
	 * 
	 * @param playerId
	 * @return
	 */
	public static GamePlayer getPlayer(long playerId) {
		if (playerId <= 0) {
			return null;
		}
		// 从内存中查找
		if (players.isExist(playerId)) {
			return (GamePlayer) players.getPlayer(playerId);
		}

		// 从数据库中加载玩家角色信息
		PlayerInfo playerInfo = DBManager.getPlayerInfoDao().getPlayerInfo(playerId);
		if (playerInfo == null) {
			Log.error("数据库中不存在 playerId : " + playerId);
			return null;
		}
		PlayerJoinInfo playerJoinInfo = DBManager.getPlayerInfoDao().getJoinInfo(playerId);
		if(playerJoinInfo == null){
			playerJoinInfo = new PlayerJoinInfo();
			playerJoinInfo.setPlayerId(playerInfo.getPlayerId());

			BaseProperty initProperty = PlayerManager.getPlayerBaseProperty(1);
			playerJoinInfo.setCurSoul(initProperty.getSoul());
			playerJoinInfo.setCurBlood(initProperty.getBlood());
			playerJoinInfo.setSoul(initProperty.getSoul());
			playerJoinInfo.setBlood(initProperty.getBlood());
			playerJoinInfo.setAttack(0);
			playerJoinInfo.setDefence(0);
			playerJoinInfo.setSoulAttack(0);
			playerJoinInfo.setSoulDefence(0);
			playerJoinInfo.setAccurate(0);
			playerJoinInfo.setDodge(0);
			playerJoinInfo.setCrit(0);
			playerJoinInfo.setCritDefence(0);
			playerJoinInfo.setCritAddtion(0);
			playerJoinInfo.setCritCut(0);
			playerJoinInfo.setAttackAddtion(0);
			playerJoinInfo.setAttackCut(0);
			playerJoinInfo.setSoulAttackAddtion(0);
			playerJoinInfo.setSoulAttackCut(0);
			playerJoinInfo.setRegainSoul(0);
			playerJoinInfo.setRegainBlood(0);
			playerJoinInfo.setMetal(0);
			playerJoinInfo.setWood(0);
			playerJoinInfo.setWater(0);
			playerJoinInfo.setFire(0);
			playerJoinInfo.setEarth(0);
			playerJoinInfo.setMetalDefence(0);
			playerJoinInfo.setWoodDefence(0);
			playerJoinInfo.setWaterDefence(0);
			playerJoinInfo.setFireDefence(0);
			playerJoinInfo.setEarthDefence(0);
			playerJoinInfo.setSpeed(initProperty.getSpeed());
			playerJoinInfo.setOp(Option.Insert);
		}
		PlayerTimeInfo playerTimeInfo = DBManager.getPlayerInfoDao().getTimeInfo(playerId);
		if (playerTimeInfo == null) {
			playerTimeInfo = new PlayerTimeInfo();
			playerTimeInfo.setPlayerId(playerInfo.getPlayerId());
			playerTimeInfo.setSigleCampCount(0);
			playerTimeInfo.setChallengeCampCount(0);
			playerTimeInfo.setResetTime(new Date());
			playerTimeInfo.setOfflineTime(new Date());
			playerTimeInfo.setOp(Option.Insert);
		}
		PlayerPositionInfo playerPositionInfo = DBManager.getPlayerPositionInfoDao().get(playerId);

		if (playerPositionInfo == null) {
			playerPositionInfo = new PlayerPositionInfo();
			playerPositionInfo.setPlayerId(playerId);
			FieldInfo fieldTemp = MapProxyManager.getFieldTempInfo(SystemConfigTemplateMgr.getInitBorn());
			playerPositionInfo.setMapId(fieldTemp.getMapKey());
			playerPositionInfo.setMapTempId(fieldTemp.getMapKey());
			playerPositionInfo.setX(Vector3BuilderHelper.build(fieldTemp.getPosition()).getX());
			playerPositionInfo.setY(Vector3BuilderHelper.build(fieldTemp.getPosition()).getY());
			playerPositionInfo.setZ(Vector3BuilderHelper.build(fieldTemp.getPosition()).getZ());
		}
		// 从数据库中加载共享信息
		GamePlayer player = new GamePlayer();
		if (!player.loadShareData(playerInfo, playerJoinInfo, playerTimeInfo, playerPositionInfo)) {
			Log.error("实例化玩家 playerId : " + playerId + " ShareData 失败，请检查数据库初始化");
			return null;
		}
		players.put(playerId, player);
		return player;
	}

	/**
	 * 直接从库里获取玩家基本信息
	 * 
	 * @param nickName
	 * @return
	 */
	public static PlayerInfo getPlayerInfoFromDatabase(String nickName) {
		PlayerInfo playerInfo = null;
		List<GamePlayer> list = getOnLinePlayers();
		for (GamePlayer p : list) {
			if (p.getNickName().equals(nickName)) {
				playerInfo = p.getBasePlayer().getPlayerInfo();
				break;
			}
		}

		if (playerInfo == null) {
			playerInfo = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
		}

		return playerInfo;
	}

	/**
	 * 缓存里面获取玩家数据
	 * 
	 * @param playerId
	 * @return
	 */
	public static GamePlayer getPlayerFromCache(Long playerId) {
		if (players.isExist(playerId)) {// 从内存中查找
			return (GamePlayer) players.getPlayer(playerId);
		}
		return null;
	}

	public static long getLastUpdateTime(GamePlayer player) {
		return players.getLastUpdateTime(player.getPlayerId());
	}

	/**
	 * 玩家是否存在1
	 * 
	 * @param playerId
	 * @return
	 */
	public static boolean isExist(long playerId) {
		return players.isExist(playerId);
	}

	/**
	 * 退出
	 * 
	 * @param userId
	 * @return
	 */
	public static boolean logout(GamePlayer player) {
		if (player == null || player.getPlayerState() != PlayerState.ONLINE) {
			return false;
		}
		player.setPlayerState(PlayerState.OFFLINE);
		try {
			player.save();
			player.unLoadPersonData();
		} catch (Exception ex) {
			Log.error("用户退出失败,nickName : " + player.getNickName() + ", state : " + player.getPlayerState(), ex);
		}
		return true;
	}

	/**
	 * 获取全部玩家
	 * 
	 * @return
	 */
	public static List<GamePlayer> getAllPlayers() {
		if (players != null) {
			return players.values();
		}
		return new ArrayList<GamePlayer>();
	}

	/**
	 * 获取在线的所有玩家
	 * 
	 * @return
	 */
	public static List<GamePlayer> getOnLinePlayers() {
		List<GamePlayer> list = new ArrayList<GamePlayer>();
		Iterator<GamePlayer> it = getAllPlayers().iterator();
		while (it.hasNext()) {
			GamePlayer player = it.next();
			if (player.getPlayerState() == PlayerState.ONLINE) {
				list.add(player);
			}
		}
		return list;
	}

	/**
	 * 获取所有在线玩家
	 * 
	 * @return
	 */
	public static List<Long> getPlayerIds(Selector selector) {
		List<Long> list = new ArrayList<Long>();
		Iterator<GamePlayer> it = getAllPlayers().iterator();
		while (it.hasNext()) {
			GamePlayer player = it.next();
			if (selector == null) {
				list.add(player.getPlayerId());
			} else {
				if (selector.selectPlayer(player)) {
					list.add(player.getPlayerId());
				}
			}
		}
		return list;
	}

	/**
	 * 定时保存
	 */
	public static void save() {
		saveUserData();
		saveSystemData();
	}

	/**
	 * <pre>
	 * 保存全服玩家数据
	 * </pre>
	 */
	private static void saveUserData() {
		if (players == null) {
			return;
		}
		List<PlayerData> playerList = players.datas();
		if (playerList == null || playerList.size() == 0) {
			return;
		}
		for (PlayerData playerData : playerList) {
			if (playerData == null) {
				continue;
			}
			String nickName = "";
			try {
				GamePlayer player = playerData.getPlayer();
				nickName = player.getNickName();
				player.save();
				if (playerData.isUnLoadData()) {
					disposePlayer(player);
				}
			} catch (Exception e) {
				Log.error("保存数据出错,nickname " + nickName, e);
			}
		}
	}

	/**
	 * 删除玩家
	 * 
	 * @param player
	 */
	public static void disposePlayer(GamePlayer player) {

		short code = (short) 0;
		PBMessage pkg = new PBMessage(code, player.getPlayerId());
		player.enqueue(new CmdTask(new GamePlayerDisposeCmd(), null, pkg, player.getCmdTaskQueue()));
	}

	/**
	 * 将玩家从队列中删除
	 * 
	 * @param player
	 */
	public static void unLoadPlayer(GamePlayer player) {
		players.remove(player.getPlayerId());
		player.save();
		player.unLoadShareData();
	}

	/**
	 * <pre>
	 * 保存全服系统数据(不涉及具体玩家)
	 * </pre>
	 */
	private static void saveSystemData() {
		ShopServerManager.saveToDatabase();
		RankServerManager.getInstance().saveToDatabase();

	}

	/**
	 * 获取玩家等级排行
	 * 
	 * @return
	 */
	public static List<GamePlayer> getPlayerLevelRank() {
		if (System.currentTimeMillis() - lastLevelRankTime < RANK_CD * 1000) {
			return levelRankCache;
		}
		List<GamePlayer> playerList = new ArrayList<>();
		playerList.addAll(players.values());

		playerList.sort(new Comparator<GamePlayer>() {
			@Override
			public int compare(GamePlayer o1, GamePlayer o2) {
				// TODO Auto-generated method stub
				return Long.compare(o1.getLevel(), o2.getLevel());
			}
		});
		levelRankCache = playerList;
		lastLevelRankTime = System.currentTimeMillis();

		return levelRankCache;
	}

	static class Players {
		Hashtable<Long, PlayerData> context = new Hashtable<Long, PlayerData>();

		/**
		 * 获取一个玩家
		 * 
		 * @param playerId
		 * @return
		 */
		public GamePlayer getPlayer(long playerId) {
			if (!context.containsKey(playerId)) {
				return null;
			}
			PlayerData item = (PlayerData) context.get(playerId);
			item.setUpdateTime(TimeUtil.getSysCurSeconds());
			return item.getPlayer();
		}

		public long getLastUpdateTime(long playerId) {
			return 0;
		}

		/**
		 * 是否存在
		 * 
		 * @param playerId
		 * @return
		 */
		public boolean isExist(long playerId) {
			return context.containsKey(playerId);
		}

		/**
		 * 添加一个玩家
		 * 
		 * @param playerId
		 * @param player
		 */
		public void put(long playerId, GamePlayer player) {
			if (!context.containsKey(playerId)) {
				context.put(playerId, new PlayerData(player));
			}
		}

		/**
		 * 移除一个玩家
		 * 
		 * @param playerId
		 */
		public void remove(long playerId) {
			context.remove(playerId);
		}

		public void clear() {
			context.clear();
		}

		public List<GamePlayer> values() {
			List<GamePlayer> infos = new ArrayList<GamePlayer>();
			synchronized (context) {
				for (PlayerData info : context.values()) {
					if ((info == null) || (info.getPlayer() == null)) {
						continue;
					}
					infos.add(info.getPlayer());
				}
			}
			return infos;
		}

		public List<PlayerData> datas() {
			List<PlayerData> infos = new ArrayList<PlayerData>();
			synchronized (context) {
				infos.addAll(context.values());
			}
			return infos;
		}

		public long getLastUpdateTime(int playerId) {
			PlayerData data = context.get(playerId);
			if (data != null) {
				return data.getUpdateTime();
			}
			return 0;
		}

		public class PlayerData {
			private static final long	timeLong	= 900;	// 15分钟=900秒
			private long				createTime;
			private long				updateTime;
			private GamePlayer			player		= null;

			public PlayerData(GamePlayer player) {
				setCreateTime(TimeUtil.getSysCurSeconds());
				setUpdateTime(TimeUtil.getSysCurSeconds());
				this.setPlayer(player);
			}

			public boolean isUnLoadData() {
				if (player.getPlayerState() != PlayerState.ONLINE) {
					return TimeUtil.getSysCurSeconds() - updateTime > timeLong;
				}
				return false;
			}

			public void setCreateTime(long createTime) {
				this.createTime = createTime;
			}

			public long getCreateTime() {
				return createTime;
			}

			public void setUpdateTime(long updateTime) {
				this.updateTime = updateTime;
			}

			public long getUpdateTime() {
				return this.updateTime;
			}

			public void setPlayer(GamePlayer player) {
				this.player = player;
			}

			public GamePlayer getPlayer() {
				return player;
			}
		}
	}

	public static void resetTimeInfo() {
		List<GamePlayer> players = getAllPlayers();
		for (GamePlayer player : players) {
			if (player.getPlayerState() == PlayerState.ONLINE) {
				player.resetPlayerData();
			}
		}
	}

	// 全服提示消息
	public static void sendMsg2All(AlertInfoMsg.Builder alertMsg) {
		BroadcastUtil.sendBroadcasePacketToAll(Protocol.U_ALERT_MSG, alertMsg.build());
	}
}