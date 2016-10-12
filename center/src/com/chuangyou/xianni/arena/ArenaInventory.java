package com.chuangyou.xianni.arena;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.chuangyou.common.protobuf.pb.arena.ArenaInfoMsgProto.ArenaInfoMsg;
import com.chuangyou.common.protobuf.pb.arena.ArenaOpponentProto.ArenaOpponentMsg;
import com.chuangyou.common.protobuf.pb.player.PlayerSimpleInfoMsgProto.PlayerSimpleInfoMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.activity.ActivityType;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.arena.ArenaInfo;
import com.chuangyou.xianni.entity.arena.FightData;
import com.chuangyou.xianni.entity.robot.RobotTemplate;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.reward.RewardManager;
import com.chuangyou.xianni.robot.RobotManager;
import com.chuangyou.xianni.sql.dao.ArenaInfoDao;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

/** 竞技场 */
public class ArenaInventory implements IInventory {
	GamePlayer				player;
	private ArenaInfo		arenaInfo;
	public static final int	RESET_COST	= 10;	// 重置消耗灵石

	public ArenaInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		ArenaInfoDao dao = DBManager.getArenaInfoDao();
		arenaInfo = dao.get(player.getPlayerId());
		if (arenaInfo == null) {
			arenaInfo = new ArenaInfo();
			arenaInfo.setPlayerId(player.getPlayerId());
			arenaInfo.setFreshTime(new Date());
			arenaInfo.setOp(Option.Insert);
			matching();
		}
		return true;
	}

	/** 竞技场信息 **/
	public void sendArenaInfo() {
		reset(false);
		ArenaInfoMsg.Builder infoMsg = ArenaInfoMsg.newBuilder();
		arenaInfo.writeProto(infoMsg);
		if (arenaInfo.getOpponenter1() != 0) {
			ArenaOpponentMsg.Builder aomsg = ArenaOpponentMsg.newBuilder();
			aomsg.setIndex(1);
			aomsg.setResult(arenaInfo.getResult1());
			writeArenaOpponentMsg(aomsg, arenaInfo.getOpponenter1());
			infoMsg.addOpponenterInfos(aomsg);
		}

		if (arenaInfo.getOpponenter2() != 0) {
			ArenaOpponentMsg.Builder aomsg = ArenaOpponentMsg.newBuilder();
			aomsg.setIndex(2);
			aomsg.setResult(arenaInfo.getResult2());
			writeArenaOpponentMsg(aomsg, arenaInfo.getOpponenter2());
			infoMsg.addOpponenterInfos(aomsg);
		}

		if (arenaInfo.getOpponenter3() != 0) {
			ArenaOpponentMsg.Builder aomsg = ArenaOpponentMsg.newBuilder();
			aomsg.setIndex(3);
			aomsg.setResult(arenaInfo.getResult3());
			writeArenaOpponentMsg(aomsg, arenaInfo.getOpponenter3());
			infoMsg.addOpponenterInfos(aomsg);
		}

		if (arenaInfo.getOpponenter4() != 0) {
			ArenaOpponentMsg.Builder aomsg = ArenaOpponentMsg.newBuilder();
			aomsg.setIndex(4);
			aomsg.setResult(arenaInfo.getResult4());
			writeArenaOpponentMsg(aomsg, arenaInfo.getOpponenter4());
			infoMsg.addOpponenterInfos(aomsg);
		}

		if (arenaInfo.getOpponenter5() != 0) {
			ArenaOpponentMsg.Builder aomsg = ArenaOpponentMsg.newBuilder();
			aomsg.setIndex(5);
			aomsg.setResult(arenaInfo.getResult5());
			writeArenaOpponentMsg(aomsg, arenaInfo.getOpponenter5());
			infoMsg.addOpponenterInfos(aomsg);
		}

		if (arenaInfo.getOpponenter6() != 0) {
			ArenaOpponentMsg.Builder aomsg = ArenaOpponentMsg.newBuilder();
			aomsg.setIndex(6);
			aomsg.setResult(arenaInfo.getResult6());
			writeArenaOpponentMsg(aomsg, arenaInfo.getOpponenter6());
			infoMsg.addOpponenterInfos(aomsg);
		}
		PBMessage message = MessageUtil.buildMessage(Protocol.U_ARENA_IFNO, infoMsg.build());
		player.sendPbMessage(message);
	}

	@Override
	public boolean unloadData() {
		arenaInfo = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (arenaInfo.getOp() != Option.None) {
			DBManager.getArenaInfoDao().saveOrUpdata(arenaInfo);
		}
		return true;
	}

	public void getReward(int rewardId) {
		if (arenaInfo.getWinCount() < rewardId) {
			Log.error("sendArenaReward fail ,the playerId: " + player.getPlayerId() + "  rewardId: " + rewardId + " arenaInfo.getWinCount()" + arenaInfo.getWinCount());
			return;
		}
		if (rewardId == 1 && arenaInfo.getReward1() != 0) {
			return;
		}
		if (rewardId == 4 && arenaInfo.getReward4() != 0) {
			return;
		}
		if (rewardId == 6 && arenaInfo.getReward6() != 0) {
			return;
		}
		if (!RewardManager.sendArenaReward(player, rewardId)) {
			Log.error("sendArenaReward fail ,the playerId: " + player.getPlayerId() + "  rewardId: " + rewardId);
			return;
		}
		if (rewardId == 1) {
			arenaInfo.setReward1(1);
		}
		if (rewardId == 4) {
			arenaInfo.setReward4(1);
		}
		if (rewardId == 6) {
			arenaInfo.setReward6(1);
		}
		sendArenaInfo();
	}

	public void updataWinCount(long opponent) {
		if (arenaInfo.getOpponenter1() == opponent) {
			arenaInfo.setResult1(1);
			arenaInfo.setWinCount(arenaInfo.getWinCount() + 1);
		}
		if (arenaInfo.getOpponenter2() == opponent) {
			arenaInfo.setResult2(1);
			arenaInfo.setWinCount(arenaInfo.getWinCount() + 1);
		}
		if (arenaInfo.getOpponenter3() == opponent) {
			arenaInfo.setResult3(1);
			arenaInfo.setWinCount(arenaInfo.getWinCount() + 1);
		}
		if (arenaInfo.getOpponenter4() == opponent) {
			arenaInfo.setResult4(1);
			arenaInfo.setWinCount(arenaInfo.getWinCount() + 1);
		}
		if (arenaInfo.getOpponenter5() == opponent) {
			arenaInfo.setResult5(1);
			arenaInfo.setWinCount(arenaInfo.getWinCount() + 1);
		}
		if (arenaInfo.getOpponenter6() == opponent) {
			arenaInfo.setResult6(1);
			arenaInfo.setWinCount(arenaInfo.getWinCount() + 1);
		}
		sendArenaInfo();
	}

	public boolean challenge() {
		if (arenaInfo.getChallengeCount() < 7) {
			arenaInfo.setChallengeCount(arenaInfo.getChallengeCount() + 1);
			if (player.getActivityInventory() != null) {
				player.getActivityInventory().changeActivityInfo(ActivityType.ARENA, 7 - arenaInfo.getChallengeCount());
			}
			sendArenaInfo();
			return true;
		}
		return false;
	}

	private void writeArenaOpponentMsg(ArenaOpponentMsg.Builder aomsg, long opponenterId) {
		PlayerSimpleInfoMsg.Builder simpleInfo = PlayerSimpleInfoMsg.newBuilder();
		if (RobotManager.isRobot(opponenterId)) {
			RobotManager.writeSimpInfo(simpleInfo, opponenterId, player.getBasePlayer().getPlayerInfo().getFight());
		} else {
			GamePlayer other = WorldMgr.getPlayer(opponenterId);
			if (other != null) {
				other.getBasePlayer().getPlayerInfo().writeSimpInfo(simpleInfo);
			}
		}
		aomsg.setPlayerSimpInfo(simpleInfo);
	}

	public void reset(boolean cost) {
		if ((System.currentTimeMillis() - arenaInfo.getFreshTime().getTime() > 2l * 60 * 60 * 1000) || cost) {
			matching();
			arenaInfo.reset();
			if (player.getActivityInventory() != null) {
				player.getActivityInventory().changeActivityInfo(ActivityType.ARENA, 7);
			}
			sendArenaInfo();
		}
	}

	/** 开始匹配 */
	public void matching() {
		int myFight = player.getBasePlayer().getPlayerInfo().getFight();
		int maxFight = myFight * 120 / 100;
		int minFight = myFight * 80 / 100;

		List<FightData> fds = DBManager.getPlayerInfoDao().getArenaOpponent(myFight, maxFight, minFight);
		List<FightData> highters = new ArrayList<FightData>();
		List<FightData> lower = new ArrayList<FightData>();
		List<FightData> random = new ArrayList<FightData>();

		// 优先匹配符合战斗力的玩家
		for (FightData fd : fds) {
			if (fd.getPlayerId() == player.getPlayerId()) {
				continue;
			}
			if (fd.getFight() > myFight && highters.size() < 2) {
				highters.add(fd);
				continue;
			}
			if (fd.getFight() < myFight && lower.size() < 1) {
				lower.add(fd);
				continue;
			}
			random.add(fd);
		}
		Set<Integer> chosedRobots = new HashSet<>();
		// 不足，则匹配机器人
		if (highters.size() < 2) {
			Set<RobotTemplate> higherRobots = RobotManager.getRandomRobots(20, 0, 2 - highters.size(), chosedRobots);
			highters.addAll(convert(higherRobots, myFight));
		}
		if (lower.size() < 1) {
			Set<RobotTemplate> lowerRobots = RobotManager.getRandomRobots(0, -10, 1 - lower.size(), chosedRobots);
			highters.addAll(convert(lowerRobots, myFight));
		}
		if (random.size() < 3) {
			Set<RobotTemplate> randomRobots = RobotManager.getRandomRobots(20, -10, 3 - random.size(), chosedRobots);
			random.addAll(convert(randomRobots, myFight));
		}

		List<FightData> all = new ArrayList<>();
		all.addAll(highters);
		all.addAll(lower);
		all.addAll(random);
		java.util.Collections.sort(all);
		for (int i = 0; i < all.size(); i++) {
			FightData fd = all.get(i);
			reseOpponenter(fd.getPlayerId(), i + 1);
		}

	}

	private void reseOpponenter(Long playerId, int index) {
		if (index == 1) {
			arenaInfo.setOpponenter1(playerId);
			arenaInfo.setResult1(0);
		}
		if (index == 2) {
			arenaInfo.setOpponenter2(playerId);
			arenaInfo.setResult2(0);
		}
		if (index == 3) {
			arenaInfo.setOpponenter3(playerId);
			arenaInfo.setResult3(0);
		}
		if (index == 4) {
			arenaInfo.setOpponenter4(playerId);
			arenaInfo.setResult4(0);
		}
		if (index == 5) {
			arenaInfo.setOpponenter5(playerId);
			arenaInfo.setResult5(0);
		}
		if (index == 6) {
			arenaInfo.setOpponenter6(playerId);
			arenaInfo.setResult6(0);
		}
	}

	private List<FightData> convert(Set<RobotTemplate> robots, int myFight) {
		List<FightData> list = new ArrayList<>();
		for (RobotTemplate temp : robots) {
			FightData fd = new FightData();
			fd.setPlayerId(temp.getId());
			fd.setFight(myFight + myFight * temp.getFluctuate());
			list.add(fd);
		}
		return list;
	}
}
